package fr.polyrecrute.controllers;

import fr.polyrecrute.models.User__;
import fr.polyrecrute.responceType.EntityDetails;
import fr.polyrecrute.responceType.User;
import fr.polyrecrute.services.EntityService;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Student", description = "The student API")
public class StudentController {

    private final EntityService entityService;
    private final UserService userService;

    @Autowired
    public StudentController(EntityService entityService, UserService userService) {
        this.entityService = entityService;
        this.userService = userService;
    }

    @Operation(summary = "Get all students", description = "",
            responses= {
                    @ApiResponse(responseCode = "200", description = "Get all students", content = @Content(schema = @Schema(implementation = EntityDetails.class))),
                    @ApiResponse(responseCode = "401", description = "Authentication error", content = @Content) })
    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EntityDetails>> getStudents() {
        List<User__> users = entityService.getAllStudents();
        List<EntityDetails> usersDetails = new ArrayList<EntityDetails>();
        for (User__ user : users){
            usersDetails.add(entityService.getDetails(user.getEntity()));
        }
        return new ResponseEntity<>(usersDetails, HttpStatus.OK);
    }
}
