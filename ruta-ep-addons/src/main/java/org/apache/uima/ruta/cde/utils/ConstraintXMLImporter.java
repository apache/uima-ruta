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

import org.apache.uima.ruta.cde.RutaConstraintFactory;
import org.eclipse.core.runtime.IPath;

@SuppressWarnings({ "unchecked", "null", "restriction" })
public class ConstraintXMLImporter {

  ArrayList<ConstraintData> constraints;

  IPath inputPath;

  RutaConstraintFactory factory;

  // IAugurConstraint constraint;

  public ConstraintXMLImporter() {
    super();
    this.factory = new RutaConstraintFactory();
  }

  public ArrayList<ConstraintData> readXML(IPath inputPath) {
//    System.out.println(inputPath.toOSString());
    this.inputPath = inputPath;
    constraints = new ArrayList<ConstraintData>();
    
//    try {
//      // First create a new XMLInputFactory
//      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//      // Setup a new eventReader
//      InputStream in = new FileInputStream(inputPath.toOSString());
//      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
//      // Read the XML document
//      IAugurTMConstraint constraint = null;
//      ConstraintData data = null;
//      while (eventReader.hasNext()) {
//        XMLEvent event = eventReader.nextEvent();
//
//        if (event.isStartElement()) {
////          System.out.println(event.asStartElement().getName().getLocalPart());
//          if (event.asStartElement().getName().getLocalPart().equals("SimpleRutaConstraint")) {
//            StartElement startElement = event.asStartElement();
//            constraint = factory.createConstraint(startElement.getName().getLocalPart());
//            data = new ConstraintData(constraint);
//            constraints.add(data);  
//          }
//        }
//        
//        if (event.isStartElement()) {
//        if (event.asStartElement().getName().getLocalPart().equals("ListRutaConstraint")) {
//          StartElement startElement = event.asStartElement();
//          constraint = factory.createConstraint(startElement.getName().getLocalPart());
//          data = new ConstraintData(constraint);
//          constraints.add(data);  
//        }
//      }
//
//        if(event.isStartElement()) {
//          if(event.asStartElement().getName().getLocalPart().equals("rule")) {
//            event = eventReader.nextEvent();
//            constraint.setRule(event.asCharacters().getData());
//            continue;
//          }
//        }
//        
//        if(event.isStartElement()) {
//          if(event.asStartElement().getName().getLocalPart().equals("description")) {
//            event = eventReader.nextEvent();
//            constraint.setDescription(event.asCharacters().getData());
//            continue;
//          }
//        }
//        
//        if(event.isStartElement()) {
//          if(event.asStartElement().getName().getLocalPart().equals("weight")) {
//            event = eventReader.nextEvent();
//            data.setWeight(Integer.valueOf(event.asCharacters().getData()));
//            continue;
//          }
//        }    
//      }   
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
    return constraints;
  }
}
