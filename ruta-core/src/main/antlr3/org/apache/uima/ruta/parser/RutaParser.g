parser grammar RutaParser;

options {
	language = Java;
	tokenVocab = RutaLexer;
}


@parser::header {
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

package org.apache.uima.ruta.parser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.TreeSet;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.EarlyExitException;
import org.antlr.runtime.FailedPredicateException;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.TypeDescription;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.UimaContext;

import org.apache.uima.ruta.descriptor.*;
import org.apache.uima.ruta.action.AbstractRutaAction;
import org.apache.uima.ruta.action.ActionFactory;
import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.condition.ConditionFactory;
import org.apache.uima.ruta.RutaBlock;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.RutaScriptBlock;
import org.apache.uima.ruta.RutaScriptFactory;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.expression.ExpressionFactory;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.list.BooleanListExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.NumberListExpression;
import org.apache.uima.ruta.expression.list.StringListExpression;
import org.apache.uima.ruta.expression.list.TypeListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionFactory;
import org.apache.uima.ruta.expression.type.TypeExpression;
import org.apache.uima.ruta.extensions.RutaExternalFactory;
import org.apache.uima.ruta.extensions.RutaParseRuntimeException;
import org.apache.uima.ruta.rule.AbstractRuleElement;
import org.apache.uima.ruta.rule.ComposedRuleElement;
import org.apache.uima.ruta.rule.RegExpRule;
import org.apache.uima.ruta.rule.RuleElement;
import org.apache.uima.ruta.rule.RuleElementContainer;
import org.apache.uima.ruta.rule.RuleElementIsolator;
import org.apache.uima.ruta.rule.RutaRule;
import org.apache.uima.ruta.rule.RutaRuleElement;
import org.apache.uima.ruta.rule.quantifier.RuleElementQuantifier;
}

@parser::members {
private List vars = new ArrayList();	
private int level = 0;
private RutaScriptFactory factory = new RutaScriptFactory();
private RutaExternalFactory external;
private String namespace;
private String moduleName;
private ResourceManager resourceManager;
private UimaContext context;

private RutaDescriptorInformation descInfo;


public void setDescriptorInformation(RutaDescriptorInformation descInfo) {
  this.descInfo = descInfo;  
}

public RutaDescriptorInformation getDescriptorInformation() {
  return descInfo;
}

public void setResourceManager(ResourceManager resourceManager) {
  this.resourceManager = resourceManager;  
}

public void setExternalFactory(RutaExternalFactory factory) {
	external = factory;
}
	public void setContext(UimaContext context){
	    this.context = context;
	    factory.setContext(context);
	}

	public void emitErrorMessage(String msg) {
		throw new RutaParseRuntimeException(msg);
	}
	 public void emitErrorMessage(RecognitionException e) {
	    int foundInt = e.c;
	    String stringFound = "<unknown token>";
	    if(foundInt >= 0 && foundInt < getTokenNames().length) {
	    	stringFound = getTokenNames()[foundInt];
	    }
	    int line = e.line;
	    String text = e.token.getText();
	
   	    if (e instanceof NoViableAltException) {
	      NoViableAltException nvae = (NoViableAltException) e;
	      String msg = "Error in "+moduleName+",  line " + nvae.line + ", \"" + text + "\": found no viable alternative";
	      emitErrorMessage(msg);
	    } else if (e instanceof MismatchedTokenException) {
	      MismatchedTokenException mte = (MismatchedTokenException) e;
	      int expectedInt = mte.expecting;
	      String stringExpected = expectedInt < 0 ? "'none'" : getTokenNames()[expectedInt];
	      String msg = "Error in "+moduleName+", line " + line + ", \"" + text + "\": expected " + stringExpected
	              + ", but found " + stringFound;
	      emitErrorMessage(msg);
	    } else if (e instanceof MissingTokenException) {
	      MissingTokenException mte = (MissingTokenException) e;
    	      int missingType = mte.getMissingType();
    	      String stringMissing = getTokenNames()[missingType];
    	      String msg = "Error in "+moduleName+",  line " + line + ", \"" + text + "\": missing " + stringMissing
                    + ", but found " + stringFound;
    	      emitErrorMessage(msg);
	    } else {
	      emitErrorMessage(e.getMessage());
	    }
	  }
	    
	public void emitErrorMessage(Throwable e) {
	    throw new RutaParseRuntimeException(e);
	    }
	
	public void reportError(RecognitionException e) {
		emitErrorMessage(e);
	}

	//public void addVariable(String var, IntStream input) throws NoViableAltException {
	//	if(!vars.contains(var)) {
	//		vars.add(var);
	//	} else {
	//		throw new NoViableAltException("already declared \"" + var + "\"", 3, 0, input);
	//	}
	//}
	public void addVariable(RutaBlock parent, String name, String type) {
		parent.getEnvironment().addVariable(name, type);
	}
	
	public boolean ownsVariable(RutaBlock parent, String name) {
		return parent.getEnvironment().ownsVariable(name);
	}
	public boolean isVariable(RutaBlock parent, String name) {
		return parent.getEnvironment().isVariable(name);
	}
	
	public void setValue(RutaBlock parent, List<String> names, Object obj) {
		for(String name : names) {
			setValue(parent,name,obj);
		}
	}
	
	public void setValue(RutaBlock parent, String name, Object obj) {
		if(obj != null) {
			Object value = parent.getEnvironment().getLiteralValue(name, obj);
			parent.getEnvironment().setVariableValue(name, value);
			parent.getEnvironment().setInitialVariableValue(name, value);
		}
	}
	
	public boolean ownsVariableOfType(RutaBlock parent, String name, String type) {
		return parent.getEnvironment().ownsVariableOfType(name,type);
	}
	
	public boolean isVariableOfType(RutaBlock parent, String name, String type) {
		return parent.getEnvironment().isVariableOfType(name,type);
	}
	
	/*
	public void addType(RutaBlock parent, String type) {
	    assert !moduleName.contains(".");
	    assert !type.contains(".");

	    String resolvedType = type;
	    if (!type.contains(".")) {
	        resolvedType = namespace + "." + moduleName + "." + type;
	    }
            parent.getEnvironment().declareType(resolvedType);
            
            if(descInfo!= null) {
	            String descriptionString = null;
		    if(StringUtils.isBlank(namespace)) {
		       descriptionString = "Type defined in " + moduleName;
		    } else {
		       descriptionString = "Type defined in " + parent.getNamespace() + "." + moduleName;
		    }
		    String parentType = "uima.tcas.Annotation";
		    
		    descInfo.addType(parent.getNamespace()+"."+type.trim(), descriptionString, parentType);
		}
	}
	*/
	public void addType(RutaBlock parent, Token nameToken, Token parentTypeToken, List featureTypes,
          List featureNames) {
          String name = nameToken.getText();
          String parentType = "uima.tcas.Annotation";
          if(parentTypeToken != null) {
          	parentType = parentTypeToken.getText();
	  }
	  String resolvedType = name;
	  if (!name.contains(".")) {
	    resolvedType = namespace + "." + moduleName + "." + name;
	  }
          parent.getEnvironment().declareType(resolvedType);
	  if(descInfo != null) {
		  name = parent.getNamespace() + "." + name.trim();
		  String descriptionString = null;
		  if(StringUtils.isBlank(namespace)) {
			  descriptionString = "Type defined in " + moduleName;
			  } else {
			  descriptionString = "Type defined in " + parent.getNamespace();
		  }
		  descInfo.addType(name, descriptionString, parentType);
		  if(featureTypes != null && featureNames != null) {
			  for (int i = 0; i < featureTypes.size(); i++) {
				  Object object = featureTypes.get(i);
				  String ftype = (String) featureTypes.get(i);
				  String fname = (String) featureNames.get(i);
				  descInfo.addFeature(name, fname, fname, ftype);
			  }
		  }
	  }
 	 }
	
	public boolean isType(RutaBlock parent, String type) {
		return parent.getEnvironment().getType(type) != null || type.equals("Document");
	}
	
	public void checkVariable(String var, IntStream input) throws NoViableAltException {
		if(!vars.contains(var)) {
			throw new NoViableAltException("not declared \"" + var + "\"", 3, 0, input);
		}
	}
	
	public void addImportTypeSystem(RutaBlock parent, String descriptor) {
		parent.getEnvironment().addTypeSystem(descriptor);
		if(descInfo != null) {
		  descInfo.addTypeSystem(descriptor);
		}
	}
	public void addImportScript(RutaBlock parent, String namespace) {
		parent.getScript().addScript(namespace, null);
		if(descInfo != null) {
		  descInfo.addScript(namespace);
		}
	}
	public void addImportEngine(RutaBlock parent, String namespace) {
		parent.getScript().addEngine(namespace, null);
		if(descInfo != null) {
		  descInfo.addEngine(namespace);
		}
	}
	public void addImportUimafitEngine(RutaBlock parent, String namespace) {
		parent.getScript().addUimafitEngine(namespace, null);
		if(descInfo != null) {
		  descInfo.addUimafitEngine(namespace);
		}
	}

	/**
	 * Import a type from a type system.
	 *
	 * @param parent - Block where the type should be imported.
	 * @param typesystem - Typesystem from which to import the type.
	 * @param qualifiedType - Type to import from the typesystem.
	 */
    public void importTypeFromTypeSystem(RutaBlock parent, String typesystem, String qualifiedType, Token alias) {
        if (alias == null) {
            parent.getEnvironment().importTypeFromTypeSystem(typesystem, qualifiedType);
        } else {
            parent.getEnvironment().importTypeFromTypeSystem(typesystem, qualifiedType, alias.getText());
        }
        if(descInfo != null) {
          descInfo.addTypeSystem(typesystem);
        }
    }

    /**
     * Import a package from a type system.
     *
     * @param parent - Block where the type should be imported.
     * @param typesystem - Typesystem from which to import the package or null to import it from the CAS typesystem.
     * @param qualifiedPackage - Package to import from the typesystem.
     * @param alias - Package alias.
     */
    public void importPackage(RutaBlock parent, String typesystem, String qualifiedPackage, Token alias) {
        RutaEnvironment env = parent.getEnvironment();
        String aliasText = alias != null? alias.getText() : null;
        if (typesystem != null) {
            env.importPackageFromTypeSystem(typesystem, qualifiedPackage, aliasText);
        } else {
            env.importPackage(qualifiedPackage, aliasText);
        }
        if(descInfo != null) {
          descInfo.addTypeSystem(typesystem);
        }
    }

    /**
     * Import all packages from a type system.
     *
     * @param parent - Block where the type should be imported.
     * @param typesystem - Typesystem from which to import the package or null to import it from the CAS typesystem.
     * @param alias - Package alias.
     */
    public void importAllPackages(RutaBlock parent, String typesystem, Token alias) {
        RutaEnvironment env = parent.getEnvironment();
        String aliasText = alias != null? alias.getText() : null;

        env.importAllPackagesFromTypeSystem(typesystem, aliasText);
        if(descInfo != null) {
          descInfo.addTypeSystem(typesystem);
        }
    }

	protected static final int[] getBounds(Token t) {
    		if (t instanceof CommonToken) {
    			CommonToken ct = (CommonToken) t;
    			int[] bounds = {ct.getStartIndex(), ct.getStopIndex()}; 
    			return bounds;
    		}
    		return null;
    	}
	
	private String[] resourcePaths;
	

	public void setResourcePaths(String[] resourcePaths) {
		this.resourcePaths = resourcePaths;
	}

	private boolean isBooleanFunctionExtension(String name) {
      	  return external.getBooleanFunctionExtensions().keySet().contains(name);
      	}
      	private boolean isActionExtension(String name) {
      	  return external.getActionExtensions().keySet().contains(name);
      	}
      	private boolean isConditionExtension(String name) {
      	  return external.getConditionExtensions().keySet().contains(name);
      	}
      	private boolean isNumberFunctionExtension(String name) {
      	  return external.getNumberFunctionExtensions().keySet().contains(name);
      	}
      	private boolean isStringFunctionExtension(String name) {
      	  return external.getStringFunctionExtensions().keySet().contains(name);
      	}
      	private boolean isTypeFunctionExtension(String name) {
      	  return external.getTypeFunctionExtensions().keySet().contains(name);
      	}
      	private boolean isBlockExtension(String name) {
      	  return external.getBlockExtensions().keySet().contains(name);
      	}
      	


}

