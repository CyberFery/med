package uqam.team17.modificationsarchiveservice.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDateTime;

@Entity
@Table(name = "modification")
public class Modification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modificationId;

    private String healthInsuranceNumber;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "modification_type")
    private ModificationType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_history_id", unique = true)
    private MedicalHistory medicalHistory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_visit_id", unique = true)
    private MedicalVisit medicalVisit;
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_id", unique = true)
    private ContactInformation contactInformation;
    private Status status;

    //private Long sourceDatabaseId;


    public Modification() {
        //default constructor
    }

    public Long getModificationId() {
        return modificationId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ModificationType getType() {
        return type;
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setModificationId(Long modificationId) {
        this.modificationId = modificationId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(ModificationType type) {
        this.type = type;
    }


    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public MedicalVisit getMedicalVisit() {
        return medicalVisit;
    }

    public void setMedicalVisit(MedicalVisit medicalVisit) {
        this.medicalVisit = medicalVisit;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "Modification{" +
                "modificationId=" + modificationId +
                ", healthInsuranceNumber='" + healthInsuranceNumber + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                ", medicalHistory=" + medicalHistory +
                ", medicalVisit=" + medicalVisit +
                ", contactInformation=" + contactInformation +
                ", status=" + status +
                '}';
    }


    public enum Status{
        UPDATE,
        CANCEL
    }



}
