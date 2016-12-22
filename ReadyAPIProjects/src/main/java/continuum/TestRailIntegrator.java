package continuum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



/***************
 * test rail java API
 *  Method used to Save the Test Case Results in TestRail
	 * 
	 * @param testCaseID
	 *            TestRail Test Case ID "CXXXX"
	 * @param test Run
	 *            TestRail Test Set ID "RXXX"
	 * @param executionStatus
	 *            The execution result "Blocked", "Error", "Fail",
	 *            "Inconclusive", "Pass"
 *@author sneha.chemburkar
 */


public class TestRailIntegrator {
	static TestRailAPIClient client;

	public static void updateResultToTestRail(){
		int result=0;
		String duration=null;
		String errorMsg=null;
		
			
		connectToTestRail();
		//String testRun=Utilities.getMavenProperties("TestRun");
		String testRun="R1252";
		if(testRun.contains("R"))
		           testRun=testRun.split("R")[1];
		
		Map<String, String> scenarioResults=ReadXmlReport.readReport();
		if(!scenarioResults.isEmpty())
		{
		  for(Map.Entry<String, String> scenario : scenarioResults.entrySet()) {
			  result=getTestRailStatus(scenario.getValue());
			   duration=getDuration(scenario.getValue());
			   errorMsg=getErrorMsg(scenario.getValue()); 
			//   System.out.println("*********Scenario :"+scenario.getKey()+" is executed in "+duration +"sec resulted in "+result);
			   if(scenario.getKey().contains(","))
			   {
				   List<String> tcId=getMultipleTcIDFromScenario(scenario.getKey());
				  
				   for(int i=0;i<tcId.size();i++)
				   {
					   addResultTestRail(testRun,tcId.get(i),result,duration,errorMsg); 
				   }
			   }
			   else
			       addResultTestRail(testRun,getTcIDFromScenario(scenario.getKey()),result,duration,errorMsg);
		     }
		}
		else
			System.out.println("*****Results not updated in Test Rail  ***** ");
	  
	}

	
	private static String getTcIDFromScenario(String key) {
		// TODO Auto-generated method stub
		return null;
	}


	private static List<String> getMultipleTcIDFromScenario(String key) {
		// TODO Auto-generated method stub
		return null;
	}


	private static String getErrorMsg(String result) {
		String errormsg=result.split("_")[2];
		return errormsg;
		
	}


	private static String getDuration(String result) {
		String duration=result.split("_")[1];
		long durSec=(Long.valueOf(duration)/1000000000)/60;
		durSec=(int) Math.round(durSec);
		if(durSec<1)
			durSec=1;
		return String.valueOf(durSec);
	}


	public static void main(String []args)
	{
		
	//	System.out.println("Connection with Test rail https://continuum.testrail.net");
		client = new TestRailAPIClient("https://continuum.testrail.net");
		client.setUser("qe_automation@continuum.net");
		client.setPassword("q34ut0m4t!0n");
	   updateResultToTestRail();
		
		
	}
	
	
	public static int getTestRailStatus(String result){
		String status=result.split("_")[0];
	    int testRailStatus=0;
		switch(status){
        case "passed":
        	testRailStatus = 1;
        	break;
        case "failed":
        	testRailStatus = 5;
        	break;
        case "skipped":
        	testRailStatus = 2;
        	break;
        }
		return testRailStatus;
	}
	
	public static void connectToTestRail(){
		
		client = new TestRailAPIClient("https://continuum.testrail.net");
		client.setUser("qe_automation@continuum.net");
		client.setPassword("q34ut0m4t!0n");
	}
	
	public static void addResultTestRail( String testRunID,String testCaseID,int status, String executionTime ,String errorMsg)  {
		try {
			JSONObject obj = (JSONObject) client.sendGet("get_case/"
					+ Integer.parseInt(testCaseID));
			Map<String, Comparable> data = new HashMap<String, Comparable>();

			data.put("status_id", status);
			data.put("elapsed",executionTime);
			data.put("comment", errorMsg);
			JSONObject r = (JSONObject) client.sendPost("add_result_for_case/"+
					testRunID + "/" + testCaseID, data);
			System.out.println("*******Result updated in  Test Rail  Successfully   ***************************");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TestRailAPIException e) {
			e.printStackTrace();
		}
	}

	
	
	  
	
	



}



