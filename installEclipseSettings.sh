#/bin/sh
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
# 
#   http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

# Formatter settings
JDT_CORE_PREFS="/Users/rec/uima-code-style-settings/org.eclipse.jdt.core.prefs"

# Save actions
JDT_UI_PREFS="/Users/rec/uima-code-style-settings/org.eclipse.jdt.ui.prefs"

function installPrefs {
  mkdir -p $1/.settings/
  cp -v $JDT_CORE_PREFS $1/.settings/
  cp -v $JDT_UI_PREFS $1/.settings/
}

installPrefs example-projects
installPrefs ruta-basic-type
installPrefs ruta-core
installPrefs ruta-core-ext
installPrefs ruta-docbook
installPrefs ruta-eclipse-feature
installPrefs ruta-eclipse-update-site
installPrefs ruta-ep-addons
installPrefs ruta-ep-caseditor
installPrefs ruta-ep-core-ext
installPrefs ruta-ep-engine
installPrefs ruta-ep-ide
installPrefs ruta-ep-ide-ui
installPrefs ruta-ep-parent
installPrefs ruta-ep-textruler
installPrefs ruta-maven-archetype
installPrefs ruta-maven-plugin
installPrefs ruta-parent
installPrefs ruta-typesystem
