package uqam.team17.medicalrecordsservice.utility;

import uqam.team17.medicalrecordsservice.entity.MedicalHistory;
import uqam.team17.medicalrecordsservice.entity.MedicalVisit;

public class MedicalHistoryRequest {
    private String healthInsuranceNumber;
    private MedicalHistory medicalHistory;

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
}
