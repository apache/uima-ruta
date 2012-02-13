package org.apache.uima.textmarker;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DynamicAnchoringTest.class, DynamicAnchoringTest2.class, FilteringTest.class,
    QuantifierTest1.class, QuantifierTest2.class, RuleInferenceTest.class, LongGreedyTest.class })
public class AllTests {

}
