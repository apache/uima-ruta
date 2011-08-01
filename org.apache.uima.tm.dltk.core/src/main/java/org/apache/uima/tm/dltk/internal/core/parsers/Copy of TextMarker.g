grammar TextMarker;

options {
	language = Java;
}

tokens {
	DocComment;
	Annotation;
	ListIdentifier;
}

@lexer::header {
	package org.apache.uima.tm.dltk.internal.core.parsers.old;
}

@parser::header {
package org.apache.uima.tm.dltk.internal.core.parsers.old;
import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
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
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.TokenStream;

import org.eclipse.dltk.ast.*;
import org.eclipse.dltk.ast.declarations.*;
import org.eclipse.dltk.ast.expressions.*;
import org.eclipse.dltk.ast.references.*;
import org.eclipse.dltk.ast.statements.*;

import org.apache.uima.tm.dltk.core.extensions.TextMarkerExternalFactory;
import org.apache.uima.tm.dltk.parser.ast.*;
import org.apache.uima.tm.dltk.parser.ast.actions.*;
import org.apache.uima.tm.dltk.parser.ast.conditions.*;
import org.apache.uima.tm.dltk.parser.ast.declarations.*;
import org.apache.uima.tm.dltk.parser.ast.expressions.*;
import org.apache.uima.tm.dltk.internal.core.builder.DescriptorManager;
}

@lexer::members {
	public int implicitLineJoiningLevel = 0;
	public int startPos=-1;
	
	public void emitErrorMessage(String msg) {
	}
}
@parser::members {
	public DLTKTextMarkerErrorReporter reporter;
	public ModuleDeclaration md;
	private List<String> vars = new ArrayList<String>();	
	private Map<String, String> varTypeMap = new HashMap<String, String>();
	private Map<String, String> lists = new HashMap<String, String>();
	private Map<String, String> tables = new HashMap<String, String>();
	public int length;
	public DLTKTokenConverter converter;
	public DescriptorManager descriptor;
	private int level = 0;
	private TextMarkerExternalFactory external = new TextMarkerExternalFactory();
	
	private String module;
	private String packageString;
	
	
	public List<String> getVariables() {
		return vars;
	}
	
	public Map<String, String> getVariableTypes() {
		return varTypeMap;
	}
	
	DLTKToken toDLTK(Token token) {
		return converter.convert(token);
	}
	public void emitErrorMessage(String msg) {
		reporter.reportMessage(msg);
	}
	public void reportError(RecognitionException e) {
		if( reporter != null ) {
			reporter.reportError(e);
		}
	}
	
	public void addType(TextMarkerBlock parent, String type, String parentType) {
		//vars.add(type);
		if(parentType == null || parentType.trim().equals("") || parentType.equals("Annotation") ) {
			parentType = "uima.tcas.Annotation";
    		} else if(parentType.indexOf(".") != -1) {
    			// already has its own namespace
    		} else {	
    			parentType = parent.getNamespace() + "."+parentType.trim();
    			vars.add(type);
		}
		descriptor.addType(parent.getNamespace()+"."+type.trim(), "Type defined in "+packageString+"."+module, parentType);
	}
	
	public void addPredefinedType(String type) {
		vars.add(type);
		varTypeMap.put(type, "TYPE");
		
	}
	
	public void addType(TextMarkerBlock parent, String name, String parentType, List featuresTypes, List featuresNames) {
		//vars.add(name);
		if(parentType.equals("Annotation")) {
			parentType = "uima.tcas.Annotation";
			
    		} else if(parentType.indexOf(".") != -1) {
    			// already has its own namespace	
    		} else {
    			vars.add(name);		
    			parentType = parent.getNamespace() + "."+parentType.trim();
		}
		name = parent.getNamespace() + "."+name.trim();
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
	
	public void addWordList(TextMarkerBlock parent, String name, String list) {
		lists.put(name, list);
	}
	
	public void addCSVTable(TextMarkerBlock parent, String name, String table) {
		tables.put(name, table);
	}
	
	public boolean isType(TextMarkerBlock parent, String type) {
		return vars.contains(type);
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
		if( reporter != null ) {
			reporter.reportError(exception1);
		}
		recover(input,exception1);
	}
	catch (Throwable exception2) {
		if( reporter != null ) {
			reporter.reportThrowable(exception2);
		}
	}
}


file_input [String module]
@init {
TextMarkerScriptBlock rootBlock = null;
List<Statement> stmts = new ArrayList<Statement>();
}
	:	
	p = packageDeclaration
	{
	String packageName = "org.apache.uima.tm";
	if(p != null) {packageName = p.getName();}
	rootBlock = ScriptFactory.createScriptBlock(0,0,0,0,module, null, null, packageName);
	stmts.add(p);
	this.module = module;
	this.packageString = p.getName();
	}
	{$blockDeclaration.push(new blockDeclaration_scope());$blockDeclaration::env = rootBlock;}

	gs = globalStatements
	s = statements
	{
	  stmts.addAll(gs);
	  stmts.addAll(s);
  	  for (Statement stmt : stmts){
		  if (stmt != null) {
		    md.addStatement(stmt);
		  }
	  };
	}
	EOF
	;
	

packageDeclaration returns [TextMarkerPackageDeclaration pack]
	:	pString = PackageString p = dottedId SEMI
	{
	pack = StatementFactory.createPkgDeclaration(p, pString);
	}
	;

statements returns [List<Statement> stmts = new ArrayList<Statement>()]
	:
	(morestmts = statement {if(morestmts != null) {stmts.addAll(morestmts);}})*
	;
	
globalStatements returns [List<Statement> stmts = new ArrayList<Statement>()]
	:
	(morestmts = globalStatement {if(morestmts != null) {stmts.addAll(morestmts);}})*
	;

globalStatement returns [List<Statement> stmts = new ArrayList<Statement>()]
	:
	stmtImport = importStatement {stmts.add(stmtImport);}
	;
	
statement returns [List<Statement> stmts = new ArrayList<Statement>()]
	:	
	( stmts1 = declaration {stmts.addAll(stmts1);}
	| stmtVariable = variableDeclaration {stmts.addAll(stmtVariable);}
	| stmt3 = blockDeclaration {stmts.add(stmt3);}
	//| stmt4 = tmRule {stmts.add(stmt4);}
	| stmt2 = simpleStatement {stmts.add(stmt2);}
	

	)
	;
	
importStatement returns [Statement stmt = null]
	:
	system = TypeSystemString ts = dottedId2 SEMI {stmt = StatementFactory.createImportTypeSystem(ts,system);addImportTypeSystem(ts.getText());}
	| im = ScriptString name = dottedId2 SEMI {stmt = StatementFactory.createImportScript(name, im); addImportScript(name);}
	| im = EngineString name = dottedId2 SEMI {stmt = StatementFactory.createImportEngine(name, im); addImportEngine(name);}
	;
	
variableDeclaration returns [List<Statement> stmts = new ArrayList<Statement>()]
@init {
	List decls = new ArrayList();
}
	:
	type = IntString id = Identifier {addVariable(id.getText(), type.getText()); decls.add(StatementFactory.createIntVariable(id, type));}
		(COMMA id= Identifier {addVariable(id.getText(), type.getText()); decls.add(StatementFactory.createIntVariable(id, type));}
		)* (ASSIGN_EQUAL init = numberExpression)?  SEMI
		{
		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
		 }
	|
	type = DoubleString id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createDoubleVariable(id, type));}
			(COMMA id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createDoubleVariable(id, type));}
		 )* (ASSIGN_EQUAL init = numberExpression)?  SEMI
		{
		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
		 }
	|
	type = StringString id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createStringVariable(id, type));}
			(COMMA id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createStringVariable(id, type));}
		 )* (ASSIGN_EQUAL init = stringExpression)?  SEMI
		{
		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
		 }
	|
	type = BooleanString id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createBooleanVariable(id, type));}
			(COMMA id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createBooleanVariable(id, type));}
		 )* (ASSIGN_EQUAL init = booleanExpression)?  SEMI
		{
		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
		 }
	|
	type = TypeString id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));}
			(COMMA id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));}
		 )* (ASSIGN_EQUAL init = annotationType)?  SEMI
		{
		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
		 }
        |
        type = LIST id = Identifier (ASSIGN_EQUAL list = listExpression)? SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createListVariable(id,type,list));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        }
        |
        type = TABLE id = Identifier (ASSIGN_EQUAL table = tableExpression)?  SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createTableVariable(id,type,table));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, table));
        }
	|
	stmt = conditionDeclaration {stmts.add(stmt);}
	|
	stmt = actionDeclaration {stmts.add(stmt);}
	;

