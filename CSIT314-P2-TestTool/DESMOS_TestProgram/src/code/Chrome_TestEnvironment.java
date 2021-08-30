package code;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

public class Chrome_TestEnvironment {
	private static Chrome_TestFunctions testSubject;
	private static WebDriver driver;
	
	private static WebElement zero, one, two,  three, four, five, six, seven, eight, nine, dot;
	private static WebElement resultTextBox, resultDigit, openBracket, closeBracket, submit, clearAll;
	private static WebElement addition, subtract, divide, multiply, sin, cos, tan, sqroot;
	
	private static List<String> testHistory;
	
	static Path driverDirectory;
	static String driverDirectory_absolute;
	
	public static boolean construct() throws Exception{
		//Dynamically fetching the location of "chromedriver.exe".
		driverDirectory = Paths.get("resources","chromedriver.exe");
		driverDirectory_absolute = driverDirectory.toFile().getAbsolutePath();
		
		testHistory = new ArrayList<String>();
		
		System.setProperty("webdriver.chrome.driver", driverDirectory_absolute); 
		testSubject = new Chrome_TestFunctions();
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.desmos.com/scientific");
		
		zero = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[4]/div[5]/span"));
		one = driver.findElement(By.xpath("//*[@id='main']/div/div/div/div[3]/div[2]/div/div/div[3]/div[5]/span"));
		two = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[3]/div[6]/span"));
		three = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[3]/div[7]/span"));
		four = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[2]/div[5]/span"));
		five = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[2]/div[6]/span"));
		six = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[2]/div[7]/span"));
		seven = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[1]/div[5]/span"));
		eight = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[1]/div[6]/span"));
		nine = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[1]/div[7]/span"));
		dot = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[4]/div[6]/span"));
		
		clearAll = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[1]/div/div[7]"));
				
		resultTextBox = driver.findElement(By.className("dcg-mq-digit"));
		openBracket = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[4]/div[1]/span"));
		closeBracket = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[4]/div[2]/span"));
		submit = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[4]/div[10]/span"));
		
		addition = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[4]/div[8]/span"));
		subtract = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[3]/div[8]/span"));
		divide = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[1]/div[8]/span"));
		multiply = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[2]/div[8]/span"));
		sin = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[3]/div[1]/span"));
		cos = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[3]/div[2]/span"));
		tan = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[3]/div[3]/span"));
		sqroot = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[3]/div[2]/div/div/div[2]/div[1]/span"));
		
		return true;
	}
	

