package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class NFA2DFA implements DFARules{

    FA fa=new FA();

    /**
     * 初始化DFA
     * @return
     */
    public NFA2DFA(FA f){
        fa.sigma=f.sigma;
        fa.Q=f.Q;
        fa.q0=f.q0;
//        System.out.println("f.q0: "+f.q0);
        fa.F=f.F;
        fa.delta=f.delta;
    }

    /**
     * 初始化DFA
     * @return
     */
    public  NFA2DFA(ArrayList<String> sigma,ArrayList<Integer> Q,int q0,ArrayList<Integer> F,ArrayList<Trans> delta){
        fa.sigma=sigma;
        fa.Q=Q;
        fa.q0=q0;
        fa.F=F;
        fa.delta=delta;
    }
    @Override
    public int getStartState() {
        FA res=fa.run();
        return res.q0;
    }

    @Override
    public int[] getEndStates() {
        FA res=fa.run();
        Integer[] arr = res.F.toArray(new Integer[0]);
        int[] arr2 = Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();
        return arr2;
    }

    @Override
    public int getNextState(int state, char ch) {
        FA res=fa.run();
        ArrayList<Trans> delta=res.delta;
        for(Trans d: delta){
            if(d.q==state&&d.a.equals(String.valueOf(ch))){
                return d.q1;
            }
        }
        return -1;
    }

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
        NFA2DFA nfa2DFA=new NFA2DFA(fa);

        int s=nfa2DFA.getStartState();
        System.out.println("初始点: "+s);

        int [] ss=nfa2DFA.getEndStates();
        System.out.print("终点集合：");
        for(int i:ss){
            System.out.print(i+" ");
        }
        System.out.println("");

        int a0= nfa2DFA.getNextState(0, 'a');
        System.out.println("0--a-->"+a0);

        int a1= nfa2DFA.getNextState(0, 'b');
        System.out.println("0--a-->"+a1);

        int a2= nfa2DFA.getNextState(1, 'a');
        System.out.println("1--a-->"+a2);

        int a3= nfa2DFA.getNextState(1, 'b');
        System.out.println("1--a-->"+a3);

        int a4= nfa2DFA.getNextState(2, 'a');
        System.out.println("2--a-->"+a4);

        int a5= nfa2DFA.getNextState(2, 'b');
        System.out.println("2--a-->"+a5);

        int a6= nfa2DFA.getNextState(3, 'a');
        System.out.println("3--a-->"+a6);

        int a7= nfa2DFA.getNextState(3, 'b');
        System.out.println("3--a-->"+a7);

        int a8= nfa2DFA.getNextState(4, 'a');
        System.out.println("4--a-->"+a8);

        int a9= nfa2DFA.getNextState(4, 'b');
        System.out.println("4--a-->"+a9);

    }
}
