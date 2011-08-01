package org.apache.uima.tm.textmarker.cev.explain.basic;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.textmarker.cev.TextMarkerCEVPlugin;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


public class BasicTokenStreamTableContentProvider implements IStructuredContentProvider {

  private List<BasicTokenEntry> basicTokenEntries;

  public BasicTokenStreamTableContentProvider(CEVData casData) {
    init(casData);
  }

  public void init(CEVData casData) {
    basicTokenEntries = new ArrayList<BasicTokenEntry>();

    Type basicType = casData.getCAS().getTypeSystem().getType(TextMarkerCEVPlugin.BASIC_TYPE);
    if (basicType != null) {
      AnnotationIndex anInd = casData.getCAS().getAnnotationIndex(basicType);
      FSIterator iti = anInd.iterator(true);
      iti.moveToFirst();
      int i = 0;
      BasicTokenEntry basicTokenEntry;
      while (iti.isValid()) {
        AnnotationFS annot = (AnnotationFS) iti.get();
        i++;

        basicTokenEntry = new BasicTokenEntry(annot, i);
        basicTokenEntries.add(basicTokenEntry);
        iti.moveToNext();
      }
    }
  }

  public Object[] getElements(Object inputElement) {
    return basicTokenEntries.toArray();
  }

  public void dispose() {
  }

  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
  }

  public BasicTokenEntry getEntryAt(int offset) {
    for (BasicTokenEntry each : basicTokenEntries) {
      if (each.getAnnotation().getBegin() <= offset && each.getAnnotation().getEnd() >= offset) {
        return each;
      }
    }
    return null;
  }

}
