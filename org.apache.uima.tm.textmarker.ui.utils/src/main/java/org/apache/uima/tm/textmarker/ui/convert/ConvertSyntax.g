grammar ConvertSyntax;

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

	package org.apache.uima.tm.textmarker.ui.convert;
}

@parser::header {
package org.apache.uima.tm.textmarker.ui.convert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.DFA;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;

import org.apache.uima.tm.dltk.parser.ast.TextMarkerBlock;
import org.apache.uima.tm.dltk.internal.core.builder.DescriptorManager;
}

@lexer::members {
	public int implicitLineJoiningLevel = 0;
	public int startPos=-1;
	
	public void emitErrorMessage(String msg) {
	}
}
@parser::members {

	public ModuleDeclaration md;
	private List<String> vars = new ArrayList<String>();	
	private Map<String, String> varTypeMap = new HashMap<String, String>();
	private List lists = new ArrayList();
	private List tables = new ArrayList();
	public int length;
	public DescriptorManager descriptor;
	private int level = 0;
	
	private String module;
	private String packageString;
	
	public StringBuilder builder = new StringBuilder();
	
	
	public List<String> getVariables() {
		return vars;
	}
	
	public Map<String, String> getVariableTypes() {
		return varTypeMap;
	}
	
	
	public void addType(TextMarkerBlock parent, String type, String parentType) {
		vars.add(type);
		if(parentType == null || parentType.trim().equals("") || parentType.equals("Annotation") ) {
			parentType = "uima.tcas.Annotation";
    		//} else if(parentType.split(".").length > 1) {
    		
    		} else {	
    			parentType = parent.getNamespace() + "."+parentType.trim();
		}
		descriptor.addType(parent.getNamespace()+"."+type.trim(), "Type defined in "+packageString+"."+module, parentType);
	}
	
	public void addPredefinedType(String type) {
		vars.add(type);
		varTypeMap.put(type, "TYPE");
		
	}
	
	public void addType(TextMarkerBlock parent, String name, String parentType, List featuresTypes, List featuresNames) {
		vars.add(name);
		name = parent.getNamespace() + "."+name.trim();
		if(parentType.equals("Annotation")) {
			parentType = "uima.tcas.Annotation";
    		//} else if(parentType.split(".").length > 1) {
    			
    		} else {		
    			parentType = parent.getNamespace() + "."+parentType.trim();
		}
		descriptor.addType(name, "Type defined in "+packageString+"."+module, parentType);
    		
    		for (int i = 0; i < featuresTypes.size(); i++) {
				String ftype = (String) featuresTypes.get(i);
				if(ftype.equals("Annotation")) {
					ftype = "uima.tcas.Annotation";
				} else	if(ftype.equals("STRING")) {
					ftype = "uima.cas.String";
				} else if(ftype.equals("INT")) { 
					ftype = "uima.cas.Integer";
				} else if(ftype.equals("DOUBLE")) { 
					ftype = "uima.cas.Double";
				} else if(ftype.equals("BOOLEAN")) { 
					ftype = "uima.cas.Boolean";
				} else if(ftype.equals("TYPE")) { 
					ftype = "uima.cas.String";
				} else {
					ftype = parent.getNamespace() + "."+ftype;
				}
				String fname = (String) featuresNames.get(i);
				descriptor.addFeature(name, fname, fname, ftype);			
			}
    	}
	
	public void addWordList(TextMarkerBlock parent, String list) {
		lists.add(list);
	}
	
	public void addCSVTable(TextMarkerBlock parent, String table) {
		tables.add(table);
	}
	
	public boolean isType(TextMarkerBlock parent, String type) {
		return vars.contains(type);
	}
	
	public boolean isWordList(TextMarkerBlock parent, String list) {
		return lists.contains(list);
	}
	public boolean isCSVTable(TextMarkerBlock parent, String table) {
		return tables.contains(table);
	}
	
	public void addVariable(String var, String type, IntStream input) throws NoViableAltException {
		if(!vars.contains(var)) {
			vars.add(var);
			varTypeMap.put(var, type);
		} 
	}
	
	public void addVariable(String var, String type) {
		if(!vars.contains(var)) {
			vars.add(var);
			varTypeMap.put(var, type);
		}
	}
	
	public boolean isVariable(String var) {
		return vars.contains(var);
	}
	
	public boolean isVariableOfType(String var, String type) {
		return vars.contains(var) && type.equals(varTypeMap.get(var));
	}
	
	public void checkVariable(String var, IntStream input) throws NoViableAltException {
		if(!vars.contains(var)) {
			throw new NoViableAltException("not declared \"" + var + "\"", 3, 0, input);
		}
	}
	
	public void addImportTypeSystem(String descriptorLocation) {
		descriptor.addTypeSystem(descriptorLocation);
	}
	
	public void addImportScript(Token module) {
		descriptor.addScript(module.getText());
	}
		
	public void addImportEngine(Token module) {
		descriptor.addEngine(module.getText());
	}
	
	protected static final int[] getBounds(Token t) {
		if (t instanceof CommonToken) {
			CommonToken ct = (CommonToken) t;
			int[] bounds = {ct.getStartIndex(), ct.getStopIndex()}; 
			return bounds;
		}
		return null;
	}
	
//	public String getTypeOf(String varName) {
//		String vn = varTypeMap.get(varName);
//		return vn != null? vn : "";
//	}
	
}

@rulecatch {
	catch (RecognitionException exception1) {
		
		recover(input,exception1);
	}
	catch (Throwable exception2) {
		
	}
}


file_input// [String module]
/*@init {
TextMarkerScriptBlock rootBlock = null;
List<Statement> stmts = new ArrayList<Statement>();
}
*/
	:	
	p = packageDeclaration {if(p != null) builder.append(p).append("\n");}
	/*{String packageName = "org.apache.uima.tm"; if(p != null) {packageName = p.getName();}
rootBlock = ScriptFactory.createScriptBlock(0,0,0,0,module, null, null, packageName);
stmts.add(p);
this.module = module;
this.packageString = p.getName();}*/

//{$blockDeclaration.push(new blockDeclaration_scope());$blockDeclaration::env = rootBlock;}

	gs = globalStatements {if(gs != null) builder.append(gs).append("\n");}
	s = statements {builder.append(s);}
	/*{
	  stmts.addAll(gs);
	  stmts.addAll(s);
  	  for (Statement stmt : stmts){
		  if (stmt != null) {
		    md.addStatement(stmt);
		  }
	  };
	}*/
	
	
	EOF
	;
	

packageDeclaration returns [StringBuilder pack = new StringBuilder()]
	:	pString = PackageString p = dottedId SEMI
	{
	//pack = StatementFactory.createPkgDeclaration(p, pString);
	pack.append(pString.getText()).append(" ").append(p).append(";");
	}
	;

