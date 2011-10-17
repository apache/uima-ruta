package org.apache.uima.textmarker.caseditor.view.tree;

import org.apache.uima.cas.text.AnnotationFS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class ToolTipListener implements Listener {

  private static final String TOOLTIP_TEXT_END = "\nEnd: ";

  private static final String TOOLTIP_TEXT_BEGIN = "Begin: ";

  private Shell tip = null;

  private Label label = null;

  private Tree tree;

  public ToolTipListener(Tree tree) {
    this.tree = tree;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets .Event)
   */
  public void handleEvent(Event event) {
    switch (event.type) {
      case SWT.Dispose:
      case SWT.KeyDown:
      case SWT.MouseMove: {
        if (tip == null)
          break;
        tip.dispose();
        tip = null;
        label = null;
        break;
      }
      case SWT.MouseHover: {
        TreeItem item = tree.getItem(new Point(event.x, event.y));

        if (item != null && item.getData() instanceof AnnotationTreeNode) {
          if (tip != null && !tip.isDisposed())
            tip.dispose();

          tip = new Shell(Display.getCurrent().getActiveShell(), SWT.ON_TOP | SWT.NO_FOCUS
                  | SWT.TOOL);

          tip.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
          FillLayout layout = new FillLayout();
          layout.marginWidth = 2;
          tip.setLayout(layout);

          label = new Label(tip, SWT.NONE);
          label.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
          label.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND));

          AnnotationFS annot = ((AnnotationTreeNode) item.getData()).getAnnotation();

          label.setText(TOOLTIP_TEXT_BEGIN + annot.getBegin() + TOOLTIP_TEXT_END + annot.getEnd());

          Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
          Rectangle rect = item.getBounds(0);
          Point pt = tree.toDisplay(rect.x, rect.y);
          tip.setBounds(pt.x, pt.y, size.x, size.y);
          tip.setVisible(true);
        }
      }
    }
  }
}
