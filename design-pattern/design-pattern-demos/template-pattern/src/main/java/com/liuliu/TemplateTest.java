package com.liuliu;

import com.liuliu.dynamic.pojo.Coffee;
import com.liuliu.dynamic.pojo.Tea;
import com.liuliu.template.DrinkTemplate;

/**
 * @Author: liulei
 * @Time: 2021/3/5 10:38
 * @Description
 */

public class TemplateTest {

    public static void main(String[] args) {
        DrinkTemplate tea = new Tea();
        DrinkTemplate coffee = new Coffee();
        System.out.println("make tea:");
        tea.makeDrink();
        System.out.println("make coffee");
        coffee.makeDrink();
    }
}
