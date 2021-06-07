package com.liuliu.ite;

import com.liuliu.dynamic.pojo.MenuItem;

import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/3/8 14:15
 * @Description
 */

public class ListMenuItemIterator implements Iterator {

    List<MenuItem> menuItems;

    int pos;

    public ListMenuItemIterator(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        return pos < menuItems.size();
    }

    @Override
    public MenuItem getNext() {
        return menuItems.get(pos++);
    }
}
