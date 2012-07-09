parser grammar TextMarkerParser;

options {
	language = Java;
	tokenVocab = TextMarkerLexer;
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

package org.apache.uima.textmarker.parser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
import org.apache.uima.resource.metadata.TypeSystemDescription;

import org.apache.uima.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.textmarker.action.ActionFactory;
import org.apache.uima.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.textmarker.condition.ConditionFactory;
import org.apache.uima.textmarker.TextMarkerAutomataBlock;
import org.apache.uima.textmarker.TextMarkerBlock;
import org.apache.uima.textmarker.TextMarkerModule;
import org.apache.uima.textmarker.TextMarkerScriptBlock;
import org.apache.uima.textmarker.TextMarkerScriptFactory;
import org.apache.uima.textmarker.TextMarkerAutomataFactory;
import org.apache.uima.textmarker.TextMarkerStatement;
import org.apache.uima.textmarker.expression.ExpressionFactory;
import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.list.BooleanListExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.expression.list.NumberListExpression;
import org.apache.uima.textmarker.expression.list.StringListExpression;
import org.apache.uima.textmarker.expression.list.TypeListExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.resource.WordListExpression;
import org.apache.uima.textmarker.expression.resource.WordTableExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.string.StringFunctionFactory;
import org.apache.uima.textmarker.expression.type.TypeExpression;
import org.apache.uima.textmarker.extensions.TextMarkerExternalFactory;
import org.apache.uima.textmarker.rule.ComposedRuleElement;
import org.apache.uima.textmarker.rule.RuleElement;
import org.apache.uima.textmarker.rule.RuleElementContainer;
import org.apache.uima.textmarker.rule.RuleElementIsolator;
import org.apache.uima.textmarker.rule.TextMarkerRule;
import org.apache.uima.textmarker.rule.TextMarkerRuleElement;
import org.apache.uima.textmarker.rule.quantifier.RuleElementQuantifier;
}

@parser::members {
private List vars = new ArrayList();	
private int level = 0;
private TextMarkerScriptFactory factory = new TextMarkerScriptFactory();
private TextMarkerScriptFactory automataFactory = new TextMarkerAutomataFactory();
private TextMarkerExternalFactory external;


public void setExternalFactory(TextMarkerExternalFactory factory) {
	external = factory;
}

public void emitErrorMessage(String msg) {
		System.out.println(msg);
	}
	public void reportError(RecognitionException e) {
		System.out.println(e);
	}

	//public void addVariable(String var, IntStream input) throws NoViableAltException {
	//	if(!vars.contains(var)) {
	//		vars.add(var);
	//	} else {
	//		throw new NoViableAltException("already declared \"" + var + "\"", 3, 0, input);
	//	}
	//}
	public void addVariable(TextMarkerBlock parent, String name, String type) {
		parent.getEnvironment().addVariable(name, type);
	}
	
	public boolean ownsVariable(TextMarkerBlock parent, String name) {
		return parent.getEnvironment().ownsVariable(name);
	}
	public boolean isVariable(TextMarkerBlock parent, String name) {
		return parent.getEnvironment().isVariable(name);
	}
	
	public void setValue(TextMarkerBlock parent, List<String> names, Object obj) {
		for(String name : names) {
			setValue(parent,name,obj);
		}
	}
	
	public void setValue(TextMarkerBlock parent, String name, Object obj) {
		if(obj != null) {
			Object value = parent.getEnvironment().getLiteralValue(name, obj);
			parent.getEnvironment().setVariableValue(name, value);
			parent.getEnvironment().setInitialVariableValue(name, value);
		}
	}
	
	public boolean ownsVariableOfType(TextMarkerBlock parent, String name, String type) {
		return parent.getEnvironment().ownsVariableOfType(name,type);
	}
	
	public boolean isVariableOfType(TextMarkerBlock parent, String name, String type) {
		return parent.getEnvironment().isVariableOfType(name,type);
	}
	
	public void addType(TextMarkerBlock parent, String type) {
	}
	
	public void addType(TextMarkerBlock parent, String name, String parentType, List featuresTypes, List featuresNames) {
    	}
	
	public boolean isType(TextMarkerBlock parent, String type) {
		return parent.getEnvironment().getType(type) != null || type.equals("Document");
	}
	
	public void checkVariable(String var, IntStream input) throws NoViableAltException {
		if(!vars.contains(var)) {
			throw new NoViableAltException("not declared \"" + var + "\"", 3, 0, input);
		}
	}
	
	public void addImportTypeSystem(TextMarkerBlock parent, String descriptor) {
		//parent.getEnvironment().addTypeSystem(descriptor);
	}
	public void addImportScript(TextMarkerBlock parent, String namespace) {
		parent.getScript().addScript(namespace, null);
	}
	public void addImportEngine(TextMarkerBlock parent, String namespace) {
		parent.getScript().addEngine(namespace, null);
	}
	
	
	protected static final int[] getBounds(Token t) {
    		if (t instanceof CommonToken) {
    			CommonToken ct = (CommonToken) t;
    			int[] bounds = {ct.getStartIndex(), ct.getStopIndex()}; 
    			return bounds;
    		}
    		return null;
    	}
	
	private TypeSystemDescription localTSD;
	private String[] resourcePaths;
	

	public void setResourcePaths(String[] resourcePaths) {
		this.resourcePaths = resourcePaths;
	}
	
	 public void setLocalTSD(TypeSystemDescription localTSD) {
      		this.localTSD = localTSD;
   	 }
}

