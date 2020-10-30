package fr.polyrecrute;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("fr.polyrecrute.repository")
@EntityScan("fr.polyrecrute.models")
@ComponentScan({"fr.polyrecrute.services", "fr.polyrecrute.config", "fr.polyrecrute.controllers", "fr.polyrecrute.security"})
public class SpringBootRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRunner.class, args);
    }

}