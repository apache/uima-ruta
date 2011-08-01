package org.apache.uima.tm.textmarker.testing.searchStrategy;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.apache.uima.tm.cev.extension.ICEVSearchStrategyFactory;

public class TestingSearchStrategyFactory implements ICEVSearchStrategyFactory {

  public TestingSearchStrategyFactory() {
  }

  @Override
  public ICEVSearchStrategy createSearchStrategy(int priority) {
    return new TestingSearchStrategy(priority);
  }
}
