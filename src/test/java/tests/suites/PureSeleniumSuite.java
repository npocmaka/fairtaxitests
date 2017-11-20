package tests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.Suite;


import tests.EnterNumberTest;
import tests.EstimateMapTest;
import tests.EstimateTrackTest;
import tests.TrackingTest;

@RunWith(Suite.class)
@Suite
.SuiteClasses({
	EnterNumberTest.class,
	EstimateMapTest.class,
	EstimateTrackTest.class,
	TrackingTest.class,
	EstimateMapTest.class
	
})

public class PureSeleniumSuite {

}
