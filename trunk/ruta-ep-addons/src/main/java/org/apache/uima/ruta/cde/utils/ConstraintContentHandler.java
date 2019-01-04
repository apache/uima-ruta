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

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.ruta.cde.IRutaConstraint;
import org.apache.uima.ruta.cde.RutaConstraintFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ConstraintContentHandler extends DefaultHandler {

  private List<ConstraintData> constraints = new ArrayList<ConstraintData>();

  private ConstraintData current = null;

  private String currentString;

  public void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (localName.equals("constraint")) {
      String type = attributes.getValue("type");
      String weight = attributes.getValue("weight");
      IRutaConstraint c = RutaConstraintFactory.createConstraint(type);
      current = new ConstraintData(c);
      current.setWeight(Integer.valueOf(weight));
    } 
  }

  public void characters(char[] ch, int start, int length) {
    currentString = new String(ch, start, length);
  }

  public void endElement(String uri, String localName, String qName) {
    if (localName.equals("constraint")) {
      constraints.add(current);
    } else if (localName.equals("data")) {
      current.getConstraint().setData(currentString);
    } else if (localName.equals("description")) {
      current.getConstraint().setDescription(currentString);
    }
  }

  public List<ConstraintData> getConstraints() {
    return constraints;
  }

}
