package fr.polyrecrute.controllers;

import fr.polyrecrute.models.File__;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utils")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Utils", description = "The utils API")
public class UtilsController {

    private final FileService fileService;

    public UtilsController(FileService fileService) {
        this.fileService = fileService;
    }

    @Operation(summary = "Get file", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Return file ",
                            content = @Content(schema = @Schema(implementation = EntityDetails.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized path", content = @Content) })
    @GetMapping(value = "/downloadFile/{fileId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        File__ file = fileService.findById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }
}
