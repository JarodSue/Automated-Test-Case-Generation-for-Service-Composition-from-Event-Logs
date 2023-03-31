/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ClusterGen.model;


import com.mycompany.models.Conversation;
import com.mycompany.models.Event;
import java.util.ArrayList;

/**
 *
 * @author jarod
 */
public class Cluster implements Comparable<Cluster>{
    public ArrayList<Conversation> trace;
    public ArrayList<Event> clusterExample;
    public Integer ScoreService;
    public Cluster() {
        this.trace=new ArrayList<Conversation>();
    }
    
    public Cluster(ArrayList<Conversation> cluster,ArrayList<Event> clusterExample) {
        this.trace=cluster;
        this.clusterExample=clusterExample;
    }
    
    
    
    public Cluster(Conversation conv,ArrayList<Event> clusterExample) {
        this.trace=new ArrayList<Conversation>();
        this.trace.add(conv);
        this.clusterExample=clusterExample;
    }

    public Cluster(Cluster clust) {
        this.trace=new ArrayList<Conversation>();
        for(Conversation conv : clust.getTrace()){
            this.trace.add(new Conversation(conv));
        }
        this.clusterExample=clust.getClusterExample();
    }
    
    public void setScoreService(Integer i){
       this.ScoreService=i; 
    }
    
    public Integer getScoreService(){
        return this.ScoreService;
    }

    
    public Conversation getFirstElement(){
        return trace.get(0);
    }
    
    public void setFirstElement(Conversation conv){
        this.trace.add(0, conv);
    }
    
    
    public void removeFirstElement(){
        this.trace.remove(0);
    }
    
    public ArrayList<Conversation> getTrace(){
        return trace;
    }
    public void deleteElement(Conversation conv){
        this.trace.remove(conv);
    }
    
    public ArrayList<Event> getClusterExample(){
        return clusterExample;
    }
    public String getClusterExampleLines(){
        String str="";
        for(Event ev : clusterExample){
            str+=ev.getligne();
            str+= " \n ";
        }
        return str;
    }
    
    public void setClusterExample(ArrayList<Event> arrayOfEvent){
        this.clusterExample=arrayOfEvent;
    }

    @Override
    public int compareTo(Cluster o) {
        return this.getScoreService().compareTo(o.getScoreService());
    }
            
}
