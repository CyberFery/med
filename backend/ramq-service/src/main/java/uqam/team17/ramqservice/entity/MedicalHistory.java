package uqam.team17.ramqservice.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalRecordCopy medicalRecordCopy;
    private String diagnosis;
    private String treatment;
    @ElementCollection
    private List<Illness> illnessList;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Doctor primaryCareDoctor;

    public MedicalHistory() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Doctor getPrimaryCareDoctor() {
        return primaryCareDoctor;
    }

    public void setPrimaryCareDoctor(Doctor primaryCareDoctor) {
        this.primaryCareDoctor = primaryCareDoctor;
    }

    @Override
    public String toString() {
        return "MedicalHistory{" +
                "id=" + id +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", illnessList=" + illnessList +
                ", primaryCareDoctor=" + primaryCareDoctor +
                '}';
    }

    @Embeddable
    public static class Illness {
        private String description;
        private Date onsetOfIllnessDate;
        private Date endOfIllnessDate;

        public Illness(String description, Date onsetOfIllnessDate, Date endOfIllnessDate) {
            this.description = description;
            this.onsetOfIllnessDate = onsetOfIllnessDate;
            this.endOfIllnessDate = endOfIllnessDate;
        }

        public Illness() {

        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getOnsetOfIllnessDate() {
            return onsetOfIllnessDate;
        }

        public void setOnsetOfIllnessDate(Date onsetOfIllnessDate) {
            this.onsetOfIllnessDate = onsetOfIllnessDate;
        }

        public Date getEndOfIllnessDate() {
            return endOfIllnessDate;
        }

        public void setEndOfIllnessDate(Date endOfIllnessDate) {
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
