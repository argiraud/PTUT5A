package fr.polyrecrute.controllers;

import fr.polyrecrute.models.Company__;
import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.responceType.Company;
import fr.polyrecrute.responceType.Offer;
import fr.polyrecrute.services.CompanyService;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Company", description = "The company API : you must be authenticated with a company account")
public class CompanyController {

    private final OfferService offerService;
    private final EntityService entityService;
    private final CompanyService companyService;

    @Autowired
    public CompanyController(OfferService offerService, EntityService entityService, CompanyService companyService) {
        this.offerService = offerService;
        this.entityService = entityService;
        this.companyService = companyService;
    }

    @Operation(summary = "Company create a new offer", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "Offer was created", content = @Content(schema = @Schema(implementation = Offer.class))),
                    @ApiResponse(responseCode = "400", description = "Title, key word or description is too long", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content),
                    @ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) })
    @PostMapping(value = "/company/offer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> createOffer(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                             @RequestBody Offer pOffer) {
        Entity__ entity = entityService.getEntityFromToken(token);
        Offer__ offer = companyService.createOffer(entity.getCompany(), pOffer);
        return new ResponseEntity<>(offer.getTransactionalObject(), HttpStatus.CREATED);
    }

    @Operation(summary = "Company upload a file for one offer", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "File was upload", content = @Content(schema = @Schema(implementation = Offer.class))),
                    @ApiResponse(responseCode = "400", description = "Title, key word or description is too long", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @PostMapping(value = "/company/offer/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> uploadFileOffer(@RequestParam("file") MultipartFile pFile,
                                                 @Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                                 @Parameter(description = "idOffer") String idOffer) {
        Offer__ offer = offerService.findById(idOffer);
        if (!offer.getCompany().getIdCompany().equals(entityService.getEntityFromToken(token).getCompany().getIdCompany()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No sufficient right");
        offerService.storeFile(pFile, offer.getTransactionalObject());
        return new ResponseEntity<>(offer.getTransactionalObject(), HttpStatus.CREATED);
    }

    @Operation(summary = "Company upload multiple files for one offer", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "Files was upload", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
                    @ApiResponse(responseCode = "400", description = "Title, key word or description is too long", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @PostMapping(value = "/company/offer/uploadMultipleFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResponseEntity> uploadMultipleFilesOffer(@RequestParam("files") MultipartFile[] files,
                                                         @Parameter(hidden=true) @RequestHeader(name="Authorization") String token,
                                                         @Parameter(description = "idOffer") String idOffer) {
        return Arrays.stream(files)
                .map(file -> uploadFileOffer(file, token, idOffer))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get all offers", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "All offers", content = @Content(schema = @Schema(implementation = Offer.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @GetMapping(value = "/company/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOffers() {
        List<Offer__> offers = offerService.getOffers();
        return new ResponseEntity<>(offerService.getTransactionalObjectList(offers), HttpStatus.OK);
    }

    @Operation(summary = "Get all offers for a company", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "All offers", content = @Content(schema = @Schema(implementation = Offer.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Company not found", content = @Content)})
    @GetMapping(value = "/company/{idEntity}/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getOffersByCompany(@Parameter(description = "idEntity") @PathVariable long idEntity) {
        Entity__ entity = entityService.findByUserId(idEntity);
        List<Offer__> offers = new ArrayList<>(entity.getCompany().getOffers());
        return new ResponseEntity<>(offerService.getTransactionalObjectList(offers), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all companies", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "All companies", content = @Content(schema = @Schema(implementation = Company.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company__> companies = companyService.findAll();
        return new ResponseEntity<>(companyService.getTransactionalObjectList(companies), HttpStatus.OK);
    }

    @Operation(summary = "Delete offer", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Delete offer", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @DeleteMapping(value = "/company/offer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteOffer(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                      @Parameter(description = "idOffer") @RequestParam String idOffer) {
        Entity__ entity = entityService.getEntityFromToken(token);
        companyService.deleteOffer(entity.getCompany(), idOffer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