	public static String getCalculationResult() {
		String value = "null";
		String tempValue = "null";

		try {
			WebElement thing = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div/div[1]/div[2]/div/div[5]/div[2]/div[1]"));
			tempValue = thing.getAttribute("aria-label");
			tempValue = tempValue.replaceAll("\\s+","");
			tempValue = tempValue.toLowerCase().replaceAll("equals","");
			tempValue = tempValue.toLowerCase().replaceAll("minus","-");
			tempValue = tempValue.replaceAll("\"","");
			tempValue = tempValue.replaceAll(",","");
			tempValue = tempValue.replaceAll("startromanfont","");
			tempValue = tempValue.replaceAll("endromanfont","");
			value = tempValue;
		}
		catch(Exception e) {
			System.out.println("Could not find result text area...math expression may have not been submitted");
		}
		return value;
	}
	
	
	public boolean runTests() {
		boolean status = false;
		
		try {
			construct();
			System.out.println();
			Result results = JUnitCore.runClasses(Chrome_TestSuite.class);
			System.out.println("CALCULATOR TEST COMPLETE - SUCCESS?: " + results.wasSuccessful());
			
			String caclulatorResults = getFancyResults(false, results, "Calculator Test Suite");
			outputTestResults(false, true, true, caclulatorResults);
			
		    for (Failure failure : results.getFailures()) {
		    	System.out.println(" - Failure: " + failure.toString());
		  	    if(results.getFailures().contains(failure))
		  	    { status = false; }
			    else
			    { status = true; }
			}
			deconstruct();
			
		} catch (Exception e) { e.printStackTrace(); }
	    return status;
	}

	
	public void deconstruct() {
		driver.quit();
		driver = null;
	}
	
	
	public static boolean outputTestResults(boolean debug, boolean overwrite, boolean header, String message) {
		//Write stuff to a .txt file
		boolean status = false;
		String fileName = "DESMOS_test_output.txt";
		
		if(header) {
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date(System.currentTimeMillis());
			
			message = "[Test results outputted: "+formatter.format(date)+"]\n" + message;
		}
		
		if(overwrite) {
			File fold = new File(fileName);
			fold.delete();
		}

		File fnew = new File(fileName);

		try {
		    FileWriter f2 = new FileWriter(fnew, true);
		    f2.write(message);
		    f2.close();
		    status = true;
		} catch (IOException e) {
		    e.printStackTrace();
		}   
	    return status;
	}
	
	
	public static String getFancyResults(boolean debug, Result results, String testID) {
		String str = "";
		
		String resultStatus = "FAIL";
		if(results.wasSuccessful()) {
			resultStatus = "PASS";
		}
		
		int numTests = results.getRunCount();
		int numFails = results.getFailureCount();
		int numPasses = results.getRunCount() - results.getFailureCount();
		
		str += ("\n\n[\""+testID+"\" status: " + resultStatus + "]\n");
		str += (" - Run time: " + results.getRunTime() + "\n");
		str += (" - Total tests run: " + numTests + "\n");
		
		if(!debug) {
			str+= (" - [Test History: ]\n");
			for(int i = 0; i < testHistory.size(); i++) {
				str += ("    > " + testHistory.get(i));
			}
		}
		
		
		str += ("\n - '   ' tests passed: " + numPasses + "\n");
		str += (" - '   ' tests failed: " + numFails + "\n");
		
		if(results.getFailureCount() > 0) {
			str+= ("\n - [Failed Tests: ]\n");
			for (Failure failure : results.getFailures()) {
				str += ("    > (fail) : " + failure.toString() + "\n");
			}
		}
		
		if(!debug) {
		str += ("\n - Note: This program follows an \"all-or-nothing\" test oracle due to\n"
			    + "         the nature of how a calculator should function. A calculator is\n"
			    + "         either correct and accurate, or it is not. Thus, a simple 100%\n"
			    + "         pass-rate required oracle is best suited under given circumstances\n");
		}
		
		return str;
	}
	
	
	//Oracle: Math expressions must return 100% success rate for accuracy and error handling.
	public static class Chrome_TestSuite {
		private Random rand = new Random();

		//Uses the random int to call a button click
		public void randomButtonClick(int x) {
			switch(x) {
				case 0: zero.click();	break;
				case 1: one.click();	break;
				case 2: two.click();	break;
				case 3: three.click();	break;
				case 4: four.click();	break;
				case 5: five.click();	break;
				case 6: six.click();	break;
				case 7: seven.click();	break;
				case 8: eight.click();	break;
				case 9: nine.click();	break;
				default: break;
			}
		}

		
		//Cuts up the button string value to get the desired value
		//Assigns the desired value to an int using Integer.parseInt() based on the random integer
		//Returns the desired int value
		public int buttonIntValue(int x) {
			int value = 0;
			
			switch(x) {
				case 0: value = Integer.parseInt(zero.getText().substring(zero.getText().lastIndexOf("\n") + 1)); break;
				case 1: value = Integer.parseInt(one.getText().substring(one.getText().lastIndexOf("\n") + 1)); break;
				case 2: value = Integer.parseInt(two.getText().substring(two.getText().lastIndexOf("\n") + 1)); break;
				case 3: value = Integer.parseInt(three.getText().substring(three.getText().lastIndexOf("\n") + 1)); break;
				case 4: value = Integer.parseInt(four.getText().substring(four.getText().lastIndexOf("\n") + 1)); break;
				case 5: value = Integer.parseInt(five.getText().substring(five.getText().lastIndexOf("\n") + 1)); break;
				case 6: value = Integer.parseInt(six.getText().substring(six.getText().lastIndexOf("\n") + 1)); break;
				case 7: value = Integer.parseInt(seven.getText().substring(seven.getText().lastIndexOf("\n") + 1)); break;
				case 8: value = Integer.parseInt(eight.getText().substring(eight.getText().lastIndexOf("\n") + 1)); break;
				case 9: value = Integer.parseInt(nine.getText().substring(nine.getText().lastIndexOf("\n") + 1)); break;
				default: break;
			}
			return value;
		}

		
		//Will log the method name, status, and the time taken
		@Rule
		public MyJUnitStopWatch stopwatch = new MyJUnitStopWatch();
		
