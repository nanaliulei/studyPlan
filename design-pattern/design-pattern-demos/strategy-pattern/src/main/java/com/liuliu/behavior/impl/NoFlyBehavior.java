package com.liuliu.behavior.impl;

import com.liuliu.behavior.FlyBehavior;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {}
}
