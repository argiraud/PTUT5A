package fr.polyrecrute.controllers;

import fr.polyrecrute.models.File__;
import fr.polyrecrute.services.EntityService;
import fr.polyrecrute.services.FileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Test", description = "The test API")
public class TestController {

    private final FileService fileService;
    private final EntityService entityService;

    @Autowired
    public TestController(FileService fileService, EntityService entityService) {
        this.fileService = fileService;
        this.entityService = entityService;
    }
/*
    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile pFile,
                                             @Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        Entity entity = entityService.getEntityFromToken(token);
        File file = fileService.storeFile(pFile, entity);

        String fileDownloadUri = "https://api.polyrecrute.tk/user/downloadFile/" + file.getIdFile();

        return new ResponseEntity<>(fileDownloadUri, HttpStatus.OK);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<ResponseEntity> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                                    @Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        return Arrays.stream(files)
                .map(file -> uploadFile(file, token))
                .collect(Collectors.toList());
    }
*/
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        File__ file = fileService.findById(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }
}
