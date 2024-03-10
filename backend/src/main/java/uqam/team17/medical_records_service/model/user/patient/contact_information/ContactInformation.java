package uqam.team17.healthcare_professionals_medical_records_service.model.user.patient.contact_information;

public class ContactInformation {
    private ResidentialAddress residentialAddress;
    private PhoneNumber phoneNumber;
    private EmailAddress emailAddress;

    ContactInformation(ResidentialAddress residentialAddress, PhoneNumber phoneNumber, EmailAddress emailAddress) {
        this.residentialAddress = residentialAddress;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public ResidentialAddress getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(ResidentialAddress residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }
}
