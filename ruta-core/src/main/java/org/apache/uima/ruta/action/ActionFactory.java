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

package org.apache.uima.ruta.action;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.antlr.runtime.Token;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.BooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.number.NumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.StringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class ActionFactory {

  private ActionFactory() {
  }

  public static AbstractRutaAction createColorAction(TypeExpression typeExpr,
          StringExpression bgcolor, StringExpression fgcolor, BooleanExpression selected,
          RutaBlock parent) {
    return new ColorAction(typeExpr, bgcolor, fgcolor, selected);
  }

  public static AbstractRutaAction createDelAction(RutaBlock parent) {
    return new DelAction();
  }

  public static AbstractRutaAction createMarkFastAction(TypeExpression type,
          WordListExpression list, BooleanExpression ignore, NumberExpression ignoreLength,
          BooleanExpression ignoreWS, RutaBlock parent) {
    return new MarkFastAction(type, list, ignore, ignoreLength, ignoreWS);
  }

  public static AbstractRutaAction createMarkFastAction(TypeExpression type,
          StringListExpression list, BooleanExpression ignore, NumberExpression ignoreLength,
          BooleanExpression ignoreWS, RutaBlock env) {
    return new MarkFastAction(type, list, ignore, ignoreLength, ignoreWS);
  }

  public static AbstractRutaAction createMarkLastAction(TypeExpression type, RutaBlock parent) {
    return new MarkLastAction(type);
  }

  public static AbstractRutaAction createRetainTypeAction(List<TypeExpression> types,
          RutaBlock parent) {
    return new RetainTypeAction(types);
  }

  public static AbstractRutaAction createLogAction(StringExpression expr, Token log,
          RutaBlock parent) {
    String logString = log == null ? "INFO" : log.getText();
    Level level = Level.parse(logString.toUpperCase());
    return new LogAction(expr, level);
  }

  public static AbstractRutaAction createMarkAction(NumberExpression score, TypeExpression type,
          List<NumberExpression> list, RutaBlock parent) {
    return new MarkAction(type, score, list);
  }

  public static AbstractRutaAction createMarkOnceAction(NumberExpression score,
          TypeExpression type, List<NumberExpression> list, RutaBlock env) {
    return new MarkOnceAction(type, score, list);
  }

  public static AbstractRutaAction createReplaceAction(StringExpression lit, RutaBlock parent) {
    return new ReplaceAction(lit);
  }

  public static AbstractRutaAction createCreateAction(TypeExpression typeExpr,
          Map<StringExpression, RutaExpression> map, List<NumberExpression> indexes,
          RutaBlock parent) {
    return new CreateAction(typeExpr, map, indexes);
  }

  public static AbstractRutaAction createGatherAction(TypeExpression typeExpr,
          Map<StringExpression, RutaExpression> map, List<NumberExpression> indexes,
          RutaBlock parent) {
    return new GatherAction(typeExpr, map, indexes);
  }

  public static AbstractRutaAction createFillAction(TypeExpression type,
          Map<StringExpression, RutaExpression> map, RutaBlock parent) {
    return new FillAction(type, map);
  }

  public static AbstractRutaAction createCallAction(String ns, RutaBlock parent) {
    return new CallAction(ns);
  }

  public static AbstractRutaAction createConfigureAction(String ns,
          Map<StringExpression, RutaExpression> map, RutaBlock env) {
    return new ConfigureAction(ns, map);
  }

  public static AbstractRutaAction createAssignAction(Token nv, RutaExpression e, RutaBlock parent) {
    return new AssignAction(nv.getText(), e);
  }

  public static AbstractRutaAction createFilterTypeAction(List<TypeExpression> types,
          RutaBlock parent) {
    return new FilterTypeAction(types);
  }

  public static AbstractRutaAction createAddRetainTypeAction(List<TypeExpression> types,
          RutaBlock env) {
    return new AddRetainTypeAction(types);
  }

  public static AbstractRutaAction createRemoveRetainTypeAction(List<TypeExpression> types,
          RutaBlock env) {
    return new RemoveRetainTypeAction(types);
  }

  public static AbstractRutaAction createAddFilterTypeAction(List<TypeExpression> types,
          RutaBlock env) {
    return new AddFilterTypeAction(types);
  }

  public static AbstractRutaAction createRemoveFilterTypeAction(List<TypeExpression> types,
          RutaBlock env) {
    return new RemoveFilterTypeAction(types);
  }

  public static AbstractRutaAction createSetFeatureAction(StringExpression f, Object v,
          RutaBlock parent) {
    if (v instanceof NumberExpression) {
      return new SetFeatureAction(f, (NumberExpression) v);
    } else if (v instanceof BooleanExpression) {
      return new SetFeatureAction(f, (BooleanExpression) v);
    } else if (v instanceof StringExpression) {
      return new SetFeatureAction(f, (StringExpression) v);
    }
    return null;
  }

  public static AbstractRutaAction createUnmarkAction(TypeExpression f,
          List<NumberExpression> list, BooleanExpression b, RutaBlock env) {
    return new UnmarkAction(f, list, b);
  }

  public static AbstractRutaAction createUnmarkAllAction(TypeExpression f, TypeListExpression list,
          RutaBlock env) {
    return new UnmarkAllAction(f, list);
  }

  public static AbstractRutaAction createComposedAction(List<AbstractRutaAction> actions,
          RutaBlock env) {
    return new ComposedAction(actions);
  }

  public static AbstractRutaAction createActionVariable(Token id) {
    return new VariableAction(id.getText());
  }

  public static AbstractRutaAction createTransferAction(TypeExpression f, RutaBlock env) {
    return new TransferAction(f);
  }

  public static AbstractRutaAction createTrieAction(WordListExpression list,
          Map<StringExpression, TypeExpression> map, BooleanExpression ignoreCase,
          NumberExpression ignoreLength, BooleanExpression edit, NumberExpression distance,
          StringExpression ignoreChar, RutaBlock parent) {
    return new TrieAction(list, map, ignoreCase, ignoreLength, edit, distance, ignoreChar);
  }

  public static AbstractRutaAction createExecAction(String ns, TypeListExpression tl, RutaBlock env) {
    return new ExecAction(ns, tl);
  }

  public static AbstractRutaAction createMarkTableAction(TypeExpression structure,
          NumberExpression index, WordTableExpression table,
          Map<StringExpression, NumberExpression> map, BooleanExpression ignoreCase,
          NumberExpression ignoreLength, StringExpression ignoreChar,
          NumberExpression maxIgnoreChar, RutaBlock env) {
    return new MarkTableAction(structure, index, table, map, ignoreCase, ignoreLength, ignoreChar,
            maxIgnoreChar);
  }

  public static AbstractRutaAction createMergeAction(BooleanExpression union, Token target,
          List<ListExpression> list, RutaBlock env) {
    return new MergeAction(union, target == null ? null : target.getText(), list);
  }

  public static AbstractRutaAction createGetAction(ListExpression<RutaExpression> f, Token var,
          StringExpression op, RutaBlock env) {
    return new GetAction(f, var == null ? null : var.getText(), op);
  }

  public static AbstractRutaAction createRemoveAction(Token var, List<RutaExpression> list,
          RutaBlock env) {
    return new RemoveAction(var == null ? null : var.getText(), list);
  }

  public static AbstractRutaAction createAddAction(Token var, List<RutaExpression> list,
          RutaBlock env) {
    return new AddAction(var == null ? null : var.getText(), list);
  }

  public static AbstractRutaAction createGetListAction(Token var, StringExpression op, RutaBlock env) {
    return new GetListAction(var == null ? null : var.getText(), op);
  }

  public static AbstractRutaAction createRemoveDuplicateAction(Token var, RutaBlock env) {
    return new RemoveDuplicateAction(var == null ? null : var.getText());
  }

  public static AbstractRutaAction createGetFeatureAction(StringExpression f, Token var,
          RutaBlock env) {
    return new GetFeatureAction(f, var == null ? null : var.getText());
  }

  public static AbstractRutaAction createMatchedTextAction(Token var, List<NumberExpression> list,
          RutaBlock env) {
    return new MatchedTextAction(var == null ? null : var.getText(), list);
  }

  public static AbstractRutaAction createClearAction(Token var, RutaBlock env) {
    return new ClearAction(var == null ? null : var.getText());
  }

  public static AbstractRutaAction createShiftAction(TypeExpression type,
          List<NumberExpression> list, RutaBlock env) {
    return new ShiftAction(type, list);
  }

  public static AbstractRutaAction createDynamicAnchoringAction(BooleanExpression active,
          NumberExpression penalty, NumberExpression factor, RutaBlock env) {
    return new DynamicAnchoringAction(active, penalty, factor);
  }

  public static AbstractRutaAction createTrimAction(List<TypeExpression> types,
          TypeListExpression typeList, RutaBlock env) {
    return new TrimAction(types, typeList);
  }

  public static AbstractRutaAction createAction(FeatureMatchExpression fae) {
    return new ImplicitFeatureAction(fae);
  }

  public static AbstractRutaAction createAction(TypeExpression te) {
    return new ImplicitMarkAction(te);
  }

}
