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
public final class AbstractConv {
    public ArrayList<Event> abstractedConv;
    public Conversation convToAbstract;
    public int counterForConv;
            
    
    public AbstractConv(Conversation conv){
        abstractedConv=new ArrayList<Event>();
        this.convToAbstract=conv;
        for(Event ev : conv.getConv()){
            
            Event abstractedEvent=abstractEv(ev);
            abstractedConv.add(abstractedEvent);
        }
    }
    
    public Event abstractEv(Event ev){
        Event evAbstracted= ev.abstractingEvent();
        return evAbstracted;
    }
    public ArrayList<Event> getAbstractedConv(){
        return this.abstractedConv;
    }
    public boolean compareAbstract(ArrayList<Event> conv){
        for(int i=0;i<this.getAbstractedConv().size();i++){
            System.out.println(conv.get(i).equals(this.getAbstractedConv().get(i)));
        }
        return true;
    }
}
