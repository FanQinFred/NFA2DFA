package com.company;

public class DTT {
    public State start;
    public String s;
    public State end;
    public DTT(State start,String s,State end){
        this.start=new State(start);
        this.s=s;
        this.end=new State(end);
    }
}
