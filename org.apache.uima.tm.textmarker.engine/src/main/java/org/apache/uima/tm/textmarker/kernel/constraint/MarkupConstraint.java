package org.apache.uima.tm.textmarker.kernel.constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.tcas.Annotation;

public class MarkupConstraint implements FSTypeConstraint {
  private static final long serialVersionUID = 1115953538613617791L;

  private final static Pattern tagPattern = Pattern.compile("<(/)?([A-Za-z�������_0-9:!=\\-]+)([^>]*)>"); // <font

  // color='red'>
  // -->
  // group(1)=font

  private final FSTypeConstraint constraint;

  private List<String> tags = new ArrayList<String>();

  public MarkupConstraint(FSTypeConstraint constraint) {
    super();
    this.constraint = constraint;
  }

  public void addTag(String tag) {
    tags.add(tag);
  }

  public void removeTag(String tag) {
    tags.add(tag);
  }

  public boolean match(FeatureStructure fs) {
    boolean result = constraint.match(fs);
    if (fs instanceof Annotation) {
      Annotation annotation = (Annotation) fs;
      String text = annotation.getCoveredText();
      Matcher m = tagPattern.matcher(text);
      if (m.find()) {
        String lowerCase = m.group(2).toLowerCase();
        // String group = m.group(2);
        result &= tags.contains(lowerCase);
      }
    }
    return result;
  }

  public void add(Type type) {
    constraint.add(type);
  }

  public void add(String type) {
    constraint.add(type);
  }

  @Override
  public String toString() {
    return constraint.toString() + "with " + tags;
  }
}
