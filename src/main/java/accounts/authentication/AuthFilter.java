package accounts.authentication;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends GenericFilterBean{
	private  String auth_token_header = "X-API-KEY";
	private  String auth_token = "Account_Auth";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)throws IOException,ServletException{
		try {
				HttpServletRequest httpReq  = (HttpServletRequest)request;
				httpReq.setAttribute(auth_token_header, auth_token);
				Authentication authentication = AuthService.getAuthentication((HttpServletRequest)request);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				filterChain.doFilter(request, response);
		}catch(Exception exp) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
			PrintWriter writer = httpResponse.getWriter();
			writer.print(exp.getMessage());
			writer.flush();
			writer.close();
		}
	}
}
