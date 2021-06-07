package com.liuliu.dynamic.pojo;

import com.liuliu.ite.ArrayMenuItemIterator;

/**
 * @Author: liulei
 * @Time: 2021/3/8 14:31
 * @Description
 */

public class MenuItemArray {

    private MenuItem[] menuItems;

    private void initMenuItem(){
        menuItems = new MenuItem[10];
        menuItems[0] = new MenuItem("apple", 10);
        menuItems[1] = new MenuItem("banana", 6);
        menuItems[2] = new MenuItem("cheerys", 30);
    }

    public MenuItemArray() {
        initMenuItem();
    }

    public ArrayMenuItemIterator getIterator(){
        return new ArrayMenuItemIterator(menuItems);
    }
}
