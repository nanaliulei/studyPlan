package com.liuliu.dynamic.pojo.sauce;

public class SaladSauce implements Sauce {
    @Override
    public String description() {
        return "salad sauce";
    }

    public String toString(){
        return description();
    }
}
