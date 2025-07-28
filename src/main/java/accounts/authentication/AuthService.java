package accounts.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.servlet.http.HttpServletRequest;

public class AuthService {

	private static final String AUTH_TOKEN_HEADER = "X-API-KEY";
	private static final String AUTH_TOKEN = "Account_Auth";
	
	public static Authentication getAuthentication(HttpServletRequest request) {
		String apiKey = request.getHeader(AUTH_TOKEN_HEADER);
		if(apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
			throw new BadCredentialsException("Invalid API Key");
		}
		
		return new KeyAuthentication(apiKey,AuthorityUtils.NO_AUTHORITIES);
	}
}
