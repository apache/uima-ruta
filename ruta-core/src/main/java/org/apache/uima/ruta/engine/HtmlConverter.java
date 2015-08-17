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
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
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
 * This Analysis Engine is able to convert html content from a source view into a plain string
 * representation stored in an output view. Especially, the Analysis Engine transfers annotations
 * under consideration of the changed document text and annotation offsets in the new view. The copy
 * process also sets features, however, features of type annotation are currently not supported.
 * Note that if an annotation would have the same start and end positions in the new view, i.e., if
 * it would be mapped to an annotation of length 0, it is not moved to the new view.
 * 
 * The HTML Converter also supports heuristic and explicit conversion patterns which default to
 * html4 decoding, e.g., "{@literal &nbsp;}", "{@literal &lt;}", etc. Concepts like tables or
 * lists are not supported.
 * 
 * Note that in general it is suggested to run an html cleaner before any further processing to
 * avoid problems with malformed html.
 * 
 * A descriptor file for this Analysis Engine is located in the folder <code>descriptor/utils</code>
 * of a UIMA Ruta project.
 * 
 */
public class HtmlConverter extends JCasAnnotator_ImplBase {

  public static final String NAMESPACE = "org.apache.uima.ruta.type.html.";

  public static final String DEFAULT_MODIFIED_VIEW = "plaintext";

  public static final String LINEBREAK = "\n";

  /**
   * This string parameter specifies the name of the new view. The default value is
   * <code>plaintext</code>.
   */
  public static final String PARAM_OUTPUT_VIEW = "outputView";

  @ConfigurationParameter(name = PARAM_OUTPUT_VIEW, mandatory = false, defaultValue = DEFAULT_MODIFIED_VIEW)
  private String modifiedViewName;

  /**
   * This string parameter can optionally be set to specify the name of the input view.
   */
  public static final String PARAM_INPUT_VIEW = "inputView";

  @ConfigurationParameter(name = PARAM_INPUT_VIEW, mandatory = false)
  private String inputViewName;

  /**
   * This boolean parameter determines if linebreaks inside the text nodes are kept or removed. The
   * default behavior is <code>true</code>.
   */
  public static final String PARAM_REPLACE_LINEBREAKS = "replaceLinebreaks";

  @ConfigurationParameter(name = PARAM_REPLACE_LINEBREAKS, mandatory = false, defaultValue = "true")
  private Boolean replaceLinebreaks;

  /**
   * This boolean parameter determines if the converter should skip whitespaces. Html documents
   * often contains whitespaces for indentation and formatting, which should not be reproduced in
   * the converted plain text document. If the parameter is set to false, then the whitespces are
   * not removed. This behavior is useful, if not Html documents are converted, but XMl files. The
   * default value is true.
   */
  public static final String PARAM_SKIP_WHITESPACES = "skipWhitespaces";

  @ConfigurationParameter(name = PARAM_SKIP_WHITESPACES, mandatory = false, defaultValue = "true")
  private Boolean skipWhitespaces;

  /**
   * If this boolean parameter is set to true, then the tags of the complete document is processed
   * and not only those within the body tag.
   */
  public static final String PARAM_PROCESS_ALL = "processAll";

  @ConfigurationParameter(name = PARAM_PROCESS_ALL, mandatory = false, defaultValue = "false")
  private Boolean processAll;

  /**
   * If this boolean parameter is set to true, then zero-length annotation will not be dropped, but
   * they will be assigned to the offset of the "nearest" annotation. In that case, a boolean
   * feature names offsetsExpanded will be set to true if available.
   */
  public static final String PARAM_EXPAND_OFFSETS = "expandOffsets";

  @ConfigurationParameter(name = PARAM_EXPAND_OFFSETS, mandatory = false, defaultValue = "false")
  private Boolean expandOffsets;

  /**
   * This string parameter determines the character sequence that replaces a linebreak. The default
   * behavior is the empty string.
   */
  public static final String PARAM_LINEBREAK_REPLACEMENT = "linebreakReplacement";

  @ConfigurationParameter(name = PARAM_LINEBREAK_REPLACEMENT, mandatory = false, defaultValue = "")
  private String linebreakReplacement;

