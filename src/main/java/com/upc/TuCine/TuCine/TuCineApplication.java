package com.upc.TuCine.TuCine;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API TuCine",
				version = "1.0",
				description = "API para gestionar la aplicación web TuCine",
				contact = @Contact(
						name = "TuCine",
						email = "tuCine@gmail.com"),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0.html"
				)
		),
		servers = {
				@Server(url = "https://backend-tucine-production.up.railway.app")
		}
)

public class TuCineApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		// Cargar variables de entorno desde el archivo .env
        Dotenv dotenv = Dotenv.load();

        // Obtener el valor de la variable de entorno DATASOURCE_PASSWORD
		String dataSourceUser = dotenv.get("DATASOURCE_USER");
        String dataSourcePassword = dotenv.get("DATASOURCE_PASSWORD");
		
        // Crear propiedades personalizadas para tu aplicación
        System.setProperty("custom.datasource.user", dataSourceUser);
		System.setProperty("custom.datasource.password", dataSourcePassword);

		SpringApplication.run(TuCineApplication.class, args);
	}

}
