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

package org.apache.uima.textmarker.textruler.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.impl.XmiCasDeserializer;
import org.apache.uima.cas.impl.XmiCasSerializer;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.textmarker.ide.core.builder.TextMarkerProjectUtils;
import org.apache.uima.textmarker.textruler.TextRulerPlugin;
import org.apache.uima.textmarker.textruler.core.TextRulerTarget.MLTargetType;
import org.apache.uima.util.XMLInputSource;
import org.apache.uima.util.XMLSerializer;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * 
 * This static class provides all kinds of helper methods and constants that are useful for all
 * kinds of stuff in this project.
 */
public class TextRulerToolkit {

  public static final boolean LOGGING_ENABLED = true;

  public static final boolean DEBUG = false;

  public static final String TM_ALL_TYPE_NAME = "org.apache.uima.textmarker.type.ALL";

  public static final String TM_ANY_TYPE_NAME = "org.apache.uima.textmarker.type.ANY";

  public static final String TM_WORD_TYPE_NAME = "org.apache.uima.textmarker.type.W";

  public static final String TM_BREAK_TYPE_NAME = "org.apache.uima.textmarker.type.BREAK";

  public static final String TM_SPACE_TYPE_NAME = "org.apache.uima.textmarker.type.SPACE";

  public static final String TM_NUM_TYPE_NAME = "org.apache.uima.textmarker.type.NUM";

  public static final String TM_MARKUP_TYPE_NAME = "org.apache.uima.textmarker.type.MARKUP";

  public static final String TM_SPECIAL_TYPE_NAME = "org.apache.uima.textmarker.type.SPECIAL";

  public static final String TM_NBSP_TYPE_NAME = "org.apache.uima.textmarker.type.NBSP";

  public static final String LEFT_BOUNDARY_EXTENSION = "START";

  public static final String RIGHT_BOUNDARY_EXTENSION = "END";

  public static void log(String str) {
    if (LOGGING_ENABLED)
      System.out.println(str);
  }

  public static void logIfDebug(String str) {
    if (DEBUG)
      log(str);
  }

  public static void logIf(boolean condition, String str) {
    if (LOGGING_ENABLED && condition)
      System.out.println(str);
  }

  public static URL getResourceURL(String name) {
    return FileLocator.find(TextRulerPlugin.getDefault().getBundle(), new Path(name), null);
  }

  // public static AnalysisEngine loadAnalysisEngine()
  // {
  // URL u = TextRulerToolkit.getResourceURL("desc/MLEngine.xml"); // this was
  // a predefined engine file in the plugin. got removed now since we load the
  // // engine file of the given preprocessing file!
  // return loadAnalysisEngine(u);
  // }

  public static AnalysisEngine loadAnalysisEngine(String descFile) {
    AnalysisEngine result = null;
    try {
      XMLInputSource in = new XMLInputSource(new File(descFile));
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
      result = UIMAFramework.produceAnalysisEngine(specifier);
    } catch (Exception e) {
      TextRulerPlugin.error(e);
      result = null;
    }
    return result;
  }

