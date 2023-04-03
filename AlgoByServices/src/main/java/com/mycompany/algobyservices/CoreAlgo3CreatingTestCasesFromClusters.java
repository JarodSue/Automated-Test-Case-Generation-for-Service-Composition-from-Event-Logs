/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algobyservices;

import com.mycompany.ClusterGen.model.Cluster;
import com.mycompany.models.Conversation;
import com.mycompany.models.Event;
import static com.mycompany.models.Event.comparingAbstractEvents;
import com.mycompany.models.TestCase;
import java.util.ArrayList;

/**
 *
 * @author jarod
 */
public class CoreAlgo3CreatingTestCasesFromClusters {
    public static ArrayList<TestCase> creatingTestCasesFromClusters(ArrayList<Cluster> ATraces, String PCO){
        ArrayList<TestCase> TC= new ArrayList<TestCase>();
        ArrayList<Cluster> ATraces2=new ArrayList<>();
        for(Cluster clust : ATraces){
            ATraces2.add(new Cluster(clust));
        }
         

        ArrayList<Cluster> usedClusters= new ArrayList<Cluster>();
        for(Cluster clus : ATraces){
            if(!ATraces2.isEmpty()){
                ATraces2.remove(0);
                if(!usedClusters.contains(clus)){
                    boolean isAMultipleTestCase=false;
                    usedClusters.add(clus);
                    Conversation choosedConv = new Conversation(clus.getFirstElement());
                    ArrayList<Event> copyOfConvForTest=new ArrayList<Event>();
                    for(Event ev : choosedConv.getConv()){
                        copyOfConvForTest.add(new Event(ev));
                    }
                    TestCase tc=new TestCase(copyOfConvForTest, choosedConv.getStatus(), PCO);
                    ArrayList<Cluster> clustToRemove=new ArrayList<Cluster>();
                    if(!ATraces2.isEmpty()){
                        for(Cluster otherCluster : ATraces2){
                            Conversation conv=otherCluster.getFirstElement();
                            int numberOfSimilarities=compareConv(clus,otherCluster, PCO);
                            if(numberOfSimilarities>0){
                                ArrayList<Event> copyOfConvForTestCaseWithSimilarities=new ArrayList<Event>();
                                for(Event ev : choosedConv.getConv()){
                                    copyOfConvForTestCaseWithSimilarities.add(new Event(ev));
                                }
                                TestCase tc2=new TestCase(copyOfConvForTestCaseWithSimilarities, conv.getConv(), numberOfSimilarities-1, 1, choosedConv.getStatus(), PCO);

                                TC.add(tc2);
                                usedClusters.add(otherCluster);
                                clustToRemove.add(otherCluster);
                                isAMultipleTestCase=true;
                            }
                         }


                    }
                    for(Cluster clust : clustToRemove){
                        ATraces2.remove(clust);
                    }
                    if(!isAMultipleTestCase){
                        TC.add(tc);
                    }
                }
            }
        }
        return TC;
    }
    
    
    public static ArrayList<Event> procedureT(Conversation conv,boolean verdict){
        Conversation conv2=new Conversation(conv);
        return conv2.getConv();
    }
    
     public static void  addToTestCase(TestCase tc1, TestCase tc2) {
        if(tc2.getTree().get(0).getEventHere().abstractingEvent()!=tc1.getTree().get(0).getEventHere().abstractingEvent()){
            addToTestCase(tc1.getTree().get(0), tc2.getTree().get(0));
        }
        else{
            for(TestCase tcToAdd : tc2.getTree())
            tc1.getTree().add(tcToAdd);
        }
    }

    private static int compareConv(Cluster clus, Cluster otherClus,String PCO) {
        int numberOfSimilarities=0;
        ArrayList<Event> conv=otherClus.getClusterExample();
        ArrayList<Event> choosedConv=clus.getClusterExample();
        
        
        boolean zero=false;
        if(choosedConv.size()<conv.size()){
            for (int i = 0; i < choosedConv.size()-1; i++) {
                numberOfSimilarities=i;
                if(!comparingAbstractEvents(choosedConv.get(i),conv.get(i))){
                    if(numberOfSimilarities>1){
                        if(!choosedConv.get(i-1).getTo().equals(PCO)){
                            zero=true;   
                        }
                        if(choosedConv.get(i-1).getFrom().equals(choosedConv.get(0).getFrom())){
                            zero=true;
                        }
                        if(choosedConv.get(i).getTo().equals(choosedConv.get(0).getFrom())){
                            zero=true;
                        }
                        
                        break;
                    }
                    break;
                }
            }
        }
        else{
            for (int i = 0; i < conv.size()-1; i++) {
                numberOfSimilarities=i;
                if(!comparingAbstractEvents(choosedConv.get(i),conv.get(i))){
                    
                    if(numberOfSimilarities>1){
                        if(!choosedConv.get(i-1).getTo().equals(PCO)){
                            zero=true;
                            if(choosedConv.get(i-1).getFrom().equals(choosedConv.get(0).getFrom())){
                                zero=true;
                            }
                            if(choosedConv.get(i).getTo().equals(choosedConv.get(0).getFrom())){
                            zero=true;
                        }
                            
                        }
                        else{
                                zero=true;  
                        }
                        break;
                    }
                    break;
                }
            
            }
        }
        if(zero){
            numberOfSimilarities=0;
        }
        return numberOfSimilarities;
    }
}
