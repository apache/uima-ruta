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

package org.apache.uima.ruta;

import org.apache.uima.ruta.condition.AfterTest;
import org.apache.uima.ruta.condition.AndTest;
import org.apache.uima.ruta.condition.BeforeTest;
import org.apache.uima.ruta.condition.ContainsTest;
import org.apache.uima.ruta.condition.ContextCountTest;
import org.apache.uima.ruta.condition.CountTest;
import org.apache.uima.ruta.condition.CurrentCountTest;
import org.apache.uima.ruta.condition.EndsWithTest;
import org.apache.uima.ruta.condition.FeatureTest;
import org.apache.uima.ruta.condition.IfTest;
import org.apache.uima.ruta.condition.InListTest;
import org.apache.uima.ruta.condition.IsTest;
import org.apache.uima.ruta.condition.LastTest;
import org.apache.uima.ruta.condition.MOfNTest;
import org.apache.uima.ruta.condition.NearTest;
import org.apache.uima.ruta.condition.NotTest;
import org.apache.uima.ruta.condition.OrTest;
import org.apache.uima.ruta.condition.ParseTest;
import org.apache.uima.ruta.condition.PartOfNEQTest;
import org.apache.uima.ruta.condition.PartOfTest;
import org.apache.uima.ruta.condition.PositionTest;
import org.apache.uima.ruta.condition.RegExpTest;
import org.apache.uima.ruta.condition.ScoreTest;
import org.apache.uima.ruta.condition.SizeTest;
import org.apache.uima.ruta.condition.StartsWithTest;
import org.apache.uima.ruta.condition.TotalCountTest;
import org.apache.uima.ruta.condition.VoteTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AfterTest.class, AndTest.class, BeforeTest.class,
  ContainsTest.class, ContextCountTest.class, CountTest.class,
  CurrentCountTest.class, EndsWithTest.class, FeatureTest.class,
  IfTest.class, InListTest.class, IsTest.class,
  LastTest.class, MOfNTest.class, NearTest.class,
  NotTest.class, OrTest.class, ParseTest.class,
  PartOfNEQTest.class, PartOfTest.class, PositionTest.class,
  RegExpTest.class, ScoreTest.class, SizeTest.class,
  StartsWithTest.class, TotalCountTest.class, VoteTest.class })
public class AllConditionsTest {

}
