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

package org.apache.uima.tm.textmarker.action;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.antlr.runtime.Token;
import org.apache.uima.tm.textmarker.kernel.TextMarkerBlock;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.ListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.list.TypeListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.resource.WordListExpression;
import org.apache.uima.tm.textmarker.kernel.expression.resource.WordTableExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;


public class ActionFactory {

  private ActionFactory() {
  }

  public static AbstractTextMarkerAction createColorAction(TypeExpression typeExpr,
          StringExpression bgcolor, StringExpression fgcolor, BooleanExpression selected,
          TextMarkerBlock parent) {
    return new ColorAction(typeExpr, bgcolor, fgcolor, selected);
  }

  public static AbstractTextMarkerAction createDelAction(TextMarkerBlock parent) {
    return new DelAction();
  }

  public static AbstractTextMarkerAction createMarkFastAction(TypeExpression type,
          WordListExpression list, BooleanExpression ignore, NumberExpression ignoreLength,
          TextMarkerBlock parent) {
    return new MarkFastAction(type, list, ignore, ignoreLength);
  }

  public static AbstractTextMarkerAction createMarkLastAction(TypeExpression type,
          TextMarkerBlock parent) {
    return new MarkLastAction(type);
  }

  public static AbstractTextMarkerAction createRetainMarkupAction(List<StringExpression> markup,
          TextMarkerBlock parent) {
    return new RetainMarkupAction(markup);
  }

  public static AbstractTextMarkerAction createRetainTypeAction(List<TypeExpression> types,
          TextMarkerBlock parent) {
    return new RetainTypeAction(types);
  }

  public static AbstractTextMarkerAction createLogAction(StringExpression expr, Token log,
          TextMarkerBlock parent) {
    String logString = log == null ? "INFO" : log.getText();
    Level level = Level.parse(logString.toUpperCase());
    return new LogAction(expr, level);
  }

  public static AbstractTextMarkerAction createMarkAction(NumberExpression score,
          TypeExpression type, List<NumberExpression> list, TextMarkerBlock parent) {
    return new MarkAction(type, score, list);
  }

  public static AbstractTextMarkerAction createMarkOnceAction(NumberExpression score,
          TypeExpression type, List<NumberExpression> list, TextMarkerBlock env) {
    return new MarkOnceAction(type, score, list);
  }

  public static AbstractTextMarkerAction createReplaceAction(StringExpression lit,
          TextMarkerBlock parent) {
    return new ReplaceAction(lit);
  }

  public static AbstractTextMarkerAction createCreateAction(TypeExpression typeExpr,
          Map<StringExpression, TextMarkerExpression> map, List<NumberExpression> indexes,
          TextMarkerBlock parent) {
    return new CreateAction(typeExpr, map, indexes);
  }

  public static AbstractTextMarkerAction createGatherAction(TypeExpression typeExpr,
          Map<StringExpression, TextMarkerExpression> map, List<NumberExpression> indexes,
          TextMarkerBlock parent) {
    return new GatherAction(typeExpr, map, indexes);
  }

  public static AbstractTextMarkerAction createFillAction(TypeExpression type,
          Map<StringExpression, TextMarkerExpression> map, TextMarkerBlock parent) {
    return new FillAction(type, map);
  }

  public static AbstractTextMarkerAction createCallAction(String ns, TextMarkerBlock parent) {
    return new CallAction(ns);
  }

  public static AbstractTextMarkerAction createAssignAction(Token nv, TextMarkerExpression e,
          TextMarkerBlock parent) {
    return new AssignAction(nv.getText(), e);
  }

  public static AbstractTextMarkerAction createFilterTypeAction(List<TypeExpression> types,
          TextMarkerBlock parent) {
    return new FilterTypeAction(types);
  }

  public static AbstractTextMarkerAction createFilterMarkupAction(List<StringExpression> list,
          TextMarkerBlock parent) {
    return new FilterMarkupAction(list);
  }