conditionDeclaration returns [Statement stmt = null]
    :
    declareToken = ConditionString id = Identifier {addVariable(id.getText(), declareToken.getText());}
    ASSIGN_EQUAL 
    LPAREN cons = conditions RPAREN SEMI
    {stmt = StatementFactory.createComposedVariableConditionDeclaration(id, cons);}
    ;

//TODO added rule
actionDeclaration returns [Statement stmt = null]
    :
    declareToken = ACTION id = Identifier {addVariable(id.getText(), declareToken.getText());}
    ASSIGN_EQUAL
    LPAREN a = actions RPAREN SEMI
    {stmt = StatementFactory.createComposedVariableActionDeclaration(id, a);}
    ;

declaration returns [List<Statement> stmts = new ArrayList<Statement>()]
@init {
	Statement stmt = null;
	List featureTypes = new ArrayList();
	List featureNames = new ArrayList();
	List declarations = new ArrayList();
}
	:
	//TODO added lazy parent
	(declareToken=DECLARE lazyParent = annotationType?
		 id = Identifier {addVariable(id.getText(), declareToken.getText());}
			{addType($blockDeclaration::env, id.getText(), lazyParent == null ? null : lazyParent.toString()); declarations.add(StatementFactory.createAnnotationType(id,declareToken));}
		(COMMA 
		 id = Identifier {addVariable(id.getText(), declareToken.getText());}
			{addType($blockDeclaration::env, id.getText(),  lazyParent == null ? null : lazyParent.toString()); declarations.add(StatementFactory.createAnnotationType(id,declareToken));}
		 )* end = SEMI
		 {
		 stmt = StatementFactory.createDeclareDeclarationsStatement(declareToken, declarations, lazyParent);
		 stmts.add(stmt);
		 }
	|
	declareToken=DECLARE {isVariableOfType(input.LT(1).getText(), "TYPE")}? type=annotationType
	id = Identifier {addVariable(id.getText(), declareToken.getText());}
		(LPAREN 
			(
			obj1 = annotationType{featureTypes.add(obj1.toString());} 
			| obj2 = StringString{featureTypes.add(obj2.getText());} 
			| obj3 = DoubleString{featureTypes.add(obj3.getText());} 
			| obj4 = IntString{featureTypes.add(obj4.getText());}
			| obj5 = BooleanString{featureTypes.add(obj5.getText());}
			) 
			fname = Identifier{featureNames.add(fname.getText());} 
			(
			COMMA 
			(
			obj1 = annotationType{featureTypes.add(obj1.toString());} 
			| obj2 = StringString{featureTypes.add(obj2.getText());} 
			| obj3 = DoubleString{featureTypes.add(obj3.getText());}
			| obj4 = IntString{featureTypes.add(obj4.getText());}
			| obj5 = BooleanString{featureTypes.add(obj5.getText());}
			)
			fname = Identifier{featureNames.add(fname.getText());})* 
		RPAREN) SEMI // TODO removed question mark
		{
		addType($blockDeclaration::env, id.getText(), type.toString(), featureTypes, featureNames);
		stmt = StatementFactory.createAnnotationType(id,declareToken);
		stmts.add(stmt);
		}
	)
	;



	

