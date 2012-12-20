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

package org.apache.uima.textmarker;

import org.apache.uima.textmarker.action.ClearTest;
import org.apache.uima.textmarker.action.CreateTest;
import org.apache.uima.textmarker.action.FillTest;
import org.apache.uima.textmarker.action.FilterTypeTest;
import org.apache.uima.textmarker.action.GatherTest;
import org.apache.uima.textmarker.action.GetFeatureTest;
import org.apache.uima.textmarker.action.GetListTest;
import org.apache.uima.textmarker.action.GetTest;
import org.apache.uima.textmarker.action.MarkFastTest;
import org.apache.uima.textmarker.action.MarkLastTest;
import org.apache.uima.textmarker.action.MarkOnceTest;
import org.apache.uima.textmarker.action.MarkScoreTest;
import org.apache.uima.textmarker.action.MarkTableTest;
import org.apache.uima.textmarker.action.MarkTest;
import org.apache.uima.textmarker.action.MatchedTextTest;
import org.apache.uima.textmarker.action.MergeTest;
import org.apache.uima.textmarker.action.RemoveDuplicateTest;
import org.apache.uima.textmarker.action.RemoveTest;
import org.apache.uima.textmarker.action.ReplaceTest;
import org.apache.uima.textmarker.action.RetainTypeTest;
import org.apache.uima.textmarker.action.SetFeatureTest;
import org.apache.uima.textmarker.action.ShiftTest;
import org.apache.uima.textmarker.action.TransferTest;
import org.apache.uima.textmarker.action.TrieTest;
import org.apache.uima.textmarker.action.TrimTest;
import org.apache.uima.textmarker.action.UnmarkAllTest;
import org.apache.uima.textmarker.action.UnmarkTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClearTest.class, CreateTest.class, FillTest.class, FilterTypeTest.class,
    GatherTest.class, GetFeatureTest.class, GetListTest.class, GetTest.class, MarkFastTest.class,
    MarkLastTest.class, MarkOnceTest.class, MarkScoreTest.class, MarkTableTest.class,
    MarkTest.class, MatchedTextTest.class, MergeTest.class, RemoveDuplicateTest.class,
    RemoveTest.class, ReplaceTest.class, RetainTypeTest.class, SetFeatureTest.class,
    ShiftTest.class, TransferTest.class, TrieTest.class, TrimTest.class, UnmarkAllTest.class,
    UnmarkTest.class, })
public class AllActionsTest {

}
