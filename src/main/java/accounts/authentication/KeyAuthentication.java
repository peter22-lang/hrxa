package accounts.authentication;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class KeyAuthentication extends AbstractAuthenticationToken{
	
	
	private static final long serialVersionUID = UUID.randomUUID().timestamp();
	private final String apiKey;	
	
	public KeyAuthentication(String apiKey, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.apiKey = apiKey;
		setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}
	
	@Override
	public Object getPrincipal() {
		return apiKey;
	}

}