  /**
   * This string array parameter sets the names of the html tags that create linebreaks in the
   * output view. The default is <code>br, p, div, ul, ol, dl, li, h1, ..., h6, blockquote</code>.
   */
  public static final String PARAM_NEWLINE_INDUCING_TAGS = "newlineInducingTags";

  @ConfigurationParameter(name = PARAM_NEWLINE_INDUCING_TAGS, mandatory = false, defaultValue = {
      "br", "p", "div", "ul", "ol", "dl", "li", "h1", "h2", "h3", "h4", "h5", "h6", "blockquote" })
  private String[] newlineInducingTags;

  /**
   * This string parameter contains a regular expression for HTML/XML elements. If the pattern
   * matches, then the element will introduce a new line break similar to the element of the
   * parameter <code>newlineInducingTags</code>.
   */
  public static final String PARAM_NEWLINE_INDUCING_TAG_REGEXP = "newlineInducingTagRegExp";

  @ConfigurationParameter(name = PARAM_NEWLINE_INDUCING_TAG_REGEXP, mandatory = false)
  private String newlineInducingTagRegExp;

  /**
   * This string array parameter sets the names of the html tags that create additional text in the
   * output view. The acutal string of the gap is defined by the parameter <code>gapText</code>.
   */
  public static final String PARAM_GAP_INDUCING_TAGS = "gapInducingTags";

  @ConfigurationParameter(name = PARAM_GAP_INDUCING_TAGS, mandatory = false)
  private String[] gapInducingTags;

  /**
   * This string parameter determines the character sequence that is introduced by the html tags
   * specified in the <code>gapInducingTags</code>.
   */
  public static final String PARAM_GAP_TEXT = "gapText";

  @ConfigurationParameter(name = PARAM_GAP_TEXT, mandatory = false, defaultValue = "")
  private String gapText;
  
  /**
   * This boolean parameter sets the value of the parameter <code>gapText</code> to a single space.
   */
  public static final String PARAM_USE_SPACE_GAP = "useSpaceGap";

  @ConfigurationParameter(name = PARAM_USE_SPACE_GAP, mandatory = false, defaultValue = "")
  private Boolean useSpaceGap;

  /**
   * This string array parameter can be used to apply custom conversions. It defaults to a list of
   * commonly used codes, e.g., {@literal &nbsp;}, which are converted using html 4 entity
   * unescaping. However, explicit conversion strings can also be passed via the parameter
   * <code>conversionReplacements</code>. Remember to enable explicit conversion via
   * <code>conversionPolicy</code> first.
   */
  public static final String PARAM_CONVERSION_PATTERNS = "conversionPatterns";

  @ConfigurationParameter(name = PARAM_CONVERSION_PATTERNS, mandatory = false, defaultValue = {
      "&nbsp;", "&laquo;", "&raquo;", "&quot;", "&amp;", "&lt;", "&gt;", "&apos;", "&sect;",
      "&uml;", "&copy;", "&trade;", "&reg;", "&ouml;", "&auml;", "&uuml;", "&#160;" })
  private String[] conversionPatterns;

  /**
   * This string parameter determines the conversion policy used, either "heuristic", "explicit", or
   * "none". When the value is "explicit", the parameters <code>conversionPatterns</code> and
   * optionally <code>conversionReplacements</code> are considered. The "heuristic" conversion
   * policy uses simple regular expressions to decode html4 entities such as "{@literal &nbsp;}".
   * The default behavior is "heuristic".
   */
  public static final String PARAM_CONVERSION_POLICY = "conversionPolicy";

  @ConfigurationParameter(name = PARAM_CONVERSION_POLICY, mandatory = false, defaultValue = "heuristic")
  private String conversionPolicy;

  /**
   * This string array parameter corresponds to <code>conversionPatterns</code> such that
   * <code>conversionPatterns[i]</code> will be replaced by <code>conversionReplacements[i]</code>;
   * replacements should be shorter than the source pattern. Per default, the replacement strings
   * are computed using Html4 decoding. Remember to enable explicit conversion via
   * <code>conversionPolicy</code> first.
   */
  public static final String PARAM_CONVERSION_REPLACEMENTS = "conversionReplacements";

