# spring-note

spring最核心的两个模块，IOC和AOP。

## IOC（DI)

### IOC的定义

### IOC与DI的关系

如果类A由类B和类C组成（A中包含B、C两个变量），那么我们可以说A依赖B、C。通常情况下，我们通过new B（），new C（）的方式来创建两个类，或者通过set方法来设置B、C。但在spring中，我们只需要声明B、C两个变量，并在xml中配置B、C对应的bean或在两个变量上添加@Autowired注解，即可使用。这就是IOC最常见的应用。

控制反转（Inversion of control）是指类将创建对象的控制权交由IOC容器处理，即类中只需要声明对象，不再负责对象的创建；

依赖注入（Dependency Inject）是指IOC容器将类需要的对象注入其中，注入方式包括set注入、构造器注入等。

可以发现，IOC和DI是对同一件事情在不同角度的描述。IOC是站在类的角度，而DI是站在IOC容器的角度。

### IOC的优点：

* 实现了创建与

## AOP

面向切面编程（Aspect Oriental Program）	。

* 切点：
* 切面：
* 切面类：



## `xml`模式

**启动调用栈：**

```
org.springframework.context.support.AbstractApplicationContext#refresh
  org.springframework.context.support.AbstractApplicationContext#obtainFreshBeanFactory
    org.springframework.context.support.AbstractRefreshableApplicationContext#refreshBeanFactory
    
```



### `BeanDefinition`

BeanDefinition创建及注册流程：

```
org.springframework.context.support.AbstractRefreshableApplicationContext#loadBeanDefinitions
  org.springframework.context.support.AbstractXmlApplicationContext#loadBeanDefinitions
    org.springframework.beans.factory.xml.XmlBeanDefinitionReader#loadBeanDefinitions
      org.springframework.beans.factory.xml.XmlBeanDefinitionReader#doLoadBeanDefinitions
        org.springframework.beans.factory.xml.XmlBeanDefinitionReader#registerBeanDefinitions
          org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader#registerBeanDefinitions
            org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader#doRegisterBeanDefinitions
              org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader#parseBeanDefinitions
                org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader#parseDefaultElement
                  org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader#processBeanDefinition--获取bdholder并注册
                    org.springframework.beans.factory.xml.BeanDefinitionParserDelegate#parseBeanDefinitionElement--创建bdholder，并设置bd属性
                      org.springframework.beans.factory.xml.BeanDefinitionParserDelegate#createBeanDefinition
                        org.springframework.beans.factory.support.BeanDefinitionReaderUtils#createBeanDefinition --最后创建bd
```

BeanDefinition注册子流程：

```
org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader#processBeanDefinition--获取bdholder并注册
  org.springframework.beans.factory.support.BeanDefinitionReaderUtils#registerBeanDefinition --注册bd，并调用组件注册事件
    org.springframework.beans.factory.support.DefaultListableBeanFactory#registerBeanDefinition --注册bd及别名
```

### BeanDefinition重要属性介绍

factory related



### 单例bean加载

```
org.springframework.context.support.AbstractApplicationContext#finishBeanFactoryInitialization
  org.springframework.beans.factory.config.ConfigurableListableBeanFactory#preInstantiateSingletons
    org.springframework.beans.factory.support.AbstractBeanFactory#getBean
      org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean
        org.springframework.beans.factory.support.AbstractBeanFactory#createBean
          org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean
            org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBeanInstance
              org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#instantiateBean
                org.springframework.beans.BeanUtils#instantiateClass	
```

### FactoryBean



### 循环依赖问题

### BeanPostProcessor

加载：

```
org.springframework.context.support.AbstractApplicationContext#refresh
  org.springframework.context.support.AbstractApplicationContext#registerBeanPostProcessors
    org.springframework.context.support.AbstractApplicationContext#registerBeanPostProcessors
      org.springframework.context.support.PostProcessorRegistrationDelegate#registerBeanPostProcessors
```

调用：

```
org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean
  org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation
    org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#doCreateBean
      org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#initializeBean  --populateBean之后
        org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization
        org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization
```



## 注解模式