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
package org.apache.uima.ruta;

/**
 * The mode how the internal RutaBasics are updated. The update depends on the given relevant types
 * and annotations which can be specified using various configuration parameters.
 *
 */
public enum ReindexUpdateMode {

  /**
   * Updates the internal information completely. First all internal indexing information is removed
   * for all relevant types. Then, all relevant annotations are added anew.
   */
  COMPLETE,

  /**
   * Updates the internal information additively. The relevant annotations are checked if they are
   * already registers in the internal indexing. If not, then they are added. This mode does not
   * ensure a valid internal indexing as it can miss modification by previous analysis engines (in
   * between two RutaEngines)
   */
  ADDITIVE,

  /**
   * This mode compares the internal indexing information with the annotation indexes and removes
   * relevant annotations that are no longer in the annotation indexes. Then, the ADDITIVE mode is
   * applied. This mode does not ensure a valid internal indexing as it can miss modification
   * concerning the offsets of an annotation.
   */
  SAFE_ADDITIVE,

  /**
   * This mode does not update the internal indexing information.
   */
  NONE;

}