  @ConfigurationParameter(name = PARAM_CONVERSION_REPLACEMENTS, mandatory = false)
  private String[] conversionReplacements;

  private int[] map;

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    inputViewName = (String) aContext.getConfigParameterValue(PARAM_INPUT_VIEW);
    inputViewName = StringUtils.isBlank(inputViewName) ? null : inputViewName;
    modifiedViewName = (String) aContext.getConfigParameterValue(PARAM_OUTPUT_VIEW);
    modifiedViewName = StringUtils.isBlank(modifiedViewName) ? DEFAULT_MODIFIED_VIEW
            : modifiedViewName;
    replaceLinebreaks = (Boolean) aContext.getConfigParameterValue(PARAM_REPLACE_LINEBREAKS);
    replaceLinebreaks = replaceLinebreaks == null ? true : replaceLinebreaks;
    skipWhitespaces = (Boolean) aContext.getConfigParameterValue(PARAM_SKIP_WHITESPACES);
    skipWhitespaces = skipWhitespaces == null ? true : skipWhitespaces;
    processAll = (Boolean) aContext.getConfigParameterValue(PARAM_PROCESS_ALL);
    processAll = processAll == null ? true : processAll;
    linebreakReplacement = (String) aContext.getConfigParameterValue(PARAM_LINEBREAK_REPLACEMENT);
    linebreakReplacement = linebreakReplacement == null ? "" : linebreakReplacement;
    String conversionPolicy = (String) aContext.getConfigParameterValue(PARAM_CONVERSION_POLICY);
    if (StringUtils.isBlank(conversionPolicy) || conversionPolicy.equals("heuristic")) {
      conversionPolicy = "heuristic";
    } else if (conversionPolicy.equals("explicit")) {
    } else if (conversionPolicy.equals("none")) {
    } else {
      throw new ResourceInitializationException("illegal conversionPolicy parameter value",
              new Object[0]);
    }
    String[] nlTags = (String[]) aContext.getConfigParameterValue(PARAM_NEWLINE_INDUCING_TAGS);
    if (nlTags == null) {
      newlineInducingTags = new String[] { "br", "p", "div", "ul", "ol", "dl", "li", "h1", "h2",
          "h3", "h4", "h5", "h6", "blockquote" };

    }
    // check assertions
    if (modifiedViewName.equals(inputViewName)) {
      throw new ResourceInitializationException("input and output view names must differ!",
              new Object[0]);
    }
    conversionPatterns = (String[]) aContext.getConfigParameterValue(PARAM_CONVERSION_PATTERNS);
    if (conversionPatterns == null) {
      conversionPatterns = new String[] { "&nbsp;", "&laquo;", "&raquo;", "&quot;", "&amp;",
          "&lt;", "&gt;", "&apos;", "&sect;", "&uml;", "&copy;", "&trade;", "&reg;", "&ouml;",
          "&auml;", "&uuml;", "&#160;" };
    }
    conversionReplacements = (String[]) aContext
            .getConfigParameterValue(PARAM_CONVERSION_REPLACEMENTS);
    if (conversionReplacements == null) {
      conversionReplacements = new String[conversionPatterns.length];
      for (int i = 0; i < conversionPatterns.length; i++) {
        String c = conversionPatterns[i];
        String rep = StringEscapeUtils.unescapeHtml4(c);
        conversionReplacements[i] = rep;
      }
    }
    
    gapText = (String) aContext.getConfigParameterValue(PARAM_GAP_TEXT);
    gapText = gapText == null ? "" : gapText;
    
    useSpaceGap = (Boolean) aContext.getConfigParameterValue(PARAM_USE_SPACE_GAP);
    useSpaceGap = useSpaceGap == null ? false : useSpaceGap;
    
    if(useSpaceGap) {
      gapText = " ";
    }
    
    gapInducingTags = (String[]) aContext.getConfigParameterValue(PARAM_GAP_INDUCING_TAGS);
    gapInducingTags = gapInducingTags == null ? new String[0] : gapInducingTags;
    
    expandOffsets = (Boolean) aContext.getConfigParameterValue(PARAM_EXPAND_OFFSETS);
    expandOffsets = expandOffsets == null ? false : expandOffsets;
    
