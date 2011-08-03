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

package org.apache.uima.tm.dltk.parser.ast;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Martin Toepfer
 * 
 */
public final class TMTypeConstants {
  public static final int TM_TYPE_BITMASK = ~((2 << 17) - 1);

  /**
   * number
   */
  public static final int TM_TYPE_N = 2 << 17;

  /**
   * integer
   */
  public static final int TM_TYPE_I = TM_TYPE_N | 2 << 18;

  /**
   * double
   */
  public static final int TM_TYPE_D = TM_TYPE_N | 2 << 19;

  /**
   * string
   */
  public static final int TM_TYPE_S = 2 << 20;

  /**
   * boolean
   */
  public static final int TM_TYPE_B = 2 << 21;

  /**
   * AnnotationType
   */
  public static final int TM_TYPE_AT = 2 << 22;

  /**
   * Generic
   */
  public static final int TM_TYPE_G = 2 << 23;

  /**
   * condition
   */
  public static final int TM_TYPE_C = 2 << 24;

  /**
   * external condition
   */
  public static final int TM_TYPE_CE = TM_TYPE_C | 2 << 25;

  /**
   * action
   */
  public static final int TM_TYPE_A = 2 << 26;

  /**
   * List
   */
  public static final int TM_TYPE_WL = 2 << 27;

  /**
   * Table
   */
  public static final int TM_TYPE_WT = (2 << 17) + 1;

  public static final int TM_TYPE_BL = (2 << 17) + 2;

  public static final int TM_TYPE_NL = (2 << 17) + 3;

  public static final int TM_TYPE_SL = (2 << 17) + 4;

  public static final int TM_TYPE_TL = (2 << 17) + 5;

  public static final Map<Integer, String> typeStringOfInt;
  static {
    typeStringOfInt = new HashMap<Integer, String>();
    typeStringOfInt.put(TM_TYPE_A, "ACTION");
    typeStringOfInt.put(TM_TYPE_B, "BOOLEAN");
    typeStringOfInt.put(TM_TYPE_C, "CONDITION");
    typeStringOfInt.put(TM_TYPE_D, "DOUBLE");
    typeStringOfInt.put(TM_TYPE_N, "NUMBER");
    typeStringOfInt.put(TM_TYPE_I, "INT");
    typeStringOfInt.put(TM_TYPE_S, "STRING");
    typeStringOfInt.put(TM_TYPE_WL, "WORDLIST");
    typeStringOfInt.put(TM_TYPE_WT, "WORDTABLE");
    typeStringOfInt.put(TM_TYPE_BL, "BOOLEANLIST");
    typeStringOfInt.put(TM_TYPE_NL, "NUMBERLIST");
    typeStringOfInt.put(TM_TYPE_SL, "STRINGLIST");
    typeStringOfInt.put(TM_TYPE_TL, "TYPELIST");
    typeStringOfInt.put(TM_TYPE_AT, "ANNOTATION_TYPE");
    typeStringOfInt.put(TM_TYPE_G, "GENERIC");
  }

  // public static void main(String[] args) {
  // System.out.println(Integer.toBinaryString(Integer.MAX_VALUE & ~((2<<17) - 1)));
  // }
}
