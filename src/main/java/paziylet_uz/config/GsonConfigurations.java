package paziylet_uz.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfigurations {
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
