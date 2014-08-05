package org.apache.uima.ruta.string.bool;

import java.util.List;

import org.apache.uima.ruta.RutaElement;
import org.apache.uima.ruta.expression.RutaExpression;
import org.apache.uima.ruta.expression.bool.BooleanFunctionExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.extensions.IRutaBooleanFunctionExtension;
import org.apache.uima.ruta.extensions.RutaParseException;
import org.apache.uima.ruta.verbalize.RutaVerbalizer;

public class BooleanOperationsExtension implements IRutaBooleanFunctionExtension {

  private final String[] knownExtensions = new String[] { "contains", "endsWith",
      "startsWith", "equals", "equalsIgnoreCase", "isEmpty" };

  private final Class<?>[] extensions = new Class[] { ContainsBooleanFunction.class,
      EndsWithBooleanFunction.class, StartsWithBooleanFunction.class,
      EqualsBooleanFunction.class, EqualsIgnoreCaseBooleanFunction.class,
      IsEmptyBooleanFunction.class };

  
  
  public String verbalize(RutaElement element, RutaVerbalizer verbalizer) {
    if (element instanceof ContainsBooleanFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((ContainsBooleanFunction) element).getExpr()) + ")";
    } else if (element instanceof EndsWithBooleanFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((EndsWithBooleanFunction) element).getExpr()) + ")";
    }
    //
    else if (element instanceof StartsWithBooleanFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((StartsWithBooleanFunction) element).getExpr()) + ")";
    } else if (element instanceof EqualsBooleanFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((EqualsBooleanFunction) element).getExpr()) + ")";
    } else if (element instanceof EqualsIgnoreCaseBooleanFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((EqualsIgnoreCaseBooleanFunction) element).getExpr()) + ")";
    } else if (element instanceof IsEmptyBooleanFunction) {
      return verbalizeName(element) + "("
              + verbalizer.verbalize(((IsEmptyBooleanFunction) element).getExpr())
              + ")";
    }
    //
    else {
      return "UnknownStringFunction";
    }
  }


  public String verbalizeName(RutaElement element) {
    
     if (element instanceof ContainsBooleanFunction) {
      return knownExtensions[0];
    }
     
     else if (element instanceof EndsWithBooleanFunction) {
      return knownExtensions[1];
    }

    
    else if (element instanceof StartsWithBooleanFunction) {
      return knownExtensions[2];
    }
    
    else if (element instanceof EqualsBooleanFunction) {
      return knownExtensions[3];
    }
    
    else if (element instanceof EqualsIgnoreCaseBooleanFunction) {
      return knownExtensions[4];
    }
    
    else if (element instanceof IsEmptyBooleanFunction) {
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

  @Override
  public BooleanFunctionExpression createBooleanFunction(String name, List<RutaExpression> args)
          throws RutaParseException {
    if(args ==null ){
      throw new RutaParseException("You have to specify at least 1 Argument to use these Functions !");
    }
    
    if (name.equals(knownExtensions[5])) {
      return new IsEmptyBooleanFunction((IStringExpression) args.get(0));
    }
    
    if (args == null || args.size()<2) {
      throw new RutaParseException("You have to specify at least 2 Arguments to use these Functions !");
    }

    if (name.equals(knownExtensions[0])) {
      return new ContainsBooleanFunction((IStringExpression) args.get(0), (IStringExpression) args.get(1));
    }

    else if (name.equals(knownExtensions[1])) {
      return new EndsWithBooleanFunction((IStringExpression) args.get(0),(IStringExpression) args.get(1));
    }

    else if (name.equals(knownExtensions[2])) {
      return new StartsWithBooleanFunction((IStringExpression) args.get(0),(IStringExpression) args.get(1));
    }
    
     if (name.equals(knownExtensions[3])) {
      return new EqualsBooleanFunction((IStringExpression) args.get(0),(IStringExpression) args.get(1));
    }
    else if (name.equals(knownExtensions[4])) {
      return new EqualsIgnoreCaseBooleanFunction((IStringExpression) args.get(0),(IStringExpression) args.get(1));
    }
     
    
    return null;
  }



}
