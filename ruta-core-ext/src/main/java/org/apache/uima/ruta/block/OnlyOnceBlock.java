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

package org.apache.uima.ruta.block;

import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaScriptBlock;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.ScriptApply;
import org.apache.uima.ruta.visitor.InferenceCrowd;

/**
 * A block construct in UIMA Ruta, in which each rule matches only once and additional positions are
 * skipped.
 * 
 */
public class OnlyOnceBlock extends RutaScriptBlock {

  public OnlyOnceBlock(RutaBlock parent, String defaultNamespace) {
    super(null, null, null, parent, defaultNamespace);
  }

  @Override
  public ScriptApply apply(RutaStream stream, InferenceCrowd crowd) {
    boolean oldSetting = stream.isOnlyOnce();
    stream.setOnlyOnce(true);
    ScriptApply result = super.apply(stream, crowd);
    stream.setOnlyOnce(oldSetting);
    return result;
  }

}
