package com.liuliu.status;

import com.liuliu.machine.CandyMachine;

/**
 * @Author: liulei
 * @Time: 2021/3/16 19:15
 * @Description
 */

public class NoCoinStatus implements Status {

    private CandyMachine candyMachine;

    public NoCoinStatus(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void putCoin() {
        System.out.println("恭喜您，硬币已成功投入，请选购糖果！");
        candyMachine.setStatus(new HaveCoinStatus(candyMachine));
    }

    @Override
    public void retriveCoin() {
        System.out.println("您还没有投入硬币！");
    }

    @Override
    public void pullHandle() {
        System.out.println("您还没有投入硬币！");
    }

    @Override
    public void deliveryCandy() {
        System.out.println("您还没有投入硬币！");
    }
}
