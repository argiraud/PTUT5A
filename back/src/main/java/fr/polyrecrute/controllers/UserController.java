package fr.polyrecrute.controllers;

import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.responceType.LongResponse;
import fr.polyrecrute.services.EntityService;
import fr.polyrecrute.services.UserService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
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
    @GetMapping(value = "/user/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityDetails> userDetails(@Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        return new ResponseEntity<>(entityService.getDetails(entityService.getEntityFromToken(token)) , HttpStatus.OK);
    }

    @Operation(summary = "Entity upload a file", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "File was upload", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content) })
    @PostMapping(value = "/user/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile pFile,
                                                 @Parameter(hidden = true) @RequestHeader(name = "Authorization") String token) {
        Entity__ entity = entityService.getEntityFromToken(token);
        entityService.storeFile(pFile, entity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Entity upload multiple files", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "Files was upload", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content) })
    @PostMapping(value = "/user/offer/uploadMultipleFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResponseEntity> uploadMultipleFilesOffer(@RequestParam("files") MultipartFile[] files,
                                                         @Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        return Arrays.stream(files)
                .map(file -> uploadFile(file, token))
                .collect(Collectors.toList());
    }


}