@rulecatch {
	catch (RecognitionException exception1) {
	state.failed = true;
		emitErrorMessage(exception1);
	}
	catch (Throwable exception2) {
		emitErrorMessage(exception2);
	}
}


file_input [String moduleName] returns [RutaModule module]
@init{
RutaScriptBlock rootBlock = null;
List<RutaStatement> stmts = new ArrayList<RutaStatement>();
}
	:
	p = packageDeclaration?
	{
	namespace = p;
	if(descInfo != null) {
		descInfo.setPackageString(p);
	}
	this.moduleName = moduleName;
	rootBlock = factory.createRootScriptBlock(moduleName, p);
        rootBlock.getEnvironment().setResourcePaths(resourcePaths);
        rootBlock.getEnvironment().setResourceManager(resourceManager);
	rootBlock.setElements(stmts);
	module = new RutaModule(rootBlock);
	rootBlock.setScript(module);
	}
	{$blockDeclaration.push(new blockDeclaration_scope());$blockDeclaration::env = rootBlock;}
	
	gs = globalStatements	{stmts.addAll(gs);}
	s = statements		{stmts.addAll(s);}
	
	EOF
	;

packageDeclaration returns [String pack = ""]
	:	PackageString p = dottedIdentifier SEMI {pack = p;}
	;

statements returns [List<RutaStatement> stmts = new ArrayList<RutaStatement>()]
	:
	(stmt = statement {{if(stmt != null) {stmts.add(stmt);}}})*
	;

globalStatements returns [List<RutaStatement> stmts = new ArrayList<RutaStatement>()]
	:
	(morestmts = globalStatement {if(morestmts != null) {stmts.addAll(morestmts);}})*
	;

globalStatement returns [List<RutaStatement> stmts = new ArrayList<RutaStatement>()]
	:
	stmtImport = importStatement {stmts.add(stmtImport);}
	;
	
statement returns [RutaStatement stmt = null]
	:	
	( stmtDecl = declaration {stmt = stmtDecl;}
	| stmtVariable = variableDeclaration {stmt = stmtVariable;}
	| stmtRule = simpleStatement {stmt = stmtRule;}
	| stmtBlock = blockDeclaration {stmt = stmtBlock;}
	| stmtExternal = externalBlock {stmt = stmtExternal;}
	)
	;

variableDeclaration returns [RutaStatement stmt = null]
@init {
List<String> vars = new ArrayList<String>();
}
	:
	type = IntString
	{!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		(COMMA {!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		 )*(ASSIGN_EQUAL value1 = numberExpression)? {setValue($blockDeclaration::env, vars, value1);}  SEMI
	|
	type = DoubleString
	{!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		(COMMA {!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		 )* (ASSIGN_EQUAL value2 = numberExpression)? {setValue($blockDeclaration::env, vars, value2);} SEMI
	|
	type = FloatString
	{!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		(COMMA {!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		 )* (ASSIGN_EQUAL value2 = numberExpression)? {setValue($blockDeclaration::env, vars, value2);} SEMI
	|
	type = StringString
	{!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		(COMMA {!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		 )* (ASSIGN_EQUAL value3 = stringExpression)? {setValue($blockDeclaration::env, vars, value3);} SEMI
	|
	type = BooleanString
	{!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		(COMMA {!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		 )* (ASSIGN_EQUAL value4 = booleanExpression)? {setValue($blockDeclaration::env, vars, value4);} SEMI
	|
	type = TypeString
	{!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		(COMMA {!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		 )* (ASSIGN_EQUAL value5 = typeExpression)? {setValue($blockDeclaration::env, vars, value5);} SEMI
	|
	type = WORDLIST 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL list = wordListExpression)? 
	{addVariable($blockDeclaration::env, name.getText(), type.getText());if(list != null){setValue($blockDeclaration::env, name.getText(), list);}} 
	SEMI 
	| 
	type = WORDTABLE 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL table = wordTableExpression)? 
	{addVariable($blockDeclaration::env, name.getText(), type.getText());if(table != null){setValue($blockDeclaration::env, name.getText(), table);}}
	SEMI 
	|
	type = BOOLEANLIST 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL bl = booleanListExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(bl != null){setValue($blockDeclaration::env, name.getText(), bl);}} 
	|
	type = STRINGLIST 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL sl = stringListExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(sl != null){setValue($blockDeclaration::env, name.getText(), sl);}} 
	|
	type = INTLIST 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL il = numberListExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(il != null){setValue($blockDeclaration::env, name.getText(), il);}} 
	|
	type = DOUBLELIST 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL dl = numberListExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(dl != null){setValue($blockDeclaration::env, name.getText(), dl);}} 
	|
	type = FLOATLIST 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL dl = numberListExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(dl != null){setValue($blockDeclaration::env, name.getText(), dl);}} 
	|
	type = TYPELIST 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL tl = typeListExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(tl != null){setValue($blockDeclaration::env, name.getText(), tl);}} 
	//|
	//stmt1 = conditionDeclaration {stmt = stmt1;}
	//|
	//stmt2 = actionDeclaration {stmt = stmt2;}
	;

//TODO added rule
//conditionDeclaration returns [RutaStatement stmt = null]
  //  :
//    type = CONDITION id = Identifier ASSIGN_EQUAL LPAREN cons = conditions RPAREN SEMI
//    {addVariable($blockDeclaration::env, id.getText(), type.getText());
//    AbstractRutaCondition condition = ConditionFactory.createConditionAnd(cons,$blockDeclaration::env);
//    setValue($blockDeclaration::env, id.getText(), condition);}
//    ;

//TODO added rule
//actionDeclaration returns [RutaStatement stmt = null]
//    :
//    type = ACTION id = Identifier ASSIGN_EQUAL LPAREN a = actions RPAREN SEMI
//    {addVariable($blockDeclaration::env, id.getText(), type.getText());
//    AbstractRutaAction action = ActionFactory.createComposedAction(a,$blockDeclaration::env);
//    setValue($blockDeclaration::env, id.getText(), action);}
//    ;

importStatement returns [RutaStatement stmt = null]
	:
	TypeSystemString ts = dottedIdentifier2{addImportTypeSystem($blockDeclaration::env, ts);} SEMI
	| ScriptString ns = dottedIdentifier2{addImportScript($blockDeclaration::env, ns);} SEMI
	| EngineString ns = dottedIdentifier2{addImportEngine($blockDeclaration::env, ns);} SEMI
	| UimafitString ns = dottedIdentifier2{addImportUimafitEngine($blockDeclaration::env, ns);} SEMI
	| ImportString type = dottedIdentifier (FromString ts = dottedIdentifier2)? (AsString alias = Identifier)? SEMI{importTypeFromTypeSystem($blockDeclaration::env, ts, type, alias);}
	| ImportString STAR FromString ts = dottedIdentifier2 SEMI{addImportTypeSystem($blockDeclaration::env, ts);}
	| ImportString PackageString pkg = dottedIdentifier (FromString ts = dottedIdentifier2)? (AsString alias = Identifier)? SEMI{importPackage($blockDeclaration::env, ts, pkg, alias);}
	| ImportString PackageString STAR FromString ts = dottedIdentifier2 (AsString alias = Identifier)? SEMI{importAllPackages($blockDeclaration::env, ts, alias);}
	;

declaration returns [RutaStatement stmt = null]
@init {
List featureTypes = new ArrayList();
List featureNames = new ArrayList();
}
	:
	DECLARE 
	//{!isType($blockDeclaration::env, input.LT(1).getText())}? 
	lazyParent = annotationType?
	id = Identifier 
	
	(
	{addType($blockDeclaration::env, id, lazyParent, null, null);}
			(
			
			COMMA 
			//{!isType($blockDeclaration::env, input.LT(1).getText())}? 
			id = Identifier {addType($blockDeclaration::env, id, lazyParent, null, null);}
		 )* SEMI
	| 
		(LPAREN 
			(
			obj1 = annotationType{featureTypes.add(obj1.getText());} 
			| obj2 = StringString{featureTypes.add(obj2.getText());} 
			| obj3 = DoubleString{featureTypes.add(obj3.getText());}
			| obj6 = FloatString{featureTypes.add(obj6.getText());}
			| obj4 = IntString{featureTypes.add(obj4.getText());}
			| obj5 = BooleanString{featureTypes.add(obj5.getText());}
			) 
			fname = Identifier{featureNames.add(fname.getText());} 
			(
			COMMA 
			(
			obj1 = annotationType{featureTypes.add(obj1.getText());} 
			| obj2 = StringString{featureTypes.add(obj2.getText());} 
			| obj3 = DoubleString{featureTypes.add(obj3.getText());}
			| obj6 = FloatString{featureTypes.add(obj6.getText());}
			| obj4 = IntString{featureTypes.add(obj4.getText());}
			| obj5 = BooleanString{featureTypes.add(obj5.getText());}
			) 
			fname = Identifier{featureNames.add(fname.getText());})* 
		RPAREN) SEMI 
		{
		addType($blockDeclaration::env, id, lazyParent, featureTypes, featureNames);
		}
	)
	;
	
	
blockDeclaration returns [RutaBlock block = null]
options {
	backtrack = true;
}

scope {
	RutaBlock env;
}
@init{
RutaRuleElement re = null;
RuleElementIsolator container = null;
level++;
}
@after {
level--;
}

	:
	type = BlockString 
	LPAREN
	id = Identifier 
	RPAREN
	{block = factory.createScriptBlock(id, re, body, $blockDeclaration[level - 1]::env);}
	{$blockDeclaration::env = block;
	container = new RuleElementIsolator();}
	re1 = ruleElementWithCA[container]
	 {re = re1;	 }
	{RutaRule rule = factory.createRule(re, block);
	if(block instanceof RutaScriptBlock) {
	((RutaScriptBlock)block).setRule(rule);
	}
	container.setContainer(rule);
	}
	LCURLY body = statements RCURLY
	{block.setElements(body);
	$blockDeclaration::env.getScript().addBlock(id.getText(),block);
	}	
	;


externalBlock returns [RutaBlock block = null]
options {
	backtrack = true;
}

scope {
	RutaBlock env;
}
@init{
RutaRuleElement re = null;
RuleElementIsolator container = null;
level++;
}
@after {
level--;
}
	:
	{isBlockExtension(input.LT(1).getText())}? 
	type = Identifier 
	(LPAREN
	args = varArgumentList
	RPAREN)?
	{block = external.createExternalBlock(type, args, $blockDeclaration[level - 1]::env);}
	{$blockDeclaration::env = block;
	container = new RuleElementIsolator();}
	
	re1 = ruleElementWithCA[container] {re = re1;}
	
	{RutaRule rule = factory.createRule(re, block);
	block.setRule(rule);
	container.setContainer(rule);}
	
	LCURLY body = statements RCURLY
	{block.setElements(body);
	
	{$blockDeclaration::env = block.getParent();}
	
	}	
	;


	
ruleElementWithCA[RuleElementContainer container] returns [RutaRuleElement re = null] 
    :
    
    idRef=typeMatchExpression 
    {re = factory.createRuleElement(idRef,null,null,null, container, $blockDeclaration::env);}
    q = quantifierPart? 
        LCURLY c = conditions? (THEN a = actions)? RCURLY
         {
	if(q != null) {
		re.setQuantifier(q);
	}
	if(c!= null) {
		re.setConditions(c);
	}
	if(a != null) {
		re.setActions(a);
	}
	}
    ;


	
simpleStatement returns [RutaStatement stmt = null]
options {
	backtrack = true;
}
@init{
	//RegExpRule rer = null;
	Map<TypeExpression, IRutaExpression> map = new HashMap<TypeExpression, IRutaExpression>();
}
	: 

	(regexpRule)=> rer = regexpRule {stmt = rer;}
	{stmt = rer;}
	
	|
	as = rawActions 
	{stmt = factory.createImplicitRule(as, $blockDeclaration::env);} 
	SEMI
	|
	{stmt = factory.createRule(elements, $blockDeclaration::env);}
	elements = ruleElementsRoot[stmt == null? null:((RutaRule)stmt).getRoot()] SEMI 
	{if(elements != null){((RutaRule)stmt).setRuleElements(elements);} else {}}
	
	
	;




regexpRule returns [RegExpRule stmt = null]
@init{
	Map<TypeExpression, INumberExpression> map = new HashMap<TypeExpression, INumberExpression>();
	Map<TypeExpression, Map<IStringExpression, IRutaExpression>> fa = new HashMap<TypeExpression, Map<IStringExpression, IRutaExpression>>();
	Map<IStringExpression, IRutaExpression> fmap = null;
}
	:
	{
	stmt = factory.createRegExpRule($blockDeclaration::env);}
	
	regexp = stringExpression THEN
	(
	(numberExpression ASSIGN_EQUAL)=> indexCG = numberExpression ASSIGN_EQUAL indexTE = typeExpression {map.put(indexTE, indexCG);}
	(LPAREN {fmap = new HashMap<IStringExpression, IRutaExpression>();} fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} 
	(COMMA fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} )* RPAREN {fa.put(indexTE, fmap);})?
	|
	te = typeExpression {map.put(te, null);}
	(LPAREN {fmap = new HashMap<IStringExpression, IRutaExpression>();} fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} 
	(COMMA fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} )* RPAREN {fa.put(te, fmap);})?
	)
	
	(
	COMMA
	(
	(numberExpression ASSIGN_EQUAL)=> indexCG = numberExpression ASSIGN_EQUAL indexTE = typeExpression {map.put(indexTE, indexCG);}
	(LPAREN {fmap = new HashMap<IStringExpression, IRutaExpression>();} fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} 
	(COMMA fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} )* RPAREN {fa.put(indexTE, fmap);})?
	|
	te = typeExpression {map.put(te, null);}
	(LPAREN {fmap = new HashMap<IStringExpression, IRutaExpression>();} fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} 
	(COMMA fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} )* RPAREN {fa.put(te, fmap);})?
	)
	
	)*

	SEMI
	{stmt.setRegExp(regexp);
	stmt.setTypeMap(map);
	stmt.setFeatureAssignments(fa);
	}
	
	;


	
