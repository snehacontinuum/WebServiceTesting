package continuum;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SoapUIDriver {
	String projectFile=null;
	
     @BeforeTest
	public void excuteSoapUI(){
		Reporter.log("*********Execute Soap API project****************");
		
	  projectFile=Utility.getMavenProperties("SoapUIProjectFile");
//	  String []projectFiles;
//	  if(projectFile.contains(","))
//	  {
//		  projectFiles=projectFile.split(",");
//		  for(int i=0;i<projectFiles.length;i++)
//			  SoapUIIntegrator.setProjectFile(projectFiles[i]);
//		  
//	  }  
//	  else
		SoapUIIntegrator.setProjectFile(projectFile);   
	       
	}
     
     @DataProvider
     public Object[][] soapTestCases() {
    	 
    	 return SoapUIIntegrator.getTestCases(projectFile); 
     }
     
     @Test( description = "Execute Soap test cases", dataProvider = "soapTestCases")
     public void executeAPI(String testCase){
    	 SoapUIIntegrator.excuteSoapUI(testCase);
     }
  
     
     @AfterTest
     public void afterTestResults(){
     if(Utility.getMavenProperties("TestRailUpdateFlag").equalsIgnoreCase("true"))
     {
  		TestRailIntegrator.updateResultToTestRail();
     }
  		HtmlEmailSender.sendReport();
     }

}
