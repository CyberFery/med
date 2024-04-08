package uqam.team17.medicalRecordsService.utility;

import uqam.team17.medicalRecordsService.entity.Patient;

import java.util.List;

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
