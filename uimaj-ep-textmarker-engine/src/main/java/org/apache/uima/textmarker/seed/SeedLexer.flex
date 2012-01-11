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


package org.apache.uima.textmarker.seed;
import java.util.*;
import java.util.regex.*;

import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.jcas.JCas;

import org.apache.uima.textmarker.type.AMP;
import org.apache.uima.textmarker.type.BREAK;
import org.apache.uima.textmarker.type.CAP;
import org.apache.uima.textmarker.type.COLON;
import org.apache.uima.textmarker.type.COMMA;
import org.apache.uima.textmarker.type.CW;
import org.apache.uima.textmarker.type.EXCLAMATION;
import org.apache.uima.textmarker.type.MARKUP;
import org.apache.uima.textmarker.type.NBSP;
import org.apache.uima.textmarker.type.NUM;
import org.apache.uima.textmarker.type.PERIOD;
import org.apache.uima.textmarker.type.QUESTION;
import org.apache.uima.textmarker.type.SEMICOLON;
import org.apache.uima.textmarker.type.SPACE;
import org.apache.uima.textmarker.type.SPECIAL;
import org.apache.uima.textmarker.type.SW;

%%

%{
    private JCas cas;
        
    public void setJCas(JCas cas) {
        this.cas = cas;
    }
%}

%unicode
%line
%char
%type AnnotationFS
%class SeedLexer

ALPHA=[A-Za-z]
DIGIT=[0-9]
WHITE_SPACE_CHAR=[\n\r\ \t\b\012]
BREAK=[\n\r\b\012]
SPACE=[ \t]

%%


<YYINITIAL> {
    
    \<[/][!][^>]*> {
                MARKUP t = new MARKUP(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }
                    
    \<[!][^>]*> {
                MARKUP t = new MARKUP(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }
    
    \<[/][A-Za-z][A-Za-z0-9]*[^>]*> {
                MARKUP t = new MARKUP(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }
                    
    \<[A-Za-z][A-Za-z0-9]*[^>]*> {
                MARKUP t = new MARKUP(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }
                
    \xA0|&nbsp;|&NBSP; {
                NBSP t = new NBSP(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    &{ALPHA}+; {
                AMP t = new AMP(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    {BREAK} {
                BREAK t = new BREAK(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    {SPACE} {
                SPACE t = new SPACE(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    ":" {
                COLON t = new COLON(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    "," {
                COMMA t = new COMMA(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    "." {
                PERIOD t = new PERIOD(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    
    "!" {
                EXCLAMATION t = new EXCLAMATION(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;    
    }

    ";" {
                SEMICOLON t = new SEMICOLON(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    "?" {
                QUESTION t = new QUESTION(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    [:lowercase:]+ {
                SW t = new SW(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    [:uppercase:][:lowercase:]* {
                CW t = new CW(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    [:uppercase:]+ {
                CAP t = new CAP(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    {DIGIT}+ {
                NUM t = new NUM(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    . {
                SPECIAL t = new SPECIAL(cas);
                t.setBegin(yychar);
                t.setEnd(yychar + yytext().length());
                
                return t;
    }

    <<EOF>> {
                return null;
    }

}

