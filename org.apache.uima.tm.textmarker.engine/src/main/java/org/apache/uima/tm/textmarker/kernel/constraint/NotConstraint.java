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

package org.apache.uima.tm.textmarker.kernel.constraint;

import org.apache.uima.cas.FSMatchConstraint;
import org.apache.uima.cas.FeatureStructure;

public class NotConstraint implements FSMatchConstraint {
  private static final long serialVersionUID = 1115953538613617791L;

  private final FSMatchConstraint constraint;

  public NotConstraint(FSMatchConstraint constraint) {
    super();
    this.constraint = constraint;
  }

  public boolean match(FeatureStructure fs) {
    return !constraint.match(fs);
  }

  @Override
  public String toString() {
    return "NOT " + constraint.toString();
  }
}
