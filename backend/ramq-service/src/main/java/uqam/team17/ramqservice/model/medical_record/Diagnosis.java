package uqam.team17.ramqservice.model.medical_record;

class Diagnosis {
    private String description;
    private String treatment;

    Diagnosis(String description, String treatment) {
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
}