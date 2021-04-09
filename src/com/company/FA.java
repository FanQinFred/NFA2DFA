package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class FA {
    private static ArrayList<String> sigma;
    private static ArrayList<Integer> Q;
    private static int q0;
    private static ArrayList<Integer> F;
    private static ArrayList<Trans> delta;

    public  FA(ArrayList<String> sigma,ArrayList<Integer> Q,int q0,ArrayList<Integer> F,ArrayList<Trans> delta){
        this.sigma=sigma;
        this.Q=Q;
        this.q0=q0;
        this.F=F;
        this.delta=delta;
    }
    // 求解 e-closure(q)
    public static ArrayList<Integer> getEq(int q, String a){
        // 暂时没有去重
        MyStack stack = new MyStack(100);

        stack.push(q);
        // 初始值置为T
        ArrayList<Integer> result=new ArrayList<Integer>(Arrays.asList(q));
        while(!stack.isEmpty()){
            Integer t=stack.pop();
            for(Trans elme:delta){
                if(elme.q==t&&elme.a==a&&!result.contains(elme.q1)){
                    result.add(elme.q1);
                    stack.push(elme.q1);
                }
            }
        }
        return new ArrayList<Integer>(new HashSet<Integer>(result));

//        ArrayList<Integer> result=new ArrayList<Integer>();
//        result.add(q);
//        for(Trans elme:delta){
//            if(elme.q==q&&elme.a==a){
//                result.add(elme.q1);
//            }
//        }
//        return new ArrayList<Integer>(new HashSet<Integer>(result));
    }

    public static ArrayList<Integer> getET(ArrayList<Integer> T){
        // 暂时没有去重  // 没使用栈
        // 使用栈来处理
        MyStack stack = new MyStack(100);
        for(Integer i:T){
            stack.push(i);
        }
        // 初始值置为T
        ArrayList<Integer> result=new ArrayList<Integer>(T);
        while(!stack.isEmpty()){
            Integer t=stack.pop();
            ArrayList<Integer> eclosuret=getEq(t, "ϵ");
            for(Integer q:eclosuret){
                if(!result.contains(q)){
                    result.add(q);
                    stack.push(q);
                }
            }
        }
        return new ArrayList<Integer>(new HashSet<Integer>(result));
    }

    // 不包括自己 //result.add(q);
    public static ArrayList<Integer> getMoveBase(int q, String a){

        ArrayList<Integer> result=new ArrayList<Integer>();
//        result.add(q);
        for(Trans elme:delta){
            if(elme.q==q&&elme.a==a){
                result.add(elme.q1);
            }
        }
        return new ArrayList<Integer>(new HashSet<Integer>(result));
    }

    public static ArrayList<Integer> getMoveTa(ArrayList<Integer> T,String a){
        // 暂时没有去重
        ArrayList<Integer> result=new ArrayList<Integer>();
        for(int t:T){
            result.addAll(getMoveBase(t,a));
        }
        return new ArrayList<Integer>(new HashSet<Integer>(result));
    }

    public static FA run() {
        int newStateID=0;
        ArrayList<DTT> DTTs=new ArrayList<DTT>();
        ArrayList<State> QT=new  ArrayList<State>();
        State state=new State(getEq(q0,"ϵ"),false);

//        ArrayList<Integer> temp= getEq(q0,"ϵ");
//                        System.out.println("temp的内容：");
//                for(int i=0;i<temp.size();i++){
//                    System.out.print(temp.get(i)+" ");
//                }
//                System.out.println("");


        State initState = null;
        QT.add(state);
        while(Tools.unlabelQT(QT)!=-1){
            int index=Tools.unlabelQT(QT);
//            System.out.println("index: "+index);
            State T = QT.get(index);
            initState=new State(T.ids,true,newStateID++);
            QT.set(index,initState);
            for(String a:sigma){
                ArrayList<Integer> U=new ArrayList<Integer>();
                U=getET(getMoveTa(T.ids,a));

//                ArrayList<Integer> test=new ArrayList<Integer>();
//                test=getMoveTa(T.ids,a);
//
//                System.out.println("T.ids的内容：");
//                for(int i=0;i<T.ids.size();i++){
//                    System.out.print(T.ids.get(i)+" ");
//                }
//                System.out.println("");
//
//                System.out.println("test的内容：");
//                for(int i=0;i<test.size();i++){
//                    System.out.print(test.get(i)+" ");
//                }
//                System.out.println("");System.out.println("");
//
//                System.out.println("U的内容：");
//                for(int i=0;i<U.size();i++){
//                    System.out.print(U.get(i)+" ");
//                }
//                System.out.println("");

                if(!Tools.isBelong(U,QT)){
                    QT.add(new State(U,false));
                }
                DTTs.add(new DTT(T,a,new State(U,false)));
            }
        }

        // 打印DTTs
//        for(DTT dtt:DTTs){
//            // 出发集合
//            System.out.println("");
//            System.out.println("出发集合：");
//            System.out.print("{");
//            for(int i=0;i<dtt.start.ids.size();i++){
//                System.out.print(dtt.start.ids.get(i).toString()+", ");
//            }
//            System.out.println("}");
//
//            // 输入符号
//            System.out.println("输入符号：");
//            System.out.println(dtt.s);
//
//            // 结束集合
//            System.out.println("结束集合：");
//            System.out.print("{");
//            for(int i=0;i<dtt.end.ids.size();i++){
//                System.out.print(dtt.end.ids.get(i).toString()+", ");
//            }
//            System.out.println("}");
//            System.out.println("");
//        }

        // 重新编号
        for(DTT dtt:DTTs){
            for(State qt:QT){
                if(qt.ids.equals(dtt.start.ids)){
                    dtt.start.newID=qt.newID;
                }
                if(qt.ids.equals(dtt.end.ids)){
                    dtt.end.newID=qt.newID;
                }
            }
        }

        // 打印DTTs
//        for(DTT dtt:DTTs){
//            // 出发集合
//            System.out.println("");
//
//            System.out.print("起点");
//            System.out.println(dtt.start.newID);
//
//            // 输入符号
//            System.out.print("输入符号：");
//            System.out.println(dtt.s);
//
//            // 结束集合
//            System.out.print("终点");
//            System.out.print(dtt.end.newID);
//
//            System.out.println("");
//        }

        // 返回一个五元组
        ArrayList<String> sigmaBrief= new ArrayList<String>();   //输入符号
        ArrayList<Integer> QBrief=new ArrayList<Integer>();      //有限的状态集合
        int q0Brief;                                             //初始状态
        ArrayList<Integer> FBrief=new ArrayList<Integer>();      //终结状态集合
        ArrayList<Trans> deltaBrief=new ArrayList<Trans>();      //状态转移

        sigmaBrief=sigma;
        for(DTT dtt:DTTs){
            QBrief.add(dtt.start.newID);
            QBrief.add(dtt.end.newID);
        }
        QBrief= new ArrayList<Integer>(new HashSet<Integer>(QBrief));
        q0Brief=initState.newID;

        for(DTT dtt:DTTs){
            for(Integer f:F){
                if(dtt.start.ids.contains(f)){
                    FBrief.add(dtt.start.newID);
                }
                if(dtt.end.ids.contains(f)){
                    FBrief.add(dtt.end.newID);
                }
            }
        }
        FBrief= new ArrayList<Integer>(new HashSet<Integer>(FBrief));

        for(DTT dtt:DTTs){
            deltaBrief.add(new Trans(dtt.start.newID,dtt.s,dtt.end.newID));
        }

//        for(Trans debrief:deltaBrief){
//            System.out.println(debrief.q+"---"+debrief.a+"-->"+debrief.q1);
//        }

        return new FA(sigmaBrief,QBrief,q0Brief,FBrief,deltaBrief);
    }

    public static void print(){
        for(Trans d:delta){
            System.out.println(d.q+"---"+d.a+"-->"+d.q1);
        }
    }
}
