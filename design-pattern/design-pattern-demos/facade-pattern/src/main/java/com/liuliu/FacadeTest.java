package com.liuliu;

import com.liuliu.facade.MovieFacade;
import com.liuliu.dynamic.pojo.CDPlayer;
import com.liuliu.dynamic.pojo.Curtain;
import com.liuliu.dynamic.pojo.Light;
import com.liuliu.dynamic.pojo.Television;

/**
 * @Author: liulei
 * @Time: 2021/3/5 11:07
 * @Description
 */

public class FacadeTest {

    public static void main(String[] args) {
        MovieFacade movieFacade = new MovieFacade(new Light(), new Television(), new CDPlayer(), new Curtain());
        movieFacade.turnOnMovieMode();
        System.out.println("close the movie mode:\n");
        movieFacade.turnOffMovieMode();
    }
}
