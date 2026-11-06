package SpringPracticeCode.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customSwagger(){
        return new OpenAPI().info(
                new Info().title("Personal Project").description("Learning Swagger here")
        )
                .servers(List.of(new Server().url("http://localhost:8080").description("Local"),
                        new Server().url("http://localhost:9001").description("Production")));
    }

}
