package com.liuliu;

import com.liuliu.dynamic.pojo.Menu;
import com.liuliu.dynamic.pojo.MenuComposite;

/**
 * @Author: liulei
 * @Time: 2021/3/15 10:54
 * @Description
 */

public class CompositeTest {

    public static void main(String[] args) {
        Menu menu = new Menu("主菜单");
        menu.addItem(new MenuComposite("地三鲜", "素菜"));
        menu.addItem(new MenuComposite("红烧肉", "肉菜"));
        Menu dessertMenu = new Menu("甜点菜单");
        dessertMenu.addItem(new MenuComposite("蓝莓山药", "素菜"));
        dessertMenu.addItem(new MenuComposite("面包诱惑", "主食"));
        menu.addItem(dessertMenu);
        menu.display();
    }
}
