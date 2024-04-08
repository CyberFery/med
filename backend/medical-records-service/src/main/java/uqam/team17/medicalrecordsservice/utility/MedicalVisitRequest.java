package uqam.team17.medicalrecordsservice.utility;

import uqam.team17.medicalrecordsservice.entity.MedicalVisit;


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
