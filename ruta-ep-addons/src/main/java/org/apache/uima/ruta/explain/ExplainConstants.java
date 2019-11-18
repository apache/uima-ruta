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

package org.apache.uima.ruta.explain;

import org.apache.uima.ruta.engine.RutaEngine;

public class ExplainConstants {

  public static final String INLINED_AS_CONDITION = "inlinedAsCondition";

  public static final String INLINED_AS_ACTION = "inlinedAsAction";

  public static final String INLINED_ACTION_BLOCK = "inlinedActionBlock";

  public static final String INLINED_CONDITION_BLOCK_MATCHED = "inlinedConditionBlockMatched";

  public static final String INLINED_CONDITION_BLOCK_FAILED = "inlinedConditionBlockFailed";

  public static final String BASIC_TYPE = RutaEngine.BASIC_TYPE;

  public static final String SCRIPT_APPLY_TYPE = "org.apache.uima.ruta.type.DebugScriptApply";

  public static final String BLOCK_APPLY_TYPE = "org.apache.uima.ruta.type.DebugBlockApply";

  public static final String RULE_APPLY_TYPE = "org.apache.uima.ruta.type.DebugRuleApply";

  public static final String RULE_MATCH_TYPE = "org.apache.uima.ruta.type.DebugRuleMatch";

  public static final String MATCHED_RULE_MATCH_TYPE = "org.apache.uima.ruta.type.DebugMatchedRuleMatch";

  public static final String FAILED_RULE_MATCH_TYPE = "org.apache.uima.ruta.type.DebugFailedRuleMatch";

  public static final String RULE_ELEMENT_MATCH_TYPE = "org.apache.uima.ruta.type.DebugRuleElementMatch";

  public static final String RULE_ELEMENT_MATCHES_TYPE = "org.apache.uima.ruta.type.DebugRuleElementMatches";

  public static final String EVAL_CONDITION_TYPE = "org.apache.uima.ruta.type.DebugEvaluatedCondition";

  public static final String BASE_CONDITION = "baseCondition";

  public static final String MATCHES = "matches";

  public static final String ELEMENTS = "elements";

  public static final String MATCHED = "matched";

  public static final String DELEGATES = "delegates";

  public static final String RULES = "rules";

  public static final String INNER_APPLY = "innerApply";

  public static final String CONDITIONS = "conditions";

  public static final String VALUE = "value";

  public static final String APPLIED = "applied";

  public static final String TRIED = "tried";

  public static final String ELEMENT = "element";

  public static final String RULE_ANCHOR = "ruleAnchor";

  public static final String TIME = "time";

  public static final String TIME_STAMP = "timestamp";

  public static final String ID = "id";

  public static final String SCRIPT = "script";
}
