/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.algobyservices;

import com.mycompany.ClusterGen.main.mainClusterGen;
import com.mycompany.models.AbstractTraces;
import com.mycompany.ClusterGen.model.Cluster;
import com.mycompany.models.Conversation;
import com.mycompany.models.Event;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author jarod
 */
public class AlgoCreatingClustersAndAbstractTraces {
    
    //launching main cluster gen then creating abstract traces of each clusters
    //need to
    public static HashMap<String, ArrayList<Cluster>> AlgoClusterisationAndCreatingAbstractTracesWithUserRequests(ArrayList<Conversation> resultFromExtractionAlgorithm, ArrayList<String> PCO) throws FileNotFoundException{
        ArrayList<Cluster> arrayOfAllClusters=new ArrayList<>();
        arrayOfAllClusters = mainClusterGen.mainClusterisation(resultFromExtractionAlgorithm);
        ArrayList<String> componentsToBeTested=new ArrayList<>();
           
        for(String controlAndObservationPoint : PCO){
            componentsToBeTested.add(controlAndObservationPoint);
        }
        HashMap<String, ArrayList<Cluster>> allAbstractTracesForEveryComponent = new HashMap<String, ArrayList<Cluster>>();
        
        for(String componentCAbstractTracesToBeAdded : componentsToBeTested){
            allAbstractTracesForEveryComponent.put(componentCAbstractTracesToBeAdded, new ArrayList<Cluster>());
        }
        
        for(Cluster clust : arrayOfAllClusters){
            for(String componentCAbstractTracesToBeAdded : componentsToBeTested){
                Cluster abstractTraceForComponentC = AdaptingTracesToIOLTS.Adapting(clust, componentCAbstractTracesToBeAdded);
                ArrayList<Cluster> arrayListOfAbstractTracesToUpdate = allAbstractTracesForEveryComponent.get(componentCAbstractTracesToBeAdded);
                arrayListOfAbstractTracesToUpdate.add(abstractTraceForComponentC);
                allAbstractTracesForEveryComponent.put(componentCAbstractTracesToBeAdded, arrayListOfAbstractTracesToUpdate);
            }
        }
        return allAbstractTracesForEveryComponent;
    }
    
}
