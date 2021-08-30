package code;

/*Driver will simply run the program in a non-debug, non-testing mode.*/

public class Driver {
	public static void main(String[] args) {
		PrerequisiteChecks program_checks = new PrerequisiteChecks(false);
		Chrome_TestEnvironment test_env = new Chrome_TestEnvironment();
		boolean check_passed = false;
		boolean testEnvironment_ready = false;
		boolean calculatorTest_complete = false;
		boolean printing_ready = false;
		
		System.out.println("\n[ COMMENCING PREREQUISITE CHECKS: ]\n");
		check_passed = program_checks.run();
		
		if(check_passed) {
			System.out.println("\n[ COMMENCING CALCULATOR TEST: ]\n");
			test_env.runTests();
			printing_ready = true;
			System.exit(0);
		}
		else {
			//Not good. We will terminate the program to be safe.
			System.exit(0);
		}
	}
}
