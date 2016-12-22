package continuum;
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//package continuum;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import org.testng.IReporter;
//import org.testng.IResultMap;
//import org.testng.ISuite;
//import org.testng.ISuiteResult;
//import org.testng.ITestContext;
//import org.testng.ITestNGMethod;
//import org.testng.ITestResult;
//import org.testng.xml.XmlSuite;
//
//public class ReporterListener implements IReporter {
//
//	@Override
//	public void generateReport(List xmlSuites, List suites,
//			String outputDirectory) {
//		System.out.println("*****Custom Report******");
//		ISuite suite = (ISuite) suites.get(0);
//		Map<String, Collection<ITestNGMethod>> methodsByGroup = suite.getMethodsByGroups();
//		Map<String, ISuiteResult> tests = suite.getResults();
//		for (String key : tests.keySet()) {
//			System.out.println("Key: " + key + ", Value: " + tests.get(key));
//		}
//		Collection suiteResults = tests.values();
//		ISuiteResult suiteResult = (ISuiteResult) suiteResults.iterator().next();
//		ITestContext testContext = suiteResult.getTestContext();
//		Collection perfMethods = methodsByGroup.get("perf");
//		IResultMap failedTests = testContext.getFailedTests();
//		for (ITestNGMethod perfMethod : perfMethods) {
//			Set testResultSet = failedTests.getResults(perfMethod);
//			for (ITestResult testResult : testResultSet) {
//				System.out.println("Test " + testResult.getName() + " failed, error " + testResult.getThrowable());
//			}
//		}
//		IResultMap passedTests = testContext.getPassedTests();
//		for (ITestNGMethod perfMethod : perfMethods) {
//			Set testResultSet = passedTests.getResults(perfMethod);
//			for (ITestResult testResult : testResultSet) {
//				System.out.println("Test " + testResult.getName() + " passed, time took " + 
//			(testResult.getStartMillis() - testResult.getEndMillis()));
//			}
//		}
//		System.out.println("*****End of Report******");
//	}
//}
