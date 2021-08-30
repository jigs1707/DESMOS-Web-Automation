package code;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Debug_TestSuite {
	static PrerequisiteChecks program_checks = null;
	static Chrome_TestEnvironment test_env = null;
	static boolean check_passed = false;
	static boolean calculatorTest_complete = false;
	
	@Before
	public void setUp() throws Exception {
		program_checks = new PrerequisiteChecks(true);
		test_env = new Chrome_TestEnvironment();
	}
	@After
	public void tearDown() throws Exception {
		program_checks = null;
		test_env = null;
	}
	
	//If there are no exceptions, then that's good. ChromeDriver automatically detects errors. 
	//"program_checks.run()" will automatically return a FALSE value if it encounters a problem.
	@Test
	public void a_Test_prerequisiteChecks() {
		System.out.println("\n\nDEBUG 1: [ Testing \"RunPrerequisiteChecks\" ] ..................................................................\n");
		check_passed = program_checks.run();
		if(check_passed) {
			return;
		}
		else {
			fail(" - Failure, \"program_checks.run()\" returned false. Prerequisite checker encountered an issue.");
		}
	}
	
	//Simple. If it encounters an exception or if it receives incorrect math results, then it is a fail.
	@Test
	public void b_Test_runCalculatorTest() {
		System.out.println("\n\nDEBUG 2: [ Testing \"RunCalculatorTest\" ] .....................................................................\n");
		
		if(check_passed) {
			test_env.runTests();
		}
		else {
			fail(" - Failure, the  \"test environment\" has not been built.");
		}
	}
}