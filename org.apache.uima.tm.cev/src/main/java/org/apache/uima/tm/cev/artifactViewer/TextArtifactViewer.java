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

package org.apache.uima.tm.cev.artifactViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.TypeSystem;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.tm.cev.data.CEVAnnotationRanges.StyleRangeContainer;
import org.apache.uima.tm.cev.data.CEVData;
import org.apache.uima.tm.cev.editor.CEVViewer;
import org.apache.uima.tm.cev.extension.ICEVArtifactViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class TextArtifactViewer extends StyledText implements ICEVArtifactViewer {

  private class ToolTipListener implements Listener {

    private Shell tip = null;

    private Label label = null;

    private StyledText text;

    private StyledText tipText;

    private ToolTipListener(StyledText text) {
      this.text = text;
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
          Point point = new Point(event.x, event.y);
          int offsetAtLocation = -1;
          try {
            offsetAtLocation = text.getOffsetAtLocation(point);
          } catch (Exception e) {
            break;
          }

          List<AnnotationFS> annotationsToShow = new ArrayList<AnnotationFS>();
          List<AnnotationFS> annotationsAt = casData.getAnnotationsAt(offsetAtLocation);
          HashMap<Type, HashMap<AnnotationFS, Boolean>> annotationState = casData
                  .getAnnotationState();
          for (AnnotationFS annotationFS : annotationsAt) {
            Type type = annotationFS.getType();
            HashMap<AnnotationFS, Boolean> hashMap = annotationState.get(type);
            Boolean visible = hashMap.get(annotationFS);
            if (visible) {
              annotationsToShow.add(annotationFS);
            }
          }
          TypeSystem typeSystem = casData.getCAS().getTypeSystem();
          Type stringType = typeSystem.getType("uima.cas.String");
          StringBuilder infoText = new StringBuilder();

          List<StyleRange> styles = new ArrayList<StyleRange>();

          int start = 0;
          int end = 0;
          for (AnnotationFS each : annotationsToShow) {
            Type type = each.getType();
            String coveredText = each.getCoveredText();
            coveredText = shrink(coveredText);
            start = infoText.length();
            infoText.append(type.getShortName() + ": ");
            end = infoText.length();
            StyleRange style1 = new StyleRange();
            style1.start = start;
            style1.length = end - start;
            style1.fontStyle = SWT.BOLD;
            styles.add(style1);
            infoText.append(coveredText + "\n");
            List<Feature> features = type.getFeatures();
            for (Feature feature : features) {
              if (typeSystem.subsumes(stringType, feature.getRange())) {
                String shortName = feature.getShortName();
                String stringValue = each.getStringValue(feature);
                start = infoText.length();
                infoText.append("- " + shortName + ": ");
                end = infoText.length();
                StyleRange style2 = new StyleRange();
                style2.start = start;
                style2.length = end - start;
                style2.fontStyle = SWT.BOLD;
                styles.add(style2);
                infoText.append(shrink(stringValue) + "\n");
              }
            }
            infoText.append("\n");
          }

          if (tip != null && !tip.isDisposed())
            tip.dispose();

          if (!infoText.toString().equals("")) {
            tip = new Shell(Display.getCurrent().getActiveShell(), SWT.BALLOON
                    | SWT.ICON_INFORMATION | SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
            tip.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
            FillLayout layout = new FillLayout();
            layout.marginWidth = 2;
            tip.setLayout(layout);

            tipText = new StyledText(tip, SWT.NONE);
            tipText.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_FOREGROUND));
            tipText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
            tipText.setText(infoText.toString());

            tipText.setStyleRanges(styles.toArray(new StyleRange[0]));
            tipText.layout(true);

            Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            Point pt = text.toDisplay(new Point(point.x + 20, point.y + 10));
            tip.setBounds(pt.x, pt.y, size.x, size.y);
            tip.setVisible(true);
          }
        }
      }
    }

    private String shrink(String coveredText) {
      if (coveredText != null && coveredText.length() > 100) {
        coveredText = coveredText.substring(0, 40) + "...";
      }
      return coveredText;
    }
  }

  private CEVData casData;

  private CEVViewer viewer;

  public TextArtifactViewer(Composite composite, int style, CEVViewer viewer) {
    super(composite, style);
    this.viewer = viewer;
    ToolTipListener tl = new ToolTipListener(this);
    addListener(SWT.Dispose, tl);
    addListener(SWT.KeyDown, tl);
    addListener(SWT.MouseMove, tl);
    addListener(SWT.MouseHover, tl);
  }

  public void annotationStateChanged(Type type) {
    StyleRangeContainer sc = casData.getAllStyleRanges();
    setStyleRanges(sc.getStart(), sc.getLength(), sc.getIndices(), sc.getRanges());
  }

  public void annotationStateChanged() {
    StyleRangeContainer sc = casData.getAllStyleRanges();
    int start = sc.getStart();
    int length = sc.getLength();
    int[] indices = sc.getIndices();
    StyleRange[] ranges = sc.getRanges();
    setStyleRanges(start, length, indices, ranges);
  }

  public Point getViewerSelectionRange() {
    return getSelectionRange();
  }

  public void viewerWidgetSelected() {
    StyleRangeContainer sc = casData.getAllStyleRanges();
    setStyleRanges(sc.getStart(), sc.getLength(), sc.getIndices(), sc.getRanges());
  }

  public void moveToAnnotation(AnnotationFS annot) {
    if (!annot.getCoveredText().equals("\n") && !annot.getCoveredText().equals("\r")) {
      setSelection(annot.getBegin());
    }
  }

  public void viewChanged(CEVData casData) {
    this.casData = casData;
    List<Type> initialVisibleTypes = viewer.getInitialVisibleTypes();
    for (Type type : initialVisibleTypes) {
      annotationStateChanged(type);
    }

  }

}
