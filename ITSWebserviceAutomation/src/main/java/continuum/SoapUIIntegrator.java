package continuum;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.xmlbeans.XmlException;
import org.testng.Reporter;

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
	 
	 public static void setProjectFile(String soapuiProjectPath)  {
	     String readyAPIProjectPath=apiFilePath+soapuiProjectPath;
	 
	  System.out.println("Soapui Project Path="+readyAPIProjectPath);
	   
	  runner.setProjectFile(readyAPIProjectPath);
	  
	  runner.setJUnitReport(true);
	     runner.setOutputFolder("target/surefire-reports");
	  runner.setIgnoreError(true);
	  
	 }
	 
	 public static String[][] getTestCases(String soapuiProjectPath) {
	  String readyAPIProjectPath=apiFilePath+soapuiProjectPath;
	  WsdlProject project;
	 
	  List<String> testCase=new ArrayList<String>();
	  try {
	   project = new WsdlProject(readyAPIProjectPath);
	     // retrieve all test suite from project
	      List<TestSuite> testSuiteList = project.getProject().getTestSuiteList();
	      
	      // Iterate all TestSuites of project
	      for (TestSuite ts : testSuiteList) {
	                               
	           // Retrieve all TestCases from a particular TestSuite
	           testCaseList = ts.getTestCaseList();
	           for (TestCase tc : testCaseList) {
	       //  System.out.println("test case size"+testCaseList.size());
	            // Iterate all TestCases of the particular TestSuite
	           
	             
	              //Reporter.log("****Running Test case " + testCaseList.get(i).getName() + "********");
	             System.out.println("****Running Test case " + tc.getName() + "********");
	              testCase.add(tc.getName());              
	                }
	         }
	   
	     } catch (XmlException | IOException | SoapUIException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	     }
	     String[][] testCaseObject=new String[testCase.size()][1];
	     for(int i=0;i<testCase.size();i++)
	     {
	         testCaseObject[i][0]=testCase.get(i).toString();
	     }
	      return testCaseObject;
	 }
	 
	 
	 public static void excuteSoapUI(String testCase)
	 {
	  System.out.println("****Executing Test case " + testCase+ "********");
	  Reporter.log("****Executing Test case " + testCase+ "********");
	  try {
	   runner.setTestCase(testCase);
	   runner.run();  
	   
	   
	  } catch (Exception e) {
	   System.out.println("Error while executing Ready Api Project");
	   e.printStackTrace();
	  } 
	 }
	 
	 public static Map<String, String> getReport(String soapuiProjectPath) 
	 {
	    
	    String flag="passed";
	    List <TestCase> testcaseFailed=runner.getFailedTests();
	  String [][]tc=getTestCases(soapuiProjectPath);
	   Map<String,String> results=new HashMap<String,String>();
	    for(int j=0;j<tc.length;j++)
	             {
	                 for(int i=0;i<testcaseFailed.size();i++)
	                  {
	                   if(tc[j][0].equalsIgnoreCase(testcaseFailed.get(i).getName()))
	                   {
	         flag="failed";
	                   }
	              
	                  }
	                 results.put(tc[j][0],flag);
	                
	         }
	 
	         return results;
	      }
	 
	 
	 
	 
	 
	 
	  

	 
	    
	// public static void testAPI(String soapuiProjectPath,String testCase)  {
	//  String readyAPIProjectPath=apiFilePath+soapuiProjectPath;
	//  //String readyAPIProjectPath=soapuiProjectPath;
	//  System.out.println("Soapui Project Path="+readyAPIProjectPath);
	//   
	//  runner.setProjectFile(soapuiProjectPath);
	//  runner.setTestCase(testCase);
	//  runner.setJUnitReport(true);
	//  runner.setOutputFolder("target/surefire-reports");
	//  runner.setIgnoreError(true);
	//
	// }

	 

	  
	  
	 
	 
	
	
	
}
