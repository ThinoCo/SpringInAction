### 第1章 Spring入门

[toc]

#### 1.1 什么是Spring

**应用程序**是由不同功能的`组件`组合在一起实现`整体功能`，所以程序需要以某种方式`创建`并`组合`所有的组件。



**Spring**的核心是一个**容器**（又称为应用程序上下文:`context`），它创建并管理**组件**(也叫`Bean`)

除容器外，还有Web框架、数据持久性选项、安全框架、系统集成、运行时监视、微服务、响应式编程等功能实现现代程序开发

**依赖注入**:`DI`将组件连接在一起。

连接Bean的方式：

* 显式
  * xml
  * java
* Spring Boot自动配置
  * 自动装配: 
  * 组件扫描: 



#### 1.2 初始化Spring应用程序

##### 1.2.1 从[官方网站](https://start.spring.io/)初始化

* Peoject
  * 选择Maven
* Spring Boot
  * 3.2.5版本
* Metadata
  * Group: **`co.thino`**
  * Artifact: `taco-cloud`
  * Name: 自动与Artifact一样
  * Description: ` Taco Cloud Example`
  * Package name: ` taco-cloud`
  * Packaging:  选`Jar`
  * Java: 选 `17`
* Dependencies
  * Spring Boot DevTools
  * Thymeleaf
  * Spring Web
* 最后点击**GENERATE**按键，创建并下载`.zip`文件

##### 1.2.2 Spring项目结构

* `taco-cloud`
  * `src`
    * `main/java`  **`TacoCloudApplication.java`** 引导项目的Spring Boot主类
    * `main/resources`
      * `statics` 浏览器展示的**静态内容**（图片、样式表、JavaScript）
      * `templates` 浏览器呈现内容的**模板文件**（放置Thymeleaf模板）
      * `application.properties` 指定配置属性
    * test/java
* `pom.xml`: Maven构建规范
* `mvnw`  `mvnw.cmd`: ` Maven`包装器脚本（使计算机在未安装Maven仍可使用脚本**构建项目**）



###### TacoCloudApplication.java

`@SpringBootApplication`注解解释

* 表明 是一个Spring引导应用程序
* 结合了以下三个注释
  * `@SpringBootConfiguration` 是`@Configuration`的一种形式，指定当前类是一个**配置类**
  * `@EnableAutoConfiguration` 启用**Spring自动配置**，让Spring Boot自动配置其认为需要的任何组件
  * `@ComponentScan` 启用**组件扫描**，可识别带有`@Component`、 ` @Controller` 、` @Service` 注释的类

`main()`方法

* 在执行JAR文件时运行

* `run()`方法

  * 指定应用程序的`实际引导`
  * 创建`Spring应用程序上下文`

  

#### 1.3 编写Spring应用程序

添加一个主页，处理主页的请求。

##### 1.3.1 处理web请求

*HomeController.java*

```java
package co.thino.tacocloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
```

**解释**

* `@Controller` 让当前类可以被Spring Boot扫描并在应用程序上下文创建`Bean`
* `@GetMapping("/")` 对根目录的**GET**请求将调用`home()`，返回`home.html`、将在浏览器展示。
  * `home.html` 在 `src/main/resource/templates` 中

##### 1.3.2 定义试图

*home.html*

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
  <head>
      <title>Taco Cloud</title>
  </head>

  <body>
      <h1>Welcome to...</h1>
      <img th:src="@{/images/TacoCloud.png}"/>
  </body>
</html>
```

**解释**

* `th:src` 属性和`@{}` 表达式 引用静态图片文件

* 图片完整路径是： ` /src/main/resources/static/images/TacoCloud.png`

  

##### 1.3.3 测试控制器

##### 1.3.4 构建并运行应用程序

* 在`VS Code`中 选择`TacoCloudApplication.java`，在main()方法上有**Run | Debug** 点击**Run**即可

* `VS Code`终端输出日志，完成启动。

  * ```shell
     /usr/bin/env /Users/kevinhsiao/.vscode/extensions/redhat.java-1.30.0-darwin-arm64/jre/17.0.10-macosx-aarch64/bin/java @/var/folders/zp/zrzcxb091q7f4cq86gty_4hh0000gn/T/cp_3w0butxnpv
    sk9cjtuzc6snsd0.argfile co.thino.tacocloud.TacoCloudApplication 
    
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::                (v3.2.5)
    
    2024-04-30T23:57:27.352+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] c.thino.tacocloud.TacoCloudApplication   : Starting TacoCloudApplication using Java 17.0.10 with PID 81347 (/Users/kevinhsiao/Desktop/Thino Co./Spring/SpringInAction/taco-cloud/target/classes started by kevinhsiao in /Users/kevinhsiao/Desktop/Thino Co./Spring/SpringInAction/taco-cloud)
    2024-04-30T23:57:27.353+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] c.thino.tacocloud.TacoCloudApplication   : No active profile set, falling back to 1 default profile: "default"
    2024-04-30T23:57:27.376+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
    2024-04-30T23:57:27.376+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
    2024-04-30T23:57:27.776+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
    2024-04-30T23:57:27.782+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
    2024-04-30T23:57:27.782+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.20]
    2024-04-30T23:57:27.805+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
    2024-04-30T23:57:27.806+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 429 ms
    2024-04-30T23:57:27.964+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
    2024-04-30T23:57:27.981+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
    2024-04-30T23:57:27.987+08:00  INFO 81347 --- [taco-cloud] [  restartedMain] c.thino.tacocloud.TacoCloudApplication   : Started TacoCloudApplication in 0.812 seconds (process running for 1.023)
    ```

* 浏览器访问 `localhost:8080`



##### 1.3.5 Spring Boot DevTool

**作用**

* 代码更改时，自动重启应用程序
* 静态资源变更时，浏览器自动刷新
* 自动禁用模板缓存