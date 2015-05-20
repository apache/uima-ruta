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

package org.apache.uima.ruta.testing.preferences;

import org.apache.uima.ruta.addons.RutaAddonsPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;

public class TestingPreferenceConstants {

  public static final String EVALUATOR_FACTORY = "EVALUATOR_FACTORY";

  public static final String DEFAULT_EVALUATOR_FACTORY = "org.apache.uima.ruta.testing.exactCasEvaluator";

  public static final String AUTOMATED_FILE_SYNCRONIZATION = "AUTOMATED_FILE_SYNCRONIZATION";

  public static final String LOAD_OLD_TEST_RESULTS = "LOAD_OLD_TEST_RESULTS";

  public static final String INCLUDE_SUBTYPES = "INCLUDE_SUBTYPES";
  
  public static final String ALL_TYPES = "ALL_TYPES";

  public static final String EXTEND_CLASSPATH = "EXTEND_CLASSPATH";

  public static String[][] EVALUATORS = getEvaluators();

  public static String[][] getEvaluators() {
    IExtension[] evalExtensions = Platform.getExtensionRegistry()
            .getExtensionPoint(RutaAddonsPlugin.PLUGIN_ID, "evaluators").getExtensions();
    int size = 0;
    for (IExtension extension : evalExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        size++;
      }
    }

    String[][] evalFactories = new String[size][2];
    int count = 0;
    for (IExtension extension : evalExtensions) {
      IConfigurationElement[] configurationElements = extension.getConfigurationElements();
      for (IConfigurationElement configurationElement : configurationElements) {
        evalFactories[count][0] = configurationElement.getAttribute("name");
        evalFactories[count][1] = configurationElement.getAttribute("id");
        count++;
      }
    }
    return evalFactories;
  }

}
