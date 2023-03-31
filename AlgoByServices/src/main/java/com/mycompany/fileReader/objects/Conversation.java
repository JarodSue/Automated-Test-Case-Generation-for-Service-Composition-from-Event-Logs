package com.mycompany.fileReader.objects;

import java.util.ArrayList;

import java.util.Collections;
import com.mycompany.fileReader.objects.EventSplit;
/**
* represent a conversation
* @param  conv  save the conversation
* @param  choosedKeys  save the choosed keys for the conversation
* @param  assignments  save the assignments used for all keys
* @param  cle  save the last used keys
* @param  nbrequest  number of requests in the conversation
* @param  nbreponse  number of responses in the conversation
*/


public class Conversation{
  public ArrayList<EventSplit> conv;

  public ArrayList<ArrayList<String>> choosedKeys;

  public ArrayList<String> assignments;
  public ArrayList<String> cle;

  public int nbRequest;
  public int nbResponse;
  
  public Conversation(){
      conv= new ArrayList<EventSplit>();


      choosedKeys= new ArrayList<ArrayList<String>>();

      nbRequest=0;
      nbResponse=0;
      assignments= new ArrayList<String>();
      
      cle=new ArrayList<String>();
  }

  public Conversation(Conversation conversation){
      conv= new ArrayList<EventSplit>();
      for (EventSplit ev : conversation.conv) {
    	  conv.add(ev);
      }
      
 
      choosedKeys= new ArrayList<ArrayList<String>>();
      for(ArrayList<String> choisi : conversation.choosedKeys) {
    	  choosedKeys.add(choisi);
      }
      nbRequest=conversation.nbRequest;
      nbResponse=conversation.nbResponse;
      assignments =  new ArrayList<String>();
      for (String assi : conversation.assignments) {
    	  assignments.add(assi);
      }
      cle=new ArrayList<String>();;
      
  }
  
  
  public Conversation(Conversation conversation, ArrayList<String> nouvellescles, EventSplit reqorrep){
	  conv= new ArrayList<EventSplit>();
      for (EventSplit ev : conversation.conv) {
    	  conv.add(ev);
      }
      
      assignments =  new ArrayList<String>();
      for (String assi : conversation.assignments) {
    	  assignments.add(assi);
      }
      

      choosedKeys= new ArrayList<ArrayList<String>>();
      for(ArrayList<String> choisi : conversation.choosedKeys) {
    	  choosedKeys.add(choisi);
      }
      
      for(String param : reqorrep.getParamsWithoutFromTo()) {
    	  if (!assignments.contains(param)) {
    		  assignments.add(param);
    	  }
      }
      if (reqorrep.isReq()) {
    	  nbRequest=conversation.getReq()+1;
    	  nbResponse=conversation.getRep();
      }
      else { if (reqorrep.isResp()) {
    	  nbRequest=conversation.getReq();
    	  nbResponse=conversation.getRep()+1;
      }
      else {
    	  nbRequest=conversation.getReq();
    	  nbResponse=conversation.getRep();
      }
      }
      cle=nouvellescles;
      choosedKeys.add(nouvellescles);
      
      conv.add(reqorrep);
  }
  
  
  public Conversation(EventSplit event){
    conv= new ArrayList<EventSplit>();
    assignments =  new ArrayList<String>();
    for(String param : event.getParamsWithoutFromTo()) {
  	  if (!assignments.contains(param)) {
  		  assignments.add(param);
  	  }
    }
    choosedKeys=new ArrayList<ArrayList<String>>();

    nbRequest=0;
    nbResponse=0;
    cle=new ArrayList<String>();
    conv.add(event);
    if (event.isReq()) {
		nbRequest++;
	}
    if (event.isResp()){
		nbResponse++;
	}
   
  }
  
 

  //renvoie la taille de la conversation
  public int size() {
      return this.conv.size();
  }
  public void NouvelEvent(EventSplit evenement ) {
    this.conv.add(evenement);
    
    if (evenement.isReq()) {
    	
    	this.nbRequest+=1;
    }
    else {
    	this.nbResponse+=1;
    }
  }

  public void setconv(Conversation conv2) {
		this.conv = conv2.conv;
	}
  

  

  //renvoie la conversation
  public ArrayList<EventSplit> getConv(){
     //Collections.unmodifiableList(conv);
	  return conv;
  }

  //renvoie le dernier evenement d'une conversation
  public EventSplit getLastEvent(){
		return this.conv.get(this.conv.size()-1);
	}
  public EventSplit getFirstEvent(){
		return this.conv.get(0);
	}

  
  
  

  public ArrayList<ArrayList<String>> getChoosedKeys() {
	return choosedKeys;
	}

	public void addChoosedKeys(ArrayList<String> clechoisi) {
	this.choosedKeys.add(clechoisi);
	}
  

	public int getReq(){
		return this.nbRequest;
	}
	public int getRep(){
		return this.nbResponse;
	}
	
	
}
