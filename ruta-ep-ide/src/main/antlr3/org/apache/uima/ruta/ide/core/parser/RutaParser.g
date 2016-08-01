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

package org.apache.uima.ruta.ide.core.parser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.DLTKToken;
import org.eclipse.dltk.ast.declarations.Declaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.BooleanLiteral;
import org.eclipse.dltk.ast.expressions.Expression;
import org.eclipse.dltk.ast.references.VariableReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.apache.commons.lang3.StringUtils;
import org.apache.uima.ruta.descriptor.RutaDescriptorInformation;

import org.apache.uima.ruta.ide.core.extensions.RutaExternalFactory;
import org.apache.uima.ruta.ide.parser.ast.ActionFactory;
import org.apache.uima.ruta.ide.parser.ast.ComponentDeclaration;
import org.apache.uima.ruta.ide.parser.ast.ComponentReference;
import org.apache.uima.ruta.ide.parser.ast.ComposedRuleElement;
import org.apache.uima.ruta.ide.parser.ast.ConditionFactory;
import org.apache.uima.ruta.ide.parser.ast.ExpressionFactory;
import org.apache.uima.ruta.ide.parser.ast.ScriptFactory;
import org.apache.uima.ruta.ide.parser.ast.StatementFactory;
import org.apache.uima.ruta.ide.parser.ast.RutaTypeConstants;
import org.apache.uima.ruta.ide.parser.ast.RutaBlock;
import org.apache.uima.ruta.ide.parser.ast.RutaExpression;
import org.apache.uima.ruta.ide.parser.ast.RutaRule;
import org.apache.uima.ruta.ide.parser.ast.RutaRuleElement;
import org.apache.uima.ruta.ide.parser.ast.RutaScriptBlock;
import org.apache.uima.ruta.ide.parser.ast.RutaAction;
import org.apache.uima.ruta.ide.parser.ast.RutaCondition;
import org.apache.uima.ruta.ide.parser.ast.RutaFeatureDeclaration;
import org.apache.uima.ruta.ide.parser.ast.RutaPackageDeclaration;

}

@parser::members {
	public DLTKRutaErrorReporter reporter;
	public ModuleDeclaration md;
	private List<String> vars = new ArrayList<String>();	
	private Map<String, String> varTypeMap = new HashMap<String, String>();
	private Map<String, String> lists = new HashMap<String, String>();
	private Map<String, String> tables = new HashMap<String, String>();
	private Collection<String> knownExternalBlocks = new ArrayList();
	public int length;
	public DLTKTokenConverter converter;
	public RutaDescriptorInformation descriptor;
	private int level = 0;
	private RutaExternalFactory external = new RutaExternalFactory();
	
	private String module;
	private String packageString;
	
	private ScriptFactory scriptFactory = new ScriptFactory();
	
	public void setKnownExternalBlocks(Collection<String> knownExternalBlocks) {
		this.knownExternalBlocks = knownExternalBlocks;
	}
	
	private boolean isBlockExtension(String name) {
		return knownExternalBlocks.contains(name);
	}
	
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
	
	public void addType(RutaBlock parent, String type, VariableReference parentTypeExpr) {
	
	String parentType = "uima.tcas.Annotation";
          if(parentTypeExpr != null) {
          	parentType = parentTypeExpr.getName();
	  }
		vars.add(type);
		String descriptionString = null;
       if(packageString == null) {
         descriptionString = "Type defined in " + module;
       } else {
         descriptionString = "Type defined in " + packageString + "." + module;
       }
		descriptor.addType(parent.getNamespace()+"."+type.trim(), descriptionString, parentType);
	}
	
	public void addPredefinedType(String type) {
		vars.add(type);
		varTypeMap.put(type, "TYPE");
		
	}
	
	public void addType(RutaBlock parent, String name, VariableReference parentTypeExpr, List featuresTypes,
          List<Token> featuresNames) {
          String parentType = "uima.tcas.Annotation";
          if(parentTypeExpr != null) {
          	parentType = parentTypeExpr.getName();
	  }
	   	 name = parent.getNamespace() + "." + name.trim();
	   	 String descriptionString = null;
	   	 if(packageString == null) {
	   	   descriptionString = "Type defined in " + module;
	   	 } else {
	   	   descriptionString = "Type defined in " + packageString + "." + module;
	   	 }
	   	 descriptor.addType(name, descriptionString, parentType);
	    	for (int i = 0; i < featuresTypes.size(); i++) {
	    	  Object object = featuresTypes.get(i);
	    	  String ftype = "";
	    	  if (object instanceof ASTNode) {
	    		    ftype = ((ASTNode) object).toString();
		      } else if (object instanceof Token) {
	     	   ftype = ((Token) object).getText();
	     	 }
		      String fname = featuresNames.get(i).getText();
	     	 descriptor.addFeature(name, fname, fname, ftype);
	   	 }
 	 }
	
	public void addWordList(RutaBlock parent, String name, String list) {
		lists.put(name, list);
	}
	
	public void addCSVTable(RutaBlock parent, String name, String table) {
		tables.put(name, table);
	}
	
	public boolean isType(RutaBlock parent, String type) {
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
		return vars.contains(var)|| isTemporaryVariable(var);
	}
	
	public boolean isVariableOfType(String var, String type) {
		return isTemporaryVariable(var, type) || (vars.contains(var) && type.equals(varTypeMap.get(var)));
	}
	
	public void checkVariable(String var, IntStream input) throws NoViableAltException {
		if(!vars.contains(var)) {
			throw new NoViableAltException("not declared \"" + var + "\"", 3, 0, input);
		}
	}
	
	  public void addImportTypeSystem(ComponentDeclaration ts) {
	    if(ts != null){
	     descriptor.addTypeSystem(ts.getName());
	    }
	  }
	
	  public void addImportScript(ComponentDeclaration module) {
		    descriptor.addScript(module.getName());
		  }
	
	  public void addImportEngine(ComponentDeclaration module) {
		    descriptor.addEngine(module.getName());
		  }
	   public void addImportUimafitEngine(ComponentDeclaration module) {
	    descriptor.addUimafitEngine(module.getName());
	  }
	
	protected static final int[] getBounds(Token t) {
		if (t instanceof CommonToken) {
			CommonToken ct = (CommonToken) t;
			int[] bounds = {ct.getStartIndex(), ct.getStopIndex()}; 
			return bounds;
		}
		return null;
	}
	
        private Map<String,String> temporaryVariables = new HashMap<>(); 
 	
 	private boolean isTemporaryVariable(String name, String type) {
 		return StringUtils.equals(temporaryVariables.get(name), type);
	}
	private boolean isTemporaryVariable(String name) {
 		return temporaryVariables.keySet().contains(name);
	}

	private void addTemporaryVariables(Map<Token, Token> def) {
		  Set<Entry<Token,Token>> entrySet = def.entrySet();
		  for (Entry<Token, Token> entry : entrySet) {
		    temporaryVariables.put(entry.getKey().getText(), entry.getValue().getText());
      }
	}
	private void removeTemporaryVariables(Map<Token, Token> def) {
		Set<Token> keySet = def.keySet();
		for (Token key : keySet) {
		    temporaryVariables.remove(key.getText());
     		}
	}
	
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
RutaScriptBlock rootBlock = null;
List<Statement> stmts = new ArrayList<Statement>();
}
	:	
	p = packageDeclaration?
	{
	String packageName = "";
	if(p != null) {packageName = p.getName();}
	if(descriptor != null) {
    descriptor.setPackageString(packageName);
  }
	rootBlock = scriptFactory.createScriptBlock(0,0,0,0,module, null, null, packageName);
	stmts.add(p);
	this.module = module;
	if(p != null) {
		this.packageString = p.getName();
	}
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
	

packageDeclaration returns [RutaPackageDeclaration pack]
	:	pString = PackageString 
	{
	pack = StatementFactory.createPkgDeclaration(p, pString);
	}
	p = dottedId 
	{
	pack = StatementFactory.createPkgDeclaration(p, pString);
	}
	SEMI
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
	| stmtCM = macroConditionDeclaration {stmts.add(stmtCM);}
	| stmtAM = macroActionDeclaration {stmts.add(stmtAM);}
	| stmt3 = blockDeclaration {stmts.add(stmt3);}
	| (externalBlock)=> stmt4 = externalBlock {stmts.add(stmt4);}
	| stmt2 = simpleStatement {stmts.add(stmt2);}
	

	)
	;

macroConditionDeclaration returns [Statement stmt = null]
@init {
Map<Token,Token> def = new LinkedHashMap<>();
}
    :
    kind = CONDITION name = Identifier 
    LPAREN  
    (VAR? argType = varTypeToken argName = Identifier  {def.put(argName,argType);}
    (COMMA VAR? argType = varTypeToken argName = Identifier {def.put(argName,argType);})*)? 
    {addTemporaryVariables(def);}
    RPAREN ASSIGN_EQUAL cs = conditions SEMI
    {removeTemporaryVariables(def);}
    {stmt = StatementFactory.createMacroStatement(kind, name, def, cs);}
    ;


macroActionDeclaration returns [Statement stmt = null]
@init {
Map<Token,Token> def = new LinkedHashMap<>();
}
    :
    kind = ACTION name = Identifier 
    LPAREN  
    (VAR? argType = varTypeToken argName = Identifier  {def.put(argName,argType);}
    (COMMA VAR? argType = varTypeToken argName = Identifier {def.put(argName,argType);})*)? 
    {addTemporaryVariables(def);}
    RPAREN ASSIGN_EQUAL as = actions SEMI
    {removeTemporaryVariables(def);}
    {stmt = StatementFactory.createMacroStatement(kind, name, def, as);}
    ;

varTypeToken returns [Token token = null ]
	:
	t = (ANNOTATION | ANNOTATIONLIST | StringString | STRINGLIST 
		| BooleanString | BOOLEANLIST | IntString | INTLIST 
		| DoubleString | DOUBLELIST | FloatString | FLOATLIST
		| TypeString | TYPELIST) {token = t;}
	;

