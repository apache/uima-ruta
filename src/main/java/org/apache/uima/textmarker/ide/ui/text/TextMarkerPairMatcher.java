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

package org.apache.uima.textmarker.ide.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.textmarker.ide.core.TextMarkerNature;
import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.expressions.StringLiteral;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.core.DLTKCore;
import org.eclipse.dltk.core.DLTKLanguageManager;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.SourceParserUtil;
import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension4;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.ICharacterPairMatcher;


/**
 * Helper class for match pairs of characters.
 */
public final class TextMarkerPairMatcher implements ICharacterPairMatcher {

  // private char[] fPairs;

  private IDocument fDocument;

  private int fOffset;

  private int fStartPos;

  private int fEndPos;

  private int fAnchor;

  private ScriptEditor editor;

  private class PairBlock {
    public PairBlock(int start, int end, char c) {
      this.start = start;
      this.end = end;
      this.c = c;
    }

    int start;

    int end;

    char c;
  }

  private PairBlock[] cachedPairs;

  private long cachedStamp = -1;

  private long cachedHash = -1;

  public TextMarkerPairMatcher(char[] pairs, ScriptEditor editor) {
    // if (pairs == null) {
    // throw new IllegalArgumentException();
    // }

    // fPairs = pairs;
    this.editor = editor;
  }

  private PairBlock[] computePairRanges(final int offset, String contents) {
    ISourceParser pp = null;
    pp = DLTKLanguageManager.getSourceParser(TextMarkerNature.NATURE_ID);
    ModuleDeclaration md = null;// pp.parse(null, contents.toCharArray(),
    // null);
    IModelElement el = this.editor.getInputModelElement();
    if (el != null && el instanceof ISourceModule) {
      md = SourceParserUtil.getModuleDeclaration((ISourceModule) el, null);
    }
    if (md == null) {
      md = pp.parse(null, contents.toCharArray(), null);
    }
    if (md == null) {
      return new PairBlock[0];
    }
    final List result = new ArrayList();
    try {
      md.traverse(new ASTVisitor() {
        @Override
        public boolean visitGeneral(ASTNode node) throws Exception {
          if (node instanceof StringLiteral) {
            StringLiteral be = (StringLiteral) node;
            result.add(new PairBlock(offset + be.sourceStart(), offset + be.sourceEnd() - 1, '\"'));
            // } else if (node instanceof TextMarkerExecuteExpression) {
            // TextMarkerExecuteExpression be = (TextMarkerExecuteExpression) node;
            // result.add(new PairBlock(offset + be.sourceStart(),
            // offset + be.sourceEnd() - 1, '['));
          } else if (node instanceof Block) {
            Block be = (Block) node;
            result.add(new PairBlock(offset + be.sourceStart(), offset + be.sourceEnd() - 1, '{'));
          }
          return super.visitGeneral(node);
        }
      });
    } catch (Exception e) {
      if (DLTKCore.DEBUG) {
        e.printStackTrace();
      }
    }

    // Iterator i = statements.iterator();
    // while (i.hasNext()) {
    // Statement sst = (Statement) i.next();
    // if (sst instanceof TextMarkerStatement) {
    // TextMarkerStatement statement = (TextMarkerStatement) sst;
    // /*
    // * result.add(new CodeBlock(statement, new Region(offset +
    // * statement.sourceStart(), statement.sourceEnd() -
    // * statement.sourceStart())));
    // */
    // Iterator si = statement.getExpressions().iterator();
    // while (si.hasNext()) {
    // Expression ex = (Expression) si.next();
    // if (ex instanceof TextMarkerBlockExpression) {
    // TextMarkerBlockExpression be = (TextMarkerBlockExpression) ex;
    // try {
    // String newContents = contents.substring(be
    // .sourceStart() + 1, be.sourceEnd() - 1);
    // result.add(new PairBlock(offset + be.sourceStart(),
    // offset + be.sourceEnd() - 1, '{'));
    // PairBlock[] cb = computePairRanges(offset
    // + be.sourceStart() + 1, newContents);
    // for (int j = 0; j < cb.length; j++) {
    // result.add(cb[j]);
    // }
    // } catch (StringIndexOutOfBoundsException e) {
    // }
    // } else if (ex instanceof StringLiteral) {
    // StringLiteral be = (StringLiteral) ex;
    // result.add(new PairBlock(offset + be.sourceStart(),
    // offset + be.sourceEnd() - 1, '\"'));
    // } else if (ex instanceof TextMarkerExecuteExpression) {
    // TextMarkerExecuteExpression be = (TextMarkerExecuteExpression) ex;
    // result.add(new PairBlock(offset + be.sourceStart(),
    // offset + be.sourceEnd() - 1, '['));
    // }
    // }
    // }
    // }
    return (PairBlock[]) result.toArray(new PairBlock[result.size()]);
  }

  /**
   * Fully recalcs pairs for document
   * 
   * @param doc
   * @throws BadLocationException
   */
  private void recalc() throws BadLocationException {
    String content = fDocument.get(0, fDocument.getLength());
    cachedPairs = computePairRanges(0, content);

    if (fDocument instanceof IDocumentExtension4) {
      cachedStamp = ((IDocumentExtension4) fDocument).getModificationStamp();
    } else {
      cachedHash = content.hashCode();
    }
  }

  /**
   * Recalcs pairs for the document, only if it is required
   */
  private void updatePairs() throws BadLocationException {
    if (fDocument instanceof IDocumentExtension4) {
      IDocumentExtension4 document = (IDocumentExtension4) fDocument;

      if (document.getModificationStamp() == cachedStamp) {
        return;
      }

    } else {
      String content = fDocument.get(0, fDocument.getLength());

      if (content.hashCode() == cachedHash) {
        return;
      }
    }

    recalc();
  }

  private static boolean isBrace(char c) {
    return (c == '{' || c == '}' || c == '\"' || c == '[' || c == ']');
  }

  public IRegion match(IDocument document, int offset) {
    if (document == null || offset < 0) {
      throw new IllegalArgumentException();
    }

    try {
      fOffset = offset;
      fDocument = document;

      if (!isBrace(fDocument.getChar(offset))
              && (offset == 0 || !isBrace(fDocument.getChar(offset - 1)))) {
        return null;
      }

      updatePairs();

      if (matchPairsAt() && fStartPos != fEndPos)
        return new Region(fStartPos, fEndPos - fStartPos + 1);
    } catch (BadLocationException e) {
      if (DLTKCore.DEBUG_PARSER)
        e.printStackTrace();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.text.source.ICharacterPairMatcher#getAnchor()
   */
  public int getAnchor() {
    return fAnchor;
  }

  public void dispose() {
    clear();
    fDocument = null;
  }

  public void clear() {
  }

  private boolean matchPairsAt() {

    fStartPos = -1;
    fEndPos = -1;

    for (int i = 0; i < cachedPairs.length; i++) {
      PairBlock block = cachedPairs[i];

      if (fOffset == block.end + 1) {
        fStartPos = block.start - 1;
        fEndPos = block.start;
        fAnchor = LEFT;
        return true;
      }
      if (fOffset == block.start + 1) {
        fStartPos = block.end - 1;
        fEndPos = block.end;
        fAnchor = LEFT;
        return true;
      }

    }

    return false;
  }
}
