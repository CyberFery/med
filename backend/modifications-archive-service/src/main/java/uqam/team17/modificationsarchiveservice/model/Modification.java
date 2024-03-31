package uqam.team17.modificationsarchiveservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Modification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modificationId;
    String content;

   // @OneToOne(cascade = CascadeType.ALL)
    //private Doctor creator;
    //@OneToOne(cascade = CascadeType.ALL)
    //private MedicalRecord medicalRecord;

    //private LocalDateTime timestamp;


    public Modification(){

    }

    public Modification( String context){
        //this.creator = doctor;
        //this.medicalRecord = record;
        //this.timestamp = timestamp;
        this.content = context;
    }

    public void setContent(String context){
        this.content = context;
    }

    public Long getModificationId() {
        return modificationId;
    }

    public void setModificationId(Long modificationId) {
        this.modificationId = modificationId;
    }

    @Override
    public String toString() {
        return "Modification{" +
                "modificationId=" + modificationId +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    //public void setCreator(Doctor doctor){
        //this.creator = doctor;
   // }

    //public void setMedicalRecord(MedicalRecord medicalRecord){
       // this.medicalRecord = medicalRecord;
   // }

    //public void setTimestamp(LocalDateTime time){
        //this.timestamp = time;
    //}



}
