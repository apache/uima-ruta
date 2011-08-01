package org.apache.uima.tm.textmarker.cev.explain.apply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.CEVDocument;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVView;
import org.apache.uima.tm.textmarker.cev.TextMarkerCEVPlugin;
import org.apache.uima.tm.textmarker.cev.explain.failed.FailedViewPage;
import org.apache.uima.tm.textmarker.cev.explain.failed.IFailedViewPage;
import org.apache.uima.tm.textmarker.cev.explain.matched.IMatchedViewPage;
import org.apache.uima.tm.textmarker.cev.explain.matched.MatchedViewPage;
import org.apache.uima.tm.textmarker.cev.explain.tree.BlockApplyNode;
import org.apache.uima.tm.textmarker.cev.explain.tree.ExplainTree;
import org.apache.uima.tm.textmarker.cev.explain.tree.RuleApplyNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.Page;


public class ApplyViewPage extends Page implements IApplyViewPage, IDoubleClickListener, ICEVView,
        ISelectionChangedListener {

  protected CEVViewer casViewer;

  protected CEVDocument casDoc;

  protected TreeViewer viewer;

  protected int current = 0;

  protected Map<String, Image> images;

  public ApplyViewPage(CEVViewer casViewer, CEVDocument casDoc, int index) {
    super();
    this.casViewer = casViewer;
    this.casDoc = casDoc;
    this.current = index;
  }

  private void initImages() {
    images = new HashMap<String, Image>();
    ImageDescriptor desc;
    Image image;
    String name;

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/arrow_refresh.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.BLOCK_APPLY_TYPE;
    images.put(name, image);

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/arrow_right.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.RULE_APPLY_TYPE;
    images.put(name, image);

    desc = TextMarkerCEVPlugin.getImageDescriptor("/icons/arrow_branch.png");
    image = desc.createImage();
    name = TextMarkerCEVPlugin.RULE_APPLY_TYPE + "Delegate";
    images.put(name, image);
  }

  public Image getImage(String name) {
    if (images == null) {
      initImages();
    }
    return images.get(name);
  }

  @Override
  public Control getControl() {
    return viewer.getControl();
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

  @Override
  public void init(IPageSite pageSite) {
    super.init(pageSite);
  }

  public TreeViewer getTreeViewer() {
    return viewer;
  }

  public CEVData getCurrentCEVData() {
    return casDoc.getCASData(current);
  }

  @Override
  public void createControl(Composite parent) {
    viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new ApplyTreeContentProvider());
    viewer.setLabelProvider(new ApplyTreeLabelProvider(this));

    viewer.addDoubleClickListener(this);
    viewer.addSelectionChangedListener(this);
    ExplainTree tree = new ExplainTree(getCurrentCEVData());
    viewer.setInput(tree.getRoot());
    viewer.refresh();
  }

  public void doubleClick(DoubleClickEvent event) {

  }

  @Override
  public void dispose() {
    super.dispose();
    if (images != null) {
      for (Image each : images.values()) {
        each.dispose();
      }
    }
  }

  public void mouseDown(final MouseEvent event) {

  }

  public void mouseUp(final MouseEvent event) {

  }

  public void mouseDoubleClick(final MouseEvent event) {

  }

  public void viewChanged(int newIndex) {
    getCurrentCEVData().removeAnnotationListener(this);
    current = newIndex;
    getCurrentCEVData().addAnnotationListener(this);

    ExplainTree tree = new ExplainTree(getCurrentCEVData());
    viewer.setInput(tree.getRoot());
    viewer.refresh();

  }

  public void annotationsAdded(List<AnnotationFS> annots) {

  }

  public void annotationsRemoved(List<AnnotationFS> annots) {

  }

  public void annotationStateChanged(Type type) {

  }

  public void annotationStateChanged(AnnotationFS annot) {

  }

  public void colorChanged(Type type) {

  }

  public void newSelection(int offset) {

  }

  public void selectionChanged(SelectionChangedEvent event) {
    ISelection selection = event.getSelection();
    if (selection instanceof ITreeSelection) {
      ITreeSelection struct = (ITreeSelection) selection;
      Object firstElement = struct.getFirstElement();
      if (firstElement instanceof RuleApplyNode) {
        RuleApplyNode node = (RuleApplyNode) firstElement;
        Object matchedPage = casViewer.getAdapter(IMatchedViewPage.class);
        if (matchedPage instanceof MatchedViewPage) {
          ((MatchedViewPage) matchedPage).inputChange(node.getMatchedChild());
        }
        Object failedPage = casViewer.getAdapter(IFailedViewPage.class);
        if (failedPage instanceof FailedViewPage) {
          ((FailedViewPage) failedPage).inputChange(node.getFailedNode());
        }
      } else if (firstElement instanceof BlockApplyNode) {
        BlockApplyNode node = (BlockApplyNode) firstElement;
        RuleApplyNode ruleNode = node.getBlockRuleNode();
        Object matchedPage = casViewer.getAdapter(IMatchedViewPage.class);
        if (matchedPage instanceof MatchedViewPage) {
          ((MatchedViewPage) matchedPage).inputChange(ruleNode.getMatchedChild());
        }
        Object failedPage = casViewer.getAdapter(IFailedViewPage.class);
        if (failedPage instanceof FailedViewPage) {
          ((FailedViewPage) failedPage).inputChange(ruleNode.getFailedNode());
        }
      }
    }
  }

  public void casChanged(CEVDocument casDocument) {
    this.casDoc = casDocument;
  }
}
