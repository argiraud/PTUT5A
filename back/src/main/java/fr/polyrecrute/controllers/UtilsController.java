package fr.polyrecrute.controllers;

import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.File__;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.services.EntityService;
import fr.polyrecrute.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/utils")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Utils", description = "The utils API")
public class UtilsController {

    private final FileService fileService;
    private final EntityService entityService;

    public UtilsController(FileService fileService, EntityService entityService) {
        this.fileService = fileService;
        this.entityService = entityService;
    }

    @Operation(summary = "Get file", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Return file ", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized path", content = @Content) })
    @GetMapping(value = "/file/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        File__ file = fileService.findById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }//downloadFile

    @Operation(summary = "Delete file", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "File was delete", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized path", content = @Content),
                    @ApiResponse(responseCode = "404", description = "File not found", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @DeleteMapping(value = "/file", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteFile(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                               @Parameter(description = "idOffer") @RequestParam String idFile) {
        Entity__ entity = entityService.getEntityFromToken(token);
        File__ file = fileService.findById(idFile);
        if (file == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
        entityService.deleteFile(entity, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
