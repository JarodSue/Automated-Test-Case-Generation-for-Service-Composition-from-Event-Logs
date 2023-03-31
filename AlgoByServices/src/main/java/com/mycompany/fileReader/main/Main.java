package com.mycompany.fileReader.main;


import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import com.mycompany.fileReader.Split.Regex;
import com.mycompany.fileReader.Split.Trace;
//import split.*;
/**
 * 
 * @author Blot Elliott
 */

public class Main {

	public static String log;
	public static String regex;
	public static String output;
	static boolean timerMode;
	public static String mode;
	public static boolean premier;
	public static Trace trace;

	public static Trace mainFile( File file, Regex reg) {
		//Split the log and detect the sessions and  dependencies
			
 
		trace=com.mycompany.fileReader.Split.MainSplit.main(file,  reg);
			

		return trace ;
	}

	/**
	 *Create the directory that will contain the results
	 **/
	private static String createDir(String tmp) {
		String tmpName = null, fName = "RESULTS/" + tmp;
		int i = 1;
		File x = new File(fName);
		while(x.exists()) {
			tmpName = fName+i;
			x = new File(tmpName);
			i++;
		}
		if (tmpName != null) {
			fName = tmpName;
		}
		output = fName;
		x = new File(fName);
		x.mkdirs();
		return fName;
	}

}
