package org.apache.uima.tm.dltk.internal.ui.text;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.tm.dltk.ui.text.TextMarkerPartitions;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;


public class TextMarkerPartitionScanner extends RuleBasedPartitionScanner {

  public TextMarkerPartitionScanner() {
    super();

    IToken string = new Token(TextMarkerPartitions.TM_STRING);
    IToken comment = new Token(TextMarkerPartitions.TM_COMMENT);

    List/* < IPredicateRule > */rules = new ArrayList/* <IPredicateRule> */();

    rules.add(new EndOfLineRule("//", comment));

    rules.add(new MultiLineRule("\"\"\"", "\"\"\"", string, '\\'));

    rules.add(new MultiLineRule("\'\'\'", "\'\'\'", string, '\\'));

    rules.add(new MultiLineRule("\'", "\'", string, '\\'));

    rules.add(new MultiLineRule("\"", "\"", string, '\\'));

    IPredicateRule[] result = new IPredicateRule[rules.size()];
    rules.toArray(result);
    setPredicateRules(result);
  }
}