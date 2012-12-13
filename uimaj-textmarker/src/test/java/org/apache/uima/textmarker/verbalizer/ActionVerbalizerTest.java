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

package org.apache.uima.textmarker.verbalizer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

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
import org.apache.uima.textmarker.action.FillAction;
import org.apache.uima.textmarker.action.FilterTypeAction;
import org.apache.uima.textmarker.action.GatherAction;
import org.apache.uima.textmarker.action.GetAction;
import org.apache.uima.textmarker.action.GetFeatureAction;
import org.apache.uima.textmarker.action.GetListAction;
import org.apache.uima.textmarker.action.LogAction;
import org.apache.uima.textmarker.action.MarkAction;
import org.apache.uima.textmarker.action.MarkFastAction;
import org.apache.uima.textmarker.action.MarkLastAction;
import org.apache.uima.textmarker.action.MarkOnceAction;
import org.apache.uima.textmarker.action.MarkTableAction;
import org.apache.uima.textmarker.action.MatchedTextAction;
import org.apache.uima.textmarker.action.MergeAction;
import org.apache.uima.textmarker.action.RemoveAction;
import org.apache.uima.textmarker.action.RemoveDuplicateAction;
import org.apache.uima.textmarker.action.ReplaceAction;
import org.apache.uima.textmarker.action.RetainTypeAction;
import org.apache.uima.textmarker.action.SetFeatureAction;
import org.apache.uima.textmarker.action.ShiftAction;
import org.apache.uima.textmarker.action.TransferAction;
import org.apache.uima.textmarker.action.TrieAction;
import org.apache.uima.textmarker.action.UnmarkAction;
import org.apache.uima.textmarker.action.UnmarkAllAction;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.bool.SimpleBooleanExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.expression.list.SimpleStringListExpression;
import org.apache.uima.textmarker.expression.list.SimpleTypeListExpression;
import org.apache.uima.textmarker.expression.list.StringListExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.ReferenceNumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.resource.LiteralWordTableExpression;
import org.apache.uima.textmarker.expression.resource.ReferenceWordListExpression;
import org.apache.uima.textmarker.expression.resource.ReferenceWordTableExpression;
import org.apache.uima.textmarker.expression.resource.WordListExpression;
import org.apache.uima.textmarker.expression.resource.WordTableExpression;
import org.apache.uima.textmarker.expression.string.SimpleStringExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.ReferenceTypeExpression;
import org.apache.uima.textmarker.expression.type.SimpleTypeExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.resource.CSVTable;
import org.apache.uima.textmarker.verbalize.TextMarkerVerbalizer;
import org.junit.Test;

public class ActionVerbalizerTest {

