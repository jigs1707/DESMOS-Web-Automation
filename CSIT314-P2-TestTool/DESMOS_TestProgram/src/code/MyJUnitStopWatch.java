package code;

import org.junit.internal.AssumptionViolatedException;
//import org.junit.runners.model.Statement;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

//Class to log the test case descriptions
public class MyJUnitStopWatch extends Stopwatch{

private static void logInfo(Description description, String status, long seconds) {
    String testName = description.getMethodName();
    
    System.out.println(String.format("Test %s %s, spent %d milliseconds",
                              testName, status, TimeUnit.NANOSECONDS.toMillis(seconds)));
}

 @Override
   protected void succeeded(long seconds, Description description) {
       logInfo(description, "succeeded", seconds);
   }

   @Override
   protected void failed(long seconds, Throwable e, Description description) {
       logInfo(description, "failed", seconds);
   }

   protected void skipped(long seconds, AssumptionViolatedException e, Description description) {
       logInfo(description, "skipped", seconds);
   }

   @Override
   protected void finished(long seconds, Description description) {
       logInfo(description, "finished", seconds);
       System.out.println();
   }    
}