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

package org.apache.uima.ruta.verbalize;

import java.util.Iterator;

import org.apache.uima.ruta.expression.AnnotationTypeExpression;
import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.NullExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationAddressExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationLabelExpression;
import org.apache.uima.ruta.expression.annotation.AnnotationVariableExpression;
import org.apache.uima.ruta.expression.annotation.IAnnotationExpression;
import org.apache.uima.ruta.expression.bool.BooleanFeatureExpression;
import org.apache.uima.ruta.expression.bool.BooleanListVariableExpression;
import org.apache.uima.ruta.expression.bool.BooleanNumberExpression;
import org.apache.uima.ruta.expression.bool.BooleanTypeExpression;
import org.apache.uima.ruta.expression.bool.BooleanVariableExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanListExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.feature.GenericFeatureExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.UntypedListExpression;
import org.apache.uima.ruta.expression.number.ComposedNumberExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.NegativeNumberExpression;
import org.apache.uima.ruta.expression.number.NumberFeatureExpression;
import org.apache.uima.ruta.expression.number.NumberListVariableExpression;
import org.apache.uima.ruta.expression.number.NumberVariableExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberListExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordListExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordTableExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.ComposedStringExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.LiteralStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringListExpression;
import org.apache.uima.ruta.expression.string.StringFeatureExpression;
import org.apache.uima.ruta.expression.string.StringListVariableExpression;
import org.apache.uima.ruta.expression.string.StringVariableExpression;
import org.apache.uima.ruta.expression.type.ITypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.type.TypeListVariableExpression;
import org.apache.uima.ruta.expression.type.TypeVariableExpression;

public class ExpressionVerbalizer {

  private RutaVerbalizer verbalizer;