blockDeclaration returns [TextMarkerBlock block = null]
options{
	backtrack = true;
}
scope {
	TextMarkerBlock env;
	}	
@init{
TextMarkerRuleElement re = null;
level++;
}
@after {
level--;
}
	:
	declareToken = BlockString 
	LPAREN
	id = Identifier {addVariable(id.getText(), declareToken.getText());}
	{
		block = ScriptFactory.createScriptBlock(id, declareToken, $blockDeclaration[level - 1]::env);
		$blockDeclaration::env = block;
	}
	RPAREN
	re1 = ruleElementWithCA {re = re1;}
	
	LCURLY body = statements rc = RCURLY
	{ScriptFactory.finalizeScriptBlock(block, rc, re, body);}
	;

	
ruleElementWithCA returns [TextMarkerRuleElement re = null] 
    :
    idRef=typeExpression quantifier = quantifierPart? 
        LCURLY c = conditions? (THEN a = actions)? end = RCURLY
        {re = ScriptFactory.createRuleElement(idRef,quantifier,c,a, end);}
    ;

	
ruleElementWithoutCA returns [TextMarkerRuleElement re = null] 
    :
    idRef=typeExpression quantifier = quantifierPart? 
             {re = ScriptFactory.createRuleElement(idRef,quantifier,null,null, null);}

    ;
		
simpleStatement returns [TextMarkerRule stmt = null]
	: 
	elements=ruleElements SEMI 
		{stmt = ScriptFactory.createRule(elements);}
	;

ruleElements returns [List<Expression> elements = new ArrayList<Expression>()]
	:
	re = ruleElement {if(re!=null) elements.add(re);} (re = ruleElement {if(re!=null) elements.add(re);})*
	;	
	
blockRuleElement returns [TextMarkerRuleElement rElement = null] //[List<TextMarkerRuleElement> elements = new ArrayList<TextMarkerRuleElement>()]
	:
	re = ruleElementType {rElement = re;}
	;	
	
ruleElement returns [Expression re = null]
	:
	re1 = ruleElementType {re = re1;}
	| re2 = ruleElementLiteral {re = re2;}
	| re3 = ruleElementComposed {re = re3;}
	;
	
ruleElementComposed returns [ComposedRuleElement re = null] 
	:
	LPAREN res = ruleElements RPAREN q = quantifierPart
	{re = ScriptFactory.createComposedRuleElement(res, q, $blockDeclaration::env);}
	;

ruleElementType returns [TextMarkerRuleElement re = null] 
    :
    (typeExpression)=>idRef=typeExpression quantifier = quantifierPart? 
        (LCURLY c = conditions? (THEN a = actions)? end = RCURLY)?
        {
        // TODO handle quantifierPart.
        re = ScriptFactory.createRuleElement(idRef,quantifier,c,a,end);}
        //start, end, "", cp, ap, "", $blockDeclaration::env);}
    ;

ruleElementLiteral returns [TextMarkerRuleElement re = null] 
    :
    (simpleStringExpression)=>idRef=simpleStringExpression quantifier = quantifierPart? 
        (LCURLY c = conditions? (THEN a = actions)? end = RCURLY)?
        {
        // TODO handle quantifierPart.
        re = ScriptFactory.createRuleElement(idRef,quantifier,c,a,end);}
        //start, end, "", cp, ap, "", $blockDeclaration::env);}
    ;
    
conditions returns [List<TextMarkerCondition> conds = new ArrayList<TextMarkerCondition>()]
    :
    c = condition {conds.add(c);} (COMMA c = condition {conds.add(c);} )*
    ;

  
actions returns [List<TextMarkerAction> actions = new ArrayList<TextMarkerAction>()]
    :
    a = action {actions.add(a);} (COMMA a = action {actions.add(a);} )*
    ; 	
	
typeExpression returns [Expression expr = null]
	:
	tf = typeFunction {expr = tf;}
	| st = simpleTypeExpression 
	{expr = ExpressionFactory.createTypeExpression(st);
	 }
	;

// not checked
typeFunction returns [Expression expr = null]
	:
	(e = externalTypeFunction)=> e = externalTypeFunction {expr = e;}
	;

// not checked
externalTypeFunction returns [Expression expr = null]
	:
	{isVariableOfType(input.LT(1).getText(), "TYPEFUNCTION")}? id = Identifier 
	LPAREN
	args = varArgumentList	
	RPAREN
	{
		expr = external.createExternalTypeFunction(id, args);
	}
	;

simpleTypeExpression returns [Expression type = null]
	:
	at = annotationType {type = at;}
	;
	
	
quantifierPart returns [List<Expression> exprs = new ArrayList<Expression>()]
	:
	 s = STAR q = QUESTION? {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(s,q));}
	| p = PLUS q = QUESTION? {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(p,q));}
	| q1 = QUESTION q = QUESTION? {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(q1,q));}
	| (LBRACK min = numberExpression (COMMA (max = numberExpression)?)? RBRACK q = QUESTION?
		 {if(min!=null) {exprs.add(min);}
		  if(max!=null) {exprs.add(max);}
		 }
	   )
	;
	
	
condition returns [TextMarkerCondition result = null]
	:
	(
	c = conditionAnd
	| c = conditionContains
	| c = conditionContextCount
	| c = conditionCount
	| c = conditionCurrentCount
	| c = conditionInList
	| c = conditionIsInTag
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
	| (c = externalCondition)=> c = externalCondition
	| c = variableCondition
	) {result = c;}
	;
	
//TODO added rule
variableCondition returns [TextMarkerCondition condition = null]
	:		
	{isVariableOfType(input.LT(1).getText(), "CONDITION")}? id = Identifier
	{
		condition = ConditionFactory.createCondition(id);
	}
	;	
	
externalCondition returns [TextMarkerCondition condition = null]
	:
	{isVariableOfType(input.LT(1).getText(), "CONDITION")}? id = Identifier
	LPAREN
	args = varArgumentList	
	RPAREN
	{
		condition = external.createExternalCondition(id, args);
	}
	;
