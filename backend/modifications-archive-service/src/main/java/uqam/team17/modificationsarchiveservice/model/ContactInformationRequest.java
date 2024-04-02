package uqam.team17.modificationsarchiveservice.model;



public class ContactInformationRequest {
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
}
