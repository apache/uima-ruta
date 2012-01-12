lexer grammar TextMarkerLexer;
options {
  language = Java;
}


tokens {
  DocComment;
  Annotation;
  ListIdentifier;
}

@lexer::header {
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

  package org.apache.uima.textmarker.parser;
}

@lexer::members {
  public int implicitLineJoiningLevel = 0;
  public int startPos=-1;
  public void emitErrorMessage(String msg) {
  }
}


TRIE
  : 'TRIE'
  ;

CONTAINS
  : 'CONTAINS'
  ;

DECLARE
  : 'DECLARE'
  ;

WORDLIST
  : 'WORDLIST'
  ;

WORDTABLE
  : 'WORDTABLE'
  ;

AND
  : 'AND'
  ;

CONTEXTCOUNT
  : 'CONTEXTCOUNT'
  ;

COUNT
  : 'COUNT'
  ;

TOTALCOUNT
  : 'TOTALCOUNT'
  ;

CURRENTCOUNT
  : 'CURRENTCOUNT'
  ;

INLIST
  : 'INLIST'
  ;


LAST
  : 'LAST'
  ;

MOFN
  : 'MOFN'
  ;

NEAR
  : 'NEAR'
  ;

OR
  : 'OR'
  ;

PARTOF
  : 'PARTOF'
  ;
  
PARTOFNEQ
  : 'PARTOFNEQ'
  ;

POSITION
  : 'POSITION'
  ;

REGEXP
  : 'REGEXP'
  ;

SCORE
  : 'SCORE'
  ;

VOTE
  : 'VOTE'
  ;

IF
  : 'IF'
  ;

FEATURE
  : 'FEATURE'
  ;

PARSE
  : 'PARSE'
  ;

CREATE
  : 'CREATE'
  ;

GATHER
  : 'GATHER'
  ;

FILL
  : 'FILL'
  ;

ATTRIBUTE
  : 'ATTRIBUTE'
  ;

COLOR
  : 'COLOR'
  ;

DEL
  : 'DEL'
  ;

LOG
  : 'LOG'
  ;

MARK
  : 'MARK'
  ;

MARKSCORE
  : 'MARKSCORE'
  ;

MARKONCE
  : 'MARKONCE'
  ;

MARKFAST
  : 'MARKFAST'
  ;
  
MARKTABLE
  : 'MARKTABLE'
  ;
  
MARKLAST
  : 'MARKLAST'
  ;

REPLACE
  : 'REPLACE'
  ;

RETAINTYPE
  : 'RETAINTYPE'
  ;

FILTERTYPE
  : 'FILTERTYPE'
  ;

CALL
  : 'CALL'
  ;


EXEC
  : 'EXEC'
  ;

CONFIGURE
  : 'CONFIGURE'
  ;

ASSIGN
  : 'ASSIGN'
  ;

SETFEATURE
  : 'SETFEATURE'
  ;

GETFEATURE
  : 'GETFEATURE'
  ;

UNMARK
  : 'UNMARK'
  ;

UNMARKALL
  : 'UNMARKALL'
  ;

TRANSFER
  : 'TRANSFER'
  ;


EXPAND  
  : 'EXPAND'   
  ;

DYNAMICANCHORING  
  : 'DYNAMICANCHORING'   
  ;

BEFORE
  : 'BEFORE'
  ;

AFTER
  : 'AFTER'
  ;

IS  
  : 'IS'   
  ;


STARTSWITH  
  : 'STARTSWITH'   
  ;

ENDSWITH  
  : 'ENDSWITH'   
  ;



NOT
  : 'NOT'
  ;

ADD : 'ADD';
REMOVE  : 'REMOVE';
REMOVEDUPLICATE : 'REMOVEDUPLICATE';
MERGE   : 'MERGE';
GET : 'GET';
GETLIST : 'GETLIST';
SIZE  : 'SIZE';
MATCHEDTEXT : 'MATCHEDTEXT';
REMOVESTRING  : 'REMOVESTRING';
CLEAR   :  'CLEAR';

THEN 
  :   '->'
  ;

BasicAnnotationType 
  : 'COLON'| 'SW' | 'MARKUP' | 'PERIOD' | 'CW'| 'NUM' | 'QUESTION' | 'SPECIAL' | 'CAP' | 'COMMA' | 'EXCLAMATION' | 'SEMICOLON' | 'NBSP'| 'AMP' |
  '_' | 'SENTENCEEND' | 'W' | 'PM' | 'ANY' | 'ALL' | 'SPACE' | 'BREAK' 
  ;
  
LogLevel:
  'finest' | 'finer' | 'fine' | 'config' | 'info' | 'warning' | 'severe'
  ; 

