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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
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
import org.apache.uima.ruta.RutaConstants;
import org.apache.uima.ruta.RutaEnvironment;
import org.apache.uima.ruta.RutaModule;
import org.apache.uima.ruta.RutaScriptFactory;
import org.apache.uima.ruta.RutaStatement;
import org.apache.uima.ruta.block.ForEachBlock;
import org.apache.uima.ruta.block.RutaBlock;
import org.apache.uima.ruta.block.RutaScriptBlock;
import org.apache.uima.ruta.expression.AnnotationTypeExpression;
import org.apache.uima.ruta.expression.ExpressionFactory;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.annotation.AbstractAnnotationListExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.AbstractBooleanListExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.AbstractNumberListExpression;
import org.apache.uima.ruta.expression.string.AbstractStringListExpression;
import org.apache.uima.ruta.expression.type.AbstractTypeListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.StringFunctionFactory;
import org.apache.uima.ruta.expression.type.ITypeExpression;
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
private RutaExternalFactory external;
private String namespace;
private String moduleName;
private ResourceManager resourceManager;
private UimaContext context;

private RutaDescriptorInformation descInfo;
private ExpressionFactory expressionFactory;
private RutaScriptFactory factory;
private ActionFactory actionFactory;
private ConditionFactory conditionFactory;

public void setScriptFactory(RutaScriptFactory factory) {
	this.factory = factory;
}

public void setExpressionFactory(ExpressionFactory factory) {
	this.expressionFactory = factory;
}

public void setActionFactory(ActionFactory factory) {
	this.actionFactory = factory;
}

