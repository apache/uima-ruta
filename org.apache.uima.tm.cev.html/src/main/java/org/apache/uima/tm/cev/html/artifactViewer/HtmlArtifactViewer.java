package org.apache.uima.tm.cev.html.artifactViewer;

import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVArtifactViewer;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.mozilla.interfaces.nsIBoxObject;
import org.mozilla.interfaces.nsIDOMCSSStyleDeclaration;
import org.mozilla.interfaces.nsIDOMDocument;
import org.mozilla.interfaces.nsIDOMElementCSSInlineStyle;
import org.mozilla.interfaces.nsIDOMEvent;
import org.mozilla.interfaces.nsIDOMEventListener;
import org.mozilla.interfaces.nsIDOMHTMLElement;
import org.mozilla.interfaces.nsIDOMNSDocument;
import org.mozilla.interfaces.nsIDOMNode;
import org.mozilla.interfaces.nsISelection;
import org.mozilla.interfaces.nsISupports;
import org.mozilla.interfaces.nsIWebBrowser;


public class HtmlArtifactViewer implements ICEVArtifactViewer, nsIDOMEventListener {

  private CEVData casData;

  private CEVViewer viewer;

  private Browser browser;

  public HtmlArtifactViewer(Browser browser, CEVViewer viewer) {
    super();
    this.browser = browser;
    this.viewer = viewer;
  }

  @Override
  public void annotationStateChanged(Type type) {
    showBrowserAnnotations(casData.getAllHtmlIdsAndActiveTypes());
  }

  @Override
  public void annotationStateChanged() {
    showBrowserAnnotations(casData.getAllHtmlIdsAndActiveTypes());
  }

  public void showBrowserAnnotations(Map<String, Type> annots) {
    nsIWebBrowser webBrowser = (nsIWebBrowser) browser.getWebBrowser();
    nsIDOMDocument dom = webBrowser.getContentDOMWindow().getDocument();
    // ueber die HTML-IDs
    for (String id : annots.keySet()) {
      // DOM-Node holen
      nsIDOMNode node = dom.getElementById(id);
      // Pruefen ob Node existiert...
      if (node == null) {
        continue;
      }
      // Farbe setzen
      nsIDOMCSSStyleDeclaration style = ((nsIDOMElementCSSInlineStyle) node
              .queryInterface(nsIDOMElementCSSInlineStyle.NS_IDOMELEMENTCSSINLINESTYLE_IID))
              .getStyle();
      Type t = annots.get(id);
      if (t == null) {
        style.removeProperty("background-color");
        style.removeProperty("color");
      } else {
        style.setProperty("background-color", casData.getHtmlBackgroundColor(t), "");
        style.setProperty("color", casData.getHtmlForegroundColor(t), "");
      }
    }
  }

  public Point getViewerSelectionRange() {
    nsISelection sel = ((nsIWebBrowser) (browser).getWebBrowser()).getContentDOMWindow()
            .getSelection();

    nsIDOMNode start = sel.getRangeAt(0).getStartContainer();

    // Selektierte Annotation suchen
    while (!(start.getNodeType() == nsIDOMNode.ELEMENT_NODE && casData
            .containsHtmlId(((nsIDOMHTMLElement) start
                    .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID)).getId()))
            && start.getParentNode() != null)
      start = start.getParentNode();

    nsIDOMNode end = sel.getRangeAt(0).getEndContainer();

    // Selektierte Annotation suchen
    while (!(end.getNodeType() == nsIDOMNode.ELEMENT_NODE && casData
            .containsHtmlId(((nsIDOMHTMLElement) end
                    .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID)).getId()))
            && end.getParentNode() != null)
      end = end.getParentNode();

    // Selektion anzeigen
    if (start.getNodeType() == nsIDOMNode.ELEMENT_NODE
            && end.getNodeType() == nsIDOMNode.ELEMENT_NODE) {
      int s = casData.getHtmlElementPos(((nsIDOMHTMLElement) start
              .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID)).getId())[1];
      int e = casData.getHtmlElementPos(((nsIDOMHTMLElement) end
              .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID)).getId())[1];

      int startPos = s + sel.getRangeAt(0).getStartOffset();
      int endPos = e + sel.getRangeAt(0).getEndOffset();
      return new Point(startPos, endPos);
    }
    return null;
  }

  @Override
  public void viewerWidgetSelected() {
    showBrowserAnnotations(casData.getAllHtmlIdsAndActiveTypes());
  }

  @Override
  public void moveToAnnotation(AnnotationFS annot) {
    nsIWebBrowser webBrowser = (nsIWebBrowser) (browser).getWebBrowser();
    nsIDOMDocument domDocument = webBrowser.getContentDOMWindow().getDocument();
    nsIDOMNSDocument nsDocument = (nsIDOMNSDocument) domDocument
            .queryInterface(nsIDOMNSDocument.NS_IDOMNSDOCUMENT_IID);

    int x = Integer.MAX_VALUE;
    int y = Integer.MAX_VALUE;
    boolean scroll = false;

    // Ueber alle DOM-Nodes der Annotation und die X und Y Koordinaten
    // bestimmen
    for (String id : casData.getHtmlIdsAndActiveTypesForAAnnotation(annot).keySet()) {
      nsIBoxObject box = nsDocument.getBoxObjectFor(domDocument.getElementById(id));

      if (box.getY() < y) {
        y = box.getY();
        x = box.getX();
        scroll = true;
      } else if (box.getX() < x) {
        x = box.getX();
        scroll = true;
      }
    }

    // Scrollen
    if (scroll) {
      webBrowser.getContentDOMWindow().scrollTo(x, y);
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.mozilla.interfaces.nsIDOMEventListener#handleEvent(org.mozilla.interfaces
   * .nsIDOMEvent)
   */
  public void handleEvent(nsIDOMEvent event) {
    // Selektion im Browser
    if (event.getType().equals("click")) {

      nsISelection sel = ((nsIWebBrowser) (browser).getWebBrowser()).getContentDOMWindow()
              .getSelection();

      nsIDOMNode node = sel.getFocusNode();

      // Selektierte Annotation suchen
      while (!(node.getNodeType() == nsIDOMNode.ELEMENT_NODE && casData
              .containsHtmlId(((nsIDOMHTMLElement) node
                      .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID)).getId()))
              && node.getParentNode() != null)
        node = node.getParentNode();

      // Selektion anzeigen
      if (node.getNodeType() == nsIDOMNode.ELEMENT_NODE) {
        nsIDOMHTMLElement element = (nsIDOMHTMLElement) node
                .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID);

        viewer.showSelection(casData.getHtmlElementPos(element.getId())[2]);
      }
    }
  }

  public void viewChanged(CEVData casData) {
    this.casData = casData;
  }

  public nsISupports queryInterface(String arg0) {
    return null;
  }

  @Override
  public void dispose() {
    browser.dispose();
  }

  @Override
  public int getOffsetAtLocation(Point point) {
    return -1;
  }
}
