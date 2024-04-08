package uqam.team17.medicalrecordsservice.utility;

import uqam.team17.medicalrecordsservice.entity.MedicalHistory;

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
