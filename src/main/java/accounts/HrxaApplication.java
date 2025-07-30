package accounts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class})
public class HrxaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrxaApplication.class, args);
	}
	
	@Bean
	OpenAPI customOpenAPI(@Value("${app.description}") String appDescription, @Value("${app.version}") String appVersion) {
		return new OpenAPI().info(new Info().title("Account API").version(appVersion)
				.description(appDescription)
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0")
						.url("http://springdoc.org")));
	}

}
