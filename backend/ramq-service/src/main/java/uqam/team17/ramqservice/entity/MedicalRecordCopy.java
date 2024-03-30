package uqam.team17.ramqservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class MedicalRecordCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalRecordCopyId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicalVisit> medicalVisitList;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MedicalHistory> medicalHistoryList;

    public MedicalRecordCopy() {
        // default constructor
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

    public Long getMedicalRecordCopyId() {
        return medicalRecordCopyId;
    }

    public void setMedicalRecordCopyId(Long medicalRecordCopyId) {
        this.medicalRecordCopyId = medicalRecordCopyId;
    }

    @Override
    public String toString() {
        return "MedicalRecordCopy{" +
                "medicalRecordCopyId=" + medicalRecordCopyId +
                ", patient=" + patient +
                ", medicalVisitList=" + medicalVisitList +
                ", medicalHistoryList=" + medicalHistoryList +
                '}';
    }
}


