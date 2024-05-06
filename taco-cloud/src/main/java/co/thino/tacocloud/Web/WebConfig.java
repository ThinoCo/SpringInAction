package co.thino.tacocloud.Web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*
         * 将 对路径"/"的GET请求转发到"home"视图
         * ViewControllerRegistry 用于注册视图控制器
         */
        registry.addViewController("/").setViewName("home");
    }
    
}
