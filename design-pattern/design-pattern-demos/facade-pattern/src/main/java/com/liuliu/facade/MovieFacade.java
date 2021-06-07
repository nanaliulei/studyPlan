package com.liuliu.facade;

import com.liuliu.dynamic.pojo.CDPlayer;
import com.liuliu.dynamic.pojo.Curtain;
import com.liuliu.dynamic.pojo.Light;
import com.liuliu.dynamic.pojo.Television;

/**
 * @Author: liulei
 * @Time: 2021/3/5 11:04
 * @Description
 */

public class MovieFacade {

    private Light light;
    private Television television;
    private CDPlayer cdPlayer;
    private Curtain curtain;

    public MovieFacade(Light light, Television television, CDPlayer cdPlayer, Curtain curtain) {
        this.light = light;
        this.television = television;
        this.cdPlayer = cdPlayer;
        this.curtain = curtain;
    }

    public void turnOnMovieMode(){
        light.on();
        light.turnDark();
        television.open();
        cdPlayer.on();
        curtain.close();
    }

    public void turnOffMovieMode(){
        light.turnLight();
        television.close();
        cdPlayer.off();
        curtain.open();
    }
}