importStatement returns [Statement stmt = null]
	:
	im = TypeSystemString 
	{stmt = StatementFactory.createImportTypeSystem(StatementFactory.createEmptyComponentDeclaration(im),im);} 
	name = dottedComponentDeclaration 
	{if(name != null) {stmt = StatementFactory.createImportTypeSystem(name,im);addImportTypeSystem(name);}}
	 SEMI 
	| im = ScriptString 
	{stmt = StatementFactory.createImportScript(StatementFactory.createEmptyComponentDeclaration(im),im);} 
	name = dottedComponentDeclaration 
	{if(name != null) {stmt = StatementFactory.createImportScript(name,im);addImportScript(name);}} 
	SEMI 
	| im = EngineString 
	{stmt = StatementFactory.createImportEngine(StatementFactory.createEmptyComponentDeclaration(im),im);} 
	name = dottedComponentDeclaration 
	{if(name != null) {stmt = StatementFactory.createImportEngine(name,im);addImportEngine(name);}}
	 SEMI 
	 | im = UimafitString 
	{stmt = StatementFactory.createImportEngine(StatementFactory.createEmptyComponentDeclaration(im),im);} 
	name = dottedComponentDeclaration 
	(LPAREN dottedIdentifier2 (COMMA dottedIdentifier2)+ RPAREN)? 
	{if(name != null) {stmt = StatementFactory.createImportEngine(name,im);addImportUimafitEngine(name);}}
	 SEMI 
	| im = ImportString type = dottedId (FromString ts = dottedComponentDeclaration)? (AsString alias = Identifier)? SEMI
	{stmt = StatementFactory.createImportType(im,type,ts,alias);addImportTypeSystem(ts);}
	| im = ImportString STAR FromString ts = dottedComponentDeclaration SEMI
	{stmt = StatementFactory.createImportTypeSystem(ts,im);addImportTypeSystem(ts);}
	| im = ImportString PackageString pkg = dottedId (FromString ts = dottedComponentDeclaration)? (AsString alias = Identifier)? SEMI
	{stmt = StatementFactory.createImportPackage(im,pkg,ts,alias);addImportTypeSystem(ts);}
	| im = ImportString PackageString STAR FromString ts = dottedComponentDeclaration (AsString alias = Identifier)? SEMI
	{stmt = StatementFactory.createImportAllPackagew(im,ts,alias);addImportTypeSystem(ts);}
	
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
	type = FloatString id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createFloatVariable(id, type));}
			(COMMA id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createFloatVariable(id, type));}
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
		 )* (ASSIGN_EQUAL init = typeExpression)?  SEMI
		{
		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
		 }
        |
        type = WORDLIST id = Identifier (ASSIGN_EQUAL list = wordListExpression)? SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createListVariable(id,type,list));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        }
        |
        type = WORDTABLE id = Identifier (ASSIGN_EQUAL table = wordTableExpression)?  SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createTableVariable(id,type,table));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, table));
        }
        |
        type = BOOLEANLIST id = Identifier (ASSIGN_EQUAL list = booleanListExpression)?  SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createVarListVariable(id,type,list, RutaTypeConstants.RUTA_TYPE_BL));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        }
        |
        type = INTLIST id = Identifier (ASSIGN_EQUAL list = numberListExpression)?  SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createVarListVariable(id,type,list, RutaTypeConstants.RUTA_TYPE_NL));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        }
         |
        type = DOUBLELIST id = Identifier (ASSIGN_EQUAL list = numberListExpression)?  SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createVarListVariable(id,type,list, RutaTypeConstants.RUTA_TYPE_NL));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        }
          |
        type = FLOATLIST id = Identifier (ASSIGN_EQUAL list = numberListExpression)?  SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createVarListVariable(id,type,list, RutaTypeConstants.RUTA_TYPE_NL));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        }
                |
        type = STRINGLIST id = Identifier (ASSIGN_EQUAL list = stringListExpression)?  SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createVarListVariable(id,type,list, RutaTypeConstants.RUTA_TYPE_SL));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        }
                |
        type = TYPELIST id = Identifier (ASSIGN_EQUAL list = typeListExpression)?  SEMI
        {
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createVarListVariable(id,type,list, RutaTypeConstants.RUTA_TYPE_TL));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        }       
        
        |
        type = ANNOTATION id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));}
			(COMMA id = Identifier {addVariable(id.getText(), type.getText());decls.add(StatementFactory.createTypeVariable(id,type));}
		 )* (ASSIGN_EQUAL init = annotationExpression)?  SEMI
		{
		 stmts.add(StatementFactory.createDeclarationsStatement(type, decls, init));
		 }
	|
	type = ANNOTATIONLIST id = Identifier (ASSIGN_EQUAL al = annotationExpression)? SEMI 
	{
        addVariable(id.getText(), type.getText());
        decls.add(StatementFactory.createVarListVariable(id,type,list, RutaTypeConstants.RUTA_TYPE_UAL));
        stmts.add(StatementFactory.createDeclarationsStatement(type, decls, list));
        } 
	
	
	
	
        
	//|
	//stmt = conditionDeclaration {stmts.add(stmt);}
	//|
	//stmt = actionDeclaration {stmts.add(stmt);}
	;

//conditionDeclaration returns [Statement stmt = null]
//    :
//    declareToken = CONDITION id = Identifier {addVariable(id.getText(), declareToken.getText());}
//    ASSIGN_EQUAL 
//    LPAREN cons = conditions RPAREN SEMI
//    {stmt = StatementFactory.createComposedVariableConditionDeclaration(id, cons);}
//    ;

//TODO added rule
//actionDeclaration returns [Statement stmt = null]
//    :
//    declareToken = ACTION id = Identifier {addVariable(id.getText(), declareToken.getText());}
//    ASSIGN_EQUAL
//    LPAREN a = actions RPAREN SEMI
//    {stmt = StatementFactory.createComposedVariableActionDeclaration(id, a);}
//    ;

declaration returns [List<Statement> stmts = new ArrayList<Statement>()]
@init {
	Statement stmt = null;
	List<Object> featureTypes = new ArrayList<Object>();
	List<Token> featureNames = new ArrayList<Token>();
	List<Declaration> declarations = new ArrayList<Declaration>();
}
	:
	declareToken=DECLARE lazyParent = annotationType?
		 id = Identifier {addVariable(id.getText(), declareToken.getText());}
			
	(
	{addType($blockDeclaration::env, id.getText(), lazyParent);
			declarations.add(StatementFactory.createAnnotationType(id,declareToken));}
		(
		
		COMMA 
		 id = Identifier {addVariable(id.getText(), declareToken.getText());}
			{addType($blockDeclaration::env, id.getText(),  lazyParent); 
			declarations.add(StatementFactory.createAnnotationType(id,declareToken));}
		 )* end = SEMI
		 {
		 stmt = StatementFactory.createDeclareDeclarationsStatement(declareToken, declarations, lazyParent);
		 stmts.add(stmt);
		 }
	|
		(LPAREN 
			(
			obj1 = annotationType{featureTypes.add(obj1);} 
			| obj2 = StringString{featureTypes.add(obj2);} 
			| obj3 = DoubleString{featureTypes.add(obj3);}
			| obj6 = FloatString{featureTypes.add(obj6);}  
			| obj4 = IntString{featureTypes.add(obj4);}
			| obj5 = BooleanString{featureTypes.add(obj5);}
			//| obj6 = fsType{featureTypes.add(obj6.getText());}
			) 
			fname = Identifier{featureNames.add(fname);} 
			(
			COMMA 
			(
			obj1 = annotationType{featureTypes.add(obj1);} 
			| obj2 = StringString{featureTypes.add(obj2);} 
			| obj3 = DoubleString{featureTypes.add(obj3);}
			| obj6 = FloatString{featureTypes.add(obj6);}  
			| obj4 = IntString{featureTypes.add(obj4);}
			| obj5 = BooleanString{featureTypes.add(obj5);}
			//| obj6 = fsType{featureTypes.add(obj5.getText());}
			)
			fname = Identifier{featureNames.add(fname);})* 
		RPAREN) SEMI // TODO removed question mark
		{
		List<RutaFeatureDeclaration> features = new ArrayList<RutaFeatureDeclaration>();
		int i = 0;
		for (Object eachTO : featureTypes) {
		   Token eachName = featureNames.get(i); 
		   features.add(StatementFactory.createFeatureDeclaration(eachTO, eachName));  
		   i++;
		}
		addType($blockDeclaration::env, id.getText(), lazyParent, featureTypes, featureNames);
		declarations.add( StatementFactory.createAnnotationType(id,declareToken, lazyParent, features));
		stmt = StatementFactory.createDeclareDeclarationsStatement(declareToken, declarations, lazyParent, features);
		stmts.add(stmt);
		}
	)
	;



	

blockDeclaration returns [RutaBlock block = null]
options{
	backtrack = true;
}
scope {
	RutaBlock env;
	}	
@init{
RutaRule rule = null;
level++;
}
@after {
level--;
}
	:

	(declareToken = (BlockString | ForEachString))
	LPAREN
	id = Identifier {addVariable(id.getText(), declareToken.getText());}
	{
		block = scriptFactory.createScriptBlock(id, declareToken, $blockDeclaration[level - 1]::env);
		$blockDeclaration::env = block;
	}
	(COMMA booleanExpression)?
	RPAREN
	re1 = ruleElementWithCA
	{
	rule = scriptFactory.createRule(re1);
	scriptFactory.finalizeBlock(block, rc, rule, body);}
	LCURLY body = statements rc = RCURLY
	{scriptFactory.finalizeBlock(block, rc, rule, body);}
	;

externalBlock returns [RutaBlock block = null]
options{
	backtrack = true;
}

