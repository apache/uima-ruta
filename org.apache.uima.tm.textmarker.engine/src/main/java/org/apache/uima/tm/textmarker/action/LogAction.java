package org.apache.uima.tm.textmarker.action;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.uima.tm.textmarker.kernel.TextMarkerStream;
import org.apache.uima.tm.textmarker.kernel.expression.string.StringExpression;
import org.apache.uima.tm.textmarker.kernel.rule.RuleMatch;
import org.apache.uima.tm.textmarker.kernel.rule.TextMarkerRuleElement;
import org.apache.uima.tm.textmarker.kernel.visitor.InferenceCrowd;


public class LogAction extends AbstractTextMarkerAction {

  public static final String LOGGER_NAME = Logger.GLOBAL_LOGGER_NAME;

  private final StringExpression text;

  private final Level level;

  public LogAction(StringExpression text, Level level) {
    super();
    this.text = text;
    this.level = level == null ? Level.INFO : level;
  }

  @Override
  public void execute(RuleMatch match, TextMarkerRuleElement element, TextMarkerStream stream,
          InferenceCrowd crowd) {
    String msg = text.getStringValue(element.getParent());
    Logger.getLogger(LOGGER_NAME).log(level, msg);
  }

  public StringExpression getText() {
    return text;
  }

  public Level getLevel() {
    return level;
  }

}