statements returns [StringBuilder stmts = new StringBuilder()]
	:
	(morestmts = statement {stmts.append(morestmts);})*
	;
	
globalStatements returns [StringBuilder stmts = new StringBuilder()]
	:
	(morestmts = globalStatement {stmts.append(morestmts).append("\n");})*
	//(morestmts = globalStatement {if(morestmts != null) {stmts.addAll(morestmts);}})*
	;

globalStatement returns [StringBuilder stmts = new StringBuilder()]
	:
	stmtImport = importStatement {stmts.append(stmtImport);}
	;
	
statement returns [StringBuilder stmts = new StringBuilder()]
	:	
	( stmts1 = declaration {stmts.append(stmts1).append("\n");}
	| stmtVariable = variableDeclaration {stmts.append(stmtVariable).append("\n");}
	| stmt3 = blockDeclaration {stmts.append(stmt3).append("\n");}
	| stmt2 = simpleStatement {stmts.append(stmt2).append("\n");}
	)
	;
	
importStatement returns [StringBuilder stmt = new StringBuilder()]
	:
	system = TypeSystemString ts = dottedId SEMI //{stmt = StatementFactory.createImportTypeSystem(ts,system);addImportTypeSystem(ts.getText());}
	{stmt.append(system.getText()).append(" ").append(ts).append(";");}
	| im = ScriptString name = dottedId SEMI //{stmt = StatementFactory.createImportScript(name, im); addImportScript(name);}
	{stmt.append(im.getText()).append(" ").append(name).append(";");}
	| im = EngineString name = dottedId SEMI //{stmt = StatementFactory.createImportEngine(name, im); addImportEngine(name);}
	{stmt.append(im.getText()).append(" ").append(name).append(";");}
	;
	
variableDeclaration returns [StringBuilder stmt = new StringBuilder()]
//@init {Statement stmt = null;}
	:

	type = IntString id = Identifier {stmt.append(type.getText()).append(" ").append(id.getText());}
		(COMMA id= Identifier {stmt.append(",").append(id.getText());}
		)* (ASSIGN_EQUAL value1 = numberExpression  {stmt.append(" = ").append(value1);})?  SEMI
		{stmt.append(";");}
	|
	type = DoubleString id = Identifier {stmt.append(type.getText()).append(" ").append(id.getText());}
			(COMMA id = Identifier {stmt.append(",").append(id.getText());}
		 )* (ASSIGN_EQUAL value2 = numberExpression {stmt.append(" = ").append(value2);})?  SEMI
		 {stmt.append(";");}
	|
	type = StringString id = Identifier {stmt.append(type.getText()).append(" ").append(id.getText());}
			(COMMA id = Identifier {stmt.append(",").append(id.getText());}
		 )* (ASSIGN_EQUAL value3 = stringExpression {stmt.append(" = ").append(value3);})?  SEMI
		 {stmt.append(";");}
	|
	type = BooleanString id = Identifier {stmt.append(type.getText()).append(" ").append(id.getText());}
			(COMMA id = Identifier {stmt.append(",").append(id.getText());}
		 )* (ASSIGN_EQUAL value4 = booleanExpression {stmt.append(" = ").append(value4);})?  SEMI
		 {stmt.append(";");}
	|
	type = TypeString id = Identifier {stmt.append(type.getText()).append(" ").append(id.getText());}
			(COMMA id = Identifier {stmt.append(",").append(id.getText());}
		 )* (ASSIGN_EQUAL value5 = annotationType {stmt.append(" = ").append(value5);})?  SEMI
		 {stmt.append(";");}
	|
	stmt1 = conditionDeclaration {stmt.append(stmt1);}
	|
	stmt2 = actionDeclaration {stmt.append(stmt2);}
	;

//TODO added rule
conditionDeclaration returns [StringBuilder stmt = new StringBuilder()]
	:
	declareToken = ConditionString id = Identifier
	ASSIGN_EQUAL 
	conditions = conditionPart SEMI
	
	{stmt.append(declareToken.getText()).append(" ").append(id.getText()).append(" = ").append(conditions).append(";");}
	//{stmt = StatementFactory.createComposedVariableConditionDeclaration(id, conditions);}
	;

//TODO added rule
actionDeclaration returns [StringBuilder stmt = new StringBuilder()]
	:
	declareToken = ACTION id = Identifier
	ASSIGN_EQUAL
	actions = actionPart SEMI
	
	{stmt.append(declareToken.getText()).append(" ").append(id.getText()).append(" = ").append(actions).append(";");}
	//{stmt = StatementFactory.createComposedVariableActionDeclaration(id, actions);}
	;

declaration returns [StringBuilder stmts = new StringBuilder()]
/*@init {
Statement stmt = null;
List featureTypes = new ArrayList();
	List featureNames = new ArrayList();
}*/
	:
	//TODO added lazy parent
	(declareToken=DECLARE lazyParent = annotationType?
		 id = Identifier 
		 {stmts.append(declareToken.getText()).append(" ");
		 if(lazyParent!= null)
		 	stmts.append(lazyParent).append(" ");
		 stmts.append(id.getText());
		 }
		(COMMA 
		 id = Identifier 
		 {stmts.append(",").append(id.getText());})* SEMI
		 {stmts.append(";");}
		 
	| listToken = LIST 
	// TODO rewrite! when ast buildt
	list = dottedIdentifier 
	 SEMI
	 {stmts.append(listToken.getText()).append(" ").append(list).append(";");}
	| tableToken = TABLE 
	// TODO rewrite! when ast buildt
	table = dottedIdentifier 
	 SEMI
	 {stmts.append(tableToken.getText()).append(" ").append(table).append(";");}
	| 
	// TODO rewrite! when ast buildt
	declareToken=DECLARE 
		type=annotationType
	id = Identifier 
		(LPAREN 
			{stmts.append(declareToken.getText()).append(" ").append(type).append(" ").append(id.getText()).append("(");}
			(
			obj1 = annotationType{stmts.append(obj1);} 
			| obj2 = StringString{stmts.append(obj2.getText());} 
			| obj3 = DoubleString{stmts.append(obj3.getText());} 
			| obj4 = IntString{stmts.append(obj4.getText());} 
			| obj5 = BooleanString{stmts.append(obj5.getText());} 
			) 
			fname = Identifier{stmts.append(" ").append(fname.getText());} 
			(
			COMMA {stmts.append(",");}
			(
			obj1 = annotationType{stmts.append(obj1);} 
			| obj2 = StringString{stmts.append(obj2.getText());} 
			| obj3 = DoubleString{stmts.append(obj3.getText());} 
			| obj4 = IntString{stmts.append(obj4.getText());} 
			| obj5 = BooleanString{stmts.append(obj5.getText());} 
			) 
			fname = Identifier{stmts.append(" ").append(fname.getText());})* 
		RPAREN) SEMI // TODO removed question mark
		{
		stmts.append(")").append(";");
		}
	)
	;



	

