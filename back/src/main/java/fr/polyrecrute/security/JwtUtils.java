package fr.polyrecrute.security;

import java.util.Date;

import fr.polyrecrute.models.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import org.springframework.web.server.ResponseStatusException;

@Component
public class JwtUtils {

	private final String jwtSecret = "PolyrecruteSecretKey";

	public String generateJwtToken(Authentication authentication) {

		Entity entityPrincipal = (Entity) authentication.getPrincipal();

		int jwtExpirationMs = 86400000;
		return Jwts.builder()
				.setSubject((entityPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getEmailFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
		} catch (MalformedJwtException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		} catch (ExpiredJwtException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JWT token is expired");
		} catch (UnsupportedJwtException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JWT token is unsupported");
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
		}
	}
}
