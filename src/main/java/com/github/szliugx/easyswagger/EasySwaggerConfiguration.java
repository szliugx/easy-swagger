package com.github.szliugx.easyswagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger相关配置
 *
 * @author szliugx@gmail.com
 * @create 2018-08-06 上午10:59
 **/
@Configuration
@ComponentScan(basePackages = "com.github.szliugx.easyswagger")
@EnableSwagger2
public class EasySwaggerConfiguration {

    @Autowired
    private EasySwaggerConfigurationProperties easySwaggerConfigurationProperties;

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .enable(easySwaggerConfigurationProperties.getEnable())
                .groupName(easySwaggerConfigurationProperties.getGroupName())
                .apiInfo(apiInfo());
        ApiSelectorBuilder builder = docket.select();
        if (!StringUtils.isEmpty(easySwaggerConfigurationProperties.getBasePackage())) {
            builder = builder.apis(RequestHandlerSelectors.basePackage(easySwaggerConfigurationProperties.getBasePackage()));
        }
        if (!StringUtils.isEmpty(easySwaggerConfigurationProperties.getAntPath())) {
            builder = builder.paths(PathSelectors.ant(easySwaggerConfigurationProperties.getAntPath()));
        }

        return builder.build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(easySwaggerConfigurationProperties.getTitle())
                //描述
                .description(easySwaggerConfigurationProperties.getDescription())
                //创建人
                .contact(new Contact("easy-swagger", "http://www.github.com/szliugx/easy-swagger", "szliugx@gmail.com"))
                //版本号
                .version(easySwaggerConfigurationProperties.getVersion())
                //开源协议
                .license(easySwaggerConfigurationProperties.getLicense())
                .build();
    }
}
