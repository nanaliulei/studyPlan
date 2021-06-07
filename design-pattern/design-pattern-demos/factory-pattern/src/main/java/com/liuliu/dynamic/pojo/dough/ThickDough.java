package com.liuliu.dynamic.pojo.dough;

public class ThickDough implements Dough {
    @Override
    public String description() {
        return "thick dough";
    }

    public String toString(){
        return description();
    }
}
