package com.liuliu;

import com.liuliu.singleton.EnumSingleton;
import com.liuliu.singleton.Hungry;
import com.liuliu.singleton.Lazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: liulei
 * @Time: 2021/3/5 12:39
 * @Description
 */

public class SingletonTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Hungry hungry;
        Lazy lazy = Lazy.getInstance();
        hungry = Hungry.getINstance();
        System.out.println(hungry);
        System.out.println(lazy);
        System.out.println(EnumSingleton.INSTANCE.hello());
        Constructor<Lazy> constructor = Lazy.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Lazy lazy1 = constructor.newInstance();
        System.out.println(lazy);
        System.out.println(lazy1);
        Constructor<EnumSingleton> enumSingletonConstructor = EnumSingleton.class.getDeclaredConstructor();
        enumSingletonConstructor.setAccessible(true);
        EnumSingleton enumSingleton = enumSingletonConstructor.newInstance();
        System.out.println(enumSingleton);
        enumSingleton.hello();
    }
}
