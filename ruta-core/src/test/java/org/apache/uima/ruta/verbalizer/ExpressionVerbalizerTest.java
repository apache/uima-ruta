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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.expression.bool.BooleanNumberExpression;
import org.apache.uima.ruta.expression.bool.BooleanTypeExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.ReferenceBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.list.BooleanListExpression;
import org.apache.uima.ruta.expression.list.NumberListExpression;
import org.apache.uima.ruta.expression.list.ReferenceBooleanListExpression;
import org.apache.uima.ruta.expression.list.ReferenceNumberListExpression;
import org.apache.uima.ruta.expression.list.ReferenceStringListExpression;
import org.apache.uima.ruta.expression.list.ReferenceTypeListExpression;
import org.apache.uima.ruta.expression.list.SimpleBooleanListExpression;
import org.apache.uima.ruta.expression.list.SimpleNumberListExpression;
import org.apache.uima.ruta.expression.list.SimpleStringListExpression;
import org.apache.uima.ruta.expression.list.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.number.ComposedNumberExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.ReferenceNumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.string.AbstractStringExpression;
import org.apache.uima.ruta.expression.string.ComposedStringExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.ReferenceStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.type.ReferenceTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.junit.Test;

public class ExpressionVerbalizerTest {

  @SuppressWarnings("unchecked")
  @Test
  public void test() {
    RutaVerbalizer v = new RutaVerbalizer();
//    List<TypeExpression> typeExprList = new ArrayList<TypeExpression>();
//    List<StringExpression> stringExprList = new ArrayList<StringExpression>();
//    List<RutaExpression> exprList = new ArrayList<RutaExpression>();
//    List<INumberExpression> indexes = new ArrayList<INumberExpression>();

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
//    Map<StringExpression, RutaExpression> stringExprMap = new HashMap<StringExpression, RutaExpression>();
//    Map<StringExpression, INumberExpression> stringExprNumExprMap = new HashMap<StringExpression, INumberExpression>();
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
    
    List<INumberExpression> numExprList1 = new ArrayList<INumberExpression>();
    List<INumberExpression> numExprList2 = new ArrayList<INumberExpression>();
    List<String> opList1 = new ArrayList<String>();
    List<String> opList2 = new ArrayList<String>();
    INumberExpression numExpr1 = new SimpleNumberExpression(4);
    INumberExpression numExpr2 = new ReferenceNumberExpression("numVar");
    INumberExpression numExpr3 = new ReferenceNumberExpression("4.9");
    INumberExpression numExpr4 = new ReferenceNumberExpression("-4");
    numExprList1.add(numExpr1);
    numExprList1.add(numExpr2);
    opList1.add("+");
    opList2.add("*");
    INumberExpression numExpr5 = new ComposedNumberExpression(numExprList1, opList1);
    numExprList2.add(numExpr3);
    numExprList2.add(numExpr5);
    INumberExpression numExpr6 = new ComposedNumberExpression(numExprList2, opList2);
    
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
    
    
    
    IBooleanExpression boolExpr1 = new SimpleBooleanExpression(true);
    IBooleanExpression boolExpr2 = new SimpleBooleanExpression(false);
    IBooleanExpression boolExpr3 = new ReferenceBooleanExpression(var);
    IBooleanExpression boolExpr4 = new BooleanNumberExpression(numExpr1, "==", numExpr2);
    IBooleanExpression boolExpr5 = new BooleanNumberExpression(numExpr1, "!=", numExpr2);
    IBooleanExpression boolExpr6 = new BooleanNumberExpression(numExpr1, "<=", numExpr2);
    IBooleanExpression boolExpr7 = new BooleanNumberExpression(numExpr1, ">=", numExpr2);
    IBooleanExpression boolExpr8 = new BooleanNumberExpression(numExpr1, "<", numExpr2);
    IBooleanExpression boolExpr9 = new BooleanNumberExpression(numExpr1, ">", numExpr2);
    IBooleanExpression boolExpr10 = new BooleanTypeExpression(typeExpr1, "==", typeExpr2);
    IBooleanExpression boolExpr11 = new BooleanTypeExpression(typeExpr1, "!=", typeExpr2);
    
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

    List<IStringExpression> stringExprList = new ArrayList<IStringExpression>();
    AbstractStringExpression stringExpr1 = new SimpleStringExpression("string");
    AbstractStringExpression stringExpr2 = new ReferenceStringExpression(var);
    stringExprList.add(stringExpr1);
    stringExprList.add(stringExpr2);
    AbstractStringExpression stringExpr3 = new ComposedStringExpression(stringExprList);
    
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
    
    List<IBooleanExpression> boolExprList = new ArrayList<IBooleanExpression>();
    boolExprList.add(boolExpr1);
    boolExprList.add(boolExpr3);
    BooleanListExpression ble1 = new SimpleBooleanListExpression(boolExprList);
    BooleanListExpression ble2 = new ReferenceBooleanListExpression(var);
    s = v.verbalize(ble1);
    assertEquals("{true, anyVar}", s);
    s = v.verbalize(ble2);
    assertEquals("anyVar", s);
    
    List<INumberExpression> numExprList = new ArrayList<INumberExpression>();
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