ruleElementsRoot[RuleElementContainer container] returns [List<RuleElement> elements = new ArrayList<RuleElement>()]
@init{
	List<RuleElement> reList = new ArrayList<RuleElement>();
	List<Token> conList = new ArrayList<Token>();
}
	:
	re = ruleElement[container] {if(re!=null){ reList.add(re); conList.add(null);} else {}} 
	(
	(con = PERCENT {conList.add(con);})? 
	re = ruleElement[container] {if(re!=null){ reList.add(re); conList.add(null);} else {}}
	)*
	{elements = factory.processConjunctRules(reList, conList, container, $blockDeclaration::env);}
	;

ruleElements[RuleElementContainer container] returns [List<RuleElement> elements = new ArrayList<RuleElement>()]
@init{
	List<RuleElement> reList = new ArrayList<RuleElement>();
}
	:
	re = ruleElement[container] {if(re!=null){ elements.add(re);}else {}} 
	(re = ruleElement[container] {if(re!=null){ elements.add(re);}else {}})*
	;
		

ruleElement[RuleElementContainer container] returns [RuleElement re = null]
@init{
List<RutaStatement> innerConditionRules = new ArrayList<RutaStatement>();
List<RutaStatement> innerActionRules = new ArrayList<RutaStatement>();
}
	:
	start = STARTANCHOR? (
	re1 = ruleElementType[container] {re = re1;}
	| re2 = ruleElementLiteral[container] {re = re2;}
	| (ruleElementComposed[null])=>re3 = ruleElementComposed[container] {re = re3;}
	| (ruleElementWildCard[null])=> re5 = ruleElementWildCard[container] {re = re5;}
	)
	{re.setStartAnchor(start != null);}
	(t = (THEN2) 
	LCURLY 
	(rule = simpleStatement {innerConditionRules.add(rule);})+ 
	RCURLY 
	{re.setInlinedConditionRules(innerConditionRules);}

	)?
	(t = (THEN) 
	LCURLY 
	(rule = simpleStatement {innerActionRules.add(rule);})+ 
	RCURLY 
	{re.setInlinedActionRules(innerActionRules);}
	)?
	;	

ruleElementWildCard [RuleElementContainer container] returns [AbstractRuleElement re = null]
    :
    
    w = WILDCARD 
     {re = factory.createWildCardRuleElement(null, null, container, $blockDeclaration::env);} 
        (LCURLY c = conditions? (THEN a = actions)? RCURLY)?
   {
	if(c!= null) {
		re.setConditions(c);
	}
	if(a != null) {
		re.setActions(a);
	}
	}
    ;



ruleElementComposed [RuleElementContainer container] returns [ComposedRuleElement re = null]
scope {
	RuleElementContainer con;
}
@init{
	Boolean conjunct = null;
	List<RuleElement> res = new ArrayList<RuleElement>();
}	
	:
	{re = factory.createComposedRuleElement(container, $blockDeclaration::env);
	$ruleElementComposed::con = re;}
	
	LPAREN 
	(
	(ruleElement[$ruleElementComposed::con] VBAR)=> re1 = ruleElement[$ruleElementComposed::con] {res.add(re1);} (VBAR re1 = ruleElement[$ruleElementComposed::con] {conjunct = false; res.add(re1);})+
	|
	(ruleElement[$ruleElementComposed::con] AMPER)=> re2 = ruleElement[$ruleElementComposed::con] {res.add(re2);} (AMPER re2 = ruleElement[$ruleElementComposed::con] {conjunct = true; res.add(re2);})+
	|
	res2 = ruleElements[$ruleElementComposed::con] {res = res2;}
	)

	RPAREN q = quantifierPart? (LCURLY c = conditions? (THEN a = actions)? RCURLY)?
	{
	re.setRuleElements(res);
	re.setConjunct(conjunct);
	if(q != null) {
		re.setQuantifier(q);
	}
	if(c!= null) {
		re.setConditions(c);
	}
	if(a != null) {
		re.setActions(a);
	}
	}
	;


ruleElementType [RuleElementContainer container] returns [RutaRuleElement re = null]
    :
    
    (typeMatchExpression)=>typeExpr = typeMatchExpression 
     {re = factory.createRuleElement(typeExpr, null, null, null, container, $blockDeclaration::env);} 
    q = quantifierPart? 
        (LCURLY c = conditions? (THEN a = actions)? RCURLY)?
   {
	if(q != null) {
		re.setQuantifier(q);
	}
	if(c!= null) {
		re.setConditions(c);
	}
	if(a != null) {
		re.setActions(a);
	}
	}
    ;

