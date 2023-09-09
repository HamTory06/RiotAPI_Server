package com.api.study.riot_api.infrastructure.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class SwaggerConfiguration {
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .components(Components())
            .info(apiInfo())
    }

    private fun apiInfo(): Info {
        return Info()
            .title("라이엇 api")
            .description("라이엇 api swagger")
            .version("1.0.0")
    }
}