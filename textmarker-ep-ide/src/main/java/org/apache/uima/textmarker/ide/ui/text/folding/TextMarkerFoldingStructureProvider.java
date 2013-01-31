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

package org.apache.uima.textmarker.ide.ui.text.folding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.textmarker.ide.TextMarkerIdePlugin;
import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.apache.uima.textmarker.ide.parser.ast.TextMarkerStatement;
import org.apache.uima.textmarker.ide.ui.TextMarkerPartitions;
import org.apache.uima.textmarker.ide.ui.TextMarkerPreferenceConstants;
import org.apache.uima.textmarker.ide.ui.text.TextMarkerPartitionScanner;
import org.eclipse.core.runtime.ILog;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.env.ModuleSource;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;

public class TextMarkerFoldingStructureProvider extends AbstractASTFoldingStructureProvider {

  // ~ Instance fields

  private List fBlockExcludeList = new ArrayList();

  /* preferences */
  private int fBlockFolding = 0;

  private List fBlockIncludeList = new ArrayList();

  private boolean fInitCollapseBlocks = true;

  private boolean fInitCollapseComments = true;

  private boolean fInitCollapseNamespaces = true;

  // ~ Methods

  @Override
  protected CodeBlock[] getCodeBlocks(String code, int offset) {
    /*
     * if an ASTVisitor implementation is created for this, just override getFoldingVisitor() and
     * remove this method
     */
    ISourceParser pp = null;
    pp = DLTKLanguageManager.getSourceParser(TextMarkerNature.NATURE_ID);
    ModuleDeclaration md = (ModuleDeclaration) pp.parse(new ModuleSource(code), null);
    List statements = md.getStatements();
    if (statements == null) {
      return new CodeBlock[0];
    }

    List result = new ArrayList();
    traverse(result, statements, offset, code);

    return (CodeBlock[]) result.toArray(new CodeBlock[result.size()]);
  }

  private void checkStatement(String code, int offset, List result, Statement sst) {
    if (sst instanceof TextMarkerStatement) {
      TextMarkerStatement statement = (TextMarkerStatement) sst;
      result.add(new CodeBlock(statement, new Region(offset + statement.sourceStart(), statement
              .sourceEnd() - statement.sourceStart())));

      Iterator si = statement.getExpressions().iterator();
      // while (si.hasNext()) {
      // Expression ex = (Expression) si.next();
      // if (ex instanceof BlockDeclaration) {
      // BlockDeclaration be = (BlockDeclaration) ex;
      // try {
      // String newContents = code.substring(
      // be.sourceStart() + 1, be.sourceEnd() - 1);
      // CodeBlock[] cb = getCodeBlocks(newContents, offset
      // + be.sourceStart() + 1);
      // for (int j = 0; j < cb.length; j++) {
      // result.add(cb[j]);
      // }
      // } catch (StringIndexOutOfBoundsException e) {
      // }
      // }
      // }
    }
  }

  private void traverse(List result, List statements, int offset, String code) {
    for (Iterator iterator = statements.iterator(); iterator.hasNext();) {
      ASTNode node = (ASTNode) iterator.next();
      if (node instanceof TextMarkerStatement) {
        checkStatement(code, offset, result, (Statement) node);
      } else if (node instanceof TypeDeclaration) {
        TypeDeclaration statement = (TypeDeclaration) node;
        result.add(new CodeBlock(statement, new Region(offset + statement.sourceStart(), statement
                .sourceEnd() - statement.sourceStart())));
        traverse(result, statement.getStatements(), offset, code);
      } else if (node instanceof MethodDeclaration) {
        MethodDeclaration statement = (MethodDeclaration) node;
        result.add(new CodeBlock(statement, new Region(offset + statement.sourceStart(), statement
                .sourceEnd() - statement.sourceStart())));
        traverse(result, statement.getStatements(), offset, code);
      }
    }
  }

  /*
   * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getCommentPartition()
   */
  @Override
  protected String getCommentPartition() {
    return TextMarkerPartitions.TM_COMMENT;
  }

  /*
   * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getLog()
   */
  @Override
  protected ILog getLog() {
    return TextMarkerIdePlugin.getDefault().getLog();
  }

  /*
   * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartition()
   */
  @Override
  protected String getPartition() {
    return TextMarkerPartitions.TM_PARTITIONING;
  }