public void setConditionFactory(ConditionFactory factory) {
	this.conditionFactory = factory;
}

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
	    String name = StringUtils.isBlank(moduleName) ? "unknown script" : moduleName;
   	    if (e instanceof NoViableAltException) {
	      NoViableAltException nvae = (NoViableAltException) e;
	      String msg = "Error in "+name+",  line " + nvae.line + ", \"" + text + "\": found no viable alternative";
	      emitErrorMessage(msg);
	    } else if (e instanceof MismatchedTokenException) {
	      MismatchedTokenException mte = (MismatchedTokenException) e;
	      int expectedInt = mte.expecting;
	      String stringExpected = expectedInt < 0 ? "'none'" : getTokenNames()[expectedInt];
	      String msg = "Error in "+name+", line " + line + ", \"" + text + "\": expected " + stringExpected
	              + ", but found " + stringFound;
	      emitErrorMessage(msg);
	    } else if (e instanceof MissingTokenException) {
	      MissingTokenException mte = (MissingTokenException) e;
    	      int missingType = mte.getMissingType();
    	      String stringMissing = getTokenNames()[missingType];
    	      String msg = "Error in "+name+",  line " + line + ", \"" + text + "\": missing " + stringMissing
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
		return parent.getEnvironment().isVariable(name) || isTemporaryVariable(name);
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
		return parent.getEnvironment().isVariableOfType(name,type) || isTemporaryVariable(name,type);
	}
	
	
	
	
	public void addType(RutaBlock parent, Token nameToken, Token parentTypeToken, List featureTypes,
          List featureNames) {
          
      
          
      String name = nameToken.getText();
      String parentType = "uima.tcas.Annotation";
      if(parentTypeToken != null) {
        parentType = parentTypeToken.getText();
	  }
	  
	  String resolvedType = name;
	  
	  if (!name.contains(".")) {
	    List<String> typeNameParts = new ArrayList<>();
	    
	    if(parent != null && !StringUtils.isBlank(parent.getNamespace())) {
	      typeNameParts.add(parent.getNamespace());
	    } else {
	      if(!StringUtils.isBlank(namespace)) {
            typeNameParts.add(namespace);
	      }
	      if(!StringUtils.isBlank(moduleName)) {
            typeNameParts.add(moduleName);
          }
        }
	    typeNameParts.add(name);
	    resolvedType = StringUtils.join(typeNameParts, ".");
	  }
      parent.getEnvironment().declareType(resolvedType);
      
      if(descInfo != null) {
        String descriptionString = null;
        if(StringUtils.isBlank(namespace)) {
          if(StringUtils.isBlank(moduleName)) {
            descriptionString = "Type defined in unknown script.";
	      } else {
            descriptionString = "Type defined in " + moduleName;
          }
		} else {
          descriptionString = "Type defined in " + parent.getNamespace();
        }
        descInfo.addType(resolvedType, descriptionString, parentType);
        if(featureTypes != null && featureNames != null) {
          for (int i = 0; i < featureTypes.size(); i++) {
            Object object = featureTypes.get(i);
            String ftype = (String) featureTypes.get(i);
            String fname = (String) featureNames.get(i);
            descInfo.addFeature(resolvedType, fname, fname, ftype);
          }
		}
	  }
 	}
	
	public boolean isType(RutaBlock parent, String type) {
		return parent.getEnvironment().getType(type) != null || type.equals("Document");
	}
	
	
	public void addImportTypeSystem(RutaBlock parent, String descriptor) {
		parent.getEnvironment().addTypeSystem(descriptor);
		if(descInfo != null) {
		  descInfo.addTypeSystem(descriptor);
		}
	}
	public void addImportScript(RutaBlock parent, String namespace) {
		parent.getScript().addScript(namespace, null);
		parent.getEnvironment().addScript(namespace);
		if(descInfo != null) {
		  descInfo.addScript(namespace);
		}
	}
	public void addImportEngine(RutaBlock parent, String namespace) {
		parent.getScript().addDescriptorEngine(namespace, null);
		if(descInfo != null) {
		  descInfo.addEngine(namespace);
		}
	}
	public void addImportUimafitEngine(RutaBlock parent, String namespace,
          List<String> configurationData) {
       
      String namespaceWithConfig = namespace;
	    if (configurationData != null && !configurationData.isEmpty()) {
	      namespaceWithConfig = namespace + "[" + StringUtils.join(configurationData, ",") + "]";
	    }
	    parent.getScript().addUimafitEngine(namespace, null);
	    parent.getScript().addConfigurationData(namespace, configurationData);
	    if (descInfo != null) {
	      descInfo.addUimafitEngine(namespaceWithConfig);
	    }
	  }

	/**
	 * Import a type from a type system.
	 *
	 * @param parent - Block where the type should be imported.
	 * @param typesystem - Typesystem from which to import the type.
	 * @param qualifiedType - Type to import from the typesystem.
	 * @param alias - aliad for the imported type
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
      	private boolean isAnnotationFunctionExtension(String name) {
      	  return external.getAnnotationFunctionExtensions().keySet().contains(name);
      	}
      	private boolean isBlockExtension(String name) {
      	  return external.getBlockExtensions().keySet().contains(name);
      	}
      	
      	

	private void addMacroAction(RutaBlock env, String name, Map<String,String> def, Set<String> vars, List<AbstractRutaAction> as) {
     		env.getEnvironment().addMacroAction(name, def, vars, as);
 	}
 	
 	private boolean isMacroAction(String name, RutaBlock env) {
		return env.getEnvironment().isMacroAction(name);
	}
 	
 	private void addMacroCondition(RutaBlock env, String name, Map<String,String> def, Set<String> vars, List<AbstractRutaCondition> cs) {
     		env.getEnvironment().addMacroCondition(name, def, vars, cs);
 	}
 	
 	private boolean isMacroCondition(String name, RutaBlock env) {
		return env.getEnvironment().isMacroCondition(name);
	}
 	 
 	private Map<String,String> temporaryVariables = new HashMap<>(); 
 	
 	private boolean isTemporaryVariable(String name, String type) {
 		return StringUtils.equals(temporaryVariables.get(name), type);
	}
	private boolean isTemporaryVariable(String name) {
 		return temporaryVariables.keySet().contains(name);
	}
	private void addTemporaryVariable(String name, String type) {
		temporaryVariables.put(name, type);
	}
	private void removeTemporaryVariable(String name) {
		temporaryVariables.remove(name);
	}
	private void addTemporaryVariables(Map<String, String> def) {
		temporaryVariables.putAll(def);
	}
	private void removeTemporaryVariables(Map<String, String> def) {
		Set<String> keySet = def.keySet();
		for (String key : keySet) {
		    temporaryVariables.remove(key);
     		}
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
	| stmtCM = macroConditionDeclaration {stmt = stmtCM;}
	| stmtAM = macroActionDeclaration {stmt = stmtAM;}
	| stmtRule = simpleStatement {stmt = stmtRule;}
	| stmtBlock = blockDeclaration {stmt = stmtBlock;}
	//| stmtBlock = forEachDeclaration {stmt = stmtBlock;}
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
	name = Identifier (ASSIGN_EQUAL list = wordListOrStringExpression)? 
	{addVariable($blockDeclaration::env, name.getText(), type.getText());if(list != null){setValue($blockDeclaration::env, name.getText(), list);}} 
	SEMI 
	| 
	type = WORDTABLE 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL table = wordTableOrStringExpression)? 
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
	
	|
	type = ANNOTATION 
	{!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		(COMMA {!ownsVariable($blockDeclaration::env, input.LT(1).getText())}? id = Identifier {vars.add(id.getText());addVariable($blockDeclaration::env, id.getText(), type.getText());}
		 )* (ASSIGN_EQUAL value6 = annotationExpression)? {setValue($blockDeclaration::env, vars, value6);} SEMI
	|
	type = ANNOTATIONLIST
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL al = annotationExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(al != null){setValue($blockDeclaration::env, name.getText(), al);}} 

	;


macroConditionDeclaration returns [RutaStatement stmt = null]
@init {
Map<String,String> def = new LinkedHashMap<>();
Set<String> vars = new TreeSet<>();
}
    :
    CONDITION name = Identifier 
    LPAREN  
    (v = VAR? argType = varTypeToken argName = Identifier  {def.put(argName.getText(),argType.getText());if(v!= null) vars.add(argName.getText());v=null;}
    (COMMA v = VAR? argType = varTypeToken argName = Identifier {def.put(argName.getText(),argType.getText());if(v!= null) vars.add(argName.getText());v=null;})*)? 
    {addTemporaryVariables(def);}
    RPAREN ASSIGN_EQUAL cs = conditions SEMI
    {removeTemporaryVariables(def);}
    {addMacroCondition($blockDeclaration::env, name.getText(), def, vars, cs);}
    ;


macroActionDeclaration returns [RutaStatement stmt = null]
@init {
Map<String,String> def = new LinkedHashMap<>();
Set<String> vars = new TreeSet<>();
}
    :
    ACTION name = Identifier 
    LPAREN  
    (v = VAR? argType = varTypeToken argName = Identifier  {def.put(argName.getText(),argType.getText());if(v != null) vars.add(argName.getText());v=null;}
    (COMMA v = VAR? argType = varTypeToken argName = Identifier {def.put(argName.getText(),argType.getText());if(v != null) vars.add(argName.getText());v=null;})*)? 
    {addTemporaryVariables(def);}
    RPAREN ASSIGN_EQUAL as = actions SEMI
    {removeTemporaryVariables(def);}
    {addMacroAction($blockDeclaration::env, name.getText(), def, vars, as);}
    ;

varTypeToken returns [Token token = null ]
	:
	t = (ANNOTATION | ANNOTATIONLIST | StringString | STRINGLIST 
		| BooleanString | BOOLEANLIST | IntString | INTLIST 
		| DoubleString | DOUBLELIST | FloatString | FLOATLIST
		| TypeString | TYPELIST) {token = t;}
	;

importStatement returns [RutaStatement stmt = null]
@init {
List<String> configurationData = new ArrayList<String>();
}
	:
	TypeSystemString ts = dottedIdentifier2{addImportTypeSystem($blockDeclaration::env, ts);} SEMI
	| ScriptString ns = dottedIdentifier2{addImportScript($blockDeclaration::env, ns);} SEMI
	| EngineString ns = dottedIdentifier2{addImportEngine($blockDeclaration::env, ns);} SEMI
	| UimafitString ns = dottedIdentifier2
		(LPAREN p = dottedIdentifier2 {configurationData.add(p);}
		(COMMA p = dottedIdentifier2 {configurationData.add(p);})+
		 RPAREN)? 
		{addImportUimafitEngine($blockDeclaration::env, ns, configurationData);} 
		SEMI
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
Map<String,String> def = new LinkedHashMap<>();
RutaRuleElement re = null;
RuleElementIsolator container = null;
level++;
}
@after {
level--;
}

	:
	((
	type = (BlockString) 
	LPAREN
	id = Identifier 
	RPAREN
	{block = factory.createScriptBlock(id, re, body, $blockDeclaration[level - 1]::env);}
	)
	|
	(
	type = ForEachString 
	LPAREN
	id = Identifier (COMMA direction = booleanExpression)?
	RPAREN
	{block = factory.createForEachBlock(id, direction, re, body, $blockDeclaration[level - 1]::env);}
	))
	
	
	{$blockDeclaration::env = block;
	container = new RuleElementIsolator();}
	re1 = ruleElementWithCA[container]
	 {re = re1;	 }
	{RutaRule rule = factory.createRule(re, block);
	block.setRule(rule);
	container.setContainer(rule);
	}	
	{if(block instanceof ForEachBlock) def.put(id.getText(),RutaConstants.RUTA_VARIABLE_ANNOTATION);}
	{if(block instanceof ForEachBlock) addTemporaryVariables(def);}
	LCURLY body = statements RCURLY
	{if(block instanceof ForEachBlock) removeTemporaryVariables(def);}
	
	{block.setElements(body);
	$blockDeclaration::env.getScript().addBlock(id.getText(),block);
	}	
	;
/*
forEachDeclaration returns [RutaBlock block = null]
options {
	backtrack = true;
}

scope {
	RutaBlock env;
}
@init{
Map<String,String> def = new LinkedHashMap<>();
RutaRuleElement re = null;
RuleElementIsolator container = null;
level++;
}
@after {
level--;
}

	:
	type = ForEachString 
	LPAREN
	id = Identifier (COMMA direction = booleanExpression)?
	RPAREN
	{block = factory.createForEachBlock(id, direction, re, body, $blockDeclaration[level - 1]::env);}
	{$blockDeclaration::env = block;
	//$blockDeclaration::env = block;
	
	container = new RuleElementIsolator();}
	re1 = ruleElementWithCA[container]
	 {re = re1;	 }
	{RutaRule rule = factory.createRule(re, block);
	block.setRule(rule);
	container.setContainer(rule);
	}
	{def.put(id.getText(),RutaConstants.RUTA_VARIABLE_ANNOTATION);}
	{addTemporaryVariables(def);}
	LCURLY body = statements RCURLY
	{removeTemporaryVariables(def);}
	{block.setElements(body);
	{$blockDeclaration::env = block.getParent();}
	}	
	;
	*/

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
    
          reMatch = ruleElementMatchPart[container] {re = reMatch;}
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
	Map<ITypeExpression, IRutaExpression> map = new HashMap<ITypeExpression, IRutaExpression>();
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
	Map<ITypeExpression, INumberExpression> map = new HashMap<ITypeExpression, INumberExpression>();
	Map<ITypeExpression, Map<IStringExpression, IRutaExpression>> fa = new HashMap<ITypeExpression, Map<IStringExpression, IRutaExpression>>();
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
String label = null;
}
	:
	(l = Identifier {label = l.getText();} COLON)?
	start = STARTANCHOR? (
	re1 = ruleElementAnnotationType[container] {re = re1;}
	| re2 = ruleElementLiteral[container] {re = re2;}
	| (ruleElementComposed[null])=>re3 = ruleElementComposed[container] {re = re3;}
	| (ruleElementWildCard[null])=> re4 = ruleElementWildCard[container] {re = re4;}
	| (ruleElementOptional[null])=> re5 = ruleElementOptional[container] {re = re5;}
	)
	{
	re.setLabel(label);
	re.setStartAnchor(start != null);
	}
	(t = (THEN2) 
	{innerConditionRules = new ArrayList<RutaStatement>();}
	LCURLY 
	(rule = simpleStatement {innerConditionRules.add(rule);})+ 
	RCURLY 
	{re.addInlinedConditionRules(innerConditionRules);}
	)*
	(t = (THEN)
	{innerActionRules = new ArrayList<RutaStatement>();} 
	LCURLY 
	(rule = simpleStatement {innerActionRules.add(rule);})+ 
	RCURLY 
	{re.addInlinedActionRules(innerActionRules);}
	)*
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

