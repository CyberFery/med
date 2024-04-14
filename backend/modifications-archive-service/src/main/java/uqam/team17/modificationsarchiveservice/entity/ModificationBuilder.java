package uqam.team17.modificationsarchiveservice.entity;


import java.time.LocalDateTime;

public class ModificationBuilder {
    private Modification modification;

    public ModificationBuilder() {
        this.modification = new Modification();
    }


    public ModificationBuilder addModificationId(Long id){
        this.modification.setModificationId(id);
        return this;
    }

    public ModificationBuilder addHealthInsuranceNumber(String insuranceNumber){
        this.modification.setHealthInsuranceNumber(insuranceNumber);
        return this;
    }

    public ModificationBuilder addTimestamp(LocalDateTime time){
        this.modification.setTimestamp(time);
        return this;
    }

    public ModificationBuilder addModificationType(ModificationType type){
        this.modification.setType(type);
        return this;
    }

    public ModificationBuilder addStatus(Modification.Status status){
        this.modification.setStatus(status);
        return this;
    }

    public ModificationBuilder addHistory(MedicalHistory history){
        this.modification.setMedicalHistory(history);
        return this;
    }

    public ModificationBuilder addVisit(MedicalVisit visit){
        this.modification.setMedicalVisit(visit);
        return this;
    }

    public ModificationBuilder addContact(ContactInformation contact){
        this.modification.setContactInformation(contact);
        return this;
    }

    public Modification build(){
        return this.modification;
    }


}
