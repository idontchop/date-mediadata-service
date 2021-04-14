package com.idontchop.datemediadataservice.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * NOTE: This class has been modified for this service, since it doesn't
 * use Spring Security
 * 
 * @author nathan
 *
 */
public class JwtTokenService {
	static final long EXPIRATIONTIME = 864000000;	// 1 day
	
	
	static String SIGNINGKEY = "SecretKey1";
	
	static final String PREFIX = "Bearer";
	
	static Logger logger = LoggerFactory.getLogger(JwtTokenService.class);
	
	// Get username from request
	static public String getAuthentication( HttpServletRequest req ) {
		
		String token = req.getHeader("Authorization");
		return (token == null) ? null : getAuthenticationFromString(token.replace(PREFIX, ""));

	}
		
	static public String getAuthenticationFromString (String token) {
		if ( token != null && StringUtils.hasText(token) ) {
			
			logger.info("Found Auth Token: " + token);
			String user = Jwts.parser()
					.setSigningKey(SIGNINGKEY)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			
			return user;
		}
		
		logger.info("Unauthenticated Request");
		return null;
	}
	
	/**
	 * Supplies a JWT Token for the given user.
	 * 
	 * Sets subject to username
	 * 
	 * Sets one claim: 
	 * 		1) Expiration time from properties
	 * 		
	 * 
	 * @param name
	 * @return the token string
	 */
	static public String buildToken ( String name ) {
		
		LocalDateTime ldt = LocalDateTime.now();
		ldt = ldt.plusHours( EXPIRATIONTIME );
		
		return Jwts.builder().setSubject(name)		
			.setExpiration( Date.from( ldt.atZone( ZoneId.systemDefault() ).toInstant()  ))
			.signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
			.compact();
	}

	public  String getSIGNINGKEY() { 
		return SIGNINGKEY;
	}

}
