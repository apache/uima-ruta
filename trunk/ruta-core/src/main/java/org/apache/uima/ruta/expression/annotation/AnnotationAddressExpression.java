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

package org.apache.uima.ruta.expression.annotation;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.LowLevelCAS;
import org.apache.uima.cas.text.AnnotationFS;
import org.apache.uima.ruta.RutaStream;
import org.apache.uima.ruta.rule.MatchContext;


/**
 *  An expression referring to an annotation identified by its address.  
 *
 */
public class AnnotationAddressExpression extends AbstractAnnotationExpression {

  private String address;
  
  public AnnotationAddressExpression(String address) {
    super();
    this.address = address;
  }
  
  
  @Override
  public AnnotationFS getAnnotation(MatchContext context, RutaStream stream) {
    Integer ref = Integer.valueOf(address);
    CAS cas = stream.getCas();
    if(cas instanceof CASImpl) {
      CASImpl casImpl = (CASImpl) cas;
      LowLevelCAS lowLevelCAS = casImpl.getLowLevelCAS();
      FeatureStructure fs = lowLevelCAS.ll_getFSForRef(ref);
      if(fs instanceof AnnotationFS) {
        return (AnnotationFS) fs;
      }
    }
    return null;
  }


  public String getAddress() {
    return address;
  }
  

}
