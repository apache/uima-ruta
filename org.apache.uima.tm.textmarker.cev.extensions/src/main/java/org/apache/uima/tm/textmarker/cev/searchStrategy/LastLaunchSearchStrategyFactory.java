package org.apache.uima.tm.textmarker.cev.searchStrategy;

import org.apache.uima.tm.cev.extension.ICEVSearchStrategy;
import org.apache.uima.tm.cev.extension.ICEVSearchStrategyFactory;

public class LastLaunchSearchStrategyFactory implements ICEVSearchStrategyFactory {

  public ICEVSearchStrategy createSearchStrategy(int priority) {
    return new LastLaunchSearchStrategy(priority);
  }

}
