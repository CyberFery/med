package uqam.team17.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import uqam.team17.gateway.config.GatewayConfig;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GatewayConfigTest {

    @Test
    public void restTemplateBeanExists() {
        ApplicationContext context = new AnnotationConfigApplicationContext(GatewayConfig.class);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        assertNotNull(restTemplate, "RestTemplate bean should not be null");
    }
}

