package uqam.team17.medicalrecordsservice.utility;

import uqam.team17.medicalrecordsservice.entity.Patient;

public class ContactInformationRequest {
    private String healthInsuranceNumber;
    private Patient.ContactInformation contactInformation;

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public Patient.ContactInformation getContactInformation() {
        return contactInformation;
    }
}
