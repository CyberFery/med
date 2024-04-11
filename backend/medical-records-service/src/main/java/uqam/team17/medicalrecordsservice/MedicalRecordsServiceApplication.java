package uqam.team17.medicalrecordsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MedicalRecordsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalRecordsServiceApplication.class, args);
    }

}
