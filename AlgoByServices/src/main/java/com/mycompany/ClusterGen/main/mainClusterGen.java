
package com.mycompany.ClusterGen.main;

import com.mycompany.ClusterGen.model.AbstractConv;
import com.mycompany.ClusterGen.model.Cluster;
import com.mycompany.models.Conversation;
import com.mycompany.models.Event;
import java.util.ArrayList;

/**
 *
 * @author jarod
 */

//create clusters from the results of the precedent algorithm
public class mainClusterGen {
    public static ArrayList<Cluster> mainClusterisation(ArrayList<Conversation> listOfAllConversations){
        ArrayList<Cluster> listOfCluster=new ArrayList<Cluster>();
        for (Conversation conv : listOfAllConversations){
            AbstractConv abstractedConv = new AbstractConv(conv);
            if(listOfCluster.size()==0){
                listOfCluster.add(new Cluster(conv,abstractedConv.getAbstractedConv()));
            }
            else{
                boolean  isNew = true;
                for (Cluster clust:listOfCluster){
                    if(comparingAbstract(abstractedConv.getAbstractedConv(), clust.getClusterExample())){
                        isNew=false;
                        break;
                    }
                }
                if(isNew){
                    listOfCluster.add(new Cluster(conv,abstractedConv.getAbstractedConv()));
                }
                
                
            }
        }
        return listOfCluster;
    }
    
    //compare different list of events which comes from abstracted events to know if there are gonna be in the same cluster
    
    public static boolean comparingAbstract( ArrayList<Event> first,  ArrayList<Event> second){
        
        boolean compare=true;
        if(first.size()==second.size()){
            for(int i=0;i<first.size();i++){
               if(!Event.comparingAbstractEvents(first.get(i), second.get(i))){
                   compare=false;
               };
            }
            if(compare){
                    return true;
                }
            return false;
        }
        return false;
         
    }
}
