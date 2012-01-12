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

package org.apache.uima.textmarker.verbalize;

import java.util.Map.Entry;

import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.action.AddAction;
import org.apache.uima.textmarker.action.AssignAction;
import org.apache.uima.textmarker.action.CallAction;
import org.apache.uima.textmarker.action.ColorAction;
import org.apache.uima.textmarker.action.CreateAction;
import org.apache.uima.textmarker.action.DelAction;
import org.apache.uima.textmarker.action.ExpandAction;
import org.apache.uima.textmarker.action.FillAction;
import org.apache.uima.textmarker.action.FilterTypeAction;
import org.apache.uima.textmarker.action.GatherAction;
import org.apache.uima.textmarker.action.GetAction;
import org.apache.uima.textmarker.action.GetFeatureAction;
import org.apache.uima.textmarker.action.LogAction;
import org.apache.uima.textmarker.action.MarkAction;
import org.apache.uima.textmarker.action.MarkFastAction;
import org.apache.uima.textmarker.action.MarkLastAction;
import org.apache.uima.textmarker.action.MarkOnceAction;
import org.apache.uima.textmarker.action.MergeAction;
import org.apache.uima.textmarker.action.RemoveAction;
import org.apache.uima.textmarker.action.RemoveDuplicateAction;
import org.apache.uima.textmarker.action.ReplaceAction;
import org.apache.uima.textmarker.action.RetainTypeAction;
import org.apache.uima.textmarker.action.SetFeatureAction;
import org.apache.uima.textmarker.action.TransferAction;
import org.apache.uima.textmarker.action.TrieAction;
import org.apache.uima.textmarker.action.UnmarkAction;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;


public class ActionVerbalizer {

  private TextMarkerVerbalizer verbalizer;