  public ExpressionVerbalizer(RutaVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalize(IRutaExpression expression) {
    if (expression instanceof GenericFeatureExpression) {
      return verbalize(((GenericFeatureExpression) expression).getFeatureExpression());
    } else if (expression instanceof AnnotationTypeExpression) {
      return verbalize((AnnotationTypeExpression) expression);
    } else if (expression instanceof IAnnotationExpression) {
      return verbalize((IAnnotationExpression) expression);
    } else if (expression instanceof ITypeExpression) {
      return verbalize((ITypeExpression) expression);
    } else if (expression instanceof IBooleanExpression) {
      return verbalize((IBooleanExpression) expression);
    } else if (expression instanceof INumberExpression) {
      return verbalize((INumberExpression) expression);
    } else if (expression instanceof WordListExpression) {
      return verbalize((WordListExpression) expression);
    } else if (expression instanceof WordTableExpression) {
      return verbalize((WordTableExpression) expression);
    } else if (expression instanceof ListExpression<?>) {
      return verbalize((ListExpression<?>) expression);
    } else if (expression instanceof FeatureMatchExpression) {
      return verbalize((FeatureMatchExpression) expression);
    } else if (expression instanceof FeatureExpression) {
      return verbalize((FeatureExpression) expression);
    } else if (expression instanceof IStringExpression) {
      return verbalize((IStringExpression) expression);
    } else if (expression instanceof MatchReference) {
      return verbalize((MatchReference) expression);
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(WordTableExpression expression) {
    if (expression instanceof ReferenceWordTableExpression) {
      ReferenceWordTableExpression e = (ReferenceWordTableExpression) expression;
      return e.getRef();
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(WordListExpression expression) {
    if (expression instanceof ReferenceWordListExpression) {
      ReferenceWordListExpression e = (ReferenceWordListExpression) expression;
      return e.getRef();
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(ListExpression<?> expression) {
    if (expression instanceof SimpleBooleanListExpression) {
      SimpleBooleanListExpression e = (SimpleBooleanListExpression) expression;
      return "{" + verbalizer.verbalizeExpressionList(e.getList()) + "}";
    } else if (expression instanceof BooleanListVariableExpression) {
      BooleanListVariableExpression e = (BooleanListVariableExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleNumberListExpression) {
      SimpleNumberListExpression e = (SimpleNumberListExpression) expression;
      return "{" + verbalizer.verbalizeExpressionList(e.getList()) + "}";
    } else if (expression instanceof NumberListVariableExpression) {
      NumberListVariableExpression e = (NumberListVariableExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleStringListExpression) {
      SimpleStringListExpression e = (SimpleStringListExpression) expression;
      return "{" + verbalizer.verbalizeExpressionList(e.getList()) + "}";
    } else if (expression instanceof StringListVariableExpression) {
      StringListVariableExpression e = (StringListVariableExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleTypeListExpression) {
      SimpleTypeListExpression e = (SimpleTypeListExpression) expression;
      return "{" + verbalizer.verbalizeExpressionList(e.getList()) + "}";
    } else if (expression instanceof TypeListVariableExpression) {
      TypeListVariableExpression e = (TypeListVariableExpression) expression;
      return e.getVar();
    } else if (expression instanceof UntypedListExpression) {
      UntypedListExpression e = (UntypedListExpression) expression;
      return "{" + verbalizer.verbalizeExpressionList(e.getList()) + "}";
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(INumberExpression expression) {
    if (expression == null) {
      return "";
    } else if (expression instanceof NegativeNumberExpression) {
      NegativeNumberExpression e = (NegativeNumberExpression) expression;
      return "-(" + e.getExpression() + ")";
    } else if (expression instanceof NumberVariableExpression) {
      NumberVariableExpression e = (NumberVariableExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleNumberExpression) {
      SimpleNumberExpression e = (SimpleNumberExpression) expression;
      return e.getNumber().toString();
    } else if (expression instanceof ComposedNumberExpression) {
      ComposedNumberExpression e = (ComposedNumberExpression) expression;
      INumberExpression ne = e.getExpressions().get(0);
      if (ne == null) {
        return "";
      }
      StringBuilder result = new StringBuilder(verbalize(ne));
      for (int i = 0; i < e.getOperators().size(); i++) {
        result.append(" " + e.getOperators().get(i) + " ");
        if (e.getExpressions().size() > i + 1) {
          result.append(verbalize(e.getExpressions().get(i + 1)));
        }
      }
      return result.toString();
    } else if (expression instanceof NumberFeatureExpression) {
      return verbalize(((NumberFeatureExpression) expression).getFe());
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(IBooleanExpression expression) {
    if (expression == null) {
      return "";
    } else if (expression instanceof BooleanNumberExpression) {
      BooleanNumberExpression e = (BooleanNumberExpression) expression;
      return verbalize(e.getFristExpression()) + " " + e.getOperator() + " "
              + verbalize(e.getSecondExpression());
    } else if (expression instanceof BooleanTypeExpression) {
      BooleanTypeExpression e = (BooleanTypeExpression) expression;
      return verbalize(e.getFristExpression()) + " " + e.getOperator() + " "
              + verbalize(e.getSecondExpression());
    } else if (expression instanceof BooleanVariableExpression) {
      BooleanVariableExpression e = (BooleanVariableExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleBooleanExpression) {
      SimpleBooleanExpression e = (SimpleBooleanExpression) expression;
      return e.getPrimitiveValue() ? "true" : "false";
    } else if (expression instanceof BooleanFeatureExpression) {
      return verbalize(((BooleanFeatureExpression) expression).getFe());
    }
    return expression.getClass().getSimpleName();
  }

  @SuppressWarnings("rawtypes")
  public String verbalize(IStringExpression expression) {
    if (expression == null) {
      return "";
    } else if (expression instanceof INumberExpression) {
      return verbalize((INumberExpression) expression);
    } else if (expression instanceof ITypeExpression) {
      return verbalize((ITypeExpression) expression);
    } else if (expression instanceof IBooleanExpression) {
      return verbalize((IBooleanExpression) expression);
    } else if (expression instanceof ListExpression) {
      return verbalize((ListExpression) expression);
    } else if (expression instanceof LiteralStringExpression) {
      return verbalize((LiteralStringExpression) expression);
    } else if (expression instanceof StringFeatureExpression) {
      return verbalize(((StringFeatureExpression) expression).getFe());
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(LiteralStringExpression expression) {
    if (expression == null) {
      return "";
    } else if (expression instanceof ComposedStringExpression) {
      ComposedStringExpression e = (ComposedStringExpression) expression;
      StringBuilder sb = new StringBuilder();
      Iterator<IStringExpression> it = e.getExpressions().iterator();
      while (it.hasNext()) {
        IStringExpression each = it.next();
        sb.append(verbalize(each));
        if (it.hasNext()) {
          sb.append(" + ");
        }
      }
      return sb.toString();
    } else if (expression instanceof StringVariableExpression) {
      StringVariableExpression e = (StringVariableExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleStringExpression) {
      SimpleStringExpression e = (SimpleStringExpression) expression;
      return "\"" + e.getValue() + "\"";
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(ITypeExpression expression) {
    if (expression == null) {
      return null;
    }
    if (expression instanceof SimpleTypeExpression) {
      SimpleTypeExpression e = (SimpleTypeExpression) expression;
      String type = e.getTypeString();
      int indexOf = type.lastIndexOf(".");
      if (indexOf != -1) {
        type = type.substring(indexOf + 1, type.length());
      }
      if (type.equals("DocumentAnnotation")) {
        type = "Document";
      }
      return type;
    } else if (expression instanceof TypeVariableExpression) {
      TypeVariableExpression e = (TypeVariableExpression) expression;
      return e.getVar();
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(IAnnotationExpression expression) {
    if (expression == null) {
      return null;
    }
    if (expression instanceof AnnotationAddressExpression) {
      AnnotationAddressExpression e = (AnnotationAddressExpression) expression;
      return "$" + e.getAddress();
    } else if (expression instanceof AnnotationVariableExpression) {
      AnnotationVariableExpression e = (AnnotationVariableExpression) expression;
      return e.getVar();
    } else if (expression instanceof AnnotationLabelExpression) {
      AnnotationLabelExpression e = (AnnotationLabelExpression) expression;
      return e.getLabel();
    }
    return expression.getClass().getSimpleName();
  }
  
  public String verbalize(MatchReference expression) {
    return expression.getMatch();
  }
  
  public String verbalize(AnnotationTypeExpression expression) {
    return verbalize(expression.getReference());
  }

  public String verbalize(FeatureExpression expression) {
    StringBuilder sb = new StringBuilder();
    if (expression instanceof SimpleFeatureExpression) {
      SimpleFeatureExpression sfe = (SimpleFeatureExpression) expression;
      sb.append(sfe.getMatchReference().getMatch());
    } else if(expression instanceof NullExpression) {
      sb.append("null");
    }
    return sb.toString();
  }

  public String verbalize(FeatureMatchExpression expression) {
    return verbalize((FeatureExpression) expression) + expression.getOp()
            + verbalize(expression.getArg());
  }

}