@rulecatch {
	catch (RecognitionException exception1) {
		emitErrorMessage(exception1.toString());
	}
	catch (Throwable exception2) {
		emitErrorMessage(exception2.toString());
	}
}


file_input [String moduleName] returns [TextMarkerModule module]
@init{
TextMarkerScriptBlock rootBlock = null;
List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>();
}
	:
	p = packageDeclaration	
	{
	rootBlock = factory.createRootScriptBlock(moduleName, p, localTSD);
        rootBlock.getEnvironment().setResourcePaths(resourcePaths);
	rootBlock.setElements(stmts);
	module = new TextMarkerModule(rootBlock);
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

statements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()]
	:
	(stmt = statement {{if(stmt != null) {stmts.add(stmt);}}})*
	;

globalStatements returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()]
	:
	(morestmts = globalStatement {if(morestmts != null) {stmts.addAll(morestmts);}})*
	;

globalStatement returns [List<TextMarkerStatement> stmts = new ArrayList<TextMarkerStatement>()]
	:
	stmtImport = importStatement {stmts.add(stmtImport);}
	;
	
statement returns [TextMarkerStatement stmt = null]
	:	
	( stmtDecl = declaration {stmt = stmtDecl;}
	| stmtVariable = variableDeclaration {stmt = stmtVariable;}
	| stmtRule = simpleStatement {stmt = stmtRule;}
	| stmtBlock = blockDeclaration {stmt = stmtBlock;}
	| stmtAutomata = automataDeclaration {stmt = stmtBlock;}
	)
	;

variableDeclaration returns [TextMarkerStatement stmt = null]
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
		 )* (ASSIGN_EQUAL value5 = annotationType)? {setValue($blockDeclaration::env, vars, value5);} SEMI
	| 
	type = WORDLIST 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL list = wordListExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(list != null){setValue($blockDeclaration::env, name.getText(), list);}} 
	| 
	type = WORDTABLE 
	{!isVariableOfType($blockDeclaration::env, input.LT(1).getText(), type.getText())}? 
	name = Identifier (ASSIGN_EQUAL table = wordTableExpression)? SEMI {addVariable($blockDeclaration::env, name.getText(), type.getText());if(table != null){setValue($blockDeclaration::env, name.getText(), table);}}
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
//conditionDeclaration returns [TextMarkerStatement stmt = null]
  //  :
//    type = CONDITION id = Identifier ASSIGN_EQUAL LPAREN cons = conditions RPAREN SEMI
//    {addVariable($blockDeclaration::env, id.getText(), type.getText());
//    AbstractTextMarkerCondition condition = ConditionFactory.createConditionAnd(cons,$blockDeclaration::env);
//    setValue($blockDeclaration::env, id.getText(), condition);}
//    ;

//TODO added rule
//actionDeclaration returns [TextMarkerStatement stmt = null]
//    :
//    type = ACTION id = Identifier ASSIGN_EQUAL LPAREN a = actions RPAREN SEMI
//    {addVariable($blockDeclaration::env, id.getText(), type.getText());
//    AbstractTextMarkerAction action = ActionFactory.createComposedAction(a,$blockDeclaration::env);
//    setValue($blockDeclaration::env, id.getText(), action);}
//    ;

importStatement returns [TextMarkerStatement stmt = null]
	:
	TypeSystemString ts = dottedIdentifier2{addImportTypeSystem($blockDeclaration::env, ts);} SEMI
	| ScriptString ns = dottedIdentifier2{addImportScript($blockDeclaration::env, ns);} SEMI
	| EngineString ns = dottedIdentifier2{addImportEngine($blockDeclaration::env, ns);} SEMI
	;

declaration returns [TextMarkerStatement stmt = null]
@init {
List featureTypes = new ArrayList();
List featureNames = new ArrayList();
}
	:
	(
	DECLARE 
	//{!isType($blockDeclaration::env, input.LT(1).getText())}? 
	lazyParent = annotationType?
	id = Identifier //{addType($blockDeclaration::env, id.getText(), lazyParent);}
			(COMMA 
			//{!isType($blockDeclaration::env, input.LT(1).getText())}? 
			id = Identifier //{addType($blockDeclaration::env, id.getText(), lazyParent);}
		 )* SEMI
	| 
	DECLARE type = annotationType newName = Identifier 
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
		{addType($blockDeclaration::env, newName.getText(), type.getText(), featureTypes, featureNames);}
	)
	;
	
	
blockDeclaration returns [TextMarkerBlock block = null]
options {
	backtrack = true;
}

