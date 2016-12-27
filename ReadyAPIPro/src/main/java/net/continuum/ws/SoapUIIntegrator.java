package net.continuum.ws;

import java.io.File;
import java.util.List;

import com.eviware.soapui.impl.wsdl.WsdlProjectPro;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestSuite;

/**
 * 
 * @author Vishal
 *
 */
public class SoapUIIntegrator {

	private static String apiFilePath = new File("").getAbsolutePath() + "/src/test/ReadyAPIProjectFiles/";

	/**
	 * Method to run a ReadyApi project. It can run Standard SOAP UI, as well
	 * Composite project
	 * 
	 * @param projectFile
	 *            project file name if the project is Standard SOAP UI project,
	 *            project's folder path if the project is composite
	 * @throws Exception
	 */
	public static void runProject(String projectFile) throws Exception {
		WsdlProjectPro project = new WsdlProjectPro(apiFilePath + projectFile);
		project.run(new PropertiesMap(), false);
	}

	/**
	 * Method to run a specific suite. It can run suite from Standard SOAP UI,
	 * as well Composite project
	 * 
	 * @param projectFile
	 *            project file name if the project is Standard SOAP UI project,
	 *            project's folder path if the project is composite
	 * @param suiteName
	 *            suite name
	 * @throws Exception
	 */
	public static void runTestsuiteByName(String projectFile, String suiteName) throws Exception {
		WsdlProjectPro project = new WsdlProjectPro(apiFilePath + projectFile);
		List<WsdlTestSuite> testSuites = project.getTestSuiteList();
		for (TestSuite suite : testSuites) {
			if (suite.getName().trim().equalsIgnoreCase(suiteName)) {
				suite.run(new PropertiesMap(), true);
			}
		}
	}

	/**
	 * Method to run a specific test case. It can run specific test case from
	 * Standard SOAP UI, as well Composite project
	 * 
	 * @param projectFile
	 *            project file name if the project is Standard SOAP UI project,
	 *            project's folder path if the project is composite
	 * @param tcId
	 *            test case id to run
	 * @throws Exception
	 */
	public static void runTestCaseById(String projectFile, String tcId) throws Exception {
		WsdlProjectPro project = new WsdlProjectPro(apiFilePath + projectFile);
		List<WsdlTestSuite> testSuites = project.getTestSuiteList();
		for (TestSuite suite : testSuites) {
			suite.run(new PropertiesMap(), true);
			List<TestCase> testCases = (List<TestCase>) suite.getTestCaseList();
			for (TestCase testCase : testCases) {
				if (testCase.getName().split("-")[0].trim().equalsIgnoreCase(tcId)) {
					testCase.run(new PropertiesMap(), false);
				}
			}
		}
	}

	/**
	 * Method to run a ReadyApi project sequentially. It can run Standard SOAP
	 * UI, as well Composite project
	 * 
	 * @param projectFile
	 *            project file name if the project is Standard SOAP UI project,
	 *            project's folder path if the project is composite
	 * @throws Exception
	 */
	public static void runAllTestCasesSequentially(String projectFile) throws Exception {
		WsdlProjectPro project = new WsdlProjectPro(apiFilePath + projectFile);
		List<WsdlTestSuite> testSuites = project.getTestSuiteList();
		for (TestSuite suite : testSuites) {
			List<TestCase> testCases = (List<TestCase>) suite.getTestCaseList();
			for (TestCase testCase : testCases) {
				testCase.run(new PropertiesMap(), false);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Execute Soap API project");
		runAllTestCasesSequentially("Peformance-soapui-project-Composite");
	}
}