ruleElementLiteral [RuleElementContainer container] returns [RutaRuleElement re = null]
    :
    
    (simpleStringExpression)=>stringExpr = simpleStringExpression 
     {re = factory.createRuleElement(stringExpr, null, null, null, container, $blockDeclaration::env);} 
    
    q = quantifierPart? 
        (LCURLY c = conditions? (THEN a = actions)? RCURLY)?
    {
	if(q != null) {
		re.setQuantifier(q);
	}
	if(c!= null) {
		re.setConditions(c);
	}
	if(a != null) {
		re.setActions(a);
	}
	}
    ;
	
conditions returns [List<AbstractRutaCondition> conds = new ArrayList<AbstractRutaCondition>()]
    :
    c = condition {conds.add(c);} (COMMA c = condition {conds.add(c);} )*
    ;
    
actions returns [List<AbstractRutaAction> actions = new ArrayList<AbstractRutaAction>()]
    :
    a = action {actions.add(a);} (COMMA a = action {actions.add(a);} )*
    ; 	
rawActions returns [List<AbstractRutaAction> actions = new ArrayList<AbstractRutaAction>()]
    :
    a = rawAction {actions.add(a);} (COMMA a = rawAction {actions.add(a);} )*
    ; 	

listExpression returns [ListExpression expr = null]
	:
	(booleanListExpression)=> bl = booleanListExpression {expr = bl;}
	| (intListExpression)=> il = intListExpression {expr = il;}
	| (doubleListExpression)=> dl = doubleListExpression {expr = dl;}
	| (floatListExpression)=> dl = floatListExpression {expr = dl;}
	| (stringListExpression)=> sl = stringListExpression {expr = sl;}
	| (typeListExpression)=> tl = typeListExpression {expr = tl;}
	| (untypedListExpression)=> utl = untypedListExpression {expr = utl;}
	;

untypedListExpression returns [ListExpression expr = null]
@init{
	List<IRutaExpression> list = new ArrayList<IRutaExpression>();
}	:
	LCURLY (e = argument {list.add(e);} (COMMA e = argument {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createUntypedListExpression(list);}
	;

booleanListExpression returns [BooleanListExpression expr = null]
	:
	e = simpleBooleanListExpression {expr = e;}
	;

simpleBooleanListExpression returns [BooleanListExpression expr = null]
@init{
	List<IBooleanExpression> list = new ArrayList<IBooleanExpression>();
}	:
	LCURLY (e = simpleBooleanExpression {list.add(e);} (COMMA e = simpleBooleanExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createBooleanListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "BOOLEANLIST")}? var = Identifier 
	{expr = ExpressionFactory.createReferenceBooleanListExpression(var);}
	;


intListExpression returns [NumberListExpression expr = null]
	:
	e = simpleIntListExpression {expr = e;}
	;

simpleIntListExpression returns [NumberListExpression expr = null]
@init{
	List<INumberExpression> list = new ArrayList<INumberExpression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createNumberListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "INTLIST")}? var = Identifier 
	{expr = ExpressionFactory.createReferenceIntListExpression(var);}
	;


numberListExpression returns [NumberListExpression expr = null]
	:
	(e1 = doubleListExpression)=> e1 = doubleListExpression {expr = e1;}
	|
	(e1 = floatListExpression)=> e1 = floatListExpression {expr = e1;}
	|
	e2 = intListExpression {expr = e2;}
	;
	
doubleListExpression returns [NumberListExpression expr = null]
	:
	e = simpleDoubleListExpression {expr = e;}
	;

simpleDoubleListExpression returns [NumberListExpression expr = null]
@init{
	List<INumberExpression> list = new ArrayList<INumberExpression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createNumberListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "DOUBLELIST")}? var = Identifier 
	{expr = ExpressionFactory.createReferenceDoubleListExpression(var);}
	;

	
floatListExpression returns [NumberListExpression expr = null]
	:
	e = simpleFloatListExpression {expr = e;}
	;

simpleFloatListExpression returns [NumberListExpression expr = null]
@init{
	List<INumberExpression> list = new ArrayList<INumberExpression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createNumberListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "FLOATLIST")}? var = Identifier 
	{expr = ExpressionFactory.createReferenceFloatListExpression(var);}
	;

stringListExpression returns [StringListExpression expr = null]
	:
	e = simpleStringListExpression {expr = e;}
	;

simpleStringListExpression returns [StringListExpression expr = null]
@init{
	List<IStringExpression> list = new ArrayList<IStringExpression>();
}	:
	LCURLY (e = simpleStringExpression {list.add(e);} (COMMA e = simpleStringExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createStringListExpression(list);}	
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "STRINGLIST")}? var = Identifier 
	{expr = ExpressionFactory.createReferenceStringListExpression(var);}
	;


typeListExpression returns [TypeListExpression expr = null]
	:
	e = simpleTypeListExpression {expr = e;}
	;

simpleTypeListExpression returns [TypeListExpression expr = null]
@init{
	List<TypeExpression> list = new ArrayList<TypeExpression>();
}	:
	LCURLY (e = simpleTypeExpression {list.add(e);} (COMMA e = simpleTypeExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createTypeListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "TYPELIST")}? var = Identifier 
	{expr = ExpressionFactory.createReferenceTypeListExpression(var);}
	;

typeMatchExpression returns [IRutaExpression expr = null]
options {
	backtrack = true;
}
	:
	(typeFunction)=> tf = typeFunction {expr = tf;}
	|
	(matchReference)=> mr = matchReference {expr = mr;}
	;

matchReference returns [MatchReference mr = null]
	:
	ref = dottedId ((comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument)?
	{mr = ExpressionFactory.createMatchReference(ref, comp, arg);}
	;

typeExpression returns [TypeExpression type = null]
options {
	backtrack = true;
}
	:
	tf = typeFunction {type = tf;}
	| st = simpleTypeExpression {type = st;}
	;
	

// not checked
typeFunction returns [TypeExpression expr = null]
	:
	(e = externalTypeFunction)=> e = externalTypeFunction {expr = e;}
	;

// not checked
externalTypeFunction returns [TypeExpression expr = null]
	:
	{isTypeFunctionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN
	args = varArgumentList?	RPAREN
	{
		expr = external.createExternalTypeFunction(id, args);
	}
	;

simpleTypeExpression returns [TypeExpression type = null]
	:
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "TYPE")}? var = Identifier 
	{type = ExpressionFactory.createReferenceTypeExpression(var);}
	|
	at = annotationType
	{type = ExpressionFactory.createSimpleTypeExpression(at, $blockDeclaration::env);}
	;

featureExpression returns [FeatureExpression feat = null]
@init{
List<Token> fs = new ArrayList<Token>();
TypeExpression te = null;
}
	:
	match = dottedId2 
	{
	MatchReference mr = ExpressionFactory.createMatchReference(match, null, null);
	feat = ExpressionFactory.createFeatureExpression(mr, $blockDeclaration::env);
	}
	;

featureMatchExpression returns [FeatureMatchExpression fme = null]
	:
	match = dottedId2 ((comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument)?
	{
	MatchReference mr = ExpressionFactory.createMatchReference(match, comp, arg);
	fme = ExpressionFactory.createFeatureMatchExpression(mr, $blockDeclaration::env);}
	;

featureMatchExpression2 returns [FeatureMatchExpression fme = null]
	:
	match = dottedId2 (comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument
	{
	MatchReference mr = ExpressionFactory.createMatchReference(match, comp, arg);
	fme = ExpressionFactory.createFeatureMatchExpression(mr, $blockDeclaration::env);}
	;


featureAssignmentExpression returns [FeatureMatchExpression fme = null]
	:
	match = dottedId2 op = ASSIGN_EQUAL arg = argument
	{
	MatchReference mr = ExpressionFactory.createMatchReference(match, op, arg);
	fme = ExpressionFactory.createFeatureMatchExpression(mr, $blockDeclaration::env);
	}
	;
	
	
variable returns [Token var = null]
	:
	{isVariable($blockDeclaration::env, input.LT(1).getText())}? v = Identifier {var = v;}
	;

listVariable returns [Token var = null]
	:
	{isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "BOOLEANLIST")
	||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "INTLIST")
	||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "DOUBLELIST")
	||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "FLOATLIST")
	||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "STRINGLIST")
	||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "TYPELIST")
	}? v = Identifier {var = v;}
	;


//typeExpressionOr returns [TypeExpression type = null]
//@init {List<TypeExpression> exprs = new ArrayList<TypeExpression>();}
//	:
//	LBRACK e = typeExpressionAnd{exprs.add(e);} ( COMMA e = typeExpressionAnd{exprs.add(e);} )* RBRACK
//	{type = ExpressionFactory.createOrTypeExpression(exprs);}
//	;

//typeExpressionAnd returns [TypeExpression type = null]
//@init {List<TypeExpression> exprs = new ArrayList<TypeExpression>();}
//	:
//	LBRACK e = simpleTypeExpression{exprs.add(e);} ( SEMI e = simpleTypeExpression{exprs.add(e);} )* RBRACK
//	{type = ExpressionFactory.createAndTypeExpression(exprs);}
//	;

quantifierPart returns [RuleElementQuantifier quantifier = null]
	:
	 STAR q = QUESTION? 
	 {if(q != null) {quantifier = RutaScriptFactory.createStarReluctantQuantifier();} 
	 	else{quantifier = RutaScriptFactory.createStarGreedyQuantifier();}}
	| PLUS q = QUESTION?
	 {if(q != null) {quantifier = RutaScriptFactory.createPlusReluctantQuantifier();}
	 else {quantifier = RutaScriptFactory.createPlusGreedyQuantifier();}}
	| QUESTION q = QUESTION? 
	 {if(q != null) {quantifier = RutaScriptFactory.createQuestionReluctantQuantifier();} 
	 else {quantifier = RutaScriptFactory.createQuestionGreedyQuantifier();}}
	| LBRACK min = numberExpression (comma = COMMA (max = numberExpression)?)? RBRACK q = QUESTION?
	 {if(q != null) {quantifier = RutaScriptFactory.createMinMaxReluctantQuantifier(min,max,comma);} 
	 else {quantifier = RutaScriptFactory.createMinMaxGreedyQuantifier(min,max,comma);}}	
	;


