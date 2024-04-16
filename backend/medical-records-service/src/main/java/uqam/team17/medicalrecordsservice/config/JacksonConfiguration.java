package uqam.team17.medicalrecordsservice.config;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        // Register the JavaTimeModule to handle Java 8 date/time types
        builder.modules(new JavaTimeModule())
                .dateFormat(new StdDateFormat().withColonInTimeZone(true));

        return builder;
    }
}


