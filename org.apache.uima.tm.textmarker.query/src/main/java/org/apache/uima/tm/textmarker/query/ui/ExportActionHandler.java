package org.apache.uima.tm.textmarker.query.ui;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class ExportActionHandler extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    QueryView view = (QueryView) HandlerUtil.getActivePart(event);
    QueryComposite composite = (QueryComposite) view.getComposite();

    TableViewer viewer = composite.getResultViewer();
    Shell shell = HandlerUtil.getActiveShell(event);

    StringBuilder sb = new StringBuilder();

    List<QueryResult> dataList = (List<QueryResult>) viewer.getInput();

    for (QueryResult entry : dataList) {
      String text = entry.getText();
      text = text.replaceAll("[\\n\\r]", " ").replaceAll("[\\s]+", " ");
      sb.append(text);
      sb.append("\n");
    }

    ResultListDialog dialog = new ResultListDialog(shell, sb.toString());
    dialog.open();

    // MessageDialog.openConfirm(shell, "Evaluation Data", text);

    return null;
  }
}