  public static AnalysisEngine loadAnalysisEngine(URL fileURL) {
    AnalysisEngine result = null;
    try {
      XMLInputSource in = new XMLInputSource(fileURL);
      ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);
      result = UIMAFramework.produceAnalysisEngine(specifier);
    } catch (Exception e) {
      TextRulerPlugin.error(e);
      result = null;
    }
    return result;
  }

  public static CAS readCASfromXMIFile(String filename, AnalysisEngine ae, CAS reuseCAS) {
    return readCASfromXMIFile(new File(filename), ae, reuseCAS);
  }

  public static CAS readCASfromXMIFile(File file, AnalysisEngine ae, CAS reuseCAS) {
    FileInputStream inputStream = null;
    try {
      CAS resultCas;
      inputStream = new FileInputStream(file);
      if (reuseCAS != null) {
        reuseCAS.reset();
        resultCas = reuseCAS;
      } else {
        resultCas = GlobalCASSource.allocCAS(ae); // ae.newCAS();
      }
      XmiCasDeserializer.deserialize(inputStream, resultCas, true);
      return resultCas;
    } catch (Exception e) {
      TextRulerPlugin.error(e);
    } finally {
      try {
        if (inputStream != null)
          inputStream.close();
      } catch (Exception e) {
        TextRulerPlugin.error(e);
      }
    }
    return null;
  }

  public static void writeCAStoXMIFile(CAS aCas, String filename)// throws
  // IOException,
  // SAXException
  {
    File newFile = new File(filename);
    FileOutputStream out = null;

    try {
      // write XMI
      out = new FileOutputStream(newFile);
      XmiCasSerializer ser = new XmiCasSerializer(aCas.getTypeSystem());
      XMLSerializer xmlSer = new XMLSerializer(out, false);
      ser.serialize(aCas, xmlSer.getContentHandler());
    } catch (Exception e) {
      TextRulerPlugin.error(e);
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (Exception e) {
          TextRulerPlugin.error(e);
        }

      }
    }
  }

  public static List<AnnotationFS> extractAnnotationsForSlotName(CAS aCas, String slotName) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    TypeSystem ts = aCas.getTypeSystem();
    Type slotType = ts.getType(slotName);
    FSIterator<AnnotationFS> it = aCas.getAnnotationIndex(slotType).iterator(true);
    if (!it.isValid()) {
      // System.out.println("##### -> iterator not valid for slots!!");
    }
    while (it.isValid()) {
      AnnotationFS fs = it.get();

      // quick hack for quantifier bug in TM:
      AnnotationFS previous = result.size() > 0 ? result.get(result.size() - 1) : null;
      if (previous == null || previous.getBegin() != fs.getBegin()
              || previous.getEnd() != fs.getEnd())
        result.add(fs);
      else {
        logIfDebug("******** TM QUANTIFIER BUG ?? Multiple annotation: " + fs.getType().getName());
      }
      it.moveToNext();
    }

    return result;
  }

  private static List<AnnotationFS> getAnnotationWithinBounds(CAS aCas, int posStart, int posEnd,
          Set<String> filterSet, Type rootType) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    TypeSystem ts = aCas.getTypeSystem();
    try {

      // TODO wie in TMs AnnotationRetrieval evtl nicht den subiterator
      // nehmen, da der auf
      // type comparisons basiert, die wir evtl nicht gegeben haben!?
      AnnotationFS boundaryAnnotation = aCas.createAnnotation(ts.getType("uima.tcas.Annotation"),
              posStart > 0 ? posStart - 1 : 0, posEnd); // TODO ist das
      // richtig so??!!
      FSIterator<AnnotationFS> it = aCas.getAnnotationIndex().subiterator(boundaryAnnotation, true,
              true);
      while (it.isValid()) {
        AnnotationFS fs = it.get();
        if (fs.getBegin() < posStart || fs.getEnd() > posEnd) {
          it.moveToNext();
          continue;
        }
        if (rootType != null) {
          if (!ts.subsumes(rootType, fs.getType())) {
            it.moveToNext();
            continue;
          }
        }
        if (filterSet == null || !filterSet.contains(fs.getType().getName())) {
          result.add(fs);
        }

        it.moveToNext();
      }

    } catch (Exception e) {
      TextRulerPlugin.error(e);
    }
    return result;
  }

  public static List<AnnotationFS> getAnnotationsBeforePosition(CAS aCas, int position,
          int maxCount, Set<String> filterSet, Type rootType) {
    List<AnnotationFS> result = getAnnotationWithinBounds(aCas, 0, position, filterSet, rootType);
    if (maxCount > 0) {
      while (result.size() > maxCount)
        result.remove(0); // remove from front of queue !
    }
    return result;
  }

  public static List<AnnotationFS> getAnnotationsAfterPosition(CAS aCas, int position,
          int maxCount, Set<String> filterSet, Type rootType) {
    int maxPos = aCas.getDocumentText().length() - 1;
    List<AnnotationFS> result = getAnnotationWithinBounds(aCas, position, maxPos, filterSet,
            rootType);
    if (maxCount > 0) {
      while (result.size() > maxCount)
        result.remove(result.size() - 1); // remove from end of queue!
    }
    return result;
  }

  public static List<AnnotationFS> getAnnotationsWithinBounds(CAS aCas, int start, int end,
          Set<String> filterSet, Type rootType) {
    return getAnnotationWithinBounds(aCas, start, end, filterSet, rootType);
  }

  public static List<AnnotationFS> getNonTMAnnoationsOverToken(CAS aCas,
          AnnotationFS tmTokenAnnotation, Set<String> filterSet) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    // filter out document annotation!!
    FSIterator<AnnotationFS> it = aCas.getAnnotationIndex().iterator();
    Type tokenType = tmTokenAnnotation.getType();
    FSIterator<AnnotationFS> leftIt = null;
    FSIterator<AnnotationFS> rightIt = null;
    TypeSystem ts = aCas.getTypeSystem();
    Type tmRootType = ts.getType(TM_ALL_TYPE_NAME);
    Set<String> allFilters = new HashSet<String>();
    allFilters.add("uima.tcas.DocumentAnnotation");
    if (filterSet != null)
      allFilters.addAll(filterSet);
    for (; it.isValid(); it.moveToNext()) {
      AnnotationFS fs = (AnnotationFS) it.get();
      // System.out.println(fs.getBegin() + " " + tmTokenAnnotation.getBegin() + " " + fs.getEnd()
      // + " " + tmTokenAnnotation.getEnd() + " " + fs.getType() + " " + tokenType);
      if (fs.getBegin() == tmTokenAnnotation.getBegin()
              && fs.getEnd() == tmTokenAnnotation.getEnd() && fs.getType().equals(tokenType)) {
        leftIt = it;

        rightIt = it.copy();
        break;
      }
    }
    if (leftIt == null)
      return null; // the token annotation was not found !
    if (leftIt.isValid())
      leftIt.moveToPrevious(); // leave our token annotation behind us...
    // search from the token annotation to the left
    for (; leftIt.isValid(); leftIt.moveToPrevious()) {
      AnnotationFS fs = (AnnotationFS) leftIt.get();
      if (fs.getEnd() <= tmTokenAnnotation.getBegin())
        break; // if that happens we are out of reach and can stop
      if (fs.getBegin() <= tmTokenAnnotation.getBegin()
              && fs.getEnd() >= tmTokenAnnotation.getEnd()
              && !allFilters.contains(fs.getType().getName())
              && !ts.subsumes(tmRootType, fs.getType()))
        result.add(fs);
    }

    // search from the token annotation to the right
    if (rightIt.isValid())
      rightIt.moveToNext(); // leave our token annotation behind us...
    for (; rightIt.isValid(); rightIt.moveToNext()) {
      AnnotationFS fs = (AnnotationFS) rightIt.get();
      if (fs.getBegin() >= tmTokenAnnotation.getEnd())
        break; // if that happens we are out of reach and can stop
      if (fs.getBegin() <= tmTokenAnnotation.getBegin()
              && fs.getEnd() >= tmTokenAnnotation.getEnd()
              && !allFilters.contains(fs.getType().getName())
              && !ts.subsumes(tmRootType, fs.getType()))
        result.add(fs);
    }
    return result;
  }

  public static synchronized Set<String> getFilterSetWithSlotNames(String[] slotNames,
          Set<String> otherFilters) {
    Set<String> result = new HashSet<String>(otherFilters);
    if (slotNames != null)
      for (String s : slotNames)
        result.add(s);
    return result;
  }

  public static synchronized Set<String> getFilterSetWithSlotName(String slotName,
          Set<String> otherFilters) {
    String[] sn = { slotName };
    return getFilterSetWithSlotNames(sn, otherFilters);
  }

  public static synchronized String getStandardFilterSetString() {
    String str = "";
    for (String s : getStandardFilterSet(null))
      if (str.length() == 0)
        str += s;
      else
        str += ", " + s;
    return str;
  }

  public static synchronized Set<String> getStandardFilterSet(String[] slotNames) {
    Set<String> filterSet = new HashSet<String>();
    if (slotNames != null) {
      for (String s : slotNames)
        filterSet.add(s);
    }
    filterSet.add(TM_SPACE_TYPE_NAME);
    filterSet.add(TM_BREAK_TYPE_NAME);
    filterSet.add(TM_MARKUP_TYPE_NAME);
    filterSet.add(TM_NBSP_TYPE_NAME);
    return filterSet;
  }

  public static synchronized Set<String> getStandardFeatureFilterSet() {
    Set<String> filterSet = new HashSet<String>();

    filterSet.add("uima.cas.AnnotationBase:sofa");
    filterSet.add("uima.tcas.Annotation:begin");
    filterSet.add("uima.tcas.Annotation:end");
    filterSet.add("org.apache.uima.textmarker.type.TextMarkerBasic:Replacement");
    return filterSet;
  }

  // return the example of the list if found, null otherwise
  public static synchronized TextRulerExample exampleListContainsAnnotation(
          List<TextRulerExample> list, TextRulerAnnotation ann) {
    TextRulerExample needle = new TextRulerExample(null, ann, true, null);
    int index = Collections.binarySearch(list, needle, new Comparator<TextRulerExample>() {
      public int compare(TextRulerExample o1, TextRulerExample o2) {
        TextRulerAnnotation afs1 = o1.getAnnotation();
        TextRulerAnnotation afs2 = o2.getAnnotation();
        if (afs1.getBegin() < afs2.getBegin())
          return -1;
        else if (afs1.getBegin() > afs2.getBegin())
          return 1;
        else if (afs1.getEnd() > afs2.getEnd())
          return -1;
        else if (afs1.getEnd() < afs2.getEnd())
          return 1;
        else
          return 0;
      }
    });
    if (index >= 0)
      return list.get(index);
    else
      return null;
  }

  public static synchronized String addTrailingSlashToPath(String path) {
    if (!(path.endsWith("/") || path.endsWith("\\")))
      path = path + System.getProperty("file.separator");
    return path;
  }

  public static synchronized String createTemporaryDirectory() throws IOException {

    final File temp;

    temp = File.createTempFile("temp", Long.toString(System.nanoTime()));
    if (!(temp.delete()))
      return null;
    if (!(temp.mkdir()))
      return null;
    temp.deleteOnExit();
    return addTrailingSlashToPath(temp.getPath());
  }

  public static synchronized String getTypeShortName(String typeName) {
    if (typeName.indexOf(".") >= 0) {
      String components[] = typeName.split("\\.");
      return components[components.length - 1];
    } else
      return typeName;
  }

  public static synchronized String getEngineDescriptorFromTMSourceFile(IPath scriptFilePath) {
    IPath folder = scriptFilePath;

    while (!folder.lastSegment().equals(TextMarkerProjectUtils.getDefaultScriptLocation())) {
      folder = folder.removeLastSegments(1);
    }
    IPath relativeTo = scriptFilePath.makeRelativeTo(folder);
    IPath projectPath = folder.removeLastSegments(1);
    String elementName = scriptFilePath.lastSegment();
    int lastIndexOf = elementName.lastIndexOf(".tm");
    if (lastIndexOf != -1) {
      elementName = elementName.substring(0, lastIndexOf);
    }
    IPath descPath = projectPath.append(TextMarkerProjectUtils.getDefaultDescriptorLocation());
    IPath descPackagePath = descPath.append(relativeTo.removeLastSegments(1));
    return descPackagePath.append(elementName + "Engine.xml").toString();
  }

  public static synchronized String getTypeSystemDescriptorFromTMSourceFile(IPath scriptFilePath) {
    IPath folder = scriptFilePath;

    while (!folder.lastSegment().equals(TextMarkerProjectUtils.getDefaultScriptLocation())) {
      folder = folder.removeLastSegments(1);
    }
    IPath relativeTo = scriptFilePath.makeRelativeTo(folder);
    IPath projectPath = folder.removeLastSegments(1);
    String elementName = scriptFilePath.lastSegment();
    int lastIndexOf = elementName.lastIndexOf(".tm");
    if (lastIndexOf != -1) {
      elementName = elementName.substring(0, lastIndexOf);
    }
    IPath descPath = projectPath.append(TextMarkerProjectUtils.getDefaultDescriptorLocation());
    IPath descPackagePath = descPath.append(relativeTo.removeLastSegments(1));
    return descPackagePath.append(elementName + "TypeSystem.xml").toString();
  }

  public static synchronized String escapeForRegExp(String aRegexFragment) {
    final StringBuilder result = new StringBuilder();

    final StringCharacterIterator iterator = new StringCharacterIterator(aRegexFragment);
    char character = iterator.current();
    while (character != CharacterIterator.DONE) {
      /*
       * All literals need to have backslashes doubled.
       */
      if (character == '.') {
        result.append("\\.");
      } else if (character == '\\') {
        result.append("\\\\");
      } else if (character == '?') {
        result.append("\\?");
      } else if (character == '*') {
        result.append("\\*");
      } else if (character == '+') {
        result.append("\\+");
      } else if (character == '&') {
        result.append("\\&");
      } else if (character == ':') {
        result.append("\\:");
      } else if (character == '{') {
        result.append("\\{");
      } else if (character == '}') {
        result.append("\\}");
      } else if (character == '[') {
        result.append("\\[");
      } else if (character == ']') {
        result.append("\\]");
      } else if (character == '(') {
        result.append("\\(");
      } else if (character == ')') {
        result.append("\\)");
      } else if (character == '^') {
        result.append("\\^");
      } else if (character == '$') {
        result.append("\\$");
      } else {
        // the char is not a special one
        // add it to the result as is
        result.append(character);
      }
      character = iterator.next();
    }
    return result.toString();
  }

  public static synchronized String escapeForTMStringParameter(String aTMStringFragment) {
    final StringBuilder result = new StringBuilder();

    final StringCharacterIterator iterator = new StringCharacterIterator(aTMStringFragment);
    char character = iterator.current();
    while (character != CharacterIterator.DONE) {
      if (character == '"') {
        result.append("\\\"");
      } else if (character == '\\') {
        result.append("\\\\");
      } else {
        result.append(character);
      }
      character = iterator.next();
    }
    return result.toString();
  }

  public static synchronized void appendStringToFile(String fileName, String str) {
    try {
      File f = new File(fileName);
      BufferedWriter output;
      if (!f.exists())
        output = new BufferedWriter(new FileWriter(fileName));
      else
        output = new BufferedWriter(new FileWriter(fileName, true));
      output.append(str);
      output.close();
    } catch (IOException e) {
      TextRulerPlugin.error(e);
    }
  }

  public static synchronized TextRulerAnnotation convertToTargetAnnotation(AnnotationFS fs,
          TextRulerExampleDocument doc, TextRulerTarget target, TypeSystem ts) {
    AnnotationFS theAnnotation;
    if (target.type == MLTargetType.SINGLE_LEFT_BOUNDARY)
      theAnnotation = fs.getCAS().createAnnotation(ts.getType(target.getSingleSlotTypeName()),
              fs.getBegin(), fs.getBegin());
    else if (target.type == MLTargetType.SINGLE_RIGHT_BOUNDARY)
      theAnnotation = fs.getCAS().createAnnotation(ts.getType(target.getSingleSlotTypeName()),
              fs.getEnd(), fs.getEnd());
    else
      theAnnotation = fs;
    return new TextRulerAnnotation(theAnnotation, doc);
  }

  public static synchronized List<Feature> getFilteredAnnotationFeatures(AnnotationFS afs) {
    List<Feature> result = new ArrayList<Feature>();
    List<Feature> theFeatures = afs.getType().getFeatures();
    Set<String> filters = getStandardFeatureFilterSet();
    for (Feature f : theFeatures)
      if (!filters.contains(f.getName()))
        result.add(f);
    return result;
  }
}