ruleElementOptional [RuleElementContainer container] returns [AbstractRuleElement re = null]
    :
    
    w = OPTIONAL 
     {re = factory.createOptionalRuleElement(null, null, container, $blockDeclaration::env);} 
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

ruleElementMatchPart [RuleElementContainer container] returns [RutaRuleElement re = null]
	:
	(
    (annotationAddressExpression)=>addressExpr = annotationAddressExpression 
     {
	     MatchReference mr = expressionFactory.createMatchReference(addressExpr);
	     re = factory.createRuleElement(mr, container, $blockDeclaration::env);
     } 
     
    |
    (typeFunction)=> tf = typeFunction 
    {
    	MatchReference mr = expressionFactory.createMatchReference(tf);
   	re = factory.createRuleElement(mr, container, $blockDeclaration::env);
    }
    
    |
     match = dottedIdWithIndex2 ((comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument)?
     {
	     MatchReference mr = expressionFactory.createMatchReference(match, comp, arg);
	     re = factory.createRuleElement(mr, container, $blockDeclaration::env);
     }
     
     
     )
	;
    
 ruleElementAnnotationType [RuleElementContainer container] returns [RutaRuleElement re = null]
    :
    
     reMatch = ruleElementMatchPart[container] {re = reMatch;}
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
	(featureExpression)=>fe = featureExpression {expr = expressionFactory.createGenericFeatureExpression(fe);}
	| (booleanListExpression)=> bl = booleanListExpression {expr = bl;}
	| (intListExpression)=> il = intListExpression {expr = il;}
	| (doubleListExpression)=> dl = doubleListExpression {expr = dl;}
	| (floatListExpression)=> dl = floatListExpression {expr = dl;}
	| (stringListExpression)=> sl = stringListExpression {expr = sl;}
	| (typeListExpression)=> tl = typeListExpression {expr = tl;}
	| (annotationListExpression)=> ale = annotationListExpression {expr = ale;}
	| (untypedListExpression)=> utl = untypedListExpression {expr = utl;}
	;

plainListExpression returns [ListExpression expr = null]
	:
	(booleanListExpression)=> bl = booleanListExpression {expr = bl;}
	| (intListExpression)=> il = intListExpression {expr = il;}
	| (doubleListExpression)=> dl = doubleListExpression {expr = dl;}
	| (floatListExpression)=> dl = floatListExpression {expr = dl;}
	| (stringListExpression)=> sl = stringListExpression {expr = sl;}
	| (typeListExpression)=> tl = typeListExpression {expr = tl;}
	| (annotationListExpression)=> ale = annotationListExpression {expr = ale;}
	| (untypedListExpression)=> utl = untypedListExpression {expr = utl;}
	;

untypedListExpression returns [ListExpression expr = null]
@init{
	List<IRutaExpression> list = new ArrayList<IRutaExpression>();
}	:
	LCURLY (e = argument {list.add(e);} (COMMA e = argument {list.add(e);})*)?  RCURLY
	{expr = expressionFactory.createUntypedListExpression(list);}
	;

booleanListExpression returns [AbstractBooleanListExpression expr = null]
	:
	e = simpleBooleanListExpression {expr = e;}
	;

simpleBooleanListExpression returns [AbstractBooleanListExpression expr = null]
@init{
	List<IBooleanExpression> list = new ArrayList<IBooleanExpression>();
}	:
	LCURLY (e = simpleBooleanExpression {list.add(e);} (COMMA e = simpleBooleanExpression {list.add(e);})*)?  RCURLY
	{expr = expressionFactory.createBooleanListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "BOOLEANLIST")}? var = Identifier 
	{expr = expressionFactory.createReferenceBooleanListExpression(var);}
	;


intListExpression returns [AbstractNumberListExpression expr = null]
	:
	e = simpleIntListExpression {expr = e;}
	;

simpleIntListExpression returns [AbstractNumberListExpression expr = null]
@init{
	List<INumberExpression> list = new ArrayList<INumberExpression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = expressionFactory.createNumberListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "INTLIST")}? var = Identifier 
	{expr = expressionFactory.createReferenceIntListExpression(var);}
	;


numberListExpression returns [AbstractNumberListExpression expr = null]
	:
	(e1 = doubleListExpression)=> e1 = doubleListExpression {expr = e1;}
	|
	(e1 = floatListExpression)=> e1 = floatListExpression {expr = e1;}
	|
	e2 = intListExpression {expr = e2;}
	;
	
doubleListExpression returns [AbstractNumberListExpression expr = null]
	:
	e = simpleDoubleListExpression {expr = e;}
	;

simpleDoubleListExpression returns [AbstractNumberListExpression expr = null]
@init{
	List<INumberExpression> list = new ArrayList<INumberExpression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = expressionFactory.createNumberListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "DOUBLELIST")}? var = Identifier 
	{expr = expressionFactory.createReferenceDoubleListExpression(var);}
	;

	
floatListExpression returns [AbstractNumberListExpression expr = null]
	:
	e = simpleFloatListExpression {expr = e;}
	;

simpleFloatListExpression returns [AbstractNumberListExpression expr = null]
@init{
	List<INumberExpression> list = new ArrayList<INumberExpression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = expressionFactory.createNumberListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "FLOATLIST")}? var = Identifier 
	{expr = expressionFactory.createReferenceFloatListExpression(var);}
	;

stringListExpression returns [AbstractStringListExpression expr = null]
	:
	e = simpleStringListExpression {expr = e;}
	;

simpleStringListExpression returns [AbstractStringListExpression expr = null]
@init{
	List<IStringExpression> list = new ArrayList<IStringExpression>();
}	:
	LCURLY (e = simpleStringExpression {list.add(e);} (COMMA e = simpleStringExpression {list.add(e);})*)?  RCURLY
	{expr = expressionFactory.createStringListExpression(list);}	
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "STRINGLIST")}? var = Identifier 
	{expr = expressionFactory.createReferenceStringListExpression(var);}
	;


typeListExpression returns [AbstractTypeListExpression expr = null]
	:
	e = simpleTypeListExpression {expr = e;}
	;

simpleTypeListExpression returns [AbstractTypeListExpression expr = null]
@init{
	List<ITypeExpression> list = new ArrayList<ITypeExpression>();
}	:
	LCURLY (e = simpleTypeExpression {list.add(e);} (COMMA e = simpleTypeExpression {list.add(e);})*)?  RCURLY
	{expr = expressionFactory.createTypeListExpression(list);}
	|
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "TYPELIST")}? var = Identifier 
	{expr = expressionFactory.createReferenceTypeListExpression(var);}
	;

typeMatchExpression returns [IRutaExpression expr = null]
options {
	backtrack = true;
}
	:	
	(featureMatchExpression)=> fme = featureMatchExpression {expr = fme;}
	|
	(typeExpression)=> te = typeExpression {expr = te;}
	;

matchReference returns [MatchReference mr = null]
	:
	ref = dottedId 
	{mr = expressionFactory.createMatchReference(ref);}
	;

typeExpression returns [ITypeExpression type = null]
options {
	backtrack = true;
}
	:
	tf = typeFunction {type = tf;}
	//| tl = typeListExpression LBRACK index = numberExpression RBRACK {type = expressionFactory.createTypeListIndexExpression(tl, index);}
	| st = simpleTypeExpression {type = st;}
	;
	

// not checked
typeFunction returns [ITypeExpression expr = null]
	:
	(e = externalTypeFunction)=> e = externalTypeFunction {expr = e;}
	;

// not checked
externalTypeFunction returns [ITypeExpression expr = null]
	:
	{isTypeFunctionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN
	args = varArgumentList?	RPAREN
	{
		expr = external.createExternalTypeFunction(id, args);
	}
	;

simpleTypeExpression returns [ITypeExpression type = null]
	:
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "TYPE")}? var = Identifier 
	{type = expressionFactory.createReferenceTypeExpression(var);}
	|
	at = annotationType
	{type = expressionFactory.createSimpleTypeExpression(at, $blockDeclaration::env);}
	;


