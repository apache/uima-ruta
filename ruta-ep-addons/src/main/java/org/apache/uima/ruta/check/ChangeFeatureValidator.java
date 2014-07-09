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

package org.apache.uima.ruta.check;

import org.apache.uima.cas.Type;
import org.eclipse.jface.dialogs.IInputValidator;

public class ChangeFeatureValidator implements IInputValidator {

  private Type range;

  public ChangeFeatureValidator(Type range) {
    this.range = range;
  }

  @Override
  public String isValid(String input) {
    String rangeType = range.getShortName();
    if (input != null && !input.equalsIgnoreCase("")) {
      if (rangeType.equals("String")) {
        return null;
      }
      if (rangeType.equals("Integer")) {
        try {
          Integer.parseInt(input);
          return null;
        } catch (NumberFormatException e) {
          return "Input must be an integer value!";
        }
      }
      if (rangeType.equals("Float")) {
        try {
          Float.parseFloat(input);
          return null;
        } catch (NumberFormatException e) {
          return "Input must be a floating point value!";
        }
      }
      if (rangeType.equals("Double")) {
        try {
          Double.parseDouble(input);
          return null;
        } catch (NumberFormatException e) {
          return "Input must be a double value!";
        }
      }
    }
    return null;
  }

}