conditionAnd returns [TextMarkerCondition cond = null]
    :   
    name = AND LPAREN conds = conditions RPAREN 
    {cond = ConditionFactory.createCondition(name, conds);}
    ;

conditionContains returns [TextMarkerCondition cond = null]
    :   
    name = CONTAINS LPAREN typeExpr = typeExpression (COMMA min = numberExpression COMMA max = numberExpression (COMMA percent = booleanExpression)?)? RPAREN
    {cond = ConditionFactory.createCondition(name, typeExpr, min, max, percent);}
    ;
    
conditionContextCount returns [TextMarkerCondition cond = null]
    :   
    name = CONTEXTCOUNT LPAREN typeExpr = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)?
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createCondition(name, typeExpr, min, max);}
    ;
    
conditionCount returns [TextMarkerCondition cond = null]
    :   
    name = COUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createCondition(name, type, min, max);}
    ;   
    
conditionCurrentCount returns [TextMarkerCondition cond = null]
    :   
    name = CURRENTCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createCondition(name,type, min, max);}
    ;   
    
conditionTotalCount returns [TextMarkerCondition cond = null]
    :   
    name = TOTALCOUNT LPAREN type = typeExpression (COMMA min = numberExpression COMMA max = numberExpression)? 
    (COMMA var = numberVariable)? RPAREN
    {cond = ConditionFactory.createCondition(name, type, min, max);}
    ;
conditionInList returns [TextMarkerCondition cond = null]
    :
    // TODO add wordlList//innerList support
    name = INLIST LPAREN wordList = listExpression (COMMA dist = numberExpression (COMMA rel = booleanExpression)?)? RPAREN
    {cond = ConditionFactory.createCondition(name, wordList, dist, rel);}
    | name = INLIST LPAREN list = innerList (COMMA dist = numberExpression (COMMA rel = booleanExpression)?)? RPAREN
    {cond = ConditionFactory.createCondition(name, list, dist, rel);}
    ;
conditionIsInTag returns [TextMarkerCondition cond = null]
@init {
List<Expression> list1 = new ArrayList<Expression>();
List<Expression> list2 = new ArrayList<Expression>();
}
    :
    name = ISINTAG LPAREN id = stringExpression (COMMA id1 = stringExpression ASSIGN_EQUAL id2 = stringExpression {list1.add(id1);list2.add(id2);})* RPAREN
    {List exprs = new ArrayList();
    exprs.add(id);
  exprs.addAll(list1);
    exprs.addAll(list2);
    cond = ConditionFactory.createCondition(name, exprs);}  
    ;
        
conditionLast returns [TextMarkerCondition cond = null]
    :   
    name = LAST LPAREN type = typeExpression RPAREN
    {cond = ConditionFactory.createCondition(name, type);}
    ;
    
conditionMofN returns [TextMarkerCondition cond = null]
    :   
    name = MOFN LPAREN min = numberExpression COMMA max = numberExpression COMMA conds = conditions RPAREN
    {List exprs = new ArrayList();
    exprs.add(min);
    exprs.add(max);
  exprs.addAll(conds);
    cond = ConditionFactory.createCondition(name, conds);}  
    ;

conditionNear returns [TextMarkerCondition cond = null]
    :   
    name = NEAR LPAREN type = typeExpression COMMA min = numberExpression COMMA max = numberExpression (COMMA direction = booleanExpression (COMMA filtered = booleanExpression)?)? RPAREN
    {cond = ConditionFactory.createCondition(name, type, min, max, direction, filtered);}   
    ;
conditionNot returns [TextMarkerCondition cond = null]
    :   
    ((name = MINUS c = condition) |  (name = NOT LPAREN c = condition RPAREN))
    {cond = ConditionFactory.createCondition(name, c);} 
    ;
conditionOr returns [TextMarkerCondition cond = null]
    :   
    name = OR LPAREN conds = conditions RPAREN
    {cond = ConditionFactory.createCondition(name, conds);}
    ;
conditionPartOf returns [TextMarkerCondition cond = null]
    :
    name = PARTOF LPAREN type = typeExpression RPAREN   
    {cond = ConditionFactory.createCondition(name, type);}
    ;
    
conditionPartOfNeq returns [TextMarkerCondition cond = null]
    :
    name = PARTOFNEQ LPAREN type = typeExpression RPAREN   
    {cond = ConditionFactory.createCondition(name, type);}
    ;    


conditionPosition returns [TextMarkerCondition cond = null]
    :   
    name = POSITION LPAREN type = typeExpression COMMA pos = numberExpression RPAREN
    {cond = ConditionFactory.createCondition(name, type, pos);}
    ;
conditionRegExp returns [TextMarkerCondition cond = null]
    :
    name = REGEXP LPAREN pattern = stringExpression (COMMA caseSensitive = booleanExpression)? RPAREN
    {cond = ConditionFactory.createCondition(name, pattern, caseSensitive);}    
    ;
    
conditionScore returns [TextMarkerCondition cond = null]
    :
    name = SCORE LPAREN min = numberExpression (COMMA max = numberExpression  
    (COMMA var = numberVariable)?)? RPAREN
    {cond = ConditionFactory.createCondition(name, min, max, var);}
    ;

conditionVote returns [TextMarkerCondition cond = null]
    :   
    name = VOTE LPAREN type1 = typeExpression COMMA type2 = typeExpression RPAREN
    {cond = ConditionFactory.createCondition(name, type1, type2);}
    ;   
conditionIf returns [TextMarkerCondition cond = null]
    :   
    name = IF LPAREN e = booleanExpression RPAREN
    {cond = ConditionFactory.createCondition(name, e);}
    ;   
    
conditionFeature returns [TextMarkerCondition cond = null]
    :   
    name = FEATURE LPAREN se = stringExpression COMMA  v = argument RPAREN
        {cond = ConditionFactory.createCondition(name, se, v);}
    ;   
