package org.apache.uima.tm.textruler.core;

import java.io.File;

import org.apache.uima.util.FileUtils;

/**
 * 
 * TextRulerRule is the basic class for any kind of TextMarker-Rule representation for any learning
 * algorithm. A rule usually has a parent algorithm (that created it) and a learning target
 * (TextRulerTarget).
 * 
 * The subclasses TextRulerSingleSlotRule and TextRulerMultiSlotRule add slot specific issues to it
 * and every algorithm then has to subclass on of those two and provide a class that implements
 * TextRulerRuleItem.
 * 
 */
public abstract class TextRulerRule {

  protected TextRulerBasicLearner algorithm;

  protected boolean needsCompile = true;

  protected String ruleString;

  protected TextRulerTarget target;

  protected TextRulerStatisticsCollector coveringStatistics;

  // copy constructor:
  public TextRulerRule(TextRulerRule copyFrom) {
    algorithm = copyFrom.algorithm;
    needsCompile = copyFrom.needsCompile;
    ruleString = copyFrom.ruleString;
    target = new TextRulerTarget(copyFrom.target, algorithm);
    coveringStatistics = copyFrom.coveringStatistics != null ? copyFrom.coveringStatistics.copy()
            : null;
  }

  public TextRulerRule(TextRulerBasicLearner parentAlgorithm, TextRulerTarget target) {
    super();
    algorithm = parentAlgorithm;
    this.target = target;
  }

  public String getRuleString() {
    if (needsCompile)
      compileRuleString();
    return ruleString;
  }

  public TextRulerTarget getTarget() {
    return target;
  }

  public abstract void compileRuleString();

  public void setNeedsCompile(boolean flag) {
    needsCompile = flag;
  }

  protected String getRulesFileContent() {
    return algorithm.getTMFileHeaderString() + getRuleString() + "\n";
  }

  public void saveToRulesFile(String filename) {
    File file = new File(filename);
    String str = getRulesFileContent();
    try {
      FileUtils.saveString2File(str, file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean equals(Object obj) {
    return this.getRuleString().equals(((TextRulerRule) obj).getRuleString());
  }

  @Override
  public int hashCode() {
    return this.getRuleString().hashCode();
  }

  public void setCoveringStatistics(TextRulerStatisticsCollector c) {
    coveringStatistics = c.copy();
  }

  public TextRulerStatisticsCollector getCoveringStatistics() {
    return coveringStatistics;
  }

  public abstract TextRulerRule copy();

  @Override
  public String toString() {
    return ruleString == null ? "<not compiled>" : ruleString; // don't
    // compile if
    // neccessary!
    // just
    // return the
    // current
    // rulestring!
  }

}