blockDeclaration returns [StringBuilder block = new StringBuilder()]
/*scope {
	TextMarkerBlock env;
	}	
@init{
TextMarkerRuleElement re = null;
level++;
}
@after {
level--;
}*/
	:
	declareToken = BlockString 
	LPAREN
	id = Identifier //{addVariable(id.getText(), declareToken.getText());
	/*{
		//block = ScriptFactory.createScriptBlock(id, declareToken, $blockDeclaration[level - 1]::env);
		//$blockDeclaration::env = block;
	}*/
	RPAREN
	(typeExpression)=>idRef=typeExpression quantifier = quantifierPart? ((cp = conditionPart)=>cp = conditionPart)? ap = actionPart?
	/*{// TODO handle quantifierPart.
		//re = ScriptFactory.createRuleElement(idRef,null,cp,ap);
		}*/

	LCURLY body = statements rc = RCURLY
	
	{block.append(declareToken.getText()).append("(").append(id.getText()).append(") ");
	if (quantifier == null) {
		if (cp == null && ap == null)
			block.append(idRef);
		else if (cp == null)	
			block.append(idRef).append("{").append(ap).append("} ");
		else if (ap == null)	
			block.append(idRef).append("{").append(cp).append("} ");
		else	
			block.append(idRef).append("{").append(cp).append(ap).append("} ");
	}else {
		if (cp == null && ap == null)
			block.append(idRef).append(quantifier);
		else if (cp == null)	
			block.append(idRef).append(quantifier).append("{").append(ap).append("} ");
		else if (ap == null)	
			block.append(idRef).append(quantifier).append("{").append(cp).append("} ");
		else	
			block.append(idRef).append(quantifier).append("{").append(cp).append(ap).append("} ");
	}
	block.append("{\n").append(body).append("\n}");
	}
	
	//{ScriptFactory.finalizeScriptBlock(block, rc, re, body);}
	;
		
simpleStatement returns [StringBuilder stmt = new StringBuilder()]
	: 
	elements=ruleElements SEMI 
		{stmt.append(elements).append(";");}
	;
	
ruleElements returns [StringBuilder ruleElements = new StringBuilder()]
	:
	re = ruleElement {ruleElements.append(re);} (re = ruleElement {ruleElements.append(" ").append(re);})*
	;	
	
blockRuleElement returns [StringBuilder rElement = new StringBuilder()] //[List<TextMarkerRuleElement> elements = new ArrayList<TextMarkerRuleElement>()]
	:
	re = ruleElementType {rElement.append(re);}
	;	
	
ruleElement returns [StringBuilder ruleElement = new StringBuilder()]
	:
	(
	re1 = ruleElementType {ruleElement.append(re1);}
	)	
	|
	(
	re2 = ruleElementLiteral {ruleElement.append(re2);}
	)	
	;
	
ruleElementType returns [StringBuilder re = new StringBuilder()]
	:
	(typeExpression)=>idRef=typeExpression quantifier = quantifierPart? cp = conditionPart? ap = actionPart?
	{
		if (quantifier == null) {
			if (cp == null && ap == null)
				re.append(idRef);
			else if (cp == null)	
				re.append(idRef).append("{").append(ap).append("}");
			else if (ap == null)	
				re.append(idRef).append("{").append(cp).append("}");
			else	
				re.append(idRef).append("{").append(cp).append(ap).append("}");
		}else {
			if (cp == null && ap == null)
				re.append(idRef).append(quantifier);
			else if (cp == null)	
				re.append(idRef).append(quantifier).append("{").append(ap).append("}");
			else if (ap == null)	
				re.append(idRef).append(quantifier).append("{").append(cp).append("}");
			else	
				re.append(idRef).append(quantifier).append("{").append(cp).append(ap).append("}");
		}
	}
	
	;

ruleElementLiteral returns [StringBuilder re = new StringBuilder()]
	:
	 lit = StringLiteral quantifier = quantifierPart? cp = conditionPart? ap = actionPart?
	 {
	 	// TODO handle quantifierPart
	 	//re = ScriptFactory.createRuleElement(lit,null,cp,ap);
	 	if (quantifier == null) {
			if (cp == null && ap == null)
				re.append(lit.getText());
			else if (cp == null)	
				re.append(lit.getText()).append("{").append(ap).append("}");
			else if (ap == null)	
				re.append(lit.getText()).append("{").append(cp).append("}");
			else	
				re.append(lit.getText()).append("{").append(cp).append(ap).append("}");
		}else {
			if (cp == null && ap == null)
				re.append(lit.getText()).append(quantifier);
			else if (cp == null)	
				re.append(lit.getText()).append(quantifier).append("{").append(ap).append("}");
			else if (ap == null)	
				re.append(lit.getText()).append(quantifier).append("{").append(cp).append("}");
			else	
				re.append(lit.getText()).append(quantifier).append("{").append(cp).append(ap).append("}");
		}
	 }
	;
	
	
typeExpression returns [StringBuilder expr = new StringBuilder()]
	:
	/*tf = typeFunction {expr.append(tf);}
	|*/ st = simpleTypeExpression {expr.append(st);}
	;

// not checked
/*typeFunction returns [StringBuilder func = new StringBuilder()]
	:
	(e = externalTypeFunction)=> e = externalTypeFunction {func.append(e);}
	;*/

// not checked
/*externalTypeFunction returns [StringBuilder exFunc = new StringBuilder()]
	:
	//{isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION")}?
	id = Identifier LPAREN
	args = varArgumentList	RPAREN
	{
		exFunc.append(id.getText()).append("(").append(args).append(")");
	}
	;*/

simpleTypeExpression returns [StringBuilder type = new StringBuilder()]
	:
	at = annotationType {type.append(at);}
	;
	
	
quantifierPart returns [StringBuilder quantifier = new StringBuilder()]
	:
	 STAR q = QUESTION? {quantifier.append("*"); if(q != null) quantifier.append(q.getText());}
	| PLUS q = QUESTION? {quantifier.append("+"); if(q != null) quantifier.append(q.getText());}
	| QUESTION q = QUESTION? {quantifier.append("?"); if(q != null) quantifier.append(q.getText());}
	| (LBRACK min = numberExpression COMMA max = numberExpression RBRACK q = QUESTION?
		{quantifier.append("[").append(min).append(",").append(max).append("]");
		 if(q != null) quantifier.append(q.getText());}
	   )
	;
	
	
conditionPart returns [StringBuilder conditions = new StringBuilder()]
	:
	LCURLY c = condition {conditions.append(c);}
	 (SEMI c = condition {conditions.append(",").append(c);} )* RCURLY  	
	;

