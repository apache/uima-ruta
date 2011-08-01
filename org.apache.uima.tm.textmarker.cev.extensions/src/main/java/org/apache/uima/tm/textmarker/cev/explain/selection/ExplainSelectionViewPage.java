package org.apache.uima.tm.textmarker.cev.explain.selection;

import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.textmarker.cev.explain.apply.ApplyTreeContentProvider;
import org.apache.uima.tm.textmarker.cev.explain.apply.ApplyTreeLabelProvider;
import org.apache.uima.tm.textmarker.cev.explain.apply.ApplyViewPage;
import org.apache.uima.tm.textmarker.cev.explain.tree.ApplyRootNode;
import org.apache.uima.tm.textmarker.cev.explain.tree.ExplainTree;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;


public class ExplainSelectionViewPage extends ApplyViewPage implements IExplainSelectionViewPage {

  private int offset = -1;

  public ExplainSelectionViewPage(CEVViewer casViewer, CEVDocument casDoc, int index) {
    super(casViewer, casDoc, index);
  }

  @Override
  public void createControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new ApplyTreeContentProvider());
    viewer.setLabelProvider(new ApplyTreeLabelProvider(this));

    viewer.addDoubleClickListener(this);
    viewer.addSelectionChangedListener(this);
    viewer.setInput(new ApplyRootNode(null, getCurrentCEVData().getCAS().getTypeSystem()));
  }

  public void viewChanged(int newIndex) {
    getCurrentCEVData().removeAnnotationListener(this);
    current = newIndex;
    getCurrentCEVData().addAnnotationListener(this);
    newSelection(offset);
  }

  public void newSelection(int offset) {
    this.offset = offset;

    if (offset >= 0) {
      ExplainTree tree = new ExplainTree(getCurrentCEVData(), offset);
      viewer.setInput(tree.getRoot());
      viewer.refresh();
    }
  }

}
