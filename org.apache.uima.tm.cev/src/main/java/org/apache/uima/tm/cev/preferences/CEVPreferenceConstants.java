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

package org.apache.uima.tm.cev.preferences;

public class CEVPreferenceConstants {

  public static final String P_ANNOTATION_FILTER = "annotationFilter";

  public static final String P_ANNOTATION_REPR = "annotationRpr";

  public static final String P_ANNOTATION_REPR_HTML = "html";

  public static final String P_ANNOTATION_REPR_TEXT = "text";

  public static final String[][] P_ANNOTATION_REPR_VALUES = new String[][] {
      { "&HTML", P_ANNOTATION_REPR_HTML }, { "&Text", P_ANNOTATION_REPR_TEXT } };

  public static final String P_ANNOTATION_BROWSER_TREE_ORDER = "annotationBrowserTreeOrder";

  public static final String P_ANNOTATION_BROWSER_TREE_ORDER_TYPE = "type";

  public static final String P_ANNOTATION_BROWSER_TREE_ORDER_ANNOT = "annot";

  public static final String[][] P_ANNOTATION_BROWSER_TREE_ORDER_VALUE = new String[][] {
      { "&Type Ordered", P_ANNOTATION_BROWSER_TREE_ORDER_TYPE },
      { "&Annotation Ordered", P_ANNOTATION_BROWSER_TREE_ORDER_ANNOT } };

  public static final String P_ANNOTATION_EDITOR_TRIM = "trim";

  public static final String P_AUTO_REFRESH = "autoRefresh";

  public static final String P_SELECT_ONLY = "selectOnly";

}
