package com.mycompany.fileReader.Split;
import java.io.File;



import java.util.HashMap;



public class MainSplit {

	public static String log;
	public static String output;
	public static boolean timerMode;
	public static String mode;

	public static HashMap<String, Double> means;
	public static Regex regex;
	public static Trace logOrigin;
	public static Trace trace;
	public static double interval = 5000.0;//in milliseconds 
	//public static double fact = 10.0;

	/** 
	public static Dependency Dep;
	*/
	public static Trace main(File log, Regex reg) {
		//parse the log

                trace = new Trace(log,reg);



  
        return trace;
        
        
	}

}
