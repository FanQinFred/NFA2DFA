package com.company;

import java.util.ArrayList;

public class Tools {
    public static int unlabelQT(ArrayList<State> S){
        for(int i=0;i<S.size();i++){
            if(S.get(i).label==false){
                return i;
            }
        }
        return -1;
    }

    public static boolean isBelong(ArrayList<Integer> u,ArrayList<State> S){
        for(State s:S){
            if(s.ids.equals(u)){
                return true;
            }
        }
        return false;
    }
}
