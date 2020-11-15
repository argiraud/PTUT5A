package fr.polyrecrute.controllers;

import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.responceType.Offer;
import fr.polyrecrute.services.EntityService;
import fr.polyrecrute.services.OfferService;
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
@RequestMapping("/company")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Company", description = "The company API : you must be authenticated with a company account")
public class CompanyController {

    private final OfferService offerService;
    private final EntityService entityService;

    @Autowired
    public CompanyController(OfferService offerService, EntityService entityService) {
        this.offerService = offerService;
        this.entityService = entityService;
    }

    @Operation(summary = "Company create a new offer", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "Offer was created", content = @Content(schema = @Schema(implementation = Offer.class))),
                    @ApiResponse(responseCode = "400", description = "Title, key word or description is too long", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) })
    @PostMapping(value = "/offer/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> createOffer(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                             @RequestBody Offer pOffer) {
        Entity__ entity = entityService.getEntityFromToken(token);
        Offer__ offer = offerService.createOffer(entity.getCompany(), pOffer);
        return new ResponseEntity<>(offer.getTransactionalObject(), HttpStatus.CREATED);
    }

    @Operation(summary = "Company upload a file for one offer", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "File was upload", content = @Content(schema = @Schema(implementation = Offer.class))),
                    @ApiResponse(responseCode = "400", description = "Title, key word or description is too long", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content) })
    @PostMapping(value = "/offer/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> uploadFileOffer(@RequestParam("file") MultipartFile pFile,
                                                   @Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                                   @Parameter(description = "idOffer") String idOffer) {
        Offer__ offer = offerService.findById(idOffer);
        offerService.storeFile(pFile, offer.getTransactionalObject());
        return new ResponseEntity<>(offer.getTransactionalObject(), HttpStatus.OK);
    }

    @Operation(summary = "Company upload multiple files for one offer", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "Files was upload", content = @Content(schema = @Schema(implementation = Offer.class))),
                    @ApiResponse(responseCode = "400", description = "Title, key word or description is too long", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content) })
    @PostMapping(value = "/offer/uploadMultipleFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResponseEntity> uploadMultipleFilesOffer(@RequestParam("files") MultipartFile[] files,
                                                         @Parameter(hidden=true) @RequestHeader(name="Authorization") String token,
                                                         @Parameter(description = "idOffer") String idOffer) {
        return Arrays.stream(files)
                .map(file -> uploadFileOffer(file, token, idOffer))
                .collect(Collectors.toList());
    }
}
