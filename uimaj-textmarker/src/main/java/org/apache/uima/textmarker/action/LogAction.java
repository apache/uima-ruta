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

package org.apache.uima.textmarker.action;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.uima.textmarker.TextMarkerStream;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleMatch;
import org.apache.uima.textmarker.visitor.InferenceCrowd;

public class LogAction extends AbstractTextMarkerAction {

  public static final String LOGGER_NAME = Logger.GLOBAL_LOGGER_NAME;

  private final StringExpression text;

  private final Level level;

  public LogAction(StringExpression text, Level level) {
    super();
    this.text = text;
    this.level = level == null ? Level.INFO : level;
  }

  @Override
  public void execute(RuleMatch match, RuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    String msg = text.getStringValue(element.getParent());
    Logger.getLogger(LOGGER_NAME).log(level, msg);
  }

  public StringExpression getText() {
    return text;
  }

  public Level getLevel() {
    return level;
  }

}
