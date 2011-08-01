package org.apache.uima.tm.textmarker.ui.convert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class ConvertSyntaxAction implements IEditorActionDelegate {

  private ISelection selection;

  private IEditorPart targetEditor;

  protected IDocument getDocument() {
    if (!(targetEditor instanceof ITextEditor))
      return null;
    ITextEditor editor = (ITextEditor) targetEditor;
    IDocumentProvider dp = editor.getDocumentProvider();
    return dp.getDocument(editor.getEditorInput());
  }

  public void setActiveEditor(IAction action, IEditorPart targetEditor) {
    this.targetEditor = targetEditor;
  }

  public void run(IAction action) {

    if (selection instanceof IStructuredSelection) {
      IStructuredSelection struct = (IStructuredSelection) selection;
      List<IResource> ifiles = new ArrayList<IResource>();
      IResource ifile = null;
      Iterator<?> iter = struct.iterator();
      while (iter.hasNext()) {
        Object obj = iter.next();
        if (obj instanceof ISourceModule) {
          ISourceModule sm = (ISourceModule) obj;
          ifile = sm.getResource();
          if ("tm".equals(ifile.getFileExtension())) {
            ifiles.add(ifile);
          }
        }
      }
      for (IResource each : ifiles) {
        if (each != null) {
          File file = new File(each.getRawLocationURI());
          ANTLRInputStream input = null;

          FileInputStream stream = null;
          try {
            stream = new FileInputStream(file);
          } catch (FileNotFoundException e1) {
            e1.printStackTrace();
          }
          try {
            input = new ANTLRInputStream(stream, "UTF-8");
          } catch (IOException e) {
            e.printStackTrace();
          }
          ConvertSyntaxLexer lexer = new ConvertSyntaxLexer(input);

          CommonTokenStream tokens = new CommonTokenStream(lexer);

          ConvertSyntaxParser parser = new ConvertSyntaxParser(tokens);

          try {
            parser.file_input();
          } catch (RecognitionException e) {
            e.printStackTrace();
          }
          String result = parser.builder.toString();
          try {
            FileOutputStream out = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
            writer.write(result);
            writer.close();
          } catch (IOException e) {
            e.printStackTrace();
          }

        }
        try {
          each.getParent().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
        } catch (CoreException e) {
        }
      }

    } else if (selection instanceof ITextSelection) {
      ITextSelection textsel = (ITextSelection) selection;
      String text = textsel.getText();
      // TODO

    }
  }

  public void selectionChanged(IAction action, ISelection selection) {
    this.selection = selection;
  }

}