condition  returns [AbstractRutaCondition result = null]
	:
	(
	c = conditionAnd
	| c = conditionContains
	| c = conditionContextCount
	| c = conditionCount
	| c = conditionCurrentCount
	| c = conditionInList
	| c = conditionLast
	| c = conditionMofN
	| c = conditionNear
	| c = conditionNot
	| c = conditionOr
	| c = conditionPartOf
	| c = conditionPosition
	| c = conditionRegExp
	| c = conditionScore
	| c = conditionTotalCount
	| c = conditionVote
	| c = conditionIf
	| c = conditionFeature
	| c = conditionParse
	| c = conditionIs
	| c = conditionBefore
	| c = conditionAfter 
	| c = conditionStartsWith
	| c = conditionEndsWith
	| c = conditionPartOfNeq
	| c = conditionSize	
	
	| (featureMatchExpression2)=> f = featureMatchExpression2 {c = ConditionFactory.createImplicitCondition(f);}
	| (booleanExpression)=> b = booleanExpression {c = ConditionFactory.createImplicitCondition(b);}
	| (c = externalCondition)=> c = externalCondition
	
//	| c = variableCondition
	) {result = c;}
	;


//variableCondition returns [AbstractRutaCondition condition = null]
//	:		
//	
//	id = Identifier
//	{
//		condition = ConditionFactory.createConditionVariable(id);
//	}
//	;

externalCondition returns [AbstractRutaCondition condition = null]
	:		
	{isConditionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN args = varArgumentList?	RPAREN
	{
		condition = external.createExternalCondition(id, args);
	}
	;
	
conditionAnd returns [AbstractRutaCondition cond = null]
    :   
    AND LPAREN conds = conditions RPAREN 
    {cond = ConditionFactory.createConditionAnd(conds, $blockDeclaration::env);}
    ;
    
conditionContains returns [AbstractRutaCondition cond = null]
 options {
	backtrack = true;
}
    :   
    CONTAINS LPAREN (type = typeExpression | list = listExpression COMMA a = argument) 
    (COMMA min = numberExpression COMMA max = numberExpression (COMMA percent = booleanExpression)?)? RPAREN
    {if(type != null) {cond = ConditionFactory.createConditionContains(type, min, max, percent,$blockDeclaration::env);}
    else {cond = ConditionFactory.createConditionContains(list,a, min, max, percent, $blockDeclaration::env);};}
    ;
conditionContextCount returns [AbstractRutaCondition cond = null]
    :   
    CONTEXTCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionContextCount(type, min, max, var, $blockDeclaration::env);}
    ;
conditionCount returns [AbstractRutaCondition cond = null]
 options {
	backtrack = true;
}
    :   
    COUNT LPAREN type = listExpression COMMA a = argument (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionCount(type, a, min, max, var,$blockDeclaration::env);}
    |
    COUNT LPAREN list = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionCount(list, min, max, var,$blockDeclaration::env);}   
    ;
conditionTotalCount returns [AbstractRutaCondition cond = null]
    :   
    TOTALCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)?
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionTotalCount(type, min, max, var, $blockDeclaration::env);}
    ;
conditionCurrentCount returns [AbstractRutaCondition cond = null]
    :   
    CURRENTCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionCurrentCount(type, min, max, var,$blockDeclaration::env);}
    ;
    
conditionInList returns [AbstractRutaCondition cond = null]
 options {
	backtrack = true;
}
    :
    INLIST LPAREN ((list2 = stringListExpression)=>list2 = stringListExpression | list1 = wordListExpression) 
    (COMMA arg = stringExpression)?
    //(COMMA dist = numberExpression (COMMA rel = booleanExpression)?)? 
    RPAREN
    {if(list1 != null) {cond = ConditionFactory.createConditionInList(list1, arg,$blockDeclaration::env);}
    else {cond = ConditionFactory.createConditionInList(list2, arg,$blockDeclaration::env);};}
    ;

    
conditionLast returns [AbstractRutaCondition cond = null]
    :   
    LAST LPAREN type = typeExpression RPAREN
    {cond = ConditionFactory.createConditionLast(type, $blockDeclaration::env);}    
    ;
    
    
conditionMofN returns [AbstractRutaCondition cond = null]
    :   
    MOFN LPAREN min = numberExpression COMMA max = numberExpression COMMA conds = conditions RPAREN
    {cond = ConditionFactory.createConditionMOfN(conds, min, max, $blockDeclaration::env);} 
    ;

conditionNear returns [AbstractRutaCondition cond = null]
    :   
    NEAR LPAREN type = typeExpression COMMA min = numberExpression COMMA max = numberExpression (COMMA direction = booleanExpression (COMMA filtered = booleanExpression)?)? RPAREN
    {cond = ConditionFactory.createConditionNear(type, min, max, direction, filtered, $blockDeclaration::env);} 
    ;
conditionNot returns [AbstractRutaCondition cond = null]
    :   
    ((MINUS c = condition) |  (NOT LPAREN c = condition RPAREN))
    {cond = ConditionFactory.createConditionNot(c, $blockDeclaration::env);}    
    ;
conditionOr returns [AbstractRutaCondition cond = null]
    :   
    OR LPAREN conds = conditions RPAREN
    {cond = ConditionFactory.createConditionOr(conds, $blockDeclaration::env);}
    ;
conditionPartOf returns [AbstractRutaCondition cond = null]
    :
    PARTOF LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionPartOf(type1, type2, $blockDeclaration::env);}
    ;
conditionPartOfNeq returns [AbstractRutaCondition cond = null]
    :
    PARTOFNEQ LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionPartOfNeq(type1, type2, $blockDeclaration::env);}
    ;

conditionPosition returns [AbstractRutaCondition cond = null]
    :   
    POSITION LPAREN type = typeExpression COMMA pos = numberExpression (COMMA rel = booleanExpression)? RPAREN
    {cond = ConditionFactory.createConditionPosition(type, pos, rel, $blockDeclaration::env);}
    ;
conditionRegExp returns [AbstractRutaCondition cond = null]
    :
    REGEXP LPAREN 
    ((stringExpression COMMA stringExpression)=> v = stringExpression COMMA pattern = stringExpression
    | pattern = stringExpression
    )
    (COMMA caseSensitive = booleanExpression)? RPAREN
    {cond = ConditionFactory.createConditionRegExp(v, pattern, caseSensitive, $blockDeclaration::env);}    
    ;

conditionScore returns [AbstractRutaCondition cond = null]
    :
    SCORE LPAREN min = numberExpression (COMMA max = numberExpression
    (COMMA var = numberVariable)?)?  RPAREN
    {cond = ConditionFactory.createConditionScore(min, max, var, $blockDeclaration::env);}
    ;


conditionVote returns [AbstractRutaCondition cond = null]
    :   
    VOTE LPAREN type1 = typeExpression COMMA type2 = typeExpression RPAREN
    {cond = ConditionFactory.createConditionVote(type1, type2, $blockDeclaration::env);}
    ;
    
conditionIf returns [AbstractRutaCondition cond = null]
    :   
    IF LPAREN e = booleanExpression RPAREN
    {cond = ConditionFactory.createConditionIf(e, $blockDeclaration::env);}
    ;

conditionFeature returns [AbstractRutaCondition cond = null]
    :   
    FEATURE LPAREN se = stringExpression COMMA v = argument RPAREN
    {cond = ConditionFactory.createConditionFeature(se, v, $blockDeclaration::env);}
    ;   

conditionParse returns [AbstractRutaCondition cond = null]
    :
    PARSE LPAREN {isVariable($blockDeclaration::env,input.LT(1).getText())}? id = Identifier 
    (COMMA locale = stringExpression)?
    RPAREN
    {cond = ConditionFactory.createConditionParse(id, locale, $blockDeclaration::env);}
    ;

conditionIs returns [AbstractRutaCondition cond = null]
    :
    IS LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionIs(type1, type2, $blockDeclaration::env);}
    ;

conditionBefore returns [AbstractRutaCondition cond = null]
    :
    BEFORE LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionBefore(type1,type2, $blockDeclaration::env);}
    ;

conditionAfter returns [AbstractRutaCondition cond = null]
    :
    AFTER LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionAfter(type1,type2,$blockDeclaration::env);}
    ;

conditionStartsWith returns [AbstractRutaCondition cond = null]
    :
    STARTSWITH LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionStartsWith(type1,type2, $blockDeclaration::env);}
    ;

conditionEndsWith returns [AbstractRutaCondition cond = null]
    :
    ENDSWITH LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionEndsWith(type1,type2,$blockDeclaration::env);}
    ;

conditionSize returns [AbstractRutaCondition cond = null]
    :
    SIZE LPAREN list = listExpression (COMMA min = numberExpression COMMA max = numberExpression)? (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionSize(list, min, max, var,$blockDeclaration::env);}
    ;

action  returns [AbstractRutaAction result = null]
	:
	(
	a = actionColor
	| a = actionDel
	| a = actionLog
	| a = actionMark
	| a = actionMarkScore
	| a = actionMarkFast
	| a = actionMarkLast
	| a = actionMarkFirst
	| a = actionReplace
	| a = actionFilterType
	| a = actionRetainType
	| a = actionCreate
	| a = actionFill
	| a = actionCall
	| a = actionAssign
	| a = actionSetFeature
	| a = actionGetFeature
	| a = actionUnmark
	| a = actionUnmarkAll
	| a = actionTransfer
	| a = actionMarkOnce
	| a = actionTrie
	| a = actionGather
	| a = actionExec
	| a = actionMarkTable
	| a = actionAdd
	| a = actionRemove
	| a = actionRemoveDuplicate
	| a = actionMerge
	| a = actionGet
	| a = actionGetList
	| a = actionMatchedText
	| a = actionClear
	| a = actionShift
	| a = actionConfigure
	| a = actionDynamicAnchoring
	| a = actionGreedyAnchoring
	| a = actionTrim 
	| a = actionAddRetainType
	| a = actionRemoveRetainType
	| a = actionAddFilterType
	| a = actionRemoveFilterType
	| (externalAction)=> a = externalAction
	| (featureAssignmentExpression)=> fae = featureAssignmentExpression {a = ActionFactory.createAction(fae);}
	| (typeExpression)=> te = typeExpression {a = ActionFactory.createAction(te);}
	
//	| a = variableAction
	) {result = a;}
	;
