package net.continuum.ws.listeners;

import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunListener;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestStepResult;

/**
 * 
 * @author Vishal
 *
 */
public class CustomTestRunListener implements TestRunListener {

	public void beforeRun(TestCaseRunner tcRunner, TestCaseRunContext tcRunContext) {
		System.out.println("BEFORE TEST RUN: " + tcRunner.getTestCase().getName());
	}

	public void beforeStep(TestCaseRunner tcRunner, TestCaseRunContext tcRunContext) {
	}

	public void beforeStep(TestCaseRunner tcRunner, TestCaseRunContext tcRunContext, TestStep testStep) {
		System.out.println("BEFORE TEST STEP: " + testStep.getName());
	}

	public void afterStep(TestCaseRunner tcRunner, TestCaseRunContext tcRunContext, TestStepResult testStepResult) {
		System.out.println("AFTER TEST STEP: " + testStepResult.getStatus());
		for (int i = 0; i < testStepResult.getMessages().length; i++) {
			System.out.println(i + ". " + testStepResult.getMessages()[i]);
		}
	}

	public void afterRun(TestCaseRunner tcRunner, TestCaseRunContext tcRunContext) {
		System.out.println("AFTER TEST RUN");
	}

}
