# $Nacos$

## common

## address

## naming

```java
com.alibaba.nacos.api.naming.NamingFactory#createNamingService
  com.alibaba.nacos.client.naming.NacosNamingService#init
  
```



**注册：**

```java
com.alibaba.nacos.api.naming.NamingService#registerInstance
  com.alibaba.nacos.client.naming.net.NamingProxy#registerService
    com.alibaba.nacos.client.naming.net.NamingProxy#reqApi
      com.alibaba.nacos.client.naming.net.NamingProxy#callServer
        com.alibaba.nacos.common.http.client.NacosRestTemplate#exchangeForm
          com.alibaba.nacos.common.http.client.NacosRestTemplate#execute
            com.alibaba.nacos.common.http.client.request.JdkHttpClientRequest#execute
```

**心跳：**



## client

## console