rawAction  returns [AbstractRutaAction result = null]
	:
	(
	a = actionColor
	| a = actionDel
	| a = actionLog
	| a = actionMark
	| a = actionMarkScore
	| a = actionMarkFast
	| a = actionMarkLast
	| a = actionMarkFirst
	| a = actionReplace
	| a = actionFilterType
	| a = actionRetainType
	| a = actionCreate
	| a = actionFill
	| a = actionCall
	| a = actionAssign
	| a = actionSetFeature
	| a = actionGetFeature
	| a = actionUnmark
	| a = actionUnmarkAll
	| a = actionTransfer
	| a = actionMarkOnce
	| a = actionTrie
	| a = actionGather
	| a = actionExec
	| a = actionMarkTable
	| a = actionAdd
	| a = actionRemove
	| a = actionRemoveDuplicate
	| a = actionMerge
	| a = actionGet
	| a = actionGetList
	| a = actionMatchedText
	| a = actionClear
	| a = actionShift
	| a = actionConfigure
	| a = actionDynamicAnchoring
	| a = actionGreedyAnchoring
	| a = actionTrim 
	| a = actionAddRetainType
	| a = actionRemoveRetainType
	| a = actionAddFilterType
	| a = actionRemoveFilterType
	| (externalAction)=> a = externalAction
	
//	| a = variableAction
	) {result = a;}
	;	

//variableAction returns [AbstractRutaAction action = null]
//	:		
//	
//	id = Identifier
//	{
//		action = ActionFactory.createActionVariable(id);
//	}
//	;


externalAction returns [AbstractRutaAction action = null]
	:		
	{isActionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN args = varArgumentList?	RPAREN
	{
		action = external.createExternalAction(id, args);
	}
	;



actionCreate returns [AbstractRutaAction action = null]
@init {
	Map<IStringExpression, IRutaExpression> map = new HashMap<IStringExpression, IRutaExpression>();
    	List<INumberExpression> indexes = new ArrayList<INumberExpression>();
}
    :
    name = CREATE LPAREN structure = typeExpression 
   (COMMA 
    (
    (index = numberExpression)=>index = numberExpression {indexes.add(index);} ((COMMA index = numberExpression)=> (COMMA index = numberExpression) {indexes.add(index);})* COMMA)?
    (fname = stringExpression ASSIGN_EQUAL obj1 = argument {map.put(fname,obj1);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = argument {map.put(fname,obj1);})*)?
    )? RPAREN
    {action = ActionFactory.createCreateAction(structure, map, indexes, $blockDeclaration::env);}
    ;


actionMarkTable returns [AbstractRutaAction action = null]
@init {
	Map<IStringExpression, INumberExpression> map = new HashMap<IStringExpression, INumberExpression>();
}
    :
    name = MARKTABLE LPAREN 
    structure = typeExpression COMMA 
    index = numberExpression COMMA
    table = wordTableExpression 
    COMMA  ( (ignoreCase =booleanExpression)=>ignoreCase = booleanExpression 
    COMMA ignoreLength = numberExpression 
    COMMA ignoreChar = stringExpression
    COMMA maxIgnoreChar = numberExpression COMMA)?
     key = stringExpression ASSIGN_EQUAL value = numberExpression{map.put(key,value);} 
    ( COMMA key = stringExpression ASSIGN_EQUAL value = numberExpression{map.put(key,value);} )*

    RPAREN


    {action = ActionFactory.createMarkTableAction(structure, index, table, map, ignoreCase, ignoreLength, ignoreChar, maxIgnoreChar,$blockDeclaration::env);}
    ;
 
actionGather returns [AbstractRutaAction action = null]
@init {
	Map<IStringExpression, IRutaExpression> map = new HashMap<IStringExpression, IRutaExpression>();
    	List<INumberExpression> indexes = new ArrayList<INumberExpression>();
}
    :
    name = GATHER LPAREN structure = typeExpression 
   (COMMA 
    ((index = numberExpression)=>index = numberExpression {indexes.add(index);} ((COMMA index = numberExpression)=>(COMMA index = numberExpression) {indexes.add(index);})* COMMA)?
    (fname = stringExpression ASSIGN_EQUAL (obj1 = numberExpression | obj2 = numberListExpression) {map.put(fname,obj1 != null? obj1 : obj2);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL (obj1 = numberExpression | obj2 = numberListExpression) {map.put(fname,obj1 != null? obj1 : obj2);})*)?
    )? RPAREN
    {action = ActionFactory.createGatherAction(structure, map, indexes, $blockDeclaration::env);}
    ;  

  

actionFill returns [AbstractRutaAction action = null]
@init {
Map<IStringExpression, IRutaExpression> map = new HashMap<IStringExpression, IRutaExpression>();
}
    :
    FILL LPAREN type = typeExpression (COMMA fname = stringExpression ASSIGN_EQUAL 
    (
    obj1 = argument{map.put(fname,obj1);} 
    )
    )+ RPAREN
    {action = ActionFactory.createFillAction(type, map, $blockDeclaration::env);}
    ;
    
actionColor returns [AbstractRutaAction action = null]
    :   
    COLOR LPAREN type = typeExpression 
    
    COMMA 
    bgcolor = stringExpression 
    (COMMA
    fgcolor = stringExpression 
    (COMMA
    selected = booleanExpression)?)?
    RPAREN
    {action = ActionFactory.createColorAction(type, bgcolor, fgcolor, selected, $blockDeclaration::env);}
    ;

actionDel returns [AbstractRutaAction action = null]
    :   
    DEL
    {action = ActionFactory.createDelAction($blockDeclaration::env);}
    ;
        
actionLog returns [AbstractRutaAction action = null]
    :   
    LOG LPAREN lit = stringExpression (COMMA log = LogLevel)? RPAREN
    {action = ActionFactory.createLogAction(lit, log, $blockDeclaration::env);}
    ;

actionMark returns [AbstractRutaAction action = null]
@init {
List<INumberExpression> list = new ArrayList<INumberExpression>();
}
    :   
    MARK LPAREN 
    type = typeExpression
    (
        COMMA (index = numberExpression) => index = numberExpression
        {list.add(index);}
    )*
     RPAREN
    
    {action = ActionFactory.createMarkAction(null, type, list, $blockDeclaration::env);}
    ;

actionShift returns [AbstractRutaAction action = null]
@init {
List<INumberExpression> list = new ArrayList<INumberExpression>();
}
    :   
    SHIFT LPAREN 
    type = typeExpression
    (
        COMMA (index = numberExpression) => index = numberExpression
        {list.add(index);}
    )*
     RPAREN
    
    {action = ActionFactory.createShiftAction(type, list,$blockDeclaration::env);}
    ;


actionMarkScore returns [AbstractRutaAction action = null]
@init {
List<INumberExpression> list = new ArrayList<INumberExpression>();
}
    :   
    MARKSCORE LPAREN 
    score = numberExpression COMMA
    type = typeExpression
    (
        COMMA (index = numberExpression) => index = numberExpression
        {list.add(index);}
    )*
     RPAREN
    
    {action = ActionFactory.createMarkAction(score, type, list, $blockDeclaration::env);}
    ;

actionMarkOnce returns [AbstractRutaAction action = null]
@init {
List<INumberExpression> list = new ArrayList<INumberExpression>();
}
    :   
    MARKONCE LPAREN ((score = numberExpression) => score = numberExpression COMMA)? (type = typeExpression) => type = typeExpression
    (
        COMMA (index = numberExpression) => index = numberExpression
        {list.add(index);}
    )* RPAREN
    
    {action = ActionFactory.createMarkOnceAction(score, type, list,$blockDeclaration::env);}
    ;

actionMarkFast returns [AbstractRutaAction action = null]
    :   
    MARKFAST LPAREN type = typeExpression COMMA (list1 = wordListExpression | list2 = stringListExpression) 
    (COMMA ignore = booleanExpression (COMMA ignoreLength = numberExpression (COMMA ignoreWS = booleanExpression)?)?)? RPAREN
    {if(list1 != null) {
     action = ActionFactory.createMarkFastAction(type, list1, ignore, ignoreLength, ignoreWS, $blockDeclaration::env);
    } else {
     action = ActionFactory.createMarkFastAction(type, list2, ignore, ignoreLength, ignoreWS, $blockDeclaration::env);
    }
    }
    ;

actionMarkLast returns [AbstractRutaAction action = null]
    :   
    MARKLAST LPAREN type = typeExpression RPAREN
    {action = ActionFactory.createMarkLastAction(type, $blockDeclaration::env);}
    ;
    
actionMarkFirst returns [AbstractRutaAction action = null]
    :   
    MARKFIRST LPAREN type = typeExpression RPAREN
    {action = ActionFactory.createMarkFirstAction(type, $blockDeclaration::env);}
    ;

actionReplace returns [AbstractRutaAction action = null]
    :   
    REPLACE LPAREN lit = stringExpression RPAREN
    {action = ActionFactory.createReplaceAction(lit, $blockDeclaration::env);}
    ;
    
  

actionRetainType returns [AbstractRutaAction action = null]
@init {
List<TypeExpression> list = new ArrayList<TypeExpression>();
}
    :   
    RETAINTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)?
    {action = ActionFactory.createRetainTypeAction(list, $blockDeclaration::env);}
    ;   
    
 

actionFilterType returns [AbstractRutaAction action = null]
@init {
List<TypeExpression> list = new ArrayList<TypeExpression>();
}
    :   
    FILTERTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)?
    {action = ActionFactory.createFilterTypeAction(list,$blockDeclaration::env);}
    ;       

actionCall returns [AbstractRutaAction action = null]
    :
    CALL LPAREN ns = dottedIdentifier RPAREN
    {action = ActionFactory.createCallAction(ns, $blockDeclaration::env);}
    ;


actionConfigure returns [AbstractRutaAction action = null]
@init {
	Map<IStringExpression, IRutaExpression> map = new HashMap<IStringExpression, IRutaExpression>();
}

    :
    CONFIGURE LPAREN ns = dottedIdentifier  
   COMMA 
   fname = stringExpression ASSIGN_EQUAL obj1 = argument {map.put(fname,obj1);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = argument {map.put(fname,obj1);})*
    RPAREN
    {action = ActionFactory.createConfigureAction(ns, map, $blockDeclaration::env);}
    ;


