/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import com.mycompany.models.Event;
import java.util.ArrayList;

/**
 *
 * @author jarod
 */
public class TestCase {
    public ArrayList<Event> testEvents;
    public ArrayList<TestCase> treeForTesting;
    public Event eventHere;
    public boolean verdict;
    public int responseCode;
    public Event last;
    public String PCO;

    public String getPCO() {
        return PCO;
    }

    public void setPCO(String PCO) {
        this.PCO = PCO;
    }
    
    public TestCase(){
        this.testEvents=new ArrayList<Event>();
    }
    //creating a test case with an array list of test cases in order to create branching
    public TestCase(ArrayList<Event> listEvent, int code, String PCO){
        if(!listEvent.isEmpty()){
            this.PCO=PCO;
            this.testEvents=new ArrayList<Event>(listEvent);
            this.eventHere=listEvent.get(0);
            this.treeForTesting=new ArrayList<TestCase>();
            listEvent.remove(0);
            if(!listEvent.isEmpty()){
                this.treeForTesting.add(new TestCase(listEvent, code, PCO));
            }
            else{
                this.last=eventHere;   
            }
            this.responseCode=code;
        }
        
    }
    //creating a test case and using the counter to divide it by pattern


    
    public TestCase(ArrayList<Event> listEvent, ArrayList<Event> listEventToAdd, int numberOfSimilarities, int numberOfSimilaritiesNow, int code, String PCO) {
        this.PCO=PCO;
        this.testEvents=new ArrayList<Event>(listEvent);
        this.eventHere=listEvent.get(0);
        this.treeForTesting=new ArrayList<TestCase>();
        Event ev =listEventToAdd.get(0);
        listEvent.remove(0);
        listEventToAdd.remove(0);
        if(numberOfSimilarities==numberOfSimilaritiesNow){
            if(!listEvent.isEmpty()){
                this.treeForTesting.add(new TestCase(listEvent, code, PCO));
            
            if(!listEventToAdd.isEmpty()){
               this.treeForTesting.add(new TestCase(listEventToAdd, code, PCO));
            }
            }
            else{
                this.last=eventHere;   
            }
        }
        else{
            this.treeForTesting.add(new TestCase(listEvent, listEventToAdd, numberOfSimilarities, numberOfSimilaritiesNow+1,  code, PCO));
        }
        this.responseCode=code;
    }
    
    public void settestEvents(ArrayList<Event> listEvent){
         this.testEvents=listEvent;
    }
    public ArrayList<Event> gettestEvents(){
         return this.testEvents;
    }
    public void setTree(ArrayList<TestCase> treeForTesting){
         this.treeForTesting=treeForTesting;
    }
    public ArrayList<TestCase> getTree(){
         return treeForTesting;
    }
    public Event getEventHere(){
         return eventHere;
    }
    public void setEventHere(Event ev){
         this.eventHere=ev;
    }
    public TestCase getTreeNumber(int i){
         return treeForTesting.get(i);
    }
    public void setVerdict(boolean i){
         this.verdict=i;
    }
    public boolean getVerdict(){
         return verdict;
    }
    public void setCode(int i){
         this.responseCode=i;
    }
    public int getCode(){
         return responseCode;
    }
    
    public void showTreeForShowing(int branch){
        if(this.eventHere!=null){
        if(this.eventHere.getIsARequestOfUser()){
            System.out.println("?"+this.eventHere.getligne() + "   " + this.eventHere.getMock() + "   branch : " + branch);
        }
        else{
            System.out.println("!"+this.eventHere.getligne() + "   " + this.eventHere.getMock() + "   branch : " + branch);
        }
        if(!this.treeForTesting.isEmpty()){
            for(TestCase TC : this.treeForTesting){
                branch++;
                TC.showTreeForShowing(branch-1);
            }
        }
         
        }
    }
    
    public ArrayList<Event> allRequestsAndWaitedResponses(){
        ArrayList<Event> allRequestsAndWaitedResponsesToReturn=new ArrayList<Event>();
        if(this.eventHere.mockNeeded){
            allRequestsAndWaitedResponsesToReturn.add(this.eventHere);
        }
        if(!this.treeForTesting.isEmpty()){
            ArrayList<Event> toAdd=new ArrayList<Event>();
            for(TestCase TC : this.treeForTesting){
                for(Event ev : TC.allRequestsAndWaitedResponses()){
                    toAdd.add(ev);
                }
            }
            for(Event ev : toAdd){
                allRequestsAndWaitedResponsesToReturn.add(ev);
            }
        }
        return allRequestsAndWaitedResponsesToReturn;
    }
    
    
    public ArrayList<Event> ResultWaited(TestCase tc){
        ArrayList<Event> last=new ArrayList<Event>();
        ArrayList<Event> returned= new ArrayList<Event>();
        if(!tc.getTree().isEmpty()){
            for(TestCase tcInTree:tc.getTree()){
                returned=ResultWaited(tcInTree);
            }
            for(Event ev : returned){
                last.add(ev);
            }
        }
        else{
            last.add(eventHere);
        }
        return last;
    }
    
    public void addToTestCase(TestCase tc1, TestCase tc2) {
        if(tc2.getTree().get(0).getEventHere().abstractingEvent()!=tc1.getTree().get(0).getEventHere().abstractingEvent()){
            addToTestCase(tc1.getTree().get(0), tc2.getTree().get(0));
        }
        else{
            for(TestCase tcToAdd : tc2.getTree())
                tc1.getTree().add(tcToAdd);
        }
    }
    
}