scope {
	TextMarkerBlock env;
}
@init{
TextMarkerRuleElement re = null;
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
	{TextMarkerRule rule = factory.createRule(re, block);
	if(block instanceof TextMarkerScriptBlock) {
	((TextMarkerScriptBlock)block).setRule(rule);
	}
	container.setContainer(rule);
	}
	LCURLY body = statements RCURLY
	{block.setElements(body);
	$blockDeclaration::env.getScript().addBlock(id.getText(),block);
	}	
	;

automataDeclaration returns [TextMarkerBlock block = null]
options {
	backtrack = true;
}

scope {
	TextMarkerBlock env;
}
@init{
TextMarkerRuleElement re = null;
RuleElementIsolator container = null;
TextMarkerScriptFactory oldFactory = factory;
factory = automataFactory; 
level++;
}
@after {
factory = oldFactory;
level--;
}
	:
	
	type = AutomataBlockString 
	LPAREN
	id = Identifier 
	RPAREN
	{block = factory.createAutomataBlock(id, re, body, $blockDeclaration[level - 1]::env);}
	{$blockDeclaration::env = block;
	container = new RuleElementIsolator();}
	re1 = ruleElementWithCA[container] {re = re1;}
	{TextMarkerRule rule = factory.createRule(re, block);
	if(block instanceof TextMarkerAutomataBlock) {
	((TextMarkerAutomataBlock)block).setMatchRule(rule);
	}
	container.setContainer(rule);
	}
	LCURLY body = statements RCURLY
	{block.setElements(body);
	$blockDeclaration::env.getScript().addBlock(id.getText(),block);

	}	
	;

	
ruleElementWithCA[RuleElementContainer container] returns [TextMarkerRuleElement re = null] 
    :
    
    idRef=typeExpression 
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


	
simpleStatement returns [TextMarkerRule stmt = null]
	: 
	{stmt = factory.createRule(elements, $blockDeclaration::env);}
	elements = ruleElements[stmt.getRoot()] SEMI 
		{stmt.setRuleElements(elements);}
	;
	
ruleElements[RuleElementContainer container] returns [List<RuleElement> elements = new ArrayList<RuleElement>()]
	:
	re = ruleElement[container] {elements.add(re);} (re = ruleElement[container] {elements.add(re);})*
	;
		

ruleElement[RuleElementContainer container] returns [RuleElement re = null]
	:
	re1 = ruleElementType[container] {re = re1;}
	| re2 = ruleElementLiteral[container] {re = re2;}
	| (ruleElementComposed[null])=>re3 = ruleElementComposed[container] {re = re3;}
	| (ruleElementDisjunctive[null])=> re4 = ruleElementDisjunctive[container] {re = re4;}
	;	

ruleElementDisjunctive [RuleElementContainer container] returns [TextMarkerRuleElement re = null]
@init{
	List<TextMarkerExpression> exprs = new ArrayList<TextMarkerExpression>();
}
    :
    LPAREN
    ((typeExpression | simpleStringExpression) VBAR)=>  (e11 =typeExpression | e12 =simpleStringExpression) 
    {if(e11 != null) exprs.add(e11);if(e12 != null) exprs.add(e12);} 
    VBAR (e21 =typeExpression | e22 =simpleStringExpression) 
    {if(e21 != null) exprs.add(e21);if(e22 != null) exprs.add(e22);} 
    (
    VBAR  (e31 =typeExpression | e32 =simpleStringExpression) 
    {if(e31 != null) exprs.add(e31);if(e32 != null) exprs.add(e32);} 
    )*
    RPAREN
     { re = factory.createRuleElement(exprs, null, null, null, container, $blockDeclaration::env);}   
    
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

ruleElementComposed [RuleElementContainer container] returns [ComposedRuleElement re = null]
scope {
	RuleElementContainer con;
}
@init{

}	
	:
	{re = factory.createComposedRuleElement(container, $blockDeclaration::env);
	// dre = factory.createDisjunctiveRuleElement(container, $blockDeclaration::env);
	$ruleElementComposed::con = re;}
	LPAREN 
	
	(
	//((ruleElement[$ruleElementComposed::con] VBAR)=>re1 = ruleElement[dre]
	// {disjunctive = true; res = new ArrayList<RuleElement>();res.add(re1);} 
	// VBAR re2 = ruleElement[dre] {res.add(re2);}
	//(VBAR re3 = ruleElement[dre] {res.add(re3);})*)
	//|
	(ruleElements[$ruleElementComposed::con])=>res = ruleElements[$ruleElementComposed::con] 
	)
	
	RPAREN q = quantifierPart? (LCURLY c = conditions? (THEN a = actions)? RCURLY)?
	{
	re.setRuleElements(res);
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


ruleElementType [RuleElementContainer container] returns [TextMarkerRuleElement re = null]
    :
    
    (typeExpression)=>typeExpr = typeExpression 
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

ruleElementLiteral [RuleElementContainer container] returns [TextMarkerRuleElement re = null]
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
	
conditions returns [List<AbstractTextMarkerCondition> conds = new ArrayList<AbstractTextMarkerCondition>()]
    :
    c = condition {conds.add(c);} (COMMA c = condition {conds.add(c);} )*
    ;
    
actions returns [List<AbstractTextMarkerAction> actions = new ArrayList<AbstractTextMarkerAction>()]
    :
    a = action {actions.add(a);} (COMMA a = action {actions.add(a);} )*
    ; 	


listExpression returns [ListExpression expr = null]
	:
	(booleanListExpression)=> bl = booleanListExpression {expr = bl;}
	| (intListExpression)=> il = intListExpression {expr = il;}
	| (doubleListExpression)=> dl = doubleListExpression {expr = dl;}
	| (floatListExpression)=> dl = floatListExpression {expr = dl;}
	| (stringListExpression)=> sl = stringListExpression {expr = sl;}
	| (typeListExpression)=> tl = typeListExpression {expr = tl;}
	;

booleanListExpression returns [BooleanListExpression expr = null]
	:
	e = simpleBooleanListExpression {expr = e;}
	;

simpleBooleanListExpression returns [BooleanListExpression expr = null]
@init{
	List<BooleanExpression> list = new ArrayList<BooleanExpression>();
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
	List<NumberExpression> list = new ArrayList<NumberExpression>();
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
	List<NumberExpression> list = new ArrayList<NumberExpression>();
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
	List<NumberExpression> list = new ArrayList<NumberExpression>();
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
	List<StringExpression> list = new ArrayList<StringExpression>();
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
	//{isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "TYPEFUNCTION")}? 
	id = Identifier LPAREN
	args = varArgumentList	RPAREN
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
	 {if(q != null) {quantifier = TextMarkerScriptFactory.createStarReluctantQuantifier();} 
	 	else{quantifier = TextMarkerScriptFactory.createStarGreedyQuantifier();}}
	| PLUS q = QUESTION?
	 {if(q != null) {quantifier = TextMarkerScriptFactory.createPlusReluctantQuantifier();}
	 else {quantifier = TextMarkerScriptFactory.createPlusGreedyQuantifier();}}
	| QUESTION q = QUESTION? 
	 {if(q != null) {quantifier = TextMarkerScriptFactory.createQuestionReluctantQuantifier();} 
	 else {quantifier = TextMarkerScriptFactory.createQuestionGreedyQuantifier();}}
	| LBRACK min = numberExpression (comma = COMMA (max = numberExpression)?)? RBRACK q = QUESTION?
	 {if(q != null) {quantifier = TextMarkerScriptFactory.createMinMaxReluctantQuantifier(min,max,comma);} 
	 else {quantifier = TextMarkerScriptFactory.createMinMaxGreedyQuantifier(min,max,comma);}}	
	;


condition  returns [AbstractTextMarkerCondition result = null]
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
	| (c = externalCondition)=> c = externalCondition
//	| c = variableCondition
	) {result = c;}
	;


//variableCondition returns [AbstractTextMarkerCondition condition = null]
//	:		
//	
//	id = Identifier
//	{
//		condition = ConditionFactory.createConditionVariable(id);
//	}
//	;

externalCondition returns [AbstractTextMarkerCondition condition = null]
	:		
	
	id = Identifier LPAREN args = varArgumentList	RPAREN
	{
		condition = external.createExternalCondition(id, args);
	}
	;
	
conditionAnd returns [AbstractTextMarkerCondition cond = null]
    :   
    AND LPAREN conds = conditions RPAREN 
    {cond = ConditionFactory.createConditionAnd(conds, $blockDeclaration::env);}
    ;
    
conditionContains returns [AbstractTextMarkerCondition cond = null]
 options {
	backtrack = true;
}
    :   
    CONTAINS LPAREN (type = typeExpression | list = listExpression COMMA a = argument) 
    (COMMA min = numberExpression COMMA max = numberExpression (COMMA percent = booleanExpression)?)? RPAREN
    {if(type != null) {cond = ConditionFactory.createConditionContains(type, min, max, percent,$blockDeclaration::env);}
    else {cond = ConditionFactory.createConditionContains(list,a, min, max, percent, $blockDeclaration::env);};}
    ;
conditionContextCount returns [AbstractTextMarkerCondition cond = null]
    :   
    CONTEXTCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionContextCount(type, min, max, var, $blockDeclaration::env);}
    ;
conditionCount returns [AbstractTextMarkerCondition cond = null]
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
conditionTotalCount returns [AbstractTextMarkerCondition cond = null]
    :   
    TOTALCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)?
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionTotalCount(type, min, max, var, $blockDeclaration::env);}
    ;
