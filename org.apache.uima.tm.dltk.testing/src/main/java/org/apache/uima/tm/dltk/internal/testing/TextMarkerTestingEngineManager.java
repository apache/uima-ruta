package org.apache.uima.tm.dltk.internal.testing;

import org.apache.uima.tm.dltk.testing.ITextMarkerTestingEngine;
import org.eclipse.dltk.core.PriorityClassDLTKExtensionManager;
import org.eclipse.dltk.core.PriorityDLTKExtensionManager.ElementInfo;


public final class TextMarkerTestingEngineManager {
  private static PriorityClassDLTKExtensionManager manager = new PriorityClassDLTKExtensionManager(
          TextMarkerTestingPlugin.PLUGIN_ID + ".tmTestEngine", "id");

  public static ITextMarkerTestingEngine[] getEngines() {
    ElementInfo[] elementInfos = manager.getElementInfos();
    ITextMarkerTestingEngine[] engines = new ITextMarkerTestingEngine[elementInfos.length];
    for (int i = 0; i < elementInfos.length; i++) {
      engines[i] = (ITextMarkerTestingEngine) manager.getInitObject(elementInfos[i]);
    }
    return engines;
  }
}