@init{
RutaRule rule = null;

}
@after {

}
	:
	{isBlockExtension(input.LT(1).getText())}? 
	(type = Identifier)
	{block = scriptFactory.createExternalBlock(type, $blockDeclaration[level]::env);
		$blockDeclaration::env = block;}
	(LPAREN
	args = varArgumentList  {block.setArguments(args);}
	RPAREN)?
	re1 = ruleElementWithCA
	{
	rule = scriptFactory.createRule(re1);
	scriptFactory.finalizeBlock(block, rc, rule, body);}
	LCURLY body = statements rc = RCURLY
	{scriptFactory.finalizeBlock(block, rc, rule, body);}
	;
	
ruleElementWithCA returns [RutaRuleElement re = null] 
    :
    idRef=typeMatchExpression quantifier = quantifierPart? {re = scriptFactory.createRuleElement(idRef,quantifier,c,a, end);}
        LCURLY c = conditions? (THEN a = actions)? end = RCURLY
        {re = scriptFactory.createRuleElement(idRef,quantifier,c,a, end);}
    ;

	
ruleElementWithoutCA returns [RutaRuleElement re = null] 
    :
    idRef=typeMatchExpression quantifier = quantifierPart? 
             {re = scriptFactory.createRuleElement(idRef,quantifier,null,null, null);}

    ;
		
simpleStatement returns [RutaRule stmt = null]
options {
	backtrack = true;
}
	: 
	(regexpRule)=> rer = regexpRule {stmt = rer;}
	|
	as = rawActions s = SEMI 
	{stmt = scriptFactory.createImplicitRule(as, s);}
	|
	elements=ruleElementsRoot
	{stmt = scriptFactory.createRule(elements, null, false);}
		s = SEMI 
	{stmt = scriptFactory.createRule(elements, s);}
	
	
	;

regexpRule returns [RutaRule stmt = null]
@init{
	List<Expression> exprs = new ArrayList<Expression>();
	Map<Expression, Map<Expression, Expression>> fa = new HashMap<Expression, Map<Expression, Expression>>();
	Map<Expression, Expression> fmap = null;
}
	:
	regexp = stringExpression {exprs.add(regexp);} {stmt = scriptFactory.createRegExpRule(exprs, fa, s);} THEN
	(
	(numberExpression ASSIGN_EQUAL)=>
	indexCG = numberExpression {exprs.add(indexCG);}{stmt = scriptFactory.createRegExpRule(exprs, fa, s);} ASSIGN_EQUAL indexTE = typeExpression {exprs.add(indexTE);}
	(LPAREN {fmap = new HashMap<Expression, Expression>();} fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} 
	(COMMA fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} )* RPAREN {fa.put(indexTE, fmap);})?
	|
	te = typeExpression {exprs.add(te);} {stmt = scriptFactory.createRegExpRule(exprs, fa, s);}
	(LPAREN {fmap = new HashMap<Expression, Expression>();} fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} 
	(COMMA fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} )* RPAREN {fa.put(te, fmap);})?
	
	)
	
	(
	COMMA
	(
	(numberExpression ASSIGN_EQUAL)=>
	indexCG = numberExpression {exprs.add(indexCG);}{stmt = scriptFactory.createRegExpRule(exprs, fa, s);} ASSIGN_EQUAL indexTE = typeExpression {exprs.add(indexTE);}
	(LPAREN {fmap = new HashMap<Expression, Expression>();} fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} 
	(COMMA fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} )* RPAREN {fa.put(indexTE, fmap);})?
	|
	te = typeExpression {exprs.add(te);}{stmt = scriptFactory.createRegExpRule(exprs, fa, s);}
	(LPAREN {fmap = new HashMap<Expression, Expression>();} fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} 
	(COMMA fk = stringExpression ASSIGN_EQUAL arg = argument {fmap.put(fk, arg);} )* RPAREN {fa.put(te, fmap);})?
	)
	)*

	s = SEMI
	{stmt = scriptFactory.createRegExpRule(exprs, fa, s, true);}
	
	;



ruleElementsRoot returns [List<Expression> elements = new ArrayList<Expression>()]
	:
	re = ruleElement {if(re!=null) elements.add(re);} (p = PERCENT? re = ruleElement {if(re!=null) {elements.add(re);re.setAfterConcat(p);}})*
	;	

ruleElements returns [List<Expression> elements = new ArrayList<Expression>()]
	:
	re = ruleElement {if(re!=null) elements.add(re);} (re = ruleElement {if(re!=null) elements.add(re);})*
	;
	
blockRuleElement returns [RutaRuleElement rElement = null] //[List<RutaRuleElement> elements = new ArrayList<RutaRuleElement>()]
	:
	re = ruleElementType {rElement = re;}
	;	
	
ruleElement returns [RutaRuleElement re = null]
@init{
List<RutaRule> innerRules = new ArrayList<RutaRule>();
String label = null;
}
	:
	(l = Identifier {label = l.getText();} COLON)?
	STARTANCHOR?
	(
	re1 = ruleElementType {re = re1;}
	| re2 = ruleElementLiteral {re = re2;}
	| re3 = ruleElementComposed {re = re3;}
	| re4 = ruleElementWildCard {re = re4;}
	)
	{re.setLabel(label);}
	(t = THEN2 LCURLY (rule = simpleStatement {innerRules.add(rule);})+ 
	RCURLY {re.setInlinedRules(innerRules);re.setInlineMode(t == null ? null : t.getText());})?
	(t = THEN LCURLY (rule = simpleStatement {innerRules.add(rule);})+ 
	RCURLY {re.setInlinedRules(innerRules);re.setInlineMode(t == null ? null : t.getText());})?
	;

ruleElementWildCard returns [RutaRuleElement re = null] 
@init{
List<RutaCondition> dummyConds = new ArrayList<RutaCondition>();
}
  :
    	w = WILDCARD
        (LCURLY 
        {
        
        dummyConds.add(ConditionFactory.createEmptyCondition(input.LT(1)));
        } 
        c = conditions? 
        {
        if(c==null) {
        	c = dummyConds;
        }
        }
        (THEN a = actions)? end = RCURLY)?
        {

        re = scriptFactory.createRuleElement(w,c,a,end);}

    ;

	
ruleElementComposed returns [ComposedRuleElement re = null] 
@init{
	Boolean disjunctive = null;
	List<Expression> res = new ArrayList<Expression>();
}
	:
	ft = LPAREN
	(
	(ruleElement VBAR)=> re1 = ruleElement {res.add(re1);} (VBAR re1 = ruleElement {disjunctive = true; res.add(re1);})+
	|
	(ruleElement AMPER)=> re2 = ruleElement {res.add(re2);} (AMPER re2 = ruleElement {disjunctive = false; res.add(re2);})+
	|
	res2 = ruleElements {res = res2;}
	)
	lt1 = RPAREN q = quantifierPart? (LCURLY c = conditions? (THEN a = actions)? lt2 = RCURLY)?
	{re = scriptFactory.createComposedRuleElement(res, q, c, a, disjunctive, $blockDeclaration::env, ft, lt1, lt2);}
	;

ruleElementType returns [RutaRuleElement re = null] 
@init{
List<RutaCondition> dummyConds = new ArrayList<RutaCondition>();
}
  :
    (typeMatchExpression)=>idRef=typeMatchExpression quantifier = quantifierPart? 
        (LCURLY 
        {
        
        dummyConds.add(ConditionFactory.createEmptyCondition(input.LT(1)));
        } 
        c = conditions? 
        {
        if(c==null) {
        	c = dummyConds;
        }
        }
        (THEN a = actions)? end = RCURLY)?
        {
        // TODO handle quantifierPart.
        re = scriptFactory.createRuleElement(idRef,quantifier,c,a,end);}
        //start, end, "", cp, ap, "", $blockDeclaration::env);}
    ;

ruleElementLiteral returns [RutaRuleElement re = null] 
    :
    
    (simpleStringExpression)=>idRef=simpleStringExpression quantifier = quantifierPart? 
        (LCURLY 

        c = conditions? 
        (THEN a = actions)? 
        end = RCURLY)?
        {
        // TODO handle quantifierPart.
        re = scriptFactory.createRuleElement(idRef,quantifier,c,a,end);}
        //start, end, "", cp, ap, "", $blockDeclaration::env);}
    ;
    
conditions returns [List<RutaCondition> conds = new ArrayList<RutaCondition>()]
@init {
conds.add(ConditionFactory.createEmptyCondition(input.LT(1)));
}
    :
    c = condition {conds.remove(0);conds.add(c);} (COMMA c = condition {conds.add(c);} )*
    ;

  
actions returns [List<RutaAction> actions = new ArrayList<RutaAction>()]
    :
    a = action {actions.add(a);} (COMMA a = action {actions.add(a);} )*
    ; 	
rawActions returns [List<RutaAction> actions = new ArrayList<RutaAction>()]
    :
    a = rawAction {actions.add(a);} (COMMA a = rawAction {actions.add(a);} )*
    ; 	
	
listExpression returns [Expression expr = null]
	:
	(booleanListExpression)=> e = booleanListExpression {expr = e;}
	| (intListExpression)=> e = intListExpression {expr = e;}
	| (doubleListExpression)=> e = doubleListExpression {expr = e;}
	| (floatListExpression)=> e = floatListExpression {expr = e;}
	| (stringListExpression)=> e = stringListExpression {expr = e;}
	| (typeListExpression)=> e = typeListExpression {expr = e;}
	| (annotationListExpression)=> e = annotationListExpression {expr = e;}
	| (untypedListExpression)=> utl = untypedListExpression {expr = utl;}
	| (featureExpression)=>fe = featureExpression {expr = fe;}
	
	;

