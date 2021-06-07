package com.liuliu.behavior.impl;

import com.liuliu.behavior.QuackBehavior;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱 吱吱！");
    }
}
