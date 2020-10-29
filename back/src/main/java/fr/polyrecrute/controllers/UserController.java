package fr.polyrecrute.controllers;

import javax.validation.Valid;

import fr.polyrecrute.responceType.UserSignin;
import fr.polyrecrute.responceType.UserSignup;
import fr.polyrecrute.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import fr.polyrecrute.models.ERole;
import fr.polyrecrute.models.Role;
import fr.polyrecrute.models.User;
import fr.polyrecrute.repository.RoleRepository;
import fr.polyrecrute.repository.UserRepository;
import fr.polyrecrute.security.JwtUtils;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@Tag(name = "User", description = "the Contact API")
public class UserController {

	@Autowired
	UserService userService;


	@Operation(summary = "Sign in user", description = "conexion")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User was register",
					content = @Content(schema = @Schema(implementation = UserSignin.class))),
			@ApiResponse(responseCode = "400", description = "sdcsdcsscd id supplied"),
			@ApiResponse(responseCode = "404", description = "Book notsdc found") })
	@GetMapping(value = "/signin", produces = { "application/json"})
	public ResponseEntity<UserSignin> signInUser(@Parameter(description = "username") @Valid String username,
												 @Parameter(description = "password") @Valid String password) {

		UserSignin userSignin = userService.loginUser(username, password);
		return new ResponseEntity<>(userSignin, HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity signUpUser(@Valid @RequestBody UserSignup userSignup) {
		userService.registerUser(userSignup);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
