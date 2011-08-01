package org.apache.uima.tm.textmarker.kernel.extensions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.Token;
import org.apache.uima.tm.textmarker.action.AbstractTextMarkerAction;
import org.apache.uima.tm.textmarker.condition.AbstractTextMarkerCondition;
import org.apache.uima.tm.textmarker.kernel.expression.TextMarkerExpression;
import org.apache.uima.tm.textmarker.kernel.expression.bool.BooleanExpression;
import org.apache.uima.tm.textmarker.kernel.expression.number.NumberExpression;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.expression.type.TypeExpression;

import antlr.ANTLRException;

public class TextMarkerExternalFactory {

  private Map<String, ITextMarkerConditionExtension> conditionExtensions;

  private Map<String, ITextMarkerActionExtension> actionExtensions;

  private Map<String, ITextMarkerTypeFunctionExtension> typeFunctionExtensions;

  private Map<String, ITextMarkerBooleanFunctionExtension> booleanFunctionExtensions;

  private Map<String, ITextMarkerStringFunctionExtension> stringFunctionExtensions;

  private Map<String, ITextMarkerNumberFunctionExtension> numberFunctionExtensions;

  public TextMarkerExternalFactory() {
    super();
    conditionExtensions = new HashMap<String, ITextMarkerConditionExtension>();
    actionExtensions = new HashMap<String, ITextMarkerActionExtension>();
    booleanFunctionExtensions = new HashMap<String, ITextMarkerBooleanFunctionExtension>();
    stringFunctionExtensions = new HashMap<String, ITextMarkerStringFunctionExtension>();
    numberFunctionExtensions = new HashMap<String, ITextMarkerNumberFunctionExtension>();
    typeFunctionExtensions = new HashMap<String, ITextMarkerTypeFunctionExtension>();
  }

  public AbstractTextMarkerCondition createExternalCondition(Token id,
          List<TextMarkerExpression> args) throws ANTLRException {
    String name = id.getText();
    ITextMarkerConditionExtension extension = conditionExtensions.get(name);
    if (extension != null) {
      return extension.createCondition(name, args);
    }
    // Throw exception
    return null;
  }

  public AbstractTextMarkerAction createExternalAction(Token id, List<TextMarkerExpression> args)
          throws ANTLRException {
    String name = id.getText();
    ITextMarkerActionExtension extension = actionExtensions.get(name);
    if (extension != null) {
      return extension.createAction(name, args);
    }
    // Throw exception
    return null;
  }

  public TypeExpression createExternalTypeFunction(Token id, List<TextMarkerExpression> args)
          throws ANTLRException {
    String name = id.getText();
    ITextMarkerTypeFunctionExtension extension = typeFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createTypeFunction(name, args);
    }
    // Throw exception
    return null;
  }

  public BooleanExpression createExternalBooleanFunction(Token id, List<TextMarkerExpression> args)
          throws ANTLRException {
    String name = id.getText();
    ITextMarkerBooleanFunctionExtension extension = booleanFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createBooleanFunction(name, args);
    }
    // Throw exception
    return null;
  }

  public StringExpression createExternalStringFunction(Token id, List<TextMarkerExpression> args)
          throws ANTLRException {
    String name = id.getText();
    ITextMarkerStringFunctionExtension extension = stringFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createStringFunction(name, args);
    }
    // Throw exception
    return null;
  }

  public NumberExpression createExternalNumberFunction(Token id, List<TextMarkerExpression> args)
          throws ANTLRException {
    String name = id.getText();
    ITextMarkerNumberFunctionExtension extension = numberFunctionExtensions.get(name);
    if (extension != null) {
      return extension.createNumberFunction(name, args);
    }
    // Throw exception
    return null;
  }

  public void addExtension(String id, ITextMarkerExtension extension) {
    if (extension instanceof ITextMarkerActionExtension) {
      addActionExtension(id, (ITextMarkerActionExtension) extension);
    } else if (extension instanceof ITextMarkerConditionExtension) {
      addConditionExtension(id, (ITextMarkerConditionExtension) extension);
    } else if (extension instanceof ITextMarkerBooleanFunctionExtension) {
      addBooleanFunctionExtension(id, (ITextMarkerBooleanFunctionExtension) extension);
    } else if (extension instanceof ITextMarkerStringFunctionExtension) {
      addStringFunctionExtension(id, (ITextMarkerStringFunctionExtension) extension);
    } else if (extension instanceof ITextMarkerNumberFunctionExtension) {
      addNumberFunctionExtension(id, (ITextMarkerNumberFunctionExtension) extension);
    } else if (extension instanceof ITextMarkerTypeFunctionExtension) {
      addTypeFunctionExtension(id, (ITextMarkerTypeFunctionExtension) extension);
    }
  }

  public void addConditionExtension(String id, ITextMarkerConditionExtension extension) {
    conditionExtensions.put(id, extension);
  }

  public void addActionExtension(String id, ITextMarkerActionExtension extension) {
    actionExtensions.put(id, extension);
  }

  public void addNumberFunctionExtension(String id, ITextMarkerNumberFunctionExtension extension) {
    numberFunctionExtensions.put(id, extension);
  }

  public void addBooleanFunctionExtension(String id, ITextMarkerBooleanFunctionExtension extension) {
    booleanFunctionExtensions.put(id, extension);
  }

  public void addStringFunctionExtension(String id, ITextMarkerStringFunctionExtension extension) {
    stringFunctionExtensions.put(id, extension);
  }

  public void addTypeFunctionExtension(String id, ITextMarkerTypeFunctionExtension extension) {
    typeFunctionExtensions.put(id, extension);
  }

  public boolean isInitialized() {
    return !actionExtensions.isEmpty() || !conditionExtensions.isEmpty()
            || !booleanFunctionExtensions.isEmpty() || !numberFunctionExtensions.isEmpty()
            || !stringFunctionExtensions.isEmpty() || !typeFunctionExtensions.isEmpty();
  }

}