conditionParse returns [TextMarkerCondition cond = null]
    :
    name = PARSE LPAREN
     var=genericVariableReference RPAREN
    {cond = ConditionFactory.createCondition(name, var);}
    ;


conditionIs returns [TextMarkerCondition cond = null]
    :
    name = IS LPAREN type = typeExpression RPAREN
    {cond = ConditionFactory.createCondition(name, type);}
    ;

conditionBefore returns [TextMarkerCondition cond = null]
    :
    name = BEFORE LPAREN type = typeExpression RPAREN
    {cond = ConditionFactory.createCondition(name, type);}
    ;

conditionAfter returns [TextMarkerCondition cond = null]
    :
    name = AFTER LPAREN type = typeExpression RPAREN
    {cond = ConditionFactory.createCondition(name, type);}
    ;
    
conditionStartsWith returns [TextMarkerCondition cond = null]
    :
    name = STARTSWITH LPAREN type = typeExpression RPAREN
    {cond = ConditionFactory.createCondition(name, type);}
    ;
    
conditionEndsWith returns [TextMarkerCondition cond = null]
    :
    name = ENDSWITH LPAREN type = typeExpression RPAREN
    {cond = ConditionFactory.createCondition(name, type);}
    ;

	
action returns [TextMarkerAction result = null]
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
	| a = actionRetainMarkup
	| a = actionRetainType
	| a = actionFilterMarkup
	| a = actionFilterType
	| a = actionCreate
	| a = actionFill
	| a = actionCall
	| a = actionAssign
	| a = actionFeature
	| a = actionUnmark
	| a = actionTransfer
	| a = actionMarkOnce
	| a = actionTrie
	| a = actionGather	
	| a = actionExec
	| a = actionMarkTable	
	| (a = externalAction)=> a = externalAction
	| a = variableAction
	) {result = a;}
	;


variableAction returns [TextMarkerAction action = null]
	:
	{isVariableOfType(input.LT(1).getText(), "ACTION")}? id = Identifier
	{
		action = ActionFactory.createAction(id);
	}
	;
externalAction returns [TextMarkerAction action = null]
	:
	{isVariableOfType(input.LT(1).getText(), "ACTION")}? id = Identifier
	 LPAREN 
	 args = varArgumentList 
	 RPAREN	
	{
		action = external.createExternalAction(id, args);
	}
	;


actionCreate returns [TextMarkerAction action = null]
@init {
    List left = new ArrayList();
    List right = new ArrayList();
    List indexes = new ArrayList();
}
    :
    name = CREATE LPAREN structure = typeExpression
    (COMMA 
        
    (index = numberExpression {indexes.add(index);} ((COMMA index = numberExpression)=> (COMMA index = numberExpression){indexes.add(index);})* COMMA)?
    
    (fname = stringExpression ASSIGN_EQUAL obj1 = argument {left.add(fname); right.add(obj1);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = argument {left.add(fname);right.add(obj1);})*)?
    
    )? RPAREN
    {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}
    ;


actionMarkTable returns [TextMarkerAction action = null]
@init {
    List left = new ArrayList();
    List right = new ArrayList();
}
    :
    name = MARKTABLE LPAREN 
    structure = typeExpression COMMA 
    index = numberExpression COMMA
    table = tableExpression 
    (COMMA 
    fname = stringExpression ASSIGN_EQUAL obj1 = numberExpression {left.add(fname); right.add(obj1);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = numberExpression {left.add(fname);right.add(obj1);})*
    )? RPAREN
    {action = ActionFactory.createStructureAction(name, structure, index, table, left, right);}
    ;

actionGather returns [TextMarkerAction action = null]
@init {
    List left = new ArrayList();
    List right = new ArrayList();
    List indexes = new ArrayList();
}
    :
    name = GATHER LPAREN structure = typeExpression
    (COMMA 
    (index = numberExpression {indexes.add(index);} ((COMMA index = numberExpression)=>(COMMA index = numberExpression) {indexes.add(index);})* COMMA)?
    (fname = stringExpression ASSIGN_EQUAL obj1 = numberExpression {left.add(fname); right.add(obj1);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = numberExpression {left.add(fname);right.add(obj1);})*)?
    
    )? RPAREN
    {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}
    ;


actionFill returns [TextMarkerAction action = null]
@init {
    List left = new ArrayList();
    List right = new ArrayList();
}
    :
    name = FILL LPAREN structure = typeExpression
    (
    COMMA fname = stringExpression ASSIGN_EQUAL
    obj1 = argument {left.add(fname); right.add(obj1);}
    )+ RPAREN
    {action = ActionFactory.createStructureAction(name, structure, null, left, right);}
    ;
    

actionColor returns [TextMarkerAction action = null]
    :   
    name = COLOR LPAREN type = typeExpression COMMA color = stringExpression RPAREN
    {action = ActionFactory.createAction(name, type, color);}
    ;

actionDel returns [TextMarkerAction action = null]
    :   
    name = DEL
    {action = ActionFactory.createAction(name, new ArrayList());}
    ;
        
actionLog returns [TextMarkerAction action = null]
    :   
    name = LOG LPAREN lit = stringExpression (COMMA log = LogLevel)? RPAREN 
    {action = ActionFactory.createLogAction(name, lit, log);} //TODO handle logLevel
    ;

actionMark returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = MARK LPAREN 
    type = typeExpression
    {list.add(type);}
    (
    COMMA (index = numberExpression) => index = numberExpression {list.add(index);}
    )* RPAREN
    {action = ActionFactory.createAction(name, list);}
    ;

actionMarkScore returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = MARKSCORE LPAREN score = numberExpression COMMA type = typeExpression
    {list.add(score); list.add(type);}
    (
    COMMA (index = numberExpression) => index = numberExpression {list.add(index);}
    )* RPAREN
    {action = ActionFactory.createAction(name, list);}
    ;

