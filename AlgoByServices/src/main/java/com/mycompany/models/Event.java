/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import com.mycompany.fileReader.objects.EventSplit;
import org.apache.commons.lang3.math.NumberUtils;
/**
 *
 * @author jarod
 */
public class Event {
    public String ligne;
	public  String from;
	public  String to;
	public boolean error;
	public String token;
	private ArrayList<String> params;
	private ArrayList<String> paramsSess;
	private boolean labelAction;
        public boolean isARequestOfUser;
        public String URL;
        public String verb;
        public String key;
        public int code;
        public boolean isAReqForAbstractEvent;
        public boolean mockNeeded;
        public String body;
        public boolean haveAToken;
        public boolean json;
        public Event(){
        }
        public Event(EventSplit ev){
            this.code=0;
            this.ligne=ev.getligne();
            this.token="";
            this.params=new ArrayList<String>();
            this.paramsSess=new ArrayList<String>();
            this.from=ev.getFrom();
            this.json=ev.getIsJson();
            this.to=ev.getTo(); 
            if(!ev.getBody().equals("")){
                this.body=ev.getBody();
                
            }
            else{
                     this.body="";   
                        }
            if(!ev.getVerb().equals("")){
                this.verb=ev.getVerb();
            }
            if(!ev.getStatus().equals("")){
                this.code=Integer.parseInt(ev.getStatus()); 
            }
            if(!ev.getPath().equals("")){
                this.URL=ev.getPath(); 
            }
            if(!ev.getDstPort().equals("")){
                this.to=ev.getDstPort(); 
            }
            if(!ev.getSrcPort().equals("")){
                this.from=ev.getSrcPort(); 
            }
            
            for (String param : ev.getparamsWithFromTo()) {
			if ((param.contains("Bearer"))) {
				token=param.replace("\"", "");
			}
                    }
            this.haveAToken = this.token.contains("Bearer");
            this.body=this.body.replace("\"","");
            String[] separateMethodAndValue= this.body.split(",");
            
           
            if((!this.body.equals("")) && json){
                boolean key=true;
                boolean multipleKeys=false;
                String toAdd="{";
                String id=separateMethodAndValue[0].split(":")[0];
                String valueId=separateMethodAndValue[0].split(":")[1];
                System.out.println("test id");
                    System.out.println(id);
                for(String str : separateMethodAndValue){
                    System.out.println("param");
                    System.out.println(str);
                    
                    String[] separateKeyAndValue= str.split(":");
                    String toCompare=separateKeyAndValue[0];
                    if((toCompare.equals(id)) && (!valueId.equals(separateKeyAndValue[1]))){
                        toAdd=toAdd.substring(0,toAdd.length()-1);
                        toAdd+="},{";
                        if(!multipleKeys){
                            toAdd="["+toAdd;
                            multipleKeys=true;
                        }
                    }
                    for(String str2 : separateKeyAndValue){
                        if(key){
                            toAdd+="\""+str2+"\":";
                            key=false;
                        }
                        else{
                            boolean isDigit=false;
                            if(str2.equals("null")){
                                toAdd+="null,";
                            }
                            else{
                                try {

                                    Float.parseFloat(str2);
                                    str2=str2.replace(",", ".");
                                    toAdd+=""+str2+",";
                                    isDigit=true;
                                } catch(NumberFormatException e) {
                                    System.out.println("not float");
                                }
                                try {
                                    if(!isDigit){
                                        Integer.parseInt(str2);
                                        System.out.println("not int");
                                        str2=str2.replace(",", ".");
                                        toAdd+=""+str2+",";
                                        isDigit=true;
                                    }
                                } catch(NumberFormatException e) {
                                    System.out.println("not int");
                                }
                                if(!isDigit){
                                    toAdd+="\""+str2+"\",";
                                }
                                isDigit=false;

                            }
                        key=true;
                        }
                    }
                }
            toAdd=toAdd.substring(0,toAdd.length()-1);
            toAdd+="}";   
            if(multipleKeys){
                toAdd+="]";
            }
            this.body=toAdd;
            }
            
  
        }
        
        
       
		
	
