package com.liuliu.status;

import com.liuliu.machine.CandyMachine;

/**
 * @Author: liulei
 * @Time: 2021/3/16 19:15
 * @Description
 */

public class HaveCoinStatus implements Status {

    private CandyMachine candyMachine;
    public HaveCoinStatus(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void putCoin() {
        System.out.println("您已经投入硬币， 不能再次投递！");
    }

    @Override
    public void retriveCoin() {
        System.out.println("返还硬币！");
        candyMachine.setStatus(new NoCoinStatus(candyMachine));
    }

    @Override
    public void pullHandle() {
        System.out.println("已拉动出货把手！");
        candyMachine.setStatus(new ReadyStatus(candyMachine));
        candyMachine.getStatus().deliveryCandy();
    }

    @Override
    public void deliveryCandy() {
        System.out.println("当前还不能进行该操作！");
    }
}
