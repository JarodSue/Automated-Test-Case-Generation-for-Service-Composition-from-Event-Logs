/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.util.ArrayList;

/**
 *
 * @author jarod
 */
public class AbstractTraces {
    public ArrayList<Conversation> trace;
    
    public AbstractTraces() {
        this.trace=new ArrayList<Conversation>();
    }
    
    public AbstractTraces(String componentIP) {
        this.trace=new ArrayList<Conversation>();
    }

    public AbstractTraces(ArrayList<Conversation> resultFromLast) {
        this.trace=resultFromLast;
    }
    public void addConv(Conversation returnConv) {
        this.trace.add(returnConv);
    }
}
