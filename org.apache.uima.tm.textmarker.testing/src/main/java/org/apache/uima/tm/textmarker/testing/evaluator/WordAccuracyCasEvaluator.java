package org.apache.uima.tm.textmarker.testing.evaluator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.textmarker.seed.DefaultSeeder;


public class WordAccuracyCasEvaluator implements ICasEvaluator {

  @Override
  public CAS evaluate(CAS test, CAS run, Collection<String> excludedTypes)
          throws CASRuntimeException, CASException {
    Type annotationType = test.getAnnotationType();
    Type falsePositiveType = run.getTypeSystem().getType(ICasEvaluator.FALSE_POSITIVE);
    Type falseNegativeType = run.getTypeSystem().getType(ICasEvaluator.FALSE_NEGATIVE);
    Type truePositveType = run.getTypeSystem().getType(ICasEvaluator.TRUE_POSITIVE);

    Feature feature = falsePositiveType.getFeatureByBaseName(ICasEvaluator.ORIGINAL);
    List<Type> allTypes = test.getTypeSystem().getProperlySubsumedTypes(annotationType);
    List<Type> types = new ArrayList<Type>();
    for (Type eachType : allTypes) {
      int size = test.getAnnotationIndex(eachType).size();
      if (!excludedTypes.contains(eachType.getName()) && size > 0
              && !eachType.equals(test.getDocumentAnnotation().getType())) {
        types.add(eachType);
      }
    }

    List<AnnotationFS> testAnnotations = getAnnotations(types, test);
    List<AnnotationFS> runAnnotations = getAnnotations(types, run);

    Collection<AnnotationFS> matched = new HashSet<AnnotationFS>();
    List<AnnotationFS> fp = new ArrayList<AnnotationFS>();
    List<AnnotationFS> fn = new ArrayList<AnnotationFS>();
    List<AnnotationFS> tp = new ArrayList<AnnotationFS>();

    // need basics in this test cas too:
    final DefaultSeeder scanner = new DefaultSeeder();
    scanner.seed(test.getDocumentText(), test);
    testAnnotations = expand(testAnnotations, test, getWordTypes(test));
    runAnnotations = expand(runAnnotations, run, getWordTypes(run));

    for (AnnotationFS eachTest : testAnnotations) {
      boolean found = false;
      for (AnnotationFS eachRun : runAnnotations) {
        if (match(eachTest, eachRun)) {
          matched.add(eachRun);
          found = true;
          break;
        }
      }
      if (!found) {
        AnnotationFS createAnnotation = run.createAnnotation(falseNegativeType,
                eachTest.getBegin(), eachTest.getEnd());
        Type type = run.getTypeSystem().getType(eachTest.getType().getName());
        AnnotationFS original = run.createAnnotation(type, eachTest.getBegin(), eachTest.getEnd());
        createAnnotation.setFeatureValue(feature, original);
        fn.add(createAnnotation);
      } else {
        AnnotationFS createAnnotation = run.createAnnotation(truePositveType, eachTest.getBegin(),
                eachTest.getEnd());
        Type type = run.getTypeSystem().getType(eachTest.getType().getName());
        AnnotationFS original = run.createAnnotation(type, eachTest.getBegin(), eachTest.getEnd());
        createAnnotation.setFeatureValue(feature, original);
        tp.add(createAnnotation);
      }
    }

    for (AnnotationFS each : runAnnotations) {
      if (!matched.contains(each)) {
        AnnotationFS createAnnotation = run.createAnnotation(falsePositiveType, each.getBegin(),
                each.getEnd());
        Type type = run.getTypeSystem().getType(each.getType().getName());
        AnnotationFS original = run.createAnnotation(type, each.getBegin(), each.getEnd());
        createAnnotation.setFeatureValue(feature, original);
        fp.add(createAnnotation);
      }
    }

    for (AnnotationFS annotationFS : fn) {
      run.addFsToIndexes(annotationFS);
    }
    for (AnnotationFS annotationFS : fp) {
      run.addFsToIndexes(annotationFS);
    }
    for (AnnotationFS annotationFS : tp) {
      run.addFsToIndexes(annotationFS);
    }
    return run;
  }

  private List<Type> getWordTypes(CAS test) {
    List<Type> result = new ArrayList<Type>();
    result.add(test.getTypeSystem().getType("org.apache.uima.tm.type.W"));
    result.add(test.getTypeSystem().getType("org.apache.uima.tm.type.NUM"));
    return result;
  }

  private List<AnnotationFS> expand(List<AnnotationFS> list, CAS cas, List<Type> basicTypes) {
    List<AnnotationFS> result = new LinkedList<AnnotationFS>();
    for (Type basicType : basicTypes) {
      for (AnnotationFS each : list) {
        AnnotationFS window = cas.createAnnotation(basicType, each.getBegin(), each.getEnd());
        FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(basicType).subiterator(window);
        while (iterator.isValid()) {
          AnnotationFS basic = iterator.get();
          if (cas.getTypeSystem().subsumes(basicType, basic.getType())
                  && basic.getBegin() >= each.getBegin() && basic.getEnd() <= each.getEnd()) {
            result.add(cas.createAnnotation(each.getType(), basic.getBegin(), basic.getEnd()));
          }
          iterator.moveToNext();
        }
      }
    }
    return result;
  }

  private List<AnnotationFS> getAnnotations(List<Type> types, CAS cas) {
    List<AnnotationFS> result = new ArrayList<AnnotationFS>();
    for (Type type : types) {
      FSIterator<AnnotationFS> iterator = cas.getAnnotationIndex(type).iterator();
      while (iterator.isValid()) {
        FeatureStructure fs = iterator.get();
        if (fs instanceof AnnotationFS) {
          result.add((AnnotationFS) fs);
        }
        iterator.moveToNext();
      }
    }
    return result;
  }

  private boolean match(AnnotationFS a1, AnnotationFS a2) {
    if (a1 != null && a2 != null) {
      if (a1.getBegin() == a2.getBegin() && a1.getEnd() == a2.getEnd()
              && a1.getType().getName().equals(a2.getType().getName()))
        return true;
    }
    return false;
  }

}
