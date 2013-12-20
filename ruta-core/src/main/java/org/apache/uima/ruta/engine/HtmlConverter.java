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

package org.apache.uima.ruta.engine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.ruta.UIMAConstants;
import org.apache.uima.util.CasCopier;
import org.apache.uima.util.Level;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/**
 * <p>
 * This is a basic html/xml to text converter that maintains annotations. <br />
 * Note that it is recommended to preprocess/prettify the html <i>before</i> any annotations are
 * added to the document.
 * </p>
 * <p>
 * how to handle: <br />
 * - TODO tables? <br />
 * - TODO lists (ul, ol) <br />
 * - TODO ... <br />
 * </p>
 * 
 */
public class HtmlConverter extends JCasAnnotator_ImplBase {
  // parameter names:
  public static final String NAMESPACE = "org.apache.uima.ruta.type.html.";

  public static final String OUTPUT_VIEW = "outputView";

  public static final String INPUT_VIEW = "inputView";

  public static final String REPLACE_LINEBREAKS = "replaceLinebreaks";

  public static final String LINEBREAK_REPLACEMENT = "linebreakReplacement";
  
  public static final String LINEBREAK = "\n";

  public static final String NEWLINE_INDUCING_TAGS = "newlineInducingTags";

  public static final String CONVERSION_POLICY = "conversionPolicy";

  public static final String CONVERSION_PATTERNS = "conversionPatterns";

  public static final String CONVERSION_REPLACEMENTS = "conversionReplacements";

  // default values:
  private static final String DEFAULT_MODIFIED_VIEW = "plaintext";

  // variables:
  private String inputViewName;

  private String modifiedViewName;

  private Set<String> newlineInducingTags;

  private String[] conversionPatterns;

  private String[] conversionReplacements;

  private Boolean replaceLinebreaks;
  
  private String linebreakReplacement;

  enum StringConversionPolicy {
    HEURISTIC, EXPLICIT, NONE
  }

  private StringConversionPolicy conversionPolicy;

