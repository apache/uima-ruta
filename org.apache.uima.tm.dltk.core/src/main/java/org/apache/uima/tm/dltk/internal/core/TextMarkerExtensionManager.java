package org.apache.uima.tm.dltk.internal.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.tm.dltk.core.TextMarkerPlugin;
import org.apache.uima.tm.dltk.core.extensions.ICompletionExtension;
import org.apache.uima.tm.dltk.core.extensions.IIDEActionExtension;
import org.apache.uima.tm.dltk.core.extensions.IIDEBooleanFunctionExtension;
import org.apache.uima.tm.dltk.core.extensions.IIDEConditionExtension;
import org.apache.uima.tm.dltk.core.extensions.IIDENumberFunctionExtension;
import org.apache.uima.tm.dltk.core.extensions.IIDEStringFunctionExtension;
import org.apache.uima.tm.dltk.core.extensions.IIDETypeFunctionExtension;
import org.apache.uima.tm.dltk.core.extensions.IMatchLocatorExtension;
import org.apache.uima.tm.dltk.core.extensions.IMixinBuildVisitorExtension;
import org.apache.uima.tm.dltk.core.extensions.ISelectionExtension;
import org.apache.uima.tm.dltk.core.extensions.ISourceElementRequestVisitorExtension;
import org.apache.uima.tm.dltk.core.extensions.ITextMarkerLanguageExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.IEngineLoader;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerActionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerBooleanFunctionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerConditionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerNumberFunctionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerStringFunctionExtension;
import org.apache.uima.tm.textmarker.kernel.extensions.ITextMarkerTypeFunctionExtension;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.PriorityClassDLTKExtensionManager;
import org.eclipse.dltk.core.PriorityDLTKExtensionManager.ElementInfo;


public class TextMarkerExtensionManager {
  PriorityClassDLTKExtensionManager manager = new PriorityClassDLTKExtensionManager(
          TextMarkerPlugin.PLUGIN_ID + ".tmExtension", "language");

  private static TextMarkerExtensionManager sInstance;

  public static TextMarkerExtensionManager getDefault() {
    if (sInstance == null) {
      sInstance = new TextMarkerExtensionManager();
    }
    return sInstance;
  }

  public ITextMarkerLanguageExtension[] getExtensions() {
    ElementInfo[] infos = manager.getElementInfos();
    if (infos == null) {
      return new ITextMarkerLanguageExtension[0];
    }
    List extensions = new ArrayList();
    for (int i = 0; i < infos.length; i++) {
      Object object = manager.getInitObject(infos[i]);
      if (object instanceof ITextMarkerLanguageExtension) {
        extensions.add(object);
      }
    }
    return (ITextMarkerLanguageExtension[]) extensions
            .toArray(new ITextMarkerLanguageExtension[extensions.size()]);
  }

  public ISourceElementRequestVisitorExtension[] getSourceElementRequestoVisitorExtensions() {
    ITextMarkerLanguageExtension[] extensions = getExtensions();
    List result = new ArrayList();
    for (int i = 0; i < extensions.length; i++) {
      ISourceElementRequestVisitorExtension visitorExtension = extensions[i]
              .createSourceElementRequestVisitorExtension();
      if (visitorExtension != null) {
        result.add(visitorExtension);
      }
    }
    return (ISourceElementRequestVisitorExtension[]) result
            .toArray(new ISourceElementRequestVisitorExtension[result.size()]);
  }

  public IMixinBuildVisitorExtension[] getMixinVisitorExtensions() {
    ITextMarkerLanguageExtension[] extensions = getExtensions();
    List result = new ArrayList();
    for (int i = 0; i < extensions.length; i++) {
      IMixinBuildVisitorExtension visitorExtension = extensions[i]
              .createMixinBuildVisitorExtension();
      if (visitorExtension != null) {
        result.add(visitorExtension);
      }
    }
    return (IMixinBuildVisitorExtension[]) result.toArray(new IMixinBuildVisitorExtension[result
            .size()]);
  }

  public IMatchLocatorExtension[] getMatchLocatorExtensions() {
    ITextMarkerLanguageExtension[] extensions = getExtensions();
    List result = new ArrayList();
    for (int i = 0; i < extensions.length; i++) {
      IMatchLocatorExtension visitorExtension = extensions[i].createMatchLocatorExtension();
      if (visitorExtension != null) {
        result.add(visitorExtension);
      }
    }
    return (IMatchLocatorExtension[]) result.toArray(new IMatchLocatorExtension[result.size()]);
  }

  public ICompletionExtension[] getCompletionExtensions() {
    ITextMarkerLanguageExtension[] extensions = getExtensions();
    List result = new ArrayList();
    for (int i = 0; i < extensions.length; i++) {
      ICompletionExtension visitorExtension = extensions[i].createCompletionExtension();
      if (visitorExtension != null) {
        result.add(visitorExtension);
      }
    }
    return (ICompletionExtension[]) result.toArray(new ICompletionExtension[result.size()]);
  }

  public ISelectionExtension[] getSelectionExtensions() {
    ITextMarkerLanguageExtension[] extensions = getExtensions();
    List result = new ArrayList();
    for (int i = 0; i < extensions.length; i++) {
      ISelectionExtension visitorExtension = extensions[i].createSelectionExtension();
      if (visitorExtension != null) {
        result.add(visitorExtension);
      }
    }
    return (ISelectionExtension[]) result.toArray(new ISelectionExtension[result.size()]);
  }