actionExec returns [AbstractRutaAction action = null]
    :
    EXEC LPAREN ((stringExpression)=> view = stringExpression COMMA)? ns = dottedIdentifier (COMMA tl = typeListExpression)? RPAREN
    {action = ActionFactory.createExecAction(ns, tl, view, $blockDeclaration::env);}
    ;    
    
actionAssign returns [AbstractRutaAction action = null]
    :
    name = ASSIGN LPAREN
    (
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "TYPE")}? 
        nv = Identifier COMMA e1 = typeExpression 
        {action = ActionFactory.createAssignAction(nv, e1,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "BOOLEAN")}? 
        nv = Identifier COMMA e2 = booleanExpression 
        {action = ActionFactory.createAssignAction(nv, e2,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "STRING")}? 
        nv = Identifier COMMA e3 = stringExpression 
        {action = ActionFactory.createAssignAction(nv, e3,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "INT")}? 
        nv = Identifier COMMA e4 = numberExpression 
        {action = ActionFactory.createAssignAction(nv, e4,$blockDeclaration::env);}
    |
     {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "FLOAT")}? 
        nv = Identifier COMMA e6 = numberExpression 
        {action = ActionFactory.createAssignAction(nv, e6,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "DOUBLE")}? 
        nv = Identifier COMMA e5 = numberExpression 
        {action = ActionFactory.createAssignAction(nv, e5,$blockDeclaration::env);}
    ) RPAREN
    ;

actionSetFeature returns [AbstractRutaAction action = null]
    :   
    name = SETFEATURE LPAREN f = stringExpression COMMA v = argument RPAREN
    {action = ActionFactory.createSetFeatureAction(f, v, $blockDeclaration::env);}
    ;


actionGetFeature returns [AbstractRutaAction action = null]
    :   
    name = GETFEATURE LPAREN f = stringExpression COMMA v = variable RPAREN
    {action = ActionFactory.createGetFeatureAction(f, v, $blockDeclaration::env);}
    ;

//unknown
actionDynamicAnchoring returns [AbstractRutaAction action = null]
    :
    name = DYNAMICANCHORING LPAREN active = booleanExpression 
    (COMMA penalty = numberExpression 
    (COMMA factor = numberExpression)?)? 
    {action = ActionFactory.createDynamicAnchoringAction(active, penalty, factor, $blockDeclaration::env);}
    RPAREN
    ;

actionGreedyAnchoring returns [AbstractRutaAction action = null]
    :
    name = GREEDYANCHORING LPAREN active = booleanExpression (COMMA active2 = booleanExpression )?
    {action = ActionFactory.createGreedyAnchoringAction(active, active2, $blockDeclaration::env);}
    RPAREN
    ;

actionTrim returns [AbstractRutaAction action = null]
@init {
  List<TypeExpression> types = new ArrayList<TypeExpression>();
}
    :
    name = TRIM LPAREN 
    (
    typeList = typeListExpression
    |
    t1 = typeExpression {types.add(t1);} (COMMA t2 = typeExpression {types.add(t2);})*
    )
    
    {action = ActionFactory.createTrimAction(types, typeList, $blockDeclaration::env);}
    RPAREN
    ;



actionUnmark returns [AbstractRutaAction action = null]
@init {
List<INumberExpression> list = new ArrayList<INumberExpression>();
}
    :
    name = UNMARK LPAREN 
    
    f = typeExpression 

    (COMMA 
    (
  	(b = booleanExpression)=> b = booleanExpression
  	|
  	(
  	index = numberExpression {list.add(index);} 
  	(COMMA index = numberExpression {list.add(index);})*
  	)
    )
      
    )?

     RPAREN
    {action = ActionFactory.createUnmarkAction(f, list, b,$blockDeclaration::env);}
    ;


actionUnmarkAll returns [AbstractRutaAction action = null]
    :
    name = UNMARKALL LPAREN f = typeExpression 
    (COMMA list = typeListExpression)? RPAREN
    {action = ActionFactory.createUnmarkAllAction(f, list, $blockDeclaration::env);}
    ;

actionTransfer returns [AbstractRutaAction action = null]
    :
    name = TRANSFER LPAREN f = typeExpression RPAREN
    {action = ActionFactory.createTransferAction(f, $blockDeclaration::env);}
    ;

actionTrie returns [AbstractRutaAction action = null]
@init {
Map<IStringExpression, IRutaExpression> map = new HashMap<IStringExpression, IRutaExpression>();
}
    :
    name = TRIE LPAREN
    key = stringExpression ASSIGN_EQUAL 
    (value = typeExpression{map.put(key,value);} | listValue = untypedListExpression {map.put(key,listValue);}) 
    (COMMA key = stringExpression ASSIGN_EQUAL 
    (value = typeExpression{map.put(key,value);} | listValue = untypedListExpression {map.put(key,listValue);}) )*
    COMMA list = wordListExpression 
    COMMA ignoreCase = booleanExpression 
    COMMA ignoreLength = numberExpression 
    COMMA edit = booleanExpression 
    COMMA distance = numberExpression 
    COMMA ignoreChar = stringExpression
    RPAREN
    //TODO cost parameter
    {action = ActionFactory.createTrieAction(list, map, ignoreCase, ignoreLength, edit, distance, ignoreChar,$blockDeclaration::env);}
    ;

actionAdd returns [AbstractRutaAction action = null]
@init{
	List<IRutaExpression> list = new ArrayList<IRutaExpression>();
} 
    :
    name = ADD LPAREN f = listVariable (COMMA a = argument {list.add(a);})+ RPAREN
    {action = ActionFactory.createAddAction(f, list, $blockDeclaration::env);}
    ;

actionRemove returns [AbstractRutaAction action = null]
@init{
	List<IRutaExpression> list = new ArrayList<IRutaExpression>();
} 
    :
    name = REMOVE LPAREN f = listVariable (COMMA a = argument {list.add(a);})+ RPAREN
    {action = ActionFactory.createRemoveAction(f, list, $blockDeclaration::env);}
    ;
 
actionRemoveDuplicate returns [AbstractRutaAction action = null]
    :
    name = REMOVEDUPLICATE LPAREN f = listVariable RPAREN
    {action = ActionFactory.createRemoveDuplicateAction(f,$blockDeclaration::env);}
    ; 
    
actionMerge returns [AbstractRutaAction action = null]
@init{
	List<ListExpression> list = new ArrayList<ListExpression>();
} 
    :
    name = MERGE LPAREN join = booleanExpression COMMA t = listVariable COMMA f = listExpression {list.add(f);} (COMMA f = listExpression {list.add(f);})+ RPAREN
    {action = ActionFactory.createMergeAction(join, t, list,$blockDeclaration::env);}
    ;

actionGet returns [AbstractRutaAction action = null]
    :
    name = GET LPAREN f = listExpression COMMA var = variable COMMA op = stringExpression RPAREN
    {action = ActionFactory.createGetAction(f, var, op,$blockDeclaration::env);}
    ;

actionGetList returns [AbstractRutaAction action = null]
    :
    name = GETLIST LPAREN var = listVariable COMMA op = stringExpression RPAREN
    {action = ActionFactory.createGetListAction(var, op,$blockDeclaration::env);}
    ;

actionMatchedText returns [AbstractRutaAction action = null]
@init {
List<INumberExpression> list = new ArrayList<INumberExpression>();
}
    :   
    MATCHEDTEXT LPAREN 
    var = variable
    (
        COMMA index = numberExpression
        {list.add(index);}
    )*
     RPAREN
    
    {action = ActionFactory.createMatchedTextAction(var, list, $blockDeclaration::env);}
    ;

actionClear returns [AbstractRutaAction action = null]
    :
    name = CLEAR LPAREN var = listVariable RPAREN
    {action = ActionFactory.createClearAction(var, $blockDeclaration::env);}
    ;

actionAddRetainType returns [AbstractRutaAction action = null]
@init {
List<TypeExpression> list = new ArrayList<TypeExpression>();
}
    :
    ADDRETAINTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)
    {action = ActionFactory.createAddRetainTypeAction(list,$blockDeclaration::env);}
    ;     

actionRemoveRetainType returns [AbstractRutaAction action = null]
@init {
List<TypeExpression> list = new ArrayList<TypeExpression>();
}
    :
    REMOVERETAINTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)
    {action = ActionFactory.createRemoveRetainTypeAction(list,$blockDeclaration::env);}
    ;   

actionAddFilterType returns [AbstractRutaAction action = null]
@init {
List<TypeExpression> list = new ArrayList<TypeExpression>();
}
    :
    ADDFILTERTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)
    {action = ActionFactory.createAddFilterTypeAction(list,$blockDeclaration::env);}
    ;   

actionRemoveFilterType returns [AbstractRutaAction action = null]
@init {
List<TypeExpression> list = new ArrayList<TypeExpression>();
}
    :
    REMOVEFILTERTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)
    {action = ActionFactory.createRemoveFilterTypeAction(list,$blockDeclaration::env);}
    ;     

varArgumentList returns [List args = new ArrayList()]
	:
	arg = argument {args.add(arg);}(COMMA arg = argument {args.add(arg);})*
	;

argument returns [IRutaExpression expr = null]
options {
	backtrack = true;
}
	:
	(featureExpression)=> fe = featureExpression {expr = ExpressionFactory.createGenericFeatureExpression(fe);}
	| a2 = booleanExpression {expr = a2;}
	| a3 = numberExpression {expr = a3;}
	| a4 = stringExpression {expr = a4;}
	| (listExpression)=> l = listExpression {expr = l;}
	| a5 = nullExpression {expr = a5;}
	| a1 = typeExpression {expr = a1;}
	
	//(a2 = booleanExpression)=> a2 = booleanExpression {expr = a2;}
	//| (a3 = numberExpression)=> a3 = numberExpression {expr = a3;}
	//| (a4 = stringExpression)=> a4 = stringExpression {expr = a4;}
	//| (a1 = typeExpression)=> a1 = typeExpression {expr = a1;}
	;

nullExpression returns [IRutaExpression expr = null]
	:
	NULL {expr = ExpressionFactory.createNullExpression();}
	;


