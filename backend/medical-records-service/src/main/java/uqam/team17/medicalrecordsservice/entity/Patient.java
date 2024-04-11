package uqam.team17.medicalrecordsservice.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Patient {
    @Id
    private String healthInsuranceNumber;
    private String firstName;
    private String lastName;
    private Genre genre;

    private Date dateOfBirth;
    private String cityOfBirth;
    private String establishedDiagnosis;

    @ElementCollection
    private List<Person> knownParentList;
    @Embedded
    private ContactInformation contactInformation;

    private Boolean isHealthcareProfessional;

    private Boolean isDoctor;

    public Patient() {
        // default constructor
    }

    public Patient(String healthInsuranceNumber, String firstName, String lastName, Genre genre, Date dateOfBirth, String establishedDiagnosis, Boolean isHealthcareProfessional, Boolean isDoctor) {
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;
        this.dateOfBirth = dateOfBirth;
        this.establishedDiagnosis = establishedDiagnosis;
        this.isHealthcareProfessional = isHealthcareProfessional;
        this.isDoctor = isDoctor;
    }
    public Boolean getIsHealthcareProfessional() {
        return isHealthcareProfessional;
    }

    public void setIsHealthcareProfessional(Boolean healthcareProfessional) {
        isHealthcareProfessional = healthcareProfessional;
    }

    public Boolean getIsDoctor() {
        return isDoctor;
    }

    public void setIsDoctor(Boolean physician) {
        isDoctor = physician;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
    }

    public void setCityOfBirth(String cityOfBirth) {
        this.cityOfBirth = cityOfBirth;
    }

    public String getEstablishedDiagnosis() {
        return establishedDiagnosis;
    }

    public void setEstablishedDiagnosis(String establishedDiagnosis) {
        this.establishedDiagnosis = establishedDiagnosis;
    }

    public List<Person> getKnownParentList() {
        return knownParentList;
    }

    public void setKnownParentList(List<Person> knownParentList) {
        this.knownParentList = knownParentList;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void updateContactInformation(ContactInformation contactInfo) {
        List<PhoneNumber> phoneNumberList = contactInfo.getPhoneNumberList();
        List<ResidentialAddress> residentialAddressList = contactInfo.getResidentialAddressList();
        List<EmailAddress> emailAddressList = contactInfo.getEmailAddressList();

        if (phoneNumberList != null && !phoneNumberList.isEmpty()) {
            this.contactInformation.setPhoneNumberList(phoneNumberList);
        }
        if (residentialAddressList != null && !residentialAddressList.isEmpty()) {
            this.contactInformation.setResidentialAddressList(residentialAddressList);
        }
        if (emailAddressList != null && !emailAddressList.isEmpty()) {
            this.contactInformation.setEmailAddressList(emailAddressList);
        }
    }

    @Override
    public String toString() {
        return "Patient{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genre=" + genre +
                ", healthInsuranceNumber=" + healthInsuranceNumber +
                ", dateOfBirth=" + dateOfBirth +
                ", cityOfBirth='" + cityOfBirth + '\'' +
                ", establishedDiagnosis='" + establishedDiagnosis + '\'' +
                ", knownParentList=" + knownParentList +
                ", contactInformation=" + contactInformation +
                ", isHealthcareProfessional=" + isHealthcareProfessional +
                ", isDoctor=" + isDoctor +
                '}';
    }

    public enum Genre {
        FEMALE,
        MALE
    }

    @Embeddable
    public static class Person {
        private String firstName;
        private String lastName;

        public Person() {
            // default constructor
        }

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }

    @Embeddable
    public static class ContactInformation {
        @ElementCollection
        private List<ResidentialAddress> residentialAddressList;
        @ElementCollection
        private List<PhoneNumber> phoneNumberList;
        @ElementCollection
        private List<EmailAddress> emailAddressList;

        public ContactInformation() {
            // default constructor
        }

        public ContactInformation(List<ResidentialAddress> residentialAddressList, List<PhoneNumber> phoneNumberList, List<EmailAddress> emailAddressList) {
            this.residentialAddressList = residentialAddressList;
            this.phoneNumberList = phoneNumberList;
            this.emailAddressList = emailAddressList;
        }

        public void setResidentialAddressList(List<ResidentialAddress> residentialAddressList) {
            this.residentialAddressList = residentialAddressList;
        }

        public List<ResidentialAddress> getResidentialAddressList() {
            return residentialAddressList;
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

        @Override
        public String toString() {
            return "ContactInformation{" +
                    "residentialAddressList=" + residentialAddressList +
                    ", phoneNumberList=" + phoneNumberList +
                    ", emailAddressList=" + emailAddressList +
                    '}';
        }
    }

    @Embeddable
    public static class ResidentialAddress {
        private String address;

        public ResidentialAddress() {
            // default constructor
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
