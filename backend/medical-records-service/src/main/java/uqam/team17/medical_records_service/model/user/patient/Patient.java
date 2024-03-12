package uqam.team17.medical_records_service.model.user.patient;

import uqam.team17.medical_records_service.model.Person;
import uqam.team17.medical_records_service.model.user.User;
import uqam.team17.medical_records_service.model.user.patient.contact_information.ContactInformation;
import uqam.team17.medical_records_service.service.ContactInformationModifier;

import java.util.Date;
import java.util.List;

public class Patient extends User implements ContactInformationModifier {
    private Genre genre;
    private Integer healthInsuranceNumber;
    private Date dateOfBirth;
    private String cityOfBirth;

    private String establishedDiagnosis;

    private List<Person> knownParents;

    private ContactInformation contactInformation;

    Patient(Person person, Genre genre, Integer healthInsuranceNumber, Date dateOfBirth) {
        super(person.getFirstName(), person.getLastName());
        this.genre = genre;
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.dateOfBirth = dateOfBirth;
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

    public List<Person> getKnownParents() {
        return knownParents;
    }

    public void setKnownParents(List<Person> knownParents) {
        this.knownParents = knownParents;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public void modifyContactInformation() {

    }

    @Override
    protected void consultMedicalRecord() {

    }

    @Override
    protected void authentification() {

    }
}