actionMarkOnce returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = MARKONCE LPAREN ((score = numberExpression) => score = numberExpression COMMA)? (type = typeExpression) => type = typeExpression
    {list.add(score); list.add(type);}
    (
    COMMA (index = numberExpression) => index = numberExpression {list.add(index);}
    )* RPAREN
    {action = ActionFactory.createAction(name, list);}
    ;

actionMarkFast returns [TextMarkerAction action = null]
    :   
    name = MARKFAST LPAREN type = typeExpression COMMA list = listExpression (COMMA ignore = booleanExpression (COMMA numExpr = numberExpression)?)? RPAREN
    {action = ActionFactory.createAction(name, type, ignore, numExpr);} // TODO handle list
    ;

actionMarkLast returns [TextMarkerAction action = null]
    :   
    name = MARKLAST LPAREN type = typeExpression RPAREN
    {action = ActionFactory.createAction(name, type);}
    ;


actionReplace returns [TextMarkerAction action = null]
    :   
    name = REPLACE LPAREN lit = stringExpression RPAREN
    {action = ActionFactory.createAction(name, lit);}
    ;

actionRetainMarkup returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = RETAINMARKUP (LPAREN id = stringExpression {list.add(id);} (COMMA id = stringExpression {list.add(id);})* RPAREN)?
    {action = ActionFactory.createAction(name, list);}
    ;
        
actionRetainType returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = RETAINTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)?
    {action = ActionFactory.createAction(name, list);}
    ;
    
actionFilterMarkup returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = FILTERMARKUP (LPAREN id = stringExpression {list.add(id);} (COMMA id = stringExpression {list.add(id);})* RPAREN)?
    {action = ActionFactory.createAction(name, list);}
    ;