    newlineInducingTagRegExp = (String) aContext.getConfigParameterValue(PARAM_NEWLINE_INDUCING_TAG_REGEXP);
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
    SortedSet<HtmlConverterPSpan> gapsFromHtmlTags = new TreeSet<HtmlConverterPSpan>();

    // process
    try {
      Parser parser = new Parser(documentText);
      NodeList list = parser.parse(null);
      HtmlConverterVisitor visitor = new HtmlConverterVisitor(newlineInducingTags, newlineInducingTagRegExp, gapInducingTags,
              gapText, skipWhitespaces, processAll);
      list.visitAllNodesWith(visitor);
      visibleSpansSoFar = visitor.getTextSpans();
      linebreaksFromHtmlTags = visitor.getLinebreaksFromHtmlTags();
      gapsFromHtmlTags = visitor.getGapsFromHtmlTags();
    } catch (ParserException e) {
      throw new AnalysisEngineProcessException(e);
    }
    if (replaceLinebreaks) {
      visibleSpansSoFar = this.handleLinebreaksInDocumentText(visibleSpansSoFar, splitSeq);
    }
    if (conversionPolicy.equals("heuristic")) {
      visibleSpansSoFar = this.htmlDecoding(visibleSpansSoFar);
    } else if (conversionPolicy.equals("explicit")) {
      for (int i = 0; i < conversionPatterns.length; i++) {
        String pat = conversionPatterns[i];
        String rep = conversionReplacements[i];
        visibleSpansSoFar = this.handleConversion(visibleSpansSoFar, pat, rep);
      }
    }
    visibleSpansSoFar.addAll(linebreaksFromHtmlTags);
    visibleSpansSoFar.addAll(gapsFromHtmlTags);

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
    Set<Annotation> toExpand = new HashSet<Annotation>();
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
      } else if (expandOffsets) {
        clone.setBegin(mappedBegin);
        clone.setEnd(mappedEnd);
        toExpand.add(clone);
      }
    }

    for (Annotation each : toExpand) {
      Annotation nextBestAnnotation = getNextBestAnnotation(each, modview);
      if (nextBestAnnotation != null) {
        each.setBegin(nextBestAnnotation.getBegin());
        each.setEnd(nextBestAnnotation.getEnd());
        Feature expandedOffsetsFeature = each.getType().getFeatureByBaseName("expandedOffsets");
        if (expandedOffsetsFeature != null) {
          each.setBooleanValue(expandedOffsetsFeature, true);
        }
        modview.addFsToIndexes(each);
      }
    }
  }

  private Annotation getNextBestAnnotation(Annotation source, JCas jcas) {

    FSIterator<Annotation> iterator = jcas.getAnnotationIndex().iterator(source);
    Annotation best = null;
    if (iterator.isValid()) {
      Annotation annotation = iterator.get();
      best = annotation;
    } else {
      Annotation dummy = new Annotation(jcas, source.getBegin(), source.getBegin() + 1);
      iterator = jcas.getAnnotationIndex().iterator(dummy);
      if (!iterator.isValid()) {
        if ((jcas.getDocumentText().length() / 2) > source.getBegin()) {
          iterator.moveToFirst();
          if (iterator.isValid()) {
            Annotation annotation = iterator.get();
            best = annotation;
          }
        } else {
          iterator.moveToLast();
          if (iterator.isValid()) {
            Annotation annotation = iterator.get();
            best = annotation;
          }
        }
      }
    }

    return best;
  }

  private SortedSet<HtmlConverterPSpan> handleLinebreaksInDocumentText(
          SortedSet<HtmlConverterPSpan> visibleSpansSoFar, String splitSeq) {
    return this.handleConversion(visibleSpansSoFar, splitSeq, linebreakReplacement);
  }

  private SortedSet<HtmlConverterPSpan> htmlDecoding(SortedSet<HtmlConverterPSpan> visibleSpansSoFar) {
    TreeSet<HtmlConverterPSpan> copy = new TreeSet<HtmlConverterPSpan>(visibleSpansSoFar);

    Pattern patt = Pattern.compile("(&[a-zA-Z0-9]{2,6};)|(&#\\d{2,5};)");

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
