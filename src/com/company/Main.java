package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> sigma= new ArrayList<String>(Arrays.asList("a","b"));
        ArrayList<Integer> Q=new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));
        int q0=0;
        ArrayList<Integer> F=new ArrayList<Integer>(Arrays.asList(10));
        ArrayList<Trans> delta=new ArrayList<Trans>();
        //添加转移
        delta.add(new Trans(0,"ϵ",1));
        delta.add(new Trans(0,"ϵ",7));
        delta.add(new Trans(1,"ϵ",2));
        delta.add(new Trans(1,"ϵ",4));
        delta.add(new Trans(6,"ϵ",1));
        delta.add(new Trans(6,"ϵ",7));
        delta.add(new Trans(3,"ϵ",6));
        delta.add(new Trans(5,"ϵ",6));

        delta.add(new Trans(2,"a",3));
        delta.add(new Trans(7,"a",8));

        delta.add(new Trans(4,"b",5));
        delta.add(new Trans(8,"b",9));
        delta.add(new Trans(9,"b",10));


        FA fa=new FA(sigma,Q,q0,F,delta);
        FA res=fa.run();
        res.print();
    }
}
