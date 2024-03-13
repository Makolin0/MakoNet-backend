package MakoNetbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
		title = "Back-end API for MakoNet",
		description =
				"Hej Adam jesteś bardzo przystojny UwU",
		version = "v0.1"))
public class MakoNetBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakoNetBackendApplication.class, args);
	}

}
