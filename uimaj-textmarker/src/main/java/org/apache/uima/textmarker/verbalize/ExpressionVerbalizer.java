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

package org.apache.uima.textmarker.verbalize;

import java.util.Iterator;

import org.apache.uima.textmarker.expression.TextMarkerExpression;
import org.apache.uima.textmarker.expression.bool.BooleanExpression;
import org.apache.uima.textmarker.expression.bool.BooleanNumberExpression;
import org.apache.uima.textmarker.expression.bool.BooleanTypeExpression;
import org.apache.uima.textmarker.expression.bool.ReferenceBooleanExpression;
import org.apache.uima.textmarker.expression.bool.SimpleBooleanExpression;
import org.apache.uima.textmarker.expression.list.ListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceBooleanListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceNumberListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceStringListExpression;
import org.apache.uima.textmarker.expression.list.ReferenceTypeListExpression;
import org.apache.uima.textmarker.expression.list.SimpleBooleanListExpression;
import org.apache.uima.textmarker.expression.list.SimpleNumberListExpression;
import org.apache.uima.textmarker.expression.list.SimpleStringListExpression;
import org.apache.uima.textmarker.expression.list.SimpleTypeListExpression;
import org.apache.uima.textmarker.expression.number.ComposedNumberExpression;
import org.apache.uima.textmarker.expression.number.NegativeNumberExpression;
import org.apache.uima.textmarker.expression.number.NumberExpression;
import org.apache.uima.textmarker.expression.number.ReferenceNumberExpression;
import org.apache.uima.textmarker.expression.number.SimpleNumberExpression;
import org.apache.uima.textmarker.expression.resource.ReferenceWordListExpression;
import org.apache.uima.textmarker.expression.resource.ReferenceWordTableExpression;
import org.apache.uima.textmarker.expression.resource.WordListExpression;
import org.apache.uima.textmarker.expression.resource.WordTableExpression;
import org.apache.uima.textmarker.expression.string.ComposedStringExpression;
import org.apache.uima.textmarker.expression.string.LiteralStringExpression;
import org.apache.uima.textmarker.expression.string.ReferenceStringExpression;
import org.apache.uima.textmarker.expression.string.SimpleStringExpression;
import org.apache.uima.textmarker.expression.string.StringExpression;
import org.apache.uima.textmarker.expression.type.ReferenceTypeExpression;
import org.apache.uima.textmarker.expression.type.SimpleTypeExpression;
import org.apache.uima.textmarker.expression.type.TypeExpression;

public class ExpressionVerbalizer {

  private TextMarkerVerbalizer verbalizer;

  public ExpressionVerbalizer(TextMarkerVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalize(TextMarkerExpression expression) {
    if (expression instanceof TypeExpression) {
      return verbalize((TypeExpression) expression);
    } else if (expression instanceof BooleanExpression) {
      return verbalize((BooleanExpression) expression);
    } else if (expression instanceof NumberExpression) {
      return verbalize((NumberExpression) expression);
    } else if (expression instanceof WordListExpression) {
      return verbalize((WordListExpression) expression);
    } else if (expression instanceof WordTableExpression) {
      return verbalize((WordTableExpression) expression);
    } else if (expression instanceof ListExpression<?>) {
      return verbalize((ListExpression<?>) expression);
    } else if (expression instanceof StringExpression) {
      return verbalize((StringExpression) expression);
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
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(NumberExpression expression) {
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
      NumberExpression ne = e.getExpressions().get(0);
      if (ne == null) {
        return "";
      }
      StringBuilder result = new StringBuilder(verbalize(ne));
      for (int i = 0; i < e.getOperators().size(); i++) {
        result.append(e.getOperators().get(i));
        if (e.getExpressions().size() > i + 1) {
          result.append(verbalize(e.getExpressions().get(i + 1)));
        }
      }
      return result.toString();
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(BooleanExpression expression) {
    if (expression == null) {
      return "";
    } else if (expression instanceof BooleanNumberExpression) {
      BooleanNumberExpression e = (BooleanNumberExpression) expression;
      return verbalize(e.getFristExpression()) + e.getOperator()
              + verbalize(e.getSecondExpression());
    } else if (expression instanceof BooleanTypeExpression) {
      BooleanTypeExpression e = (BooleanTypeExpression) expression;
      return verbalize(e.getFristExpression()) + e.getOperator()
              + verbalize(e.getSecondExpression());
    } else if (expression instanceof ReferenceBooleanExpression) {
      ReferenceBooleanExpression e = (ReferenceBooleanExpression) expression;
      return e.getVar();
    } else if (expression instanceof SimpleBooleanExpression) {
      SimpleBooleanExpression e = (SimpleBooleanExpression) expression;
      return e.getPrimitiveValue() ? "true" : "false";
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(StringExpression expression) {
    if (expression == null) {
      return "";
    } else if (expression instanceof NumberExpression) {
      return verbalize((NumberExpression) expression);
    } else if (expression instanceof TypeExpression) {
      return verbalize((TypeExpression) expression);
    } else if (expression instanceof BooleanExpression) {
      return verbalize((BooleanExpression) expression);
    } else if (expression instanceof ListExpression) {
      return verbalize((ListExpression) expression);
    } else if (expression instanceof LiteralStringExpression) {
      return verbalize((LiteralStringExpression) expression);
    }
    return expression.getClass().getSimpleName();
  }

  public String verbalize(LiteralStringExpression expression) {
    if (expression == null) {
      return "";
    } else if (expression instanceof ComposedStringExpression) {
      ComposedStringExpression e = (ComposedStringExpression) expression;
      StringBuilder sb = new StringBuilder();
      Iterator<StringExpression> it = e.getExpressions().iterator();
      while (it.hasNext()) {
        StringExpression each = it.next();
        sb.append(verbalize(each));
        if (it.hasNext()) {
          sb.append("+");
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

}
