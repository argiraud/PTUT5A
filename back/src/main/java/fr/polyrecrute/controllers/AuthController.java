package fr.polyrecrute.controllers;

import javax.validation.Valid;

import fr.polyrecrute.responceType.EntitySignin;
import fr.polyrecrute.responceType.EntitySignup;
import fr.polyrecrute.services.EntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "The authentication API")
public class AuthController {

	private final EntityService entityService;

	@Autowired
	public AuthController(EntityService entityService) {
		this.entityService = entityService;
	}


	@Operation(summary = "Sign in entity", description = "Login to an existing account",
			responses= {
				@ApiResponse(responseCode = "200", description = "User was register",
						content = @Content(schema = @Schema(implementation = EntitySignin.class))),
				@ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) })
	@GetMapping(value = "/signing", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntitySignin> signInEntity(@Parameter(description = "username") @Valid String username,
													 @Parameter(description = "password") @Valid String password) {
		EntitySignin entitySignin = entityService.loginEntity(username, password);
		return new ResponseEntity<>(entitySignin, HttpStatus.OK);
	}

	@Operation(summary = "Sign up entity", description = "Create a new entity",
			responses= {
					@ApiResponse(responseCode = "201", description = "User was registered", content = @Content),
					@ApiResponse(responseCode = "400", description = "Email or name is too long", content = @Content),
					@ApiResponse(responseCode = "409", description = "Email already exists", content = @Content) })
	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity signUpEntity(@Valid @RequestBody EntitySignup entitySignup) {
		entityService.registerEntity(entitySignup);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
