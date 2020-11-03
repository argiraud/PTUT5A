package fr.polyrecrute.controllers;

import javax.validation.Valid;

import fr.polyrecrute.responceType.CompanySignup;
import fr.polyrecrute.responceType.EntitySignin;
import fr.polyrecrute.responceType.UserSignup;
import fr.polyrecrute.services.CompanyService;
import fr.polyrecrute.services.EntityService;
import fr.polyrecrute.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Authentication", description = "The authentication API")
public class AuthController {

	private final EntityService entityService;

	private final CompanyService companyService;

	private final UserService userService;

	@Autowired
	public AuthController(EntityService entityService, CompanyService companyService, UserService userService) {
		this.companyService = companyService;
		this.userService = userService;
		this.entityService = entityService;
	}


	@Operation(summary = "Sign in entity", description = "Login to an existing account",
			responses= {
				@ApiResponse(responseCode = "200", description = "User was register",
						content = @Content(schema = @Schema(implementation = EntitySignin.class))),
				@ApiResponse(responseCode = "404", description = "Email or password incorrect", content = @Content) })
	@GetMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntitySignin> signInEntity(@Parameter(description = "mail") @Valid String mail,
													 @Parameter(description = "password") @Valid String password) {
		EntitySignin entitySignin = entityService.loginEntity(mail, password);
		return new ResponseEntity<>(entitySignin, HttpStatus.OK);
	}

	@Operation(summary = "Sign up company", description = "Create a new company",
			responses= {
					@ApiResponse(responseCode = "201", description = "User was registered", content = @Content),
					@ApiResponse(responseCode = "400", description = "Email or name is too long", content = @Content),
					@ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) })
	@PostMapping(value = "/company/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity companySignCompany(@Valid @RequestBody CompanySignup companySignup) {
		companyService.registerEntity(companySignup);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@Operation(summary = "Sign up user", description = "Create a new user",
			responses= {
					@ApiResponse(responseCode = "201", description = "User was registered", content = @Content),
					@ApiResponse(responseCode = "400", description = "Email, name, first name or etudiant number is too long / Birth date is wrong", content = @Content),
					@ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) })
	@PostMapping(value = "/user/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity SignUpUser(@Valid @RequestBody UserSignup userSignup) {
		userService.registerEntity(userSignup);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
