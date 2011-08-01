package org.apache.uima.tm.textmarker.kernel.constraint;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.FSTypeConstraint;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.tm.textmarker.kernel.type.TextMarkerBasic;


public class BasicTypeConstraint implements FSTypeConstraint {
  private static final long serialVersionUID = 1115953538613617791L;

  private final FSTypeConstraint constraint;

  private final List<String> types;

  private final List<String> partOfs;

  public BasicTypeConstraint(FSTypeConstraint constraint, List<String> types, List<String> partOfs) {
    super();
    this.constraint = constraint;
    this.types = types;
    this.partOfs = partOfs;
  }

  public BasicTypeConstraint(FSTypeConstraint constraint, String type, String partOf) {
    super();
    this.constraint = constraint;
    if (type != null) {
      this.types = new ArrayList<String>();
      this.types.add(type);
    } else {
      this.types = null;
    }
    if (partOf != null) {
      this.partOfs = new ArrayList<String>();
      this.partOfs.add(partOf);
    } else {
      this.partOfs = null;
    }
  }

  public void add(Type type) {
    constraint.add(type);
  }

  public boolean match(FeatureStructure fs) {
    boolean matched = constraint.match(fs);
    if (matched)
      return true;
    boolean result = false;
    if (fs instanceof TextMarkerBasic) {
      TextMarkerBasic tmb = (TextMarkerBasic) fs;
      if (types != null) {
        for (String each : types) {
          result |= tmb.isAnchorOf(each);
          if (result)
            break;
        }
      }
      if (partOfs != null) {
        for (String each : partOfs) {
          result |= tmb.isAnchorOf(each);
          if (result)
            break;
        }
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return "(BASIC " + constraint.toString() + " with " + types + " and " + partOfs + ")";
  }

  public void add(String type) {
    constraint.add(type);
  }
}
