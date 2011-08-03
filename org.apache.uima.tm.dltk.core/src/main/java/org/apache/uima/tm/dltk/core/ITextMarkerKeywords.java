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

package org.apache.uima.tm.dltk.core;

public interface ITextMarkerKeywords {

  public static final int ALL = 0;

  public static final int DECLARATION = 1;

  public static final int BASIC = 2;

  public static final int CONDITION = 4;

  public static final int ACTION = 5;

  public static final int BOOLEANFUNCTION = 6;

  public static final int NUMBERFUNCTION = 7;

  public static final int STRINGFUNCTION = 8;

  public static final int TYPEFUNCTION = 9;

  public static final int THEN = 10;

  public static final int START_INDEX = 1;

  public static final int END_INDEX = 11;

  String[] getKeywords(int type);
}