condition returns [StringBuilder condition = null]
	:
	(
	c = conditionAnd
	/*| c = conditionContains
	| c = conditionContextCount
	| c = conditionCount
	| c = conditionCurrentCount */
	| c = conditionInList
	/*
	| c = conditionIsInTag
	| c = conditionLast */
	| c = conditionMofN
	//| c = conditionNear
	| c = conditionNot
	| c = conditionOr
	/*| c = conditionPartOf
	| c = conditionPosition
	| c = conditionRegExp
	| c = conditionScore
	| c = conditionTotalCount
	| c = conditionVote */
	| c = conditionIf /*
	| c = conditionFeature
	| c = conditionParse
	| c = conditionIs
	| c = conditionBefore
	| c = conditionAfter */
	| (c = externalCondition)=> c = externalCondition
	| c = variableCondition
	) {condition = c;}
	;
	
//TODO added rule
variableCondition returns [StringBuilder condition = new StringBuilder()]
	:		
	//{isVariableOfType(input.LT(1).getText(), "CONDITION")}?
	 id = Identifier
	
	{condition.append(id.getText());
	}
	/*{
		condition = ConditionFactory.createCondition(id);
	}*/
	;	
	
externalCondition returns [StringBuilder condition = new StringBuilder()]
	:
	//{isVariableOfType(input.LT(1).getText(), "CONDITION")}?
	 id = Identifier COMMA
	args = varArgumentList	
	{condition.append(id.getText()).append("(").append(args).append(")");
	}
	/*{
		condition = external.createExternalCondition(id, args);
	}*/
	;
conditionAnd returns [StringBuilder cond = new StringBuilder()]
	:	
	name = AND conds = conditionPart 
	{cond.append(name.getText()).append("(").append(conds).append(")");}
	;
/*
conditionContains returns [TextMarkerCondition cond = null]
	:	
	name = CONTAINS COMMA typeExpr = typeExpression (COMMA min = numberExpression COMMA max = numberExpression (COMMA percent = booleanExpression)?)?
	{cond = ConditionFactory.createCondition(name, typeExpr, min, max, percent);}
	;
conditionContextCount returns [TextMarkerCondition cond = null]
	:	
	name = CONTEXTCOUNT COMMA typeExpr = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)?
	(COMMA var = numberVariable)?
	{cond = ConditionFactory.createCondition(name, typeExpr, min, max);}
	;
conditionCount returns [TextMarkerCondition cond = null]
	:	
	name = COUNT COMMA type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
	(COMMA var = numberVariable)?
	{cond = ConditionFactory.createCondition(name, type, min, max);}
	;
conditionCurrentCount returns [TextMarkerCondition cond = null]
	:	
	name = CURRENTCOUNT COMMA type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
	(COMMA var = numberVariable)?
	{cond = ConditionFactory.createCondition(name,type, min, max);}
	;
conditionTotalCount returns [TextMarkerCondition cond = null]
	:	
	name = TOTALCOUNT COMMA type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
	(COMMA var = numberVariable)?
	{cond = ConditionFactory.createCondition(name, type, min, max);}
	; */
conditionInList returns [StringBuilder cond = new StringBuilder()]
	:
	// TODO add wordlList//innerList support
	name = INLIST COMMA wordList = dottedIdentifier {cond.append(name.getText()).append("(").append(wordList);}
	 (COMMA dist = numberExpression {cond.append(",").append(dist);} (COMMA rel = booleanExpression {cond.append(",").append(rel);})?)?
	 {cond.append(")");}
	| name = INLIST COMMA list = innerList {cond.append(name.getText()).append("(").append(list);}
	 (COMMA dist = numberExpression {cond.append(",").append(dist);} (COMMA rel = booleanExpression {cond.append(",").append(rel);})?)?
	{cond.append(")");}
	;
/*
conditionIsInTag returns [TextMarkerCondition cond = null]
@init {
List<Expression> list1 = new ArrayList<Expression>();
List<Expression> list2 = new ArrayList<Expression>();
}
	:
	name = ISINTAG COMMA id = stringExpression (COMMA id1 = stringExpression ASSIGN_EQUAL id2 = stringExpression {list1.add(id1);list2.add(id2);})*
	{List exprs = new ArrayList();
	exprs.add(id);
  exprs.addAll(list1);
	exprs.addAll(list2);
	cond = ConditionFactory.createCondition(name, exprs);}	
	;
		
conditionLast returns [TextMarkerCondition cond = null]
	:	
	name = LAST COMMA type = typeExpression
	{cond = ConditionFactory.createCondition(name, type);}
	;
	
*/
conditionMofN returns [StringBuilder cond = new StringBuilder()]
	:	
	name = MOFN COMMA min = numberExpression COMMA max = numberExpression conds = conditionPart
	{cond.append(name.getText()).append("(").append(min).append(",").append(max).append("(").append(conds).append(")");}	
	;
/*
conditionNear returns [TextMarkerCondition cond = null]
	:	
	name = NEAR COMMA type = typeExpression COMMA min = numberExpression COMMA max = numberExpression (COMMA direction = booleanExpression (COMMA filtered = booleanExpression)?)?
	{cond = ConditionFactory.createCondition(name, type, min, max, direction, filtered);}	
	;
*/
conditionNot returns [StringBuilder cond = new StringBuilder()]
	:	
	name = MINUS c = condition
	{cond.append(name.getText()).append(c);}	
	;
conditionOr returns [StringBuilder cond = new StringBuilder()]
	:	
	name = OR conds = conditionPart
	{cond.append(name.getText()).append("(").append(conds).append(")");}
	;
/*
conditionPartOf returns [TextMarkerCondition cond = null]
	:
	name = PARTOF COMMA type = typeExpression	
	{cond = ConditionFactory.createCondition(name, type);}
	;
conditionPosition returns [TextMarkerCondition cond = null]
	:	
	name = POSITION COMMA type = typeExpression COMMA pos = numberExpression
	{cond = ConditionFactory.createCondition(name, type, pos);}
	;
conditionRegExp returns [TextMarkerCondition cond = null]
	:
	name = REGEXP COMMA pattern = stringExpression (COMMA caseSensitive = booleanExpression)?
	{cond = ConditionFactory.createCondition(name, pattern, caseSensitive);}	
	;

//regExpString returns [String pattern = ""]
//	:
//	(re = ~(LCURLY|RCURLY|SEMI) {pattern += re.getText();} | re = LCURLY {pattern += re.getText();} (re = ~(LCURLY|RCURLY|SEMI){pattern += re.getText();})* re = RCURLY {pattern += re.getText();})+
//	;
	
conditionScore returns [TextMarkerCondition cond = null]
	:
	name = SCORE COMMA type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)?	
	(COMMA var = numberVariable)?	
	{cond = ConditionFactory.createCondition(name, type, min, max, var);}
	;

conditionVote returns [TextMarkerCondition cond = null]
	:	
	name = VOTE COMMA type1 = typeExpression COMMA type2 = typeExpression
	{cond = ConditionFactory.createCondition(name, type1, type2);}
	;
*/	
conditionIf returns [StringBuilder cond = new StringBuilder()]
	:	
	name = IF COMMA e = booleanExpression
	{cond.append(name.getText()).append("(").append(e).append(")");}
	;
