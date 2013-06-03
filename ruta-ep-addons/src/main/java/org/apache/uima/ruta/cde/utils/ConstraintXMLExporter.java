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

import org.eclipse.core.runtime.IPath;

@SuppressWarnings("restriction")
public class ConstraintXMLExporter {

  IPath outputPath;

  ArrayList<ConstraintData> constraints;

  public ConstraintXMLExporter(ArrayList<ConstraintData> constraints) {
    this.outputPath = outputPath;
    this.constraints = constraints;
  }

  public void saveConstraints(IPath outputPath) throws Exception {
    
//    IWorkspace workspace = ResourcesPlugin.getWorkspace();
//    IWorkspaceRoot root = workspace.getRoot();
//    String rootPath = root.getRawLocation().toPortableString();
      
//      if(outputPath.getFileExtension() == null) {
//        outputPath = outputPath.addFileExtension("xml");
//        
//        System.out.println("adsf :" + outputPath.toOSString());
//      } else if
//      
//      (!outputPath.getFileExtension().equals("xml")) {
//        outputPath.removeFileExtension();
//        outputPath = outputPath.addFileExtension("xml");
//    }
    
//    String absolutPath = rootPath + System.getProperty("file.separator")  +   outputPath.toOSString();
//    String absolutPath = outputPath.toOSString();
//     File file = new File(absolutPath);
//     
// 
//     file.createNewFile();
//     
//
//     
//    // outputPath = Path.fromOSString("C:/test/test1.xml");
//    // Create a XMLOutputFactory
//    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
//    // Create XMLEventWriter
//    XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(absolutPath));
//    // Create a EventFactory
//    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//    XMLEvent end = eventFactory.createDTD("\n");
//    // Create and write Start Tag
//    StartDocument startDocument = eventFactory.createStartDocument();
//    eventWriter.add(startDocument);
//    StartElement constraintListStartElement = eventFactory.createStartElement("", "",
//            "constraintList");
//    eventWriter.add(constraintListStartElement);
//    eventWriter.add(end);
//
//    for (ConstraintData data : constraints) {
//      String constraintType = "";
//      if (data.getConstraint() instanceof IAugurTMConstraint) {
//        IAugurTMConstraint constraint = (IAugurTMConstraint) data.getConstraint();
//
//        if (constraint instanceof SimpleRutaConstraint) {
//          constraintType = "SimpleRutaConstraint";
//
//        }
//        if (constraint instanceof ListRutaConstraint) {
//          constraintType = "ListRutaConstraint";
//        }
//
//        StartElement constraintStartElement = eventFactory.createStartElement("", "",
//                constraintType);
//        eventWriter.add(constraintStartElement);
//        eventWriter.add(end);
//
//        createNode(eventWriter, "rule", constraint.getRule());
//      }
//      createNode(eventWriter, "description", data.getDescription());
//      createNode(eventWriter, "weight", String.valueOf(data.getWeight()));
//      eventWriter.add(eventFactory.createEndElement("", "", constraintType));
//      eventWriter.add(end);
//    }
//    eventWriter.add(eventFactory.createEndElement("", "", "constraintList"));
//    eventWriter.add(end);
//    eventWriter.add(eventFactory.createEndDocument());
//    eventWriter.close();
  }

//  private void createNode(XMLEventWriter eventWriter, String name, String value)
//          throws XMLStreamException {
//
//    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//    XMLEvent end = eventFactory.createDTD("\n");
//    XMLEvent tab = eventFactory.createDTD("\t");
//    // Create Start node
//    StartElement sElement = eventFactory.createStartElement("", "", name);
//    eventWriter.add(tab);
//    eventWriter.add(sElement);
//    // Create Content
//    Characters characters = eventFactory.createCharacters(value);
//    eventWriter.add(characters);
//    // Create End node
//    EndElement eElement = eventFactory.createEndElement("", "", name);
//    eventWriter.add(eElement);
//    eventWriter.add(end);
//
//  }

}
