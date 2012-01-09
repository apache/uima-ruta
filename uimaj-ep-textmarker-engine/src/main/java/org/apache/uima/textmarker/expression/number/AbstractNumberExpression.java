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

package org.apache.uima.textmarker.expression.number;


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

  protected float calculate(float t1, float t2, String op) {
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
      return (float)Math.exp(t1);
    } else if ("LOG".equals(op)) {
      return (float)Math.log(t1);
    } else if ("SIN".equals(op)) {
      return (float)Math.sin(t1);
    } else if ("COS".equals(op)) {
      return (float)Math.cos(t1);
    } else if ("TAN".equals(op)) {
      return (float)Math.tan(t1);
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