  public ActionVerbalizer(TextMarkerVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalizeName(AbstractTextMarkerAction action) {
    if (action instanceof AddAction) {
      return "ADD";
    } else if (action instanceof AssignAction) {
      return "ASSIGN";
    } else if (action instanceof CallAction) {
      return "CALL";
    } else if (action instanceof ColorAction) {
      return "COLOR";
    } else if (action instanceof CreateAction) {
      return "CREATE";
    } else if (action instanceof DelAction) {
      return "DEL";
    } else if (action instanceof FillAction) {
      return "FILL";
    } else if (action instanceof FilterTypeAction) {
      return "FILTERTYPE";
    } else if (action instanceof LogAction) {
      return "LOG";
    } else if (action instanceof MarkOnceAction) {
      return "MARKONCE";
    } else if (action instanceof ExpandAction) {
      return "EXPAND";
    } else if (action instanceof MarkAction) {
      MarkAction a = (MarkAction) action;
      if (a.getScore() != null) {
        return "MARKSCORE";
      }
      return "MARK";
    } else if (action instanceof MarkFastAction) {
      return "MARKFAST";
    } else if (action instanceof MarkLastAction) {
      return "MARKLAST";
    } else if (action instanceof ReplaceAction) {
      return "REPLACE";
    } else if (action instanceof RetainTypeAction) {
      return "RETAINTYPE";
    } else if (action instanceof SetFeatureAction) {
      String name = "SETFEATURE";
      return name;
    } else if (action instanceof GetFeatureAction) {
      String name = "GETFEATURE";
      return name;
    } else if (action instanceof UnmarkAction) {
      return "UNMARK";
    } else if (action instanceof TransferAction) {
      return "TRANSFER";
    } else if (action instanceof TrieAction) {
      return "TRIE";
    } else if (action instanceof GatherAction) {
      return "GATHER";
    } else if (action instanceof MergeAction) {
      return "MERGE";
    } else if (action instanceof GetAction) {
      return "GET";
    } else if (action instanceof RemoveAction) {
      return "REMOVE";
    } else if (action instanceof RemoveDuplicateAction) {
      return "REMOVEDUPLICATE";
    }
    return action.getClass().getSimpleName();
  }

  public String verbalize(AbstractTextMarkerAction action) {
    if (action instanceof AssignAction) {
      AssignAction a = (AssignAction) action;
      return "ASSIGN(" + a.getVar() + "," + verbalizer.verbalize(a.getExpression()) + ")";
    } else if (action instanceof CallAction) {
      CallAction a = (CallAction) action;
      return "CALL(" + a.getNamespace() + ")";
    } else if (action instanceof ColorAction) {
      ColorAction a = (ColorAction) action;
      return "COLOR(" + verbalizer.verbalize(a.getType()) + ","
              + verbalizer.verbalize(a.getBgColor()) + "," + verbalizer.verbalize(a.getFgColor())
              + "," + verbalizer.verbalize(a.getSelected()) + ")";
    } else if (action instanceof CreateAction) {
      CreateAction a = (CreateAction) action;
      StringBuilder features = new StringBuilder();
      if (a.getFeatures() != null) {
        features.append(",");
        for (Entry<StringExpression, TextMarkerExpression> each : a.getFeatures().entrySet()) {
          features.append(verbalizer.verbalize(each.getKey()));
          features.append("=");
          features.append(verbalizer.verbalize(each.getValue()));
          features.append(",");
        }
      }
      String feats = features.toString();
      if (feats.endsWith(",")) {
        feats = feats.substring(0, features.length() - 1);
      }
      String indexes = "";
      if (a.getIndexes() != null) {
        indexes = verbalizer.verbalizeExpressionList(a.getIndexes());
        indexes += ", ";
      }
      return "CREATE(" + verbalizer.verbalize(a.getStructureType()) + indexes + feats + ")";
    } else if (action instanceof GatherAction) {
      GatherAction a = (GatherAction) action;
      String features = "";
      if (a.getFeatures() != null) {
        features += ", ";
        for (StringExpression each : a.getFeatures().keySet()) {
          features += verbalizer.verbalize(each);
          features += "=";
          features += verbalizer.verbalize(a.getFeatures().get(each));
          features += ", ";
        }
      }
      if (features.endsWith(", ")) {
        features = features.substring(0, features.length() - 2);
      }
      String indexes = "";
      if (a.getIndexes() != null) {
        indexes += ", ";
        indexes = verbalizer.verbalizeExpressionList(a.getIndexes());
      }
      if (indexes.endsWith(", ") && !a.getFeatures().isEmpty()) {
        indexes = indexes.substring(0, features.length() - 1);
      }
      return "GATHER(" + verbalizer.verbalize(a.getStructureType()) + ", " + indexes + features
              + ")";
    } else if (action instanceof DelAction) {
      return "DEL";
    } else if (action instanceof FillAction) {
      FillAction a = (FillAction) action;
      String features = "";
      if (a.getFeatures() != null) {
        features += ",";
        for (StringExpression each : a.getFeatures().keySet()) {
          features += verbalizer.verbalize(each);
          features += "=";
          features += verbalizer.verbalize(a.getFeatures().get(each));
          features += ",";
        }
      }
      if (features.endsWith(",")) {
        features = features.substring(0, features.length() - 1);
      }
      return "FILL(" + verbalizer.verbalize(a.getStructureType()) + features + ")";
    } else if (action instanceof FilterTypeAction) {
      FilterTypeAction a = (FilterTypeAction) action;
      return a.getList().isEmpty() ? "FILTERTYPE" : "FILTERTYPE("
              + verbalizer.verbalizeExpressionList(a.getList()) + ")";
    } else if (action instanceof LogAction) {
      LogAction a = (LogAction) action;
      return "LOG(" + verbalizer.verbalize(a.getText()) + "," + a.getLevel() + ")";
    } else if (action instanceof MarkOnceAction) {
      MarkOnceAction a = (MarkOnceAction) action;
      // String score = verbalizer.verbalize(a.getScore());
      // if (!"".equals(score)) {
      // score += ",";
      // }
      String string = "";
      if (a.getList() != null && !a.getList().isEmpty()) {
        string = "," + verbalizer.verbalizeExpressionList(a.getList());
      }
      return "MARKONCE(" + verbalizer.verbalize(a.getType()) + string + ")";
    } else if (action instanceof ExpandAction) {
      ExpandAction a = (ExpandAction) action;
      String string = "";
      if (a.getList() != null && !a.getList().isEmpty()) {
        string = "," + verbalizer.verbalizeExpressionList(a.getList());
      }
      return "EXPAND(" + verbalizer.verbalize(a.getType()) + string + ")";
    } else if (action instanceof MarkAction) {
      MarkAction a = (MarkAction) action;
      if (a.getScore() != null) {
        String score = verbalizer.verbalize(a.getScore());
        if (!"".equals(score)) {
          score += ",";
        }
        String string = "";
        if (a.getList() != null && !a.getList().isEmpty()) {
          string = "," + verbalizer.verbalizeExpressionList(a.getList());
        }
        return "MARKSCORE(" + score + verbalizer.verbalize(a.getType()) + string + ")";
      } else {
        String string = "";
        if (a.getList() != null && !a.getList().isEmpty()) {
          string = "," + verbalizer.verbalizeExpressionList(a.getList());
        }
        return "MARK(" + verbalizer.verbalize(a.getType()) + string + ")";
      }
    } else if (action instanceof MarkFastAction) {
      MarkFastAction a = (MarkFastAction) action;
      return "MARKFAST(" + verbalizer.verbalize(a.getType()) + ","
              + verbalizer.verbalize(a.getList()) + ")";
    } else if (action instanceof MarkLastAction) {
      MarkLastAction a = (MarkLastAction) action;
      return "MARKLAST(" + verbalizer.verbalize(a.getType()) + ")";
    } else if (action instanceof ReplaceAction) {
      ReplaceAction a = (ReplaceAction) action;
      return "REPLACE(" + verbalizer.verbalize(a.getReplacement()) + ")";
    } else if (action instanceof RetainTypeAction) {
      RetainTypeAction a = (RetainTypeAction) action;
      return a.getList().isEmpty() ? "RETAINTYPE" : "RETAINTYPE("
              + verbalizer.verbalizeExpressionList(a.getList()) + ")";
    } else if (action instanceof SetFeatureAction) {
      SetFeatureAction a = (SetFeatureAction) action;
      String e1 = verbalizer.verbalize(a.getFeatureStringExpression());
      String name = "";
      String e2 = "";
      if (a.getBooleanExpr() != null) {
        name = "SETFEATURE(";
        e2 = verbalizer.verbalize(a.getBooleanExpr());
      } else if (a.getNumberExpr() != null) {
        name = "SETFEATURE(";
        e2 = verbalizer.verbalize(a.getNumberExpr());
      } else if (a.getStringExpr() != null) {
        name = "SETFEATURE(";
        e2 = verbalizer.verbalize(a.getStringExpr());
      }
      return name + e1 + "," + e2 + ")";
    } else if (action instanceof GetFeatureAction) {
      GetFeatureAction a = (GetFeatureAction) action;
      String name = "GETFEATURE(";
      return name + verbalizer.verbalize(a.getFeatureStringExpression()) + "," + a.getVariable()
              + ")";
    } else if (action instanceof UnmarkAction) {
      UnmarkAction a = (UnmarkAction) action;
      return "UNMARK(" + verbalizer.verbalize(a.getType()) + ")";
    } else if (action instanceof TransferAction) {
      TransferAction a = (TransferAction) action;
      return "TRANSFER(" + verbalizer.verbalize(a.getType()) + ")";

    } else if (action instanceof TrieAction) {
      TrieAction a = (TrieAction) action;
      String map = "";
      if (a.getMap() != null) {
        map += ",";
        for (StringExpression each : a.getMap().keySet()) {
          map += verbalizer.verbalize(each);
          map += "=";
          map += verbalizer.verbalize(a.getMap().get(each));
          map += ",";
        }
      }
      return "TRIE(" + map + verbalizer.verbalize(a.getList()) + ","
              + verbalizer.verbalize(a.getIgnoreCase()) + ","
              + verbalizer.verbalize(a.getIgnoreLength()) + "," + verbalizer.verbalize(a.getEdit())
              + "," + verbalizer.verbalize(a.getDistance()) + ","
              + verbalizer.verbalize(a.getIgnoreChar()) + ")";
    } else if (action instanceof AddAction) {
      AddAction a = (AddAction) action;
      return "ADD(" + a.getListExpr() + "," + verbalizer.verbalizeExpressionList(a.getElements())
              + ")";
    } else if (action instanceof RemoveAction) {
      RemoveAction a = (RemoveAction) action;
      return "REMOVE(" + a.getListExpr() + ","
              + verbalizer.verbalizeExpressionList(a.getElements()) + ")";
    } else if (action instanceof RemoveAction) {
      RemoveAction a = (RemoveAction) action;
      return "REMOVEDUPLICATE(" + a.getListExpr() + ")";
    } else if (action instanceof MergeAction) {
      MergeAction a = (MergeAction) action;
      return "MERGE(" + verbalizer.verbalize(a.getUnion()) + "," + a.getTarget() + ","
              + verbalizer.verbalizeExpressionList(a.getLists()) + ")";
    } else if (action instanceof GetAction) {
      GetAction a = (GetAction) action;
      return "GET(" + verbalizer.verbalize(a.getListExpr()) + "," + a.getVar() + ","
              + verbalizer.verbalize(a.getOpExpr()) + ")";
    }

    return action.getClass().getSimpleName();
  }
}
