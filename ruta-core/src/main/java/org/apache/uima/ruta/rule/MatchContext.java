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
package org.apache.uima.ruta.rule;

import org.apache.uima.cas.text.AnnotationFS;

/**
 * This is a generic container class for contextual information during rule matching
 *
 */
public class MatchContext {

	private AnnotationFS annotation;
	private RuleElement element;
	private RuleMatch ruleMatch;
	private boolean direction;

	/**
	 * Container object of the current context during matching
	 * 
	 * @param annotation
	 * @param element
	 * @param ruleMatch
	 * @param direction, true: left to right, false, right to left
	 */
	public MatchContext(AnnotationFS annotation, RuleElement element, RuleMatch ruleMatch, boolean direction) {
		super();
		this.annotation = annotation;
		this.element = element;
		this.ruleMatch = ruleMatch;
		this.direction = direction;
	}
	
	/**
	 * Container object of the current context during matching
	 * 
	 * @param element
	 * @param ruleMatch
	 * @param direction, true: left to right, false, right to left
	 */
	public MatchContext(RuleElement element, RuleMatch ruleMatch, boolean direction) {
		this(null, element, ruleMatch, direction);
	}
	
	/**
	* Container object of the current context during matching
	* 
	 * @param element
	 * @param ruleMatch
	 */
	public MatchContext(AbstractRuleElement element,
			RuleMatch ruleMatch) {
		this(element, ruleMatch, true);
	}

	public AnnotationFS getAnnotation() {
		if(annotation!= null) {
			return annotation;
		} else {
			return null;
		}
	}

	public void setAnnotation(AnnotationFS annotation) {
		this.annotation = annotation;
	}

	public RuleElement getElement() {
		return element;
	}

	public void setElement(RuleElement element) {
		this.element = element;
	}

	public RuleMatch getRuleMatch() {
		return ruleMatch;
	}

	public void setRuleMatch(RuleMatch ruleMatch) {
		this.ruleMatch = ruleMatch;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	
}
