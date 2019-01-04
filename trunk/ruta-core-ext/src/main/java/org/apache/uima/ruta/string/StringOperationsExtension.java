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

package org.apache.uima.ruta.string;

import java.util.List;

import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionExpression;
import org.apache.uima.ruta.extensions.IRutaStringFunctionExtension;
import org.apache.uima.ruta.extensions.RutaParseException;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class StringOperationsExtension implements IRutaStringFunctionExtension {

  private final String[] knownExtensions = new String[] { "toUpperCase", "toLowerCase",
      "replaceFirst", "replaceAll", "substring", "firstCharToUpperCase" };

  private final Class<?>[] extensions = new Class[] { ToUpperCaseStringFunction.class,
      ToLowerCaseStringFunction.class, ReplaceFirstStringFunction.class,
      ReplaceAllStringFunction.class, SubstringStringFunction.class,
      FirstCharToUpperCaseStringFunction.class };

  
  
  public String verbalize(RutaElement element, RutaVerbalizer verbalizer) {
    if (element instanceof ToLowerCaseStringFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((ToLowerCaseStringFunction) element).getExpr()) + ")";
    } else if (element instanceof ToUpperCaseStringFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((ToUpperCaseStringFunction) element).getExpr()) + ")";
    }
    //
    else if (element instanceof ReplaceFirstStringFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((ReplaceFirstStringFunction) element).getExpr()) + ")";
    } else if (element instanceof ReplaceAllStringFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((ReplaceAllStringFunction) element).getExpr()) + ")";
    } else if (element instanceof SubstringStringFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((SubstringStringFunction) element).getExpr()) + ")";
    } else if (element instanceof FirstCharToUpperCaseStringFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((FirstCharToUpperCaseStringFunction) element).getExpr())
              + ")";
    }
    //
    else {
      return "UnknownStringFunction";
    }
  }

  public StringFunctionExpression createStringFunction(String name, List<RutaExpression> args)
          throws RutaParseException {
    if (args == null  || !(args.get(0) instanceof IStringExpression)) {
      throw new RutaParseException("You have to specify StringExpressions to use these Functions !");
    }

    // ToUpperCase  [0] is the String to change
    if (name.equals(knownExtensions[0])) {
      return new ToUpperCaseStringFunction((IStringExpression) args.get(0));
    }

    // ToLowerCase Function
    else if (name.equals(knownExtensions[1])) {
      return new ToLowerCaseStringFunction((IStringExpression) args.get(0));
    }

    // FirstCharToUppercase
    else if (name.equals(knownExtensions[5])) {
      return new FirstCharToUpperCaseStringFunction((IStringExpression) args.get(0));
    }
    
    if(args.size()!=3){
      throw new RutaParseException("You need 3 Arguments to use ReplaceFirst, ReplaceAll, Substring");
    }
    // ReplaceFirst [0] is the String where stuff is to be replaced, [1] is the search term [2] is the replacement
     if (name.equals(knownExtensions[2])) {
      return new ReplaceFirstStringFunction((IStringExpression) args.get(0),(IStringExpression) args.get(1),(IStringExpression) args.get(2));
    }
    // ReplaceAll [0] is the String where stuff is to be replaced, [1] is the search term [2] is the replacement
    else if (name.equals(knownExtensions[3])) {
      return new ReplaceAllStringFunction((IStringExpression) args.get(0),(IStringExpression) args.get(1),(IStringExpression) args.get(2));
    }
    // Substring [0] is the String where stuff is to be replaced, [1] is the begin index [2] is the endIndex
    else if (name.equals(knownExtensions[4])) {
      return new SubstringStringFunction((IStringExpression) args.get(0),(INumberExpression) args.get(1),(INumberExpression)args.get(2));
    }
    
    return null;
  }

  public String verbalizeName(RutaElement element) {
    
     if (element instanceof ToUpperCaseStringFunction) {
      return knownExtensions[0];
    }
     
     else if (element instanceof ToLowerCaseStringFunction) {
      return knownExtensions[1];
    }

    
    else if (element instanceof ReplaceFirstStringFunction) {
      return knownExtensions[2];
    }
    
    else if (element instanceof ReplaceAllStringFunction) {
      return knownExtensions[3];
    }
    
    else if (element instanceof SubstringStringFunction) {
      return knownExtensions[4];
    }
    
    else if (element instanceof FirstCharToUpperCaseStringFunction) {
      return knownExtensions[5];
    }
    

    
    
    else {
      return "UnknownStringFunction";
    }
  }

  public String[] getKnownExtensions() {
    return knownExtensions;
  }

  public Class<?>[] extensions() {
    return extensions;
  }

}
