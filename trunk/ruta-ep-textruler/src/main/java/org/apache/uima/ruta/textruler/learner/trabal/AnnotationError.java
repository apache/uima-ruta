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

package org.apache.uima.ruta.textruler.learner.trabal;

import org.apache.uima.ruta.textruler.core.TextRulerExample;

public class AnnotationError implements Comparable<AnnotationError> {
  private TextRulerExample error;

  private TextRulerExample truth;

  private AnnotationErrorType type;

  public AnnotationError(TextRulerExample error, TextRulerExample truth, AnnotationErrorType type) {
    this.error = error;
    this.truth = truth;
    this.type = type;
  }

  public TextRulerExample getError() {
    return error;
  }

  public TextRulerExample getTruth() {
    return truth;
  }

  public AnnotationErrorType getType() {
    return type;
  }

  public TrabalAnnotation getAnnotation() {
    if (error != null)
      return (TrabalAnnotation) error.getAnnotation();
    return null;
  }

  public TrabalAnnotation getTargetAnnotation() {
    if (truth != null)
      return (TrabalAnnotation) truth.getAnnotation();
    return null;
  }

  @Override
  public String toString() {
    if (error != null && truth != null)
      return type + ": " + error.getAnnotation().getType().getShortName() + "("
              + error.getAnnotation().getBegin() + ", " + error.getAnnotation().getEnd() + ") -> "
              + truth.getAnnotation().getType().getShortName() + "("
              + truth.getAnnotation().getBegin() + ", " + truth.getAnnotation().getEnd() + ")";
    if (truth != null)
      return type + ": " + truth.getAnnotation().getType().getShortName() + "("
              + truth.getAnnotation().getBegin() + ", " + truth.getAnnotation().getEnd() + ")";
    return type + ": " + error.getAnnotation().getType().getShortName() + "("
            + error.getAnnotation().getBegin() + ", " + error.getAnnotation().getEnd() + ")";
  }

  public int compareTo(AnnotationError o) {
    if (error != null) {
      if (o.getError() != null) {
        int comp = error.getAnnotation().getType().toString()
                .compareTo(o.getError().getAnnotation().getType().toString());
        if (comp != 0)
          return comp;
      } else
        return 1;
    } else if (o.getError() != null)
      return -1;
    if (truth != null) {
      if (o.getTruth() != null) {
        int comp = truth.getAnnotation().getType().toString()
                .compareTo(o.getTruth().getAnnotation().getType().toString());
        if (comp != 0)
          return comp;
      } else
        return 1;
    } else if (o.getTruth() != null)
      return -1;
    return type.compareTo(o.getType());
  }

}
