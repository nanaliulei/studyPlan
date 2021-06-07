package com.liuliu.dynamic.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: liulei
 * @Time: 2021/3/19 14:36
 * @Description
 */

public class MyInvocationHandler implements InvocationHandler {

    Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try{
            before();
            Object result = method.invoke(object, args);
            afterReturn();
            return result;
        }catch (Exception e) {
            afterThrowing();
            e.printStackTrace();
        }finally{
            after();
        }
        return null;
    }

    private void before(){
        System.out.println("进行调用前处理!");
    }

    private void afterReturn(){
        System.out.println("进行调用成功后处理");
    }

    private void afterThrowing(){
        System.out.println("进行调用异常后处理");
    }

    private void after(){
        System.out.println("进行调用后处理，不论成功还是异常！");
    }

}
