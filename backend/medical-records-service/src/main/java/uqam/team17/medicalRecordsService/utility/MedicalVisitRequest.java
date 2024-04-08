package uqam.team17.medicalRecordsService.utility;

import uqam.team17.medicalRecordsService.entity.MedicalVisit;


public class MedicalVisitRequest {
    private String healthInsuranceNumber;
    private MedicalVisit medicalVisit;

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public MedicalVisit getMedicalVisit() {
        return medicalVisit;
    }
}
