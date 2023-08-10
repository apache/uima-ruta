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
package org.apache.uima.ruta.cache;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.UNICODE_CASE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.ruta.FilterManager;
import org.apache.uima.ruta.RutaScriptFactory;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.TypeUsageInformation;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.condition.ConditionFactory;
import org.apache.uima.ruta.condition.RegExpCondition;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.rule.EvaluatedCondition;
import org.apache.uima.ruta.rule.MatchContext;
import org.apache.uima.ruta.rule.RegExpRule;
import org.apache.uima.ruta.seed.TextSeeder;
import org.apache.uima.ruta.type.ANY;
import org.apache.uima.ruta.type.RutaBasic;
import org.apache.uima.ruta.visitor.InferenceCrowd;
import org.junit.jupiter.api.Test;

public class RutaPatternCacheTest {

  @Test
  public void test() throws Exception {

    JCas jCas = JCasFactory.createJCas();
    jCas.setDocumentText("A test.");
    CAS cas = jCas.getCas();

    ConditionFactory conditionFactory = new ConditionFactory();
    RutaScriptFactory scriptFactory = new RutaScriptFactory(null);
    RutaBlock parentBlock = scriptFactory.createRootScriptBlock("root", "uima.ruta");
    InferenceCrowd inferenceCrowd = new InferenceCrowd(Collections.emptyList());
    FilterManager filterManager = new FilterManager(Collections.emptyList(), false, cas);
    Type basicType = cas.getTypeSystem().getType(RutaBasic.class.getName());
    TypeUsageInformation typeUsageInformation = new TypeUsageInformation();

    RutaStream stream = new RutaStream(cas, basicType, filterManager, false, false, false,
            typeUsageInformation, inferenceCrowd);
    stream.setMaxRuleMatches(100);
    stream.setMaxRuleElementMatches(100);

    TextSeeder seeder = new TextSeeder();
    seeder.seed(cas.getDocumentText(), cas);

    AnnotationFS firstAny = JCasUtil.selectByIndex(jCas, ANY.class, 0);
    MatchContext matchContext = new MatchContext(parentBlock);
    matchContext.setAnnotation(firstAny);

    RegExpCondition regexpCondition1 = (RegExpCondition) conditionFactory.createConditionRegExp(
            new SimpleStringExpression("A"), new SimpleBooleanExpression(false), parentBlock);
    RegExpCondition regexpCondition2 = (RegExpCondition) conditionFactory.createConditionRegExp(
            new SimpleStringExpression("A"), new SimpleBooleanExpression(false), parentBlock);
    RegExpRule regexpRule = scriptFactory.createRegExpRule(parentBlock);
    regexpRule.setRegExp(new SimpleStringExpression("A"));
    regexpRule.setTypeMap(new HashMap<>());
    regexpRule.setFeatureAssignments(new HashMap<>());

    RutaPatternCache.clearCache();

    EvaluatedCondition eval1 = regexpCondition1.eval(matchContext, stream, inferenceCrowd);
    EvaluatedCondition eval2 = regexpCondition2.eval(matchContext, stream, inferenceCrowd);
    regexpRule.apply(stream, inferenceCrowd);

    assertThat(eval1.isValue()).isTrue();
    assertThat(eval2.isValue()).isTrue();

    Map<PatternCacheKey, Pattern> cache = RutaPatternCache.getCacheMap();
    assertThat(cache).hasSize(1);
    Entry<PatternCacheKey, Pattern> entry = cache.entrySet().iterator().next();
    assertThat(entry.getKey().getPatternString()).isEqualTo("A");
    assertThat(entry.getKey().getFlags() & (CASE_INSENSITIVE | UNICODE_CASE)).isEqualTo(0);
    assertThat(entry.getKey().getFlags() & (MULTILINE | DOTALL)).isEqualTo(MULTILINE | DOTALL);
  }

}
