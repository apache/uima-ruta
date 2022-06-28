[![Maven Central](https://img.shields.io/maven-central/v/org.apache.uima/ruta-core?style=for-the-badge)](https://search.maven.org/search?q=g:org.apache.uima%20a:ruta*)

[![Build Status](https://ci-builds.apache.org/buildStatus/icon?job=UIMA%2Fuima-ruta%2Fmain&subject=main%20build)](https://ci-builds.apache.org/job/UIMA/job/uima-ruta/job/main/) [![Build Status](https://ci-builds.apache.org/buildStatus/icon?job=UIMA%2Fuima-ruta%2Fmain-v2&subject=main-v2%20build)](https://ci-builds.apache.org/job/UIMA/job/uima-ruta/job/main-v2/)

What is Apache UIMA Ruta?
-------------------------

Apache UIMA Ruta™ is a rule-based script language supported by Eclipse-based tooling. The language is designed to enable rapid development of text processing applications within Apache UIMA™. A special focus lies on the intuitive and flexible domain specific language for defining patterns of annotations. Writing rules for information extraction or other text processing applications is a tedious process. The Eclipse-based tooling for UIMA Ruta, called the Apache UIMA Ruta Workbench, was created to support the user and to facilitate every step when writing UIMA Ruta rules. Both the Ruta rule language and the UIMA Ruta Workbench integrate smoothly with Apache UIMA.


Rule Language
-------------

The UIMA Ruta language is an imperative rule language extended with scripting elements. A rule defines a
pattern of annotations with additional conditions. If this pattern applies, then the actions of the rule are performed 
on the matched annotations. A rule is composed of a sequence of rule elements and a rule element usually consists of four parts: 
A matching condition, an optional quantifier, a list of conditions and a list of actions.
The matching condition is typically a type of an annotation by which the rule element matches on the covered text of one of those annotations.
The quantifier specifies, whether it is necessary that the rule element successfully matches and how often the rule element may match.
The list of conditions specifies additional constraints that the matched text or annotations need to fulfill. The list of actions defines
the consequences of the rule and often creates new annotations or modifies existing annotations.


The following example rule consists of three rule elements. The first one (`ANY...`) matches on every token, which has a covered text that occurs in a word lists, named `MonthsList`.
The second rule element (`PERIOD?`) is optional and does not need to be fulfilled, which is indicated by the quantifier `?`. The last rule element (`NUM...`) matches
on numbers that fulfill the regular expression `REGEXP(".{2,4}")` and are therefore at least two characters to a maximum of four characters long.
If this rule successfully matches on a text passage, then its three actions are executed: An annotation of the type `Month` is created for the first rule element,
an annotation of the type `Year` is created for the last rule element and an annotation of the type `Date` 
is created for the span of all three rule elements. If the word list contains the correct entries, then this rule matches on strings like 
`Dec. 2004`, `July 85` or `11.2008` and creates the corresponding annotations.
  
~~~~
(ANY{INLIST(MonthsList) -> Month} PERIOD? @NUM{REGEXP(".{2,4}") -> Year}){-> Date};
~~~~

Here is a short overview of additional features of the rule language:

* Expressions and variables
* Import and execution of external components
* Flexible matching with filtering
* Modularization in different files or blocks
* Control structures, e.g., for windowing
* Score-based extraction
* Modification
* Html support 
* Dictionaries
* Extensible language definition


Workbench
---------

The UIMA Ruta Workbench was created to facilitate all steps in creating Analysis Engines based on the UIMA Ruta language.
Here is a short overview of included features: 

**Editing support:** The full-featured editor for the UIMA Ruta language provides syntax and semantic highlighting, 
syntax checking, context-sensitive auto-completion, template-based completion, open declaration and more.

**Rule Explanation:** Each step in the matching process can be explained: This includes how often a rule was applied, 
which condition was not fulfilled, or by which rule a specific annotation was created. Additionally, profile information 
about the runtime performance can be accessed.

**Automatic Validation:** UIMA Ruta scripts can automatically validated against a set of annotated documents (F1 score, test-driven development) 
and even against unlabeled documents (constraint-driven evaluation). 

**Rule learning:** The supervised learning algorithms of the included TextRuler framework are able to induce rules 
and, therefore, enable semi-automatic development of rule-based components.

**Query:** Rules can be used as query statements in order to investigate annotated documents.


The UIMA Ruta Workbench can be installed via Eclipse update sites:

* for UIMA 2: <a href="https://downloads.apache.org/uima/eclipse-update-site/">https://downloads.apache.org/uima/eclipse-update-site/</a>
* for UIMA 3: <a href="https://downloads.apache.org/uima/eclipse-update-site-v3/">https://downloads.apache.org/uima/eclipse-update-site-v3/</a>


Building from the Source Distribution
-------------------------------------

We use Maven 3.0 and Java 8 or later for building; download this if needed, 
and set the environment variable MAVEN_OPTS to -Xmx800m.

Then do the build by going into the UIMA Ruta directory, and issuing the command
   
   mvn clean install
   
This builds everything except the ...source-release.zip file. If you want that,
change the command to 

   mvn clean install -Papache-release
   
For more details, please see https://uima.apache.org/building-uima.html   


How to Get Involved
-------------------

The Apache UIMA project really needs and appreciates any contributions, including documentation 
help, source code and feedback. If you are interested in contributing, please visit 
[http://uima.apache.org/get-involved.html](http://uima.apache.org/get-involved.html).


How to Report Issues
--------------------

The Apache UIMA project uses JIRA for issue tracking. Please report any issues you find at 
[our issue tracker](http://issues.apache.org/jira/browse/uima).


Useful tips
-----------

This product was originally released as Apache UIMA TextMarker. The UIMA Ruta Workbench provides
a command for updating old projects. Please right-click on a project and select "UIMA Ruta -> Update Project". 

The UIMA Ruta analysis engine requires type priorities for the correct execution of rules. 
If a CAS is created using the CasCreationUtils, please provide the type priorities, e.g., by:

    URL tpUrl = this.getClass().getResource("/org/apache/uima/ruta/engine/TypePriorities.xml");
    TypePriorities typePriorities = UIMAFramework.getXMLParser().parseTypePriorities(
        new XMLInputSource(tpUrl));
    CAS cas = CasCreationUtils.createCas(descriptor, typePriorities, new FsIndexDescription[0]);

Using the `jcasgen-maven-plugin` may cause problems if it creates duplicate classes for the 
internal UIMA Ruta types (overwriting the implementation of RutaBasic). Depending on the location 
of the type system descriptors, the plugin should be configured to be limited on the project, 
or the UIMA Ruta type system descriptors should explicitly be excluded:

    <configuration>
      <typeSystemExcludes>
        <typeSystemExclude>/**/BasicTypeSystem.xml</typeSystemExclude>
        <typeSystemExclude>/**/InternalTypeSystem.xml</typeSystemExclude>
      </typeSystemExcludes>
    </configuration>


Useful links
------------

* [Apache UIMA](https://uima.apache.org)
* [Apache UIMA Ruta Documentation](https://uima.apache.org/d/ruta-current/tools.ruta.book.html)
* [Averbis Ruta Training material](https://github.com/averbis/ruta-training) (external)


Reference
---------

If you use UIMA Ruta to support academic research, then please consider citing the following paper as appropriate:

~~~~
@article{NLE:10051335,
  author = {Kluegl, Peter and Toepfer, Martin and Beck, Philip-Daniel and Fette, Georg and Puppe, Frank},
  title = {UIMA Ruta: Rapid development of rule-based information extraction applications},
  journal = {Natural Language Engineering},
  volume = {22},
  issue = {01},
  month = {1},
  year = {2016},
  issn = {1469-8110},
  pages = {1--40},
  numpages = {40},
  doi = {10.1017/S1351324914000114},
  URL = {https://journals.cambridge.org/article_S1351324914000114},
}
~~~~