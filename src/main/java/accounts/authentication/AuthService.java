package accounts.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthService {
	
	
	private static String AUTH_TOKEN_HEADER ;

	private static String AUTH_TOKEN;
	
	@Value("${authentication.token.header}")
	public void setTokenHeader(String tokenHeader) {
		AUTH_TOKEN_HEADER = tokenHeader;
	}
	
	@Value("${authentication.token}")
	public void setToken(String token) {
		AUTH_TOKEN = token;
	}
	
	public static Authentication getAuthentication(HttpServletRequest request) {
		String apiKey = request.getHeader(AUTH_TOKEN_HEADER);
		if(apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
			throw new BadCredentialsException("Invalid API Key");
		}
		
		return new KeyAuthentication(apiKey,AuthorityUtils.NO_AUTHORITIES);
	}
	

}
