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
   
# Apache UIMA Ruta (TM) v3.2.0

Apache UIMA Ruta&#8482; is a rule-based script language supported by Eclipse-based tooling.
The language is designed to enable rapid development of text processing applications within Apache UIMA&#8482;. 
A special focus lies on the intuitive and flexible domain specific language for defining 
patterns of annotations. The Eclipse-based tooling for Ruta, called the Ruta Workbench,
was created to support the user and to facilitate every step when writing Ruta rules. Both the 
Ruta rule language and the Ruta Workbench integrate smoothly with Apache UIMA.

This is a feature and bugfix release.

## Notable changes in this release

* [UIMA-6411] - Avoid creation of `RutaBasics` for bad annotations
* [UIMA-6406] - Removing an annotation inside a `BLOCK` only takes effect outside the block
* [UIMA-6408] - No type check of features in `TRANSFER`
* [UIMA-6409] - Possible endless wildcard lookahead in combination with subtokens
* [UIMA-6414] - Missing match for optional after sidestep out of composed
* [UIMA-6404] - `@` with quantifier ignores matches
* [UIMA-6405] - Local variable not captured properly in a wildcard matching condition.
* [UIMA-6461] - Wrong argument to `contains()`
* [UIMA-6399] - `RutaPatternCache` prevents `CPEEngine` from terminating
* [UIMA-6383] - TRIE - Wordlist entry not annotated
* [UIMA-6394] - Label assignment in alternative match causes problems

A [full list of issues](https://issues.apache.org/jira/issues/?jql=project%20%3D%20UIMA%20AND%20fixVersion%20%3D%203.2.0ruta) addressed in this release can be found on issue tracker.

Please use the [mailing lists](https://uima.apache.org/mail-lists.html) for feedback and the [issue tracker](https://issues.apache.org/jira/browse/uima) to report bugs.


## Supported Platforms

UIMA Ruta 3.2.0 should be used in combination with

- Java 1.8 or higher
- UIMA Java SDK 3.3.0 or higher
- uimaFIT 3.3.0 or higher
- Spring Framework 5.3.20 or higher
