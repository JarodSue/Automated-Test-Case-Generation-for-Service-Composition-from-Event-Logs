/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algobyservices;

import com.mycompany.ClusterGen.model.Cluster;
import com.mycompany.models.AbstractTraces;
import com.mycompany.models.Conversation;
import com.mycompany.models.Event;
import java.util.ArrayList;

/**
 *
 * @author jarod
 */
public class AdaptingTracesToIOLTS {
    public static Cluster Adapting(Cluster clust, String testedServiceIP){
        boolean responseAfterMockResponded=false;
        AbstractTraces aTrace=new AbstractTraces(testedServiceIP);
        ArrayList<Event> newConversationWithOnlyDependancies=new ArrayList<Event>();
        Cluster copyOfClust=new Cluster(clust);
        ArrayList<Event> abstractedTracesForTheClusterClust = clust.getClusterExample();
        Conversation firstConvOfTheCluster=copyOfClust.getFirstElement();

        Conversation NewFirstConversationForTheCluster= new Conversation();
        newConversationWithOnlyDependancies=adaptingArrayListOfEvents(abstractedTracesForTheClusterClust, testedServiceIP);
        NewFirstConversationForTheCluster.setConvFromArray(adaptingArrayListOfEvents(firstConvOfTheCluster.getConv(), testedServiceIP));
        copyOfClust.removeFirstElement();
        copyOfClust.setFirstElement(NewFirstConversationForTheCluster);
        copyOfClust.setClusterExample(newConversationWithOnlyDependancies);
        return copyOfClust;
    }
    
    public static boolean isADependancy(Event ev, String testedServiceIP){
        if((ev.isReq())&&(ev.getFrom().equals(testedServiceIP))){
            return true;
        }
        if((ev.isResp())&&(ev.getTo().equals(testedServiceIP))){
            return true;
        }
        return false;
    }
    
    public static ArrayList<Event> adaptingArrayListOfEvents(ArrayList<Event> abstractedTracesForTheClusterClust,String testedServiceIP){
        
        ArrayList<Event> newConversationWithOnlyDependancies=new ArrayList<Event>();
        for(Event ev : abstractedTracesForTheClusterClust){
            Event copyEv=new Event(ev);
            if(ev.haveAToken){
                copyEv.setHaveAToken(true);
            }
            if(ev.getTo().equals(testedServiceIP)){
                if((ev.isReq()) ){
                    if(!ev.getFrom().equals(testedServiceIP)){
                        copyEv.setIsARequest(true);
                        copyEv.setMock(false);
                        newConversationWithOnlyDependancies.add(copyEv);
                    }
                    else{
                        newConversationWithOnlyDependancies.add(copyEv);
                    }
                }
                else{
                    copyEv.setIsARequest(false);
                    copyEv.setMock(true);
                    newConversationWithOnlyDependancies.add(copyEv);
                }
            }
            else if(ev.getFrom().equals(testedServiceIP)){
                if((ev.isReq()) ){
                    if(!ev.getFrom().equals(testedServiceIP)){
                        copyEv.setIsARequest(true);
                        copyEv.setMock(true);
                        newConversationWithOnlyDependancies.add(copyEv);
                    }
                    else{
                        copyEv.setIsARequest(false);
                        copyEv.setMock(false);
                        newConversationWithOnlyDependancies.add(copyEv);
                    }
                }
                else{
                    copyEv.setIsARequest(false);
                    copyEv.setMock(true);
                    newConversationWithOnlyDependancies.add(copyEv);
                }
            }
        }
        return newConversationWithOnlyDependancies;
        
    }
}
