package uqam.team17.medicalRecordsService.entity;

import jakarta.persistence.*;
import uqam.team17.medicalRecordsService.entity.Patient;

import java.util.List;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalRecordId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicalVisit> medicalVisitList;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicalHistory> medicalHistoryList;

    public MedicalRecord(Patient patient) {
        this.patient = patient;
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

    public Long getMedicalRecordId() {
        return medicalRecordId;
    }

    public void setMedicalRecordId(Long medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "medicalRecordId=" + medicalRecordId +
                ", patient=" + patient +
                ", medicalVisitList=" + medicalVisitList +
                ", medicalHistoryList=" + medicalHistoryList +
                '}';
    }
}

