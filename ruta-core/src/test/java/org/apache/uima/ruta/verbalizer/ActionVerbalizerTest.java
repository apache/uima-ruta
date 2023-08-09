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

package org.apache.uima.ruta.verbalizer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.action.AddAction;
import org.apache.uima.ruta.action.AssignAction;
import org.apache.uima.ruta.action.CallAction;
import org.apache.uima.ruta.action.ClearAction;
import org.apache.uima.ruta.action.ColorAction;
import org.apache.uima.ruta.action.ConfigureAction;
import org.apache.uima.ruta.action.CreateAction;
import org.apache.uima.ruta.action.DelAction;
import org.apache.uima.ruta.action.DynamicAnchoringAction;
import org.apache.uima.ruta.action.ExecAction;
import org.apache.uima.ruta.action.FillAction;
import org.apache.uima.ruta.action.FilterTypeAction;
import org.apache.uima.ruta.action.GatherAction;
import org.apache.uima.ruta.action.GetAction;
import org.apache.uima.ruta.action.GetFeatureAction;
import org.apache.uima.ruta.action.GetListAction;
import org.apache.uima.ruta.action.LogAction;
import org.apache.uima.ruta.action.MarkAction;
import org.apache.uima.ruta.action.MarkFastAction;
import org.apache.uima.ruta.action.MarkLastAction;
import org.apache.uima.ruta.action.MarkOnceAction;
import org.apache.uima.ruta.action.MarkTableAction;
import org.apache.uima.ruta.action.MatchedTextAction;
import org.apache.uima.ruta.action.MergeAction;
import org.apache.uima.ruta.action.RemoveDuplicateAction;
import org.apache.uima.ruta.action.ReplaceAction;
import org.apache.uima.ruta.action.RetainTypeAction;
import org.apache.uima.ruta.action.SetFeatureAction;
import org.apache.uima.ruta.action.ShiftAction;
import org.apache.uima.ruta.action.TransferAction;
import org.apache.uima.ruta.action.TrieAction;
import org.apache.uima.ruta.action.UnmarkAction;
import org.apache.uima.ruta.action.UnmarkAllAction;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.NumberVariableExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordListExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordTableExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringListExpression;
import org.apache.uima.ruta.expression.type.AbstractTypeListExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.type.TypeVariableExpression;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.junit.jupiter.api.Test;

public class ActionVerbalizerTest {

