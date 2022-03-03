package com.zeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }
    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }
    @Bean
    public Docket docket4(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("D");
    }
    //配置lswagger的docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");
        //通过environment。acceptsProfiles 判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //enable 是否启用swgger
                .enable(flag)
                //分组
                .groupName("增加")
                .select()
                /*
                RequestHandlerSelectors   配置要扫描的接口的方式
                basePackage("com.zeng.control")   指定要扫描的包
                any()   扫描全部
                none()  不扫描
                withClassAnnotation(GetMapping.class)   扫描类上的注解，参数是一个注解的反射对象
                withMethodAnnotation(RestController.class)  扫描方法上的注解
                 */
                .apis(RequestHandlerSelectors.basePackage("com.zeng.control"))
                //paths过滤什么路径
                //.paths(PathSelectors.ant("/zeng/**"))
                .build();
    }

    //配置swgger信息
    Contact contact = new Contact("zeng","https://www.baidu.com","22222@qq.com");
    private ApiInfo apiInfo(){
        return new ApiInfo(
                "曾嘉彬的Api文档 ",
                "Api 有钱秀你一脸",
                "1.0",
                "https://www.baidu.com",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
