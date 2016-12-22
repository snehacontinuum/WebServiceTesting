package continuum;

import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.XmlException;

import com.eviware.soapui.support.XmlHolder;

public class ReadXmlReport {

	public static Map<String, String> readReport() {
		Map<String,String> results = new HashMap<String,String>();
		 XmlHolder xmlReport;
		try {
			xmlReport = new XmlHolder("report.xml");
			
			
		String testCase= xmlReport.getNodeValue("//testcase");
//		testCase =testCase.replace( "<!\\[CDATA\\[", "" );
//		testCase = testCase.replaceAll( "]]>", "" );
	        System.out.println( xmlReport.getNodeValue("//testcase name"));
	        
	        
		    } catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		return results;
	}
 
	
	public static void main(String []args){
		readReport();
		
	}

}
