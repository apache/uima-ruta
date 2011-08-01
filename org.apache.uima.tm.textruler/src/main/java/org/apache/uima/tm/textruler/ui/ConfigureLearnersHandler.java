package org.apache.uima.tm.textruler.ui;

import java.util.List;

import org.apache.uima.tm.textruler.TextRulerPlugin;
import org.apache.uima.tm.textruler.extension.TextRulerController;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerController;
import org.apache.uima.tm.textruler.extension.TextRulerLearnerParameter;
import org.apache.uima.tm.textruler.preferences.AlgorithmPreferencePage;
import org.apache.uima.tm.textruler.preferences.ConfigPreferencePage;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;


public class ConfigureLearnersHandler implements IHandler {
  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {

  }

  @Override
  public void dispose() {

  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    PreferenceManager pm = new PreferenceManager();

    PreferencePage page = new ConfigPreferencePage();
    page.setTitle("TextRuler");
    PreferenceNode node = new PreferenceNode("org.apache.uima.tm.textruler.config", page);
    pm.addToRoot(node);

    List<PreferenceNode> nodes = pm.getElements(0);
    PreferenceNode top = null;
    for (PreferenceNode n : nodes)
      if (n.getId().equals("org.apache.uima.tm.textruler.config"))
        top = n;
    if (top != null) {
      for (TextRulerLearnerController ctrl : TextRulerController.getAvailableControllers()) {
        TextRulerLearnerParameter[] params = ctrl.getFactory().getAlgorithmParameters();
        if (params == null || params.length == 0)
          continue;
        page = new AlgorithmPreferencePage(ctrl);
        page.setTitle(ctrl.getName());
        node = new PreferenceNode(ctrl.getID(), page);
        top.add(node);
      }
    }

    Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    PreferenceDialog pd = new PreferenceDialog(shell, pm);
    pd.setPreferenceStore(TextRulerPlugin.getDefault().getPreferenceStore());
    pd.create();
    pd.open();

    // Dialog dialog = PreferencesUtil.createPreferenceDialogOn(HandlerUtil.getActiveShell(event),
    // TestingPreferencePage.ID, new String[] { TestingPreferencePage.ID }, null);
    // dialog.open();
    return null;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean isHandled() {
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {

  }

}