matchExpression returns [FeatureExpression feat = null]
	:
	match = dottedId
	{MatchReference mr = expressionFactory.createMatchReference(match);}
	;

featureExpression returns [FeatureExpression feat = null]
@init{
List<Token> fs = new ArrayList<Token>();
ITypeExpression te = null;
}
	:
	match = dottedIdWithIndex 
	{
	MatchReference mr = expressionFactory.createMatchReference(match);
	feat = expressionFactory.createFeatureExpression(mr, $blockDeclaration::env);
	}
	;

featureMatchExpression returns [FeatureExpression fme = null]
	:
	match = dottedIdWithIndex ((comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument)?
	{
	MatchReference mr = expressionFactory.createMatchReference(match);
	if(comp != null) {
	fme = expressionFactory.createFeatureMatchExpression(mr, comp, arg, $blockDeclaration::env);
	} else {
	fme = expressionFactory.createFeatureExpression(mr, $blockDeclaration::env);
	}
	}
	;

featureMatchExpression2 returns [FeatureMatchExpression fme = null]
	:
	match = dottedIdWithIndex (comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument
	{
	MatchReference mr = expressionFactory.createMatchReference(match);
	fme = expressionFactory.createFeatureMatchExpression(mr, comp, arg, $blockDeclaration::env);}
	;


featureAssignmentExpression returns [FeatureMatchExpression fme = null]
	:
	match = dottedIdWithIndex op = ASSIGN_EQUAL arg = argument
	{
	MatchReference mr = expressionFactory.createMatchReference(match);
	fme = expressionFactory.createFeatureMatchExpression(mr, op, arg, $blockDeclaration::env);
	}
	;
	
variableAssignmentAction returns [AbstractRutaAction a = null]
	:
	var = variable op = ASSIGN_EQUAL arg = argument
	{
	a = actionFactory.createImplicitVariableAssignmentAction(var, op, arg, $blockDeclaration::env);
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
	||isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "ANNOTATIONLIST")
	}? v = Identifier {var = v;}
	;


//typeExpressionOr returns [ITypeExpression type = null]
//@init {List<ITypeExpression> exprs = new ArrayList<ITypeExpression>();}
//	:
//	LBRACK e = typeExpressionAnd{exprs.add(e);} ( COMMA e = typeExpressionAnd{exprs.add(e);} )* RBRACK
//	{type = expressionFactory.createOrTypeExpression(exprs);}
//	;

//typeExpressionAnd returns [ITypeExpression type = null]
//@init {List<ITypeExpression> exprs = new ArrayList<ITypeExpression>();}
//	:
//	LBRACK e = simpleTypeExpression{exprs.add(e);} ( SEMI e = simpleTypeExpression{exprs.add(e);} )* RBRACK
//	{type = expressionFactory.createAndTypeExpression(exprs);}
//	;

quantifierPart returns [RuleElementQuantifier quantifier = null]
options {
	backtrack = true;
}
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
	| LBRACK min = numberExpression comma = COMMA max = numberExpression RBRACK q = QUESTION?
	 {if(q != null) {quantifier = RutaScriptFactory.createMinMaxReluctantQuantifier(min,max,comma);} 
	 else {quantifier = RutaScriptFactory.createMinMaxGreedyQuantifier(min,max,comma);}}	
	 | LBRACK min = numberExpression comma = COMMA RBRACK q = QUESTION?
	 {if(q != null) {quantifier = RutaScriptFactory.createMinMaxReluctantQuantifier(min,max,comma);} 
	 else {quantifier = RutaScriptFactory.createMinMaxGreedyQuantifier(min,max,comma);}}	
	 | LBRACK comma = COMMA max = numberExpression RBRACK q = QUESTION?
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
	
	| (featureMatchExpression2)=> f = featureMatchExpression2 {c = conditionFactory.createImplicitCondition(f);}
	| (booleanExpression)=> b = booleanExpression {c = conditionFactory.createImplicitCondition(b);}
	| (c = externalCondition)=> c = externalCondition
	| (c = macroCondition)=> c = macroCondition
	) {result = c;}
	;


externalCondition returns [AbstractRutaCondition condition = null]
	:		
	{isConditionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN args = varArgumentList?	RPAREN
	{
		condition = external.createExternalCondition(id, args);
	}
	;
	
macroCondition returns [AbstractRutaCondition condition = null]
	:		
	{isMacroCondition(input.LT(1).getText(), $blockDeclaration::env)}? 
	id = Identifier LPAREN args = varArgumentList?	RPAREN
	{
		condition = conditionFactory.createMacroCondition(id, args, $blockDeclaration::env);
	}
	;
conditionAnd returns [AbstractRutaCondition cond = null]
    :   
    AND LPAREN conds = conditions RPAREN 
    {cond = conditionFactory.createConditionAnd(conds, $blockDeclaration::env);}
    ;
    
conditionContains returns [AbstractRutaCondition cond = null]
options {
	backtrack = true;
}
@init {
List<IRutaExpression> args = new ArrayList<>();
}
    :   
    CONTAINS LPAREN 
    a = argument {args.add(a);} 
    (COMMA a = argument{args.add(a);})*
    RPAREN
    
    //(type = typeExpression | list = plainListExpression COMMA a = argument) 
    //(COMMA min = numberExpression COMMA max = numberExpression (COMMA percent = booleanExpression)?)? RPAREN
    {
    cond = conditionFactory.createConditionContains(args, $blockDeclaration::env);
    //if(type != null) {cond = conditionFactory.createConditionContains(type, min, max, percent,$blockDeclaration::env);}
    //else if(list != null) {cond = conditionFactory.createConditionContains(list, a, min, max, percent, $blockDeclaration::env);}
    }
    ;
conditionContextCount returns [AbstractRutaCondition cond = null]
    :   
    CONTEXTCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = conditionFactory.createConditionContextCount(type, min, max, var, $blockDeclaration::env);}
    ;
conditionCount returns [AbstractRutaCondition cond = null]
 options {
	backtrack = true;
}
    :   
    COUNT LPAREN type = listExpression COMMA a = argument (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = conditionFactory.createConditionCount(type, a, min, max, var,$blockDeclaration::env);}
    |
    COUNT LPAREN list = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = conditionFactory.createConditionCount(list, min, max, var,$blockDeclaration::env);}   
    ;
conditionTotalCount returns [AbstractRutaCondition cond = null]
    :   
    TOTALCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)?
    (COMMA var = numberVariable)? RPAREN
    {cond = conditionFactory.createConditionTotalCount(type, min, max, var, $blockDeclaration::env);}
    ;
conditionCurrentCount returns [AbstractRutaCondition cond = null]
    :   
    CURRENTCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = conditionFactory.createConditionCurrentCount(type, min, max, var,$blockDeclaration::env);}
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
    {if(list1 != null) {cond = conditionFactory.createConditionInList(list1, arg,$blockDeclaration::env);}
    else {cond = conditionFactory.createConditionInList(list2, arg,$blockDeclaration::env);};}
    ;

    
conditionLast returns [AbstractRutaCondition cond = null]
    :   
    LAST LPAREN type = typeExpression RPAREN
    {cond = conditionFactory.createConditionLast(type, $blockDeclaration::env);}    
    ;
    
    
conditionMofN returns [AbstractRutaCondition cond = null]
    :   
    MOFN LPAREN min = numberExpression COMMA max = numberExpression COMMA conds = conditions RPAREN
    {cond = conditionFactory.createConditionMOfN(conds, min, max, $blockDeclaration::env);} 
    ;

conditionNear returns [AbstractRutaCondition cond = null]
    :   
    NEAR LPAREN type = typeExpression COMMA min = numberExpression COMMA max = numberExpression (COMMA direction = booleanExpression (COMMA filtered = booleanExpression)?)? RPAREN
    {cond = conditionFactory.createConditionNear(type, min, max, direction, filtered, $blockDeclaration::env);} 
    ;
