package uqam.team17.modificationsarchiveservice.model;

public class MedicalVisitRequest implements ModificationRequest {
    private String healthInsuranceNumber;
    private MedicalVisit medicalVisit;

    public MedicalVisitRequest() {
        // default constructor
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

    @Override
    public Boolean isValid() {

        return healthInsuranceNumber != null &&
                medicalVisit != null &&
                medicalVisit.getVisitedEstablishment() != null &&
                medicalVisit.getVisitDate() != null &&
                medicalVisit.getDoctorSeen() != null &&
                medicalVisit.getSummaryOfTheVisitByDoctor() != null &&
                medicalVisit.getNotesForOtherDoctors() != null &&
                medicalVisit.getDiagnosisList() != null &&
                !medicalVisit.getDiagnosisList().isEmpty();
    }
}
