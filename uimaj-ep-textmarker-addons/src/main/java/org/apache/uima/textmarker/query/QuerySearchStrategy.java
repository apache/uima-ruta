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

package org.apache.uima.textmarker.query;

import org.apache.uima.cev.extension.ICEVSearchStrategy;
import org.eclipse.core.resources.IFile;


public class QuerySearchStrategy implements ICEVSearchStrategy {

  private int priority;

  public QuerySearchStrategy(int priority) {

    this.priority = priority;
  }

  @Override
  public int getPriority() {
    return priority;
  }

  @Override
  public IFile searchDescriptor(IFile file) {
    // TODO: add strategy
    return null;
  }

}
