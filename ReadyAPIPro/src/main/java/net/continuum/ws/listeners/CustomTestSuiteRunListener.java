package net.continuum.ws.listeners;

import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestSuiteRunContext;
import com.eviware.soapui.model.testsuite.TestSuiteRunListener;
import com.eviware.soapui.model.testsuite.TestSuiteRunner;

/**
 * 
 * @author Vishal
 *
 */
public class CustomTestSuiteRunListener implements TestSuiteRunListener {

	public CustomTestSuiteRunListener() {
	}

	public void beforeRun(TestSuiteRunner arg0, TestSuiteRunContext arg1) {
		System.out.println("BEFORE SUITE");
	}

	public void beforeTestCase(TestSuiteRunner arg0, TestSuiteRunContext arg1, TestCase arg2) {
		System.out.println("BEFORE TEST CASE");
	}

	public void afterTestCase(TestSuiteRunner runner, TestSuiteRunContext context, TestCaseRunner tcRunner) {
		System.out.println("AFTER TEST CASE");
	}

	public void afterRun(TestSuiteRunner runner, TestSuiteRunContext context) {
		System.out.println("AFTER SUITE");
	}
}
