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

import org.apache.uima.ruta.condition.AbstractRutaCondition;
import org.apache.uima.ruta.condition.AfterCondition;
import org.apache.uima.ruta.condition.AndCondition;
import org.apache.uima.ruta.condition.BeforeCondition;
import org.apache.uima.ruta.condition.ContainsCondition;
import org.apache.uima.ruta.condition.ContextCountCondition;
import org.apache.uima.ruta.condition.CountCondition;
import org.apache.uima.ruta.condition.CurrentCountCondition;
import org.apache.uima.ruta.condition.EndsWithCondition;
import org.apache.uima.ruta.condition.FeatureCondition;
import org.apache.uima.ruta.condition.IfCondition;
import org.apache.uima.ruta.condition.ImplicitCondition;
import org.apache.uima.ruta.condition.InListCondition;
import org.apache.uima.ruta.condition.IsCondition;
import org.apache.uima.ruta.condition.LastCondition;
import org.apache.uima.ruta.condition.MOfNCondition;
import org.apache.uima.ruta.condition.NearCondition;
import org.apache.uima.ruta.condition.NotCondition;
import org.apache.uima.ruta.condition.OrCondition;
import org.apache.uima.ruta.condition.ParseCondition;
import org.apache.uima.ruta.condition.PartOfCondition;
import org.apache.uima.ruta.condition.PartOfNeqCondition;
import org.apache.uima.ruta.condition.PositionCondition;
import org.apache.uima.ruta.condition.RegExpCondition;
import org.apache.uima.ruta.condition.ScoreCondition;
import org.apache.uima.ruta.condition.SizeCondition;
import org.apache.uima.ruta.condition.StartsWithCondition;
import org.apache.uima.ruta.condition.TotalCountCondition;
import org.apache.uima.ruta.condition.VoteCondition;
import org.apache.uima.ruta.expression.list.ListExpression;
import org.apache.uima.ruta.expression.number.INumberExpression;
import org.apache.uima.ruta.expression.number.SimpleNumberExpression;
import org.apache.uima.ruta.expression.string.IStringExpression;

public class ConditionVerbalizer {

  private RutaVerbalizer verbalizer;

  public ConditionVerbalizer(RutaVerbalizer verbalizer) {
    super();
    this.verbalizer = verbalizer;
  }

  public String verbalizeName(AbstractRutaCondition condition) {
    if (condition instanceof AndCondition) {
      return "AND";
    } else if (condition instanceof ContainsCondition) {
      String name = "CONTAINS";
      return name;
    } else if (condition instanceof ContextCountCondition) {
      String name = "CONTEXTCOUNT";
      return name;
    } else if (condition instanceof CountCondition) {
      String name = "COUNT";
      return name;
    } else if (condition instanceof CurrentCountCondition) {
      String name = "CURRENTCOUNT";
      return name;
    } else if (condition instanceof IfCondition) {
      String name = "IF";
      return name;
    } else if (condition instanceof InListCondition) {
      return "INLIST";
    } else if (condition instanceof LastCondition) {
      return "LAST";
    } else if (condition instanceof MOfNCondition) {
      return "MOFN";
    } else if (condition instanceof NearCondition) {
      String name = "NEAR";
      return name;
    } else if (condition instanceof NotCondition) {
      return "NOT";
    } else if (condition instanceof OrCondition) {
      return "OR";
    } else if (condition instanceof PartOfCondition) {
      return "PARTOF";
    } else if (condition instanceof PartOfNeqCondition) {
      return "PARTOFNEQ";
    } else if (condition instanceof PositionCondition) {
      return "POSITION";
    } else if (condition instanceof RegExpCondition) {
      return "REGEXP";
    } else if (condition instanceof ScoreCondition) {
      String name = "SCORE";
      return name;
    } else if (condition instanceof TotalCountCondition) {
      String name = "TOTALCOUNT";
      return name;
    } else if (condition instanceof VoteCondition) {
      String name = "VOTE";
      return name;
    } else if (condition instanceof FeatureCondition) {
      String name = "FEATURE";
      return name;
    } else if (condition instanceof ParseCondition) {
      String name = "PARSE";
      return name;
    } else if (condition instanceof IsCondition) {
      String name = "IS";
      return name;
    } else if (condition instanceof BeforeCondition) {
      String name = "BEFORE";
      return name;
    } else if (condition instanceof AfterCondition) {
      String name = "AFTER";
      return name;
    } else if (condition instanceof StartsWithCondition) {
      String name = "STARTSWITH";
      return name;
    } else if (condition instanceof EndsWithCondition) {
      String name = "ENDSWITH";
      return name;
    } else if (condition instanceof SizeCondition) {
      String name = "SIZE";
      return name;
    } else if (condition instanceof ImplicitCondition) {
      String name = "";
      return name;
    }

    return condition.getClass().getSimpleName();
  }

