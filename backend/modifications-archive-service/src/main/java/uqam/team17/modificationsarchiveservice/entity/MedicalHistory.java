package uqam.team17.modificationsarchiveservice.entity;

import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "medical_history")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modHistoryId;

    private Long medicalHistoryId;
    private String diagnosis;
    private String treatment;
    @ElementCollection
    private List<Illness> illnessList;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "mod_doc_id")
    private Doctor primaryCareDoctor;
    public MedicalHistory() {
        // default constructor
    }

    public MedicalHistory(Long modHistoryId, Long medicalHistoryId, String diagnosis, String treatment, List<Illness> illnessList,
                          Doctor primaryCareDoctor) {
        this.modHistoryId = modHistoryId;
        this.medicalHistoryId = medicalHistoryId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.illnessList = illnessList;
        this.primaryCareDoctor = primaryCareDoctor;
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


    public ModificationType getType() {
        return ModificationType.MEDICAL_HISTORY;
    }

    public Long getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(Long medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "medicalHistoryId=" + medicalHistoryId +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", illnessList=" + illnessList +
                ", primaryDoctor=" + primaryCareDoctor +
                '}';
    }

    public Long getModHistoryId() {
        return modHistoryId;
    }

    public void setModHistoryId(Long modHistoryId) {
        this.modHistoryId = modHistoryId;
    }

    public Doctor getPrimaryCareDoctor() {
        return primaryCareDoctor;
    }

    public void setPrimaryCareDoctor(Doctor primaryCareDoctor) {
        this.primaryCareDoctor = primaryCareDoctor;
    }

    @Embeddable
    public static class Illness {
        private String description;
        private LocalDate onsetOfIllnessDate;
        private LocalDate endOfIllnessDate;

        public Illness() {
        }


        public Illness(String description, LocalDate onsetOfIllnessDate, LocalDate endOfIllnessDate) {
            this.description = description;
            this.onsetOfIllnessDate = onsetOfIllnessDate;
            this.endOfIllnessDate = endOfIllnessDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public LocalDate getOnsetOfIllnessDate() {
            return onsetOfIllnessDate;
        }

        public void setOnsetOfIllnessDate(LocalDate onsetOfIllnessDate) {
            this.onsetOfIllnessDate = onsetOfIllnessDate;
        }

        public LocalDate getEndOfIllnessDate() {
            return endOfIllnessDate;
        }

        public void setEndOfIllnessDate(LocalDate endOfIllnessDate) {
            this.endOfIllnessDate = endOfIllnessDate;
        }

        @Override
        public String toString() {
            return "Illness{" +
                    "description='" + description + '\'' +
                    ", onsetOfIllnessDate=" + onsetOfIllnessDate +
                    ", endOfIllnessDate=" + endOfIllnessDate +
                    '}';
        }
    }
}
