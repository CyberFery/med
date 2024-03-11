package uqam.team17.medical_records_service.medical_record;

import uqam.team17.medical_records_service.model.user.Physician;

import java.util.List;

class MedicalHistory {
    private String diagnosis;
    private String treatment;
    private List<Illness> illnessList;
    private Physician primaryCarePhysician;

    MedicalHistory(String diagnosis, String treatment, List<Illness> illnessList, Physician primaryCarePhysician) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.illnessList = illnessList;
        this.primaryCarePhysician = primaryCarePhysician;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public List<Illness> getIllnessList() {
        return illnessList;
    }

    public void setIllnessList(List<Illness> illnessList) {
        this.illnessList = illnessList;
    }

    public Physician getPrimaryCarePhysician() {
        return primaryCarePhysician;
    }

    public void setPrimaryCarePhysician(Physician primaryCarePhysician) {
        this.primaryCarePhysician = primaryCarePhysician;
    }
}

