package code;

/*TestRunner will execute the program in dev-mode. It will include debug and testing information and utilise JUnit tests.*/

//This driver is almost identical to the standard one "Driver.java", but utilises JUnit tests and debug information.

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Driver_Debug {
	public static void main(String[] args) {
		System.out.println("Note: This is a development/debug driver to be utilised by program developers only.");

		System.out.println("\n................................... "
				+ "Development/debug driver test - (START)"
				+ " ...................................\n");
		
		Result prereq_result = JUnitCore.runClasses(Debug_TestSuite.class);
		reportResults(prereq_result);
		//Oracle for debug: 100% success rate required. It either works correctly or it doesn't.
	}

	private static void reportResults(Result result) {
		String caclulatorResults = Chrome_TestEnvironment.getFancyResults(true, result, "Dev-debug Program Ops Test");
		Chrome_TestEnvironment.outputTestResults(true, false, false, caclulatorResults);
		
		String resultStatus = "FAIL";
		if(result.wasSuccessful()) {
			resultStatus = "PASS";
		}
		
		System.out.println("\n\n\n................................... "
				+ "Development/debug driver test - (END)"
				+ " ...................................\n"
				+ "\nOverall result status: " + resultStatus);
		  
	      for (Failure failure : result.getFailures()) {
	         		System.out.println("Failure: " + failure.toString());
	      }
	}
}
