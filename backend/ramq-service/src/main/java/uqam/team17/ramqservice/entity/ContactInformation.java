package uqam.team17.ramqservice.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;
    @ElementCollection
    private List<ResidentialAddress> residentialAddressList;
    private PhoneNumber phoneNumber;
    private EmailAddress emailAddress;

    public ContactInformation() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ResidentialAddress> getResidentialAddress() {
        return this.residentialAddressList;
    }

    public void setResidentialAddress(List<ResidentialAddress> residentialAddress) {
        this.residentialAddressList = residentialAddress;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmailAddress getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setResidentialAddressList(List<ResidentialAddress> residentialAddressList) {
        this.residentialAddressList = residentialAddressList;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "id=" + id +
                ", patient=" + patient +
                ", residentialAddressList=" + residentialAddressList +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress=" + emailAddress +
                '}';
    }

    @Embeddable
    public static class ResidentialAddress {
        private String address;

        public ResidentialAddress() {
            // default constructor
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

        public String getNumber() {
            return this.number;
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