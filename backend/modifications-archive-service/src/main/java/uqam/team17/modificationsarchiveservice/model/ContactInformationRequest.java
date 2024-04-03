package uqam.team17.modificationsarchiveservice.model;


public class ContactInformationRequest implements ModificationRequest {
    private String healthInsuranceNumber;

    private ContactInformation contactInformation;

    public ContactInformationRequest() {
        //default constructor
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }


    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "ContactInformationRequest{" +
                "healthInsuranceNumber='" + healthInsuranceNumber + '\'' +
                ", contactInformation=" + contactInformation +
                '}';
    }

    @Override
    public Modifiable createModifiable(){
        return contactInformation;
    }

    @Override
    public Boolean isValid() {
        return healthInsuranceNumber != null &&
                contactInformation != null &&
                contactInformation.getEmailAddressList() != null &&
                contactInformation.getPhoneNumberList() != null &&
                contactInformation.getResidentialAddressList() != null &&
                !contactInformation.getPhoneNumberList().isEmpty() &&
                !contactInformation.getEmailAddressList().isEmpty() &&
                !contactInformation.getResidentialAddressList().isEmpty();

    }

}
