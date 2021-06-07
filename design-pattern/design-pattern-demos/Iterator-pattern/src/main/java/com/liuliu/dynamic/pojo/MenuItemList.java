package com.liuliu.dynamic.pojo;

import com.liuliu.ite.ListMenuItemIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/3/8 14:25
 * @Description
 */

public class MenuItemList {

    private List<MenuItem> menuItems;

    private void initMenuItem(){
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("beef", 38));
        menuItems.add(new MenuItem("pork", 30));
        menuItems.add(new MenuItem("salad", 20));
    }

    public MenuItemList() {
        initMenuItem();
    }

    public ListMenuItemIterator getIterator(){
        return new ListMenuItemIterator(menuItems);
    }
}