conditionNot returns [AbstractRutaCondition cond = null]
    :   
    ((MINUS c = condition) |  (NOT LPAREN c = condition RPAREN))
    {cond = conditionFactory.createConditionNot(c, $blockDeclaration::env);}    
    ;
conditionOr returns [AbstractRutaCondition cond = null]
    :   
    OR LPAREN conds = conditions RPAREN
    {cond = conditionFactory.createConditionOr(conds, $blockDeclaration::env);}
    ;
conditionPartOf returns [AbstractRutaCondition cond = null]
    :
    PARTOF LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = conditionFactory.createConditionPartOf(type1, type2, $blockDeclaration::env);}
    ;
conditionPartOfNeq returns [AbstractRutaCondition cond = null]
    :
    PARTOFNEQ LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = conditionFactory.createConditionPartOfNeq(type1, type2, $blockDeclaration::env);}
    ;

conditionPosition returns [AbstractRutaCondition cond = null]
    :   
    POSITION LPAREN type = typeExpression COMMA pos = numberExpression (COMMA rel = booleanExpression)? RPAREN
    {cond = conditionFactory.createConditionPosition(type, pos, rel, $blockDeclaration::env);}
    ;
conditionRegExp returns [AbstractRutaCondition cond = null]
    :
    REGEXP LPAREN 
    ((stringExpression COMMA stringExpression)=> v = stringExpression COMMA pattern = stringExpression
    | pattern = stringExpression
    )
    (COMMA caseSensitive = booleanExpression)? RPAREN
    {cond = conditionFactory.createConditionRegExp(v, pattern, caseSensitive, $blockDeclaration::env);}    
    ;

conditionScore returns [AbstractRutaCondition cond = null]
    :
    SCORE LPAREN min = numberExpression (COMMA max = numberExpression
    (COMMA var = numberVariable)?)?  RPAREN
    {cond = conditionFactory.createConditionScore(min, max, var, $blockDeclaration::env);}
    ;


conditionVote returns [AbstractRutaCondition cond = null]
    :   
    VOTE LPAREN type1 = typeExpression COMMA type2 = typeExpression RPAREN
    {cond = conditionFactory.createConditionVote(type1, type2, $blockDeclaration::env);}
    ;
    
conditionIf returns [AbstractRutaCondition cond = null]
    :   
    IF LPAREN e = booleanExpression RPAREN
    {cond = conditionFactory.createConditionIf(e, $blockDeclaration::env);}
    ;

conditionFeature returns [AbstractRutaCondition cond = null]
    :   
    FEATURE LPAREN se = stringExpression COMMA v = argument RPAREN
    {cond = conditionFactory.createConditionFeature(se, v, $blockDeclaration::env);}
    ;   

conditionParse returns [AbstractRutaCondition cond = null]
    :
    PARSE LPAREN {isVariable($blockDeclaration::env,input.LT(1).getText())}? id = Identifier 
    (COMMA locale = stringExpression)?
    RPAREN
    {cond = conditionFactory.createConditionParse(id, locale, $blockDeclaration::env);}
    ;

conditionIs returns [AbstractRutaCondition cond = null]
    :
    IS LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = conditionFactory.createConditionIs(type1, type2, $blockDeclaration::env);}
    ;

conditionBefore returns [AbstractRutaCondition cond = null]
    :
    BEFORE LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = conditionFactory.createConditionBefore(type1,type2, $blockDeclaration::env);}
    ;

conditionAfter returns [AbstractRutaCondition cond = null]
    :
    AFTER LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = conditionFactory.createConditionAfter(type1,type2,$blockDeclaration::env);}
    ;

conditionStartsWith returns [AbstractRutaCondition cond = null]
    :
    STARTSWITH LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = conditionFactory.createConditionStartsWith(type1,type2, $blockDeclaration::env);}
    ;

conditionEndsWith returns [AbstractRutaCondition cond = null]
    :
    ENDSWITH LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = conditionFactory.createConditionEndsWith(type1,type2,$blockDeclaration::env);}
    ;

conditionSize returns [AbstractRutaCondition cond = null]
    :
    SIZE LPAREN list = listExpression (COMMA min = numberExpression COMMA max = numberExpression)? (COMMA var = numberVariable)? RPAREN
    {cond = conditionFactory.createConditionSize(list, min, max, var,$blockDeclaration::env);}
    ;

action  returns [AbstractRutaAction result = null]
@init{
  String label = null;
}
	:
	(l = Identifier {label = l.getText();} COLON)?
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
	| a = actionSplit
	| a = actionConfigure
	| a = actionDynamicAnchoring
	| a = actionGreedyAnchoring
	| a = actionTrim 
	| a = actionAddRetainType
	| a = actionRemoveRetainType
	| a = actionAddFilterType
	| a = actionRemoveFilterType
	| (variableAssignmentAction)=> vae = variableAssignmentAction {a = vae;}
	| (externalAction)=> a = externalAction
	| (macroAction)=> a = macroAction
	| (featureAssignmentExpression)=> fae = featureAssignmentExpression {a = actionFactory.createAction(fae);}
	| (typeExpression)=> te = typeExpression {a = actionFactory.createAction(te);}
	
//	| a = variableAction
	) 
	{
	result = a;
	result.setLabel(label);
	}
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
	| (macroAction)=> a = macroAction
	) {result = a;}
	;	




externalAction returns [AbstractRutaAction action = null]
	:		
	{isActionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN args = varArgumentList?	RPAREN
	{
		action = external.createExternalAction(id, args);
	}
	;

macroAction returns [AbstractRutaAction action = null]
	:		
	{isMacroAction(input.LT(1).getText(), $blockDeclaration::env)}? 
	id = Identifier LPAREN args = varArgumentList?	RPAREN
	{
		action = actionFactory.createMacroAction(id, args, $blockDeclaration::env);
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
    {action = actionFactory.createCreateAction(structure, map, indexes, $blockDeclaration::env);}
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


    {action = actionFactory.createMarkTableAction(structure, index, table, map, ignoreCase, ignoreLength, ignoreChar, maxIgnoreChar,$blockDeclaration::env);}
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
    {action = actionFactory.createGatherAction(structure, map, indexes, $blockDeclaration::env);}
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
    {action = actionFactory.createFillAction(type, map, $blockDeclaration::env);}
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
    {action = actionFactory.createColorAction(type, bgcolor, fgcolor, selected, $blockDeclaration::env);}
    ;

actionDel returns [AbstractRutaAction action = null]
    :   
    DEL
    {action = actionFactory.createDelAction($blockDeclaration::env);}
    ;
        
actionLog returns [AbstractRutaAction action = null]
    :   
    LOG LPAREN lit = stringExpression (COMMA log = LogLevel)? RPAREN
    {action = actionFactory.createLogAction(lit, log, $blockDeclaration::env);}
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
    
    {action = actionFactory.createMarkAction(null, type, list, $blockDeclaration::env);}
    ;

actionSplit returns [AbstractRutaAction action = null]
	:
	SPLIT 
	LPAREN
	type = typeExpression
	(
	COMMA
	complete = booleanExpression
	(
	COMMA
	appendToBegin = booleanExpression
	COMMA
	appendToEnd = booleanExpression
	)?
	)? 
	RPAREN
	{action = actionFactory.createSplitAction(type, complete, appendToBegin, appendToEnd, $blockDeclaration::env);}
	;
	
actionShift returns [AbstractRutaAction action = null]
@init {
List<INumberExpression> list = new ArrayList<INumberExpression>();
}
    :   
    SHIFT LPAREN 
    type = typeExpression 
    COMMA index1 = numberExpression {list.add(index1);}
    COMMA index2 = numberExpression {list.add(index2);}
    (COMMA (all=booleanExpression)=> all= booleanExpression)?
    RPAREN
    
    {action = actionFactory.createShiftAction(type, list, all, $blockDeclaration::env);}
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
    
    {action = actionFactory.createMarkAction(score, type, list, $blockDeclaration::env);}
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
    
    {action = actionFactory.createMarkOnceAction(score, type, list,$blockDeclaration::env);}
    ;

actionMarkFast returns [AbstractRutaAction action = null]
    :   
    MARKFAST LPAREN type = typeExpression COMMA (list1 = wordListExpression | list2 = stringListExpression) 
    (COMMA ignore = booleanExpression (COMMA ignoreLength = numberExpression (COMMA ignoreWS = booleanExpression)?)?)? RPAREN
    {if(list1 != null) {
     action = actionFactory.createMarkFastAction(type, list1, ignore, ignoreLength, ignoreWS, $blockDeclaration::env);
    } else {
     action = actionFactory.createMarkFastAction(type, list2, ignore, ignoreLength, ignoreWS, $blockDeclaration::env);
    }
    }
    ;

actionMarkLast returns [AbstractRutaAction action = null]
    :   
    MARKLAST LPAREN type = typeExpression RPAREN
    {action = actionFactory.createMarkLastAction(type, $blockDeclaration::env);}
    ;
    
actionMarkFirst returns [AbstractRutaAction action = null]
    :   
    MARKFIRST LPAREN type = typeExpression RPAREN
    {action = actionFactory.createMarkFirstAction(type, $blockDeclaration::env);}
    ;

actionReplace returns [AbstractRutaAction action = null]
    :   
    REPLACE LPAREN lit = stringExpression RPAREN
    {action = actionFactory.createReplaceAction(lit, $blockDeclaration::env);}
    ;
    
  

actionRetainType returns [AbstractRutaAction action = null]
@init {
List<ITypeExpression> list = new ArrayList<ITypeExpression>();
}
    :   
    RETAINTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)?
    {action = actionFactory.createRetainTypeAction(list, $blockDeclaration::env);}
    ;   
    
 

actionFilterType returns [AbstractRutaAction action = null]
@init {
List<ITypeExpression> list = new ArrayList<ITypeExpression>();
}
    :   
    FILTERTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)?
    {action = actionFactory.createFilterTypeAction(list,$blockDeclaration::env);}
    ;       

