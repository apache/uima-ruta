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

package org.apache.uima.ruta.ide.ui;

import org.apache.uima.ruta.ide.RutaIdeUIPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.dltk.ui.PluginImagesHelper;
import org.eclipse.jface.resource.ImageDescriptor;

public class RutaImages {
  private static final PluginImagesHelper helper = new PluginImagesHelper(RutaIdeUIPlugin
          .getDefault().getBundle(), new Path("/icons"));

  public static final ImageDescriptor PROJECT_DECARATOR = helper
          .createUnManaged("", "ruta_ovr.gif");

  public static final ImageDescriptor DESC_WIZBAN_PROJECT_CREATION = helper.createUnManaged("",
          "projectcreate_wiz.png");

  public static final ImageDescriptor DESC_WIZBAN_FILE_CREATION = helper.createUnManaged("",
          "filecreate_wiz.png");
}