  private int[] map;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    inputViewName = (String) aContext.getConfigParameterValue(INPUT_VIEW);
    inputViewName = StringUtils.isBlank(inputViewName) ? null : inputViewName;
    modifiedViewName = (String) aContext.getConfigParameterValue(OUTPUT_VIEW);
    modifiedViewName = StringUtils.isBlank(modifiedViewName) ? DEFAULT_MODIFIED_VIEW
            : modifiedViewName;
    replaceLinebreaks = (Boolean) aContext.getConfigParameterValue(REPLACE_LINEBREAKS);
    replaceLinebreaks = replaceLinebreaks == null ? true : replaceLinebreaks;
    linebreakReplacement = (String) aContext.getConfigParameterValue(LINEBREAK_REPLACEMENT);
    linebreakReplacement = linebreakReplacement == null ? "" : linebreakReplacement;
    String conversionPolicyString = (String) aContext.getConfigParameterValue(CONVERSION_POLICY);
    conversionPolicyString = conversionPolicyString == null ? null : conversionPolicyString
            .toLowerCase();
    if (StringUtils.isBlank(conversionPolicyString) || conversionPolicyString.equals("heuristic")) {
      conversionPolicy = StringConversionPolicy.HEURISTIC;
    } else if (conversionPolicyString.equals("explicit")) {
      conversionPolicy = StringConversionPolicy.EXPLICIT;
    } else if (conversionPolicyString.equals("none")) {
      conversionPolicy = StringConversionPolicy.NONE;
    } else {
      throw new ResourceInitializationException("illegal conversionPolicy parameter value",
              new Object[0]);
    }
    newlineInducingTags = new HashSet<String>();
    String[] nlTags = (String[]) aContext.getConfigParameterValue(NEWLINE_INDUCING_TAGS);
    if (nlTags == null || nlTags.length == 0) {
      newlineInducingTags.add("br");
      newlineInducingTags.add("p");
      newlineInducingTags.add("div");
      newlineInducingTags.add("ul");
      newlineInducingTags.add("ol");
      newlineInducingTags.add("dl");
      newlineInducingTags.add("li");
      newlineInducingTags.add("h1");
      newlineInducingTags.add("h2");
      newlineInducingTags.add("h3");
      newlineInducingTags.add("h4");
      newlineInducingTags.add("h5");
      newlineInducingTags.add("h6");
      newlineInducingTags.add("blockquote");
    } else {
      for (String nlTag : nlTags) {
        newlineInducingTags.add(nlTag);
      }
      // check assertions
      if (modifiedViewName.equals(inputViewName)) {
        throw new ResourceInitializationException("input and output view names must differ!",
                new Object[0]);
      }
    }
    conversionPatterns = (String[]) aContext.getConfigParameterValue(CONVERSION_PATTERNS);
    if (conversionPatterns == null) {
      conversionPatterns = new String[] { "&nbsp;", "&laquo;", "&raquo;", "&quot;", "&amp;",
          "&lt;", "&gt;", "&apos;", "&sect;", "&uml;", "&copy;", "&trade;", "&reg;", "&ouml;",
          "&auml;", "&uuml;", "&#160;" };
    }
    conversionReplacements = (String[]) aContext.getConfigParameterValue(CONVERSION_REPLACEMENTS);
    if (conversionReplacements == null) {
      conversionReplacements = new String[conversionPatterns.length];
      for (int i = 0; i < conversionPatterns.length; i++) {
        String c = conversionPatterns[i];
        String rep = StringEscapeUtils.unescapeHtml4(c);
        conversionReplacements[i] = rep;
      }
    }
  }

  @Override
  public void process(JCas jcaz) throws AnalysisEngineProcessException {
    JCas jcas;
    try {
      if (inputViewName != null) {
        jcas = jcaz.getView(inputViewName);
      } else {
        jcas = jcaz;
      }
    } catch (CASException e1) {
      throw new AnalysisEngineProcessException(e1.getCause());
    }
    // init:
    String documentText = jcas.getDocumentText();
    String splitSeq = documentText.contains("\r\n") ? "\r\n" : "\n";
    map = new int[documentText.length() + 1];
    JCas modview = null;
    try {
      // check if view already exists:
      Iterator<JCas> viewIterator = jcas.getViewIterator();
      while (viewIterator.hasNext()) {
        JCas jCas2 = (JCas) viewIterator.next();
        if (jCas2.getViewName().equals(modifiedViewName)) {
          modview = jCas2;
          getContext().getLogger().log(Level.WARNING,
                  "view with name \"" + modifiedViewName + "\" already exists.");
        }
      }
      if (modview == null) {
        modview = jcas.createView(modifiedViewName);
      }
    } catch (CASException e) {
      e.printStackTrace();
      return;
    }
    SortedSet<HtmlConverterPSpan> visibleSpansSoFar = new TreeSet<HtmlConverterPSpan>();
    SortedSet<HtmlConverterPSpan> linebreaksFromHtmlTags = new TreeSet<HtmlConverterPSpan>();

    // process
    try {
      Parser parser = new Parser(documentText);
      NodeList list = parser.parse(null);
      HtmlConverterVisitor visitor = new HtmlConverterVisitor(newlineInducingTags);
      list.visitAllNodesWith(visitor);
      visibleSpansSoFar = visitor.getTextSpans();
      linebreaksFromHtmlTags = visitor.getLinebreaksFromHtmlTags();
    } catch (ParserException e) {
      throw new AnalysisEngineProcessException(e);
    }
    if (replaceLinebreaks) {
      visibleSpansSoFar = this.handleLinebreaksInDocumentText(visibleSpansSoFar, splitSeq);
    }
    if (conversionPolicy == StringConversionPolicy.HEURISTIC) {
      visibleSpansSoFar = this.htmlDecoding(visibleSpansSoFar);
    } else if (conversionPolicy == StringConversionPolicy.EXPLICIT) {
      for (int i = 0; i < conversionPatterns.length; i++) {
        String pat = conversionPatterns[i];
        String rep = conversionReplacements[i];
        visibleSpansSoFar = this.handleConversion(visibleSpansSoFar, pat, rep);
      }
    }
    visibleSpansSoFar.addAll(linebreaksFromHtmlTags);

    // create new doc-text and the map from deletions and visible-text-spans:
    StringBuffer sbu = new StringBuffer(documentText.length());
    int originalOffsetI = 0;
    int outOffset = 0;
    for (HtmlConverterPSpan vis : visibleSpansSoFar) {
      final int begin = vis.getBegin();
      final int end = vis.getEnd();

      // map text before annotation:
      while (originalOffsetI < begin) {
        map[originalOffsetI++] = outOffset;
      }

      // get and map text/replacement:
      String s = "";
      if (vis instanceof HtmlConverterPSpanReplacement) {
        // conversion/replacement:
        s = vis.getTxt();
        // asserts that s is shorter than the original source
        while (originalOffsetI < begin + s.length()) {
          map[originalOffsetI++] = outOffset++;
        }
        while (originalOffsetI < end) {
          map[originalOffsetI++] = outOffset;
        }
      } else {
        // simple annotation:
        s = documentText.substring(begin, end);
        while (originalOffsetI < end) {
          map[originalOffsetI++] = outOffset++;
        }
      }
      sbu.append(s);
    }
    while (originalOffsetI < documentText.length()) {
      map[originalOffsetI++] = outOffset;
    }
    map[documentText.length()] = outOffset + 1; // handle doc end separately
    String modTxt = sbu.toString();
    modview.setDocumentText(modTxt);

    // copy annotations using the 'map':
    try {
      mapAnnotations(jcas, map, modifiedViewName);
    } catch (CASException e) {
      e.printStackTrace();
    }
  }

  private void mapAnnotations(JCas fromJcas, int[] map, String toView) throws CASException {
    JCas modview = fromJcas.getView(toView);

    Set<Annotation> indexedFs = new HashSet<Annotation>();
    AnnotationIndex<Annotation> annotationIndex = fromJcas.getAnnotationIndex();
    TypeSystem typeSystem = fromJcas.getTypeSystem();
    Type docType = typeSystem.getType(UIMAConstants.TYPE_DOCUMENT);
    CasCopier casCopier = new CasCopier(fromJcas.getCas(), modview.getCas());
    for (Annotation annotation : annotationIndex) {
      // TODO be careful here, because some people inherit from DocumentAnnotation
      if (typeSystem.subsumes(docType, annotation.getType())) {
        continue;
      }
      Annotation clone = (Annotation) casCopier.copyFs(annotation);
      // change the view/sofa of the new annotation...
      // see: http://osdir.com/ml/apache.uima.general/2007-09/msg00107.html
      clone.setFeatureValue(modview.getTypeSystem()
              .getFeatureByFullName(CAS.FEATURE_FULL_NAME_SOFA), modview.getSofa());
      final int mappedBegin = map[clone.getBegin()];
      final int mappedEnd = map[clone.getEnd()];
      if (mappedBegin < mappedEnd) {
        if (mappedEnd > fromJcas.getCas().getDocumentAnnotation().getEnd()) {
          getContext().getLogger().log(Level.WARNING, "illegal annotation offset mapping");
        } else {
          int max = modview.getCas().getDocumentAnnotation().getEnd();
          if (mappedBegin < max && mappedEnd <= max && mappedBegin >= 0 && mappedEnd > 0) {
            clone.setBegin(mappedBegin);
            clone.setEnd(mappedEnd);
            // TODO handle nested annotation features
            modview.addFsToIndexes(clone);
            indexedFs.add(clone);
          } else {
            getContext().getLogger().log(Level.WARNING, "illegal annotation offset mapping");
          }
        }
      }
    }
  }

  private SortedSet<HtmlConverterPSpan> handleLinebreaksInDocumentText(
          SortedSet<HtmlConverterPSpan> visibleSpansSoFar, String splitSeq) {
    return this.handleConversion(visibleSpansSoFar, splitSeq, linebreakReplacement);
  }

  private SortedSet<HtmlConverterPSpan> htmlDecoding(SortedSet<HtmlConverterPSpan> visibleSpansSoFar) {
    TreeSet<HtmlConverterPSpan> copy = new TreeSet<HtmlConverterPSpan>(visibleSpansSoFar);

    Pattern patt = Pattern.compile("(&[a-zA-Z]{2,5};)|(&#\\d{2,5};)");

    for (HtmlConverterPSpan pSpan : visibleSpansSoFar) {
      String spanTxt = pSpan.getTxt();
      Matcher matcher = patt.matcher(spanTxt);

      if (matcher.find()) {
        copy.remove(pSpan);
        int pSpanBegin = pSpan.getBegin();
        int ioff = pSpan.getBegin();
        do {
          String sourceString = matcher.group();
          String replacement = StringEscapeUtils.unescapeHtml4(sourceString);
          HtmlConverterPSpanReplacement replacementSpan = new HtmlConverterPSpanReplacement(
                  pSpanBegin + matcher.start(), pSpanBegin + matcher.end(), replacement);
          copy.add(replacementSpan);

          int replacementLength = sourceString.length();
          if (pSpanBegin + matcher.end() > ioff + replacementLength) {
            int ib = ioff;
            int ie = pSpanBegin + matcher.start();
            String newTxt = spanTxt.substring(ib - pSpanBegin, ie - pSpanBegin);
            copy.add(new HtmlConverterPSpan(ib, ie, newTxt));
            ioff = ie;
          }
          ioff += replacementLength; //
        } while (matcher.find());
        if (ioff < pSpan.getEnd()) {
          int ib = ioff;
          int ie = pSpan.getEnd();
          String newTxt = spanTxt.substring(ib - pSpanBegin, ie - pSpanBegin);
          copy.add(new HtmlConverterPSpan(ioff, pSpan.getEnd(), newTxt));
        }
      }
    }
    return copy;
  }

  private SortedSet<HtmlConverterPSpan> handleConversion(
          SortedSet<HtmlConverterPSpan> visibleSpansSoFar, String patternString, String replacement) {
    TreeSet<HtmlConverterPSpan> copy = new TreeSet<HtmlConverterPSpan>(visibleSpansSoFar);

    Pattern patt = Pattern.compile(patternString);
    int replacementLength = patternString.length();

    for (HtmlConverterPSpan pSpan : visibleSpansSoFar) {
      String spanTxt = pSpan.getTxt();
      Matcher matcher = patt.matcher(spanTxt);

      if (matcher.find()) {
        copy.remove(pSpan);
        int pSpanBegin = pSpan.getBegin();
        int ioff = pSpan.getBegin();
        do {
          if (!StringUtils.isEmpty(replacement)) {
            HtmlConverterPSpanReplacement replacementSpan = new HtmlConverterPSpanReplacement(
                    pSpanBegin + matcher.start(), pSpanBegin + matcher.end(), replacement);
            copy.add(replacementSpan);
          }
          if (pSpanBegin + matcher.end() > ioff + replacementLength) {
            int ib = ioff;
            int ie = pSpanBegin + matcher.start();
            String newTxt = spanTxt.substring(ib - pSpanBegin, ie - pSpanBegin);
            copy.add(new HtmlConverterPSpan(ib, ie, newTxt));
            ioff = ie;
          }
          ioff += replacementLength; //
        } while (matcher.find());
        if (ioff < pSpan.getEnd()) {
          int ib = ioff;
          int ie = pSpan.getEnd();
          String newTxt = spanTxt.substring(ib - pSpanBegin, ie - pSpanBegin);
          copy.add(new HtmlConverterPSpan(ioff, pSpan.getEnd(), newTxt));
        }
      }
    }
    return copy;
  }

}