untypedListExpression returns [Expression expr = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
}	:
	LCURLY (e = argument {list.add(e);} (COMMA e = argument {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createListExpression(list, RutaTypeConstants.RUTA_TYPE_UTL);}
	;
	
booleanListExpression returns [Expression expr = null]

	:
	e = simpleBooleanListExpression {expr = e;}
	;

simpleBooleanListExpression returns [Expression expr = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
}	:
	LCURLY (e = simpleBooleanExpression {list.add(e);} (COMMA e = simpleBooleanExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createListExpression(list, RutaTypeConstants.RUTA_TYPE_BL);}
	|
	{isVariableOfType(input.LT(1).getText(), "BOOLEANLIST")}? 
	var = Identifier 
	{expr = ExpressionFactory.createListExpression(var, RutaTypeConstants.RUTA_TYPE_BL);}
	;


intListExpression returns [Expression expr = null]
	:
	e = simpleIntListExpression {expr = e;}
	;

simpleIntListExpression returns [Expression expr = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createListExpression(list, RutaTypeConstants.RUTA_TYPE_NL);}
	|
	{isVariableOfType(input.LT(1).getText(), "INTLIST")}? 
	var = Identifier 
	{expr = ExpressionFactory.createListExpression(var, RutaTypeConstants.RUTA_TYPE_NL);}
	;


numberListExpression returns [Expression expr = null]
	:
	(e1 = doubleListExpression)=> e1 = doubleListExpression {expr = e1;}
	|
	(e1 = floatListExpression)=> e1 = floatListExpression {expr = e1;}
	|
	e2 = intListExpression {expr = e2;}
	;
	
doubleListExpression returns [Expression expr = null]
	:
	e = simpleDoubleListExpression {expr = e;}
	;

simpleDoubleListExpression returns [Expression expr = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createListExpression(list, RutaTypeConstants.RUTA_TYPE_NL);}
	|
	{isVariableOfType(input.LT(1).getText(), "DOUBLELIST")}? 
	var = Identifier 
	{expr = ExpressionFactory.createListExpression(var, RutaTypeConstants.RUTA_TYPE_NL);}
	;

floatListExpression returns [Expression expr = null]
	:
	e = simpleFloatListExpression {expr = e;}
	;

simpleFloatListExpression returns [Expression expr = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
}	:
	LCURLY (e = simpleNumberExpression {list.add(e);} (COMMA e = simpleNumberExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createListExpression(list, RutaTypeConstants.RUTA_TYPE_NL);}
	|
	{isVariableOfType(input.LT(1).getText(), "FLOATLIST")}? 
	var = Identifier 
	{expr = ExpressionFactory.createListExpression(var, RutaTypeConstants.RUTA_TYPE_NL);}
	;


stringListExpression returns [Expression expr = null]
	:
	e = simpleStringListExpression {expr = e;}
	;

simpleStringListExpression returns [Expression expr = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
}	:
	LCURLY (e = simpleStringExpression {list.add(e);} (COMMA e = simpleStringExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createListExpression(list, RutaTypeConstants.RUTA_TYPE_SL);}
	|
	{isVariableOfType(input.LT(1).getText(), "STRINGLIST")}? 
	var = Identifier 
	{expr = ExpressionFactory.createListExpression(var, RutaTypeConstants.RUTA_TYPE_SL);}
	;


typeListExpression returns [Expression expr = null]
	:
	e = simpleTypeListExpression {expr = e;}
	;

simpleTypeListExpression returns [Expression expr = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
}	:
	LCURLY (e = simpleTypeExpression {list.add(e);} (COMMA e = simpleTypeExpression {list.add(e);})*)?  RCURLY
	{expr = ExpressionFactory.createListExpression(list, RutaTypeConstants.RUTA_TYPE_TL);}
	|
	{isVariableOfType(input.LT(1).getText(), "TYPELIST")}? 
	var = Identifier 
	{expr = ExpressionFactory.createListExpression(var, RutaTypeConstants.RUTA_TYPE_TL);}
	;	
	
	
typeMatchExpression returns [Expression expr = null]
options {
	backtrack = true;
}
@init {
expr = ExpressionFactory.createEmptyTypeExpression(input.LT(1));
}
	:
	(
	(featureTypeExpression)=> ft = featureTypeExpression {expr = ft;}
	| 
	(typeFunction)=> tf = typeFunction {expr = tf;}
	| 
	st = simpleTypeExpression 
	{expr = ExpressionFactory.createTypeExpression(st);
	 }
	)
	
	
	;	
	
typeExpression returns [Expression expr = null]
@init {
expr = ExpressionFactory.createEmptyTypeExpression(input.LT(1));
}
	:
	(typeFunction)=> tf = typeFunction {expr = tf;}
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
	id = Identifier 
	LPAREN
	args = varArgumentList?
	RPAREN
	{
		expr = external.createExternalTypeFunction(id, args);
	}
	;

featureAssignmentExpression returns [Expression expr = null]
	:
	feature = dottedIdWithIndex comp = ASSIGN_EQUAL value = argument {expr = ExpressionFactory.createFeatureMatch(feature, comp, value);}
	;

featureTypeExpression returns [Expression expr = null]
	:
	feature = dottedIdWithIndex (comp = LESS | comp = GREATER | comp = GREATEREQUAL | comp = LESSEQUAL |comp =  EQUAL | comp = NOTEQUAL) value = argument {expr = ExpressionFactory.createFeatureMatch(feature, comp, value);}
	;

featureExpression returns [Expression expr = null]
	:
	f = dottedIdWithIndex  {expr = ExpressionFactory.createFeatureExpression(f);}
	;

simpleTypeExpression returns [Expression type = null]
	:
	at = annotationType {type = at;}
	;

variable returns [Expression var = null]
	:
	{vars.contains(input.LT(1).getText())}? v = Identifier {var=ExpressionFactory.createGenericVariableReference(v);}
	;	
	

variableAssignmentAction returns [RutaAction a = null]
	:
	var = variable op = ASSIGN_EQUAL arg = argument
	{
	a = ActionFactory.createAction(var, arg);
	}
	;

listVariable returns [Expression var = null]
	:
	{isVariableOfType(input.LT(1).getText(), "BOOLEANLIST")
	||isVariableOfType(input.LT(1).getText(), "INTLIST")
	||isVariableOfType(input.LT(1).getText(), "FLOATLIST")
	||isVariableOfType(input.LT(1).getText(), "DOUBLELIST")
	||isVariableOfType(input.LT(1).getText(), "STRINGLIST")
	||isVariableOfType(input.LT(1).getText(), "TYPELIST")
	||isVariableOfType(input.LT(1).getText(), "ANNOTATIONLIST")
	}? v = Identifier {var=ExpressionFactory.createGenericVariableReference(v);}
	;
	

	
quantifierPart returns [List<Expression> exprs = new ArrayList<Expression>()]
options {
	backtrack = true;
}
	:
	 s = STAR q = QUESTION? {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(s,q));}
	| p = PLUS q = QUESTION? {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(p,q));}
	| q1 = QUESTION q = QUESTION? {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(q1,q));}
	
	| (b1 = LBRACK min = numberExpression COMMA max = numberExpression b2 = RBRACK q = QUESTION?
	 {if(min!=null) {exprs.add(min);} if(max!=null) {exprs.add(max);}
	  if(q!=null) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(q, null));}}
	   )
	| b1 = LBRACK  COMMA max = numberExpression b2 = RBRACK q = QUESTION?
	 {if(min!=null) {exprs.add(min);} if(max!=null) {exprs.add(max);}
	  if(q!=null) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(q, null));}}
	   
	| b1 = LBRACK min = numberExpression COMMA b2 = RBRACK q = QUESTION?
	 {if(min!=null) {exprs.add(min);} if(max!=null) {exprs.add(max);}
	  if(q!=null) {exprs.add(ExpressionFactory.createQuantifierLiteralExpression(q, null));}}
	   
	;
	
	
condition returns [RutaCondition result = null]
@init {
result = ConditionFactory.createEmptyCondition(input.LT(1));
}
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
	
	| (booleanExpression)=> b = booleanExpression {c = ConditionFactory.createCondition(b);}
	| (externalCondition)=> c = externalCondition
	) {result = c;}
	;
	
//TODO added rule
variableCondition returns [RutaCondition condition = null]
	:		
	// also create condition for auto-completion
	//{isVariableOfType(input.LT(1).getText(), "CONDITION")}? 
	id = Identifier
	{
		condition = ConditionFactory.createCondition(id);
	}
	;	
	
	
externalCondition returns [RutaCondition condition = null]
	:
	id = Identifier
	{condition = external.createExternalCondition(id, args);}
	LPAREN
	args = varArgumentList?
	RPAREN
	{condition = external.createExternalCondition(id, args);}
	;
conditionAnd returns [RutaCondition cond = null]
    :   
    name = AND LPAREN conds = conditions  
    {cond = ConditionFactory.createCondition(name, conds);}
    RPAREN
    ;

conditionContains returns [RutaCondition cond = null]
@init {
List<Expression> args = new ArrayList<>();
}
    :   
    name = CONTAINS LPAREN 
    a = argument {args.add(a);} 
    (COMMA a = argument{args.add(a);})*
    RPAREN
    
    //(type = typeExpression | list = plainListExpression COMMA a = argument) 
    //(COMMA min = numberExpression COMMA max = numberExpression (COMMA percent = booleanExpression)?)? RPAREN
    {
    cond = ConditionFactory.createCondition(name, args);
    //if(type != null) {cond = ConditionFactory.createConditionContains(type, min, max, percent,$blockDeclaration::env);}
    //else if(list != null) {cond = ConditionFactory.createConditionContains(list, a, min, max, percent, $blockDeclaration::env);}
    }
    ;
    
conditionContextCount returns [RutaCondition cond = null]
    :   
    name = CONTEXTCOUNT LPAREN typeExpr = typeExpression 
    {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}
    (COMMA min = numberExpression COMMA max = numberExpression)?
    {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}
    (COMMA var = numberVariable)? 
    {cond = ConditionFactory.createCondition(name, typeExpr, min, max, var);}
    RPAREN
    ;
    
