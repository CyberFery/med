package uqam.team17.modificationsarchiveservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ModificationsArchiveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModificationsArchiveServiceApplication.class, args);
    }

}