actionFilterType returns [TextMarkerAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = FILTERTYPE (LPAREN id = typeExpression {list.add(id);} (COMMA id = typeExpression {list.add(id);})* RPAREN)?
    {action = ActionFactory.createAction(name, list);}
    ;       

actionCall returns [TextMarkerAction action = null]
@init {
String string = "";
}
    :
    name = CALL LPAREN ns = dottedId RPAREN
    {action = ActionFactory.createCallAction(name, ns);} // TODO handle dottedId
    ;

actionExec returns [TextMarkerAction action = null]
@init {
String string = "";
}
    :
    name = EXEC LPAREN ns = dottedId RPAREN
    {action = ActionFactory.createCallAction(name, ns);} // TODO handle dottedId
    ;

        
actionAssign returns [TextMarkerAction action = null]
@init{
    VariableReference ref = null;
}
    :
    name = ASSIGN LPAREN
    (id = Identifier COMMA e = argument
    ) RPAREN {
    ref = ExpressionFactory.createGenericVariableReference(id);
    action = ActionFactory.createAction(name, ref, e);}
    ;

//unknown
actionFeature returns [TextMarkerAction action = null]
    :
    name = SETFEATURE LPAREN f = stringExpression COMMA v = argument RPAREN
    {action = ActionFactory.createAction(name, f, v);}
    ;

//unknown
actionUnmark returns [TextMarkerAction action = null]
    :
    name = UNMARK LPAREN f = typeExpression RPAREN
    {action = ActionFactory.createAction(name, f);}
    ;

//unknown

actionTransfer returns [TextMarkerAction action = null]
    :
    name = TRANSFER LPAREN f = typeExpression RPAREN
    {action = ActionFactory.createAction(name, f);}
    ;
    
actionTrie returns [TextMarkerAction action = null]
@init {
Map<Expression, Expression> map = new HashMap<Expression, Expression>();
}
    :
    name = TRIE LPAREN
        key = stringExpression ASSIGN_EQUAL 
        value = typeExpression{map.put(key,value);}
        (COMMA key = stringExpression ASSIGN_EQUAL 
        value = typeExpression{map.put(key,value);})*
        COMMA list = listExpression 
    COMMA ignoreCase = booleanExpression 
    COMMA ignoreLength = numberExpression 
    COMMA edit = booleanExpression 
    COMMA distance = numberExpression 
    COMMA ignoreChar = stringExpression RPAREN
    //TODO cost parameter
    {action = ActionFactory.createAction(name, map, list, ignoreCase, ignoreLength, edit, distance, ignoreChar);}
    ;   

//OKdc
varArgumentList returns [List<Expression> args = new ArrayList<Expression>()]
	:
	(LPAREN arg = argument {args.add(arg);} (COMMA arg = argument {args.add(arg);})* RPAREN)?
	;

//changed but unknown statuslistExpression
argument returns [Expression expr = null] //SimpleReference arg1 = null]
options {
	backtrack = true;
}
	:
	 a4 = stringExpression {expr = a4;}
	| a2 = booleanExpression {expr = a2;}
	| a3 = numberExpression {expr = a3;}
	| a1 = typeExpression {expr = a1;}
	//token = (
	//(booleanExpression[par]) => booleanExpression[par]
	//| (numberExpression[par]) => numberExpression[par]
	//| (stringExpression[par]) => stringExpression[par]
	//| (typeExpression[par]) => typeExpression[par]
	//)
	//{arg = token;}
	;


	
innerList returns [Expression result = null;]
@init{
	List<String> inner = new ArrayList<String>();
}
	:
	lBrak = LBRACK id = Identifier {inner.add(id.getText());}
	
	(bar = '|' {inner.add(bar.getText());} id = Identifier {inner.add(id.getText());} )* rBrak = RBRACK
	{result = ExpressionFactory.createInnerListExpression(lBrak, inner, rBrak);}
	;	

//snooze
dottedIdentifier returns [String idString = ""]
	:
	id = Identifier {idString += id.getText();}
	(
		dot = DOT {idString += dot.getText();}
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

//snooze	
dottedId2 returns [Token token = null ]
@init {CommonToken ct = null;}
	:
	id = Identifier {
		ct = new CommonToken(id);
		}
	(
		dot = (DOT | '-') {ct.setText(ct.getText() + dot.getText());}
		id = Identifier {ct.setStopIndex(getBounds(id)[1]);
		                 ct.setText(ct.getText() + id.getText());}
	)*
	{token = ct;
	 return token;}
	;

//seems OK
annotationType returns [Expression at = null]
	: 
	(
//{if(!isType($blockDeclaration::env, input.LT(1).getText())) {reporter.reportError(new FailedPredicateException(input,input.LT(1).getText(),"!isType($blockDeclaration::env, input.LT(1).getText())"));}} //m 
	//{isType($blockDeclaration::env, input.LT(1).getText())}? 
	atRef = annotationTypeVariableReference {at = atRef;}
	| basicAT = BasicAnnotationType {at = ExpressionFactory.createAnnotationTypeConstantReference(basicAT);}
	
	)
	;
	
annotationTypeVariableReference returns [Expression typeVar = null]
  :
  //{isVariableOfType(input.LT(1).getText(), "TYPE")}? //m
  atRef = dottedId 
  {typeVar = ExpressionFactory.createAnnotationTypeVariableReference(atRef);}
;

listExpression returns [Expression expr = null]
	:
	id = Identifier
	{expr = ExpressionFactory.createListVariableReference(id);}
	|
	path = RessourceLiteral
	{expr = ExpressionFactory.createRessourceReference(path);}
	;


tableExpression returns [Expression expr = null]
	:
	id = Identifier
	{expr = ExpressionFactory.createTableVariableReference(id);}
	|
	path = RessourceLiteral
	{expr = ExpressionFactory.createRessourceReference(path);}
	;

//seems OK
numberExpression returns [Expression expr = null]
	:
	e = additiveExpression
	{if(e!=null) expr = ExpressionFactory.createNumberExpression(e);}
	;

//seems OK
additiveExpression returns [Expression root = null]
    :   expr1=multiplicativeExpression {root=expr1;}
	( op=(PLUS | MINUS) expr2=multiplicativeExpression {root=ExpressionFactory.createBinaryArithmeticExpr(root,expr2,op);})*
	;

//NOT OK TODO
multiplicativeExpression returns [Expression root = null]
    :
	(expr1 = simpleNumberExpression {root=expr1;}
	( op=( STAR | SLASH | PERCENT ) sNE = simpleNumberExpression {root=ExpressionFactory.createBinaryArithmeticExpr(root,sNE,op);} )*
	|   e1 = numberFunction {root = e1;})
	;

//seems OK
numberExpressionInPar returns [Expression expr = null]
	:
	LPAREN numE = numberExpression RPAREN {((TextMarkerExpression)numE).setInParantheses(true); expr = numE;}
	;

//seems OK
simpleNumberExpression returns [Expression expr = null]
	:
	m = MINUS? numVarRef = numberVariable
	  {expr = numVarRef;}
	| (m = MINUS)? decLit = DecimalLiteral
	  {expr = ExpressionFactory.createDecimalLiteral(decLit,m);}
	| m = MINUS? fpLit = FloatingPointLiteral
	  {expr = ExpressionFactory.createFloatingPointLiteral(fpLit,m);}
	
	| numExprPar = numberExpressionInPar
	  {expr = numExprPar;}
	;

// not checked
numberFunction returns [Expression expr = null]
	:
	(op=(EXP | LOGN | SIN | COS | TAN) numExprP=numberExpressionInPar)
	{expr = ExpressionFactory.createUnaryArithmeticExpr(numExprP,op);}
	//| {root = ExpressionFactory.createNumberFunction(numExprP,op)}
	| (e = externalNumberFunction)=> e = externalNumberFunction {expr = e;}
	;

// not checked
externalNumberFunction returns [Expression expr = null]
	:
	{isVariableOfType(input.LT(1).getText(), "NUMBERFUNCTION")}? id = Identifier 
	LPAREN
	args = varArgumentList
	RPAREN
	{
		expr = external.createExternalNumberFunction(id, args);
	}
	;

//OK
numberVariable returns [Expression expr = null]
	:
	   ( {isVariableOfType(input.LT(1).getText(), "INT")}? numVarRef = Identifier //
	 | {isVariableOfType(input.LT(1).getText(), "DOUBLE")}? numVarRef = Identifier)
	 {	 expr = ExpressionFactory.createNumberVariableReference(numVarRef);}
	;
	catch [Exception e]{expr = ExpressionFactory.createNumberVariableReference(input.LT(1));}
	
//OK - interface to flag stringExpressions?
stringExpression returns [Expression expr = null]
@init {List<Expression> exprList = new ArrayList<Expression>();}
	:
	e = stringFunction {expr = e;} 
	|
	strExpr1 = simpleStringExpression {if (strExpr1!=null) exprList.add(strExpr1);}
	(PLUS (nextstrExpr = simpleStringExpression 
		| ne = numberExpressionInPar {if (ne!=null) exprList.add(ne);}
		| be = simpleBooleanExpression {if (be!=null) exprList.add(be);}
		| te = typeExpression {if (te!=null) exprList.add(te);}
		))*
	{expr = ExpressionFactory.createStringExpression(exprList);}
	;


// not checked
stringFunction returns [Expression expr = null]
	:
	(e = externalStringFunction)=> e = externalStringFunction {expr = e;}
	;

// not checked
externalStringFunction returns [Expression expr = null]
	:
	{isVariableOfType(input.LT(1).getText(), "STRINGFUNCTION")}? id = Identifier 
	LPAREN
	args = varArgumentList
	RPAREN
	{
		expr = external.createExternalStringFunction(id, args);
	}
	;

//OK - interface to flag stringExpressions?
simpleStringExpression returns [Expression expr = null]
	: 
	lit = StringLiteral {expr = ExpressionFactory.createSimpleString(lit);} 
	 | {isVariableOfType(input.LT(1).getText(), "STRING")}? variableId = Identifier {expr = ExpressionFactory.createStringVariableReference(variableId);} 
	; /*{isVariableOfType(input.LT(1).getText(), "STRING")}?*/

// commented thu, 19.02.09 => rewritten 'stringExpression'
//simpleStringExpressionOrNumberExpression returns [Expression expr = null]
//	: StringLiteral
//	 | {isVariableOfType(input.LT(1).getText(), "STRING")}? id = Identifier
//	| e = numberExpressionInPar {} 
//	;

//OK - interface to flag booleanExpressions?
booleanExpression returns [Expression expr = null]
	:
	bcE = composedBooleanExpression {expr = bcE;}
	| sbE = simpleBooleanExpression {expr = sbE;}
	;
	
simpleBooleanExpression returns [Expression expr = null]
	:
	 (lbE = literalBooleanExpression {expr = lbE;}
	| {isVariableOfType(input.LT(1).getText(), "BOOLEAN")}?(variableId = Identifier
	  {expr = ExpressionFactory.createBooleanVariableReference(variableId);})
	  )
	  {expr = ExpressionFactory.createBooleanExpression(expr);}
	;

// not checked
composedBooleanExpression returns [Expression expr = null]
	:
	(e2 = booleanCompare)=> e2 = booleanCompare {expr = e2;}
	| (bte = booleanTypeExpression)=> bte = booleanTypeExpression{expr = bte;}
	| (bne = booleanNumberExpression)=> bne = booleanNumberExpression{expr = bne;}
	| e1 = booleanFunction {expr = e1;}
	;

// not checked
booleanFunction returns [Expression expr = null]

	:
	(op = XOR LPAREN e1 = booleanExpression COMMA e2 = booleanExpression RPAREN)
	{expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}
	| (e = externalBooleanFunction)=> e = externalBooleanFunction {expr = e;}
	;
	
// not checked
externalBooleanFunction returns [Expression expr = null]
	:
	{isVariableOfType(input.LT(1).getText(), "BOOLEANFUNCTION")}? id = Identifier
	LPAREN
	args = varArgumentList	
	RPAREN
	{
		expr = external.createExternalBooleanFunction(id, args);
	}
	;

// not checked
booleanCompare returns [Expression expr = null]
	:
	(e1 = simpleBooleanExpression op = (EQUAL | NOTEQUAL) e2 = booleanExpression)
	{expr = ExpressionFactory.createBooleanFunction(op,e1,e2);}
	;

//OK
literalBooleanExpression returns  [BooleanLiteral expr = null]
	:
	(value = TRUE 
	| value = FALSE)
	{expr = ExpressionFactory.createSimpleBooleanExpression(value);}
	;

//not checked
booleanTypeExpression  returns  [Expression expr = null]
	:
	e1 = typeExpression
	op = (EQUAL | NOTEQUAL)
	e2 = typeExpression
	{expr = ExpressionFactory.createBooleanTypeExpression(e1,op,e2);}
	;

// TODO	requires numberExpression first!
//nearly OK
booleanNumberExpression  returns  [Expression expr = null]
	:
	LPAREN
	e1 = numberExpression
	op = (LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL)
	e2 = numberExpression
	RPAREN
	{expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);}
	;

genericVariableReference returns[Expression varRef]
:
  id=Identifier
  {return ExpressionFactory.createGenericVariableReference(id);}
;


// Lexer



TRIE
	:	'TRIE'
	;

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

CONTEXTCOUNT
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

INLIST
	:	'INLIST'
	;

ISINTAG
	:	'ISINTAG'
	;

LAST
	:	'LAST'
	;

MOFN
	:	'MOFN'
	;

NEAR
	:	'NEAR'
	;

OR
	:	'OR'
	;

PARTOF
	:	'PARTOF'
	;
	
PARTOFNEQ
	:	'PARTOFNEQ'
	;

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

IF
	:	'IF'
	;

FEATURE
	:	'FEATURE'
	;

PARSE
	:	'PARSE'
	;

CREATE
	:	'CREATE'
	;

GATHER
	:	'GATHER'
	;

FILL
	:	'FILL'
	;

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

MARK
	:	'MARK'
	;

MARKSCORE
	:	'MARKSCORE'
	;

MARKONCE
	:	'MARKONCE'
	;

MARKFAST
	:	'MARKFAST'
	;

MARKTABLE
	:	'MARKTABLE'
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

CALL
	:	'CALL'
	;


EXEC
	:	'EXEC'
	;

ASSIGN
	:	'ASSIGN'
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

STARTSWITH 	
	:	'STARTSWITH'	 
	;

ENDSWITH 	
	:	'ENDSWITH'	 
	;

NOT
	:	'NOT'
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
SymbolString	:	'SYMBOL';
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

CONTAINS:	'CONTAINS';

THEN 	:	 '->';

HexLiteral : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

DecimalLiteral : ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OctalLiteral : '0' ('0'..'7')+ IntegerTypeSuffix? ;

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



LPAREN	: '(' {implicitLineJoiningLevel++;} ;

RPAREN	: ')' {implicitLineJoiningLevel--;} ;

LBRACK	: '[' {implicitLineJoiningLevel++;} ;

RBRACK	: ']' {implicitLineJoiningLevel--;} ;

LCURLY	: '{' {implicitLineJoiningLevel++;} ;

RCURLY	: '}' {implicitLineJoiningLevel--;} ;

CIRCUMFLEX	: '^' ;


AT : '@' ;

DOT : '.' ;

COLON 	: ':' ;

COMMA	: ',' ;

SEMI	: ';' ;

PLUS	: '+' ;

MINUS	: '-' ;

STAR	: '*' ;

SLASH	: '/' ;

VBAR	: '|' ;

AMPER	: '&' ;

LESS	: '<' ;

GREATER	: '>' ;

ASSIGN_EQUAL	: '=' ;

PERCENT	: '%' ;

QUESTION	: '?' ;

EQUAL	: '==' ;

NOTEQUAL	: '!=' ;

ALT_NOTEQUAL: '<>' ;

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


    


