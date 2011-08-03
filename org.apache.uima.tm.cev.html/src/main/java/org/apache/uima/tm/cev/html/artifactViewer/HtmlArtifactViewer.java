/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
*/

package org.apache.uima.tm.cev.html.artifactViewer;

import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVArtifactViewer;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;

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
    for (String id : annots.keySet()) {
      nsIDOMNode node = dom.getElementById(id);
      if (node == null) {
        continue;
      }
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

    while (!(start.getNodeType() == nsIDOMNode.ELEMENT_NODE && casData
            .containsHtmlId(((nsIDOMHTMLElement) start
                    .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID)).getId()))
            && start.getParentNode() != null)
      start = start.getParentNode();

    nsIDOMNode end = sel.getRangeAt(0).getEndContainer();

    while (!(end.getNodeType() == nsIDOMNode.ELEMENT_NODE && casData
            .containsHtmlId(((nsIDOMHTMLElement) end
                    .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID)).getId()))
            && end.getParentNode() != null)
      end = end.getParentNode();

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
    if (event.getType().equals("click")) {

      nsISelection sel = ((nsIWebBrowser) (browser).getWebBrowser()).getContentDOMWindow()
              .getSelection();

      nsIDOMNode node = sel.getFocusNode();

      while (!(node.getNodeType() == nsIDOMNode.ELEMENT_NODE && casData
              .containsHtmlId(((nsIDOMHTMLElement) node
                      .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID)).getId()))
              && node.getParentNode() != null)
        node = node.getParentNode();

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
