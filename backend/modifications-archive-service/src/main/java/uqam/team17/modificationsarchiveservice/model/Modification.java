package uqam.team17.modificationsarchiveservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Modification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modificationId;

    private String healthInsuranceNumber;

    private LocalDateTime timestamp;

    private ModificationType type;

    @Transient
    private ModificationIdRequest.Modifiable modifiable;

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

    //public String getModificationData() {
      //  return modificationData;
   // }

   // public Long getSourceDatabaseId() {
        //return sourceDatabaseId;
    //}

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

    //public void setModificationData(String modificationData) {
      //  this.modificationData = modificationData;
   // }

    ///public void setSourceDatabaseId(Long sourceDatabaseId) {
   //     this.sourceDatabaseId = sourceDatabaseId;
    //}



    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    @Override
    public String toString() {
        return "Modification{" +
                "modificationId=" + modificationId +
                ", timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }

    public ModificationIdRequest.Modifiable getModifiable() {
        return modifiable;
    }

    public void setModifiable(ModificationIdRequest.Modifiable modifiable) {
        this.modifiable = modifiable;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public enum Status{
        UPDATE,
        CANCEL
    }



}
