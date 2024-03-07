package uqam.team17.api.model.medical_record;

import uqam.team17.api.model.user.patient.Patient;

import java.util.List;

public class MedicalRecord {
    private Patient patient;
    private List<MedicalVisit> medicalVisitList;
    private List<MedicalHistory> medicalHistoryList;

    public MedicalRecord(Patient patient) {
        this.patient = patient;
    }

    public MedicalRecord() {

    }

    void save() {
    }

    void archiveModification() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<MedicalVisit> getMedicalVisitList() {
        return medicalVisitList;
    }

    public void setMedicalVisitList(List<MedicalVisit> medicalVisitList) {
        this.medicalVisitList = medicalVisitList;
    }

    public List<MedicalHistory> getMedicalHistoryList() {
        return medicalHistoryList;
    }

    public void setMedicalHistoryList(List<MedicalHistory> medicalHistoryList) {
        this.medicalHistoryList = medicalHistoryList;
    }
}
