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
import java.util.List;

import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.bool.BooleanNumberExpression;
import org.apache.uima.textmarker.expression.bool.BooleanTypeExpression;
import org.apache.uima.textmarker.expression.bool.ReferenceBooleanExpression;
import org.apache.uima.textmarker.expression.bool.SimpleBooleanExpression;
import org.apache.uima.textmarker.expression.list.BooleanListExpression;
import org.apache.uima.textmarker.expression.list.NumberListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceBooleanListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceNumberListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceStringListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceTypeListExpression;
import org.apache.uima.textmarker.expression.list.SimpleBooleanListExpression;
import org.apache.uima.textmarker.expression.list.SimpleNumberListExpression;
import org.apache.uima.textmarker.expression.list.SimpleStringListExpression;
import org.apache.uima.textmarker.expression.list.SimpleTypeListExpression;
import org.apache.uima.textmarker.expression.list.StringListExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.ComposedNumberExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.ReferenceNumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.string.ComposedStringExpression;
import org.apache.uima.textmarker.expression.string.ReferenceStringExpression;
import org.apache.uima.textmarker.expression.string.SimpleStringExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.ReferenceTypeExpression;
import org.apache.uima.textmarker.expression.type.SimpleTypeExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.verbalize.TextMarkerVerbalizer;
import org.junit.Test;

public class ExpressionVerbalizerTest {