actionCall returns [AbstractRutaAction action = null]
    :
    CALL LPAREN ns = dottedIdentifier RPAREN
    {action = actionFactory.createCallAction(ns, $blockDeclaration::env);}
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
    {action = actionFactory.createConfigureAction(ns, map, $blockDeclaration::env);}
    ;


actionExec returns [AbstractRutaAction action = null]
    :
    EXEC LPAREN ((stringExpression)=> view = stringExpression COMMA)? ns = dottedIdentifier (COMMA tl = typeListExpression)? RPAREN
    {action = actionFactory.createExecAction(ns, tl, view, $blockDeclaration::env);}
    ;    
    
actionAssign returns [AbstractRutaAction action = null]
    :
    name = ASSIGN LPAREN
    (
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "ANNOTATION")||
    isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "ANNOTATIONLIST")||
    isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "TYPE")}? 
        nv = Identifier COMMA ea = annotationOrTypeExpression 
        {action = actionFactory.createAssignAction(nv, ea,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "BOOLEAN")}? 
        nv = Identifier COMMA e2 = booleanExpression 
        {action = actionFactory.createAssignAction(nv, e2,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "STRING")}? 
        nv = Identifier COMMA e3 = stringExpression 
        {action = actionFactory.createAssignAction(nv, e3,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "INT")}? 
        nv = Identifier COMMA e4 = numberExpression 
        {action = actionFactory.createAssignAction(nv, e4,$blockDeclaration::env);}
    |
     {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "FLOAT")}? 
        nv = Identifier COMMA e6 = numberExpression 
        {action = actionFactory.createAssignAction(nv, e6,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "DOUBLE")}? 
        nv = Identifier COMMA e5 = numberExpression 
        {action = actionFactory.createAssignAction(nv, e5,$blockDeclaration::env);}
    |
    {isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "BOOLEANLIST")||
     isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "INTLIST")||
     isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "DOUBLELIST")||
     isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "FLOATLIST")||
     isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "STRINGLIST")||
     isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "TYPELIST")}? 
        nv = Identifier COMMA e7 = listExpression 
        {action = actionFactory.createAssignAction(nv, e7,$blockDeclaration::env);}
    ) RPAREN
    ;

actionSetFeature returns [AbstractRutaAction action = null]
    :   
    name = SETFEATURE LPAREN f = stringExpression COMMA v = argument RPAREN
    {action = actionFactory.createSetFeatureAction(f, v, $blockDeclaration::env);}
    ;


actionGetFeature returns [AbstractRutaAction action = null]
    :   
    name = GETFEATURE LPAREN f = stringExpression COMMA v = variable RPAREN
    {action = actionFactory.createGetFeatureAction(f, v, $blockDeclaration::env);}
    ;

//unknown
actionDynamicAnchoring returns [AbstractRutaAction action = null]
    :
    name = DYNAMICANCHORING LPAREN active = booleanExpression 
    (COMMA penalty = numberExpression 
    (COMMA factor = numberExpression)?)? 
    {action = actionFactory.createDynamicAnchoringAction(active, penalty, factor, $blockDeclaration::env);}
    RPAREN
    ;

actionGreedyAnchoring returns [AbstractRutaAction action = null]
    :
    name = GREEDYANCHORING LPAREN active = booleanExpression (COMMA active2 = booleanExpression )?
    {action = actionFactory.createGreedyAnchoringAction(active, active2, $blockDeclaration::env);}
    RPAREN
    ;

actionTrim returns [AbstractRutaAction action = null]
@init {
  List<ITypeExpression> types = new ArrayList<ITypeExpression>();
}
    :
    name = TRIM LPAREN 
    (
    typeList = typeListExpression
    |
    t1 = typeExpression {types.add(t1);} (COMMA t2 = typeExpression {types.add(t2);})*
    )
    
    {action = actionFactory.createTrimAction(types, typeList, $blockDeclaration::env);}
    RPAREN
    ;



actionUnmark returns [AbstractRutaAction action = null]
@init {
List<INumberExpression> list = new ArrayList<INumberExpression>();
}
    :
    name = UNMARK LPAREN 
    
    (
    
    (typeExpression COMMA)=>f = typeExpression 

    (COMMA 
    (
  	(b = booleanExpression)=> b = booleanExpression
  	|
  	(
  	index = numberExpression {list.add(index);} 
  	(COMMA index = numberExpression {list.add(index);})*
  	)
    ) 
    )
     RPAREN
    {action = actionFactory.createUnmarkAction(f, list, b,$blockDeclaration::env);}
    |
    (annotationOrTypeExpression)=>a = annotationOrTypeExpression RPAREN {action = actionFactory.createUnmarkAction(a, $blockDeclaration::env);}
    
    )
    ;


actionUnmarkAll returns [AbstractRutaAction action = null]
    :
    name = UNMARKALL LPAREN f = typeExpression 
    (COMMA list = typeListExpression)? RPAREN
    {action = actionFactory.createUnmarkAllAction(f, list, $blockDeclaration::env);}
    ;

actionTransfer returns [AbstractRutaAction action = null]
    :
    name = TRANSFER LPAREN f = typeExpression RPAREN
    {action = actionFactory.createTransferAction(f, $blockDeclaration::env);}
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
    {action = actionFactory.createTrieAction(list, map, ignoreCase, ignoreLength, edit, distance, ignoreChar,$blockDeclaration::env);}
    ;

actionAdd returns [AbstractRutaAction action = null]
@init{
	List<IRutaExpression> list = new ArrayList<IRutaExpression>();
} 
    :
    name = ADD LPAREN f = listVariable (COMMA a = argument {list.add(a);})+ RPAREN
    {action = actionFactory.createAddAction(f, list, $blockDeclaration::env);}
    ;

actionRemove returns [AbstractRutaAction action = null]
@init{
	List<IRutaExpression> list = new ArrayList<IRutaExpression>();
} 
    :
    name = REMOVE LPAREN f = listVariable (COMMA a = argument {list.add(a);})+ RPAREN
    {action = actionFactory.createRemoveAction(f, list, $blockDeclaration::env);}
    ;
 
actionRemoveDuplicate returns [AbstractRutaAction action = null]
    :
    name = REMOVEDUPLICATE LPAREN f = listVariable RPAREN
    {action = actionFactory.createRemoveDuplicateAction(f,$blockDeclaration::env);}
    ; 
    
