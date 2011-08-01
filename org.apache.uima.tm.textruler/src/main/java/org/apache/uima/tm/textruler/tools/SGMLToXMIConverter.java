package org.apache.uima.tm.textruler.tools;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textruler.core.TextRulerToolkit;
import org.apache.uima.util.FileUtils;
import org.eclipse.core.runtime.Path;

public class SGMLToXMIConverter {

  public static boolean DEBUG = true;

  static void convertSGMLAnnotationsToCASAnotations(String documentText, CAS cas) {
    StringBuffer textBuf = new StringBuffer(documentText);
    TypeSystem ts = cas.getTypeSystem();
    int annotations = 0;
    while (true) {
      int indexTagBegin = textBuf.indexOf("<");
      int indexTagEnd = textBuf.indexOf(">", indexTagBegin);
      if (indexTagBegin < 0 || indexTagEnd < 0)
        break;
      try {
        String name = textBuf.substring(indexTagBegin + 1, indexTagEnd);

        int lengthTag = indexTagEnd - indexTagBegin + 1;
        Type theType = ts.getType("de.uniwue.ml.types." + name);
        if (theType != null) {
          TextRulerToolkit.log("\tType known, creating Annotation: " + name);
          int indexTagCloseBegin = textBuf.indexOf("<", indexTagEnd);
          AnnotationFS fs = cas.createAnnotation(theType, indexTagBegin, indexTagCloseBegin
                  - lengthTag);
          cas.addFsToIndexes(fs);
          annotations++;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      textBuf.delete(indexTagBegin, indexTagEnd + 1);
    }
    cas.setDocumentText(textBuf.toString());
    if (annotations == 0) {
      TextRulerToolkit.log("NO ANNOTATIONS CREATED! IS THAT INTENDED ?");
    }
  }

  public static void convertFilesInFolder(String inFolder, String outFolder, String tmFileForEngine) {
    File in = new File(inFolder);
    File[] files = in.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return !name.startsWith(".");
      }
    });

    outFolder = TextRulerToolkit.addTrailingSlashToPath(outFolder);

    AnalysisEngine ae = TextRulerToolkit.loadAnalysisEngine(TextRulerToolkit
            .getEngineDescriptorFromTMSourceFile(new Path(tmFileForEngine)));
    if (ae == null) {
      TextRulerToolkit.log("ERROR LOADING ANALYSIS ENGINE!");
      return;
    }
    for (File f : files) {
      try {
        String fileContents = FileUtils.file2String(f);
        CAS theCas = ae.newCAS();
        convertSGMLAnnotationsToCASAnotations(fileContents, theCas);
        TextRulerToolkit.writeCAStoXMIFile(theCas, outFolder + f.getName() + ".xmi");
        TextRulerToolkit.log("Saved to " + outFolder + f.getName() + ".xmi");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  public static void main(String[] args) {
    String inputFolder = "/testinput/SA/all/txt";
    String outputFolder = "/testinput/SA/all/xmi/";
    String tmScriptFileForEngine = "/Users/tobi/Documents/runtime-EclipseApplication/MLSandBox/script/de.uniwue.ml/types.tm";

    convertFilesInFolder(inputFolder, outputFolder, tmScriptFileForEngine);
  }
}