conditionCurrentCount returns [AbstractTextMarkerCondition cond = null]
    :   
    CURRENTCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionCurrentCount(type, min, max, var,$blockDeclaration::env);}
    ;
conditionInList returns [AbstractTextMarkerCondition cond = null]
 options {
	backtrack = true;
}
    :
    INLIST LPAREN ((list2 = stringListExpression)=>list2 = stringListExpression | list1 = wordListExpression) (COMMA dist = numberExpression (COMMA rel = booleanExpression)?)? RPAREN
    {if(list1 != null) {cond = ConditionFactory.createConditionInList(list1, dist, rel,$blockDeclaration::env);}
    else {cond = ConditionFactory.createConditionInList(list2, dist, rel,$blockDeclaration::env);};}
    ;

    
conditionLast returns [AbstractTextMarkerCondition cond = null]
    :   
    LAST LPAREN type = typeExpression RPAREN
    {cond = ConditionFactory.createConditionLast(type, $blockDeclaration::env);}    
    ;
    
    
conditionMofN returns [AbstractTextMarkerCondition cond = null]
    :   
    MOFN LPAREN min = numberExpression COMMA max = numberExpression COMMA conds = conditions RPAREN
    {cond = ConditionFactory.createConditionMOfN(conds, min, max, $blockDeclaration::env);} 
    ;

conditionNear returns [AbstractTextMarkerCondition cond = null]
    :   
    NEAR LPAREN type = typeExpression COMMA min = numberExpression COMMA max = numberExpression (COMMA direction = booleanExpression (COMMA filtered = booleanExpression)?)? RPAREN
    {cond = ConditionFactory.createConditionNear(type, min, max, direction, filtered, $blockDeclaration::env);} 
    ;
conditionNot returns [AbstractTextMarkerCondition cond = null]
    :   
    ((MINUS c = condition) |  (NOT LPAREN c = condition RPAREN))
    {cond = ConditionFactory.createConditionNot(c, $blockDeclaration::env);}    
    ;
conditionOr returns [AbstractTextMarkerCondition cond = null]
    :   
    OR LPAREN conds = conditions RPAREN
    {cond = ConditionFactory.createConditionOr(conds, $blockDeclaration::env);}
    ;
conditionPartOf returns [AbstractTextMarkerCondition cond = null]
    :
    PARTOF LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionPartOf(type1, type2, $blockDeclaration::env);}
    ;
conditionPartOfNeq returns [AbstractTextMarkerCondition cond = null]
    :
    PARTOFNEQ LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionPartOfNeq(type1, type2, $blockDeclaration::env);}
    ;

