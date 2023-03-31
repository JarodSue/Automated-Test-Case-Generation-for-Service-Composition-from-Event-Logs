/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jarod
 */
public class Conversation implements Comparable<Conversation>{
    public ArrayList<Event> conv;
    public boolean errorLabel;
    public Set<String> from;
    public ArrayList<ArrayList<String>> events;
    public Integer scoreService;
    public int responseCode;
    public int scoreEvent;
    public ArrayList<Event> convForTesting;

    //replicate a conversation without pointers
      public Conversation(Conversation conver){
        this.conv=new ArrayList<Event>();
        for(Event ev : conver.getConv()){
            this.conv.add(new Event(ev));
        }
        this.responseCode=conver.getStatus();
        this.errorLabel=conver.getErrorLabel();
        this.from=conver.getFromSet();
        this.scoreService=conver.getScoreService();
        this.events=new ArrayList<>();
        this.convForTesting=new ArrayList<>();
        for (Event ev : conver.getconvForTesting()){
            this.convForTesting.add(ev);
        }
        
    }
  

    //create an empty conversation
    public Conversation() {
        this.conv=new ArrayList<>();
        this.from=new HashSet<>();
        this.events=new ArrayList<>();
        this.convForTesting=new ArrayList<>();
    }


    //tell if the conversation finish by an error
    public void isAnErrorConv(){
        this.errorLabel=false;
        for(Event e: this.conv){
            if(e.getLabelAction()){
                this.errorLabel=true;
            }
        }
    }
    
    //get a list of all the from of this conversation in a new arraylist for the copy
    public ArrayList<String> getFrom(){
        return new ArrayList<>(this.from);
    }
    
    //get the list of all the from of this conversation
    public Set<String> getFromSet(){
        return this.from;
    }
    
    //get the list of events in String in the conversation
    public ArrayList<ArrayList<String>> getEvents(){
        return this.events;
    }
    
    //get if the conversation has an error
    public boolean getErrorLabel(){
        return this.errorLabel;
    }
    
    //Set if the conversation has an error
    public void setErrorLabel(boolean bool){
        this.errorLabel=bool;
    }
    
    //add an event in the testing
    public void addEventForTesting(Event e) {
	this.convForTesting.add(e);
    }
    
    //get the list of events for testing
    public ArrayList<Event> getconvForTesting() {
	return convForTesting;
    }
    
    //add an event and his from
    public void addEvent(Event e) {
	this.conv.add(e);
        this.from.add(e.getFrom());
    }
    //renvoie la taille de la conversation
    public int size() {
        return this.conv.size();
    }

    //set the arraylist of event by copying the one from a conversation, used for copying a conversation without pointers
    public void setconv(Conversation conv2) {
                  this.conv = conv2.conv;
          }
    
    //set the arraylist of event by copying the one from a conversation, used for copying a conversation without pointers
    public void setConvFromArray(ArrayList<Event> conv2) {
                  this.conv = conv2;
          }
    
    //get the response code of the conversation
    public int getStatus(){
       //Collections.unmodifiableList(conv);
            return responseCode;
    }
  

    //return the conversation
    public ArrayList<Event> getConv(){
       //Collections.unmodifiableList(conv);
            return conv;
    }

    //return the last event of a conversation
    public Event getLastEvent(){
                  return this.conv.get(this.conv.size()-1);
          }

    //return the first event of a conversation
    public Event getFirstEvent(){
                  return this.conv.get(0);
    }

    //return the score for the list of from
    public int getFromScore(Set<String> listScore){
        ArrayList<String> b = new ArrayList<String>(listScore);
        b.retainAll(this.from);
        this.scoreService=b.size();
        return scoreService;
    }

    //return the score of the conversations for the events
    public int getEventScore(Set<Event> listEvents){
        ArrayList<Event> b = new ArrayList<Event>(listEvents);
        b.retainAll(this.events);
        this.scoreService=b.size();
        return scoreService;
    }
    
    //return the score when it's already done
    public Integer getScoreService(){
        return this.scoreService;
    }
    
    //compare 2 conversations by the score
    @Override
    public int compareTo(Conversation o) {
        return this.getScoreService().compareTo(o.getScoreService());
    }
    
}
