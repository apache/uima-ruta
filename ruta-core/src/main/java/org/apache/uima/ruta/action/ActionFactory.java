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
import java.util.Set;
import java.util.logging.Level;

import org.antlr.runtime.Token;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.uima.ruta.TypeUsageInformation;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.expression.AnnotationTypeExpression;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.type.AbstractTypeListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.extensions.RutaParseRuntimeException;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class ActionFactory {

  private TypeUsageInformation typeUsage;

  private RutaVerbalizer verbalizer;
  
  public ActionFactory(TypeUsageInformation typeUsage) {
    super();
    this.typeUsage = typeUsage;
    this.verbalizer = new RutaVerbalizer();
  }

  public ActionFactory() {
    this(null);
  }

  public AbstractRutaAction createColorAction(ITypeExpression typeExpr, IStringExpression bgcolor,
          IStringExpression fgcolor, IBooleanExpression selected, RutaBlock parent) {
    return new ColorAction(typeExpr, bgcolor, fgcolor, selected);
  }

  public AbstractRutaAction createDelAction(RutaBlock parent) {
    return new DelAction();
  }

  public AbstractRutaAction createMarkFastAction(ITypeExpression type, WordListExpression list,
          IBooleanExpression ignore, INumberExpression ignoreLength, IBooleanExpression ignoreWS,
          RutaBlock parent) {
    removeMention(type);
    return new MarkFastAction(type, list, ignore, ignoreLength, ignoreWS);
  }

  public AbstractRutaAction createMarkFastAction(ITypeExpression type,
          AbstractStringListExpression list, IBooleanExpression ignore,
          INumberExpression ignoreLength, IBooleanExpression ignoreWS, RutaBlock env) {
    removeMention(type);
    return new MarkFastAction(type, list, ignore, ignoreLength, ignoreWS);
  }

  public AbstractRutaAction createMarkLastAction(ITypeExpression type, RutaBlock parent) {
    removeMention(type);
    return new MarkLastAction(type);
  }

  public AbstractRutaAction createRetainTypeAction(List<ITypeExpression> types, RutaBlock parent) {
    return new RetainTypeAction(types);
  }

  public AbstractRutaAction createLogAction(IStringExpression expr, Token log, RutaBlock parent) {
    String logString = log == null ? "INFO" : log.getText();
    Level level = Level.parse(logString.toUpperCase());
    return new LogAction(expr, level);
  }

  public AbstractRutaAction createMarkAction(INumberExpression score, ITypeExpression type,
          List<INumberExpression> list, RutaBlock parent) {
    removeMention(type);
    return new MarkAction(type, score, list);
  }

  public AbstractRutaAction createMarkOnceAction(INumberExpression score, ITypeExpression type,
          List<INumberExpression> list, RutaBlock env) {
    return new MarkOnceAction(type, score, list);
  }

  public AbstractRutaAction createReplaceAction(IStringExpression lit, RutaBlock parent) {
    return new ReplaceAction(lit);
  }

  public AbstractRutaAction createCreateAction(ITypeExpression typeExpr,
          Map<IStringExpression, IRutaExpression> map, List<INumberExpression> indexes,
          RutaBlock parent) {
    removeMention(typeExpr);
    return new CreateAction(typeExpr, map, indexes);
  }

  public AbstractRutaAction createGatherAction(ITypeExpression typeExpr,
          Map<IStringExpression, IRutaExpression> map, List<INumberExpression> indexes,
          RutaBlock parent) {
    removeMention(typeExpr);
    return new GatherAction(typeExpr, map, indexes);
  }

  public AbstractRutaAction createFillAction(ITypeExpression type,
          Map<IStringExpression, IRutaExpression> map, RutaBlock parent) {
    return new FillAction(type, map);
  }

  public AbstractRutaAction createCallAction(String ns, RutaBlock parent) {
    return new CallAction(ns);
  }

  public AbstractRutaAction createConfigureAction(String ns,
          Map<IStringExpression, IRutaExpression> map, RutaBlock env) {
    return new ConfigureAction(ns, map);
  }

  public AbstractRutaAction createAssignAction(Token nv, IRutaExpression e, RutaBlock parent) {
    return new AssignAction(nv.getText(), e);
  }

  public AbstractRutaAction createFilterTypeAction(List<ITypeExpression> types, RutaBlock parent) {
    return new FilterTypeAction(types);
  }

  public AbstractRutaAction createAddRetainTypeAction(List<ITypeExpression> types, RutaBlock env) {
    return new AddRetainTypeAction(types);
  }

  public AbstractRutaAction createRemoveRetainTypeAction(List<ITypeExpression> types,
          RutaBlock env) {
    return new RemoveRetainTypeAction(types);
  }

  public AbstractRutaAction createAddFilterTypeAction(List<ITypeExpression> types, RutaBlock env) {
    return new AddFilterTypeAction(types);
  }

  public AbstractRutaAction createRemoveFilterTypeAction(List<ITypeExpression> types,
          RutaBlock env) {
    return new RemoveFilterTypeAction(types);
  }

  public AbstractRutaAction createSetFeatureAction(IStringExpression f, IRutaExpression v,
          RutaBlock parent) {
    return new SetFeatureAction(f, v);
  }

  public AbstractRutaAction createUnmarkAction(ITypeExpression f, List<INumberExpression> list,
          IBooleanExpression b, RutaBlock env) {
    return new UnmarkAction(f, list, b);
  }

  public AbstractRutaAction createUnmarkAction(IRutaExpression a, RutaBlock env) {
    if (a instanceof AnnotationTypeExpression) {
      return new UnmarkAction((AnnotationTypeExpression) a);
    }
    throw new IllegalArgumentException("Expression " + a + " is nto a valid argument for UNMARK.");
  }

  public AbstractRutaAction createUnmarkAllAction(ITypeExpression f,
          AbstractTypeListExpression list, RutaBlock env) {
    return new UnmarkAllAction(f, list);
  }

  public AbstractRutaAction createComposedAction(List<AbstractRutaAction> actions, RutaBlock env) {
    return new ComposedAction(actions);
  }

  public AbstractRutaAction createActionVariable(Token id) {
    return new VariableAction(id.getText());
  }

  public AbstractRutaAction createTransferAction(ITypeExpression f, RutaBlock env) {
    return new TransferAction(f);
  }

  public AbstractRutaAction createTrieAction(WordListExpression list,
          Map<IStringExpression, IRutaExpression> map, IBooleanExpression ignoreCase,
          INumberExpression ignoreLength, IBooleanExpression edit, INumberExpression distance,
          IStringExpression ignoreChar, RutaBlock parent) {
    return new TrieAction(list, map, ignoreCase, ignoreLength, edit, distance, ignoreChar);
  }

  public AbstractRutaAction createExecAction(String ns, AbstractTypeListExpression tl,
          IStringExpression view, RutaBlock env) {
    return new ExecAction(ns, tl, view);
  }

  public AbstractRutaAction createMarkTableAction(ITypeExpression structure,
          INumberExpression index, WordTableExpression table,
          Map<IStringExpression, INumberExpression> map, IBooleanExpression ignoreCase,
          INumberExpression ignoreLength, IStringExpression ignoreChar,
          INumberExpression maxIgnoreChar, RutaBlock env) {
    return new MarkTableAction(structure, index, table, map, ignoreCase, ignoreLength, ignoreChar,
            maxIgnoreChar);
  }

  @SuppressWarnings("rawtypes")
  public AbstractRutaAction createMergeAction(IBooleanExpression union, Token target,
          List<ListExpression> list, RutaBlock env) {
    return new MergeAction(union, target == null ? null : target.getText(), list);
  }

  public AbstractRutaAction createGetAction(ListExpression<IRutaExpression> f, Token var,
          IStringExpression op, RutaBlock env) {
    return new GetAction(f, var == null ? null : var.getText(), op);
  }

  public AbstractRutaAction createRemoveAction(Token var, List<IRutaExpression> list,
          RutaBlock env) {
    return new RemoveAction(var == null ? null : var.getText(), list);
  }

  public AbstractRutaAction createAddAction(Token var, List<IRutaExpression> list, RutaBlock env) {
    return new AddAction(var == null ? null : var.getText(), list);
  }

  public AbstractRutaAction createGetListAction(Token var, IStringExpression op, RutaBlock env) {
    return new GetListAction(var == null ? null : var.getText(), op);
  }

  public AbstractRutaAction createRemoveDuplicateAction(Token var, RutaBlock env) {
    return new RemoveDuplicateAction(var == null ? null : var.getText());
  }

  public AbstractRutaAction createGetFeatureAction(IStringExpression f, Token var, RutaBlock env) {
    return new GetFeatureAction(f, var == null ? null : var.getText());
  }

  public AbstractRutaAction createMatchedTextAction(Token var, List<INumberExpression> list,
          RutaBlock env) {
    return new MatchedTextAction(var == null ? null : var.getText(), list);
  }

  public AbstractRutaAction createClearAction(Token var, RutaBlock env) {
    return new ClearAction(var == null ? null : var.getText());
  }

  public AbstractRutaAction createShiftAction(ITypeExpression type, List<INumberExpression> list,
          IBooleanExpression all, RutaBlock env) {
    return new ShiftAction(type, list, all);
  }

  public AbstractRutaAction createDynamicAnchoringAction(IBooleanExpression active,
          INumberExpression penalty, INumberExpression factor, RutaBlock env) {
    return new DynamicAnchoringAction(active, penalty, factor);
  }

  public AbstractRutaAction createTrimAction(List<ITypeExpression> types,
          AbstractTypeListExpression typeList, RutaBlock env) {
    return new TrimAction(types, typeList);
  }

  public AbstractRutaAction createAction(FeatureMatchExpression fae) {
    return new ImplicitFeatureAction(fae);
  }

  public AbstractRutaAction createAction(ITypeExpression type) {
    removeMention(type);
    return new ImplicitMarkAction(type);
  }

  public AbstractRutaAction createImplicitVariableAssignmentAction(Token var, Token op,
          IRutaExpression arg, RutaBlock env) {
    String varString = var != null ? var.getText() : null;
    String opString = op != null ? op.getText() : "=";
    return new ImplicitVariableAssignmentAction(varString, opString, arg);
  }

  public AbstractRutaAction createMarkFirstAction(ITypeExpression type, RutaBlock env) {
    return new MarkFirstAction(type);
  }

  public AbstractRutaAction createGreedyAnchoringAction(IBooleanExpression active,
          IBooleanExpression active2, RutaBlock env) {
    return new GreedyAnchoringAction(active, active2);
  }

  public AbstractRutaAction createSplitAction(ITypeExpression type, IBooleanExpression complete,
          IBooleanExpression appendToBegin, IBooleanExpression appendToEnd, RutaBlock env) {
    return new SplitAction(type, complete, appendToBegin, appendToEnd);
  }

  public AbstractRutaAction createMacroAction(Token id, List<IRutaExpression> args, RutaBlock env) {
    String name = id.getText();
    Triple<Map<String, String>, List<AbstractRutaAction>, Set<String>> macroActionDefinition = env
            .getEnvironment().getMacroAction(name);
    if (macroActionDefinition == null) {
      return null;
    }
    int argSize = 0;
    if (args != null) {
      argSize = args.size();
    }
    Map<String, String> definition = macroActionDefinition.getLeft();
    List<AbstractRutaAction> actions = macroActionDefinition.getMiddle();
    Set<String> vars = macroActionDefinition.getRight();
    if (definition.size() != argSize) {
      throw new RutaParseRuntimeException("Arguments of macro action '" + name
              + "' do not match its definition: " + definition.values());
    }

    return new MacroAction(name, definition, actions, vars, args);
  }

  private void removeMention(ITypeExpression type) {
    if(typeUsage != null) {
      String verbalize = verbalizer.verbalize(type);
      typeUsage.removeMentionedType(verbalize);
    }
  }
  
}
