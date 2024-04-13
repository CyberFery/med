package uqam.team17.modificationsarchiveservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "contact_information")
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactInformationId;
    @ElementCollection
    private List<ResidentialAddress> residentialAddressList;
    @ElementCollection
    private List<PhoneNumber> phoneNumberList;
    @ElementCollection
    private List<EmailAddress> emailAddressList;

    public ContactInformation() {
    }

    public ContactInformation(Long contactInformationId, List<ResidentialAddress> residentialAddressList,
                              List<PhoneNumber> phoneNumberList, List<EmailAddress> emailAddressList) {
        this.contactInformationId = contactInformationId;
        this.residentialAddressList = residentialAddressList;
        this.phoneNumberList = phoneNumberList;
        this.emailAddressList = emailAddressList;
    }



    public List<ResidentialAddress> getResidentialAddressList() {
        return residentialAddressList;
    }

    public void setResidentialAddressList(List<ResidentialAddress> residentialAddressList) {
        this.residentialAddressList = residentialAddressList;
    }

    public List<PhoneNumber> getPhoneNumberList() {
        return phoneNumberList;
    }

    public void setPhoneNumberList(List<PhoneNumber> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    public List<EmailAddress> getEmailAddressList() {
        return emailAddressList;
    }

    public void setEmailAddressList(List<EmailAddress> emailAddressList) {
        this.emailAddressList = emailAddressList;
    }

    public ModificationType getType() {
        return ModificationType.CONTACT_INFORMATION;
    }

    public Long getContactInformationId() {
        return contactInformationId;
    }

    public void setContactInformationId(Long contactInformationId) {
        this.contactInformationId = contactInformationId;
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "contactInformationId=" + contactInformationId +
                ", residentialAddressList=" + residentialAddressList +
                ", phoneNumberList=" + phoneNumberList +
                ", emailAddressList=" + emailAddressList +
                '}';
    }

    @Embeddable
    public static class ResidentialAddress {
        private String address;

        public ResidentialAddress() {
            //empty constructor
        }

        public ResidentialAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "ResidentialAddress{" +
                    "address='" + address + '\'' +
                    '}';
        }
    }

    @Embeddable
    public static class PhoneNumber {
        private String number;

        public PhoneNumber() {
            // default constructor
        }

        public PhoneNumber(String number) {
            this.number = number;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "PhoneNumber{" +
                    "number='" + number + '\'' +
                    '}';
        }
    }

    @Embeddable
    public static class EmailAddress {
        private String email;

        public EmailAddress() {
            // default constructor
        }

        public EmailAddress(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return "EmailAddress{" +
                    "email='" + email + '\'' +
                    '}';
        }
    }
}