/*
conditionFeature returns [TextMarkerCondition cond = null]
	:	
	name = FEATURE COMMA se = stringExpression COMMA  v = argument
		{cond = ConditionFactory.createCondition(name, se, v);}
	;	
conditionParse returns [TextMarkerCondition cond = null]
	:
	name = PARSE COMMA
	 var=genericVariableReference
	{cond = ConditionFactory.createCondition(name, var);}
	;


conditionIs returns [TextMarkerCondition cond = null]
	:
	name = IS COMMA type = typeExpression
	{cond = ConditionFactory.createCondition(name, type);}
	;

conditionBefore returns [TextMarkerCondition cond = null]
	:
	name = BEFORE COMMA type = typeExpression
	{cond = ConditionFactory.createCondition(name, type);}
	;

conditionAfter returns [TextMarkerCondition cond = null]
	:
	name = AFTER COMMA type = typeExpression
	{cond = ConditionFactory.createCondition(name, type);}
	;
	*/
	
	
	
actionPart returns [StringBuilder actions = null]
	:
	(LPAREN action)=>LPAREN a = action {actions = new StringBuilder("->"); actions.append(a);}
	 (SEMI a = action {actions.append(",").append(a);} )* RPAREN
	;

action returns [StringBuilder result = new StringBuilder()]
	:
	(
/*	a = actionColor
	| a = actionDel
	| a = actionLog
	| a = actionMark
	| a = actionMarkFast
	| a = actionMarkLast
	| a = actionReplace
	| a = actionRetainMarkup
	| a = actionRetainType
	| a = actionFilterMarkup
	| a = actionFilterType
	|*/ a = actionCreateStructure /*
	| a = actionFillStructure */
	| a = actionCall /*
	| a = actionAssign
	| a = actionMarkSkill
	| a = actionFeature
	| a = actionUnmark
	| a = actionTransfer
	| a = actionMarkOnce
	| a = actionTrie */
	| (a = externalAction)=> a = externalAction
	| a = variableAction
	) {result = a;}
	;


variableAction returns [StringBuilder action = new StringBuilder()]
	:
	//{isVariableOfType(input.LT(1).getText(), "ACTION")}?
	 id = Identifier
	 {action.append(id.getText());}
	/*{
		action = ActionFactory.createAction(id);
	}*/
	;
externalAction returns [StringBuilder action = new StringBuilder()]
	:
	//{isVariableOfType(input.LT(1).getText(), "ACTION")}?
	 id = Identifier COMMA args = varArgumentList
	 {action.append(id.getText()).append("(").append(args).append(")");}
	/*{
		action = external.createExternalAction(id, args);
	}*/
	;


actionCreateStructure returns [StringBuilder exprs = new StringBuilder()]
	:
	name = (CREATE | FILL) COMMA structure = typeExpression {exprs.append(name.getText()).append("(").append(structure);}
	(
	COMMA fname = stringExpression ASSIGN_EQUAL obj1 = argument {exprs.append(",").append(fname).append(" = ").append(obj1);}
	)+
	{exprs.append(")");}
	;

/*
actionFillStructure returns [TextMarkerAction action = null]
@init {
	List exprs = new ArrayList();
}
	:
	name = FILL COMMA type = typeExpression {exprs.add(type);}
	(
	COMMA fname = stringExpression ASSIGN_EQUAL {exprs.add(fname);}
	obj1 = argument {exprs.add(obj1);}
	)+
	{action = ActionFactory.createAction(name, exprs);}
	;
	

actionColor returns [TextMarkerAction action = null]
	:	
	name = COLOR COMMA type = typeExpression COMMA color = stringExpression
	{action = ActionFactory.createAction(name, type, color);}
	;

actionDel returns [TextMarkerAction action = null]
	:	
	name = DEL
	{action = ActionFactory.createAction(name, new ArrayList());}
	;
		
actionLog returns [TextMarkerAction action = null]
	:	
	name = LOG COMMA lit = stringExpression (COMMA log = LogLevel)? 
	{action = ActionFactory.createAction(name, lit);} //TODO handle logLevel
	;

actionMark returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
	:	
	name = MARK COMMA ((score = numberExpression) => score = numberExpression COMMA)? (type = typeExpression) => type = typeExpression
	{list.add(score); list.add(type);}
	(
	COMMA (index = numberExpression) => index = numberExpression {list.add(index);}
	)* 
	{action = ActionFactory.createAction(name, list);}
	;

actionMarkOnce returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
	:	
	name = MARKONCE COMMA ((score = numberExpression) => score = numberExpression COMMA)? (type = typeExpression) => type = typeExpression
	{list.add(score); list.add(type);}
	(
	COMMA (index = numberExpression) => index = numberExpression {list.add(index);}
	)* 
	{action = ActionFactory.createAction(name, list);}
	;

actionMarkFast returns [TextMarkerAction action = null]
	:	
	name = MARKFAST COMMA type = typeExpression COMMA list = dottedIdentifier (COMMA ignore = booleanExpression (COMMA numExpr = numberExpression)?)?
	{action = ActionFactory.createAction(name, type, ignore, numExpr);} // TODO handle list
	;

actionMarkLast returns [TextMarkerAction action = null]
	:	
	name = MARKLAST COMMA type = typeExpression
	{action = ActionFactory.createAction(name, type);}
	;


actionReplace returns [TextMarkerAction action = null]
	:	
	name = REPLACE COMMA lit = stringExpression
	{action = ActionFactory.createAction(name, lit);}
	;

	

actionRetainMarkup returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
	:	
	name = RETAINMARKUP (COMMA id = stringExpression {list.add(id);})*
	{action = ActionFactory.createAction(name, list);}
	;
	

actionRetainType returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
	:	
	name = RETAINTYPE (COMMA id = typeExpression {list.add(id);})*
	{action = ActionFactory.createAction(name, list);}
	;	
	
	
actionFilterMarkup returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
	:	
	name = FILTERMARKUP (COMMA id = stringExpression {list.add(id);})*
	{action = ActionFactory.createAction(name, list);}
	;
	

actionFilterType returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
	:	
	name = FILTERTYPE (COMMA id = typeExpression {list.add(id);})*
	{action = ActionFactory.createAction(name, list);}
	;	
*/
actionCall returns [StringBuilder action = new StringBuilder()]
	:
	name = CALL COMMA ns = dottedId
	{action.append(name.getText()).append("(").append(ns).append(")");} // TODO handle dottedId
	;
