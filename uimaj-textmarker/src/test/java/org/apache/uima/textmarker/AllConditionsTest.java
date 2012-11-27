package org.apache.uima.textmarker;

import org.apache.uima.textmarker.condition.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AfterTest.class, AndTest.class, BeforeTest.class,
  ContainsTest.class, ContextCountTest.class, CountTest.class,
  CurrentCountTest.class, EndsWithTest.class, FeatureTest.class,
  IfTest.class, InListTest.class, IsTest.class,
  LastTest.class, MOfNTest.class, NearTest.class,
  NotTest.class, OrTest.class, ParseTest.class,
  PartOfNEQTest.class, PartOfTest.class, PositionTest.class,
  RegExpTest.class, ScoreTest.class, SizeTest.class,
  StartsWithTest.class, TotalCountTest.class, VoteTest.class,})
public class AllConditionsTest {

}
