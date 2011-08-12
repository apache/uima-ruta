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

package org.apache.uima.textmarker.ide.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.ide.ui.TextMarkerPartitions;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;


public class TextMarkerPartitionScanner extends RuleBasedPartitionScanner {

  public TextMarkerPartitionScanner() {
    super();

    IToken string = new Token(TextMarkerPartitions.TM_STRING);
    IToken comment = new Token(TextMarkerPartitions.TM_COMMENT);

    List/* < IPredicateRule > */rules = new ArrayList/* <IPredicateRule> */();

    rules.add(new EndOfLineRule("//", comment));

    rules.add(new MultiLineRule("\"\"\"", "\"\"\"", string, '\\'));

    rules.add(new MultiLineRule("\'\'\'", "\'\'\'", string, '\\'));

    rules.add(new MultiLineRule("\'", "\'", string, '\\'));

    rules.add(new MultiLineRule("\"", "\"", string, '\\'));

    IPredicateRule[] result = new IPredicateRule[rules.size()];
    rules.toArray(result);
    setPredicateRules(result);
  }
}
