package com.online.shopping.hub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ecommerce API")
                        .description("Documentation Ecommerce API")
                        .version("1.0.0")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Support Team")
                                .email("ecsitesupport@example.com")
                                .url("https://www.ecsite.com/contact"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.online.shopping.hub"))
//                .paths(PathSelectors.any())
//                .build();

    }
}
