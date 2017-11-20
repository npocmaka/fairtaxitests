package tests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.EnterNumberPhotoTest;
import tests.InitialPageTest;

@RunWith(Suite.class)
@Suite
.SuiteClasses({
	InitialPageTest.class,
	EnterNumberPhotoTest.class
})

public class ImageUploadSuite {

}
