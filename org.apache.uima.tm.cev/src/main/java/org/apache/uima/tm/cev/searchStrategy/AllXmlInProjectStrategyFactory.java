package org.apache.uima.tm.cev.searchStrategy;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.apache.uima.tm.cev.extension.ICEVSearchStrategyFactory;

public class AllXmlInProjectStrategyFactory implements ICEVSearchStrategyFactory {

  public ICEVSearchStrategy createSearchStrategy(int priority) {
    return new AllXmlInProjectStrategy(priority);
  }

}
