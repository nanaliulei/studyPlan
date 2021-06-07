package com.liuliu.command;

import com.liuliu.dynamic.pojo.Light;

/**
 * @Author: liulei
 * @Time: 2021/3/4 15:08
 * @Description
 */

public class LightOnCommand implements Command{

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}
