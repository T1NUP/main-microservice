package com.cts.microservice.main.requestfilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cts.microservice.main.service.UserValidationService;
import com.cts.microservice.main.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	static Logger log = LogManager.getLogger(JwtRequestFilter.class);
	// Object of MyUserDetailsService class
	@Autowired
	private UserValidationService userValidationService;

	// Object of jwtUtil class
	@Autowired
	private JwtUtil jwtUtil;

	// invoked once per request within a single request thread.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.debug("doFilterInternal()");
		// get the valide authorization Header
		final String authorizationHeader = request.getHeader("Authorization");
		log.debug("doFilterInternal()--2"+ authorizationHeader);
		String username = null;
		String jwt = null;

		// IF condition to get or extract the jwt token and also extract the username
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt);
		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			log.debug("Json Web Token ---> " + jwt);
			log.debug("User Name  ---> " + username);
			// userDetails variable to store the username using loadUserByUsername mathod
			UserDetails userDetails = this.userValidationService.loadUserByUsername(username);

			// this is the process that spring security does by its own to enable security
			// here by passing the same userdetails we are just doing the same work that
			// spring does internally
			if (jwtUtil.validateToken(jwt, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
