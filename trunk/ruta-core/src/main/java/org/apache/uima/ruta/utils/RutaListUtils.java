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

package org.apache.uima.ruta.utils;

import java.util.List;

public class RutaListUtils {

  public static int[] toIntArray(List<Number> list) {
    int[] result = new int[list.size()];
    int index = 0;
    for (Number number : list) {
      result[index] = number.intValue();
      index++;
    }
    return result;
  }
  
  public static double[] toDoubleArray(List<Number> list) {
    double[] result = new double[list.size()];
    int index = 0;
    for (Number number : list) {
      result[index] = number.doubleValue();
      index++;
    }
    return result;
  }
  
  public static float[] toFloatArray(List<Number> list) {
    float[] result = new float[list.size()];
    int index = 0;
    for (Number number : list) {
      result[index] = number.floatValue();
      index++;
    }
    return result;
  }
  
}
