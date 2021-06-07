package com.liuliu.dynamic.pojo.dough;

public class ThinDough implements Dough {
    @Override
    public String description() {
        return "thin dough";
    }

    public String toString(){
        return description();
    }
}
