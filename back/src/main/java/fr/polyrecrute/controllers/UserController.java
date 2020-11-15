package fr.polyrecrute.controllers;

import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.services.EntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Authorization")
@Tag(name = "User", description = "The user API")
public class UserController {

    private final EntityService entityService;

    @Autowired
    public UserController(EntityService entityService) {
        this.entityService = entityService;
    }

    @Operation(summary = "Get entity details", description = "Get more details like first name, birth date, etc",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Entity details",
                            content = @Content(schema = @Schema(implementation = EntityDetails.class))),
                    @ApiResponse(responseCode = "400", description = "Error token", content = @Content) })
    @GetMapping(value = "/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityDetails> userDetails(@Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        return new ResponseEntity<>(entityService.getDetails(entityService.getEntityFromToken(token)) , HttpStatus.OK);
    }
}
