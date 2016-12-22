
package continuum;

import org.testng.annotations.Test;

import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunListener;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestStepResult;
import com.eviware.soapui.plugins.ListenerConfiguration;




public class UpdateResultsToTestRail  {

	
	@Test
	public void updateResultToTest(){
		System.out.println("###################################### Test Result updation will work");
	    TestRailIntegrator.updateResultToTestRail();
	}
//	private long startTime;
//
//	public void beforeRun( TestCaseRunner testRunner, TestCaseRunContext runContext )
//	{
//		startTime = System.nanoTime();
//	}
//
//	public void afterRun( TestCaseRunner testRunner, TestCaseRunContext runContext )
//	{
//		long endTime = System.nanoTime();
//		System.out.println("###################################### TestCase [" + testRunner.getTestCase().getLabel() + "] took " + (endTime-startTime) + 
//				" nanoseconds." );
//	}
//
//	@Override
//	public void afterStep(TestCaseRunner arg0, TestCaseRunContext arg1, TestStepResult arg2) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void beforeStep(TestCaseRunner arg0, TestCaseRunContext arg1) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void beforeStep(TestCaseRunner arg0, TestCaseRunContext arg1, TestStep arg2) {
//		// TODO Auto-generated method stub
//
//	}




}
