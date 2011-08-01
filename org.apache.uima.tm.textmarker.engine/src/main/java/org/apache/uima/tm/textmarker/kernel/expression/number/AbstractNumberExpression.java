package org.apache.uima.tm.textmarker.kernel.expression.number;


public abstract class AbstractNumberExpression extends NumberExpression {

  protected double calculate(double t1, double t2, String op) {
    if ("+".equals(op)) {
      return t1 + t2;
    } else if ("-".equals(op)) {
      return t1 - t2;
    } else if ("*".equals(op)) {
      return t1 * t2;
    } else if ("/".equals(op)) {
      return t1 / t2;
    } else if ("%".equals(op)) {
      return t1 % t2;
    } else if ("EXP".equals(op)) {
      return Math.exp(t1);
    } else if ("LOG".equals(op)) {
      return Math.log(t1);
    } else if ("SIN".equals(op)) {
      return Math.sin(t1);
    } else if ("COS".equals(op)) {
      return Math.cos(t1);
    } else if ("TAN".equals(op)) {
      return Math.tan(t1);
    }
    return 0;
  }

  protected int calculate(int t1, int t2, String op) {
    if ("+".equals(op)) {
      return t1 + t2;
    } else if ("-".equals(op)) {
      return t1 - t2;
    } else if ("*".equals(op)) {
      return t1 * t2;
    } else if ("/".equals(op)) {
      return t1 / t2;
    } else if ("%".equals(op)) {
      return t1 % t2;
    } else if ("EXP".equals(op)) {
      return (int) Math.exp(t1);
    } else if ("LOG".equals(op)) {
      return (int) Math.log(t1);
    } else if ("SIN".equals(op)) {
      return (int) Math.sin(t1);
    } else if ("COS".equals(op)) {
      return (int) Math.cos(t1);
    } else if ("TAN".equals(op)) {
      return (int) Math.tan(t1);
    }
    return 0;
  }

}