actionMerge returns [AbstractRutaAction action = null]
@init{
	List<ListExpression> list = new ArrayList<ListExpression>();
} 
    :
    name = MERGE LPAREN join = booleanExpression COMMA t = listVariable COMMA f = listExpression {list.add(f);} (COMMA f = listExpression {list.add(f);})+ RPAREN
    {action = actionFactory.createMergeAction(join, t, list,$blockDeclaration::env);}
    ;

actionGet returns [AbstractRutaAction action = null]
    :
    name = GET LPAREN f = listExpression COMMA var = variable COMMA op = stringExpression RPAREN
    {action = actionFactory.createGetAction(f, var, op,$blockDeclaration::env);}
    ;

actionGetList returns [AbstractRutaAction action = null]
    :
    name = GETLIST LPAREN var = listVariable COMMA op = stringExpression RPAREN
    {action = actionFactory.createGetListAction(var, op,$blockDeclaration::env);}
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
    
    {action = actionFactory.createMatchedTextAction(var, list, $blockDeclaration::env);}
    ;

actionClear returns [AbstractRutaAction action = null]
    :
    name = CLEAR LPAREN var = listVariable RPAREN
    {action = actionFactory.createClearAction(var, $blockDeclaration::env);}
    ;

actionAddRetainType returns [AbstractRutaAction action = null]
@init {
List<ITypeExpression> list = new ArrayList<ITypeExpression>();
}
    :
    ADDRETAINTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)
    {action = actionFactory.createAddRetainTypeAction(list,$blockDeclaration::env);}
    ;     

actionRemoveRetainType returns [AbstractRutaAction action = null]
@init {
List<ITypeExpression> list = new ArrayList<ITypeExpression>();
}
    :
    REMOVERETAINTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)
    {action = actionFactory.createRemoveRetainTypeAction(list,$blockDeclaration::env);}
    ;   

actionAddFilterType returns [AbstractRutaAction action = null]
@init {
List<ITypeExpression> list = new ArrayList<ITypeExpression>();
}
    :
    ADDFILTERTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)
    {action = actionFactory.createAddFilterTypeAction(list,$blockDeclaration::env);}
    ;   

actionRemoveFilterType returns [AbstractRutaAction action = null]
@init {
List<ITypeExpression> list = new ArrayList<ITypeExpression>();
}
    :
    REMOVEFILTERTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)
    {action = actionFactory.createRemoveFilterTypeAction(list,$blockDeclaration::env);}
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
	match = dottedIdWithIndex2 (comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument LCURLY cs = conditions RCURLY
	{MatchReference mr = expressionFactory.createMatchReference(match, comp, arg);
	expr = expressionFactory.createConditionedAnnotationTypeExpression(mr,cs);}
	| match = dottedIdWithIndex2 LCURLY cs = conditions RCURLY
	{MatchReference mr = expressionFactory.createMatchReference(match);
	expr = expressionFactory.createConditionedAnnotationTypeExpression(mr,cs);}
	| match = dottedIdWithIndex2 (comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument
	{MatchReference mr = expressionFactory.createMatchReference(match, comp, arg);
	expr = expressionFactory.createAnnotationTypeExpression(mr);}

	| (featureExpression)=> fe = featureExpression {expr = expressionFactory.createGenericFeatureExpression(fe);}
	| a2 = booleanExpression {expr = a2;}
	| a3 = numberExpression {expr = a3;}
	| a4 = stringExpression {expr = a4;}
	| (listExpression)=> l = listExpression {expr = l;}
	| a5 = nullExpression {expr = a5;}
	| a6 = annotationOrTypeExpression {expr = a6;}
	//| match = dottedIdWithIndex2 ((comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument)?
	//{MatchReference mr = expressionFactory.createMatchReference(match, comp, arg);
	//expr = expressionFactory.createAnnotationTypeExpression(mr);}
	
	//(a2 = booleanExpression)=> a2 = booleanExpression {expr = a2;}
	//| (a3 = numberExpression)=> a3 = numberExpression {expr = a3;}
	//| (a4 = stringExpression)=> a4 = stringExpression {expr = a4;}
	//| (a1 = typeExpression)=> a1 = typeExpression {expr = a1;}
	;

simpleArgument returns [IRutaExpression expr = null]
options {
	backtrack = true;
}
	:
	(featureExpression)=> fe = featureExpression {expr = expressionFactory.createGenericFeatureExpression(fe);}
	| a2 = booleanExpression {expr = a2;}
	| a3 = numberExpression {expr = a3;}
	| a4 = stringExpression {expr = a4;}
	| (listExpression)=> l = listExpression {expr = l;}
	| a5 = nullExpression {expr = a5;}
	;


annotationOrTypeExpression returns [IRutaExpression expr = null]
	:
	aae = annotationAddressExpression {expr = aae;}
	|
	tf = typeFunction {expr = tf;}
	|
	af = annotationFunction {expr = af;}	
	|
	match = dottedIdWithIndex2 ((comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) arg = argument)?
	{MatchReference mr = expressionFactory.createMatchReference(match, comp, arg);
	expr = expressionFactory.createAnnotationTypeExpression(mr);}
	//ref = dottedId
	//{expr = expressionFactory.createGenericExpression(ref);}
	;

annotationExpression returns [IRutaExpression expr = null]
	:
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "ANNOTATION")	}? 
	id = Identifier {expr = expressionFactory.createAnnotationVariableExpression(id);} 
	|
	//(annotationListIndexExpression)=> alie = annotationListIndexExpression {expr = alie;}
	//|
	ale = annotationListExpression {expr = ale;}
	|
	aae = annotationAddressExpression {expr = aae;}
	|
	ale = annotationLabelExpression {expr = ale;}
	;


annotationListIndexExpression returns [IRutaExpression expr = null]
	:
	al = annotationListExpression LBRACK index = numberExpression RBRACK {expr = expressionFactory.createAnnotationListIndexExpression(al, index);}
	;

annotationListExpression returns [AbstractAnnotationListExpression expr = null]
	:
	{isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "ANNOTATIONLIST")}? 
	id = Identifier {expr = expressionFactory.createAnnotationListVariableExpression(id);} 
	;
	

annotationAddressExpression returns [IAnnotationExpression expr = null]
	:
	ADDRESS_PREFIX address = DecimalLiteral {expr = expressionFactory.createAnnotationAddressExpression(address);}
	;
	
annotationLabelExpression returns [IRutaExpression expr = null]
	:
	label = Identifier {expr = expressionFactory.createAnnotationLabelExpression(label);}
	;

annotationFunction returns [IAnnotationExpression expr = null]
	:
	(e = externalAnnotationFunction)=> e = externalAnnotationFunction {expr = e;}
	;

externalAnnotationFunction returns [IAnnotationExpression expr = null]
	:
	{isAnnotationFunctionExtension(input.LT(1).getText())}? 
	id = Identifier LPAREN
	args = varArgumentList?	RPAREN
	{
		expr = external.createExternalAnnotationFunction(id, args);
	}
	;

nullExpression returns [IStringExpression expr = null]
	:
	NULL {expr = expressionFactory.createNullExpression();}
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

dottedIdWithIndex returns [Token token = null ]
@init {CommonToken ct = null;}
	:
	id = Identifier {ct = new CommonToken(id);}
	(
	lb =  LBRACK {ct.setText(ct.getText() + lb.getText());}
	index = DecimalLiteral {ct.setText(ct.getText() + index.getText());}
	rb = RBRACK {ct.setStopIndex(getBounds(rb)[1]);ct.setText(ct.getText() + rb.getText());}
	|
	dot = DOT {ct.setText(ct.getText() + dot.getText());}
	id = Identifier {ct.setStopIndex(getBounds(id)[1]);ct.setText(ct.getText() + id.getText());}
	)+
	{token = ct; return token;}
	;

