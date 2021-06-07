package com.liuliu;

import com.liuliu.machine.CandyMachine;
import com.liuliu.status.NoCoinStatus;

/**
 * @Author: liulei
 * @Time: 2021/3/16 19:05
 * @Description
 */

public class StatusTest {

    public static void main(String[] args) {
        CandyMachine candyMachine = new CandyMachine(1);
        candyMachine.setStatus(new NoCoinStatus(candyMachine));
        candyMachine.getStatus().putCoin();
        candyMachine.getStatus().pullHandle();
        candyMachine.getStatus().pullHandle();
        System.out.println(candyMachine.getCount());
        System.out.println(candyMachine.getStatus());
    }
}
