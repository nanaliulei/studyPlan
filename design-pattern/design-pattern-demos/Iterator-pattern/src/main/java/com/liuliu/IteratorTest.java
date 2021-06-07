package com.liuliu;

import com.liuliu.ite.Iterator;
import com.liuliu.dynamic.pojo.MenuItem;
import com.liuliu.dynamic.pojo.MenuItemArray;
import com.liuliu.dynamic.pojo.MenuItemList;

/**
 * @Author: liulei
 * @Time: 2021/3/8 14:33
 * @Description
 */

public class IteratorTest {

    public static void main(String[] args) {
        System.out.println('a' > 'b');
        Iterator ite = new MenuItemArray().getIterator();
        while (ite.hasNext()){
            System.out.println((MenuItem)ite.getNext());
        }
        ite = new MenuItemList().getIterator();
        while (ite.hasNext()){
            System.out.println((MenuItem)ite.getNext());
        }
    }
}
