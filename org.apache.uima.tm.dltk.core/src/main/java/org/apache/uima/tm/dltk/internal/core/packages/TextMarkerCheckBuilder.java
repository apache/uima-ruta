package org.apache.uima.tm.dltk.internal.core.packages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.tm.dltk.core.CodeModel;
import org.apache.uima.tm.dltk.core.TextMarkerPlugin;
import org.apache.uima.tm.dltk.core.TextMarkerProblems;
import org.apache.uima.tm.dltk.parser.ast.declarations.TextMarkerPackageDeclaration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverities;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.IBuildpathEntry;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension2;
import org.eclipse.dltk.core.builder.IScriptBuilder.DependencyResponse;
import org.eclipse.dltk.core.environment.EnvironmentPathUtils;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.eclipse.osgi.util.NLS;


public class TextMarkerCheckBuilder implements IBuildParticipant, IBuildParticipantExtension,
        IBuildParticipantExtension2 {

  private final IScriptProject project;

  private final IInterpreterInstall install;

  private final PackagesManager manager = PackagesManager.getInstance();

  private final TextMarkerBuildPathPackageCollector packageCollector = new TextMarkerBuildPathPackageCollector();

  private final Map codeModels = new HashMap();

  private final Map resourceToModuleInfos = new HashMap();

  private final Set knownPackageNames;

  private final Set buildpath;

  private static class ModuleInfo {
    final List requireDirectives;

    final IProblemReporter reporter;

    public ModuleInfo(IProblemReporter reporter, List requireDirectives) {
      this.reporter = reporter;
      this.requireDirectives = requireDirectives;
    }

  }

  /**
   * @param project
   * @throws CoreException
   * @throws IllegalStateException
   *           if associated interpreter could not be found
   */
  public TextMarkerCheckBuilder(IScriptProject project) throws CoreException, IllegalStateException {
    this.project = project;
    install = ScriptRuntime.getInterpreterInstall(project);
    if (install == null) {
      // thrown exception is caught in the TextMarkerPackageCheckerType
      throw new IllegalStateException(NLS.bind(Messages.TextMarkerCheckBuilder_interpreterNotFound,
              project.getElementName()));
    }
    knownPackageNames = manager.getPackageNames(install);
    buildpath = getBuildpath(project);
  }

  private int buildType;

  public boolean beginBuild(int buildType) {
    this.buildType = buildType;
    if (buildType != FULL_BUILD) {
      // retrieve packages provided by this project
      packageCollector.getPackagesProvided().addAll(
              manager.getInternalPackageNames(install, project));
    }
    loadProvidedPackagesFromRequiredProjects();
    return true;
  }

  private void loadProvidedPackagesFromRequiredProjects() {
    final IBuildpathEntry[] resolvedBuildpath;
    try {
      resolvedBuildpath = project.getResolvedBuildpath(true);
    } catch (ModelException e) {
      TextMarkerPlugin.error(e);
      return;
    }
    final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
    for (int i = 0; i < resolvedBuildpath.length; i++) {
      final IBuildpathEntry entry = resolvedBuildpath[i];
      if (entry.getEntryKind() == IBuildpathEntry.BPE_PROJECT) {
        final IPath path = entry.getPath();
        final IProject project = workspaceRoot.getProject(path.lastSegment());
        if (project.exists()) {
          packageCollector.getPackagesProvided().addAll(
                  manager.getInternalPackageNames(install, project));
        }
      }
    }
  }

  public void buildExternalModule(IBuildContext context) throws CoreException {
    final ModuleDeclaration ast = (ModuleDeclaration) context
            .get(IBuildContext.ATTR_MODULE_DECLARATION);
    if (ast != null) {
      packageCollector.getRequireDirectives().clear();
      packageCollector.process(ast);
    }
  }

  public void build(IBuildContext context) throws CoreException {
    ModuleDeclaration ast = (ModuleDeclaration) context.get(ModuleDeclaration.class.getName());
    if (ast == null) {
      return;
    }
    packageCollector.getRequireDirectives().clear();
    packageCollector.process(ast);
    if (!packageCollector.getRequireDirectives().isEmpty()) {
      resourceToModuleInfos.put(context.getSourceModule(), new ModuleInfo(context
              .getProblemReporter(), new ArrayList(packageCollector.getRequireDirectives())));
    }
  }

  public void endBuild(IProgressMonitor monitor) {
    if (buildType != RECONCILE_BUILD) {
      // Save packages provided by the project
      manager.setInternalPackageNames(install, project, packageCollector.getPackagesProvided());
    }
    monitor.subTask(Messages.TextMarkerCheckBuilder_retrievePackages);
    // initialize manager caches after they are collected
    final Set requiredPackages = packageCollector.getPackagesRequired();
    if (!requiredPackages.isEmpty()) {
      manager.getPathsForPackages(install, requiredPackages);
      manager.getPathsForPackagesWithDeps(install, requiredPackages);
    }
    // process all modules
    int remainingWork = resourceToModuleInfos.size();
    for (Iterator i = resourceToModuleInfos.entrySet().iterator(); i.hasNext();) {
      final Map.Entry entry = (Map.Entry) i.next();
      final ISourceModule module = (ISourceModule) entry.getKey();
      final ModuleInfo info = (ModuleInfo) entry.getValue();
      monitor.subTask(NLS.bind(Messages.TextMarkerCheckBuilder_processing, module.getElementName(),
              Integer.toString(remainingWork)));
      codeModels.clear();
      --remainingWork;
    }
  }

  private CodeModel getCodeModel(ISourceModule module) {
    CodeModel model = (CodeModel) codeModels.get(module);
    if (model == null) {
      try {
        model = new CodeModel(module.getSource());
        codeModels.put(module, model);
      } catch (ModelException e) {
        if (DLTKCore.DEBUG) {
          e.printStackTrace();
        }
        return null;
      }
    }
    return model;
  }

  private static Set getBuildpath(IScriptProject project) {
    final IBuildpathEntry[] resolvedBuildpath;
    try {
      resolvedBuildpath = project.getResolvedBuildpath(true);
    } catch (ModelException e1) {
      TextMarkerPlugin.error(e1);
      return Collections.EMPTY_SET;
    }
    final Set buildpath = new HashSet();
    for (int i = 0; i < resolvedBuildpath.length; i++) {
      final IBuildpathEntry entry = resolvedBuildpath[i];
      if (entry.getEntryKind() == IBuildpathEntry.BPE_LIBRARY && entry.isExternal()) {
        buildpath.add(EnvironmentPathUtils.getLocalPath(entry.getPath()));
      }
    }
    return buildpath;
  }

  private void reportPackageProblem(TextMarkerPackageDeclaration pkg, IProblemReporter reporter,
          ISourceModule module, String message, String pkgName) {
    final CodeModel model = getCodeModel(module);
    if (model == null) {
      return;
    }
    reporter.reportProblem(new DefaultProblem(message, TextMarkerProblems.UNKNOWN_REQUIRED_PACKAGE,
            new String[] { pkgName }, ProblemSeverities.Error, pkg.sourceStart(), pkg.sourceEnd(),
            model.getLineNumber(pkg.sourceStart(), pkg.sourceEnd())));
  }

  private void checkPackage(ISourceModule module, TextMarkerPackageDeclaration pkg,
          IProblemReporter reporter) {
    final String packageName = pkg.getName();

    if (packageCollector.getPackagesProvided().contains(packageName)) {
      return;
    }
    if (!isValidPackageName(packageName)) {
      return;
    }

    // Report unknown packages
    if (!knownPackageNames.contains(packageName)) {
      reportPackageProblem(pkg, reporter, module, NLS.bind(
              Messages.TextMarkerCheckBuilder_unknownPackage, packageName), packageName);
      return;
    }

    // Receive main package and it paths.
    if (checkPackage(packageName)) {
      reportPackageProblem(pkg, reporter, module, NLS.bind(
              Messages.TextMarkerCheckBuilder_unresolvedDependencies, packageName), packageName);
      return;
    }

    final Set dependencies = manager.getDependencies(packageName, install).keySet();
    for (Iterator i = dependencies.iterator(); i.hasNext();) {
      String pkgName = (String) i.next();
      if (checkPackage(pkgName)) {
        reportPackageProblem(pkg, reporter, module, NLS.bind(
                Messages.TextMarkerCheckBuilder_unresolvedDependencies, packageName), packageName);
        return;
      }
    }
  }

  static boolean isValidPackageName(String packageName) {
    return packageName != null && packageName.length() != 0 && packageName.indexOf('$') == -1
            && packageName.indexOf('[') == -1 && packageName.indexOf(']') == -1;
  }

  /**
   * returns <code>true</code> on error
   * 
   * @param packageName
   * @return
   */
  private boolean checkPackage(String packageName) {
    if (packageCollector.getPackagesProvided().contains(packageName)) {
      return false;
    }
    return isOnBuildpath(buildpath, manager.getPathsForPackage(install, packageName));
  }

  private static boolean isOnBuildpath(Set buildpath, IPath path) {
    if (!buildpath.contains(path)) {
      for (Iterator i = buildpath.iterator(); i.hasNext();) {
        IPath pp = (IPath) i.next();
        if (pp.isPrefixOf(path)) {
          return true;
        }
      }
      return false;
    }
    return true;
  }

  private static boolean isOnBuildpath(Set buildpath, IPath[] paths) {
    if (paths != null) {
      for (int i = 0; i < paths.length; i++) {
        final IPath path = paths[i];
        if (!isOnBuildpath(buildpath, path)) {
          return true;
        }
      }
    }
    return false;
  }

  public DependencyResponse getDependencies(int buildType, Set localElements, Set externalElements,
          Set oldExternalFolders, Set externalFolders) {
    if (buildType == FULL_BUILD || !oldExternalFolders.equals(externalFolders)) {
      return DependencyResponse.FULL_EXTERNAL_BUILD;
    } else {
      return null;
    }
  }

}
