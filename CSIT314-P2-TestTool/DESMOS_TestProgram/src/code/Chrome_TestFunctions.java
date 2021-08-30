package code;

public class Chrome_TestFunctions {
	public int add(int x, int y) {
		return x + y;
	}
	public int subtract(int x, int y) {
		return x - y;
	}
	
	//Divide function
		//Calculates and returns double in case of a decimal point number
		public double divide(double x, double y)
		{
			double result = (x/y);
			return result;
		}
	public int multiply(int x, int y) {
		return x * y;
	}
	
	public double fourOperands(double firstNumber, double secondNumber, double thirdNumber, double fourthNumber, double fifthNumber)
	{
		return firstNumber + secondNumber - thirdNumber * (fourthNumber / fifthNumber);
	}
}