  /*
   * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartitionScanner()
   */
  @Override
  protected IPartitionTokenScanner getPartitionScanner() {
    return new TextMarkerPartitionScanner();
  }

  /*
   * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getPartitionTypes()
   */
  @Override
  protected String[] getPartitionTypes() {
    return TextMarkerPartitions.TM_PARTITION_TYPES;
  }

  /*
   * @see org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#getNatureId()
   */
  @Override
  protected String getNatureId() {
    return TextMarkerNature.NATURE_ID;
  }

  /*
   * @see
   * org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#initializePreferences(
   * org.eclipse.jface.preference.IPreferenceStore)
   */
  @Override
  protected void initializePreferences(IPreferenceStore store) {
    super.initializePreferences(store);
    fBlockFolding = store.getInt(TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS);

    String t = store.getString(TextMarkerPreferenceConstants.EDITOR_FOLDING_EXCLUDE_LIST);
    String[] items = t.split(",");
    fBlockExcludeList.clear();
    for (int i = 0; i < items.length; i++) {
      if (items[i].trim().length() > 0) {
        fBlockExcludeList.add(items[i]);
      }
    }

    t = store.getString(TextMarkerPreferenceConstants.EDITOR_FOLDING_INCLUDE_LIST);
    items = t.split(",");
    fBlockIncludeList.clear();
    for (int i = 0; i < items.length; i++) {
      if (items[i].trim().length() > 0) {
        fBlockIncludeList.add(items[i]);
      }
    }

    fFoldNewLines = store
            .getBoolean(TextMarkerPreferenceConstants.EDITOR_FOLDING_COMMENTS_WITH_NEWLINES);
    fInitCollapseBlocks = store
            .getBoolean(TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_BLOCKS);
    fInitCollapseComments = store
            .getBoolean(TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_COMMENTS);
    fInitCollapseNamespaces = store
            .getBoolean(TextMarkerPreferenceConstants.EDITOR_FOLDING_INIT_NAMESPACES);
  }

  @Override
  protected boolean initiallyCollapse(ASTNode s, FoldingStructureComputationContext ctx) {
    if (s instanceof TextMarkerStatement) {
      TextMarkerStatement statement = (TextMarkerStatement) s;
      if (!(statement.getAt(0) instanceof SimpleReference)) {
        return false;
      }

      String name = null;
      name = ((SimpleReference) statement.getAt(0)).getName();
      if (name.equals("namespace")) {
        return ctx.allowCollapsing() && fInitCollapseNamespaces;
      }

      return ctx.allowCollapsing() && fInitCollapseBlocks;
    }

    return false;
  }

  /*
   * @see
   * org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#initiallyCollapseComments
   * (org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider.
   * FoldingStructureComputationContext)
   */
  protected boolean initiallyCollapseComments(FoldingStructureComputationContext ctx) {
    return ctx.allowCollapsing() && fInitCollapseComments;
  }

  /*
   * @see
   * org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider#mayCollapse(org.eclipse
   * .dltk.ast.statements.Statement,
   * org.eclipse.dltk.ui.text.folding.AbstractASTFoldingStructureProvider
   * .FoldingStructureComputationContext)
   */
  protected boolean canFold(String name) {
    switch (fBlockFolding) {
      case TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS_OFF: {
        if (name.equals("proc") || name.equals("namespace")) {
          return true;
        }

        return false;
      }
      case TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS_INCLUDE: {
        if (fBlockIncludeList.contains(name)) {
          return true;
        }

        return false;
      }
      case TextMarkerPreferenceConstants.EDITOR_FOLDING_BLOCKS_EXCLUDE: {
        if (fBlockExcludeList.contains(name)) {
          return false;
        }

        return true;
      }
    }
    return false;
  }

  @Override
  protected boolean mayCollapse(ASTNode s, FoldingStructureComputationContext ctx) {
    if (s instanceof TypeDeclaration) {
      return canFold("namespace");
    } else if (s instanceof MethodDeclaration) {
      return canFold("proc");
    } else if (s instanceof TextMarkerStatement) {
      TextMarkerStatement statement = (TextMarkerStatement) s;
      if (!(statement.getAt(0) instanceof SimpleReference)) {
        return false;
      }

      String name = null;
      name = ((SimpleReference) statement.getAt(0)).getName();
      return canFold(name);
    }

    return false;
  }

}
