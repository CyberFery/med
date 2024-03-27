package uqam.team17.ramqservice.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Genre genre;
    private Integer healthInsuranceNumber;
    private Date dateOfBirth;
    private String cityOfBirth;

    private String establishedDiagnosis;

    @ElementCollection
    private List<Person> knownParentList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ContactInformation> contactInformationList;

    private Boolean isHealthcareProfessional;

    private Boolean isDoctor;

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

    public Patient() {
        // default constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(Integer healthInsuranceNumber) {
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

    public List<ContactInformation> getContactInformationList() {
        return contactInformationList;
    }

    public void setContactInformationList(List<ContactInformation> contactInformationList) {
        this.contactInformationList = contactInformationList;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genre=" + genre +
                ", healthInsuranceNumber=" + healthInsuranceNumber +
                ", dateOfBirth=" + dateOfBirth +
                ", cityOfBirth='" + cityOfBirth + '\'' +
                ", establishedDiagnosis='" + establishedDiagnosis + '\'' +
                ", knownParentList=" + knownParentList +
                ", contactInformations=" + contactInformationList +
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

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Person() {

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
}
