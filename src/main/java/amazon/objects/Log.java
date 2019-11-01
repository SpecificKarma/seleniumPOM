package amazon.objects;

import org.testng.Reporter;

public class Log {

    public Log() {
    }

    public static void printLn(String message){
        Reporter.log(message + "<br>");
    }
}
