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

import java.util.LinkedList;

import org.apache.uima.cas.Type;
import org.apache.uima.tm.cev.CEVPlugin;
import org.apache.uima.tm.cev.data.CEVAnnotationRanges.HtmlIdInfo;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.data.ICEVDataExtension;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.Translate;

public class CEVDataHtmlExtension implements ICEVDataExtension, ProgressListener {

  private CEVData casData;

  private Browser browser;

  private nsIDOMEventListener browserListener;

  public CEVDataHtmlExtension(CEVData casData) {
    super();
    this.casData = casData;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.browser.ProgressListener#changed(org.eclipse.swt.browser .ProgressEvent)
   */
  public void changed(ProgressEvent event) {
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.swt.browser.ProgressListener#completed(org.eclipse.swt.browser .ProgressEvent)
   */
  public void completed(ProgressEvent event) {

    browser.removeProgressListener(this);

    nsIWebBrowser webBrowser = (nsIWebBrowser) (browser).getWebBrowser();
    nsIDOMDocument domDocument = webBrowser.getContentDOMWindow().getDocument();

    nsIDOMHTMLDocument htmlDomDoc = (nsIDOMHTMLDocument) domDocument
            .queryInterface(nsIDOMHTMLDocument.NS_IDOMHTMLDOCUMENT_IID);

    nsIDOMHTMLElement body = htmlDomDoc.getBody();

    try {
      if (body != null && casData.containsHtmlId(body.getId())) {
        int[] pos = casData.getHtmlElementPos(body.getId());
        prepareDOM(browserListener, body, pos[1], pos[2]);
      }
    } catch (SWTError err) {
      CEVPlugin.error(err);
    }

    for (Type t : casData.getAnnotationStateCount().keySet()) {
      if (casData.getAnnotationStateCount().get(t) > 0) {
        casData.annotationStateChanged(t);
      }
    }
  }

  public void setBrowser(Browser browser, nsIDOMEventListener listener) {
    this.browser = browser;
    browserListener = listener;
  }

  @Override
  public void initialize() {
    initializeHTML();

    if (browser != null && casData.getHTMLSource() != null) {
      browser.setText(casData.getHTMLSource());
      browser.addProgressListener(this);
    }
  }

  private void initializeHTML() {

    try {
      if (casData.getCAS().getDocumentText() != null) {

        Parser parser = new Parser();
        parser.setInputHTML(casData.getCAS().getDocumentText());

        HTMLVisitor visitor = new HTMLVisitor(casData.getCAS().getDocumentText());

        NodeList nl = parser.parse(null);
        nl.visitAllNodesWith(visitor);

        casData.htmlText = nl.toHtml();
        casData.htmlElementPos = visitor.getIDPosMap();
        casData.idName = visitor.getIDName();
        casData.idCount = visitor.getIdCount();
      }
    } catch (Exception e) {
      CEVPlugin.error(e);
    }
  }

  public void prepareDOM(nsIDOMEventListener listener, nsIDOMNode node, int start, int end) {
    int leftPos = start;
    int rightPos = end;

    if (node.getNodeType() == nsIDOMNode.TEXT_NODE) {

      leftPos = getLeftPosition(node, start);
      rightPos = getRightPosition(node, end);

      createSpan(listener, node, leftPos, rightPos);

    } else if (node.getNodeType() == nsIDOMNode.ELEMENT_NODE) {
      nsIDOMHTMLElement element = (nsIDOMHTMLElement) node
              .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID);

      if (casData.htmlElementPos.containsKey(element.getId())) {
        int[] pos = casData.htmlElementPos.get(element.getId());
        leftPos = pos[1];
        rightPos = pos[2];
      }

      nsIDOMNodeList children = element.getChildNodes();

      for (int i = 0; i < children.getLength(); i++) {
        nsIDOMNode item = children.item(i);
        prepareDOM(listener, item, leftPos, rightPos);
      }
    }
  }

  private int getLeftPosition(nsIDOMNode node, int start) {
    int leftPos = start;

    boolean foundBefore = false;
    nsIDOMNode before = node;
    while ((before = before.getPreviousSibling()) != null && !foundBefore) {
      if (before.getNodeType() == nsIDOMNode.ELEMENT_NODE) {
        nsIDOMHTMLElement r = (nsIDOMHTMLElement) before
                .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID);
        if (casData.htmlElementPos.containsKey(r.getId())) {
          int[] pos = casData.htmlElementPos.get(r.getId());
          leftPos = pos[3] == 0 ? pos[1] : pos[3];
          break;
        } else {
          nsIDOMNodeList nodes = r.getChildNodes();
          for (int i = 0; i < nodes.getLength(); i++) {
            nsIDOMNode item = nodes.item(i);
            if (item != null && item.getNodeType() == nsIDOMNode.ELEMENT_NODE) {
              nsIDOMHTMLElement inner = (nsIDOMHTMLElement) item
                      .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID);
              if (casData.htmlElementPos.containsKey(inner.getId())) {
                int[] pos = casData.htmlElementPos.get(inner.getId());
                leftPos = pos[3] == 0 ? pos[1] : pos[3];
                foundBefore = true;
                break;
              } else {
                before = item;
                break;
              }
            }
          }
        }
      }
    }
    return leftPos;
  }

  private int getRightPosition(nsIDOMNode node, int end) {
    int rightPos = end;

    nsIDOMNode next = node;
    boolean foundNext = false;
    while ((next = next.getNextSibling()) != null && !foundNext) {
      if (next.getNodeType() == nsIDOMNode.ELEMENT_NODE && end != 0) {
        nsIDOMHTMLElement r = (nsIDOMHTMLElement) next
                .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID);
        if (casData.htmlElementPos.containsKey(r.getId())) {
          int[] pos = casData.htmlElementPos.get(r.getId());
          rightPos = pos[0];
          break;
        } else {
          nsIDOMNodeList nodes = r.getChildNodes();
          for (int i = 0; i < nodes.getLength(); i++) {
            nsIDOMNode item = nodes.item(i);
            if (item != null && item.getNodeType() == nsIDOMNode.ELEMENT_NODE) {
              nsIDOMHTMLElement inner = (nsIDOMHTMLElement) item
                      .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID);
              if (casData.htmlElementPos.containsKey(inner.getId())) {
                int[] pos = casData.htmlElementPos.get(inner.getId());
                rightPos = pos[0];
                foundNext = true;
                break;
              } else {
                nsIDOMNodeList innerChildren = inner.getChildNodes();
                for (int j = 0; j < innerChildren.getLength(); j++) {
                  nsIDOMNode c = nodes.item(i);
                  if (c != null && c.getNodeType() == nsIDOMNode.ELEMENT_NODE) {
                    nsIDOMHTMLElement ic = (nsIDOMHTMLElement) c
                            .queryInterface(nsIDOMHTMLElement.NS_IDOMHTMLELEMENT_IID);
                    if (casData.htmlElementPos.containsKey(ic.getId())) {
                      int[] pos = casData.htmlElementPos.get(ic.getId());
                      rightPos = pos[0];
                      foundNext = true;
                      break;
                    } else {

                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return rightPos;
  }

  private void createSpan(nsIDOMEventListener listener, nsIDOMNode node, int start, int end) {

    LinkedList<HtmlIdInfo> newIDs = casData.annotationRanges.createHtmlIDs(start, end);

    if (newIDs.isEmpty())
      return;

    nsIDOMElement container = node.getOwnerDocument().createElementNS(
            "http://www.w3.org/1999/xhtml", "SPAN");
    node.getParentNode().replaceChild(container, node);

    int last = start;

    while (!newIDs.isEmpty()) {
      HtmlIdInfo idInfo = newIDs.removeFirst();

      if (last < idInfo.getStart()) {
        String decode = Translate.decode(casData.getCAS().getDocumentText()
                .substring(last, idInfo.getStart()));
        nsIDOMText text = node.getOwnerDocument().createTextNode(decode);
        container.appendChild(text);
        last = idInfo.getStart();
      }
      // Span
      nsIDOMElement span = node.getOwnerDocument().createElementNS("http://www.w3.org/1999/xhtml",
              "SPAN");
      span.setAttribute("id", idInfo.getId());

      casData.htmlElementPos.put(idInfo.getId(), new int[] { 0, idInfo.getStart(), idInfo.getEnd(),
          0 });

      nsIDOMEventTarget target = (nsIDOMEventTarget) span
              .queryInterface(nsIDOMEventTarget.NS_IDOMEVENTTARGET_IID);

      target.addEventListener("click", listener, false);

      String decode = Translate.decode(casData.getCAS().getDocumentText()
              .substring(idInfo.getStart(), idInfo.getEnd()));
      nsIDOMText text = node.getOwnerDocument().createTextNode(decode);
      span.appendChild(text);
      container.appendChild(span);
      last = idInfo.getEnd();
    }

    if (last < end) {
      String decode = Translate.decode(casData.getCAS().getDocumentText().substring(last, end));
      nsIDOMText text = node.getOwnerDocument().createTextNode(decode);
      container.appendChild(text);
    }
  }

}
