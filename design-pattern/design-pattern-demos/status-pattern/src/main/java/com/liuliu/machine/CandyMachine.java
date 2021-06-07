package com.liuliu.machine;

import com.liuliu.status.Status;

/**
 * @Author: liulei
 * @Time: 2021/3/16 19:06
 * @Description
 */

public class CandyMachine {
    private Status status;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CandyMachine(int count) {
        this.count = count;
    }

    public CandyMachine(Status status) {
        this.status = status;
    }
}