  public IIDEConditionExtension[] getIDEConditionExtensions() {
    Collection<IIDEConditionExtension> result = new ArrayList<IIDEConditionExtension>();
    IExtension[] conditionExtensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "conditionExtension").getExtensions();
    for (IExtension extension : conditionExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("class");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IIDEConditionExtension) {
          result.add((IIDEConditionExtension) obj);
        }
      }
    }
    return result.toArray(new IIDEConditionExtension[0]);
  }

  public IIDEActionExtension[] getIDEActionExtensions() {
    Collection<IIDEActionExtension> result = new ArrayList<IIDEActionExtension>();
    IExtension[] conditionExtensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "actionExtension").getExtensions();
    for (IExtension extension : conditionExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("class");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IIDEActionExtension) {
          result.add((IIDEActionExtension) obj);
        }
      }
    }
    return result.toArray(new IIDEActionExtension[0]);
  }

  public IIDENumberFunctionExtension[] getIDENumberFunctionExtensions() {
    Collection<IIDENumberFunctionExtension> result = new ArrayList<IIDENumberFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "numberFunctionExtension").getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("class");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IIDENumberFunctionExtension) {
          result.add((IIDENumberFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new IIDENumberFunctionExtension[0]);
  }

  public IIDEBooleanFunctionExtension[] getIDEBooleanFunctionExtensions() {
    Collection<IIDEBooleanFunctionExtension> result = new ArrayList<IIDEBooleanFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "booleanFunctionExtension").getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("class");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IIDEBooleanFunctionExtension) {
          result.add((IIDEBooleanFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new IIDEBooleanFunctionExtension[0]);
  }

  public IIDEStringFunctionExtension[] getIDEStringFunctionExtensions() {
    Collection<IIDEStringFunctionExtension> result = new ArrayList<IIDEStringFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "stringFunctionExtension").getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("class");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IIDEStringFunctionExtension) {
          result.add((IIDEStringFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new IIDEStringFunctionExtension[0]);
  }

  public IIDETypeFunctionExtension[] getIDETypeFunctionExtensions() {
    Collection<IIDETypeFunctionExtension> result = new ArrayList<IIDETypeFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "typeFunctionExtension").getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("class");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IIDETypeFunctionExtension) {
          result.add((IIDETypeFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new IIDETypeFunctionExtension[0]);
  }

  public ITextMarkerConditionExtension[] getTextMarkerConditionExtensions() {
    Collection<ITextMarkerConditionExtension> result = new ArrayList<ITextMarkerConditionExtension>();
    IExtension[] conditionExtensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "conditionExtension").getExtensions();
    for (IExtension extension : conditionExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof ITextMarkerConditionExtension) {
          result.add((ITextMarkerConditionExtension) obj);
        }
      }
    }
    return result.toArray(new ITextMarkerConditionExtension[0]);
  }

  public ITextMarkerActionExtension[] getTextMarkerActionExtensions() {
    Collection<ITextMarkerActionExtension> result = new ArrayList<ITextMarkerActionExtension>();
    IExtension[] conditionExtensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "actionExtension").getExtensions();
    for (IExtension extension : conditionExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof ITextMarkerActionExtension) {
          result.add((ITextMarkerActionExtension) obj);
        }
      }
    }
    return result.toArray(new ITextMarkerActionExtension[0]);
  }

  public ITextMarkerNumberFunctionExtension[] getTextMarkerNumberFunctionExtensions() {
    Collection<ITextMarkerNumberFunctionExtension> result = new ArrayList<ITextMarkerNumberFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "numberFunctionExtension").getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof ITextMarkerNumberFunctionExtension) {
          result.add((ITextMarkerNumberFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new ITextMarkerNumberFunctionExtension[0]);
  }

  public ITextMarkerBooleanFunctionExtension[] getTextMarkerBooleanFunctionExtensions() {
    Collection<ITextMarkerBooleanFunctionExtension> result = new ArrayList<ITextMarkerBooleanFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "booleanFunctionExtension").getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof ITextMarkerBooleanFunctionExtension) {
          result.add((ITextMarkerBooleanFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new ITextMarkerBooleanFunctionExtension[0]);
  }

  public ITextMarkerStringFunctionExtension[] getTextMarkerStringFunctionExtensions() {
    Collection<ITextMarkerStringFunctionExtension> result = new ArrayList<ITextMarkerStringFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "stringFunctionExtension").getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof ITextMarkerStringFunctionExtension) {
          result.add((ITextMarkerStringFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new ITextMarkerStringFunctionExtension[0]);
  }

  public ITextMarkerTypeFunctionExtension[] getTextMarkerTypeFunctionExtensions() {
    Collection<ITextMarkerTypeFunctionExtension> result = new ArrayList<ITextMarkerTypeFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "typeFunctionExtension").getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof ITextMarkerTypeFunctionExtension) {
          result.add((ITextMarkerTypeFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new ITextMarkerTypeFunctionExtension[0]);
  }

  public IEngineLoader[] getEngineExtensions() {
    Collection<IEngineLoader> result = new ArrayList<IEngineLoader>();
    IExtension[] conditionExtensions = Platform.getExtensionRegistry().getExtensionPoint(
            TextMarkerPlugin.PLUGIN_ID, "engineExtension").getExtensions();
    for (IExtension extension : conditionExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("class");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IEngineLoader) {
          result.add((IEngineLoader) obj);
        }
      }
    }
    return result.toArray(new IEngineLoader[0]);
  }

}