  @SuppressWarnings("unchecked")
  @Test
  public void test() {
    RutaVerbalizer v = new RutaVerbalizer();
    List<ITypeExpression> typeExprList = new ArrayList<ITypeExpression>();
    List<IStringExpression> stringExprList = new ArrayList<IStringExpression>();
    List<IRutaExpression> exprList = new ArrayList<IRutaExpression>();
    List<INumberExpression> indexes = new ArrayList<INumberExpression>();
    String var = "anyVar";
    ITypeExpression typeExpr1 = new SimpleTypeExpression("Type1");
    ITypeExpression typeExpr2 = new TypeVariableExpression("typeVar");
    typeExprList.add(typeExpr1);
    typeExprList.add(typeExpr2);
    INumberExpression numExpr1 = new SimpleNumberExpression(4);
    INumberExpression numExpr2 = new NumberVariableExpression("numVar");
    IBooleanExpression boolExpr1 = new SimpleBooleanExpression(true);
    AbstractStringExpression stringExpr = new SimpleStringExpression("string");
    stringExprList.add(stringExpr);
    exprList.add(typeExpr1);
    WordTableExpression wordTableExpr = new ReferenceWordTableExpression(var);
    WordListExpression wordListExpr = new ReferenceWordListExpression(var);
    AbstractTypeListExpression typeListExpr = new SimpleTypeListExpression(typeExprList);
    AbstractStringListExpression stringListExpr = new SimpleStringListExpression(stringExprList);
    Map<IStringExpression, IRutaExpression> stringExprMap = new HashMap<IStringExpression, IRutaExpression>();
    Map<IStringExpression, INumberExpression> stringExprNumExprMap = new HashMap<IStringExpression, INumberExpression>();
    Map<IStringExpression, IRutaExpression> stringExprTypeExprMap = new HashMap<IStringExpression, IRutaExpression>();
    @SuppressWarnings("rawtypes")
    ListExpression listExpr = new SimpleTypeListExpression(typeExprList);
    @SuppressWarnings("rawtypes")
    List<ListExpression> listExprList = new ArrayList<ListExpression>();
    listExprList.add(listExpr);
    stringExprMap.put(stringExpr, stringExpr);
    stringExprNumExprMap.put(stringExpr, numExpr1);
    stringExprTypeExprMap.put(stringExpr, typeExpr1);
    indexes.add(numExpr1);
    indexes.add(numExpr2);
    AbstractRutaAction a = null;
    String s = null;

    // ADD
    a = new AddAction(var, exprList);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("ADD(anyVar, Type1)");

    // ASSIGN
    a = new AssignAction(var, stringExpr);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("ASSIGN(anyVar, \"string\")");

    // CALL
    a = new CallAction(var);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("CALL(anyVar)");

    // CLEAR
    a = new ClearAction(var);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("CLEAR(anyVar)");

    // COLOR
    a = new ColorAction(typeExpr1, stringExpr, stringExpr, boolExpr1);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("COLOR(Type1, \"string\", \"string\", true)");

    // CONFIGURE
    a = new ConfigureAction(var, stringExprMap);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("CONFIGURE(anyVar, \"string\" = \"string\")");

    // CREATE
    a = new CreateAction(typeExpr1, stringExprMap, indexes);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("CREATE(Type1, 4, numVar, \"string\" = \"string\")");

    // DEL
    a = new DelAction();
    s = v.verbalize(a);
    assertThat(s).isEqualTo("DEL");

    // DYNAMICANCHORING
    a = new DynamicAnchoringAction(boolExpr1, numExpr1, numExpr2);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("DYNAMICANCHORING(true, 4, numVar)");

    // EXEC
    a = new ExecAction(var, typeListExpr, stringExpr);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("EXEC(\"string\", anyVar, {Type1, typeVar})");

    // FILL
    a = new FillAction(typeExpr1, stringExprMap);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("FILL(Type1, \"string\" = \"string\")");

    // FILTERTYPE
    a = new FilterTypeAction(typeExprList);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("FILTERTYPE(Type1, typeVar)");

    // GATHER
    a = new GatherAction(typeExpr1, stringExprMap, indexes);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("GATHER(Type1, 4, numVar, \"string\" = \"string\")");

    // GET
    a = new GetAction(listExpr, var, stringExpr);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("GET({Type1, typeVar}, anyVar, \"string\")");

    // GETFEATURE
    a = new GetFeatureAction(stringExpr, var);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("GETFEATURE(\"string\", anyVar)");

    // GETLIST
    a = new GetListAction(var, stringExpr);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("GETLIST(anyVar, \"string\")");

    // LOG
    a = new LogAction(stringExpr, Level.INFO);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("LOG(\"string\", INFO)");

    // MARK
    a = new MarkAction(typeExpr1, null, indexes);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("MARK(Type1, 4, numVar)");

    // MARKFAST
    a = new MarkFastAction(typeExpr1, stringListExpr, boolExpr1, numExpr1, boolExpr1);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("MARKFAST(Type1, {\"string\"}, true, 4, true)");

    // MARKLAST
    a = new MarkLastAction(typeExpr1);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("MARKLAST(Type1)");

    // MARKONCE
    a = new MarkOnceAction(typeExpr1, null, indexes);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("MARKONCE(Type1, 4, numVar)");

    // MARKSCORE
    a = new MarkAction(typeExpr1, numExpr1, indexes);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("MARKSCORE(4, Type1, 4, numVar)");

    // MARKTABLE
    a = new MarkTableAction(typeExpr1, numExpr1, wordTableExpr, stringExprNumExprMap, boolExpr1,
            numExpr1, stringExpr, numExpr1);
    s = v.verbalize(a);
    assertThat(s)
            .isEqualTo("MARKTABLE(Type1, 4, anyVar, \"string\" = 4, , true, 4, \"string\", 4)");

    // MATCHEDTEXT
    a = new MatchedTextAction(var, indexes);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("MATCHEDTEXT(anyVar, 4, numVar)");

    a = new MatchedTextAction(var, null);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("MATCHEDTEXT(anyVar)");

    // MERGE
    a = new MergeAction(boolExpr1, var, listExprList);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("MERGE(true, anyVar, {Type1, typeVar})");

    // REMOVEDUPLICATE
    a = new RemoveDuplicateAction(var);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("REMOVEDUPLICATE(anyVar)");

    // REPLACE
    a = new ReplaceAction(stringExpr);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("REPLACE(\"string\")");

    // RETAINTYPE
    a = new RetainTypeAction(typeExprList);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("RETAINTYPE(Type1, typeVar)");

    // SETFEATURE
    a = new SetFeatureAction(stringExpr, typeExpr1);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("SETFEATURE(\"string\", Type1)");

    // SHIFT
    a = new ShiftAction(typeExpr1, indexes, null);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("SHIFT(Type1, 4, numVar)");

    // TRANSFER
    a = new TransferAction(typeExpr1);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("TRANSFER(Type1)");

    // TRIE
    a = new TrieAction(wordListExpr, stringExprTypeExprMap, boolExpr1, numExpr1, boolExpr1,
            numExpr1, stringExpr);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("TRIE(\"string\" = Type1, anyVar, true, 4, true, 4, \"string\")");

    // UNMARK
    a = new UnmarkAction(typeExpr1, indexes, boolExpr1);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("UNMARK(Type1, 4, numVar, true)");

    // UNMARKALL
    a = new UnmarkAllAction(typeExpr1, typeListExpr);
    s = v.verbalize(a);
    assertThat(s).isEqualTo("UNMARKALL(Type1, {Type1, typeVar})");

  }
}