dottedIdWithIndex2 returns [Token token = null ]
@init {CommonToken ct = null;}
	:
	id = Identifier {ct = new CommonToken(id);}
	(
	lb =  LBRACK {ct.setText(ct.getText() + lb.getText());}
	index = DecimalLiteral {ct.setText(ct.getText() + index.getText());}
	rb = RBRACK {ct.setStopIndex(getBounds(rb)[1]);ct.setText(ct.getText() + rb.getText());}
	|
	dot = DOT {ct.setText(ct.getText() + dot.getText());}
	id = Identifier {ct.setStopIndex(getBounds(id)[1]);ct.setText(ct.getText() + id.getText());}
	)*
	{token = ct; return token;}
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
	{expr = expressionFactory.createExternalWordListExpression(name, args);}
	|
	path = RessourceLiteral
	{expr = expressionFactory.createLiteralWordListExpression(path);}
	|
	id = Identifier
	{expr = expressionFactory.createReferenceWordListExpression(id);}
	;

wordListOrStringExpression returns [WordListExpression expr = null]
	:
	(stringExpression)=> string = stringExpression
	{expr = expressionFactory.createStringWordListExpression(string);}
	|	
	e = wordListExpression
	{expr = e;}
	;

wordTableExpression returns [WordTableExpression expr = null]
@init  {
List<IStringExpression> args = new ArrayList<IStringExpression>();
}
	:
	RESOURCE LPAREN name = dottedId (COMMA arg = stringExpression {args.add(arg);} )* RPAREN
	{expr = expressionFactory.createExternalWordTableExpression(name, args);}
	|
	path = RessourceLiteral
	{expr = expressionFactory.createLiteralWordTableExpression(path);}
	|
	id = Identifier
	{expr = expressionFactory.createReferenceWordTableExpression(id);}
	;

wordTableOrStringExpression returns [WordTableExpression expr = null]
	:
	(stringExpression)=>string = stringExpression
	{expr = expressionFactory.createStringWordTableExpression(string);}
	|	
	e = wordTableExpression
	{expr = e;}
	|
	;

// not checked
numberFunction returns [INumberExpression expr = null]
	:
	(op=(EXP | LOGN | SIN | COS | TAN ) numExprP=numberExpressionInPar)
	{expr = expressionFactory.createComposedNumberExpression(numExprP,op);}
	| op = POW LPAREN n1 = numberExpression COMMA n2 = numberExpression RPAREN
	{expr = expressionFactory.createComposedNumberExpression(n1,op, n2);}
	//| {root = expressionFactory.createNumberFunction(numExprP,op)}
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
	{expr = expressionFactory.createComposedNumberExpression(exprs,ops);}
	;

multiplicativeExpression returns [INumberExpression expr = null]
@init{List<INumberExpression> exprs = new ArrayList<INumberExpression>();
	List<Token> ops = new ArrayList<Token>();}
	:	
	(e = simpleNumberExpression{exprs.add(e);} (( STAR | SLASH | PERCENT )=> op = ( STAR | SLASH | PERCENT ){ops.add(op);} e = simpleNumberExpression{exprs.add(e);} )*	
	{expr = expressionFactory.createComposedNumberExpression(exprs,ops);}
	//| nl = numberListExpression LBRACK index = numberExpression RBRACK {expr = expressionFactory.createNumberListIndexExpression(nl, index);}
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
	(featureExpression)=> fe = featureExpression {expr = expressionFactory.createNumberFeatureExpression(fe);}	
	| m = MINUS? lit = DecimalLiteral {expr = expressionFactory.createIntegerExpression(lit,m);} 
	// TODO what about float numbers?
	| m = MINUS? lit = FloatingPointLiteral {expr = expressionFactory.createDoubleExpression(lit,m);}
	| m = MINUS? var = numberVariable {expr = expressionFactory.createReferenceNumberExpression(var,m);}
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
	(featureExpression)=> fe = featureExpression {expr = expressionFactory.createStringFeatureExpression(fe);}
	//|(stringListExpression)=> sl = stringListExpression LBRACK index = numberExpression RBRACK {expr = expressionFactory.createStringListIndexExpression(sl, index);}
	| e = simpleStringExpression {exprs.add(e);} 
	((PLUS)=>PLUS (
		(numberExpressionInPar)=> e2 = numberExpressionInPar {exprs.add(e2);}
		| arg = simpleArgument {if(arg instanceof IStringExpression) {exprs.add((IStringExpression)arg);}}
		//| le = listExpression {exprs.add(le);}
		))*
	{expr = expressionFactory.createComposedStringExpression(exprs);}
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
	lit = StringLiteral {expr = expressionFactory.createSimpleStringExpression(lit);} 
	| {isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "STRING")}? id = Identifier {expr = expressionFactory.createReferenceStringExpression(id);} 
	;


booleanExpression returns [IBooleanExpression expr = null]
	:
	(featureExpression)=> fe = featureExpression {expr = expressionFactory.createBooleanFeatureExpression(fe);}
	| (e = composedBooleanExpression)=> e = composedBooleanExpression {expr = e;}
	|sbE =  simpleBooleanExpression {expr = sbE;}
	//| bl = booleanListExpression LBRACK index = numberExpression RBRACK {expr = expressionFactory.createBooleanListIndexExpression(bl, index);}
	;

simpleBooleanExpression returns [IBooleanExpression expr = null]
	:
	 e = literalBooleanExpression {expr = e;}
	| {isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "BOOLEAN")}? 
	id = Identifier {expr = expressionFactory.createReferenceBooleanExpression(id);} 
	;

// not checked
composedBooleanExpression returns [IBooleanExpression expr = null]

	:
	(e2 = booleanCompare)=> e2 = booleanCompare {expr = e2;}
	| (bne = booleanNumberExpression)=> bne = booleanNumberExpression{expr = bne;}
	| (bse = booleanStringExpression)=> bse = booleanStringExpression{expr = bse;}
	| (bale = booleanAnnotationListExpression)=> bale = booleanAnnotationListExpression{expr = bale;}
	| (bae = booleanAnnotationExpression)=> bae = booleanAnnotationExpression{expr = bae;}
	| (bte = booleanTypeExpression)=> bte = booleanTypeExpression{expr = bte;}
	| e1 = booleanFunction {expr = e1;}
	| LPAREN ep = booleanExpression RPAREN {expr = ep;}
	;

// not checked
booleanFunction returns [IBooleanExpression expr = null]

	:
	(op = XOR LPAREN e1 = booleanExpression COMMA e2 = booleanExpression RPAREN)
	{expr = expressionFactory.createBooleanFunction(op,e1,e2);}
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
	{expr = expressionFactory.createBooleanFunction(op,e1,e2);}
	;


literalBooleanExpression returns  [IBooleanExpression expr = null]
	:
	v = TRUE {expr = expressionFactory.createSimpleBooleanExpression(v);} 
	| v = FALSE {expr = expressionFactory.createSimpleBooleanExpression(v);}
	;



booleanTypeExpression  returns  [IBooleanExpression expr = null]
	:
	e1 = typeExpression
	op = (EQUAL | NOTEQUAL)
	e2 = typeExpression
	{expr = expressionFactory.createBooleanTypeExpression(e1,op,e2);}
	;

booleanAnnotationExpression  returns  [IBooleanExpression expr = null]
	:
	e1 = annotationExpression
	op = (EQUAL | NOTEQUAL)
	e2 = annotationExpression
	{expr = expressionFactory.createBooleanAnnotationExpression(e1,op,e2);}
	;

booleanAnnotationListExpression  returns  [IBooleanExpression expr = null]
	:
	e1 = annotationListExpression
	op = (EQUAL | NOTEQUAL)
	e2 = annotationListExpression
	{expr = expressionFactory.createBooleanAnnotationListExpression(e1,op,e2);}
	;
	
booleanStringExpression  returns  [IBooleanExpression expr = null]
	:
	e1 = stringExpression
	op = (EQUAL | NOTEQUAL)
	(
	e2 = stringExpression
	| e2 = nullExpression
	)
	{expr = expressionFactory.createBooleanStringExpression(e1,op,e2);}
	;

booleanNumberExpression  returns  [IBooleanExpression expr = null]
	:
	//LPAREN
	e1 = numberExpression//{exprs.add(e);} 
	op = (LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL)//{ops.add(op);} 
	e2 = numberExpression//{exprs.add(e);}
	//RPAREN
	{expr = expressionFactory.createBooleanNumberExpression(e1,op,e2);}
	;
	