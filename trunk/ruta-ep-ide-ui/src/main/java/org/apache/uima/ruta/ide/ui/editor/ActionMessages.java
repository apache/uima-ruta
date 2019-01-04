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

package org.apache.uima.ruta.ide.ui.editor;

import org.eclipse.osgi.util.NLS;

public final class ActionMessages extends NLS {

  private static final String BUNDLE_NAME = "org.apache.uima.ruta.ide.ui.editor.ActionMessages";//$NON-NLS-1$

  private ActionMessages() {
    // Do not instantiate
  }

  public static String MemberFilterActionGroup_hide_variables_label;

  public static String MemberFilterActionGroup_hide_variables_tooltip;

  public static String MemberFilterActionGroup_hide_variables_description;

  public static String MemberFilterActionGroup_hide_functions_label;

  public static String MemberFilterActionGroup_hide_functions_tooltip;

  public static String MemberFilterActionGroup_hide_functions_description;

  public static String MemberFilterActionGroup_hide_classes_label;

  public static String MemberFilterActionGroup_hide_classes_tooltip;

  public static String MemberFilterActionGroup_hide_classes_description;

  static {
    NLS.initializeMessages(BUNDLE_NAME, ActionMessages.class);
  }

}
