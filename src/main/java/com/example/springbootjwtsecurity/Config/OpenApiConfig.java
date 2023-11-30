package com.example.springbootjwtsecurity.Config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name= "wajdi" ,
                        email = "wajdi.barhoumi26@gmail.com"
                ),
                description = "Spring boot new configuration ",
                title =" open api config ",
                version = "1.0.1",
                license = @License(
                        name=""
                ),
                termsOfService = "terms Services "

        ),
        servers = {
                @Server (
                        description = "local" ,
                        url = "http://127.0.0.1:8282"
                )
        } ,
        security = {
        @SecurityRequirement(
                name = "bearerAuth"
        )
}


)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