		@Before
		public void setUp() throws Exception { }
		
		
		@Test
		public void test_addition() {
			System.out.println("Testing addition:");
			
			//Creates two number within the range of 0 and 9
			int numberOne = rand.nextInt(10);
			int numberTwo = rand.nextInt(10);
			
			System.out.println("Expression = ("+numberOne+" + "+numberTwo+")");
			
			//The random number is used to click its assigned value
			randomButtonClick(numberOne);
			addition.click();
			randomButtonClick(numberTwo);
			
			int resultDigitInt = Integer.parseInt(getCalculationResult());
			
			//resultDigitInt is set as the expected value
			//The random integer is used in the method buttonIntValue() to get the text value based on the number
			//The two random integers are then calculated to compare with the expected
			
			testHistory.add("Type:\"Addition\", Target:("+numberOne+" + "+numberTwo+"), Expected Result:"+testSubject.add(buttonIntValue(numberOne), buttonIntValue(numberTwo))+", Actual (DESMOS) Result:"+resultDigitInt+"\n");
			
		    assertEquals("Adding", resultDigitInt, testSubject.add(buttonIntValue(numberOne), buttonIntValue(numberTwo)));
		}
		
		
		@Test
		public void test_subtraction() {
			System.out.println("Testing subtraction:");
			int numberOne = rand.nextInt(10);
			int numberTwo = rand.nextInt(10);
			
			System.out.println("Expression = ("+numberOne+" - "+numberTwo+")");
			
			randomButtonClick(numberOne);
			subtract.click();
			randomButtonClick(numberTwo);
			
			int resultDigitInt = Integer.parseInt(getCalculationResult());
			
			testHistory.add("Type:\"Subtraction\", Target:("+numberOne+" - "+numberTwo+"), Expected Result:"+testSubject.subtract(buttonIntValue(numberOne), buttonIntValue(numberTwo))+", Actual (DESMOS) Result:"+resultDigitInt+"\n");
			
			assertEquals("Subtracting", resultDigitInt, testSubject.subtract(buttonIntValue(numberOne), buttonIntValue(numberTwo)));
		}
		
		
		@Test
		public void test_multiplication() {
			System.out.println("Testing multiplication:");
			int numberOne = rand.nextInt(10);
			int numberTwo = rand.nextInt(10);
			
			System.out.println("Expression = ("+numberOne+" * "+numberTwo+")");
			
			randomButtonClick(numberOne);
			multiply.click();
			randomButtonClick(numberTwo);
			
			int resultDigitInt = Integer.parseInt(getCalculationResult());
			
			testHistory.add("Type:\"Multiplication\", Target:("+numberOne+" * "+numberTwo+"), Expected Result:"+testSubject.multiply(buttonIntValue(numberOne), buttonIntValue(numberTwo))+", Actual (DESMOS) Result:"+resultDigitInt+"\n");
			
		    assertEquals("Multiplying", resultDigitInt, testSubject.multiply(buttonIntValue(numberOne), buttonIntValue(numberTwo)));
		}
		
		
		@Test
		public void test_divide() {
			System.out.println("Testing division:");
			//To avoid the risk of error, the random numbers will be in range of 1-9
			int numberOne = rand.nextInt(9) + 1;
			int numberTwo = rand.nextInt(9) + 1;
			
			System.out.println("Expression = ("+numberOne+" / "+numberTwo+")");
			
			randomButtonClick(numberOne);
			divide.click();
			randomButtonClick(numberTwo);

			double resultDigitDouble = Double.parseDouble(getCalculationResult());
			double actualResult = testSubject.divide(buttonIntValue(numberOne), buttonIntValue(numberTwo));
			
			testHistory.add("Type:\"Division\", Target:("+numberOne+" / "+numberTwo+"), Expected Result:"+actualResult+", Actual (DESMOS) Result:"+resultDigitDouble+"\n");
			
			if(String.valueOf(actualResult).length() > String.valueOf(resultDigitDouble).length()) {
				fail("Java math has proven to be more accurate.");
			}
			
			
			assertEquals("Dividing", resultDigitDouble, actualResult, 0);
		} 
		
		
		@Test
		public void test_FourOperands() {
			System.out.println("Testing four-operands:");
			int numberOne = rand.nextInt(10);
			int numberTwo = rand.nextInt(10);
			int numberThree = rand.nextInt(10);
			
			//Ranged between 1-9 to avoid error
			int numberFour = rand.nextInt(9) + 1;
			int numberFive = rand.nextInt(9) + 1;

			System.out.println("Expression = ("+numberOne+" + "+numberTwo+" - "+numberThree+" * "+numberFour+" / "+numberFive+")");
			
			randomButtonClick(numberOne);
			addition.click();
			randomButtonClick(numberTwo);
			subtract.click();
			randomButtonClick(numberThree);
			multiply.click();
			randomButtonClick(numberFour);
			divide.click();
			randomButtonClick(numberFive);
			
			Double resultDigitDouble = Double.parseDouble(getCalculationResult());
			Double actualResult = testSubject.fourOperands(numberOne, numberTwo, numberThree, numberFour, numberFive);
			
			testHistory.add("Type:\"four-operands\", Target:("+numberOne+" + "+numberTwo+" - "+numberThree+" * "+numberFour+" / "+numberFive+"), Expected Result:"+actualResult+", Actual (DESMOS) Result:"+resultDigitDouble+"\n");
			
			if(String.valueOf(actualResult).length() > String.valueOf(resultDigitDouble).length()) {
				fail("Java math has proven to be more accurate.");
			}

			assertEquals("Printing result of the 4 operands", resultDigitDouble, actualResult, 0);
		}
		
		
		@Test
		public void test_sin() {
			System.out.println("Testing sin-operator:");
			//The calculator displays and rounds it up to 5 decimal places max
			//The DecimalFormat is set to fit those requirements
			DecimalFormat df = new DecimalFormat("#.#####");
			
			int numberOne = rand.nextInt(10);
			int numberTwo = rand.nextInt(10);
			
			System.out.println("Expression = ("+numberOne+" sin("+numberTwo+"))");
			
			sin.click();
			randomButtonClick(numberOne);
			closeBracket.click();
			multiply.click();
			randomButtonClick(numberTwo);

			
			//Cutting the string value to the desired value needed
			String resultDigitDouble = getCalculationResult();
			
			//The RoundingMode.CEILING will round up toward positive infinity
			df.setRoundingMode(RoundingMode.HALF_UP);
			 
			//Turns the string variable of the result on Desmos calculator into a double variable
			double desmosResult = Double.valueOf(resultDigitDouble);
			
			//Changes the first number into radians to use the cos equation and caluclates the result
			double result = Math.toRadians(numberOne);
			result = Math.sin(result) * numberTwo;
			
			//Formats both results to 5 decimal places
			result = Double.parseDouble(df.format(result));
			desmosResult = Double.parseDouble(df.format(desmosResult));
			
			testHistory.add("Type:\"Sin-operation\", Target:("+numberOne+" sin("+numberTwo+")), Expected Result:"+result+", Actual (DESMOS) Result:"+desmosResult+"\n");
			
			//the actual result is then rounded up when compared to the expected result
			assertEquals("Calculatin Sin equation", desmosResult, result, 0);
		}
		
		
		@Test
		public void test_cos() {
			System.out.println("Testing cos-operator:");
			//The calculator displays and rounds it up to 5 decimal places max
			//The DecimalFormat is set to fit those requirements
			DecimalFormat df = new DecimalFormat("#.#####");
			
			//These random numbers are generated to assign which number will be clicked in the test case
			int numberOne = rand.nextInt(10);
			int numberTwo = rand.nextInt(10);
			
			System.out.println("Expression = ("+numberOne+" cos("+numberTwo+"))");
			
			cos.click();
			randomButtonClick(numberOne);
			closeBracket.click();
			multiply.click();
			randomButtonClick(numberTwo);
			
			//Cutting the string value to the desired value needed
			String resultDigitDouble = getCalculationResult();
			
			//The RoundingMode.CEILING will round up toward positive infinity
			df.setRoundingMode(RoundingMode.HALF_UP);
			 
			//Turns the string variable of the result on Desmos calculator into a double variable
			double desmosResult = Double.valueOf(resultDigitDouble);
			
			//Changes the first number into radians to use the cos equation and caluclates the result
			double result = Math.toRadians(numberOne);
			result = Math.cos(result) * numberTwo;
			
			//Formats both results to 5 decimal places
			result = Double.parseDouble(df.format(result));
			desmosResult = Double.parseDouble(df.format(desmosResult));
			
			testHistory.add("Type:\"Cos-operation\", Target:("+numberOne+" cos("+numberTwo+")), Expected Result:"+result+", Actual (DESMOS) Result:"+desmosResult+"\n");
			
			//the actual result is then rounded up when compared to the expected result
			assertEquals("Calculatin Cos equation", desmosResult, result, 0);
		}
		
		
		@Test
		public void test_tan() {
			System.out.println("Testing tan-operator:");
			//The calculator displays and rounds it up to 5 decimal places max
			//The DecimalFormat is set to fit those requirements
			DecimalFormat df = new DecimalFormat("#.#####");
			
			//These random numbers are generated to assign which number will be clicked in the test case
			int numberOne = rand.nextInt(10);
			int numberTwo = rand.nextInt(10);
			
			System.out.println("Expression = ("+numberOne+" tan("+numberTwo+"))");
			
			tan.click();
			randomButtonClick(numberOne);
			closeBracket.click();
			multiply.click();
			randomButtonClick(numberTwo);
			
			//Cutting the string value to the desired value needed
			String resultDigitDouble = getCalculationResult();
			
			//The RoundingMode.CEILING will round up toward positive infinity
			df.setRoundingMode(RoundingMode.HALF_UP);
			 
			//Turns the string variable of the result on Desmos calculator into a double variable
			double desmosResult = Double.valueOf(resultDigitDouble);
			
			//Changes the first number into radians to use the cos equation and caluclates the result
			double result = Math.toRadians(numberOne);
			result = Math.tan(result) * numberTwo;
			
			//Formats both results to 5 decimal places
			result = Double.parseDouble(df.format(result));
			desmosResult = Double.parseDouble(df.format(desmosResult));
			
			testHistory.add("Type:\"Tan-operation\", Target:("+numberOne+" tan("+numberTwo+")), Expected Result:"+result+", Actual (DESMOS) Result:"+desmosResult+"\n");
			
			//the actual result is then rounded up when compared to the expected result
			assertEquals("Calculatin Tan equation", desmosResult, result, 0);
		}
		
		
		@Test
		public void test_sqroot() {
			System.out.println("Testing square-root-operator:");
			//The calculator displays and rounds it up to 5 decimal places max
			//The DecimalFormat is set to fit those requirements
			DecimalFormat df = new DecimalFormat("#.#####");
			
			//These random numbers are generated to assign which number will be clicked in the test case
			int randomNumber = rand.nextInt(10);
			
			System.out.println("Expression = (squareroot("+randomNumber+"))");
			
			sqroot.click();
			randomButtonClick(randomNumber);
			
			String resultDigitDouble = getCalculationResult();

			//The RoundingMode.CEILING will round up toward positive infinity
			df.setRoundingMode(RoundingMode.HALF_UP);
			 
			//Tests result with java function
			double result = Math.sqrt(randomNumber);
			double desmosResult = Double.parseDouble(resultDigitDouble);
			
			//Formats both results to 5 decimal places
			result = Double.parseDouble(df.format(result));
			desmosResult = Double.parseDouble(df.format(desmosResult));
			
			testHistory.add("Type:\"Tan-operation\", Target:(squareroot("+randomNumber+")), Expected Result:"+result+", Actual (DESMOS) Result:"+desmosResult+"\n");
			
			//the actual result is then rounded up when compared to the expected result
			assertEquals("Calculating square root", desmosResult, result, 0);
		}
		
		@Test
		public void test_error()
		{
			System.out.println("Testing error equation:");
			int numberOne = rand.nextInt(10);
			int numberTwo = 0;
			
			System.out.println("Expression = ("+numberOne+" / "+numberTwo+")");
			
			randomButtonClick(numberOne);
			divide.click();
			randomButtonClick(numberTwo);
			
			String resultDigitString = getCalculationResult();
			
			testHistory.add("Type:\"Error equation\", Target:("+numberOne+" / "+numberTwo+"), Expected Result: undefined"+", Actual (DESMOS) Result:"+resultDigitString+"\n");
			
			assertEquals("Calculating error equation", resultDigitString, "undefined");
			
		}
		
		
		@After
		public void tearDown() {
			clearAll.click();
		}
	}
}