/*	
actionAssign returns [TextMarkerAction action = null]
@init{
	VariableReference ref = null;
}
	:
	name = ASSIGN COMMA
	(id = Identifier COMMA e = argument
	) {
	ref = ExpressionFactory.createGenericVariableReference(id);
	action = ActionFactory.createAction(name, ref, e);}
	;	
	
// to be removed (fall back backup)
actionAssignOld returns [TextMarkerAction action = null]
@init{
	VariableReference ref = null;
}
	:
	name = ASSIGN COMMA
	(
	{isVariableOfType(input.LT(1).getText(), "BOOLEAN")}? id = Identifier
			{ref = ExpressionFactory.createBooleanVariableReference(id);} COMMA e = booleanExpression |
	{isVariableOfType(input.LT(1).getText(), "STRING")}? id = Identifier
			{ref = ExpressionFactory.createStringVariableReference(id);} COMMA e = stringExpression |
	{isVariableOfType(input.LT(1).getText(), "INT")}? id = Identifier
			{ref = ExpressionFactory.createNumberVariableReference(id);} COMMA e = numberExpression |
	{isVariableOfType(input.LT(1).getText(), "DOUBLE")}? id = Identifier
			{ref = ExpressionFactory.createNumberVariableReference(id);} COMMA e = numberExpression |
				//{isVariableOfType(input.LT(1).getText(), "TYPE")}?
	id = Identifier
		{ref = ExpressionFactory.createAnnotationTypeVariableReference(id);} COMMA e = typeExpression |
	)
	{action = ActionFactory.createAction(name, ref, e);} // TODO handle identifier
	;
	
actionMarkSkill returns [TextMarkerAction action = null]
	:	
	name = MARKSKILL COMMA typeExpr = typeExpression COMMA list = dottedIdentifier COMMA index = numberExpression (COMMA ignore = booleanExpression COMMA numExpr = numberExpression)?
	{action = ActionFactory.createAction(name, typeExpr, index, ignore, numExpr);} // TODO handle list
	;

//unknown
actionFeature returns [TextMarkerAction action = null]
	:
	name = SETFEATURE COMMA f = stringExpression COMMA v = argument
	{action = ActionFactory.createAction(name, f, v);}
	;

//unknown
actionUnmark returns [TextMarkerAction action = null]
	:
	name = UNMARK COMMA f = typeExpression
	{action = ActionFactory.createAction(name, f);}
	;

//unknown

actionTransfer returns [TextMarkerAction action = null]
	:
	name = TRANSFER COMMA f = typeExpression
	{action = ActionFactory.createAction(name, f);}
	;

actionTrie returns [TextMarkerAction action = null]
@init {
Map<Expression, Expression> map = new HashMap<Expression, Expression>();
}
	:
	name = TRIE
		(COMMA key = stringExpression ASSIGN_EQUAL 
		value = typeExpression{map.put(key,value);})+ 
		COMMA list = dottedIdentifier 
	COMMA ignoreCase = booleanExpression 
	COMMA ignoreLength = numberExpression 
	COMMA edit = booleanExpression 
	COMMA distance = numberExpression 
	COMMA ignoreChar = stringExpression
	//TODO cost parameter
	{action = ActionFactory.createAction(name, map, list, ignoreCase, ignoreLength, edit, distance, ignoreChar);}
	;
*/
//OKdc
varArgumentList returns [StringBuilder args = new StringBuilder()]
	:
	arg = argument {args.append(arg);} (COMMA arg = argument {args.append(",").append(arg);})*
	;

//changed but unknown status
argument returns [StringBuilder arg = new StringBuilder()] //SimpleReference arg1 = null]
options {
	backtrack = true;
}
	:
	 a4 = stringExpression {arg.append(a4);}
	| a2 = booleanExpression {arg.append(a2);}
	| a3 = numberExpression {arg.append(a3);}
	| a1 = typeExpression {arg.append(a1);}
	//token = (
	//(booleanExpression[par]) => booleanExpression[par]
	//| (numberExpression[par]) => numberExpression[par]
	//| (stringExpression[par]) => stringExpression[par]
	//| (typeExpression[par]) => typeExpression[par]
	//)
	//{arg = token;}
	;


	
innerList returns [StringBuilder result = new StringBuilder()]
	:
	LBRACK id = Identifier {result.append("[").append(id.getText());}
	
	('|' id = Identifier {result.append("|").append(id.getText());} )* RBRACK
	{result.append("]");}
	;	

//snooze
dottedIdentifier returns [StringBuilder token = new StringBuilder()]
	:
	id = Identifier {token.append(id.getText());}
	(
		dot = DOT
		idn = Identifier
		{token.append(dot.getText()).append(id.getText());}
	)*
	;

//snooze	
dottedId returns [StringBuilder token = new StringBuilder()]
//@init {CommonToken ct = null;}
	:
	id = Identifier// {ct = new CommonToken(id);}
	{token.append(id.getText());}

	(
		//dot = DOT {ct.setText(ct.getText() + dot.getText());}
		//id = Identifier {ct.setStopIndex(getBounds(id)[1]);
		//                 ct.setText(ct.getText() + id.getText());}
		dot = DOT 
		id = Identifier
		{token.append(dot.getText()).append(id.getText());}
	)*
//	{token = ct;
//	 return token;}
	;

//seems OK
annotationType returns [StringBuilder at = new StringBuilder()]
	: 
	(
//{if(!isType($blockDeclaration::env, input.LT(1).getText())) {reporter.reportError(new FailedPredicateException(input,input.LT(1).getText(),"!isType($blockDeclaration::env, input.LT(1).getText())"));}} //m 
	//{isType($blockDeclaration::env, input.LT(1).getText())}? 
	atRef = annotationTypeVariableReference {at.append(atRef);}
	| basicAT = BasicAnnotationType {at.append(basicAT.getText());}
	)
	;
	
annotationTypeVariableReference returns [StringBuilder typeVar = new StringBuilder()]
  :
  //{isVariableOfType(input.LT(1).getText(), "TYPE")}? //m
  atRef = Identifier 
  {typeVar.append(atRef.getText());}
;

//seems OK
numberExpression returns [StringBuilder expr = new StringBuilder()]
	:
	e = additiveExpression
	{/*if(e!=null)*/ expr.append(e);}
	;

//seems OK
additiveExpression returns [StringBuilder root = new StringBuilder()]
    :   expr1=multiplicativeExpression {root.append(expr1);}
	( op=(PLUS | MINUS) expr2=multiplicativeExpression {root.append(op.getText()).append(expr2);})*
	;

//NOT OK TODO
multiplicativeExpression returns [StringBuilder root = new StringBuilder()]
    :
	(expr1 = simpleNumberExpression {root.append(expr1);}
	( op=( STAR | SLASH | PERCENT ) sNE = simpleNumberExpression {root.append(op.getText()).append(sNE);} )*
	|   e1 = numberFunction {root.append(e1);})
	;

