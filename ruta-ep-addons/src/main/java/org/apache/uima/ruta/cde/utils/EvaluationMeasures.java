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

package org.apache.uima.ruta.cde.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.math3.stat.inference.TestUtils;

public class EvaluationMeasures {

  public static String getMeasureReport(ArrayList<Double[]> results) {
    double[] x = new double[results.size()];
    double[] y = new double[results.size()];
    int index = 0;
    for (Double[] resultPair : results) {
      x[index] = resultPair[0];
      y[index] = resultPair[1];
      index++;
    }
    double mse = meanSquareError(x, y);
    double spearmans = new SpearmansCorrelation().correlation(x, y);
    double pearsons = new PearsonsCorrelation().correlation(x, y);
    double cosine = cosine(x, y);
    mse = round(mse);
    spearmans = round(spearmans);
    pearsons = round(pearsons);
    cosine = round(cosine);

    String report = "mse=" + mse + "  spearmans=" + spearmans + "  pearsons=" + pearsons + "  cosine="
            + cosine;
    return report;
  }

  public static double round(double d) {
    DecimalFormat instance = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
    instance.applyPattern("#.####");
    String format = instance.format(d);
    return Double.valueOf(format);
  }

  public static double cosine(double[] a1, double[] a2) {
    ArrayRealVector v1 = new ArrayRealVector(a1);
    ArrayRealVector v2 = new ArrayRealVector(a2);
    return v1.dotProduct(v2) / (v1.getNorm() * v2.getNorm());
  }

  public static double cosine(ArrayList<Double[]> results) {
    double[] x = new double[results.size()];
    double[] y = new double[results.size()];
    int index = 0;
    for (Double[] resultPair : results) {
      x[index] = resultPair[0];
      y[index] = resultPair[1];
      index++;
    }
    return cosine(x, y);
  }

  public static double meanSquareError(double[] x, double[] y) {
    double sum = 0;
    for (int i = 0; i < x.length; i++) {
      double xi = x[i];
      double yi = y[i];
      double diff = xi - yi;
      sum += (diff * diff);
    }
    return sum / x.length;
  }

}
