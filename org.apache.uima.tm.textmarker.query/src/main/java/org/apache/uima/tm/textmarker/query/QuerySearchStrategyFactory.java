package org.apache.uima.tm.textmarker.query;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.apache.uima.tm.cev.extension.ICEVSearchStrategyFactory;

public class QuerySearchStrategyFactory implements ICEVSearchStrategyFactory {

  public QuerySearchStrategyFactory() {
    super();
  }

  @Override
  public ICEVSearchStrategy createSearchStrategy(int priority) {
    return new QuerySearchStrategy(priority);
  }

}