  @SuppressWarnings("unchecked")
  @Test
  public void test() {
    TextMarkerVerbalizer v = new TextMarkerVerbalizer();
    List<TypeExpression> typeExprList = new ArrayList<TypeExpression>();
    List<StringExpression> stringExprList = new ArrayList<StringExpression>();
    List<TextMarkerExpression> exprList = new ArrayList<TextMarkerExpression>();
    List<NumberExpression> indexes = new ArrayList<NumberExpression>();
    String var = "anyVar";
    TypeExpression typeExpr1 = new SimpleTypeExpression("Type1");
    TypeExpression typeExpr2 = new ReferenceTypeExpression("typeVar");
    typeExprList.add(typeExpr1);
    typeExprList.add(typeExpr2);
    NumberExpression numExpr1 = new SimpleNumberExpression(4);
    NumberExpression numExpr2 = new ReferenceNumberExpression("numVar");
    BooleanExpression boolExpr1 = new SimpleBooleanExpression(true);
    StringExpression stringExpr = new SimpleStringExpression("string");
    stringExprList.add(stringExpr);
    exprList.add(typeExpr1);
    WordTableExpression wordTableExpr = new ReferenceWordTableExpression(var);
    WordListExpression wordListExpr = new ReferenceWordListExpression(var);
    TypeListExpression typeListExpr = new SimpleTypeListExpression(typeExprList);
    StringListExpression stringListExpr = new SimpleStringListExpression(stringExprList);
    Map<StringExpression, TextMarkerExpression> stringExprMap = new HashMap<StringExpression, TextMarkerExpression>();
    Map<StringExpression, NumberExpression> stringExprNumExprMap = new HashMap<StringExpression, NumberExpression>();
    Map<StringExpression, TypeExpression> stringExprTypeExprMap = new HashMap<StringExpression, TypeExpression>();
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
    AbstractTextMarkerAction a = null;
    String s = null;

    // ADD
    a = new AddAction(var, exprList);
    s = v.verbalize(a);
    assertEquals("ADD(anyVar, Type1)", s);

    // ASSIGN
    a = new AssignAction(var, stringExpr);
    s = v.verbalize(a);
    assertEquals("ASSIGN(anyVar, \"string\")", s);

    // CALL
    a = new CallAction(var);
    s = v.verbalize(a);
    assertEquals("CALL(anyVar)", s);

    // CLEAR
    a = new ClearAction(var);
    s = v.verbalize(a);
    assertEquals("CLEAR(anyVar)", s);

    // COLOR
    a = new ColorAction(typeExpr1, stringExpr, stringExpr, boolExpr1);
    s = v.verbalize(a);
    assertEquals("COLOR(Type1, \"string\", \"string\", true)", s);

    // CONFIGURE
    a = new ConfigureAction(var, stringExprMap);
    s = v.verbalize(a);
    assertEquals("CONFIGURE(anyVar, \"string\" = \"string\")", s);

    // CREATE
    a = new CreateAction(typeExpr1, stringExprMap, indexes);
    s = v.verbalize(a);
    assertEquals("CREATE(Type1, 4, numVar, \"string\" = \"string\")", s);

    // DEL
    a = new DelAction();
    s = v.verbalize(a);
    assertEquals("DEL", s);

    // DYNAMICANCHORING
    a = new DynamicAnchoringAction(boolExpr1, numExpr1, numExpr2);
    s = v.verbalize(a);
    assertEquals("DYNAMICANCHORING(true, 4, numVar)", s);

    // EXEC
    a = new ExecAction(var, typeListExpr);
    s = v.verbalize(a);
    assertEquals("EXEC(anyVar, {Type1, typeVar})", s);

    // FILL
    a = new FillAction(typeExpr1, stringExprMap);
    s = v.verbalize(a);
    assertEquals("FILL(Type1, \"string\" = \"string\")", s);

    // FILTERTYPE
    a = new FilterTypeAction(typeExprList);
    s = v.verbalize(a);
    assertEquals("FILTERTYPE(Type1, typeVar)", s);

    // GATHER
    a = new GatherAction(typeExpr1, stringExprMap, indexes);
    s = v.verbalize(a);
    assertEquals("GATHER(Type1, 4, numVar, \"string\" = \"string\")", s);

    // GET
    a = new GetAction(listExpr, var, stringExpr);
    s = v.verbalize(a);
    assertEquals("GET({Type1, typeVar}, anyVar, \"string\")", s);

    // GETFEATURE
    a = new GetFeatureAction(stringExpr, var);
    s = v.verbalize(a);
    assertEquals("GETFEATURE(\"string\", anyVar)", s);

    // GETLIST
    a = new GetListAction(var, stringExpr);
    s = v.verbalize(a);
    assertEquals("GETLIST(anyVar, \"string\")", s);

    // LOG
    a = new LogAction(stringExpr, Level.INFO);
    s = v.verbalize(a);
    assertEquals("LOG(\"string\", INFO)", s);

    // MARK
    a = new MarkAction(typeExpr1, null, indexes);
    s = v.verbalize(a);
    assertEquals("MARK(Type1, 4, numVar)", s);

    // MARKFAST
    a = new MarkFastAction(typeExpr1, stringListExpr, boolExpr1, numExpr1, boolExpr1);
    s = v.verbalize(a);
    assertEquals("MARKFAST(Type1, {\"string\"}, true, 4, true)", s);

    // MARKLAST
    a = new MarkLastAction(typeExpr1);
    s = v.verbalize(a);
    assertEquals("MARKLAST(Type1)", s);

    // MARKONCE
    a = new MarkOnceAction(typeExpr1, null, indexes);
    s = v.verbalize(a);
    assertEquals("MARKONCE(Type1, 4, numVar)", s);

    // MARKSCORE
    a = new MarkAction(typeExpr1, numExpr1, indexes);
    s = v.verbalize(a);
    assertEquals("MARKSCORE(4, Type1, 4, numVar)", s);

    // MARKTABLE
    a = new MarkTableAction(typeExpr1, numExpr1, wordTableExpr, stringExprNumExprMap, boolExpr1,
            numExpr1, stringExpr, numExpr1);
    s = v.verbalize(a);
    assertEquals("MARKTABLE(Type1, 4, anyVar, \"string\" = 4, , true, 4, \"string\", 4)", s);

    // MATCHEDTEXT
    a = new MatchedTextAction(var, indexes);
    s = v.verbalize(a);
    assertEquals("MATCHEDTEXT(anyVar, 4, numVar)", s);

    // MERGE
    a = new MergeAction(boolExpr1, var, listExprList);
    s = v.verbalize(a);
    assertEquals("MERGE(true, anyVar, {Type1, typeVar})", s);

    // REMOVEDUPLICATE
    a = new RemoveDuplicateAction(var);
    s = v.verbalize(a);
    assertEquals("REMOVEDUPLICATE(anyVar)", s);

    // REPLACE
    a = new ReplaceAction(stringExpr);
    s = v.verbalize(a);
    assertEquals("REPLACE(\"string\")", s);

    // RETAINTYPE
    a = new RetainTypeAction(typeExprList);
    s = v.verbalize(a);
    assertEquals("RETAINTYPE(Type1, typeVar)", s);

    // SETFEATURE
    a = new SetFeatureAction(stringExpr, typeExpr1);
    s = v.verbalize(a);
    assertEquals("SETFEATURE(\"string\", Type1)", s);

    // SHIFT
    a = new ShiftAction(typeExpr1, indexes);
    s = v.verbalize(a);
    assertEquals("SHIFT(Type1, 4, numVar)", s);

    // TRANSFER
    a = new TransferAction(typeExpr1);
    s = v.verbalize(a);
    assertEquals("TRANSFER(Type1)", s);

    // TRIE
    a = new TrieAction(wordListExpr, stringExprTypeExprMap, boolExpr1, numExpr1, boolExpr1,
            numExpr1, stringExpr);
    s = v.verbalize(a);
    assertEquals("TRIE(\"string\" = Type1, anyVar, true, 4, true, 4, \"string\")", s);

    // UNMARK
    a = new UnmarkAction(typeExpr1, indexes, boolExpr1);
    s = v.verbalize(a);
    assertEquals("UNMARK(Type1, 4, numVar, true)", s);

    // UNMARKALL
    a = new UnmarkAllAction(typeExpr1, typeListExpr);
    s = v.verbalize(a);
    assertEquals("UNMARKALL(Type1, {Type1, typeVar})", s);

  }
}
