package com.upc.TuCine;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.info.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API TuCine",
				version = "1.0",
				description = "API para gestionar la aplicación web TuCine",
				contact = @Contact(
						name = "TuCine",
						email = "tucine@gmail.com"),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0.html"
				)
		),
		servers = {
				@Server(url = "http://localhost:8080")
		}
)
@EnableJpaAuditing
public class TuCineApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		// Load environment variables from .env file
        Dotenv dotenv = Dotenv.load();

        // Get value of variables
		String dataSourceUser = dotenv.get("DATASOURCE_USER");
        String dataSourcePassword = dotenv.get("DATASOURCE_PASSWORD");
		
        // Create custom properties for app
        System.setProperty("custom.datasource.user", dataSourceUser);
		System.setProperty("custom.datasource.password", dataSourcePassword);

		SpringApplication.run(TuCineApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
            }
        };
    }
}
