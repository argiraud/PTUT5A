package fr.polyrecrute.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        externalDocs = @ExternalDocumentation(
                description = "API du PTUT de ses morts !!!! j'ai mis 30 h juste pour cette interface de ses morts !!! ",
                url = "https://quiveutdesgoodies.com/products/t-shirt-de-ses-morts"),
        info = @Info(
                title="PolyRecrute API",
                version = "0.1"),
        servers = { @Server(url = "https://api.polyrecrute.tk", description = "Prod API server") }
)
public class OpenApiConfig {
}
