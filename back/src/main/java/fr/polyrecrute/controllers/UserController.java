package fr.polyrecrute.controllers;

import fr.polyrecrute.models.*;
import fr.polyrecrute.responceType.Company;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.responceType.Offer;
import fr.polyrecrute.responceType.Question;
import fr.polyrecrute.services.*;
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
@Tag(name = "User", description = "The user API")
public class UserController {

    private final EntityService entityService;
    private final CompanyService companyService;
    private final OfferService offerService;
    private final UserService userService;
    private final RoleService roleService;
    private final QuestionService questionService;

    @Autowired
    public UserController(EntityService entityService, CompanyService companyService, OfferService offerService, UserService userService, RoleService roleService, QuestionService questionService) {
        this.entityService = entityService;
        this.companyService = companyService;
        this.offerService = offerService;
        this.userService = userService;
        this.roleService = roleService;
        this.questionService = questionService;
    }

    @Operation(summary = "Get entity details by id", description = "Get more details like first name, birth date, etc",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Entity details",
                            content = @Content(schema = @Schema(implementation = EntityDetails.class))),
                    @ApiResponse(responseCode = "400", description = "Error token", content = @Content) })
    @GetMapping(value = "/user/details/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityDetails> userDetailsId(@Parameter(hidden=true) @RequestHeader(name="Authorization") String token,
                                                     @Parameter(description = "idUser") @PathVariable long idUser) {
        return new ResponseEntity(entityService.getDetails(entityService.findByUserId(idUser)) , HttpStatus.OK);
    }

    @Operation(summary = "Get entity details", description = "Get more details like first name, birth date, etc",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Entity details",
                            content = @Content(schema = @Schema(implementation = EntityDetails.class))),
                    @ApiResponse(responseCode = "400", description = "Error token", content = @Content) })
    @GetMapping(value = "/user/details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityDetails> userDetails(@Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        return new ResponseEntity(entityService.getDetails(entityService.getEntityFromToken(token)) , HttpStatus.OK);
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
    @PostMapping(value = "/user/uploadMultipleFiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResponseEntity> uploadMultipleFilesOffer(@RequestParam("files") MultipartFile[] files,
                                                         @Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        return Arrays.stream(files)
                .map(file -> uploadFile(file, token))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Update entity details", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Entity update details",
                            content = @Content(schema = @Schema(implementation = EntityDetails.class))),
                    @ApiResponse(responseCode = "400", description = "Error token", content = @Content) })
    @PatchMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityDetails> updateDetails(@RequestBody EntityDetails entityUpdate,
                                                        @Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        Entity__ entity = entityService.getEntityFromToken(token);
        if(!entityUpdate.getId().equals(entity.getIdEntity()) && !entity.getRoles().contains(roleService.findByName(ERole.ADMIN)))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");

        entity = entityService.findByUserId(entityUpdate.getId());
        entity = entityService.updateEntity(entity, entityUpdate);
        return new ResponseEntity(entityService.getDetails(entity) , HttpStatus.OK);
    }

    @Operation(summary = "Update entity password", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Entity password was update",
                            content = @Content(schema = @Schema(implementation = EntityDetails.class))),
                    @ApiResponse(responseCode = "400", description = "Error token", content = @Content) })
    @PatchMapping(value = "/user/password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePassword(@Parameter(description = "New password") @RequestParam String password,
                                                       @Parameter(hidden=true) @RequestHeader(name="Authorization") String token) {
        Entity__ entity = entityService.getEntityFromToken(token);
        entityService.updatePassword(entity, password);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "User want offer", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Offer was added in list", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Offer not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @PostMapping(value = "/user/wantedOffer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addWantedOffer(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                        @Parameter(description = "idOffer") @RequestParam String idOffer) {
        Entity__ entity = entityService.getEntityFromToken(token);
        if (entity.getUser() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not a user");
        User__ user = entity.getUser();
        Offer__ offer = offerService.findById(idOffer);
        userService.addWantedOffer(user, offer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Add answer", description = "",
            responses= {
                    @ApiResponse(responseCode = "201", description = "answer was created", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @PostMapping(value = "/user/question", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addQuestion(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                      @RequestBody Question question) {
        Entity__ entity = entityService.getEntityFromToken(token);
        entityService.addQuestion(entity, question);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get answer", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "answer", content = @Content(schema = @Schema(implementation = Question.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @PostMapping(value = "/user/question/{questionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getQuestion(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                      @Parameter(description = "questionId") @PathVariable long questionId) {
        Entity__ entity = entityService.getEntityFromToken(token);
        List<Question__> questions = questionService.findQuestionByNoQuestion(questionId);
        return new ResponseEntity<>(questionService.getTransactionalObjectList(questions), HttpStatus.OK);
    }


    @Operation(summary = "Company want one user", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "User was delete in list", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @DeleteMapping(value = "/user/wantedOffer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteWantedUser(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                           @Parameter(description = "idOffer") @RequestParam String idOffer) {
        Entity__ entity = entityService.getEntityFromToken(token);
        if (entity.getUser() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are not a user");
        User__ user = entity.getUser();
        Offer__ offer = offerService.findById(idOffer);
        userService.deleteWantedOffer(user, offer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "User wanted list", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Get list of wanted Offers", content = @Content(schema = @Schema(implementation = Offer.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @GetMapping(value = "/user/{idUser}/wantedOffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> getWantedUsers(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                                      @Parameter(description = "idUser") @PathVariable long idUser) {
        Entity__ entity = entityService.findByUserId(idUser);
        if (entity.getUser() == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return new ResponseEntity<>(offerService.getTransactionalObjectList(entity.getUser().getWantedOffer()), HttpStatus.OK);
    }

    @Operation(summary = "Companies who wanted user", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Get list of companies who wanted user", content = @Content(schema = @Schema(implementation = Company.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content),
                    @ApiResponse(responseCode = "403", description = "No sufficient right", content = @Content)})
    @GetMapping(value = "/user/{idUser}/cpompanyWhowanted", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Company>> getCompaniesWhoWantedUser(@Parameter(hidden = true) @RequestHeader(name = "Authorization") String token,
                                                                  @Parameter(description = "idUser") @PathVariable long idUser) {
        Entity__ entity = entityService.findByUserId(idUser);
        if(entity.getUser() == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        List<Company__> companies = companyService.FindCompanyWhoWantedUser(entity.getUser());

        return new ResponseEntity<>(companyService.getTransactionalObjectList(companies), HttpStatus.OK);
    }
}
