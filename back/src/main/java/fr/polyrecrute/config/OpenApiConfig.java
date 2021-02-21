package fr.polyrecrute.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        externalDocs = @ExternalDocumentation(
                description = "API pour accéder aux données",
                url = ""),
        info = @Info(
                title="PolyRecrute API",
                version = "1.0"),
        servers = { @Server(url = "https://api.polyrecrute.tk", description = "Prod API server") }
)
@SecurityScheme(
        name = "Authorization",
        description = "Enter JWT Bearer token",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {

}
