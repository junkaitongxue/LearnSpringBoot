package com.dreamkite.demo.configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * usage: 浏览器输入 http://{{ip}}:{{port}}/doc.html#/home
 * example:  http://127.0.0.1:8080/doc.html#/home
 * <p>
 * 简单说knife4j就swagger的升级版API文档的一个框架,但是用起来比swagger方便多了，UI更加丰富。
 * 使用一个配置类来承载这样就不需要再springboot启动服务的地方进行注解，如下
 * <code>
 *
 * @EnableSwagger2
 * @EnableKnife4j
 * @SpringBootApplication //...
 * </code>
 */
@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket createWMS() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("DreamKite")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dreamkite"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 设置api信息
     * <p>
     * .contact    设置文档的联系方式
     * .title  设置文档的标题
     * .description    设置文档的描述
     * .version    设置文档的版本信息
     * .termsOfServiceUrl  设置文档的License信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("dreamkite", "", ""))
                .title("API接口")
                .description("Rest 接口测试")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    /**
     * 增加如下配置可解决Spring Boot 6.x 与Swagger 3.0.0 不兼容问题
     **/
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier,
                                                                         ServletEndpointsSupplier servletEndpointsSupplier,
                                                                         ControllerEndpointsSupplier controllerEndpointsSupplier,
                                                                         EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties,
                                                                         WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(),
                new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
                || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}

