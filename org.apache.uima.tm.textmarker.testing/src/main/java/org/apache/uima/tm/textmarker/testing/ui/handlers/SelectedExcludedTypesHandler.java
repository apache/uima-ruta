package org.apache.uima.tm.textmarker.testing.ui.handlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.uima.UIMAFramework;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.tm.dltk.internal.core.builder.TextMarkerProjectUtils;
import org.apache.uima.tm.textmarker.testing.TestingPlugin;
import org.apache.uima.tm.textmarker.testing.ui.views.TestPageBookView;
import org.apache.uima.tm.textmarker.testing.ui.views.TestViewPage;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.XMLInputSource;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;


public class SelectedExcludedTypesHandler implements IHandler {

  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {

  }

  @Override
  public void dispose() {

  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    TestPageBookView debugView = (TestPageBookView) HandlerUtil.getActivePart(event);
    TestViewPage activePage = (TestViewPage) debugView.getCurrentPage();
    IResource resource = activePage.getResource();
    IPath location = resource.getLocation();
    String preFilePath = location.toPortableString();
    File preFile = new File(preFilePath);
    if (preFile.exists() == false || preFilePath.equals("")) {
      printErrorDialog("The preprocessing file was not found!");
      return null;
    }
    TypeSystemDescription defaultTypeSystemDescription = null;
    List<String> types = new ArrayList<String>();
    try {
      String tsDesc = TextMarkerProjectUtils.getTypeSystemDescriptorPath(location,
              resource.getProject()).toPortableString();

      defaultTypeSystemDescription = UIMAFramework.getXMLParser().parseTypeSystemDescription(
              new XMLInputSource(new File(tsDesc)));
      defaultTypeSystemDescription.resolveImports();
      TypeDescription[] systemTypes = defaultTypeSystemDescription.getTypes();
      for (TypeDescription typeDescription : systemTypes) {
        types.add(typeDescription.getName());
      }
      Collections.sort(types);
    } catch (InvalidXMLException e) {
      TestingPlugin.error(e);
    } catch (IOException e) {
      TestingPlugin.error(e);
    }
    Display display = Display.getDefault();
    Shell shell = new Shell(display, SWT.RESIZE | SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
    shell.setText("Excluded types");
    new SelectTypesDialog(shell, types, activePage);
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

  public static void printErrorDialog(String error) {
    ErrorDialog.openError(Display.getCurrent().getActiveShell(), "File not Found!", error,
            new Status(IStatus.ERROR, "-1", "File not found!"));
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {

  }

}
