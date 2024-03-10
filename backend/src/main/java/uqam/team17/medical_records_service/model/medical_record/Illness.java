package uqam.team17.healthcare_professionals_medical_records_service.model.medical_record;

import java.util.Date;

class Illness {
    private String description;
    private Date onsetOfIllnessDate;
    private Date endOfIllnessDate;

    Illness(String description, Date onsetOfIllnessDate, Date endOfIllnessDate) {
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
}
