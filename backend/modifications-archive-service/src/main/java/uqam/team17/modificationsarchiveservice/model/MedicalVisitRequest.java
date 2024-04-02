package uqam.team17.modificationsarchiveservice.model;

public class MedicalVisitRequest {
    private String healthInsuranceNumber;

    private MedicalVisit medicalVisit;

    public MedicalVisitRequest() {
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public MedicalVisit getMedicalVisit() {
        return medicalVisit;
    }

    public void setMedicalVisit(MedicalVisit medicalVisit) {
        this.medicalVisit = medicalVisit;
    }

    @Override
    public String toString() {
        return "MedicalVisitRequest{" +
                "healthInsuranceNumber='" + healthInsuranceNumber + '\'' +
                ", medicalVisit=" + medicalVisit +
                '}';
    }
}
