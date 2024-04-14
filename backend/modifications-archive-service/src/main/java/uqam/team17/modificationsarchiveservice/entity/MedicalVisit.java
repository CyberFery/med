package uqam.team17.modificationsarchiveservice.entity;

import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "medical_visit")
public class MedicalVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalVisitId;
    private String visitedEstablishment;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Doctor doctorSeen;
    private LocalDate visitDate;
    @ElementCollection
    private List<Diagnosis> diagnosisList;
    private String summaryOfTheVisitByDoctor;
    private String notesForOtherDoctors;

    public MedicalVisit() {
        // default constructor
    }

    public MedicalVisit(Long medicalVisitId, String visitedEstablishment, Doctor doctorSeen, LocalDate visitDate,
                        List<Diagnosis> diagnosisList, String summaryOfTheVisitByDoctor,
                        String notesForOtherDoctors) {
        this.medicalVisitId = medicalVisitId;
        this.visitedEstablishment = visitedEstablishment;
        this.doctorSeen = doctorSeen;
        this.visitDate = visitDate;
        this.diagnosisList = diagnosisList;
        this.summaryOfTheVisitByDoctor = summaryOfTheVisitByDoctor;
        this.notesForOtherDoctors = notesForOtherDoctors;
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

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
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


    public ModificationType getType() {
        return ModificationType.MEDICAL_VISIT;
    }

    @Override
    public String toString() {
        return "MedicalVisit{" +
                "medicalVisitId=" + medicalVisitId +
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

        public Diagnosis() {
            // default constructor
        }

        public Diagnosis(String description, String treatment) {
            this.description = description;
            this.treatment = treatment;
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
