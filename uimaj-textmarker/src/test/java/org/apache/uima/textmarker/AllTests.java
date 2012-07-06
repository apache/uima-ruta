package org.apache.uima.textmarker;

import org.apache.uima.textmarker.action.PartOfTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DynamicAnchoringTest.class, DynamicAnchoringTest2.class, FilteringTest.class,
    QuantifierTest1.class, QuantifierTest2.class, RuleInferenceTest.class,
    RuleInferenceTest2.class, RuleInferenceTest3.class, LongGreedyTest.class, PartOfTest.class })
public class AllTests {

}
