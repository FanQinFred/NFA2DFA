package com.company.state;

import java.util.ArrayList;

public class State {
    public ArrayList<Integer> ids;   //原来状态的集合
    public Boolean label; //是否被标记
    public int newID=-1;  //新的编号
    public State(ArrayList<Integer> ids){
        this.ids=ids;
    }
    public State(ArrayList<Integer> ids,Boolean label){
        this.ids=ids;
        this.label=label;
    }

    public State(ArrayList<Integer> ids,Boolean label,int newID){
        this.ids=ids;
        this.label=label;
        this.newID=newID;
//        System.out.println("this.newID"+this.newID);
    }


    public State(State s){
        this.ids=s.ids;
        this.label=s.label;
        this.newID=s.newID;
    }
}
