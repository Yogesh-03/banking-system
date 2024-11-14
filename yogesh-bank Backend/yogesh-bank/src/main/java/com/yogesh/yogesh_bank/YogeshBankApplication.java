package com.yogesh.yogesh_bank;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Yadav Banking Services",
				description = "Backend Rest APIs for YBS",
				version = "v1.0",
				contact = @Contact(
						name = "Yogesh Yadav",
						email = "yadav.56@iitj.ac.in",
						url = ""
				),
				license = @License(
						name = "Yogesh Yadav",
						url = " "
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Yadav Banking Services Documentation",
				url = " "
		)
)
public class YogeshBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(YogeshBankApplication.class, args);
	}

}
