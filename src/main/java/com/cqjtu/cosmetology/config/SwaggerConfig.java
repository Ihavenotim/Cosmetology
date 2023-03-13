package com.cqjtu.cosmetology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cqjtu.cosmetology"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("重庆交通大学实训文档演示")
                .description("美容店")
                .version("1.0.0")
                .contact(new Contact("wang","https://user.qzone.qq.com/1466679570?ADUIN=1808446037&ADSESSION=1677718196&ADTAG=CLIENT.QQ.5929_FriendInfo_PersonalInfo.0&ADPUBNO=27255&source=namecardstar","2379200155@qq.com"))
                .build();
    }
}