OldColor 
  : 'black' | 'maroon' | 'green' | 'olive' | 'navy' | 'purple' | 'teal' | 'gray' | 'silver' | 'red' | 'lime' | 'yellow' | 'blue' | 'fuchsia' | 'aqua'
  ;

PackageString   : 'PACKAGE';
ScriptString  : 'SCRIPT';
EngineString  : 'ENGINE';
BlockString   : 'BLOCK';
AutomataBlockString   : 'RULES';
TypeString  : 'TYPE';
IntString : 'INT';
DoubleString  : 'DOUBLE';
FloatString : 'FLOAT';
StringString  : 'STRING';
BooleanString : 'BOOLEAN';
TypeSystemString: 'TYPESYSTEM'; 
SymbolString  : 'SYMBOL';
CONDITION : 'CONDITION';  
ACTION    : 'ACTION';
BOOLEANLIST 
  :  'BOOLEANLIST';
INTLIST : 'INTLIST';
DOUBLELIST
  :  'DOUBLELIST';
FLOATLIST
  :  'FLOATLIST';
STRINGLIST
  : 'STRINGLIST'; 
TYPELIST: 'TYPELIST';



EXP   : 'EXP';
LOGN  : 'LOGN';
SIN : 'SIN';
COS : 'COS';
TAN : 'TAN';
XOR :   'XOR';
TRUE  : 'true';
FALSE   : 'false';

HexLiteral : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OctalLiteral : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix : ('l'|'L') ;

FloatingPointLiteral
    :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
    //|   ('0'..'9')+ Exponent FloatTypeSuffix?
    //|   ('0'..'9')+ Exponent? FloatTypeSuffix
  ;
  
fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

CharacterLiteral
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;

StringLiteral
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

RessourceLiteral
    :  '\'' ( EscapeSequence | ~('\\'|'\'') )* '\''
    ;

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UnicodeEscape
    |   OctalEscape
    ;

fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

  
Identifier 
    :   Letter (Letter|JavaIDDigit)*
    ;


fragment
Letter
    :  '\u0024' |
       '\u0041'..'\u005a' |
       '\u005f' |
       '\u0061'..'\u007a' |
       '\u00c0'..'\u00d6' |
       '\u00d8'..'\u00f6' |
       '\u00f8'..'\u00ff' |
       '\u0100'..'\u1fff' |
       '\u3040'..'\u318f' |
       '\u3300'..'\u337f' |
       '\u3400'..'\u3d2d' |
       '\u4e00'..'\u9fff' |
       '\uf900'..'\ufaff'
    ;

fragment
JavaIDDigit
    :  '\u0030'..'\u0039' |
       '\u0660'..'\u0669' |
       '\u06f0'..'\u06f9' |
       '\u0966'..'\u096f' |
       '\u09e6'..'\u09ef' |
       '\u0a66'..'\u0a6f' |
       '\u0ae6'..'\u0aef' |
       '\u0b66'..'\u0b6f' |
       '\u0be7'..'\u0bef' |
       '\u0c66'..'\u0c6f' |
       '\u0ce6'..'\u0cef' |
       '\u0d66'..'\u0d6f' |
       '\u0e50'..'\u0e59' |
       '\u0ed0'..'\u0ed9' |
       '\u1040'..'\u1049'
   ;



LPAREN  : '(' {implicitLineJoiningLevel++;} ;

RPAREN  : ')' {implicitLineJoiningLevel--;} ;

LBRACK  : '[' {implicitLineJoiningLevel++;} ;

RBRACK  : ']' {implicitLineJoiningLevel--;} ;

LCURLY  : '{' {implicitLineJoiningLevel++;} ;

RCURLY  : '}' {implicitLineJoiningLevel--;} ;

CIRCUMFLEX  : '^' ;

AT : '@' ;

DOT : '.' ;

COLON   : ':' ;

COMMA : ',' ;

SEMI  : ';' ;

PLUS  : '+' ;

MINUS : '-' ;

STAR  : '*' ;

SLASH : '/' ;

VBAR  : '|' ;

AMPER : '&' ;

LESS  : '<' ;

GREATER : '>' ;

ASSIGN_EQUAL  : '=' ;

PERCENT : '%' ;

QUESTION  : '?' ;

EQUAL : '==' ;

NOTEQUAL  : '!=' ;

ALT_NOTEQUAL: '<>' ;

LESSEQUAL : '<=' ;


GREATEREQUAL  : '>=' ;

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;







COMMENT
    :   '/*'{if (input.LA(1)=='*') $type=DocComment; else $channel=HIDDEN;} ( options {greedy=false;} : . )* '*/' 
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;



    
