/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.uima.ruta.ide.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.ruta.extensions.IEngineLoader;
import org.apache.uima.ruta.extensions.IRutaActionExtension;
import org.apache.uima.ruta.extensions.IRutaBooleanFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaConditionExtension;
import org.apache.uima.ruta.extensions.IRutaNumberFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaStringFunctionExtension;
import org.apache.uima.ruta.extensions.IRutaTypeFunctionExtension;
import org.apache.uima.ruta.ide.RutaIdePlugin;
import org.apache.uima.ruta.ide.core.extensions.ICompletionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEActionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEBooleanFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEConditionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDENumberFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDEStringFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IIDETypeFunctionExtension;
import org.apache.uima.ruta.ide.core.extensions.IMatchLocatorExtension;
import org.apache.uima.ruta.ide.core.extensions.IMixinBuildVisitorExtension;
import org.apache.uima.ruta.ide.core.extensions.ISelectionExtension;
import org.apache.uima.ruta.ide.core.extensions.ISourceElementRequestVisitorExtension;
import org.apache.uima.ruta.ide.core.extensions.IRutaLanguageExtension;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.PriorityClassDLTKExtensionManager;
import org.eclipse.dltk.core.PriorityDLTKExtensionManager.ElementInfo;

public class RutaExtensionManager {
  PriorityClassDLTKExtensionManager manager = new PriorityClassDLTKExtensionManager(
          RutaIdePlugin.PLUGIN_ID + ".tmExtension", "language");

  private static RutaExtensionManager sInstance;

  public static RutaExtensionManager getDefault() {
    if (sInstance == null) {
      sInstance = new RutaExtensionManager();
    }
    return sInstance;
  }

  public IRutaLanguageExtension[] getExtensions() {
    ElementInfo[] infos = manager.getElementInfos();
    if (infos == null) {
      return new IRutaLanguageExtension[0];
    }
    List extensions = new ArrayList();
    for (int i = 0; i < infos.length; i++) {
      Object object = manager.getInitObject(infos[i]);
      if (object instanceof IRutaLanguageExtension) {
        extensions.add(object);
      }
    }
    return (IRutaLanguageExtension[]) extensions
            .toArray(new IRutaLanguageExtension[extensions.size()]);
  }

  public ISourceElementRequestVisitorExtension[] getSourceElementRequestoVisitorExtensions() {
    IRutaLanguageExtension[] extensions = getExtensions();
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
    IRutaLanguageExtension[] extensions = getExtensions();
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
    IRutaLanguageExtension[] extensions = getExtensions();
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
    IRutaLanguageExtension[] extensions = getExtensions();
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
    IRutaLanguageExtension[] extensions = getExtensions();
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
    IExtension[] conditionExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "conditionExtension").getExtensions();
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
    IExtension[] conditionExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "actionExtension").getExtensions();
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
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "numberFunctionExtension")
            .getExtensions();
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
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "booleanFunctionExtension")
            .getExtensions();
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
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "stringFunctionExtension")
            .getExtensions();
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
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "typeFunctionExtension")
            .getExtensions();
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

  public IRutaConditionExtension[] getRutaConditionExtensions() {
    Collection<IRutaConditionExtension> result = new ArrayList<IRutaConditionExtension>();
    IExtension[] conditionExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "conditionExtension").getExtensions();
    for (IExtension extension : conditionExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IRutaConditionExtension) {
          result.add((IRutaConditionExtension) obj);
        }
      }
    }
    return result.toArray(new IRutaConditionExtension[0]);
  }

  public IRutaActionExtension[] getRutaActionExtensions() {
    Collection<IRutaActionExtension> result = new ArrayList<IRutaActionExtension>();
    IExtension[] conditionExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "actionExtension").getExtensions();
    for (IExtension extension : conditionExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IRutaActionExtension) {
          result.add((IRutaActionExtension) obj);
        }
      }
    }
    return result.toArray(new IRutaActionExtension[0]);
  }

  public IRutaNumberFunctionExtension[] getRutaNumberFunctionExtensions() {
    Collection<IRutaNumberFunctionExtension> result = new ArrayList<IRutaNumberFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "numberFunctionExtension")
            .getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IRutaNumberFunctionExtension) {
          result.add((IRutaNumberFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new IRutaNumberFunctionExtension[0]);
  }

  public IRutaBooleanFunctionExtension[] getRutaBooleanFunctionExtensions() {
    Collection<IRutaBooleanFunctionExtension> result = new ArrayList<IRutaBooleanFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "booleanFunctionExtension")
            .getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IRutaBooleanFunctionExtension) {
          result.add((IRutaBooleanFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new IRutaBooleanFunctionExtension[0]);
  }

  public IRutaStringFunctionExtension[] getRutaStringFunctionExtensions() {
    Collection<IRutaStringFunctionExtension> result = new ArrayList<IRutaStringFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "stringFunctionExtension")
            .getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IRutaStringFunctionExtension) {
          result.add((IRutaStringFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new IRutaStringFunctionExtension[0]);
  }

  public IRutaTypeFunctionExtension[] getRutaTypeFunctionExtensions() {
    Collection<IRutaTypeFunctionExtension> result = new ArrayList<IRutaTypeFunctionExtension>();
    IExtension[] extensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "typeFunctionExtension")
            .getExtensions();
    for (IExtension extension : extensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        Object obj = null;
        try {
          obj = configurationElement.createExecutableExtension("engine");
        } catch (CoreException e) {
          e.printStackTrace();
        }
        if (obj instanceof IRutaTypeFunctionExtension) {
          result.add((IRutaTypeFunctionExtension) obj);
        }
      }
    }
    return result.toArray(new IRutaTypeFunctionExtension[0]);
  }

  public IEngineLoader[] getEngineExtensions() {
    Collection<IEngineLoader> result = new ArrayList<IEngineLoader>();
    IExtension[] conditionExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaIdePlugin.PLUGIN_ID, "engineExtension").getExtensions();
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
