package org.apache.uima.tm.dltk.core.extensions;

public interface ITextMarkerLanguageExtension {
  String getName();

  ISourceElementRequestVisitorExtension createSourceElementRequestVisitorExtension();

  IMixinBuildVisitorExtension createMixinBuildVisitorExtension();

  IMatchLocatorExtension createMatchLocatorExtension();

  ICompletionExtension createCompletionExtension();

  ISelectionExtension createSelectionExtension();
}