conditionCount returns [RutaCondition cond = null]
 options {
	backtrack = true;
}
     :   
     name = COUNT LPAREN type = listExpression 
     {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}
     COMMA a = argument 
     {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}
     (COMMA min = numberExpression COMMA max = numberExpression)? 
     {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}
     (COMMA var = numberVariable)? 
     {cond = ConditionFactory.createCondition(name, type, a, min, max, var);}
    RPAREN
    |
    name = COUNT LPAREN type = typeExpression 
    {cond = ConditionFactory.createCondition(name, type, min, max, var);}
    (COMMA min = numberExpression COMMA max = numberExpression)?
    {cond = ConditionFactory.createCondition(name, type, min, max, var);}
    (COMMA var = numberVariable)? 
    {cond = ConditionFactory.createCondition(name, type, min, max, var);}  
    RPAREN 
    ;   
    
conditionCurrentCount returns [RutaCondition cond = null]
    :   
    name = CURRENTCOUNT LPAREN type = typeExpression 
    {cond = ConditionFactory.createCondition(name,type, min, max, var);}
    (COMMA min = numberExpression COMMA max = numberExpression)? 
    {cond = ConditionFactory.createCondition(name,type, min, max, var);}
    (COMMA var = numberVariable)? 
    {cond = ConditionFactory.createCondition(name,type, min, max, var);}
    RPAREN
    ;   
    
conditionTotalCount returns [RutaCondition cond = null]
    :   
    name = TOTALCOUNT LPAREN type = typeExpression 
    {cond = ConditionFactory.createCondition(name,type, min, max, var);}
    (COMMA min = numberExpression COMMA max = numberExpression)? 
   {cond = ConditionFactory.createCondition(name,type, min, max, var);}
    (COMMA var = numberVariable)? 
    {cond = ConditionFactory.createCondition(name, type, min, max, var);}
    RPAREN
    ;
conditionInList returns [RutaCondition cond = null]
 options {
	backtrack = true;
}
    :
    name = INLIST LPAREN ((list2 = stringListExpression)=>list2 = stringListExpression | list1 = wordListExpression) 
    (COMMA arg = stringExpression)?
    //(COMMA dist = numberExpression (COMMA rel = booleanExpression)?)? 
     {if(list1 != null) {cond = ConditionFactory.createCondition(name, list1, arg);}
    else {cond = ConditionFactory.createCondition(name, list2, arg);};}
    RPAREN
    ;
            
conditionLast returns [RutaCondition cond = null]
    :   
    name = LAST LPAREN type = typeExpression 
    {cond = ConditionFactory.createCondition(name, type);}
    RPAREN
    ;
    
conditionMofN returns [RutaCondition cond = null]
    :   
    name = MOFN LPAREN min = numberExpression COMMA max = numberExpression COMMA conds = conditions 
    {List exprs = new ArrayList();
    exprs.add(min);
    exprs.add(max);
    exprs.addAll(conds);
    cond = ConditionFactory.createCondition(name, exprs);}  
    RPAREN
    ;

conditionNear returns [RutaCondition cond = null]
    :   
    name = NEAR LPAREN type = typeExpression COMMA min = numberExpression COMMA max = numberExpression 
    (COMMA direction = booleanExpression (COMMA filtered = booleanExpression)?)? 
    {cond = ConditionFactory.createCondition(name, type, min, max, direction, filtered);}   
    RPAREN
    ;
conditionNot returns [RutaCondition cond = null]
    :   
    ((name = MINUS c = condition) |  (name = NOT LPAREN c = condition RPAREN))
    {cond = ConditionFactory.createCondition(name, c);} 
    ;
conditionOr returns [RutaCondition cond = null]
    :   
    name = OR LPAREN conds = conditions 
    {cond = ConditionFactory.createCondition(name, conds);}
    RPAREN
    ;
conditionPartOf returns [RutaCondition cond = null]
    :
    name = PARTOF LPAREN (type = typeExpression|type = typeListExpression)    
    {cond = ConditionFactory.createCondition(name, type);}
    RPAREN
    ;
    
conditionPartOfNeq returns [RutaCondition cond = null]
    :
    name = PARTOFNEQ LPAREN (type = typeExpression|type = typeListExpression)    
    {cond = ConditionFactory.createCondition(name, type);}
    RPAREN
    ;    


conditionPosition returns [RutaCondition cond = null]
    :   
    name = POSITION LPAREN type = typeExpression 
    {cond = ConditionFactory.createCondition(name, type, pos, rel);}
    COMMA pos = numberExpression 
    {cond = ConditionFactory.createCondition(name, type, pos, rel);}
    (COMMA rel = booleanExpression)? 
    {cond = ConditionFactory.createCondition(name, type, pos, rel);}
    RPAREN
    ;
conditionRegExp returns [RutaCondition cond = null]
    :
    name = REGEXP LPAREN 
    ((stringExpression COMMA stringExpression)=> v = stringExpression COMMA pattern = stringExpression
    | pattern = stringExpression
    ) (COMMA caseSensitive = booleanExpression)? 
    {cond = ConditionFactory.createCondition(name, v, pattern, caseSensitive);}   
    RPAREN 
    ;
    
conditionScore returns [RutaCondition cond = null]
    :
    name = SCORE LPAREN min = numberExpression (COMMA max = numberExpression  
    (COMMA var = numberVariable)?)? 
    {cond = ConditionFactory.createCondition(name, min, max, var);}
    RPAREN
    ;

conditionVote returns [RutaCondition cond = null]
    :   
    name = VOTE LPAREN type1 = typeExpression COMMA type2 = typeExpression 
    {cond = ConditionFactory.createCondition(name, type1, type2);}
    RPAREN
    ;   
conditionIf returns [RutaCondition cond = null]
    :   
    name = IF LPAREN e = booleanExpression 
    {cond = ConditionFactory.createCondition(name, e);}
    RPAREN
    ;   
    
conditionFeature returns [RutaCondition cond = null]
    :   
    name = FEATURE LPAREN se = stringExpression COMMA v = argument 
    {cond = ConditionFactory.createCondition(name, se, v);}
    RPAREN
    ;   
conditionParse returns [RutaCondition cond = null]
    :
    name = PARSE LPAREN
     var=genericVariableReference (COMMA locale = stringExpression)?
    {cond = ConditionFactory.createCondition(name, var, locale);}
    RPAREN
    ;


conditionIs returns [RutaCondition cond = null]
    :
    name = IS LPAREN (type = typeExpression|type = typeListExpression) 
    {cond = ConditionFactory.createCondition(name, type);}
    RPAREN
    ;

conditionBefore returns [RutaCondition cond = null]
    :
    name = BEFORE LPAREN (type = typeExpression|type = typeListExpression) 
    {cond = ConditionFactory.createCondition(name, type);}
    RPAREN
    ;

conditionAfter returns [RutaCondition cond = null]
    :
    name = AFTER LPAREN (type = typeExpression|type = typeListExpression) 
    {cond = ConditionFactory.createCondition(name, type);}
    RPAREN
    ;
    
conditionStartsWith returns [RutaCondition cond = null]
    :
    name = STARTSWITH LPAREN (type = typeExpression|type = typeListExpression) 
    {cond = ConditionFactory.createCondition(name, type);}
    RPAREN
    ;
    
conditionEndsWith returns [RutaCondition cond = null]
    :
    name = ENDSWITH LPAREN (type = typeExpression|type = typeListExpression) 
    {cond = ConditionFactory.createCondition(name, type);}
    RPAREN
    ;
    
conditionSize returns [RutaCondition cond = null]
    :
    name = SIZE LPAREN list = listExpression (COMMA min = numberExpression COMMA max = numberExpression)? (COMMA var = numberVariable)? 
    {cond = ConditionFactory.createCondition(name, list, min, max, var);}
    RPAREN
    ;

rawAction returns [RutaAction result = null]
@init {
result = ActionFactory.createEmptyAction(input.LT(1));
}
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
	| a = actionRetainType
	| a = actionFilterType
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
	| a = actionAddFilterType
	| a = actionAddRetainType
	| a = actionRemoveFilterType
	| a = actionRemoveRetainType
	| (externalAction)=> a = externalAction
	
	// | a = variableAction
	) {result = a;}
	;
	
action returns [RutaAction result = null]
@init {
result = ActionFactory.createEmptyAction(input.LT(1));
}
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
	| a = actionRetainType
	| a = actionFilterType
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
	| a = actionAddFilterType
	| a = actionAddRetainType
	| a = actionRemoveFilterType
	| a = actionRemoveRetainType
	| (variableAssignmentAction)=> vae = variableAssignmentAction {a = vae;}
	| (externalAction)=> a = externalAction
	| (featureAssignmentExpression)=> fae = featureAssignmentExpression {a = ActionFactory.createAction(fae);}
	| (typeExpression)=> te = typeExpression {a = ActionFactory.createAction(te);}
	
	// | a = variableAction
	) {result = a;}
	;




variableAction returns [RutaAction action = null]
	:
	// also create an dummy action for auto-completion
	//{isVariableOfType(input.LT(1).getText(), "ACTION")}?
	 id = Identifier
	{
		action = ActionFactory.createAction(id);
	}
	;
	
externalAction returns [RutaAction action = null]
	:
	 id = Identifier
	 LPAREN
	 args = varArgumentList? 
	 RPAREN
	{
		action = external.createExternalAction(id, args);
	}
	;


actionCreate returns [RutaAction action = null]
@init {
    List left = new ArrayList();
    List right = new ArrayList();
    List indexes = new ArrayList();
}
    :
    name = CREATE LPAREN structure = typeExpression
    (COMMA 
        
    ((numberExpression)=> index = numberExpression {indexes.add(index);} ((COMMA index = numberExpression)=> (COMMA index = numberExpression){indexes.add(index);})* COMMA)?
    
    ((stringExpression ASSIGN_EQUAL)=> fname = stringExpression ASSIGN_EQUAL obj1 = argument {left.add(fname); right.add(obj1);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = argument {left.add(fname);right.add(obj1);})*)?
    
    )?
    {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}
     RPAREN
    ;


