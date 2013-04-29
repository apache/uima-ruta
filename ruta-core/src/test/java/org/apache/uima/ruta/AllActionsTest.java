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

import org.apache.uima.ruta.action.AddFilterTypeTest;
import org.apache.uima.ruta.action.AddRetainTypeTest;
import org.apache.uima.ruta.action.ClearTest;
import org.apache.uima.ruta.action.CreateTest;
import org.apache.uima.ruta.action.FillTest;
import org.apache.uima.ruta.action.FilterTypeTest;
import org.apache.uima.ruta.action.GatherTest;
import org.apache.uima.ruta.action.GetFeatureTest;
import org.apache.uima.ruta.action.GetListTest;
import org.apache.uima.ruta.action.GetTest;
import org.apache.uima.ruta.action.MarkFastTest;
import org.apache.uima.ruta.action.MarkLastTest;
import org.apache.uima.ruta.action.MarkOnceTest;
import org.apache.uima.ruta.action.MarkScoreTest;
import org.apache.uima.ruta.action.MarkTableTest;
import org.apache.uima.ruta.action.MarkTest;
import org.apache.uima.ruta.action.MatchedTextTest;
import org.apache.uima.ruta.action.MergeTest;
import org.apache.uima.ruta.action.RemoveDuplicateTest;
import org.apache.uima.ruta.action.RemoveFilterTypeTest;
import org.apache.uima.ruta.action.RemoveRetainTypeTest;
import org.apache.uima.ruta.action.RemoveTest;
import org.apache.uima.ruta.action.ReplaceTest;
import org.apache.uima.ruta.action.RetainTypeTest;
import org.apache.uima.ruta.action.SetFeatureTest;
import org.apache.uima.ruta.action.ShiftTest;
import org.apache.uima.ruta.action.ShiftTest2;
import org.apache.uima.ruta.action.TransferTest;
import org.apache.uima.ruta.action.TrieTest;
import org.apache.uima.ruta.action.TrimTest;
import org.apache.uima.ruta.action.UnmarkAllTest;
import org.apache.uima.ruta.action.UnmarkTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddFilterTypeTest.class, AddRetainTypeTest.class, ClearTest.class,
    CreateTest.class, FillTest.class, FilterTypeTest.class, GatherTest.class, GetFeatureTest.class,
    GetListTest.class, GetTest.class, MarkFastTest.class, MarkLastTest.class, MarkOnceTest.class,
    MarkScoreTest.class, MarkTableTest.class, MarkTest.class, MatchedTextTest.class,
    MergeTest.class, RemoveDuplicateTest.class, RemoveFilterTypeTest.class,
    RemoveRetainTypeTest.class, RemoveTest.class, ReplaceTest.class, RetainTypeTest.class,
    SetFeatureTest.class, ShiftTest.class, ShiftTest2.class, TransferTest.class, TrieTest.class,
    TrimTest.class, UnmarkAllTest.class, UnmarkTest.class })
public class AllActionsTest {

}
