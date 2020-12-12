# 学习笔记

## Week05 作业题目（周四）

### 1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。

Jdk动态代理只能代理实现了接口的类。

代码示例：[JavaProxy.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work01/JavaProxy.java)   [ProxyFactory.java](..//spring-demo/src/main/java/com/wkk/learn/java/springdemo/work01/ProxyFactory.java)

### 2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 Github。

* 1.通过xml文件配置的方式实现bean装配
  [XmlInjectionDemo.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work02/XmlInjectionDemo.java)
  [xmlInjectionApplicationContext.xml](../spring-demo/src/main/resources/xmlInjectionApplicationContext.xml)

* 2.通过注解的方式实现bean装配
  [AnnotationInjectionDemo.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work02/AnnotationInjectionDemo.java)

* 3.通过自动配置的方式实现bean装配
  [AutowireInjectDemo.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work02/AutowireInjectDemo.java)
  [xmlInjectionApplicationContext.xml](../spring-demo/src/main/resources/xmlInjectionApplicationContext.xml)

* 4.通过BeanFactoryAware的方式实现bean装配
  [AwareInjectDemo.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work02/AwareInjectDemo.java)
  [beanFactoryInjectionApplicationContext.xml](../spring-demo/src/main/resources/beanFactoryInjectionApplicationContext.xml)

* 5.通过API的方式实现bean装配
  [ApiInjectionDemo.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work02/ApiInjectionDemo.java)

### 3.（选做）实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。

* 设置xsd文件 [student.xsd](../spring-demo/src/main/resources/META-INF/namespace/student.xsd)

* 添加handler和xml解析类

  [StudentHandler.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work03/handler/StudentHandler.java)  [KlassParser.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work03/parser/KlassParser.java)  [StudentParser.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work03/parser/StudentParser.java)

* 配置spring.handlers，spring.schemas

  [spring.handlers](../spring-demo/src/main/resources/META-INF/spring.handlers)  [spring.schemas](../spring-demo/src/main/resources/META-INF/spring.schemas)

### 4.（选做，会添加到高手附加题）

4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；
4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；
4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；
4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。

## Week05 作业题目（周六）

### 1.（选做）总结一下，单例的各种写法，比较它们的优劣。

* 
### 2.（选做）maven/spring 的 profile 机制，都有什么用法？

### 3.（选做）总结 Hibernate 与 MyBatis 的各方面异同点。

### 4.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

* 配置AutoConfiguration: [spring.factories](../student-school-starter/src/main/resources/META-INF/spring.factories) [StudentSchoolAutoConfiguration.java](../student-school-starter/src/main/java/com/wkk/learn/java/studentschoolstarter/entity/StudentSchoolAutoConfiguration.java)

* 添加配置元数据文件
  [spring-configuration-metadata.json](../student-school-starter/src/main/resources/META-INF/spring-configuration-metadata.json)

* 启动类
  [StudentSchoolStarterApplication.java](../spring-demo/src/main/java/com/wkk/learn/java/springdemo/work04/StudentSchoolStarterApplication.java)

* 配置文件

  [application.yml](../spring-demo/src/main/resources/application.yml)

### 5.（选做）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。

### 6.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：

1）使用 JDBC 原生接口，实现数据库的增删改查操作。
2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。