actionMarkTable returns [RutaAction action = null]
@init {
    List<Expression> left = new ArrayList<Expression>();
    List<Expression> right = new ArrayList<Expression>();
}
    :
    name = MARKTABLE LPAREN 
    structure = typeExpression COMMA 
    index = numberExpression COMMA
    table = wordTableExpression 
    COMMA
    ( (booleanExpression)=> ignoreCase = booleanExpression 
    COMMA ignoreLength = numberExpression 
    COMMA ignoreChar = stringExpression
    COMMA maxIgnoreChar = numberExpression COMMA)?
     key = stringExpression {left.add(key);} ASSIGN_EQUAL value = numberExpression{right.add(value);}
    (COMMA key = stringExpression {left.add(key);} ASSIGN_EQUAL value = numberExpression{right.add(value);} )*
    
    
    {
    List<Expression> args = new ArrayList<Expression>();
    args.add(index);
    args.add(table);
    	args.add(ignoreCase);
	args.add(ignoreLength);
	args.add(ignoreChar);
    	args.add(maxIgnoreChar);
    action = ActionFactory.createStructureAction(name, args, left, right, structure);}
    RPAREN
    ;

actionGather returns [RutaAction action = null]
@init {
    List left = new ArrayList();
    List right = new ArrayList();
    List indexes = new ArrayList();
}
    :
    name = GATHER LPAREN structure = typeExpression
     {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}
    (COMMA 
    ((numberExpression)=> index = numberExpression {indexes.add(index);} ((COMMA index = numberExpression)=>(COMMA index = numberExpression) {indexes.add(index);})* COMMA)?
    (fname = stringExpression ASSIGN_EQUAL (obj1 = numberExpression | obj2 = numberListExpression) {left.add(fname); right.add(obj1 != null? obj1 : obj2);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL (obj1 = numberExpression | obj2 = numberListExpression) {left.add(fname);right.add(obj1 != null? obj1 : obj2);})*)?
    
    )? 
    {action = ActionFactory.createStructureAction(name, structure, indexes, left, right);}
    RPAREN
    ;


actionFill returns [RutaAction action = null]
@init {
    List left = new ArrayList();
    List right = new ArrayList();
}
    :
    name = FILL LPAREN structure = typeExpression
    {action = ActionFactory.createStructureAction(name, structure, null, left, right);}
    (
    COMMA fname = stringExpression ASSIGN_EQUAL
    obj1 = argument {left.add(fname); right.add(obj1);}
    )+
    {action = ActionFactory.createStructureAction(name, structure, null, left, right);}
     RPAREN
    ;
    

actionColor returns [RutaAction action = null]
    :   
    name = COLOR LPAREN type = typeExpression 
    {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}
     COMMA 
    bgcolor = stringExpression 
    {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}
    (COMMA
    fgcolor = stringExpression
    {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);} 
    (COMMA
    selected = booleanExpression)?)?
    {action = ActionFactory.createAction(name, type, bgcolor, fgcolor, selected);}
    RPAREN
    ;

actionDel returns [RutaAction action = null]
    :   
    name = DEL
    {action = ActionFactory.createAction(name, new ArrayList());}
    ;
        
actionLog returns [RutaAction action = null]
    :   
    name = LOG LPAREN lit = stringExpression (COMMA log = LogLevel)? 
    {action = ActionFactory.createLogAction(name, lit, log);} //TODO handle logLevel
    RPAREN 
    ;

actionMark returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = MARK LPAREN 
    type = typeExpression
    {list.add(type);}
    (
    COMMA (index = numberExpression) => index = numberExpression {list.add(index);}
    )*
    {action = ActionFactory.createAction(name, list);}
     RPAREN
    ;

actionShift returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = SHIFT LPAREN 
    type = typeExpression
    {list.add(type);}
    
    COMMA index1 = numberExpression {list.add(index1);}
    COMMA index2 = numberExpression {list.add(index2);}
    (COMMA  all= booleanExpression {list.add(all);})? 
    {action = ActionFactory.createAction(name, list);}
     RPAREN
    ;

actionSplit returns [RutaAction action = null]
@init {
	List<Expression> list = new ArrayList<Expression>();
}
	:
	name = SPLIT 
	LPAREN
	type = typeExpression
	{list.add(type);}
	(
	COMMA
	complete = booleanExpression
	{list.add(complete);}
	(
	COMMA
	appendToBegin = booleanExpression
	{list.add(appendToBegin);}
	COMMA
	appendToEnd = booleanExpression
	{list.add(appendToEnd);}
	)?
	)? 
	RPAREN
	{action = ActionFactory.createAction(name, list);}
	;

actionMarkScore returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = MARKSCORE LPAREN score = numberExpression COMMA type = typeExpression
    {list.add(score); list.add(type);}
    (
    COMMA (index = numberExpression) => index = numberExpression {list.add(index);}
    )*
    {action = ActionFactory.createAction(name, list);}
     RPAREN
    ;

actionMarkOnce returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = MARKONCE LPAREN ((score = numberExpression) => score = numberExpression COMMA)? (type = typeExpression) => type = typeExpression
    {list.add(score); list.add(type);}
    (
    COMMA (index = numberExpression) => index = numberExpression {list.add(index);}
    )* 
    {action = ActionFactory.createAction(name, list);}
    RPAREN
    ;

actionMarkFast returns [RutaAction action = null]
@init {
 Expression list = null;
}
    :   
    name = MARKFAST LPAREN type = typeExpression 
    {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}
    COMMA (list1 = wordListExpression | list2 = stringListExpression) {list = list1 == null ? list2 : list1;}
    {action = ActionFactory.createAction(name, type, list, ignore, numExpr);}
    (COMMA ignore = booleanExpression (COMMA numExpr = numberExpression (COMMA ignoreWS = booleanExpression)?)?)? 
    {action = ActionFactory.createAction(name, type, list, ignore, numExpr, ignoreWS);} // TODO handle list
    RPAREN
    ;

actionMarkLast returns [RutaAction action = null]
    :   
    name = MARKLAST LPAREN type = typeExpression 
    {action = ActionFactory.createAction(name, type);}
    RPAREN
    ;

actionMarkFirst returns [RutaAction action = null]
    :   
    name = MARKFIRST LPAREN type = typeExpression 
    {action = ActionFactory.createAction(name, type);}
    RPAREN
    ;


actionReplace returns [RutaAction action = null]
    :   
    name = REPLACE LPAREN lit = stringExpression 
    {action = ActionFactory.createAction(name, lit);}
    RPAREN
    ;

        
actionRetainType returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = RETAINTYPE (LPAREN id = typeExpression {list.add(id);} 
    {action = ActionFactory.createAction(name, list);}
    (COMMA id = typeExpression {list.add(id);})*
    {action = ActionFactory.createAction(name, list);}
     RPAREN)?
    {action = ActionFactory.createAction(name, list);}
    ;
    

actionFilterType returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = FILTERTYPE (LPAREN id = typeExpression {list.add(id);} 
    {action = ActionFactory.createAction(name, list);}
    (COMMA id = typeExpression {list.add(id);})* 
    {action = ActionFactory.createAction(name, list);}
    RPAREN)?
    {action = ActionFactory.createAction(name, list);}
    ;       

actionAddFilterType returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = ADDFILTERTYPE (LPAREN id = typeExpression {list.add(id);} 
    {action = ActionFactory.createAction(name, list);}
    (COMMA id = typeExpression {list.add(id);})* 
    {action = ActionFactory.createAction(name, list);}
    RPAREN)
    {action = ActionFactory.createAction(name, list);}
    ;   

actionAddRetainType returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = ADDRETAINTYPE (LPAREN id = typeExpression {list.add(id);} 
    {action = ActionFactory.createAction(name, list);}
    (COMMA id = typeExpression {list.add(id);})* 
    {action = ActionFactory.createAction(name, list);}
    RPAREN)
    {action = ActionFactory.createAction(name, list);}
    ;   


actionRemoveFilterType returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = REMOVEFILTERTYPE (LPAREN id = typeExpression {list.add(id);} 
    {action = ActionFactory.createAction(name, list);}
    (COMMA id = typeExpression {list.add(id);})* 
    {action = ActionFactory.createAction(name, list);}
    RPAREN)
    {action = ActionFactory.createAction(name, list);}
    ;   

actionRemoveRetainType returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = REMOVERETAINTYPE (LPAREN id = typeExpression {list.add(id);} 
    {action = ActionFactory.createAction(name, list);}
    (COMMA id = typeExpression {list.add(id);})* 
    {action = ActionFactory.createAction(name, list);}
    RPAREN)
    {action = ActionFactory.createAction(name, list);}
    ; 

actionCall returns [RutaAction action = null]
@init {
String string = "";

}
    :
    name = CALL lp = LPAREN 
    {   action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));}
    
    ns = dottedComponentReference 
    {   if(ns != null) {action = ActionFactory.createCallAction(name, ns);}} 
    RPAREN
    ;


actionConfigure returns [RutaAction action = null]
@init {
    List left = new ArrayList();
    List right = new ArrayList();
}
    :
    name = CONFIGURE lp = LPAREN 
    {   action = ActionFactory.createConfigureAction(name, StatementFactory.createEmtpyComponentReference(lp), null , null);}
    
    ns = dottedComponentReference 
    {   if(ns != null) {action = ActionFactory.createConfigureAction(name, ns, null , null);}} 
    
     (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = argument {left.add(fname); right.add(obj1);} 
    (COMMA fname = stringExpression ASSIGN_EQUAL obj1 = argument {left.add(fname);right.add(obj1);})*)?
    {   action = ActionFactory.createConfigureAction(name, ns, left , right);} 
    
    RPAREN
    ;


