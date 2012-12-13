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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.action.AddAction;
import org.apache.uima.textmarker.action.AssignAction;
import org.apache.uima.textmarker.action.CallAction;
import org.apache.uima.textmarker.action.ClearAction;
import org.apache.uima.textmarker.action.ColorAction;
import org.apache.uima.textmarker.action.ConfigureAction;
import org.apache.uima.textmarker.action.CreateAction;
import org.apache.uima.textmarker.action.DelAction;
import org.apache.uima.textmarker.action.DynamicAnchoringAction;
import org.apache.uima.textmarker.action.ExecAction;
import org.apache.uima.textmarker.action.GetListAction;
import org.apache.uima.textmarker.action.MarkTableAction;
import org.apache.uima.textmarker.action.MatchedTextAction;
import org.apache.uima.textmarker.action.ShiftAction;
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
import org.apache.uima.textmarker.action.UnmarkAllAction;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.resource.WordTableExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;

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
    } else if (action instanceof ExecAction) {
      return "EXEC";
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
    } else if (action instanceof ShiftAction) {
      return "SHIFT";
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
    } else if (action instanceof ClearAction) {
      return "CLEAR";
    } else if (action instanceof ConfigureAction) {
      return "CONFIGURE";
    } else if (action instanceof DynamicAnchoringAction) {
      return "DYNAMICANCHORING";
    } else if (action instanceof GetListAction) {
      return "GETLIST";
    } else if (action instanceof MarkTableAction) {
      return "MARKTABLE";
    } else if (action instanceof MatchedTextAction) {
      return "MATCHEDTEXT";
    } else if (action instanceof RemoveDuplicateAction) {
      return "REMOVEDUPLICATE";
    } else if (action instanceof UnmarkAllAction) {
      return "UNMARKALL";
    }

    return action.getClass().getSimpleName();
  }

  public String verbalize(AbstractTextMarkerAction action) {
    String name = verbalizeName(action) + "(";
    if (action instanceof AssignAction) {
      AssignAction a = (AssignAction) action;
      return name + a.getVar() + ", " + verbalizer.verbalize(a.getExpression()) + ")";
    } else if (action instanceof ExecAction) {
      ExecAction a = (ExecAction) action;
      TypeListExpression typeList = a.getTypeList();
      String types = typeList == null ? "" : ", " + verbalizer.verbalize(typeList);
      return name + a.getNamespace() + types + ")";
    } else if (action instanceof CallAction) {
      CallAction a = (CallAction) action;
      return name + a.getNamespace() + ")";
    } else if (action instanceof ColorAction) {
      ColorAction a = (ColorAction) action;
      return name + verbalizer.verbalize(a.getType()) + ", " + verbalizer.verbalize(a.getBgColor())
              + ", " + verbalizer.verbalize(a.getFgColor()) + ", "
              + verbalizer.verbalize(a.getSelected()) + ")";
    } else if (action instanceof CreateAction) {
      CreateAction a = (CreateAction) action;
      StringBuilder features = new StringBuilder();
      if (a.getFeatures() != null) {
        features.append(", ");
        for (Entry<StringExpression, TextMarkerExpression> each : a.getFeatures().entrySet()) {
          features.append(verbalizer.verbalize(each.getKey()));
          features.append(" = ");
          features.append(verbalizer.verbalize(each.getValue()));
          features.append(", ");
        }
      }
      String feats = features.toString();
      if (feats.endsWith(", ")) {
        feats = feats.substring(0, features.length() - 2);
      }
      String indexes = "";
      if (a.getIndexes() != null) {
        indexes += ", ";
        indexes += verbalizer.verbalizeExpressionList(a.getIndexes());
      }

      return name + verbalizer.verbalize(a.getStructureType()) + indexes + feats + ")";
    } else if (action instanceof GatherAction) {
      GatherAction a = (GatherAction) action;
      String features = "";
      if (a.getFeatures() != null) {
        features += ", ";
        for (StringExpression each : a.getFeatures().keySet()) {
          features += verbalizer.verbalize(each);
          features += " = ";
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
      return name + verbalizer.verbalize(a.getStructureType()) + ", " + indexes + features + ")";
    } else if (action instanceof DelAction) {
      return "DEL";
    } else if (action instanceof FillAction) {
      FillAction a = (FillAction) action;
      String features = "";
      if (a.getFeatures() != null) {
        features += ", ";
        for (StringExpression each : a.getFeatures().keySet()) {
          features += verbalizer.verbalize(each);
          features += " = ";
          features += verbalizer.verbalize(a.getFeatures().get(each));
          features += ", ";
        }
      }
      if (features.endsWith(", ")) {
        features = features.substring(0, features.length() - 2);
      }
      return name + verbalizer.verbalize(a.getStructureType()) + features + ")";
    } else if (action instanceof FilterTypeAction) {
      FilterTypeAction a = (FilterTypeAction) action;
      return a.getList().isEmpty() ? "FILTERTYPE" : "FILTERTYPE("
              + verbalizer.verbalizeExpressionList(a.getList()) + ")";
    } else if (action instanceof LogAction) {
      LogAction a = (LogAction) action;
      return name + verbalizer.verbalize(a.getText()) + ", " + a.getLevel() + ")";
    } else if (action instanceof MarkOnceAction) {
      MarkOnceAction a = (MarkOnceAction) action;
      // String score = verbalizer.verbalize(a.getScore());
      // if (!"".equals(score)) {
      // score += ", ";
      // }
      String string = "";
      if (a.getList() != null && !a.getList().isEmpty()) {
        string = ", " + verbalizer.verbalizeExpressionList(a.getList());
      }
      return name + verbalizer.verbalize(a.getType()) + string + ")";
    } else if (action instanceof ShiftAction) {
      ShiftAction a = (ShiftAction) action;
      String string = "";
      if (a.getList() != null && !a.getList().isEmpty()) {
        string = ", " + verbalizer.verbalizeExpressionList(a.getList());
      }
      return name + verbalizer.verbalize(a.getType()) + string + ")";
    } else if (action instanceof MarkAction) {
      MarkAction a = (MarkAction) action;
      if (a.getScore() != null) {
        String score = verbalizer.verbalize(a.getScore());
        if (!"".equals(score)) {
          score += ", ";
        }
        String string = "";
        if (a.getList() != null && !a.getList().isEmpty()) {
          string = ", " + verbalizer.verbalizeExpressionList(a.getList());
        }
        return name + score + verbalizer.verbalize(a.getType()) + string + ")";
      } else {
        String string = "";
        if (a.getList() != null && !a.getList().isEmpty()) {
          string = ", " + verbalizer.verbalizeExpressionList(a.getList());
        }
        return name + verbalizer.verbalize(a.getType()) + string + ")";
      }
    } else if (action instanceof MarkFastAction) {
      MarkFastAction a = (MarkFastAction) action;
      String list = "";
      if (a.getList() != null) {
        list = verbalizer.verbalize(a.getList());
      } else if (a.getStringList() != null) {
        list = verbalizer.verbalize(a.getStringList());
      }
      return name + verbalizer.verbalize(a.getType()) + ", " + list + ", "
              + verbalizer.verbalize(a.getIgnore()) + ", "
              + verbalizer.verbalize(a.getIgnoreLength()) + ", "
              + verbalizer.verbalize(a.getIgnoreWS()) + ")";
    } else if (action instanceof MarkLastAction) {
      MarkLastAction a = (MarkLastAction) action;
      return name + verbalizer.verbalize(a.getType()) + ")";
    } else if (action instanceof ReplaceAction) {
      ReplaceAction a = (ReplaceAction) action;
      return name + verbalizer.verbalize(a.getReplacement()) + ")";
    } else if (action instanceof RetainTypeAction) {
      RetainTypeAction a = (RetainTypeAction) action;
      return a.getList().isEmpty() ? "RETAINTYPE" : "RETAINTYPE("
              + verbalizer.verbalizeExpressionList(a.getList()) + ")";
    } else if (action instanceof SetFeatureAction) {
      SetFeatureAction a = (SetFeatureAction) action;
      String e1 = verbalizer.verbalize(a.getFeatureStringExpression());
      String e2 = "";
      if (a.getBooleanExpr() != null) {
        e2 = verbalizer.verbalize(a.getBooleanExpr());
      } else if (a.getNumberExpr() != null) {
        e2 = verbalizer.verbalize(a.getNumberExpr());
      } else if (a.getStringExpr() != null) {
        e2 = verbalizer.verbalize(a.getStringExpr());
      } else if (a.getTypeExpr() != null) {
        e2 = verbalizer.verbalize(a.getTypeExpr());
      }
      return name + e1 + ", " + e2 + ")";
    } else if (action instanceof GetFeatureAction) {
      GetFeatureAction a = (GetFeatureAction) action;

      return name + verbalizer.verbalize(a.getFeatureStringExpression()) + ", " + a.getVariable()
              + ")";
    } else if (action instanceof UnmarkAction) {
      UnmarkAction a = (UnmarkAction) action;
      if (a.getAllAnchor() == null) {
        if (a.getList() == null) {
          return name + verbalizer.verbalize(a.getType()) + ")";
        } else {
          return name + verbalizer.verbalize(a.getType()) + ", "
                  + verbalizer.verbalizeExpressionList(a.getList()) + ")";
        }
      } else {
        return name + verbalizer.verbalize(a.getType()) + ", "
                + verbalizer.verbalizeExpressionList(a.getList()) + ", "
                + verbalizer.verbalize(a.getAllAnchor()) + ")";
      }
    } else if (action instanceof TransferAction) {
      TransferAction a = (TransferAction) action;
      return name + verbalizer.verbalize(a.getType()) + ")";
    } else if (action instanceof TrieAction) {
      TrieAction a = (TrieAction) action;
      String map = "";
      if (a.getMap() != null) {
        for (StringExpression each : a.getMap().keySet()) {
          map += verbalizer.verbalize(each);
          map += " = ";
          map += verbalizer.verbalize(a.getMap().get(each));
          map += ", ";
        }
      }
      return name + map + verbalizer.verbalize(a.getList()) + ", "
              + verbalizer.verbalize(a.getIgnoreCase()) + ", "
              + verbalizer.verbalize(a.getIgnoreLength()) + ", "
              + verbalizer.verbalize(a.getEdit()) + ", " + verbalizer.verbalize(a.getDistance())
              + ", " + verbalizer.verbalize(a.getIgnoreChar()) + ")";
    } else if (action instanceof AddAction) {
      AddAction a = (AddAction) action;
      return name + a.getListExpr() + ", " + verbalizer.verbalizeExpressionList(a.getElements())
              + ")";
    } else if (action instanceof RemoveAction) {
      RemoveAction a = (RemoveAction) action;
      return name + a.getListExpr() + ", " + verbalizer.verbalizeExpressionList(a.getElements())
              + ")";
    } else if (action instanceof RemoveAction) {
      RemoveAction a = (RemoveAction) action;
      return name + a.getListExpr() + ")";
    } else if (action instanceof MergeAction) {
      MergeAction a = (MergeAction) action;
      return name + verbalizer.verbalize(a.getUnion()) + ", " + a.getTarget() + ", "
              + verbalizer.verbalizeExpressionList(a.getLists()) + ")";
    } else if (action instanceof GetAction) {
      GetAction a = (GetAction) action;
      return name + verbalizer.verbalize(a.getListExpr()) + ", " + a.getVar() + ", "
              + verbalizer.verbalize(a.getOpExpr()) + ")";
    } else if (action instanceof ClearAction) {
      ClearAction a = (ClearAction) action;
      return name + a.getList() + ")";
    } else if (action instanceof ConfigureAction) {
      ConfigureAction a = (ConfigureAction) action;

      String map = "";
      if (a.getParameterMap() != null) {
        map += ", ";
        for (StringExpression each : a.getParameterMap().keySet()) {
          map += verbalizer.verbalize(each);
          map += " = ";
          map += verbalizer.verbalize(a.getParameterMap().get(each));
          map += ", ";
        }
      }
      map = map.substring(0, map.length() - 2);
      return name + a.getNamespace() + map + ")";
    } else if (action instanceof DynamicAnchoringAction) {
      DynamicAnchoringAction a = (DynamicAnchoringAction) action;

      BooleanExpression active = a.getActive();
      NumberExpression panelty = a.getPanelty();
      NumberExpression factor = a.getFactor();

      String pa = verbalizer.verbalize(active);
      String pp = panelty == null ? "" : ", " + verbalizer.verbalize(panelty);
      String pf = factor == null ? "" : ", " + verbalizer.verbalize(factor);
      return name + pa + pp + pf + ")";
    } else if (action instanceof GetListAction) {
      GetListAction a = (GetListAction) action;
      String var = a.getVar();
      String op = verbalizer.verbalize(a.getOpExpr());
      return name + var + ", " + op + ")";
    } else if (action instanceof MarkTableAction) {
      MarkTableAction a = (MarkTableAction) action;
      TypeExpression typeExpr = a.getTypeExpr();
      NumberExpression indexExpr = a.getIndexExpr();
      WordTableExpression tableExpr = a.getTableExpr();
      Map<StringExpression, NumberExpression> featureMap = a.getFeatureMap();
      BooleanExpression ignoreCase = a.getIgnoreCase();
      NumberExpression ignoreLength = a.getIgnoreLength();
      StringExpression ignoreChar = a.getIgnoreChar();
      NumberExpression maxIgnoreChar = a.getMaxIgnoreChar();

      String type = verbalizer.verbalize(typeExpr);
      String index = verbalizer.verbalize(indexExpr);
      String table = verbalizer.verbalize(tableExpr);
      String map = "";
      if (featureMap != null) {
        map += ", ";
        for (StringExpression each : featureMap.keySet()) {
          map += verbalizer.verbalize(each);
          map += " = ";
          map += verbalizer.verbalize(featureMap.get(each));
          map += ", ";
        }
      }
      String icase = ignoreCase == null ? "" : ", " + verbalizer.verbalize(ignoreCase);
      String ilength = ignoreCase == null ? "" : ", " + verbalizer.verbalize(ignoreLength);
      String ichar = ignoreCase == null ? "" : ", " + verbalizer.verbalize(ignoreChar);
      String mic = ignoreCase == null ? "" : ", " + verbalizer.verbalize(maxIgnoreChar);

      return name + type + ", " + index + ", " + table + map + icase + ilength + ichar + mic + ")";
    } else if (action instanceof MatchedTextAction) {
      MatchedTextAction a = (MatchedTextAction) action;
      String var = a.getVar();
      List<NumberExpression> list = a.getList();
      String indexes = list == null ? "" : ", " + verbalizer.verbalizeExpressionList(list);
      return name + var + indexes + ")";
    } else if (action instanceof RemoveDuplicateAction) {
      RemoveDuplicateAction a = (RemoveDuplicateAction) action;
      String listExpr = a.getListExpr();
      return name + listExpr + ")";
    } else if (action instanceof UnmarkAllAction) {
      UnmarkAllAction a = (UnmarkAllAction) action;
      String verbalize = verbalizer.verbalize(a.getType());
      String but = a.getList() == null ? "" : ", " + verbalizer.verbalize(a.getList());
      return name + verbalize + but + ")";
    }

    return action.getClass().getSimpleName();
  }
}
