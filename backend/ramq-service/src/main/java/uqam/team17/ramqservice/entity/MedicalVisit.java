package uqam.team17.ramqservice.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class MedicalVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalRecordCopy medicalRecordCopy;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "MedicalVisit{" +
                "id=" + id +
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
