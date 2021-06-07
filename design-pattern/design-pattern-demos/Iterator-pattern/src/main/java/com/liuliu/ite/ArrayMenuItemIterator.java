package com.liuliu.ite;

import com.liuliu.dynamic.pojo.MenuItem;

/**
 * @Author: liulei
 * @Time: 2021/3/8 14:22
 * @Description
 */

public class ArrayMenuItemIterator implements Iterator {

    private MenuItem[] menuItems;
    private int pos;

    public ArrayMenuItemIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        return pos < menuItems.length && menuItems[pos] != null;
    }

    @Override
    public MenuItem getNext() {
        return menuItems[pos++];
    }
}