actionExec returns [RutaAction action = null]
@init {
String string = "";
}
    :
    name = EXEC lp = LPAREN 
     {   action = ActionFactory.createCallAction(name, StatementFactory.createEmtpyComponentReference(lp));}
     ((stringExpression)=> view = stringExpression COMMA)?
     {   if(view != null) {action = ActionFactory.createCallAction(name, ns, null, view);}}
    ns = dottedComponentReference 
     {   if(ns != null) {action = ActionFactory.createCallAction(name, ns, null, view);}}
     (COMMA tl = typeListExpression)?
     {   if(ns != null) {action = ActionFactory.createCallAction(name, ns, tl, view);}}
    RPAREN
    ;

        
actionAssign returns [RutaAction action = null]
@init{
    VariableReference ref = null;
}
    :
    name = ASSIGN LPAREN
    (id = Identifier 
    
    {
    ref = ExpressionFactory.createGenericVariableReference(id);
    action = ActionFactory.createAction(name, ref, e);}
    COMMA e = argument
    ) RPAREN 
    {
    ref = ExpressionFactory.createGenericVariableReference(id);
    action = ActionFactory.createAction(name, ref, e);}
    ;

//unknown
actionSetFeature returns [RutaAction action = null]
    :
    name = SETFEATURE LPAREN f = stringExpression 
     {action = ActionFactory.createAction(name, f, v);}
    COMMA v = argument 
    {action = ActionFactory.createAction(name, f, v);}
    RPAREN
    ;

actionGetFeature returns [RutaAction action = null]
    :
    name = GETFEATURE LPAREN f = stringExpression 
    {action = ActionFactory.createAction(name, f, v);}
    COMMA v = variable 
    {action = ActionFactory.createAction(name, f, v);}
    RPAREN
    ;

//unknown
actionDynamicAnchoring returns [RutaAction action = null]
    :
    name = DYNAMICANCHORING LPAREN active = booleanExpression 
     {action = ActionFactory.createAction(name, active);}
    (COMMA penalty = numberExpression 
    {action = ActionFactory.createAction(name, active, penalty);}
    (COMMA factor = numberExpression)?)? 
    {action = ActionFactory.createAction(name, active, penalty, factor);}
    RPAREN
    ;

actionGreedyAnchoring returns [RutaAction action = null]
    :
    name = GREEDYANCHORING 
    {action = ActionFactory.createAction(name, active);}
    LPAREN active = booleanExpression (COMMA active2 = booleanExpression)?
     {action = ActionFactory.createAction(name, active, active2);}
    RPAREN
    ;

actionTrim returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :
    name = TRIM LPAREN
    {action = ActionFactory.createAction(name, list);}
    (
    t1 = typeExpression {list.add(t1); action = ActionFactory.createAction(name, list);} 
    (COMMA t2 = typeExpression {list.add(t1); action = ActionFactory.createAction(name, list);})*
    |
    tl = typeListExpression {action = ActionFactory.createAction(name, tl);}
    )
    RPAREN
    ;

//unknown
actionUnmark returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :
    name = UNMARK LPAREN (
   
    
    f = typeExpression
    {action = ActionFactory.createAction(name, f);}
    
    (COMMA 
    (
  	(b = booleanExpression) => b = booleanExpression
  	{action = ActionFactory.createAction(name, f, b);}
  	|
  	(
  	index = numberExpression {list.add(index);} 
  	{action = ActionFactory.createAction(name, f, list);}
  	(COMMA index = numberExpression {list.add(index);})*
  	{action = ActionFactory.createAction(name, f, list);}
  	)
    )
      
    )?
    {action = ActionFactory.createAction(name, f , b, list);}
     RPAREN
     )
    ;



actionUnmarkAll returns [RutaAction action = null]
    :
    name = UNMARKALL LPAREN f = typeExpression 
    {action = ActionFactory.createAction(name, f, list);}
    (COMMA list = typeListExpression)?
    {action = ActionFactory.createAction(name, f, list);}
     RPAREN
    ;


//unknown

actionTransfer returns [RutaAction action = null]
    :
    name = TRANSFER LPAREN f = typeExpression 
    {action = ActionFactory.createAction(name, f);}
    RPAREN
    ;
    
actionTrie returns [RutaAction action = null]
@init {
Map<Expression, Expression> map = new HashMap<Expression, Expression>();
List<Expression> left = new ArrayList<Expression>();
List<Expression> right = new ArrayList<Expression>();
}
    :
    name = TRIE LPAREN
        key = stringExpression {left.add(key);}ASSIGN_EQUAL 
        (value = typeExpression{right.add(value);} | listValue = untypedListExpression {right.add(listValue);}) 
        (COMMA key = stringExpression {left.add(key);} ASSIGN_EQUAL 
        (value = typeExpression{right.add(value);} | listValue = untypedListExpression {right.add(listValue);}) 
        )*
        COMMA list = wordListExpression 
        
    COMMA ignoreCase = booleanExpression 
    COMMA ignoreLength = numberExpression 
    COMMA edit = booleanExpression 
    COMMA distance = numberExpression 
    COMMA ignoreChar = stringExpression 
    //TODO cost parameter
    
    {
    List<Expression> args = new ArrayList<Expression>();
    	args.add(ignoreCase);
	args.add(ignoreLength);
	args.add(edit);
	args.add(distance);
	args.add(ignoreChar);
    
    action = ActionFactory.createStructureAction(name, args, left, right, list);}
    RPAREN
    ;   


actionAdd returns [RutaAction action = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
} 
    :
    name = ADD LPAREN f = listVariable 
    {action = ActionFactory.createAction(name, f, list);}
    (COMMA a = argument {list.add(a);})+ 
    {action = ActionFactory.createAction(name, f, list);}
    RPAREN
    ;

actionRemove returns [RutaAction action = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
} 
    :
    name = REMOVE LPAREN f = listVariable 
    {action = ActionFactory.createAction(name, f, list);}
    (COMMA a = argument {list.add(a);})+ 
    {action = ActionFactory.createAction(name, f, list);}
    RPAREN
    ;


actionRemoveDuplicate returns [RutaAction action = null]
    :
    name = REMOVEDUPLICATE LPAREN f = listVariable 
    {action = ActionFactory.createAction(name, f);}
    RPAREN
    ;
   
actionMerge returns [RutaAction action = null]
@init{
	List<Expression> list = new ArrayList<Expression>();
} 
    :
    name = MERGE LPAREN join = booleanExpression 
     {action = ActionFactory.createAction(name, join, t, list);}
    COMMA t = listVariable 
     {action = ActionFactory.createAction(name, join, t, list);}
    COMMA f = listExpression {list.add(f);} 
    (COMMA f = listExpression {list.add(f);})+ 
    {action = ActionFactory.createAction(name, join, t, list);}
    RPAREN
    ;

actionGet returns [RutaAction action = null]
    :
    name = GET LPAREN f = listExpression 
    {action = ActionFactory.createAction(name, f, var, op);}
    COMMA var = variable 
    {action = ActionFactory.createAction(name, f, var, op);}
    COMMA op = stringExpression 
    {action = ActionFactory.createAction(name, f, var, op);}
    RPAREN
    ;


actionGetList returns [RutaAction action = null]
    :
    name = GETLIST LPAREN var = listVariable 
    {action = ActionFactory.createAction(name, var, op);}
    COMMA op = stringExpression 
    {action = ActionFactory.createAction(name, var, op);}
    RPAREN
    ;

actionMatchedText returns [RutaAction action = null]
@init {
List<Expression> list = new ArrayList<Expression>();
}
    :   
    name = MATCHEDTEXT LPAREN 
    var = variable
    (
    COMMA index = numberExpression {list.add(index);}
    )* 
    {action = ActionFactory.createAction(name, var, list);}
    RPAREN
    ;
    
actionClear returns [RutaAction action = null]
    :
    name = CLEAR LPAREN var = listVariable 
    {action = ActionFactory.createAction(name, var);}
    RPAREN
    ;


//OKdc
varArgumentList returns [List<Expression> args = new ArrayList<Expression>()]
	:
	arg = argument {args.add(arg);} (COMMA arg = argument {args.add(arg);})*
	;

//changed but unknown statuslistExpression
argument returns [Expression expr = null] //SimpleReference arg1 = null]
//options {
//	backtrack = true;
//}
	:
	(nullExpression) => a5 = nullExpression {expr = a5;}
	| (featureExpression)=> fe = featureExpression {expr = fe;}
	| (booleanExpression)=> a2 = booleanExpression {expr = a2;}
	| (numberExpression)=> a3 = numberExpression {expr = a3;}
	| (stringExpression)=> a4 = stringExpression {expr = a4;}
	| (listExpression)=> l = listExpression {expr = l;}
	| a1 = typeExpression {expr = a1;}
	//token = (
	//(booleanExpression[par]) => booleanExpression[par]
	//| (numberExpression[par]) => numberExpression[par]
	//| (stringExpression[par]) => stringExpression[par]
	//| (typeExpression[par]) => typeExpression[par]
	//)
	//{arg = token;}
	;

nullExpression returns [Expression expr = null]
	:
	t = NULL {expr = ExpressionFactory.createNullExpression(t);}
	;

primitiveArgument returns [Expression expr = null]
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

//snooze
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
		dot = (DOT| MINUS) {ct.setText(ct.getText() + dot.getText());}
		id = Identifier {ct.setStopIndex(getBounds(id)[1]);
		                 ct.setText(ct.getText() + id.getText());}
	)+
	{token = ct;
	 return token;}
	;

dottedId3 returns [Token token = null ]
@init {CommonToken ct = null;}
	:
	// at least one dot!
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

