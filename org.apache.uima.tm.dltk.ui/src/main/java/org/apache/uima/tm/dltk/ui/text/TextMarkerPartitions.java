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

package org.apache.uima.tm.dltk.ui.text;

import org.apache.uima.tm.dltk.core.TextMarkerConstants;
import org.eclipse.jface.text.IDocument;


public interface TextMarkerPartitions {

  public final static String TM_PARTITIONING = TextMarkerConstants.TM_PARTITIONING;

  public final static String TM_COMMENT = "__textmarker_comment";

  public final static String TM_STRING = "__textmarker_string";

  public static final String TM_INNER_CODE = "__tm_inner_code";

  public final static String[] TM_PARTITION_TYPES = new String[] { TextMarkerPartitions.TM_STRING,
      TextMarkerPartitions.TM_COMMENT, IDocument.DEFAULT_CONTENT_TYPE };
}
