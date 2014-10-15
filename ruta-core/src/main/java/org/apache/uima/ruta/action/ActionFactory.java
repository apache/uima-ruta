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
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class ActionFactory {

  private ActionFactory() {
  }

  public static AbstractRutaAction createColorAction(TypeExpression typeExpr,
          IStringExpression bgcolor, IStringExpression fgcolor, IBooleanExpression selected,
          RutaBlock parent) {
    return new ColorAction(typeExpr, bgcolor, fgcolor, selected);
  }

  public static AbstractRutaAction createDelAction(RutaBlock parent) {
    return new DelAction();
  }

  public static AbstractRutaAction createMarkFastAction(TypeExpression type,
          WordListExpression list, IBooleanExpression ignore, INumberExpression ignoreLength,
          IBooleanExpression ignoreWS, RutaBlock parent) {
    return new MarkFastAction(type, list, ignore, ignoreLength, ignoreWS);
  }

  public static AbstractRutaAction createMarkFastAction(TypeExpression type,
          StringListExpression list, IBooleanExpression ignore, INumberExpression ignoreLength,
          IBooleanExpression ignoreWS, RutaBlock env) {
    return new MarkFastAction(type, list, ignore, ignoreLength, ignoreWS);
  }

  public static AbstractRutaAction createMarkLastAction(TypeExpression type, RutaBlock parent) {
    return new MarkLastAction(type);
  }

  public static AbstractRutaAction createRetainTypeAction(List<TypeExpression> types,
          RutaBlock parent) {
    return new RetainTypeAction(types);
  }

  public static AbstractRutaAction createLogAction(IStringExpression expr, Token log,
          RutaBlock parent) {
    String logString = log == null ? "INFO" : log.getText();
    Level level = Level.parse(logString.toUpperCase());
    return new LogAction(expr, level);
  }

  public static AbstractRutaAction createMarkAction(INumberExpression score, TypeExpression type,
          List<INumberExpression> list, RutaBlock parent) {
    return new MarkAction(type, score, list);
  }

  public static AbstractRutaAction createMarkOnceAction(INumberExpression score,
          TypeExpression type, List<INumberExpression> list, RutaBlock env) {
    return new MarkOnceAction(type, score, list);
  }

  public static AbstractRutaAction createReplaceAction(IStringExpression lit, RutaBlock parent) {
    return new ReplaceAction(lit);
  }

  public static AbstractRutaAction createCreateAction(TypeExpression typeExpr,
          Map<IStringExpression, IRutaExpression> map, List<INumberExpression> indexes,
          RutaBlock parent) {
    return new CreateAction(typeExpr, map, indexes);
  }

  public static AbstractRutaAction createGatherAction(TypeExpression typeExpr,
          Map<IStringExpression, IRutaExpression> map, List<INumberExpression> indexes,
          RutaBlock parent) {
    return new GatherAction(typeExpr, map, indexes);
  }

  public static AbstractRutaAction createFillAction(TypeExpression type,
          Map<IStringExpression, IRutaExpression> map, RutaBlock parent) {
    return new FillAction(type, map);
  }

  public static AbstractRutaAction createCallAction(String ns, RutaBlock parent) {
    return new CallAction(ns);
  }

  public static AbstractRutaAction createConfigureAction(String ns,
          Map<IStringExpression, IRutaExpression> map, RutaBlock env) {
    return new ConfigureAction(ns, map);
  }

  public static AbstractRutaAction createAssignAction(Token nv, IRutaExpression e, RutaBlock parent) {
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

  public static AbstractRutaAction createSetFeatureAction(IStringExpression f, IRutaExpression v,
          RutaBlock parent) {
    return new SetFeatureAction(f, v);
  }

  public static AbstractRutaAction createUnmarkAction(TypeExpression f,
          List<INumberExpression> list, IBooleanExpression b, RutaBlock env) {
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
          Map<IStringExpression, IRutaExpression> map, IBooleanExpression ignoreCase,
          INumberExpression ignoreLength, IBooleanExpression edit, INumberExpression distance,
          IStringExpression ignoreChar, RutaBlock parent) {
    return new TrieAction(list, map, ignoreCase, ignoreLength, edit, distance, ignoreChar);
  }

  public static AbstractRutaAction createExecAction(String ns, TypeListExpression tl,
          IStringExpression view, RutaBlock env) {
    return new ExecAction(ns, tl, view);
  }

  public static AbstractRutaAction createMarkTableAction(TypeExpression structure,
          INumberExpression index, WordTableExpression table,
          Map<IStringExpression, INumberExpression> map, IBooleanExpression ignoreCase,
          INumberExpression ignoreLength, IStringExpression ignoreChar,
          INumberExpression maxIgnoreChar, RutaBlock env) {
    return new MarkTableAction(structure, index, table, map, ignoreCase, ignoreLength, ignoreChar,
            maxIgnoreChar);
  }

  public static AbstractRutaAction createMergeAction(IBooleanExpression union, Token target,
          List<ListExpression> list, RutaBlock env) {
    return new MergeAction(union, target == null ? null : target.getText(), list);
  }

  public static AbstractRutaAction createGetAction(ListExpression<IRutaExpression> f, Token var,
          IStringExpression op, RutaBlock env) {
    return new GetAction(f, var == null ? null : var.getText(), op);
  }

  public static AbstractRutaAction createRemoveAction(Token var, List<IRutaExpression> list,
          RutaBlock env) {
    return new RemoveAction(var == null ? null : var.getText(), list);
  }

  public static AbstractRutaAction createAddAction(Token var, List<IRutaExpression> list,
          RutaBlock env) {
    return new AddAction(var == null ? null : var.getText(), list);
  }

  public static AbstractRutaAction createGetListAction(Token var, IStringExpression op,
          RutaBlock env) {
    return new GetListAction(var == null ? null : var.getText(), op);
  }

  public static AbstractRutaAction createRemoveDuplicateAction(Token var, RutaBlock env) {
    return new RemoveDuplicateAction(var == null ? null : var.getText());
  }

  public static AbstractRutaAction createGetFeatureAction(IStringExpression f, Token var,
          RutaBlock env) {
    return new GetFeatureAction(f, var == null ? null : var.getText());
  }

  public static AbstractRutaAction createMatchedTextAction(Token var, List<INumberExpression> list,
          RutaBlock env) {
    return new MatchedTextAction(var == null ? null : var.getText(), list);
  }

  public static AbstractRutaAction createClearAction(Token var, RutaBlock env) {
    return new ClearAction(var == null ? null : var.getText());
  }

  public static AbstractRutaAction createShiftAction(TypeExpression type,
          List<INumberExpression> list, RutaBlock env) {
    return new ShiftAction(type, list);
  }

  public static AbstractRutaAction createDynamicAnchoringAction(IBooleanExpression active,
          INumberExpression penalty, INumberExpression factor, RutaBlock env) {
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

  public static AbstractRutaAction createMarkFirstAction(TypeExpression type, RutaBlock env) {
    return new MarkFirstAction(type);
  }

  public static AbstractRutaAction createGreedyAnchoringAction(IBooleanExpression active,
          IBooleanExpression active2, RutaBlock env) {
    return new GreedyAnchoringAction(active, active2);
  }

}
