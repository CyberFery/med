package uqam.team17.modificationsarchiveservice.model;

import java.time.LocalDateTime;

public class ModificationRequest {
    //Doctor creator;
    //MedicalRecord record;
    //LocalDateTime timestamp;
    String content;

        //Doctor creator, MedicalRecord record, LocalDateTime timestamp
    public ModificationRequest (String context){
        //this.creator = creator;
        //this.record = record;
        //this.timestamp = timestamp;
        this.content = context;
    }

    /**
     *  public Doctor getCreator(){
     *         return creator;
     *     }
     *
     *     public MedicalRecord getRecord(){
     *         return record;
     *     }
     *
     *     public LocalDateTime getTimestamp(){
     *         return timestamp;
     *     }
     *
     */

    public String getContent(){
        return content;
    }

    public void setContent(String context){
        this.content = context;
    }

    @Override
    public String toString() {
        return "ModificationRequest{" +
                "content='" + content + '\'' +
                '}';
    }
}
