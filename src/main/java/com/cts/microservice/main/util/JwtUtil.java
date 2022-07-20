package com.cts.microservice.main.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

	static Logger log = LogManager.getLogger(JwtUtil.class);
	private String SECRET_KEY = "punitkm";

	public String generateToken(UserDetails userDetails) {
		log.debug("generateToken() --> UserDetails " + userDetails);
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		log.debug("createToken() --> Subject " + subject);
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	// The extractUsername method is used to know the User for whom the
	// authentication is done
	public String extractUsername(String token) {
		log.debug("extractUsername() --> Token " + token);
		return extractClaim(token, Claims::getSubject);
	}

	// The extractExpiration method is used to know the expire date of the JWT
	public Date extractExpiration(String token) {
		log.debug("extractExpiration() --> Token " + token);
		return extractClaim(token, Claims::getExpiration);
	}

	// The method extractClaim is used to extract the information according to the
	// claimsResolver passed
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		log.debug("extractClaims() --> Token " + token + "and ClaimsResolver " + claimsResolver);
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// The extractAllClaims method extract all claims from JWt
	private Claims extractAllClaims(String token) {
		log.debug("extractAllClaims() --> Token " + token);

		Claims c = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		log.debug("extractAllClaims() -> subject::" + c.getSubject());

		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	// The isTokenExpired method returns if the token is expired or not
	private Boolean isTokenExpired(String token) {
		log.debug("isTokenExpired() --> Token " + token);
		return extractExpiration(token).before(new Date());
	}

	// the validateToken method validates the token using the isTokenExpired method
	// and the username
	public Boolean validateToken(String token, UserDetails userDetails) {
		log.debug("validateToken() --> Token " + token + "and UserDetails" + userDetails);
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
