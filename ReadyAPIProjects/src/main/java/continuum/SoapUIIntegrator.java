package continuum;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.impl.rest.RestRequest;
import com.eviware.soapui.impl.rest.RestService;
import com.eviware.soapui.impl.rest.RestServiceFactory;
import com.eviware.soapui.impl.rest.WadlGenerator;
import com.eviware.soapui.impl.wadl.*;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestRequestStep;
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStepResult;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestAssertion;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunnable;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;
import com.eviware.soapui.tools.SoapUITestCaseRunner;




public class SoapUIIntegrator {
	
	/**************
	 * Integrating soap UI with java
	 * @author sneha.chemburkar
	 * @return 
	 * 	 
	 */
	
	
	public static final SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
	static //static TestRunner runner1=null;
	List<TestCase> testCaseList;
	
	static String apiFilePath = new File("").getAbsolutePath()+"\\src\\test\\ReadyAPIProjectFiles\\";
	
	public static void testAPI(String soapuiProjectPath)  {
    	String readyAPIProjectPath=apiFilePath+soapuiProjectPath;
	
		System.out.println("Soapui Project Path="+readyAPIProjectPath);
			
		runner.setProjectFile(readyAPIProjectPath);
		runner.setJUnitReport(true);
	   	runner.setOutputFolder("target/surefire-reports");
		runner.setIgnoreError(true);
		try {
			runner.run();		
			
			
		} catch (Exception e) {
			System.out.println("Error while executing Ready Api Project");
			e.printStackTrace();
		} 
	}
	
	public static void testReport(String soapuiProjectPath)
	{
	  	String readyAPIProjectPath=apiFilePath+soapuiProjectPath;
	  	String flag="passed";
	  	List <TestCase> testcaseFailed=runner.getFailedTests();
			System.out.println("Soapui Project Path="+readyAPIProjectPath);
			Map<String,String> results=new HashMap<String,String>();
		WsdlProject project;
		
		try {
			project = new WsdlProject(readyAPIProjectPath);
			 			  
		    
		   // retrieve all test suite from project
		    List<TestSuite> testSuiteList = project.getProject().getTestSuiteList();
		    
		    // Iterate all TestSuites of project
		    for (TestSuite ts : testSuiteList) {
		         System.out.println("****Running Test suite " + ts.getName() + "********");
		                                             
		         // Retrieve all TestCases from a particular TestSuite
		         testCaseList = ts.getTestCaseList();
		                                             
		          // Iterate all TestCases of the particular TestSuite
		           for (TestCase testcase : testCaseList)
		           {
		                System.out.println("****Running Test Case " + testcase.getName()+ "*****");
		               for(int i=0;i<testcaseFailed.size();i++)
		                {
		                 if(testcase.getName().equalsIgnoreCase(testcaseFailed.get(i).getName()))
		                 {
		 					 flag="failed";
		                 }
		                }
		               results.put(testcase.getName(),flag);
		               
		              }
		       }
			printReport(results);
		   } catch (XmlException | IOException | SoapUIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    }
	
	
		

	
		  
	public static void testAPI(String soapuiProjectPath,String testCase)  {
		String readyAPIProjectPath=apiFilePath+soapuiProjectPath;
		//String readyAPIProjectPath=soapuiProjectPath;
		System.out.println("Soapui Project Path="+readyAPIProjectPath);
			
		runner.setProjectFile(soapuiProjectPath);
		runner.setTestCase(testCase);
		runner.setJUnitReport(true);
		runner.setOutputFolder("target/surefire-reports");
		runner.setIgnoreError(true);

	}

	
		public static void main(String []args){
			System.out.println("Execute Soap API project");
			testAPI("ITGlue-Changed-URL-lastUserLogOn-soapui-project.xml");
			testReport("ITGlue-Changed-URL-lastUserLogOn-soapui-project.xml");
			
		
		         
		       
		}

		private static void printReport(Map<String, String> results) {
			for(Map.Entry<String, String> scenario : results.entrySet()) {
				  System.out.println("Executed "+scenario.getKey()+" with results "+scenario.getValue());
			}
		}
	
	
}