        public Event(Event ev){
            this.ligne=ev.getligne();
            this.from=ev.getFrom();
            this.to=ev.getTo();
            this.error=ev.getError();
            this.token=ev.getToken();
            this.params=ev.getParamsWithFromTo();
            this.paramsSess=ev.getParamsWithoutFromTo();
            this.labelAction=ev.getLabelAction();
            this.isARequestOfUser=ev.getIsARequestOfUser();
            this.URL=ev.getURL();
            this.verb=ev.getVerb();
            this.isAReqForAbstractEvent=ev.getisAReqForAbstractEvent();
            this.mockNeeded=ev.getMock();
            this.code=ev.getCode();
            this.body=ev.getBody();
            this.haveAToken = ev.token.contains("Bearer");
        }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public void setIsARequest(boolean input){
         this.isARequestOfUser=input;
    }
    
    

	
	public boolean getLabelAction() {
		return labelAction;
	}

	public void setLabelAction(boolean labelAction) {
		this.labelAction = labelAction;
	}
	
	public ArrayList<String> getParamsWithoutFromTo() {
		return paramsSess;
	}
	
	public ArrayList<String> getParamsWithFromTo() {
		return params;
	}
	public boolean getError() {
		return error;
	}
	public void setError(boolean i) {
		this.error = i;
	}
	public String getFrom() {
		return from;
	}

	public String  getTo() {
		return to;
	}

	public String getligne() {
		return this.ligne;
	}
        
        public String getToken(){
            return this.token;
        }
        
        public boolean getIsARequestOfUser(){
            return this.isARequestOfUser;
        }
        public void setURL(String i) {
		this.URL = i;
	}
	public String getURL() {
		return URL;
	}
        public void setVerb(String i) {
		this.verb = i;
	}
	public String getVerb() {
		return verb;
	}
        public void setCode(int i) {
		this.code = i;
	}
	public int getCode() {
		return code;
	}
        public void setMock(boolean isAMockNeeded) {
		this.mockNeeded = isAMockNeeded;
	}
	public boolean getMock() {
		return mockNeeded;
	}
        
        public boolean getisAReqForAbstractEvent() {
		return isAReqForAbstractEvent;
	}
        
        public void setisAReqForAbstractEvent(boolean isAReq){
		this.isAReqForAbstractEvent=isAReq;
	}
        
        public boolean isReq() {
            return this.code==0;
	}

	/**
	 * Check if the event is a response.
        * @return 
	 */
	public boolean isResp() {
        return this.ligne.contains("esponse") | this.toString().contains("Resp") | this.toString().contains("resp") | this.code!=0;
                
	}


        
           public Event abstractingEvent(){
            Event newEv=new Event();
            if (this.isReq()){
                newEv.isAReqForAbstractEvent=true;
            }
            newEv.URL=this.getURL();
            newEv.ligne="";
            newEv.from=this.getFrom();
            newEv.to=this.getTo();
            newEv.error=this.getError();
            newEv.code=this.code;
            newEv.body=this.body;
            newEv.verb=this.verb;
            newEv.token="";
            newEv.paramsSess=this.getParamsWithoutFromTo();
            newEv.labelAction=this.getLabelAction();
            newEv.isARequestOfUser=this.getIsARequestOfUser();
            newEv.haveAToken = this.token.contains("Bearer");
            return newEv;
        }
           
           public static boolean comparingAbstractEvents(Event first, Event second){
               System.out.println("lesBodys");
               System.out.println(first.getBody());
               System.out.println(second.getBody());
               System.out.println(first.getURL());
               System.out.println(second.getURL());
               System.out.println(first.getVerb());
               System.out.println(second.getVerb());
               if(first.getURL()!=null){
                    if(first.getBody().equals(second.getBody())){
                         if(first.getURL().equals(second.getURL())){
                             if(first.getVerb().equals(second.getVerb())){
                                 if(first.getFrom().equals(second.getFrom())){
                                     if(first.getTo().equals(second.getTo())){
                                         if(first.haveAToken==second.haveAToken){
                                             return true;
                                         }
                                     }
                                 }

                             }
                         }
                    }
               }
               else{
                                 if(first.getFrom().equals(second.getFrom())){
                                     if(first.getTo().equals(second.getTo())){
                                         if(first.haveAToken==second.haveAToken){
                                             return true;
                                         }
                                     
                         }
                    }
               }
            return false;
        }

    public void setHaveAToken(boolean b) {
        this.haveAToken=b;
                }
           
           
    
}
