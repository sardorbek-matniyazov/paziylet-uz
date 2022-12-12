package paziylet_uz.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        Info info = new Info();
        String appName = "Khantore-crm";
        info.title(appName);
        String version = "0.1";
        info.version(version);
        info.description("This is Api documentation for developer or any person who interested my api.");
        final Contact contact = new Contact();
        contact.name("Sardorbek Matniyazov");
        contact.email("sardorbekmatniyazov03@gmail.com");
        info.contact(contact);
        return info;
    }
}
