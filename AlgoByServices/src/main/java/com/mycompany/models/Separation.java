package com.mycompany.models;


import java.util.ArrayList;


public class Separation {
	//split the line into several events
	public static String[] lineSplitting(String line) {
		String[] listEventsInOneConversationSplited=line.split(";");
		return listEventsInOneConversationSplited;
	}
	
	public static String[] eventSplitting(String soloEvent) {
			String line="";
			String[] listEventsInOneConversationSplited=soloEvent.split("\\(");
			line=listEventsInOneConversationSplited[1];
			line=line.substring(0, line.length() - 1);
			return listEventsInOneConversationSplited;
		}
}