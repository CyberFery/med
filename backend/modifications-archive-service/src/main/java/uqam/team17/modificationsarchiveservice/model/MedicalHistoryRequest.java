package uqam.team17.modificationsarchiveservice.model;

public class MedicalHistoryRequest {
    private String healthInsuranceNumber;

    private MedicalHistory medicalHistory;

    public MedicalHistoryRequest() {
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
}