  @SuppressWarnings("unchecked")
  @Test
  public void test() {
    TextMarkerVerbalizer v = new TextMarkerVerbalizer();
//    List<TypeExpression> typeExprList = new ArrayList<TypeExpression>();
//    List<StringExpression> stringExprList = new ArrayList<StringExpression>();
//    List<TextMarkerExpression> exprList = new ArrayList<TextMarkerExpression>();
//    List<NumberExpression> indexes = new ArrayList<NumberExpression>();

//    
////    typeExprList.add(typeExpr1);
////    typeExprList.add(typeExpr2);
//    
//
//    StringExpression stringExpr = new SimpleStringExpression("string");
//    stringExprList.add(stringExpr);
////    exprList.add(typeExpr1);
//    WordTableExpression wordTableExpr = new ReferenceWordTableExpression(var);
//    WordListExpression wordListExpr = new ReferenceWordListExpression(var);
//    TypeListExpression typeListExpr = new SimpleTypeListExpression(typeExprList);
//    StringListExpression stringListExpr = new SimpleStringListExpression(stringExprList);
//    Map<StringExpression, TextMarkerExpression> stringExprMap = new HashMap<StringExpression, TextMarkerExpression>();
//    Map<StringExpression, NumberExpression> stringExprNumExprMap = new HashMap<StringExpression, NumberExpression>();
//    Map<StringExpression, TypeExpression> stringExprTypeExprMap = new HashMap<StringExpression, TypeExpression>();
//    @SuppressWarnings("rawtypes")
//    ListExpression listExpr = new SimpleTypeListExpression(typeExprList);
//    @SuppressWarnings("rawtypes")
//    List<ListExpression> listExprList = new ArrayList<ListExpression>();
//    listExprList.add(listExpr);
//    stringExprMap.put(stringExpr, stringExpr);
////    stringExprNumExprMap.put(stringExpr, numExpr1);
////    stringExprTypeExprMap.put(stringExpr, typeExpr1);
////    indexes.add(numExpr1);
////    indexes.add(numExpr2);

    String s = null;
  String var = "anyVar";
    TypeExpression typeExpr1 = new SimpleTypeExpression("Type1");
    TypeExpression typeExpr2 = new ReferenceTypeExpression("typeVar");
    
    List<NumberExpression> numExprList1 = new ArrayList<NumberExpression>();
    List<NumberExpression> numExprList2 = new ArrayList<NumberExpression>();
    List<String> opList1 = new ArrayList<String>();
    List<String> opList2 = new ArrayList<String>();
    NumberExpression numExpr1 = new SimpleNumberExpression(4);
    NumberExpression numExpr2 = new ReferenceNumberExpression("numVar");
    NumberExpression numExpr3 = new ReferenceNumberExpression("4.9");
    NumberExpression numExpr4 = new ReferenceNumberExpression("-4");
    numExprList1.add(numExpr1);
    numExprList1.add(numExpr2);
    opList1.add("+");
    opList2.add("*");
    NumberExpression numExpr5 = new ComposedNumberExpression(numExprList1, opList1);
    numExprList2.add(numExpr3);
    numExprList2.add(numExpr5);
    NumberExpression numExpr6 = new ComposedNumberExpression(numExprList2, opList2);
    
    s = v.verbalize(numExpr1);
    assertEquals("4", s);
    s = v.verbalize(numExpr2);
    assertEquals("numVar", s);
    s = v.verbalize(numExpr3);
    assertEquals("4.9", s);
    s = v.verbalize(numExpr4);
    assertEquals("-4", s);
    s = v.verbalize(numExpr5);
    assertEquals("4 + numVar", s);
    s = v.verbalize(numExpr6);
    assertEquals("4.9 * 4 + numVar", s);
    
    
    
    BooleanExpression boolExpr1 = new SimpleBooleanExpression(true);
    BooleanExpression boolExpr2 = new SimpleBooleanExpression(false);
    BooleanExpression boolExpr3 = new ReferenceBooleanExpression(var);
    BooleanExpression boolExpr4 = new BooleanNumberExpression(numExpr1, "==", numExpr2);
    BooleanExpression boolExpr5 = new BooleanNumberExpression(numExpr1, "!=", numExpr2);
    BooleanExpression boolExpr6 = new BooleanNumberExpression(numExpr1, "<=", numExpr2);
    BooleanExpression boolExpr7 = new BooleanNumberExpression(numExpr1, ">=", numExpr2);
    BooleanExpression boolExpr8 = new BooleanNumberExpression(numExpr1, "<", numExpr2);
    BooleanExpression boolExpr9 = new BooleanNumberExpression(numExpr1, ">", numExpr2);
    BooleanExpression boolExpr10 = new BooleanTypeExpression(typeExpr1, "==", typeExpr2);
    BooleanExpression boolExpr11 = new BooleanTypeExpression(typeExpr1, "!=", typeExpr2);
    
    s = v.verbalize(boolExpr1);
    assertEquals("true", s);
    s = v.verbalize(boolExpr2);
    assertEquals("false", s);
    s = v.verbalize(boolExpr3);
    assertEquals("anyVar", s);
    s = v.verbalize(boolExpr4);
    assertEquals("4 == numVar", s);
    s = v.verbalize(boolExpr5);
    assertEquals("4 != numVar", s);
    s = v.verbalize(boolExpr6);
    assertEquals("4 <= numVar", s);
    s = v.verbalize(boolExpr7);
    assertEquals("4 >= numVar", s);
    s = v.verbalize(boolExpr8);
    assertEquals("4 < numVar", s);
    s = v.verbalize(boolExpr9);
    assertEquals("4 > numVar", s);
    s = v.verbalize(boolExpr10);
    assertEquals("Type1 == typeVar", s);
    s = v.verbalize(boolExpr11);
    assertEquals("Type1 != typeVar", s);

    List<StringExpression> stringExprList = new ArrayList<StringExpression>();
    StringExpression stringExpr1 = new SimpleStringExpression("string");
    StringExpression stringExpr2 = new ReferenceStringExpression(var);
    stringExprList.add(stringExpr1);
    stringExprList.add(stringExpr2);
    StringExpression stringExpr3 = new ComposedStringExpression(stringExprList);
    
    s = v.verbalize(stringExpr1);
    assertEquals("\"string\"", s);
    s = v.verbalize(stringExpr2);
    assertEquals("anyVar", s);
    s = v.verbalize(stringExpr3);
    assertEquals("\"string\" + anyVar"  , s);
    
    
    StringListExpression sle1 = new SimpleStringListExpression(stringExprList);
    StringListExpression sle2 = new ReferenceStringListExpression(var);
    s = v.verbalize(sle1);
    assertEquals("{\"string\", anyVar}", s);
    s = v.verbalize(sle2);
    assertEquals("anyVar", s);
    
    List<BooleanExpression> boolExprList = new ArrayList<BooleanExpression>();
    boolExprList.add(boolExpr1);
    boolExprList.add(boolExpr3);
    BooleanListExpression ble1 = new SimpleBooleanListExpression(boolExprList);
    BooleanListExpression ble2 = new ReferenceBooleanListExpression(var);
    s = v.verbalize(ble1);
    assertEquals("{true, anyVar}", s);
    s = v.verbalize(ble2);
    assertEquals("anyVar", s);
    
    List<NumberExpression> numExprList = new ArrayList<NumberExpression>();
    numExprList.add(numExpr1);
    numExprList.add(numExpr3);
    NumberListExpression nle1 = new SimpleNumberListExpression(numExprList);
    NumberListExpression nle2 = new ReferenceNumberListExpression(var);
    s = v.verbalize(nle1);
    assertEquals("{4, 4.9}", s);
    s = v.verbalize(nle2);
    assertEquals("anyVar", s);
    
    List<TypeExpression> typeExprList = new ArrayList<TypeExpression>();
    typeExprList.add(typeExpr1);
    typeExprList.add(typeExpr2);
    TypeListExpression tle1 = new SimpleTypeListExpression(typeExprList);
    TypeListExpression tle2 = new ReferenceTypeListExpression(var);
    s = v.verbalize(tle1);
    assertEquals("{Type1, typeVar}", s);
    s = v.verbalize(tle2);
    assertEquals("anyVar", s);
    
  }
}
