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

package org.apache.uima.ruta.string.bool;

import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;
import org.junit.Assert;
import org.junit.Test;

public class VerbalizerTest {

  @Test
  public void test() {
    ContainsBooleanFunction f = new ContainsBooleanFunction(new SimpleStringExpression("abc"),
            new SimpleStringExpression("def"));

    BooleanOperationsExtension extension = new BooleanOperationsExtension();
    String verbalize = extension.verbalize(f, new RutaVerbalizer());

    Assert.assertEquals("contains(\"abc\",\"def\")", verbalize);
  }
}