conditionPosition returns [AbstractTextMarkerCondition cond = null]
    :   
    POSITION LPAREN type = typeExpression COMMA pos = numberExpression RPAREN
    {cond = ConditionFactory.createConditionPosition(type, pos, $blockDeclaration::env);}
    ;
conditionRegExp returns [AbstractTextMarkerCondition cond = null]
    :
    REGEXP LPAREN pattern = stringExpression (COMMA caseSensitive = booleanExpression)? RPAREN
    {cond = ConditionFactory.createConditionRegExp(pattern, caseSensitive, $blockDeclaration::env);}    
    ;

conditionScore returns [AbstractTextMarkerCondition cond = null]
    :
    SCORE LPAREN min = numberExpression (COMMA max = numberExpression
    (COMMA var = numberVariable)?)?  RPAREN
    {cond = ConditionFactory.createConditionScore(min, max, var, $blockDeclaration::env);}
    ;


conditionVote returns [AbstractTextMarkerCondition cond = null]
    :   
    VOTE LPAREN type1 = typeExpression COMMA type2 = typeExpression RPAREN
    {cond = ConditionFactory.createConditionVote(type1, type2, $blockDeclaration::env);}
    ;
    
conditionIf returns [AbstractTextMarkerCondition cond = null]
    :   
    IF LPAREN e = booleanExpression RPAREN
    {cond = ConditionFactory.createConditionIf(e, $blockDeclaration::env);}
    ;

conditionFeature returns [AbstractTextMarkerCondition cond = null]
    :   
    FEATURE LPAREN se = stringExpression COMMA v = argument RPAREN
    {cond = ConditionFactory.createConditionFeature(se, v, $blockDeclaration::env);}
    ;   

conditionParse returns [AbstractTextMarkerCondition cond = null]
    :
    PARSE LPAREN {isVariable($blockDeclaration::env,input.LT(1).getText())}? id = Identifier RPAREN
    {cond = ConditionFactory.createConditionParse(id, $blockDeclaration::env);}
    ;

conditionIs returns [AbstractTextMarkerCondition cond = null]
    :
    IS LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionIs(type1, type2, $blockDeclaration::env);}
    ;

conditionBefore returns [AbstractTextMarkerCondition cond = null]
    :
    BEFORE LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionBefore(type1,type2, $blockDeclaration::env);}
    ;

conditionAfter returns [AbstractTextMarkerCondition cond = null]
    :
    AFTER LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionAfter(type1,type2,$blockDeclaration::env);}
    ;

conditionStartsWith returns [AbstractTextMarkerCondition cond = null]
    :
    STARTSWITH LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionStartsWith(type1,type2, $blockDeclaration::env);}
    ;

conditionEndsWith returns [AbstractTextMarkerCondition cond = null]
    :
    ENDSWITH LPAREN (type1 = typeExpression|type2 = typeListExpression) RPAREN
    {cond = ConditionFactory.createConditionEndsWith(type1,type2,$blockDeclaration::env);}
    ;

conditionSize returns [AbstractTextMarkerCondition cond = null]
    :
    SIZE LPAREN list = listExpression (COMMA min = numberExpression COMMA max = numberExpression)? (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createConditionSize(list, min, max, var,$blockDeclaration::env);}
    ;

action  returns [AbstractTextMarkerAction result = null]
	:
	(
	a = actionColor
	| a = actionDel
	| a = actionLog
	| a = actionMark
	| a = actionMarkScore
	| a = actionMarkFast
	| a = actionMarkLast
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
	| a = actionExpand
	| a = actionConfigure
	| a = actionDynamicAnchoring 
	| (a = externalAction)=> a = externalAction
//	| a = variableAction
	) {result = a;}
	;

//variableAction returns [AbstractTextMarkerAction action = null]
//	:		
//	
//	id = Identifier
//	{
//		action = ActionFactory.createActionVariable(id);
//	}
//	;


externalAction returns [AbstractTextMarkerAction action = null]
	:		
	
	id = Identifier LPAREN args = varArgumentList	RPAREN
	{
		action = external.createExternalAction(id, args);
	}
	;



