/**
 * 
 */
package org.apache.uima.tm.dltk.validator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantExtension;

/**
 * @author Martin Toepfer
 * 
 */
public class TextMarkerChecker implements IBuildParticipant, IBuildParticipantExtension {
  List<IBuildParticipant> buildParticipants = null;

  public TextMarkerChecker(IScriptProject project) {
    buildParticipants = new ArrayList<IBuildParticipant>();
    try {
      buildParticipants.add(new TextMarkerTypeChecker(project));
      buildParticipants.add(new TextMarkerVarRefChecker());
      buildParticipants.add(new TextMarkerEngineAndCallChecker(project));
      buildParticipants.add(new TextMarkerRessourceChecker(project));
    } catch (CoreException e) {
      e.printStackTrace();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.core.builder.IBuildParticipant#build(org.eclipse.dltk
   * .core.builder.IBuildContext)
   */
  public void build(IBuildContext context) throws CoreException {
    // if ast not declared in context ..
    Object mdObj = context.get(IBuildContext.ATTR_MODULE_DECLARATION);
    if (!(mdObj instanceof ModuleDeclaration)) {
      // ...temporary inefficient hack to get live error msgs
      // TODO refactor
      ISourceModule sourceModule = context.getSourceModule();
      ModuleDeclaration md = SourceParserUtil.getModuleDeclaration(sourceModule, null);
      context.set(IBuildContext.ATTR_MODULE_DECLARATION, md);
    }
    for (IBuildParticipant buildP : buildParticipants) {
      buildP.build(context);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.core.builder.IBuildParticipantExtension#beginBuild(int)
   */
  public boolean beginBuild(int buildType) {
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.dltk.core.builder.IBuildParticipantExtension#endBuild(org
   * .eclipse.core.runtime.IProgressMonitor)
   */
  public void endBuild(IProgressMonitor monitor) {
  }

}
