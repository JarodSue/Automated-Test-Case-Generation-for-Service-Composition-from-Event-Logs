package com.mycompany.fileReader.Split;
import org.apache.commons.cli.CommandLine;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * @author Blot Elliott
 *
 */
public class MapperOptions {
	
	public static void setOptions(String[] args, boolean reg) throws Exception {
		final Options options = configParameters(reg);
	    final CommandLineParser parser = new DefaultParser();
	    try {
		    final CommandLine line = parser.parse(options, args);
		    
		   
		    if (reg) {
		    	 MainSplit.regex = new Regex(line.getOptionValue("reg"));
		    }
		   	
		    MainSplit.output = line.getOptionValue("output");
		    
		    
		    boolean idmode = line.hasOption("id");
		    if(idmode) {
		    	MainSplit.mode = "id";
		    }
		    else {
		    	MainSplit.mode = "classic"; 
		    }
		    		
		    // Timer
		    boolean timerMode = line.hasOption("timer");
		    if(timerMode) {
		    	MainSplit.timerMode = true;
		    }

	    
	    }catch(Exception e) {
	    	System.out.println("Usage mapper : Main -i <input> -r <regex> -o <output>\n"
	    			+ "-i/-input : log file to analyse\n"
	    			+ "-r/-regex : regex file to use on the input file\n"
	    			+ "-o/-output : name of the output directory\n"
	    			+ "Options :\n"
	    			+ "-t\tshow the duration of each step of the program\n"
	    			);
	    	System.out.println(e);
	    	System.exit(1);}
	}
	
	
	private static Options configParameters(boolean reg) {
		final Options options = new Options();
		if (reg) {
			final Option regexOption = Option.builder("r")
					.longOpt("reg")
					.desc("regex to use")
					.hasArg(true)
					.argName("reg")
					.required(true)
					.build();
			options.addOption(regexOption);
		}
	    final Option timerFileOption = Option.builder("t") 
	            .longOpt("timer") 
	            .desc("Timer") 
	            .hasArg(false) 
	            .required(false) 
	            .build();
	    
	    final Option outputOption = Option.builder("o")
				.longOpt("output")
				.desc("output file")
				.hasArg(true)
				.argName("output")
				.required(true)
				.build();

	    final Option modeOption = Option.builder("id")
				.longOpt("sessID")
				.desc("use session id")
				.hasArg(false)
				.argName("sessID")
				.required(false)
				.build();
	
	    
	
	    
	    options.addOption(timerFileOption);
	    options.addOption(outputOption);
	    options.addOption(modeOption);
	    
	    return options;
	}
	
	
}