//seems OK
numberExpressionInPar returns [StringBuilder expr = new StringBuilder()]
	:
	LPAREN numE = numberExpression RPAREN {expr.append("(").append(numE).append(")");}
	;

//seems OK
simpleNumberExpression returns [StringBuilder expr = new StringBuilder()]
	:
	  (m = MINUS)? decLit = DecimalLiteral
	  //{expr = ExpressionFactory.createDecimalLiteral(decLit,m);}
	  {	
	  	if(m!=null)
	  		expr.append(m.getText()).append(decLit.getText());
	  	else
	  		expr.append(decLit.getText());}
	| m = MINUS? fpLit = FloatingPointLiteral
	 // {expr = ExpressionFactory.createFloatingPointLiteral(fpLit,m);}
	  {	
	  	if(m!=null)
	  		expr.append(m.getText()).append(fpLit.getText());
	  	else
	  		expr.append(fpLit.getText());}
	| m = MINUS? numVarRef = numberVariable
	  //{expr = numVarRef;}
	  {	
	  	if(m!=null)
	  		expr.append(m.getText()).append(numVarRef);
	  	else
	  		expr.append(numVarRef);}
	| numExprPar = numberExpressionInPar
	 // {expr = numExprPar;}
	  {expr.append(numExprPar);}
	;

// not checked
numberFunction returns [StringBuilder expr = new StringBuilder()]
	:
	(op=(EXP | LOGN | SIN | COS | TAN) numExprP=numberExpressionInPar)
	//{expr = ExpressionFactory.createUnaryArithmeticExpr(numExprP,op);}
	//| {root = ExpressionFactory.createNumberFunction(numExprP,op)}
	{expr.append(op.getText()).append(numExprP);}
	| (e = externalNumberFunction)=> e = externalNumberFunction {expr.append(e);}
	;

// not checked
externalNumberFunction returns [StringBuilder expr = new StringBuilder()]
	:
	//{isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")}?
	id = Identifier LPAREN
	args = varArgumentList	RPAREN
	{expr.append(id.getText()).append("(").append(args).append(")");}
	/*{
		expr = external.createExternalNumberFunction(id, args);
	}*/
	;

//OK
numberVariable returns [StringBuilder expr = new StringBuilder()]
	:
	//   ( {isVariableOfType(input.LT(1).getText(), "INT")}? numVarRef = Identifier //
	// | {isVariableOfType(input.LT(1).getText(), "DOUBLE")}? numVarRef = Identifier)
	 numVarRef = Identifier
	 //{expr = ExpressionFactory.createNumberVariableReference(numVarRef);}
	 {expr.append(numVarRef.getText());}
	;
	//catch [Exception e]{expr = ExpressionFactory.createNumberVariableReference(input.LT(1));}
	
//OK - interface to flag stringExpressions?
stringExpression returns [StringBuilder expr = new StringBuilder()]
//@init {List<Expression> exprList = new ArrayList<Expression>();}
	:
	e = stringFunction {expr.append(e);} 
	|
	//strExpr1 = simpleStringExpression {if (strExpr1!=null) exprList.add(strExpr1);}
	strExpr1 = simpleStringExpression {expr.append(strExpr1);}
	(PLUS (nextstrExpr = simpleStringExpression | nextstrExpr = numberExpressionInPar) {expr.append(" + ").append(nextstrExpr);})*
	 //| nextstrExpr = numberExpressionInPar) {if (nextstrExpr!=null) exprList.add(nextstrExpr);})*
	 
	;


// not checked
stringFunction returns [StringBuilder expr = new StringBuilder()]
	:
	(e = externalStringFunction)=> e = externalStringFunction {expr.append(e);}
	;

// not checked
externalStringFunction returns [StringBuilder expr = new StringBuilder()]
	:
	//{isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION")}?
	 id = Identifier LPAREN
	args = varArgumentList	RPAREN
	{expr.append(id.getText()).append("(").append(args).append(")");}
	
	/*{
		expr = external.createExternalStringFunction(id, args);
	}*/
	;

//OK - interface to flag stringExpressions?
simpleStringExpression returns [StringBuilder expr = new StringBuilder()]
	: 
	lit = StringLiteral {expr.append(lit.getText());} 
	 | /*{isVariableOfType(input.LT(1).getText(), "STRING")}?*/
	 	 variableId = Identifier {expr.append(variableId.getText());} 
	; /*{isVariableOfType(input.LT(1).getText(), "STRING")}?*/

// commented thu, 19.02.09 => rewritten 'stringExpression'
//simpleStringExpressionOrNumberExpression returns [Expression expr = null]
//	: StringLiteral
//	 | {isVariableOfType(input.LT(1).getText(), "STRING")}? id = Identifier
//	| e = numberExpressionInPar {} 
//	;

//OK - interface to flag booleanExpressions?
booleanExpression returns [StringBuilder expr = new StringBuilder()]
options {
	backtrack = true;
}
	:	
	bcE = composedBooleanExpression {expr.append(bcE);}
	| sbE = simpleBooleanExpression {expr.append(sbE);}
	| //{isVariableOfType(input.LT(1).getText(), "BOOLEAN")}?
	  (variableId = Identifier
	  //{expr = ExpressionFactory.createBooleanVariableReference(variableId);})
	  	{expr.append(variableId.getText());}
	  )
	 // {expr = ExpressionFactory.createBooleanExpression(expr);}
	;

// not checked
composedBooleanExpression returns [StringBuilder expr = new StringBuilder()]

	:
	e1 = booleanFunction {expr.append(e1);}
	| bnE = booleanNumberExpression {expr.append(bnE);}
	| e2 = booleanCompare {expr.append(e2);}
	;

// not checked
booleanFunction returns [StringBuilder expr = new StringBuilder()]

	:
	(op = XOR LPAREN e1 = booleanExpression COMMA e2 = booleanExpression RPAREN)
	//{expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}
	{expr.append(op.getText()).append("(").append(e1).append(",").append(e2).append(")");}
	//| (e = externalBooleanFunction)=> e = externalBooleanFunction {expr.append(e);}
	;
	
// not checked
externalBooleanFunction returns [StringBuilder expr = new StringBuilder()]
	:
	//{isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION")}?
	id = Identifier LPAREN
	args = varArgumentList	RPAREN
	{expr.append(id.getText()).append("(").append(args).append(")");}
	/*{
		expr = external.createExternalBooleanFunction(id, args);
	}*/
	;

// not checked
booleanCompare returns [StringBuilder expr = new StringBuilder()]
	:
	(e1 = simpleBooleanExpression op = (EQUAL | NOTEQUAL) e2 = booleanExpression)
	//{expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}
	{expr.append(e1).append(op.getText()).append(e2);}
	;

