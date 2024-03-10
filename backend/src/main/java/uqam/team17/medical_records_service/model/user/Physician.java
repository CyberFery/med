package uqam.team17.healthcare_professionals_medical_records_service.model.user;

import uqam.team17.healthcare_professionals_medical_records_service.service.MedicalRecordModifier;

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
