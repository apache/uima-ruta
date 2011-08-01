package org.apache.uima.tm.dltk.testing;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.testing.ITestingProcessor;

public interface ITextMarkerTestingEngine {
  String getId();

  String getName();

  boolean isValidModule(ISourceModule module);

  ITestingProcessor getProcessor(ILaunch launch);

  void correctLaunchConfiguration(InterpreterConfig config, ILaunchConfiguration configuration);
}
