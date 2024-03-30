package uqam.team17.medicalRecordsService.entity;

import jakarta.persistence.*;
import uqam.team17.medicalRecordsService.entity.MedicalRecord;
import uqam.team17.medicalRecordsService.entity.Doctor;

import java.util.Date;
import java.util.List;

@Entity
public class MedicalVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalVisitId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;
    private String visitedEstablishment;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Doctor doctorSeen;
    private Date visitDate;
    @ElementCollection
    private List<Diagnosis> diagnosisList;
    private String summaryOfTheVisitByDoctor;
    private String notesForOtherDoctors;

    public MedicalVisit() {
        // default constructor
    }

    public String getVisitedEstablishment() {
        return visitedEstablishment;
    }

    public void setVisitedEstablishment(String visitedEstablishment) {
        this.visitedEstablishment = visitedEstablishment;
    }

    public Doctor getDoctorSeen() {
        return doctorSeen;
    }

    public void setDoctorSeen(Doctor doctorSeen) {
        this.doctorSeen = doctorSeen;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public List<Diagnosis> getDiagnosisList() {
        return diagnosisList;
    }

    public void setDiagnosisList(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public String getSummaryOfTheVisitByDoctor() {
        return summaryOfTheVisitByDoctor;
    }

    public void setSummaryOfTheVisitByDoctor(String summaryOfTheVisitByDoctor) {
        this.summaryOfTheVisitByDoctor = summaryOfTheVisitByDoctor;
    }

    public String getNotesForOtherDoctors() {
        return notesForOtherDoctors;
    }

    public void setNotesForOtherDoctors(String notesForOtherDoctors) {
        this.notesForOtherDoctors = notesForOtherDoctors;
    }

    public Long getMedicalVisitId() {
        return medicalVisitId;
    }

    public void setMedicalVisitId(Long medicalVisitId) {
        this.medicalVisitId = medicalVisitId;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecordCopy(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @Override
    public String toString() {
        return "MedicalVisit{" +
                "medicalVisitId=" + medicalVisitId +
                ", medicalRecordCopy=" + medicalRecord +
                ", visitedEstablishment='" + visitedEstablishment + '\'' +
                ", doctorSeen=" + doctorSeen +
                ", visitDate=" + visitDate +
                ", diagnosisList=" + diagnosisList +
                ", summaryOfTheVisitByDoctor='" + summaryOfTheVisitByDoctor + '\'' +
                ", notesForOtherDoctors='" + notesForOtherDoctors + '\'' +
                '}';
    }

@Embeddable
public static class Diagnosis {
    private String description;
    private String treatment;

    public Diagnosis(String description, String treatment) {
        this.description = description;
        this.treatment = treatment;
    }

    public Diagnosis() {
        // default constructor
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "description='" + description + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }
}
}

