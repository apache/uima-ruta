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

public class RutaIndexingConfiguration {

  private String[] indexOnly;

  private String[] indexSkipTypes;

  private boolean indexOnlyMentionedTypes;

  private String[] indexAdditionally;

  private String[] reindexOnly;

  private String[] reindexSkipTypes;

  private boolean reindexOnlyMentionedTypes;

  private String[] reindexAdditionally;

  private ReindexUpdateMode reindexUpdateMode;

  public RutaIndexingConfiguration() {
    super();
  }

  public String[] getIndexSkipTypes() {
    return indexSkipTypes;
  }

  public void setIndexSkipTypes(String[] indexSkipTypes) {
    this.indexSkipTypes = indexSkipTypes;
  }

  public String[] getReindexOnly() {
    return reindexOnly;
  }

  public void setReindexOnly(String[] reindexOnly) {
    this.reindexOnly = reindexOnly;
  }

  public boolean isReindexOnlyMentionedTypes() {
    return reindexOnlyMentionedTypes;
  }

  public void setReindexOnlyMentionedTypes(boolean reindexOnlyMentionedTypes) {
    this.reindexOnlyMentionedTypes = reindexOnlyMentionedTypes;
  }

  public String[] getReindexSkipTypes() {
    return reindexSkipTypes;
  }

  public void setReindexSkipTypes(String[] reindexSkipTypes) {
    this.reindexSkipTypes = reindexSkipTypes;
  }

  public ReindexUpdateMode getReindexUpdateMode() {
    return reindexUpdateMode;
  }

  public void setReindexUpdateMode(ReindexUpdateMode reindexUpdateMode) {
    this.reindexUpdateMode = reindexUpdateMode;
  }

  public String[] getIndexOnly() {
    return indexOnly;
  }

  public void setIndexOnly(String[] indexOnly) {
    this.indexOnly = indexOnly;
  }

  public boolean isIndexOnlyMentionedTypes() {
    return indexOnlyMentionedTypes;
  }

  public void setIndexOnlyMentionedTypes(boolean indexOnlyMentionedTypes) {
    this.indexOnlyMentionedTypes = indexOnlyMentionedTypes;
  }

  public String[] getIndexAdditionally() {
    return indexAdditionally;
  }

  public void setIndexAdditionally(String[] indexAdditionally) {
    this.indexAdditionally = indexAdditionally;
  }

  public String[] getReindexAdditionally() {
    return reindexAdditionally;
  }

  public void setReindexAdditionally(String[] reindexAdditionally) {
    this.reindexAdditionally = reindexAdditionally;
  }

}
