package cc.insist.manage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: cc
 * @Date: 2023/11/20 11:14 AM
 * @Description:
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("标题")
                        .description("我的API文档")
                        .version("v1")
                        .contact(new Contact().name("cc").email("11111").url("https://www.baidu.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