actionCreate returns [AbstractTextMarkerAction action = null]
@init {
	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();
    	List<NumberExpression> indexes = new ArrayList<NumberExpression>();
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


actionMarkTable returns [AbstractTextMarkerAction action = null]
@init {
	Map<StringExpression, NumberExpression> map = new HashMap<StringExpression, NumberExpression>();
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
 
actionGather returns [AbstractTextMarkerAction action = null]
@init {
	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();
    	List<NumberExpression> indexes = new ArrayList<NumberExpression>();
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

  

actionFill returns [AbstractTextMarkerAction action = null]
@init {
Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();
}
    :
    FILL LPAREN type = typeExpression (COMMA fname = stringExpression ASSIGN_EQUAL 
    (
    obj1 = argument{map.put(fname,obj1);} 
    )
    )+ RPAREN
    {action = ActionFactory.createFillAction(type, map, $blockDeclaration::env);}
    ;
    
actionColor returns [AbstractTextMarkerAction action = null]
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

actionDel returns [AbstractTextMarkerAction action = null]
    :   
    DEL
    {action = ActionFactory.createDelAction($blockDeclaration::env);}
    ;
        
actionLog returns [AbstractTextMarkerAction action = null]
    :   
    LOG LPAREN lit = stringExpression (COMMA log = LogLevel)? RPAREN
    {action = ActionFactory.createLogAction(lit, log, $blockDeclaration::env);}
    ;

actionMark returns [AbstractTextMarkerAction action = null]
@init {
List<NumberExpression> list = new ArrayList<NumberExpression>();
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

actionExpand returns [AbstractTextMarkerAction action = null]
@init {
List<NumberExpression> list = new ArrayList<NumberExpression>();
}
    :   
    EXPAND LPAREN 
    type = typeExpression
    (
        COMMA (index = numberExpression) => index = numberExpression
        {list.add(index);}
    )*
     RPAREN
    
    {action = ActionFactory.createExpandAction(type, list,$blockDeclaration::env);}
    ;


actionMarkScore returns [AbstractTextMarkerAction action = null]
@init {
List<NumberExpression> list = new ArrayList<NumberExpression>();
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

actionMarkOnce returns [AbstractTextMarkerAction action = null]
@init {
List<NumberExpression> list = new ArrayList<NumberExpression>();
}
    :   
    MARKONCE LPAREN ((score = numberExpression) => score = numberExpression COMMA)? (type = typeExpression) => type = typeExpression
    (
        COMMA (index = numberExpression) => index = numberExpression
        {list.add(index);}
    )* RPAREN
    
    {action = ActionFactory.createMarkOnceAction(score, type, list,$blockDeclaration::env);}
    ;

actionMarkFast returns [AbstractTextMarkerAction action = null]
    :   
    MARKFAST LPAREN type = typeExpression COMMA list = wordListExpression (COMMA ignore = booleanExpression (COMMA ignoreLength = numberExpression)?)? RPAREN
    {action = ActionFactory.createMarkFastAction(type, list, ignore, ignoreLength, $blockDeclaration::env);}
    ;

actionMarkLast returns [AbstractTextMarkerAction action = null]
    :   
    MARKLAST LPAREN type = typeExpression RPAREN
    {action = ActionFactory.createMarkLastAction(type, $blockDeclaration::env);}
    ;

actionReplace returns [AbstractTextMarkerAction action = null]
    :   
    REPLACE LPAREN lit = stringExpression RPAREN
    {action = ActionFactory.createReplaceAction(lit, $blockDeclaration::env);}
    ;
    
  

actionRetainType returns [AbstractTextMarkerAction action = null]
@init {
List<TypeExpression> list = new ArrayList<TypeExpression>();
}
    :   
    RETAINTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)?
    {action = ActionFactory.createRetainTypeAction(list, $blockDeclaration::env);}
    ;   
    
 

actionFilterType returns [AbstractTextMarkerAction action = null]
@init {
List<TypeExpression> list = new ArrayList<TypeExpression>();
}
    :   
    FILTERTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)?
    {action = ActionFactory.createFilterTypeAction(list,$blockDeclaration::env);}
    ;       

actionCall returns [AbstractTextMarkerAction action = null]
    :
    CALL LPAREN ns = dottedIdentifier RPAREN
    {action = ActionFactory.createCallAction(ns, $blockDeclaration::env);}
    ;


actionConfigure returns [AbstractTextMarkerAction action = null]
@init {
	Map<StringExpression, TextMarkerExpression> map = new HashMap<StringExpression, TextMarkerExpression>();
}

    :
    CONFIGURE LPAREN ns = dottedIdentifier  
   COMMA 
   fname = stringExpression ASSIGN_EQUAL obj1 = argument {map.put(fname,obj1);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = argument {map.put(fname,obj1);})*
    RPAREN
    {action = ActionFactory.createConfigureAction(ns, map, $blockDeclaration::env);}
    ;


actionExec returns [AbstractTextMarkerAction action = null]
    :
    EXEC LPAREN ns = dottedIdentifier (COMMA tl = typeListExpression)? RPAREN
    {action = ActionFactory.createExecAction(ns, tl, $blockDeclaration::env);}
    ;    
    
actionAssign returns [AbstractTextMarkerAction action = null]
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

actionSetFeature returns [AbstractTextMarkerAction action = null]
    :   
    name = SETFEATURE LPAREN f = stringExpression COMMA v = argument RPAREN
    {action = ActionFactory.createSetFeatureAction(f, v, $blockDeclaration::env);}
    ;


actionGetFeature returns [AbstractTextMarkerAction action = null]
    :   
    name = GETFEATURE LPAREN f = stringExpression COMMA v = variable RPAREN
    {action = ActionFactory.createGetFeatureAction(f, v, $blockDeclaration::env);}
    ;

//unknown
actionDynamicAnchoring returns [AbstractTextMarkerAction action = null]
    :
    name = DYNAMICANCHORING LPAREN active = booleanExpression 
    (COMMA penalty = numberExpression 
    (COMMA factor = numberExpression)?)? 
    {action = ActionFactory.createDynamicAnchoringAction(active, penalty, factor, $blockDeclaration::env);}
    RPAREN
    ;


actionUnmark returns [AbstractTextMarkerAction action = null]
    :
    name = UNMARK LPAREN f = typeExpression RPAREN
    {action = ActionFactory.createUnmarkAction(f,$blockDeclaration::env);}
    ;


actionUnmarkAll returns [AbstractTextMarkerAction action = null]
    :
    name = UNMARKALL LPAREN f = typeExpression 
    (COMMA list = typeListExpression)? RPAREN
    {action = ActionFactory.createUnmarkAllAction(f, list, $blockDeclaration::env);}
    ;

actionTransfer returns [AbstractTextMarkerAction action = null]
    :
    name = TRANSFER LPAREN f = typeExpression RPAREN
    {action = ActionFactory.createTransferAction(f, $blockDeclaration::env);}
    ;

actionTrie returns [AbstractTextMarkerAction action = null]
@init {
Map<StringExpression, TypeExpression> map = new HashMap<StringExpression, TypeExpression>();
}
    :
    name = TRIE LPAREN
    key = stringExpression ASSIGN_EQUAL value = typeExpression{map.put(key,value);}
    (COMMA key = stringExpression ASSIGN_EQUAL value = typeExpression{map.put(key,value);} )*
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

actionAdd returns [AbstractTextMarkerAction action = null]
@init{
	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();
} 
    :
    name = ADD LPAREN f = listVariable (COMMA a = argument {list.add(a);})+ RPAREN
    {action = ActionFactory.createAddAction(f, list, $blockDeclaration::env);}
    ;

actionRemove returns [AbstractTextMarkerAction action = null]
@init{
	List<TextMarkerExpression> list = new ArrayList<TextMarkerExpression>();
} 
    :
    name = REMOVE LPAREN f = listVariable (COMMA a = argument {list.add(a);})+ RPAREN
    {action = ActionFactory.createRemoveAction(f, list, $blockDeclaration::env);}
    ;
 
actionRemoveDuplicate returns [AbstractTextMarkerAction action = null]
    :
    name = REMOVEDUPLICATE LPAREN f = listVariable RPAREN
    {action = ActionFactory.createRemoveDuplicateAction(f,$blockDeclaration::env);}
    ; 
    
actionMerge returns [AbstractTextMarkerAction action = null]
@init{
	List<ListExpression> list = new ArrayList<ListExpression>();
} 
    :
    name = MERGE LPAREN join = booleanExpression COMMA t = listVariable COMMA f = listExpression {list.add(f);} (COMMA f = listExpression {list.add(f);})+ RPAREN
    {action = ActionFactory.createMergeAction(join, t, list,$blockDeclaration::env);}
    ;

actionGet returns [AbstractTextMarkerAction action = null]
    :
    name = GET LPAREN f = listExpression COMMA var = variable COMMA op = stringExpression RPAREN
    {action = ActionFactory.createGetAction(f, var, op,$blockDeclaration::env);}
    ;

actionGetList returns [AbstractTextMarkerAction action = null]
    :
    name = GETLIST LPAREN var = listVariable COMMA op = stringExpression RPAREN
    {action = ActionFactory.createGetListAction(var, op,$blockDeclaration::env);}
    ;

actionMatchedText returns [AbstractTextMarkerAction action = null]
@init {
List<NumberExpression> list = new ArrayList<NumberExpression>();
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

actionClear returns [AbstractTextMarkerAction action = null]
    :
    name = CLEAR LPAREN var = listVariable RPAREN
    {action = ActionFactory.createClearAction(var, $blockDeclaration::env);}
    ;


varArgumentList returns [List args = new ArrayList()]
	:
	(LPAREN arg = argument {args.add(arg);}(COMMA arg = argument {args.add(arg);})* RPAREN)?
	;

argument returns [TextMarkerExpression expr = null]
options {
	backtrack = true;
}
	:
	a4 = stringExpression {expr = a4;}
	| a2 = booleanExpression {expr = a2;}
	| a3 = numberExpression {expr = a3;}
	| a1 = typeExpression {expr = a1;}
	
	//(a2 = booleanExpression)=> a2 = booleanExpression {expr = a2;}
	//| (a3 = numberExpression)=> a3 = numberExpression {expr = a3;}
	//| (a4 = stringExpression)=> a4 = stringExpression {expr = a4;}
	//| (a1 = typeExpression)=> a1 = typeExpression {expr = a1;}
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

annotationType returns [Token ref = null]
	: 
	(
	//{isType($blockDeclaration::env, input.LT(1).getText())}? 
	token = BasicAnnotationType {ref = token;}
	|
	did = dottedId {ref = did;}
	)
	;

wordListExpression returns [WordListExpression expr = null]
	:
	id = Identifier
	{expr = ExpressionFactory.createReferenceWordListExpression(id);}
	|
	path = RessourceLiteral
	{expr = ExpressionFactory.createLiteralWordListExpression(path);}
	;


wordTableExpression returns [WordTableExpression expr = null]
	:
	id = Identifier
	{expr = ExpressionFactory.createReferenceWordTableExpression(id);}
	|
	path = RessourceLiteral
	{expr = ExpressionFactory.createLiteralWordTableExpression(path);}
	;

// not checked
numberFunction returns [NumberExpression expr = null]
	:
	(op=(EXP | LOGN | SIN | COS | TAN) numExprP=numberExpressionInPar)
	{expr = ExpressionFactory.createComposedNumberExpression(numExprP,op);}
	//| {root = ExpressionFactory.createNumberFunction(numExprP,op)}
	| (e = externalNumberFunction)=> e = externalNumberFunction {expr = e;}
	;


// not checked
externalNumberFunction returns [NumberExpression expr = null]
	:
	//{isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "NUMBERFUNCTION")}? 
	id = Identifier LPAREN
	args = varArgumentList RPAREN
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


additiveExpression returns [NumberExpression expr = null]
@init{List<NumberExpression> exprs = new ArrayList<NumberExpression>();
	List<Token> ops = new ArrayList<Token>();}
	:   
	e = multiplicativeExpression{exprs.add(e);} ( op = (PLUS | MINUS){ops.add(op);} e = multiplicativeExpression{exprs.add(e);} )*
	{expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);}
	;

multiplicativeExpression returns [NumberExpression expr = null]
@init{List<NumberExpression> exprs = new ArrayList<NumberExpression>();
	List<Token> ops = new ArrayList<Token>();}
	:	
	(e = simpleNumberExpression{exprs.add(e);} ( op = ( STAR | SLASH | PERCENT ){ops.add(op);} e = simpleNumberExpression{exprs.add(e);} )*
	{expr = ExpressionFactory.createComposedNumberExpression(exprs,ops);}
	|   e1 = numberFunction {expr = e1;})
	;

numberExpression returns [NumberExpression expr = null]
	:
	e = additiveExpression {expr = e;}
	;

numberExpressionInPar returns [NumberExpression expr = null]
	:
	LPAREN  e = additiveExpression RPAREN {expr = e;}
	;

simpleNumberExpression returns [NumberExpression expr = null]
	:	
	m = MINUS? lit = DecimalLiteral {expr = ExpressionFactory.createIntegerExpression(lit,m);} 
	// TODO what about float numbers?
	| m = MINUS? lit = FloatingPointLiteral {expr = ExpressionFactory.createDoubleExpression(lit,m);}
	| m = MINUS? var = numberVariable {expr = ExpressionFactory.createReferenceNumberExpression(var,m);}
	| e = numberExpressionInPar {expr = e;}
	;
	
stringExpression returns [StringExpression expr = null]
options {
	backtrack = true;
}
@init {
List<StringExpression> exprs = new ArrayList<StringExpression>();
}
	:
	e = simpleStringExpression {exprs.add(e);} 
	(PLUS (e1 = simpleStringExpression {exprs.add(e1);} 
		| e2 = numberExpressionInPar {exprs.add(e2);}
		| be = simpleBooleanExpression {exprs.add(be);}
		| te = typeExpression {exprs.add(te);}
		| le = listExpression {exprs.add(le);}
		))*
	{expr = ExpressionFactory.createComposedStringExpression(exprs);}
	|(e = stringFunction)=> e = stringFunction{expr = e;} 
	;

// not checked
stringFunction returns [StringExpression expr = null]
@init {List<StringExpression> list = new ArrayList<StringExpression>();}
:
	name = REMOVESTRING LPAREN var = variable (COMMA t = stringExpression {list.add(t);})+ RPAREN
	{expr = StringFunctionFactory.createRemoveFunction(var,list);}
	|
	(e = externalStringFunction)=> e = externalStringFunction {expr = e;}
	;

// not checked
externalStringFunction returns [StringExpression expr = null]
	:
	//{isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "STRINGFUNCTION")}? 
	id = Identifier LPAREN
	args = varArgumentList	RPAREN
	{
		expr = external.createExternalStringFunction(id, args);
	}
	;

simpleStringExpression returns [StringExpression expr = null]
	: 
	lit = StringLiteral {expr = ExpressionFactory.createSimpleStringExpression(lit);} 
	| {isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "STRING")}? id = Identifier {expr = ExpressionFactory.createReferenceStringExpression(id);} 
	;


booleanExpression returns [BooleanExpression expr = null]
	:
	(e = composedBooleanExpression)=> e = composedBooleanExpression {expr = e;}
	|sbE =  simpleBooleanExpression {expr = sbE;}
	;

simpleBooleanExpression returns [BooleanExpression expr = null]
	:
	 e = literalBooleanExpression {expr = e;}
	| {isVariableOfType($blockDeclaration::env,input.LT(1).getText(), "BOOLEAN")}? 
	id = Identifier {expr = ExpressionFactory.createReferenceBooleanExpression(id);} 
	;

// not checked
composedBooleanExpression returns [BooleanExpression expr = null]

	:
	(e2 = booleanCompare)=> e2 = booleanCompare {expr = e2;}
	| (bte = booleanTypeExpression)=> bte = booleanTypeExpression{expr = bte;}
	| (bne = booleanNumberExpression)=> bne = booleanNumberExpression{expr = bne;}
	| e1 = booleanFunction {expr = e1;}
	;

// not checked
booleanFunction returns [BooleanExpression expr = null]

	:
	(op = XOR LPAREN e1 = booleanExpression COMMA e2 = booleanExpression RPAREN)
	{expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}
	| (e = externalBooleanFunction)=> e = externalBooleanFunction {expr = e;}
	;

// not checked
externalBooleanFunction returns [BooleanExpression expr = null]
	:
	//{isVariableOfType($blockDeclaration::env, input.LT(1).getText(), "BOOLEANFUNCTION")}? 
	id = Identifier LPAREN
	args = varArgumentList RPAREN
	{
		expr = external.createExternalBooleanFunction(id, args);
	}
	;

// not checked
booleanCompare returns [BooleanExpression expr = null]
	:
	(e1 = simpleBooleanExpression op = (EQUAL | NOTEQUAL) e2 = booleanExpression)
	{expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}
	;


literalBooleanExpression returns  [BooleanExpression expr = null]
	:
	v = TRUE {expr = ExpressionFactory.createSimpleBooleanExpression(v);} 
	| v = FALSE {expr = ExpressionFactory.createSimpleBooleanExpression(v);}
	;



booleanTypeExpression  returns  [BooleanExpression expr = null]
	:
	e1 = typeExpression
	op = (EQUAL | NOTEQUAL)
	e2 = typeExpression
	{expr = ExpressionFactory.createBooleanTypeExpression(e1,op,e2);}
	;
	
booleanNumberExpression  returns  [BooleanExpression expr = null]
	:
	LPAREN
	e1 = numberExpression//{exprs.add(e);} 
	op = (LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL)//{ops.add(op);} 
	e2 = numberExpression//{exprs.add(e);}
	RPAREN
	{expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);}
	;
