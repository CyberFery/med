package uqam.team17.medicalRecordsService.utility;

import uqam.team17.medicalRecordsService.entity.MedicalHistory;

public class MedicalHistoryRequest {
    private String healthInsuranceNumber;
    private MedicalHistory medicalHistory;
    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }
    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }
}
