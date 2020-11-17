package fr.polyrecrute.controllers;

import fr.polyrecrute.responceType.LongResponse;
import fr.polyrecrute.services.CompanyService;
import fr.polyrecrute.services.OfferService;
import fr.polyrecrute.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kpi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Authorization")
@Tag(name = "KPI", description = "The KPI API : you must be authenticated with a Admin account")
public class KPIController {

    private final OfferService offerService;
    private final CompanyService companyService;
    private final UserService userService;

    @Autowired
    public KPIController(OfferService offerService, CompanyService companyService, UserService userService) {
        this.offerService = offerService;
        this.companyService = companyService;
        this.userService = userService;
    }


    @Operation(summary = "Count of users", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Count of companies", content = @Content(schema = @Schema(implementation = LongResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content) })
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LongResponse> getCountUsers() {
        return new ResponseEntity<>(new LongResponse(userService.countAll()), HttpStatus.OK);
    }

    @Operation(summary = "Count of companies", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Count of companies", content = @Content(schema = @Schema(implementation = LongResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LongResponse> getCountCompany() {
        return new ResponseEntity<>(new LongResponse(companyService.countAll()), HttpStatus.OK);
    }

    @Operation(summary = "Count of offers", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Count of offers", content = @Content(schema = @Schema(implementation = LongResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @GetMapping(value = "/companies/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LongResponse> getCountOffers() {
        return new ResponseEntity<>(new LongResponse(offerService.countAll()), HttpStatus.OK);
    }

    @Operation(summary = "Count offers are pending", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Count of offers pending", content = @Content(schema = @Schema(implementation = LongResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @GetMapping(value = "/companies/offer/pending", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LongResponse> getCountOffersPending() {
        return new ResponseEntity<>(new LongResponse(offerService.countOffersPending()), HttpStatus.OK);
    }
}
