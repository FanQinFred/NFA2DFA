package com.company;

/**
 * DFA状态转移规则集合
 */
public interface DFARules {
    /**
     * 获取开始状态
     * @return
     */
    public int getStartState();

    /**
     * 获取终止状态集合
     * @return
     */
    public int[] getEndStates();

    /**
     * 根据当前状态和下一个输入获取下一个状态,若出错，返回-1
     * @param state
     * @param ch
     * @return
     */
    public int getNextState(int state,char ch);
}