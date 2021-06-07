package com.liuliu.dynamic.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/3/15 10:44
 * @Description
 */

public class Menu implements Composite {

    String name;
    List<Composite> composites;

    public Menu(String name) {
        this.name = name;
        composites = new ArrayList<>();
    }

    public Menu(List<Composite> composites) {
        this.composites = composites;
    }

    public void addItem(Composite composite){
        composites.add(composite);
    }

    @Override
    public List<Composite> getMenuItems() {
        return composites;
    }

    @Override
    public void display() {
        System.out.println(name + ": ");
        for (Composite composite : composites) {
            composite.display();
        }
    }
}