primitiveArgument returns [IRutaExpression expr = null]
options {
	backtrack = true;
}
	:
	 a4 = simpleStringExpression {expr = a4;}
	| a2 = simpleBooleanExpression {expr = a2;}
	| a3 = simpleNumberExpression {expr = a3;}
	| a1 = simpleTypeExpression {expr = a1;}
	//token = (
	//(booleanExpression[par]) => booleanExpression[par]
	//| (numberExpression[par]) => numberExpression[par]
	//| (stringExpression[par]) => stringExpression[par]
	//| (typeExpression[par]) => typeExpression[par]
	//)
	//{arg = token;}
	;	

dottedIdentifier returns [String idString = ""]
	:
	id = Identifier {idString += id.getText();}
	(
		dot = DOT {idString += dot.getText();}
		idn = Identifier {idString += idn.getText();}
	)*
	;

dottedIdentifier2 returns [String idString = ""]
	:
	id = Identifier {idString += id.getText();}
	(
		dot = (DOT|MINUS) {idString += dot.getText();}
		idn = Identifier {idString += idn.getText();}
	)*
	;

//snooze	
dottedId returns [Token token = null ]
@init {CommonToken ct = null;}
	:
	id = Identifier {
		ct = new CommonToken(id);
		}
	(
		dot = DOT {ct.setText(ct.getText() + dot.getText());}
		id = Identifier {ct.setStopIndex(getBounds(id)[1]);
		                 ct.setText(ct.getText() + id.getText());}
	)*
	{token = ct;
	 return token;}
	;
dottedId2 returns [Token token = null ]
@init {CommonToken ct = null;}
	:
	id = Identifier {
		ct = new CommonToken(id);
		}
	(
		dot = DOT {ct.setText(ct.getText() + dot.getText());}
		id = Identifier {ct.setStopIndex(getBounds(id)[1]);
		                 ct.setText(ct.getText() + id.getText());}
	)+
	{token = ct;
	 return token;}
	;

annotationType returns [Token ref = null]
	: 
	(
	did = dottedId {ref = did;}
	)
	;

wordListExpression returns [WordListExpression expr = null]
@init  {
List<IStringExpression> args = new ArrayList<IStringExpression>();
}
	:
	RESOURCE LPAREN name = dottedId (COMMA arg = stringExpression {args.add(arg);} )* RPAREN
	{expr = ExpressionFactory.createExternalWordListExpression(name, args);}
	|
	id = Identifier
	{expr = ExpressionFactory.createReferenceWordListExpression(id);}
	|
	path = RessourceLiteral
	{expr = ExpressionFactory.createLiteralWordListExpression(path);}
	;


wordTableExpression returns [WordTableExpression expr = null]
@init  {
List<IStringExpression> args = new ArrayList<IStringExpression>();
}
	:
	RESOURCE LPAREN name = dottedId (COMMA arg = stringExpression {args.add(arg);} )* RPAREN
	{expr = ExpressionFactory.createExternalWordTableExpression(name, args);}
	|
	id = Identifier
	{expr = ExpressionFactory.createReferenceWordTableExpression(id);}
	|
	path = RessourceLiteral
	{expr = ExpressionFactory.createLiteralWordTableExpression(path);}
	;

// not checked
numberFunction returns [INumberExpression expr = null]
	:
	(op=(EXP | LOGN | SIN | COS | TAN ) numExprP=numberExpressionInPar)
	{expr = ExpressionFactory.createComposedNumberExpression(numExprP,op);}
	| op = POW LPAREN n1 = numberExpression COMMA n2 = numberExpression RPAREN
	{expr = ExpressionFactory.createComposedNumberExpression(n1,op, n2);}
	//| {root = ExpressionFactory.createNumberFunction(numExprP,op)}
	| (e = externalNumberFunction)=> e = externalNumberFunction {expr = e;}
	;


// not checked
externalNumberFunction returns [INumberExpression expr = null]
	:
	{isNumberFunctionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN
	args = varArgumentList? RPAREN
	{
		expr = external.createExternalNumberFunction(id, args);
	}
	;

numberVariable returns [Token ref = null]
	:
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "INT")}? token1 = Identifier {ref = token1;}
	| {isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "DOUBLE")}? token2 = Identifier {ref = token2;}
	| {isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "FLOAT")}? token2 = Identifier {ref = token2;}
	;


additiveExpression returns [INumberExpression expr = null]
@init{List<INumberExpression> exprs = new ArrayList<INumberExpression>();
	List<Token> ops = new ArrayList<Token>();}
	:   
	e = multiplicativeExpression{exprs.add(e);} ((PLUS | MINUS)=> op = (PLUS | MINUS){ops.add(op);} e = multiplicativeExpression{exprs.add(e);} )*
	{expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);}
	;

multiplicativeExpression returns [INumberExpression expr = null]
@init{List<INumberExpression> exprs = new ArrayList<INumberExpression>();
	List<Token> ops = new ArrayList<Token>();}
	:	
	(e = simpleNumberExpression{exprs.add(e);} (( STAR | SLASH | PERCENT )=> op = ( STAR | SLASH | PERCENT ){ops.add(op);} e = simpleNumberExpression{exprs.add(e);} )*
	{expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);}
	|   e1 = numberFunction {expr = e1;})
	;

numberExpression returns [INumberExpression expr = null]
	:
	e = additiveExpression {expr = e;}
	;

numberExpressionInPar returns [INumberExpression expr = null]
	:
	LPAREN  e = additiveExpression RPAREN {expr = e;}
	;

simpleNumberExpression returns [INumberExpression expr = null]
	:
	(featureExpression)=> fe = featureExpression {expr = ExpressionFactory.createNumberFeatureExpression(fe);}	
	| m = MINUS? lit = DecimalLiteral {expr = ExpressionFactory.createIntegerExpression(lit,m);} 
	// TODO what about float numbers?
	| m = MINUS? lit = FloatingPointLiteral {expr = ExpressionFactory.createDoubleExpression(lit,m);}
	| m = MINUS? var = numberVariable {expr = ExpressionFactory.createReferenceNumberExpression(var,m);}
	| e = numberExpressionInPar {expr = e;}
	;
	
stringExpression returns [IStringExpression expr = null]
options {
	backtrack = true;
}
@init {
List<IStringExpression> exprs = new ArrayList<IStringExpression>();
}
	:
	(featureExpression)=> fe = featureExpression {expr = ExpressionFactory.createStringFeatureExpression(fe);}
	| e = simpleStringExpression {exprs.add(e);} 
	((PLUS)=>PLUS (e1 = simpleStringExpression {exprs.add(e1);} 
		| e2 = numberExpressionInPar {exprs.add(e2);}
		| be = simpleBooleanExpression {exprs.add(be);}
		| te = typeExpression {exprs.add(te);}
		| le = listExpression {exprs.add(le);}
		))*
	{expr = ExpressionFactory.createComposedStringExpression(exprs);}
	|(e = stringFunction)=> e = stringFunction{expr = e;} 
	;

// not checked
stringFunction returns [IStringExpression expr = null]
@init {List<IStringExpression> list = new ArrayList<IStringExpression>();}
:
	name = REMOVESTRING LPAREN var = variable (COMMA t = stringExpression {list.add(t);})+ RPAREN
	{expr = StringFunctionFactory.createRemoveFunction(var,list);}
	|
	(e = externalStringFunction)=> e = externalStringFunction {expr = e;}
	;

// not checked
externalStringFunction returns [IStringExpression expr = null]
	:
	{isStringFunctionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN
	args = varArgumentList?	RPAREN
	{
		expr = external.createExternalStringFunction(id, args);
	}
	;

simpleStringExpression returns [IStringExpression expr = null]
	: 
	lit = StringLiteral {expr = ExpressionFactory.createSimpleStringExpression(lit);} 
	| {isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "STRING")}? id = Identifier {expr = ExpressionFactory.createReferenceStringExpression(id);} 
	;


booleanExpression returns [IBooleanExpression expr = null]
	:
	(featureExpression)=> fe = featureExpression {expr = ExpressionFactory.createBooleanFeatureExpression(fe);}
	| (e = composedBooleanExpression)=> e = composedBooleanExpression {expr = e;}
	|sbE =  simpleBooleanExpression {expr = sbE;}
	
	;

simpleBooleanExpression returns [IBooleanExpression expr = null]
	:
	 e = literalBooleanExpression {expr = e;}
	| {isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "BOOLEAN")}? 
	id = Identifier {expr = ExpressionFactory.createReferenceBooleanExpression(id);} 
	;

// not checked
composedBooleanExpression returns [IBooleanExpression expr = null]

	:
	(e2 = booleanCompare)=> e2 = booleanCompare {expr = e2;}
	| (bte = booleanTypeExpression)=> bte = booleanTypeExpression{expr = bte;}
	| (bne = booleanNumberExpression)=> bne = booleanNumberExpression{expr = bne;}
	| e1 = booleanFunction {expr = e1;}
	| LPAREN ep = booleanExpression RPAREN {expr = ep;}
	;

// not checked
booleanFunction returns [IBooleanExpression expr = null]

	:
	(op = XOR LPAREN e1 = booleanExpression COMMA e2 = booleanExpression RPAREN)
	{expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}
	| (e = externalBooleanFunction)=> e = externalBooleanFunction {expr = e;}
	;

// not checked
externalBooleanFunction returns [IBooleanExpression expr = null]
	:
	{isBooleanFunctionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN
	args = varArgumentList? RPAREN
	{
		expr = external.createExternalBooleanFunction(id, args);
	}
	;

// not checked
booleanCompare returns [IBooleanExpression expr = null]
	:
	(e1 = simpleBooleanExpression op = (EQUAL | NOTEQUAL) e2 = booleanExpression)
	{expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}
	;


literalBooleanExpression returns  [IBooleanExpression expr = null]
	:
	v = TRUE {expr = ExpressionFactory.createSimpleBooleanExpression(v);} 
	| v = FALSE {expr = ExpressionFactory.createSimpleBooleanExpression(v);}
	;



booleanTypeExpression  returns  [IBooleanExpression expr = null]
	:
	e1 = typeExpression
	op = (EQUAL | NOTEQUAL)
	e2 = typeExpression
	{expr = ExpressionFactory.createBooleanTypeExpression(e1,op,e2);}
	;
	
booleanNumberExpression  returns  [IBooleanExpression expr = null]
	:
	//LPAREN
	e1 = numberExpression//{exprs.add(e);} 
	op = (LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL)//{ops.add(op);} 
	e2 = numberExpression//{exprs.add(e);}
	//RPAREN
	{expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);}
	;
	