package backend.findjob.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
@OpenAPIDefinition(
        info = @Info(
                description = "API Findjob",
                title = "OpenApi - FindJob",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "LOCAL",
                        url = "http://localhost:443"
                ),
                @Server(
                        description = "PRODUCT",
                        url = "https://azure-findjob-springboot.azurewebsites.net"
                )
        }
)
@SecurityScheme(
        name = "Bearer Auth",
        description = "JWT authentication",
        scheme = "bearer",
        type =  SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
