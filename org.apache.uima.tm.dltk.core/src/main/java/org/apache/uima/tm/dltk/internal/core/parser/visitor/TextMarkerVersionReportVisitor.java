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

package org.apache.uima.tm.dltk.internal.core.parser.visitor;
//package org.apache.uima.tm.dltk.internal.core.parser.visitor;
//
//import java.util.List;
//
//import org.eclipse.dltk.ast.ASTVisitor;
//import org.eclipse.dltk.ast.declarations.MethodDeclaration;
//import org.eclipse.dltk.ast.expressions.Expression;
//import org.eclipse.dltk.ast.statements.Statement;
//
//public class TextMarkerVersionReportVisitor extends ASTVisitor {
//
//	/**
//	 * 1.5.x and all before it.
//	 */
//	public static final int VERSION_15x = 0x0105;
//
//	/**
//	 * Added strict assignments. +=, -=, *=, /=, %=, &= |=, ^=, <<=, >>=, **=
//	 */
//	public static final int VERSION_20x = 0x0200;
//
//	/**
//	 * Added import as expressions.
//	 */
//	public static final int VERSION_21x = 0x0201;
//
//	/**
//	 * Removed ^= Added //= In term added //.
//	 * 
//	 */
//	public static final int VERSION_22x = 0x0202;
//
//	public static final int VERSION_23x = 0x0203;
//
//	/**
//	 * Decorators Added.
//	 */
//	public static final int VERSION_24x = 0x0204;
//
//	private int fCurrentVersion = VERSION_15x;
//
//	private int fProjectVersion = 0;
//
//	/**
//	 * Construct version detect visitor.
//	 * 
//	 * TODO: Add reporting handler here.
//	 */
//	public TextMarkerVersionReportVisitor(int version) {
//		this.fProjectVersion = version;
//	}
//
//	public int getRequredVersion() {
//		return this.fCurrentVersion;
//	}
//
//	public String getRequestedVersionStr() {
//		return this.getVersionStr(this.fCurrentVersion);
//	}
//
//	public String getVersionStr(int version) {
//		return "TextMarker version not determined.";
//	}
//
//	public boolean visit(Expression expression) throws Exception {
//
//		return true;
//	}
//
//	public boolean visit(MethodDeclaration s) throws Exception {
//		List decorators = s.getDecorators();
//		if (decorators != null && decorators.size() > 0) {
//			this.requreVersion(VERSION_24x, s);
//		}
//		return true;
//	}
//
//	private void requreVersion(int version, Statement statement) {
//
//		if (this.fCurrentVersion < version) {
//			this.fCurrentVersion = version;
//		}
//		if (this.fCurrentVersion > this.fProjectVersion) {
////			
//			// TODO:: Add version error setting here.
//		}
//	}
//
//	public boolean visit(Statement statement) throws Exception {
//
//		return true;
//	}
// }
