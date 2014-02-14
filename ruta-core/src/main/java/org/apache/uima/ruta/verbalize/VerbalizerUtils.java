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

package org.apache.uima.ruta.verbalize;

import java.util.Iterator;
import java.util.List;

import org.apache.uima.cas.Type;
import org.apache.uima.ruta.expression.IRutaExpression;

public class VerbalizerUtils {

  private RutaVerbalizer verbalizer;

  public VerbalizerUtils(RutaVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalizeList(List<?> list) {
    StringBuilder result = new StringBuilder();
    Iterator<?> it = list.iterator();
    while (it.hasNext()) {
      Object obj = it.next();
      result.append(obj.toString());
      if (it.hasNext()) {
        result.append(", ");
      }
    }
    return result.toString();
  }

  public String verbalizeTypeList(List<Type> list) {
    StringBuilder result = new StringBuilder();
    Iterator<Type> it = list.iterator();
    while (it.hasNext()) {
      Type type = it.next();
      result.append(verbalizer.verbalizeType(type));
      if (it.hasNext()) {
        result.append(", ");
      }
    }
    return result.toString();
  }

  public String verbalizeExpressionList(List<? extends IRutaExpression> list) {
    StringBuilder result = new StringBuilder();
    Iterator<? extends IRutaExpression> it = list.iterator();
    while (it.hasNext()) {
      IRutaExpression e = it.next();
      result.append(verbalizer.verbalize(e));
      if (it.hasNext()) {
        result.append(", ");
      }
    }
    return result.toString();
  }

}
