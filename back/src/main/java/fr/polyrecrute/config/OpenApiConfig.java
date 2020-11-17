package fr.polyrecrute.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@OpenAPIDefinition(
        externalDocs = @ExternalDocumentation(
                description = "API pour accéder aux données",
                url = ""),
        info = @Info(
                title="PolyRecrute API",
                version = "0.2"),
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
