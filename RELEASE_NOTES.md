<!--
***************************************************************
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
***************************************************************
-->
   
# Apache UIMA Ruta (TM) v3.5.1

Apache UIMA Ruta™ is a rule-based script language supported by Eclipse-based tooling.
The language is designed to enable rapid development of text processing applications within Apache UIMA&#8482;. 
A special focus lies on the intuitive and flexible domain specific language for defining 
patterns of annotations. The Eclipse-based tooling for Ruta, called the Ruta Workbench,
was created to support the user and to facilitate every step when writing Ruta rules. Both the 
Ruta rule language and the Ruta Workbench integrate smoothly with Apache UIMA.

This is a feature and bugfix release.

## What's Changed

* ⭐️ Issue #189: Avoid type system scanning when initializing uimaFIT annotators from a Ruta script
* ⭐️ Issue #191: Add option to build without Eclipse plugins
* 🦟 Issue #193: Build path problems after Maven project update


**Full Changelog**: https://github.com/apache/uima-ruta/compare/rel/ruta-3.5.0...rel/3.5.1

Please use the [mailing lists](https://uima.apache.org/mail-lists.html) for feedback and the [issue tracker](https://github.com/apache/uima-ruta/issues) to report bugs.

## Supported Platforms

UIMA Ruta 3.5.1 should be used in combination with

- Java 17 or higher
- UIMA Java SDK / uimaFIT 3.6.1 or higher
- Spring Framework 6.2.11 or higher
