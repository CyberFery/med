package uqam.team17.medicalRecordsService.controller;

import jakarta.persistence.ElementCollection;
import uqam.team17.medicalRecordsService.entity.Patient;

import java.util.List;

public class ContactInformationRequest {
    private String healthInsuranceNumber;
    private List<Patient.ResidentialAddress> residentialAddressList;

    private List<Patient.PhoneNumber> phoneNumberList;

    private List<Patient.EmailAddress> emailAddressList;
    private String address;
    private Patient.ContactInformation contactInformation;

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }
    public Patient.ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(Patient.ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

}
