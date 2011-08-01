package org.apache.uima.tm.textmarker.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.DoubleArray;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.IntegerArray;
import org.apache.uima.jcas.cas.StringArray;

public class UIMAUtils {

  public static FSArray toFSArray(JCas jCas, List<? extends FeatureStructure> fsList) {
    FSArray fsArray = new FSArray(jCas, fsList.size());
    fsArray.copyFromArray(fsList.toArray(new FeatureStructure[fsList.size()]),
            0, 0, fsList.size());
    return fsArray;
  }

  public static StringArray toStringArray(JCas jCas, String[] sArray) {
    StringArray uimaSArray = new StringArray(jCas, sArray.length);
    uimaSArray.copyFromArray(sArray, 0, 0, sArray.length);
    return uimaSArray;
  }

  public static DoubleArray toDoubleArray(JCas jCas, double[] sArray) {
    DoubleArray uimaSArray = new DoubleArray(jCas, sArray.length);
    uimaSArray.copyFromArray(sArray, 0, 0, sArray.length);
    return uimaSArray;
  }

  public static IntegerArray toIntegerArray(JCas jCas, int[] sArray) {
    IntegerArray uimaSArray = new IntegerArray(jCas, sArray.length);
    uimaSArray.copyFromArray(sArray, 0, 0, sArray.length);
    return uimaSArray;
  }

  public static <T extends FeatureStructure> List<T> toList(FSArray fsArray, Class<T> cls) {
    List<T> list = new ArrayList<T>();
    if (fsArray == null) {
      return list;
    }
    for (FeatureStructure fs : fsArray.toArray()) {
      list.add(cls.cast(fs));
    }
    return list;

  }

}
