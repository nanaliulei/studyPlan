package com.liuliu.status;

import com.liuliu.machine.CandyMachine;

/**
 * @Author: liulei
 * @Time: 2021/3/16 19:15
 * @Description
 */

public class ReadyStatus implements Status {

    private CandyMachine candyMachine;

    public ReadyStatus(CandyMachine candyMachine) {
        this.candyMachine = candyMachine;
    }

    @Override
    public void putCoin() {
        System.out.println("您已经投入硬币，不能再次投递！");
    }

    @Override
    public void retriveCoin() {
        System.out.println("返还硬币！");
    }

    @Override
    public void pullHandle() {
        System.out.println("您已经拉动出货把手了！");
    }

    @Override
    public void deliveryCandy() {
        int count = candyMachine.getCount();
        if (count > 0){
            System.out.println("发放一个糖果！");
            candyMachine.setStatus(new NoCoinStatus(candyMachine));
            candyMachine.setCount(count - 1);
        }else{
            candyMachine.setStatus(new SoldOutStatus(candyMachine));
            System.out.println("糖果已售空，返还硬币");
        }

    }
}
