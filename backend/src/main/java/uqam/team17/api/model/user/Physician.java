package uqam.team17.api.model.user;

import uqam.team17.api.service.MedicalRecordModifier;

public class Physician extends User implements MedicalRecordModifier {
    private String specialization;

    void cancelModificationToMedicalRecord() {
    }

    @Override
    protected void consultMedicalRecord() {

    }

    @Override
    protected void authentification() {

    }

    @Override
    public void modifyMedicalRecord() {

    }
}