//snooze	
dottedComponentReference returns [ComponentReference ref = null ]
@init {CommonToken ct = null;}
	:
	id = Identifier {
		ct = new CommonToken(id);
		//if (ct.getText().equals("<missing Identifier>")) {
	        //    CommonTokenStream cts = (CommonTokenStream) input;
	        //    Token lt = cts.LT(1);
	        //    ct = new CommonToken(lt);
	        //  }
		}
	(
		dot = (DOT | MINUS) {ct.setText(ct.getText() + dot.getText());}
		id = Identifier {ct.setStopIndex(getBounds(id)[1]);
		                 ct.setText(ct.getText() + id.getText());}
	)*
	{
	 if (!ct.getText().equals("<missing Identifier>")) ref = StatementFactory.createComponentReference(ct);}
	;

dottedComponentDeclaration returns [ComponentDeclaration ref = null ]
@init {CommonToken ct = null;}
	:
	id = Identifier {
		ct = new CommonToken(id);
		ref = StatementFactory.createComponentDeclaration(ct);
		}
	(
		{(input.get(id.getTokenIndex()+1).getChannel() != Token.HIDDEN_CHANNEL)}?
		dot = (DOT | MINUS) {ct.setText(ct.getText() + dot.getText());ref = StatementFactory.createComponentDeclaration(ct);}
		{(input.get(dot.getTokenIndex()+1).getChannel() != Token.HIDDEN_CHANNEL)}?
		id = Identifier {ct.setStopIndex(getBounds(id)[1]);
		                 ct.setText(ct.getText() + id.getText());
		                 ref = StatementFactory.createComponentDeclaration(ct);}
	)*
	{
	 if (!ct.getText().equals("<missing Identifier>")) ref = StatementFactory.createComponentDeclaration(ct);}
	;

//seems OK
annotationType returns [VariableReference at = null]
	: 
	(
	atRef = annotationTypeVariableReference {at = atRef;}
	)
	;
		
annotationTypeVariableReference returns [VariableReference typeVar = null]
  :
  atRef = dottedId 
  {typeVar = ExpressionFactory.createAnnotationTypeVariableReference(atRef);}
;

wordListExpression returns [Expression expr = null]
	:
	id = Identifier
	{expr = ExpressionFactory.createListVariableReference(id);}
	|
	path = RessourceLiteral
	{expr = ExpressionFactory.createRessourceReference(path);}
	;


wordTableExpression returns [Expression expr = null]
	:
	id = Identifier
	{expr = ExpressionFactory.createTableVariableReference(id);}
	|
	path = RessourceLiteral
	{expr = ExpressionFactory.createRessourceReference(path);}
	;

//seems OK
numberExpression returns [Expression expr = null]
@init {
expr = ExpressionFactory.createEmptyNumberExpression(input.LT(1));
}
	:
	e = additiveExpression
	{if(e!=null) expr = ExpressionFactory.createNumberExpression(e);}
	;

//seems OK
additiveExpression returns [Expression root = null]
    :   expr1=multiplicativeExpression {root=expr1;}
	((PLUS | MINUS)=> op=(PLUS | MINUS) expr2=multiplicativeExpression {root=ExpressionFactory.createBinaryArithmeticExpr(root,expr2,op);})*
	;

//NOT OK TODO
multiplicativeExpression returns [Expression root = null]
    :
	((simpleNumberExpression)=> expr1 = simpleNumberExpression {root=expr1;}
	( ( STAR | SLASH | PERCENT )=> op=( STAR | SLASH | PERCENT ) sNE = simpleNumberExpression {root=ExpressionFactory.createBinaryArithmeticExpr(root,sNE,op);} )*
	|   e1 = numberFunction {root = e1;})
	;

//seems OK
numberExpressionInPar returns [RutaExpression expr = null]
	:
	lp = LPAREN numE = numberExpression rp = RPAREN 
	{expr = ExpressionFactory.createNumberExpression((RutaExpression)numE); 
	  expr.setInParantheses(true);
          expr.setStart(((CommonToken) lp).getStartIndex());
          expr.setEnd(((CommonToken) rp).getStopIndex()+1);}
	;

//seems OK
simpleNumberExpression returns [Expression expr = null]
	:
	(featureExpression)=> fe = featureExpression {expr = ExpressionFactory.createNumberExpression(fe);}
	|
	 m = MINUS? numVarRef = numberVariable
	  {if(m == null) {expr = numVarRef;} else {expr = ExpressionFactory.createNegatedNumberExpression(m, numVarRef);}}
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
	| op = POW LPAREN n1 = numberExpression COMMA n2 = numberExpression RPAREN
	{expr = ExpressionFactory.createBinaryArithmeticExpr(n1, n2, op);}
	//| {root = ExpressionFactory.createNumberFunction(numExprP,op)}
	| (e = externalNumberFunction)=> e = externalNumberFunction {expr = e;}
	;

// not checked
externalNumberFunction returns [Expression expr = null]
	:
	id = Identifier
	LPAREN
	args = varArgumentList?
	RPAREN
	{
		expr = external.createExternalNumberFunction(id, args);
	}
	;

//OK
numberVariable returns [Expression expr = null]
	:
	 ( {isVariableOfType(input.LT(1).getText(), "INT")}? numVarRef = Identifier //
	 | {isVariableOfType(input.LT(1).getText(), "DOUBLE")}? numVarRef = Identifier
	 | {isVariableOfType(input.LT(1).getText(), "FLOAT")}? numVarRef = Identifier)
	 {	 expr = ExpressionFactory.createNumberVariableReference(numVarRef);}
	;
	catch [Exception e]{expr = ExpressionFactory.createNumberVariableReference(input.LT(1));}
	
//OK - interface to flag stringExpressions?
stringExpression returns [Expression expr = null]
@init {
List<Expression> exprList = new ArrayList<Expression>();
{expr = ExpressionFactory.createEmptyStringExpression(input.LT(1));}
}
	:
	(featureExpression)=> fe = featureExpression {expr = ExpressionFactory.createStringExpression(fe);}
	|
	(stringFunction)=> e = stringFunction {expr = e;} 
	|
	strExpr1 = simpleStringExpression {if (strExpr1!=null) exprList.add(strExpr1);}
	((PLUS)=> PLUS (nextstrExpr = simpleStringExpression {if (nextstrExpr!=null) exprList.add(nextstrExpr);}
		| ne = numberExpressionInPar {if (ne!=null) exprList.add(ne);}
		| be = simpleBooleanExpression {if (be!=null) exprList.add(be);}
		| (listExpression)=> le = listExpression {if (le!=null) exprList.add(le);}
		| te = typeExpression {if (te!=null) exprList.add(te);}
		))*
	{expr = ExpressionFactory.createStringExpression(exprList);}
	;


// not checked
stringFunction returns [Expression expr = null]
@init {List<Expression> list = new ArrayList<Expression>();}
:
	name = REMOVESTRING LPAREN var = variable (COMMA s = stringExpression{list.add(s);})+ RPAREN
	{expr = ExpressionFactory.createStringFunction(name,var,list);}
	|
	(e = externalStringFunction)=> e = externalStringFunction {expr = e;}
	;

// not checked
externalStringFunction returns [Expression expr = null]
	:
	id = Identifier
	LPAREN
	args = varArgumentList?
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
@init{
expr = ExpressionFactory.createEmptyBooleanExpression(input.LT(1));
}
	:
	(featureTypeExpression)=>fmbe = featureTypeExpression {expr = ExpressionFactory.createBooleanExpression(fmbe);}
	| (featureExpression)=> fe = featureExpression {expr = ExpressionFactory.createBooleanExpression(fe);}
	| (composedBooleanExpression)=> bcE = composedBooleanExpression {expr = bcE;}
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
	| (bne = booleanNumberExpression)=> bne = booleanNumberExpression{expr = bne;}
	| (bse = booleanStringExpression)=> bse = booleanStringExpression{expr = bse;}
	| (bte = booleanTypeExpression)=> bte = booleanTypeExpression{expr = bte;}
	| e1 = booleanFunction {expr = e1;}
	| LPAREN ep = booleanExpression RPAREN {expr = ep;}
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
	id = Identifier
	LPAREN
	args = varArgumentList?
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
	//LPAREN
	e1 = numberExpression
	op = (LESS | GREATER | GREATEREQUAL | LESSEQUAL | EQUAL | NOTEQUAL)
	e2 = numberExpression
	//RPAREN
	{expr = ExpressionFactory.createBooleanNumberExpression(e1,op,e2);}
	;

booleanStringExpression  returns  [Expression expr = null]
	:
	//LPAREN
	e1 = stringExpression
	op = (EQUAL | NOTEQUAL)
	e2 = stringExpression
	//RPAREN
	{expr = ExpressionFactory.createBooleanStringExpression(e1,op,e2);}
	;

genericVariableReference returns[Expression varRef]
:
  id=Identifier
  {return ExpressionFactory.createGenericVariableReference(id);}
;


annotationExpression returns [Expression expr = null]
	:
	{isVariableOfType(input.LT(1).getText(), "ANNOTATION")}? 
	id = Identifier {expr = ExpressionFactory.createAnnotationTypeVariableReference(id);} 
	|
	ale = annotationListExpression {expr = ale;}
	|
	aae = annotationAddressExpression {expr = aae;}
	|
	ale = annotationLabelExpression {expr = ale;}
	;  

annotationListExpression returns [Expression expr = null]
	:
	{isVariableOfType(input.LT(1).getText(), "ANNOTATIONLIST")}? 
	id = Identifier {expr = ExpressionFactory.createAnnotationTypeVariableReference(id);} 
	;

annotationAddressExpression returns [Expression expr = null]
	:
	ADDRESS_PREFIX address = DecimalLiteral {expr = ExpressionFactory.createAnnotationTypeVariableReference(address);}
	;
	
annotationLabelExpression returns [Expression expr = null]
	:
	label = Identifier {expr = ExpressionFactory.createAnnotationTypeVariableReference(label);}
	;