  public String verbalize(AbstractRutaCondition condition) {
    String name = verbalizeName(condition) + "(";
    if (condition instanceof AndCondition) {
      AndCondition c = (AndCondition) condition;
      List<AbstractRutaCondition> conditions = c.getConditions();
      StringBuilder sb = new StringBuilder();
      sb.append(name);
      Iterator<AbstractRutaCondition> it = conditions.iterator();
      while (it.hasNext()) {
        AbstractRutaCondition each = (AbstractRutaCondition) it.next();
        sb.append(verbalize(each));
        if (it.hasNext()) {
          sb.append(", ");
        }
      }
      sb.append(")");
      return sb.toString();
    } else if (condition instanceof ContainsCondition) {
      ContainsCondition c = (ContainsCondition) condition;
      INumberExpression minE = c.getMin();
      String min = verbalizeMin(minE, 1);
      INumberExpression maxE = c.getMax();
      String max = verbalizeMax(maxE, Integer.MAX_VALUE);
      String percent = verbalizer.verbalize(c.getPercent());
      percent = !percent.equals("false") ? ", " + percent : "";
      if (c.getType() != null) {
        String type = verbalizer.verbalize(c.getType());
        return name + type + min + max + percent + ")";
      } else {
        return name + verbalizer.verbalize(c.getArgList()) + ", "
                + verbalizer.verbalize(c.getArg()) + min + max + percent + ")";
      }
    } else if (condition instanceof ContextCountCondition) {
      ContextCountCondition c = (ContextCountCondition) condition;
      String type = verbalizer.verbalize(c.getType());
      INumberExpression minE = c.getMin();
      String min = verbalizeMin(minE, Integer.MIN_VALUE);
      INumberExpression maxE = c.getMax();
      String max = verbalizeMax(maxE, Integer.MAX_VALUE);
      String var = c.getVar() == null ? "" : ", " + c.getVar();
      return name + type + min + max + var + ")";
    } else if (condition instanceof CountCondition) {
      CountCondition c = (CountCondition) condition;
      if (c.getArg() == null) {
        String type = verbalizer.verbalize(c.getType());
        INumberExpression minE = c.getMin();
        String min = verbalizeMin(minE, Integer.MIN_VALUE);
        INumberExpression maxE = c.getMax();
        String max = verbalizeMax(maxE, Integer.MAX_VALUE);
        String var = c.getVar() == null ? "" : ", " + c.getVar();
        return name + type + min + max + var + ")";
      } else {
        String list = verbalizer.verbalize(c.getArgList());
        String arg = verbalizer.verbalize(c.getArg());
        INumberExpression minE = c.getMin();
        String min = verbalizeMin(minE, Integer.MIN_VALUE);
        INumberExpression maxE = c.getMax();
        String max = verbalizeMax(maxE, Integer.MAX_VALUE);
        String var = c.getVar() == null ? "" : ", " + c.getVar();
        return name + list + ", " + arg + min + max + var + ")";
      }
    } else if (condition instanceof CurrentCountCondition) {
      CurrentCountCondition c = (CurrentCountCondition) condition;
      String type = verbalizer.verbalize(c.getType());
      INumberExpression minE = c.getMin();
      String min = verbalizeMin(minE, Integer.MIN_VALUE);
      INumberExpression maxE = c.getMax();
      String max = verbalizeMax(maxE, Integer.MAX_VALUE);
      String var = c.getVar() == null ? "" : ", " + c.getVar();
      return name + type + min + max + var + ")";
    } else if (condition instanceof IfCondition) {
      IfCondition c = (IfCondition) condition;
      return name + verbalizer.verbalize(c.getExpression()) + ")";
    } else if (condition instanceof InListCondition) {
      InListCondition c = (InListCondition) condition;
      String list = "";
      if (c.getListExpression() != null) {
        list = verbalizer.verbalize(c.getListExpression());
      } else {
        list = verbalizer.verbalize(c.getStringList());
      }
      IStringExpression a = c.getArg();
      String arg = "";
      if (a != null) {
        arg = ", " + verbalizer.verbalize(a);
      }
      return name + list +arg + ")";
    } else if (condition instanceof LastCondition) {
      LastCondition c = (LastCondition) condition;
      return name + verbalizer.verbalize(c.getType()) + ")";
    } else if (condition instanceof MOfNCondition) {
      MOfNCondition c = (MOfNCondition) condition;
      StringBuilder sb = new StringBuilder();
      sb.append(name);

      INumberExpression minE = c.getMin();
      String min = verbalizeMin(minE, Integer.MIN_VALUE);
      INumberExpression maxE = c.getMax();
      String max = verbalizeMax(maxE, Integer.MAX_VALUE);
      sb.append(min.substring(2, min.length()));
      sb.append(max);
      List<AbstractRutaCondition> conditions = c.getConditions();
      if (!conditions.isEmpty()) {
        sb.append(", ");
      }
      Iterator<AbstractRutaCondition> it = conditions.iterator();
      while (it.hasNext()) {
        AbstractRutaCondition each = (AbstractRutaCondition) it.next();
        sb.append(verbalize(each));
        if (it.hasNext()) {
          sb.append(", ");
        }
      }
      sb.append(")");
      return sb.toString();
    } else if (condition instanceof NearCondition) {
      NearCondition c = (NearCondition) condition;
      String type = verbalizer.verbalize(c.getType());
      String var = verbalizer.verbalize(c.getForward());
      INumberExpression minE = c.getMin();
      String min = verbalizeMin(minE, Integer.MIN_VALUE);
      INumberExpression maxE = c.getMax();
      String max = verbalizeMax(maxE, Integer.MAX_VALUE);
      String filtered = verbalizer.verbalize(c.getFiltered());
      if (!"".equals(filtered)) {
        filtered = ", " + filtered;
      }
      return name + type + min + max + ", " + var + filtered + ")";
    } else if (condition instanceof NotCondition) {
      NotCondition c = (NotCondition) condition;
      List<AbstractRutaCondition> conditions = c.getConditions();
      if (conditions.size() != 1) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        Iterator<AbstractRutaCondition> it = conditions.iterator();
        while (it.hasNext()) {
          AbstractRutaCondition each = (AbstractRutaCondition) it.next();
          sb.append(verbalize(each));
          if (it.hasNext()) {
            sb.append(", ");
          }
        }
        sb.append(")");
        return sb.toString();
      } else {
        return "-" + verbalize(conditions.get(0));
      }
    } else if (condition instanceof OrCondition) {
      OrCondition c = (OrCondition) condition;
      List<AbstractRutaCondition> conditions = c.getConditions();
      StringBuilder sb = new StringBuilder();
      sb.append(name);
      Iterator<AbstractRutaCondition> it = conditions.iterator();
      while (it.hasNext()) {
        AbstractRutaCondition each = (AbstractRutaCondition) it.next();
        sb.append(verbalize(each));
        if (it.hasNext()) {
          sb.append(", ");
        }
      }
      sb.append(")");
      return sb.toString();
    } else if (condition instanceof PartOfCondition) {
      PartOfCondition c = (PartOfCondition) condition;
      if (c.getType() == null) {
        return name + verbalizer.verbalize(c.getList()) + ")";
      } else {
        return name + verbalizer.verbalize(c.getType()) + ")";
      }
    } else if (condition instanceof PartOfNeqCondition) {
      PartOfNeqCondition c = (PartOfNeqCondition) condition;
      if (c.getType() == null) {
        return name + verbalizer.verbalize(c.getList()) + ")";
      } else {
        return name + verbalizer.verbalize(c.getType()) + ")";
      }
    } else if (condition instanceof PositionCondition) {
      PositionCondition c = (PositionCondition) condition;
      String relative = "";
      if (c.getRelative() != null) {
        relative = ", " + verbalizer.verbalize(c.getRelative());
      }
      return name + verbalizer.verbalize(c.getType()) + ", "
              + verbalizer.verbalize(c.getPosition()) + relative + ")";
    } else if (condition instanceof RegExpCondition) {
      RegExpCondition c = (RegExpCondition) condition;
      IStringExpression variable = c.getVariable();
      String ic = verbalizer.verbalize(c.getIgnoreCase());
      if (variable == null) {
        return name + verbalizer.verbalize(c.getPattern()) + ", " + ic + ")";
      } else {
        return name + verbalizer.verbalize(variable) + ", " + verbalizer.verbalize(c.getPattern()) + ", " + ic + ")";
      }
    } else if (condition instanceof ScoreCondition) {
      ScoreCondition c = (ScoreCondition) condition;
      // String type = verbalizer.verbalize(c.getType());
      INumberExpression minE = c.getMin();
      String min = verbalizeMin(minE, Integer.MIN_VALUE);
      INumberExpression maxE = c.getMax();
      String max = verbalizeMax(maxE, Integer.MAX_VALUE);
      String var = c.getVar() == null ? "" : ", " + c.getVar();
      return name + min.substring(2) + max + var + ")";
    } else if (condition instanceof TotalCountCondition) {
      TotalCountCondition c = (TotalCountCondition) condition;
      String type = verbalizer.verbalize(c.getType());
      INumberExpression minE = c.getMin();
      String min = verbalizeMin(minE, Integer.MIN_VALUE);
      INumberExpression maxE = c.getMax();
      String max = verbalizeMax(maxE, Integer.MAX_VALUE);
      String var = c.getVar() == null ? "" : ", " + c.getVar();
      return name + type + min + max + var + ")";
    } else if (condition instanceof VoteCondition) {
      VoteCondition c = (VoteCondition) condition;
      String type1 = verbalizer.verbalize(c.getType1());
      String type2 = verbalizer.verbalize(c.getType2());
      return name + type1 + ", " + type2 + ")";
    } else if (condition instanceof FeatureCondition) {
      FeatureCondition c = (FeatureCondition) condition;
      String e1 = verbalizer.verbalize(c.getFeatureStringExpression());
      String e2 = verbalizer.verbalize(c.getArgExpr());
      return name + e1 + ", " + e2 + ")";
    } else if (condition instanceof ParseCondition) {
      ParseCondition c = (ParseCondition) condition;
      String var = c.getVar();
      return name + var + ")";
    } else if (condition instanceof IsCondition) {
      IsCondition c = (IsCondition) condition;
      String type = "";
      if (c.getType() != null) {
        type = verbalizer.verbalize(c.getType());
      } else {
        type = verbalizer.verbalize(c.getList());
      }
      return name + type + ")";
    } else if (condition instanceof BeforeCondition) {
      BeforeCondition c = (BeforeCondition) condition;
      String type = "";
      if (c.getType() != null) {
        type = verbalizer.verbalize(c.getType());
      } else {
        type = verbalizer.verbalize(c.getList());
      }
      return name + type + ")";
    } else if (condition instanceof AfterCondition) {
      AfterCondition c = (AfterCondition) condition;
      String type = "";
      if (c.getType() != null) {
        type = verbalizer.verbalize(c.getType());
      } else {
        type = verbalizer.verbalize(c.getList());
      }
      return name + type + ")";
    } else if (condition instanceof StartsWithCondition) {
      StartsWithCondition c = (StartsWithCondition) condition;
      String arg = "";
      if (c.getType() != null) {
        arg = verbalizer.verbalize(c.getType());
      } else {
        arg = verbalizer.verbalize(c.getList());
      }
      return name + arg + ")";
    } else if (condition instanceof EndsWithCondition) {
      EndsWithCondition c = (EndsWithCondition) condition;
      String type = "";
      if (c.getType() != null) {
        type = verbalizer.verbalize(c.getType());
      } else {
        type = verbalizer.verbalize(c.getList());
      }
      return name + type + ")";
    } else if (condition instanceof SizeCondition) {
      SizeCondition c = (SizeCondition) condition;
      INumberExpression minE = c.getMinExpr();
      String min = verbalizeMin(minE, Integer.MIN_VALUE);
      INumberExpression maxE = c.getMaxExpr();
      String max = verbalizeMax(maxE, Integer.MAX_VALUE);
      String var = c.getVarExpr() == null ? "" : ", " + c.getVarExpr();
      ListExpression<?> listExpr = c.getListExpr();
      if (listExpr == null) {
        return name + min + max + var + ")";
      } else {
        String l = verbalizer.verbalize(listExpr);
        return name + l + min + max + var + ")";
      }
    } else if (condition instanceof ImplicitCondition) {
      ImplicitCondition c = (ImplicitCondition) condition;
      return verbalizer.verbalize(c.getExpr());
    }

    return condition.getClass().getSimpleName();
  }

  private String verbalizeMax(INumberExpression maxE, int def) {
    String max = "";
    if (!(maxE instanceof SimpleNumberExpression && ((SimpleNumberExpression) maxE).getNumber()
            .equals(def))) {
      max = ", " + verbalizer.verbalize(maxE);
    }
    return max;
  }

  private String verbalizeMin(INumberExpression minE, int def) {
    String min = "";
    if (!(minE instanceof SimpleNumberExpression && ((SimpleNumberExpression) minE).getNumber()
            .equals(def))) {
      min = ", " + verbalizer.verbalize(minE);
    }
    return min;
  }

}
