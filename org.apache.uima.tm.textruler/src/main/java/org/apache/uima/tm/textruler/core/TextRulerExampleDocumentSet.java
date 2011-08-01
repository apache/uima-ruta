package org.apache.uima.tm.textruler.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;

/**
 * 
 * TextRulerExampleDocumentSet encapsulates an input set of documents, e.g. examples for a learning
 * algorithm. It creates an instance of TextRulerExampleDocument for each found XMI file of the
 * passed input folder
 * 
 * For loading CASes you have to provide an CasCache. If you use TextRulerBasicLearner, this is done
 * for you automatically.
 * 
 *         hint: this could be renamed to MLDocumentSet instead of TextRulerExampleDocumentSet ?
 */
public class TextRulerExampleDocumentSet {

  protected List<TextRulerExampleDocument> documents;

  protected CasCache casCache;

  public TextRulerExampleDocumentSet(String xmiFolderName, CasCache casCache) {
    super();
    documents = new ArrayList<TextRulerExampleDocument>();
    this.casCache = casCache;
    File trainingFolder = new File(xmiFolderName);
    File[] files = trainingFolder.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return (name.endsWith(".xmi"));
      }
    });

    for (File file : files) {
      TextRulerToolkit.log("found document XMI file: " + file.getName());
      documents.add(new TextRulerExampleDocument(file.getAbsolutePath(), casCache));
    }
  }

  // for subset creations:
  protected TextRulerExampleDocumentSet(String[] inputXmiFiles, CasCache casCache) {
    super();
    this.casCache = casCache;
    documents = new ArrayList<TextRulerExampleDocument>();
    for (String fileName : inputXmiFiles)
      documents.add(new TextRulerExampleDocument(fileName, casCache));
  }

  public void createExamplesForTarget(TextRulerTarget target) {
    TextRulerExampleDocument[] sortedDocs = getSortedDocumentsInCacheOptimizedOrder();
    for (TextRulerExampleDocument doc : sortedDocs) {
      doc.createExamplesForTarget(target);
    }
  }

  public void clearCurrentExamples() {
    for (TextRulerExampleDocument doc : documents)
      doc.clearCurrentExamples();
  }

  public Collection<CAS> getCachedCASes() {
    return casCache.getCachedCASes();
  }

  public boolean casCacheContainsKey(String key) {
    return casCache.containsElementWithKey(key);
  }

  public List<TextRulerExample> getAllExamples() {
    return getAllExamples(false);
  }

  public List<TextRulerExample> getAllPositiveExamples() {
    return getAllExamples(true);
  }

  public List<TextRulerExample> getAllExamples(boolean onlyPositives) {
    List<TextRulerExample> result = new ArrayList<TextRulerExample>();
    for (TextRulerExampleDocument doc : documents) {
      result.addAll(doc.getPositiveExamples());
      if (!onlyPositives)
        result.addAll(doc.getNegativeExamples());
    }
    return result;
  }

  public List<TextRulerExampleDocument> getDocuments() {
    return documents;
  }

  public TextRulerExampleDocument[] getSortedDocumentsInCacheOptimizedOrder(
          Collection<TextRulerExampleDocument> documents) {
    Set<TextRulerExampleDocument> docsLeft = new HashSet<TextRulerExampleDocument>(documents);
    TextRulerExampleDocument[] sortedDocs = new TextRulerExampleDocument[documents.size()];

    // "sort" the currently cached documents to the front of the document
    // list, so that
    // we can use them directly and do not have to reload all docs everytime
    // we come here!
    int i = 0;
    for (TextRulerExampleDocument doc : documents) {
      if (casCacheContainsKey(doc.getCasFileName())) {
        docsLeft.remove(doc);
        sortedDocs[i] = doc;
        i++;
      }
    }
    for (TextRulerExampleDocument doc : docsLeft) {
      sortedDocs[i] = doc;
      i++;
    }
    if (TextRulerToolkit.DEBUG) {
      TextRulerToolkit.logIf(i != documents.size(), "ERROR, SIZE MISMATCH!");
    }

    return sortedDocs;
  }

  public TextRulerExampleDocument[] getSortedDocumentsInCacheOptimizedOrder() {
    return getSortedDocumentsInCacheOptimizedOrder(documents);
  }

  public List<Integer> getTokenCountHistogrammForSlotName(String slotName, Set<String> filterSet) {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    int maxLen = 0;

    TextRulerExampleDocument[] sortedDocs = getSortedDocumentsInCacheOptimizedOrder(documents);

    for (TextRulerExampleDocument doc : sortedDocs) {
      CAS aCas = doc.getCAS();
      List<AnnotationFS> slots = TextRulerToolkit.extractAnnotationsForSlotName(aCas, slotName);
      TypeSystem ts = aCas.getTypeSystem();
      for (AnnotationFS a : slots) {

        List<AnnotationFS> slotTokens = TextRulerToolkit.getAnnotationsWithinBounds(aCas, a
                .getBegin(), a.getEnd(), TextRulerToolkit.getFilterSetWithSlotName(slotName,
                filterSet), ts.getType(TextRulerToolkit.TM_ANY_TYPE_NAME));
        int len = slotTokens.size();
        if (len > maxLen)
          maxLen = len;
        Integer key = new Integer(len);
        int current = map.containsKey(key) ? map.get(key) : 0;
        map.put(key, len + current);
      }
    }
    List<Integer> resultList = new ArrayList<Integer>(maxLen + 1);
    for (int i = 0; i <= maxLen; i++) {
      int value = map.containsKey(i) ? map.get(i) : 0;
      resultList.add(value);
    }
    return resultList;
  }

  public CAS getCAS(String key) {
    return casCache.getCAS(key);
  }

  public int size() {
    return documents.size();
  }

  public TextRulerExampleDocument getDocumentForFileName(String fileName) {
    for (TextRulerExampleDocument doc : documents)
      if (doc.getCasFileName().equals(fileName))
        return doc;
    return null;
  }

  // TODO this is not tested yet!
  public List<TextRulerExampleDocumentSet> partitionIntoSubsets(int[] percentages) {
    List<TextRulerExampleDocumentSet> result = new ArrayList<TextRulerExampleDocumentSet>();

    int sum = 0;
    for (int p : percentages) {
      if (p == 0) {
        TextRulerToolkit
                .log("[TextRulerExampleDocumentSet.partitionIntoSubsets] a percentage must not be zero!");
        return null;
      }
      sum += p;
    }
    if (sum != 100) {
      TextRulerToolkit
              .log("[TextRulerExampleDocumentSet.partitionIntoSubsets] percentages has to be 100 in total!");
      return null;
    }

    int rest = size();
    int docIndex = 0;

    for (int i = 0; i < percentages.length; i++) {
      int partSize;
      if (i == percentages.length - 1) {
        partSize = Math.round((((percentages[i] * size()) / 100.0f)));
        if (partSize == 0)
          partSize = 1;
      } else
        partSize = rest;

      if (partSize == 0) {
        TextRulerToolkit
                .log("[TextRulerExampleDocumentSet.partitionIntoSubsets] a percentage must not be zero! too few example documents for your partition?");
        return null;
      }
      String[] fileNames = new String[partSize];
      for (int doc = 0; doc < partSize; doc++)
        fileNames[doc] = documents.get(doc + docIndex).getCasFileName();
      docIndex += partSize;
      result.add(new TextRulerExampleDocumentSet(fileNames, casCache));
      rest -= partSize;
    }
    return result;
  }

}
