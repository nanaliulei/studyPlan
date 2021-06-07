package com.liuliu.dynamic.pojo.sauce;

public class TomatoSauce implements Sauce {
    @Override
    public String description() {
        return "tomato sauce";
    }

    public String toString(){
        return description();
    }
}
