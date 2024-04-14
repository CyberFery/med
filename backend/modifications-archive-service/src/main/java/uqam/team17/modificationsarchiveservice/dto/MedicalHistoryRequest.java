package uqam.team17.modificationsarchiveservice.dto;

import uqam.team17.modificationsarchiveservice.entity.MedicalHistory;

public class MedicalHistoryRequest implements ModificationRequest {
    private String healthInsuranceNumber;

    private MedicalHistory medicalHistory;

    public MedicalHistoryRequest() {
        // default constructor
    }

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

    @Override
    public String toString() {
        return "MedicalHistoryRequest{" +
                "healthInsuranceNumber='" + healthInsuranceNumber + '\'' +
                ", medicalHistory=" + medicalHistory +
                '}';
    }

    @Override
    public Boolean isValid() {
        return healthInsuranceNumber != null &&
                medicalHistory != null &&
                medicalHistory.getDiagnosis() != null &&
                medicalHistory.getTreatment() != null &&
                medicalHistory.getIllnessList() != null &&
                medicalHistory.getPrimaryDoctor() != null &&
                medicalHistory.getPrimaryDoctor().getFirstName() != null &&
                medicalHistory.getPrimaryDoctor().getLastName() != null &&
                medicalHistory.getPrimaryDoctor().getSpecialization() != null &&
                !medicalHistory.getIllnessList().isEmpty();
    }
}