  public static AbstractTextMarkerAction createSetFeatureAction(StringExpression f, Object v,
          TextMarkerBlock parent) {
    if (v instanceof NumberExpression) {
      return new SetFeatureAction(f, (NumberExpression) v);
    } else if (v instanceof StringExpression) {
      return new SetFeatureAction(f, (StringExpression) v);
    } else if (v instanceof BooleanExpression) {
      return new SetFeatureAction(f, (BooleanExpression) v);
    }
    return null;
  }

  public static AbstractTextMarkerAction createUnmarkAction(TypeExpression f, TextMarkerBlock env) {
    return new UnmarkAction(f);
  }

  public static AbstractTextMarkerAction createUnmarkAllAction(TypeExpression f,
          TypeListExpression list, TextMarkerBlock env) {
    return new UnmarkAllAction(f, list);
  }

  public static AbstractTextMarkerAction createComposedAction(
          List<AbstractTextMarkerAction> actions, TextMarkerBlock env) {
    return new ComposedAction(actions);
  }

  public static AbstractTextMarkerAction createActionVariable(Token id) {
    return new VariableAction(id.getText());
  }

  public static AbstractTextMarkerAction createTransferAction(TypeExpression f, TextMarkerBlock env) {
    return new TransferAction(f);
  }

  public static AbstractTextMarkerAction createTrieAction(WordListExpression list,
          Map<StringExpression, TypeExpression> map, BooleanExpression ignoreCase,
          NumberExpression ignoreLength, BooleanExpression edit, NumberExpression distance,
          StringExpression ignoreChar, TextMarkerBlock parent) {
    return new TrieAction(list, map, ignoreCase, ignoreLength, edit, distance, ignoreChar);
  }

  public static AbstractTextMarkerAction createExecAction(String ns, TypeListExpression tl,
          TextMarkerBlock env) {
    return new ExecAction(ns, tl);
  }

  public static AbstractTextMarkerAction createMarkTableAction(TypeExpression structure,
          NumberExpression index, WordTableExpression table,
          Map<StringExpression, NumberExpression> map, TextMarkerBlock env) {
    return new MarkTableAction(structure, index, table, map);
  }

  public static AbstractTextMarkerAction createMergeAction(BooleanExpression union, Token target,
          List<ListExpression> list, TextMarkerBlock env) {
    return new MergeAction(union, target == null ? null : target.getText(), list);
  }

  public static AbstractTextMarkerAction createGetAction(ListExpression<TextMarkerExpression> f,
          Token var, StringExpression op, TextMarkerBlock env) {
    return new GetAction(f, var == null ? null : var.getText(), op);
  }

  public static AbstractTextMarkerAction createRemoveAction(Token var,
          List<TextMarkerExpression> list, TextMarkerBlock env) {
    return new RemoveAction(var == null ? null : var.getText(), list);
  }

  public static AbstractTextMarkerAction createAddAction(Token var,
          List<TextMarkerExpression> list, TextMarkerBlock env) {
    return new AddAction(var == null ? null : var.getText(), list);
  }

  public static AbstractTextMarkerAction createGetListAction(Token var, StringExpression op,
          TextMarkerBlock env) {
    return new GetListAction(var == null ? null : var.getText(), op);
  }

  public static AbstractTextMarkerAction createRemoveDuplicateAction(Token var, TextMarkerBlock env) {
    return new RemoveDuplicateAction(var == null ? null : var.getText());
  }

  public static AbstractTextMarkerAction createGetFeatureAction(StringExpression f, Token var,
          TextMarkerBlock env) {
    return new GetFeatureAction(f, var == null ? null : var.getText());
  }

  public static AbstractTextMarkerAction createMatchedTextAction(Token var,
          List<NumberExpression> list, TextMarkerBlock env) {
    return new MatchedTextAction(var == null ? null : var.getText(), list);
  }

  public static AbstractTextMarkerAction createClearAction(Token var, TextMarkerBlock env) {
    return new ClearAction(var == null ? null : var.getText());
  }

  public static AbstractTextMarkerAction createExpandAction(TypeExpression type,
          List<NumberExpression> list, TextMarkerBlock env) {
    return new ExpandAction(type, list);
  }

}
