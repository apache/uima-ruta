package org.apache.uima.textmarker;

import org.apache.uima.textmarker.condition.CurrentCountTest;
import org.apache.uima.textmarker.condition.PartOfTest;
import org.apache.uima.textmarker.condition.PositionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DynamicAnchoringTest.class, DynamicAnchoringTest2.class, FilteringTest.class,
    LiteralStringMatchTest.class, LongGreedyTest.class, OutOfWindowTest.class,
    QuantifierTest1.class, QuantifierTest2.class, RuleInferenceTest.class,
    RuleInferenceTest2.class, RuleInferenceTest3.class, AllActionsTest.class,
    AllConditionsTest.class, CurrentCountTest.class,
    PartOfTest.class, PositionTest.class })
public class AllTests {

}
