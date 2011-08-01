package org.apache.uima.tm.textmarker.testing.ui.handlers;

import org.apache.uima.tm.textmarker.testing.ui.views.TestViewPage;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;


public class RunTestHandler implements IHandler {

  private IResource resource;

  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    // TODO create a new view for every resource file
    TestViewPage view = null;
    try {
      FileEditorInput input = (FileEditorInput) HandlerUtil.getActiveEditor(event).getEditorInput();
      IResource r = input.getFile();
      view = (TestViewPage) HandlerUtil.getActiveWorkbenchWindow(event).getWorkbench()
              .getActiveWorkbenchWindow().getActivePage().showView(
                      "org.apache.uima.tm.textmarker.testing.annotationTest");
      view.setResource(r);
    } catch (PartInitException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    /*
     * TODO Create a separate view for every tm project that is selected if
     * (HandlerUtil.getActiveMenuSelection(event) instanceof IStructuredSelection) { // if
     * (HandlerUtil.getCurrentSelection(event) instanceof IStructuredSelection) {
     * StructuredSelection selection =
     * (StructuredSelection)HandlerUtil.getActiveMenuSelection(event);; // StructuredSelection
     * selection = (StructuredSelection)HandlerUtil.getCurrentSelection(event); Iterator iter =
     * selection.iterator(); ArrayList<IResource> selectionList = new ArrayList<IResource>(); while
     * (iter.hasNext()) {
     * 
     * Object obj = iter.next(); if (obj instanceof IResource) { selectionList.add((IResource)obj);
     * } view.setResource(selectionList.get(0)); if (selectionList.get(0) == null) {
     * System.out.println("keks"); } //view.setTitle(selectionList.get(0).getName()); }
     * 
     * ArrayList<String> testList = new ArrayList<String>();
     * 
     * if (view.getViewer() != null) { List list = view.getViewer().getList(); for (int i = 0; i <
     * list.getItemCount(); i++) { String s = view.getViewer().getList().getItem(i).toString();
     * testList.add(s); } }
     * 
     * IResource resource = selectionList.get(0); for (String s : testList) {
     * 
     * }
     * 
     * }
     */
    return null;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isHandled() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {
    // TODO Auto-generated method stub

  }

}
