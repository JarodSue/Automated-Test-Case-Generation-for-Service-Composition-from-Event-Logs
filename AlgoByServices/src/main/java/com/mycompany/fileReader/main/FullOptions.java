package com.mycompany.fileReader.main;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * @author Sue Jarod from code from Blot Elliott
 *
 */

/**
* create the options for the programs from the line of command used
*
* @param  args arguments for the options
* @param  reg  said to the programm if there is a use of a regex or not
* @return      void
*/
public class FullOptions {
	/**
	* set the options from the line of command used
	* @param  args The line of command used
	* @param  reg boolean to express a regex
	* @return void
	*/
	public static void setOptions(String[] args, boolean reg) throws Exception {
		final Options options = configParameters(reg);
		
	    final CommandLineParser parser = new DefaultParser();
	    try {
		    final CommandLine line = parser.parse(options, args);
		    	    	
		    if (reg) {
		    	Main.regex = line.getOptionValue("reg");	 
		    }
		    Main.output = line.getOptionValue("output");
		    
		    boolean idmode = line.hasOption("id");
		    if(idmode) {
		    	Main.mode = "-id";
		    }
		    else {
		    	Main.mode = ""; 
		    }
		   

		    
		    
		    // Timer
		    boolean timerMode = line.hasOption("timer");
		    if(timerMode) {
		    	Main.timerMode = true;
		    }
		    
		    boolean arretpremiermode = line.hasOption("premier");
		    if(arretpremiermode) {
		    	Main.premier = true;
		    }
	    
	    }catch(Exception e) {
	    	System.out.println("Usage : Main -i <input> -r <regex> -o <output>\n"
	    			+ "-i/-input : log file to analyse\n"
	    			+ "-r/-regex : regex file to use on the input file\n"
	    			+ "-o/-output : name of the output directory\n"
	    			+ "Options :\n"
	    			+ "-f\fif you want to stop at the first result\n"
	    			+ "-t\tshow the duration of each step of the program\n"
	    			+ "-id\tuse session identifier to split the log\n"
	    			);
	    	System.exit(1);}
	}
	
	/**
	* configure the options for the programs from the line of command used
	*
	* @param  reg  said to the programm if there is a use of a regex or not
	* @return      Options
	*/
	
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
	    
	    final Option arretpremier = Option.builder("f") 
	            .longOpt("premier") 
	            .desc("premier") 
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
	    options.addOption(arretpremier);
	
	    return options;
	}
	
	
}