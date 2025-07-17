package com.vvdn.nms.nms_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("NMS Backend API")
                        .description("API documentation for NMS system")
                        .version("1.0.0"));
    }
}
