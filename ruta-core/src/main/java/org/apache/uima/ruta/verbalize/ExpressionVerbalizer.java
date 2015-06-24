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
import java.util.List;

import org.apache.uima.ruta.expression.IRutaExpression;
import org.apache.uima.ruta.expression.MatchReference;
import org.apache.uima.ruta.expression.bool.BooleanFeatureExpression;
import org.apache.uima.ruta.expression.bool.BooleanNumberExpression;
import org.apache.uima.ruta.expression.bool.BooleanTypeExpression;
import org.apache.uima.ruta.expression.bool.IBooleanExpression;
import org.apache.uima.ruta.expression.bool.ReferenceBooleanExpression;
import org.apache.uima.ruta.expression.bool.SimpleBooleanExpression;
import org.apache.uima.ruta.expression.feature.FeatureExpression;
import org.apache.uima.ruta.expression.feature.FeatureMatchExpression;
import org.apache.uima.ruta.expression.feature.GenericFeatureExpression;
import org.apache.uima.ruta.expression.feature.SimpleFeatureExpression;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.list.ReferenceBooleanListExpression;
import org.apache.uima.ruta.expression.list.ReferenceNumberListExpression;
import org.apache.uima.ruta.expression.list.ReferenceStringListExpression;
import org.apache.uima.ruta.expression.list.ReferenceTypeListExpression;
import org.apache.uima.ruta.expression.list.SimpleBooleanListExpression;
import org.apache.uima.ruta.expression.list.SimpleNumberListExpression;
import org.apache.uima.ruta.expression.list.SimpleStringListExpression;
import org.apache.uima.ruta.expression.list.SimpleTypeListExpression;
import org.apache.uima.ruta.expression.list.UntypedListExpression;
import org.apache.uima.ruta.expression.number.ComposedNumberExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.NegativeNumberExpression;
import org.apache.uima.ruta.expression.number.NumberFeatureExpression;
import org.apache.uima.ruta.expression.number.ReferenceNumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordListExpression;
import org.apache.uima.ruta.expression.resource.ReferenceWordTableExpression;
import org.apache.uima.ruta.expression.resource.WordListExpression;
import org.apache.uima.ruta.expression.resource.WordTableExpression;
import org.apache.uima.ruta.expression.string.ComposedStringExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;
import org.apache.uima.ruta.expression.string.LiteralStringExpression;
import org.apache.uima.ruta.expression.string.ReferenceStringExpression;
import org.apache.uima.ruta.expression.string.SimpleStringExpression;
import org.apache.uima.ruta.expression.string.StringFeatureExpression;
import org.apache.uima.ruta.expression.type.ReferenceTypeExpression;
import org.apache.uima.ruta.expression.type.SimpleTypeExpression;
import org.apache.uima.ruta.expression.type.TypeExpression;

public class ExpressionVerbalizer {

  private RutaVerbalizer verbalizer;

  public ExpressionVerbalizer(RutaVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalize(IRutaExpression expression) {
    if (expression instanceof GenericFeatureExpression) {
      return verbalize(((GenericFeatureExpression) expression).getFeatureExpression());
    } else if (expression instanceof TypeExpression) {
      return verbalize((TypeExpression) expression);
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
    } else if (expression instanceof ReferenceBooleanListExpression) {
      ReferenceBooleanListExpression e = (ReferenceBooleanListExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleNumberListExpression) {
      SimpleNumberListExpression e = (SimpleNumberListExpression) expression;
      return "{" + verbalizer.verbalizeExpressionList(e.getList()) + "}";
    } else if (expression instanceof ReferenceNumberListExpression) {
      ReferenceNumberListExpression e = (ReferenceNumberListExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleStringListExpression) {
      SimpleStringListExpression e = (SimpleStringListExpression) expression;
      return "{" + verbalizer.verbalizeExpressionList(e.getList()) + "}";
    } else if (expression instanceof ReferenceStringListExpression) {
      ReferenceStringListExpression e = (ReferenceStringListExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleTypeListExpression) {
      SimpleTypeListExpression e = (SimpleTypeListExpression) expression;
      return "{" + verbalizer.verbalizeExpressionList(e.getList()) + "}";
    } else if (expression instanceof ReferenceTypeListExpression) {
      ReferenceTypeListExpression e = (ReferenceTypeListExpression) expression;
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
    } else if (expression instanceof ReferenceNumberExpression) {
      ReferenceNumberExpression e = (ReferenceNumberExpression) expression;
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
    } else if (expression instanceof ReferenceBooleanExpression) {
      ReferenceBooleanExpression e = (ReferenceBooleanExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleBooleanExpression) {
      SimpleBooleanExpression e = (SimpleBooleanExpression) expression;
      return e.getPrimitiveValue() ? "true" : "false";
    } else if (expression instanceof BooleanFeatureExpression) {
      return verbalize(((BooleanFeatureExpression) expression).getFe());
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(IStringExpression expression) {
    if (expression == null) {
      return "";
    } else if (expression instanceof INumberExpression) {
      return verbalize((INumberExpression) expression);
    } else if (expression instanceof TypeExpression) {
      return verbalize((TypeExpression) expression);
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
    } else if (expression instanceof ReferenceStringExpression) {
      ReferenceStringExpression e = (ReferenceStringExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleStringExpression) {
      SimpleStringExpression e = (SimpleStringExpression) expression;
      return "\"" + e.getValue() + "\"";
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(TypeExpression expression) {
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
    } else if (expression instanceof ReferenceTypeExpression) {
      ReferenceTypeExpression e = (ReferenceTypeExpression) expression;
      return e.getVar();
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(MatchReference expression) {
    String tail = "";
    String head = expression.getMatch();
    if (expression.getOp() != null) {
      tail += expression.getOp();
      if (expression.getArg() != null) {
        tail += verbalize(expression.getArg());
      }
    }
    return head + tail;
  }

  public String verbalize(FeatureExpression expression) {
    StringBuilder sb = new StringBuilder();
    if (expression instanceof SimpleFeatureExpression) {
      SimpleFeatureExpression sfe = (SimpleFeatureExpression) expression;
      sb.append(sfe.getMatchReference().getMatch());
    } else {
      sb.append(verbalize(expression.getTypeExpr(null)));
      List<String> list = expression.getFeatureStringList(null);
      if (list != null) {
        for (String string : list) {
          sb.append(".");
          sb.append(string);
        }
      }
    }
    return sb.toString();
  }

  public String verbalize(FeatureMatchExpression expression) {
    return verbalize((FeatureExpression) expression) + expression.getOp()
            + verbalize(expression.getArg());
  }

}