//OK
simpleBooleanExpression returns  [StringBuilder expr = new StringBuilder()]
	:
	(value = TRUE 
	| value = FALSE)
	//{expr = ExpressionFactory.createSimpleBooleanExpression(value);}
	{expr.append(value.getText());}
	;

// TODO	requires numberExpression first!
//nearly OK
booleanNumberExpression  returns  [StringBuilder expr = new StringBuilder()]
	:
	e1 = numberExpression
	op = (LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL)
	e2 = numberExpression
	//{expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);}
	{expr.append(e1).append(op.getText()).append(e2);}
	;

genericVariableReference returns[StringBuilder ref = new StringBuilder()]
:
  id=Identifier
  {ref.append(id.getText());}
;


// Lexer



/*TRIE
	:	'TRIE'
	;*/

DECLARE
	:	'DECLARE'
	;

LIST
	:	'LIST'
	;

TABLE
	:	'TABLE'
	;

AND
	:	'AND'
	;

/*CONTEXTCOUNT
	:	'CONTEXTCOUNT'
	;

COUNT
	:	'COUNT'
	;

CURRENTCOUNT
	:	'CURRENTCOUNT'
	;

TOTALCOUNT
	:	'TOTALCOUNT'
	;
*/
INLIST
	:	'INLIST'
	;
/*
ISINTAG
	:	'ISINTAG'
	;

LAST
	:	'LAST'EQUAL
	;
*/
MOFN
	:	'MOFN'
	;
/*
NEAR
	:	'NEAR'
	;
*/
OR
	:	'OR'
	;
/*
POSITION
	:	'POSITION'
	;

REGEXP
	:	'REGEXP'
	;

SCORE
	:	'SCORE'
	;

VOTE
	:	'VOTE'
	;
*/
IF
	:	'IF'
	;
/*
FEATURE
	:	'FEATURE'
	;

PARSE
	:	'PARSE'
	;
*/
CREATE
	:	'CREATE'
	;

FILL
	:	'FILL'
	;
/*
ATTRIBUTE
	:	'ATTRIBUTE'
	;

COLOR
	:	'COLOR'
	;

DEL
	:	'DEL'
	;

LOG
	:	'LOG'
	;

MARKONCE
	:	'MARKONCE'
	;

MARKFAST
	:	'MARKFAST'
	;

MARKLAST
	:	'MARKLAST'
	;

REPLACE
	:	'REPLACE'
	;

RETAINMARKUP
	:	'RETAINMARKUP'
	;

RETAINTYPE
	:	'RETAINTYPE'
	;

FILTERMARKUP
	:	'FILTERMARKUP'
	;

FILTERTYPE
	:	'FILTERTYPE'
	;
*/
CALL
	:	'CALL'
	;
/*
ASSIGN
	:	'ASSIGN'
	;

MARKSKILL
	:	'MARKSKILL'
	;

SETFEATURE
	:	'SETFEATURE'
	;
	
UNMARK
	:	'UNMARK'
	;

TRANSFER
	:	'TRANSFER'
	;


BEFORE
	:	'BEFORE'
	;

AFTER
	:	'AFTER'
	;

IS 	
	:	'IS'	 
	;
*/
BasicAnnotationType 
	: 'COLON'| 'SW' | 'MARKUP' | 'PERIOD' | 'CW'| 'NUM' | 'QUESTION' | 'SPECIAL' | 'CAP' | 'COMMA' | 'EXCLAMATION' | 'SEMICOLON' | 'NBSP'| 'AMP' |
	'_' | 'SENTENCEEND' | 'W' | 'PM' | 'ANY' | 'ALL' | 'SPACE' | 'BREAK' 
	;
	
/*LogLevel:
	'finest' | 'finer' | 'fine' | 'config' | 'info' | 'warning' | 'severe'
	;	

OldColor 
	: 'black' | 'maroon' | 'green' | 'olive' | 'navy' | 'purple' | 'teal' | 'gray' | 'silver' | 'red' | 'lime' | 'yellow' | 'blue' | 'fuchsia' | 'aqua'
	;
*/
PackageString   :	'PACKAGE';
ScriptString	:	'SCRIPT';
EngineString	:	'ENGINE';
BlockString 	:	'BLOCK';
TypeString 	:	'TYPE';
IntString	:	'INT';
DoubleString	:	'DOUBLE';
StringString	:	'STRING';
BooleanString	:	'BOOLEAN';
TypeSystemString:	'TYPESYSTEM';	
//SymbolString	:	'SYMBOL';
ConditionString	:	'CONDITION';	
ACTION		:	'ACTION';

EXP 	:	 'EXP';
LOGN	:	'LOGN';
SIN	:	'SIN';
COS	:	'COS';
TAN	:	'TAN';
XOR	: 	'XOR';
TRUE 	:	'true';
FALSE 	:	'false';

/*
CONTAINS:	'CONTAINS';

THEN 	:	 '->';

HexLiteral : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ; */

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

//OctalLiteral : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
IntegerTypeSuffix : ('l'|'L') ;


fragment
Exponent : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

FloatingPointLiteral
    :   ('0'..'9')+ '.' ('0'..'9')* Exponent? FloatTypeSuffix?
    |   '.' ('0'..'9')+ Exponent? FloatTypeSuffix?
	;

fragment
FloatTypeSuffix : ('f'|'F'|'d'|'D') ;

/*CharacterLiteral
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;*/

StringLiteral
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
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
    : 
       '\u0024' |
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



LPAREN	: '(' {implicitLineJoiningLevel++;} ;

RPAREN	: ')' {implicitLineJoiningLevel--;} ;

LBRACK	: '[' {implicitLineJoiningLevel++;} ;

RBRACK	: ']' {implicitLineJoiningLevel--;} ;

LCURLY	: '{' {implicitLineJoiningLevel++;} ;

RCURLY	: '}' {implicitLineJoiningLevel--;} ;

//CIRCUMFLEX	: '^' ;


//AT : '@' ;

DOT : '.' ;

//COLON 	: ':' ;

COMMA	: ',' ;

SEMI	: ';' ;

PLUS	: '+' ;

MINUS	: '-' ;

STAR	: '*' ;

SLASH	: '/' ;

//VBAR	: '|' ;

//AMPER	: '&' ;

LESS	: '<' ;

GREATER	: '>' ;

ASSIGN_EQUAL	: '=' ;

PERCENT	: '%' ;

QUESTION	: '?' ;

EQUAL	: '==' ;

NOTEQUAL	: '!=' ;

//ALT_NOTEQUAL: '<>' ;

LESSEQUAL	: '<=' ;


GREATEREQUAL	: '>=' ;

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;







COMMENT
    :   '/*'{if (input.LA(1)=='*') $type=DocComment; else $channel=HIDDEN;} ( options {greedy=false;} : . )* '*/' 
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;


    


