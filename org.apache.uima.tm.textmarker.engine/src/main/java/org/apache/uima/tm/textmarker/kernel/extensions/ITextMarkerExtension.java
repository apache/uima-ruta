package org.apache.uima.tm.textmarker.kernel.extensions;

import org.apache.uima.tm.textmarker.kernel.TextMarkerElement;
import org.apache.uima.tm.textmarker.verbalize.TextMarkerVerbalizer;

public interface ITextMarkerExtension {

  String[] getKnownExtensions();

  Class<?>[] extensions();

  String verbalize(TextMarkerElement element, TextMarkerVerbalizer verbalizer);

  String verbalizeName(TextMarkerElement element);

}
