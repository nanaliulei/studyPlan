# springcloud 学习笔记

## 应用架构发展

* 单体
* 垂直切分
* SOA（面向服务架构，如dubbo）
* 微服务架构（在SOA基础上的发展，比SOA对服务的拆分粒度更细，提供了一站式的微服务解决方案），关键**微小、独立、轻量级通信（restful）**

## 微服务架构思想及优缺点

微服务结构的核心：**拆分粒度小**。

### 微服务架构的优点

* 微服务很小，便于业务聚焦；
* 每个服务交由一个团队开发，实现解耦；
* 服务之间可以使用不同语言进行开发；
* 服务组件便于重用和组合；
* 单一组件升级影响较小，便于使用新技术；
* 可以更好的实现DevOps开发运维⼀体化。

### 微服务架构的缺点

* 服务多，分布式管理复杂；
* 分布式链路追踪困难。

### 微服务架构的一些概念

* **服务注册与发现**，服务提供者注册到注册中心，消费者去注册中心拉取服务提供者列表，根据负载均衡等选取服务提供者进行调用；
* **负载均衡**，服务提供者一般为集群，需要根据负载均衡策略选取提供者进行调用；
* **熔断**，微服务之间一般存在复杂的调用关系，如A调用B，B调用C，当其中一环出现问题（处理缓慢或者宕机），那么请求就会累积在C，而B的调用得不到返回结果，请求也会累积在B，再导致请求累积在A，最后ABC服务全面崩盘，也叫服务雪崩。如果C是一个非核心服务，那么C服务故障是不应该导致主业务A不能正常提供服务的，此时可以再调用C时设置熔断策略，如果得不到返回，则后续直接返回预设数据；
* **分布式链路追踪**，一个请求进入后，整个调用链路上所有的服务设置相同的链路id，方便服务调用链路的追踪；
* **API网关**，服务的统一入口（可以前置nginx），主要完成服务的路由（通过请求url路径等方式）、负载均衡、统一鉴权等。

## springcloud综述

**百度百科**Spring Cloud是⼀系列框架的有序集合。它利⽤Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，如服务发现注册、配置中⼼、消息总线、负载均衡、断路器、数据监控等，都可以⽤ Spring Boot的开发⻛格做到⼀键启动和部署。

**提炼几个关键点：**

* springcloud是一系列框架的有序集合，也就是一个规范（SCN、SCA都是具体的实现）；
* springcloud利用了springboot的开发风格（自动装配），简化了微服务架构的开发；
* 开发 服务发现注册、配置中⼼、消息总线、负载均衡、断路器、数据监控。

springcloud要解决问题就是微服务架构实施中会遇到的一些问题，如**服务注册与发现，网络问题处理（熔断场景）、负载均衡、分布式链路跟踪、统一鉴权等**。

 ### 关键组件

![image-20210114193912050](C:\Users\ll\AppData\Roaming\Typora\typora-user-images\image-20210114193912050.png)

### 工作机制

![image-20210114194322704](C:\Users\ll\AppData\Roaming\Typora\typora-user-images\image-20210114194322704.png)

* 客户的API调用请求发送到API网关；
* 网关经过统一鉴权、服务路由后，调用负载均衡策略选取的服务；
* 服务间的调用使用Feign或Dubbo调用，使用Ribbon或Dubbo进行负载均衡，使用hystrix或sentinel进行限流熔断；
* 使用Eureka或Nacos注册中心进行服务注册与发现；
* 使用springcloud Config或nacos、apollo作为配置中心，进行配置的集中管理；
* 使用消息总线（消息队列实现）进行配置中心的配置同步。

## 负载均衡ribbon

### 源码分析

**@LoadBalanced注解**

```
/**
 * Annotation to mark a RestTemplate or WebClient bean to be configured to use a
 * LoadBalancerClient.
 * 注解里本身没什么内容，只是标识添加了该注解的RestTemplate会被配置成一个LoadBalanceClient来使用
 * @author Spencer Gibb
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface LoadBalanced {

}
```

**经验：**springcloud使用了springboot的自动装配机制，因此在对应的包下spring.factories下查找。在spring-cloud-netflix-ribbon-2.2.6.RELEASE.jar包下找到了

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration
```

**RibbonAutoConfiguration分析**

* 注解分析

```
@Configuration
@Conditional(RibbonAutoConfiguration.RibbonClassesConditions.class)
@RibbonClients
//在加载完EurekaClientAutoConfiguration之后再加载RibbonAutoConfiguration
@AutoConfigureAfter(
      name = "org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration")
//在加载LoadBalancerAutoConfiguration、AsyncLoadBalancerAutoConfiguration之前加载RibbonAutoConfiguration
@AutoConfigureBefore({ LoadBalancerAutoConfiguration.class,
      AsyncLoadBalancerAutoConfiguration.class })
@EnableConfigurationProperties({ RibbonEagerLoadProperties.class,
      ServerIntrospectorProperties.class })
public class RibbonAutoConfiguration {******}
```

**LoadBalancerAutoConfiguration分析**

```
@Configuration(proxyBeanMethods = false)
//只有存在RestTemplate这个类时，才会加载类中的bean
@ConditionalOnClass(RestTemplate.class)
//只有springcontext中加载了LoadBalancerClient这个bean，才会加载类中的bean
@ConditionalOnBean(LoadBalancerClient.class)
@EnableConfigurationProperties(LoadBalancerRetryProperties.class)
public class LoadBalancerAutoConfiguration {
	//加载所有添加了@LoadBalanced的RestTemplate
	@LoadBalanced
	@Autowired(required = false)
	private List<RestTemplate> restTemplates = Collections.emptyList();
******
}
```

