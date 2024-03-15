package uqam.team17.ramqservice.model.user.patient;

import uqam.team17.ramqservice.model.Person;
import uqam.team17.ramqservice.model.user.User;
import uqam.team17.ramqservice.model.user.patient.contact_information.ContactInformation;

import java.util.Date;
import java.util.List;

public class Patient extends User {
    private final Integer healthInsuranceNumber;
    private final Date dateOfBirth;
    private Genre genre;
    private String establishedDiagnosis;
    private List<Person> knownParents;
    private final String cityOfBirth;
    private List<ContactInformation> contactInformations;

    private final Boolean isHealthcareProfessionnal;
    private final Boolean isPhysician;

    Patient(Genre genre, Integer healthInsuranceNumber, Date dateOfBirth, String cityOfBirth, Boolean isHealthcareProfessionnal, Boolean isPhysician) {
        super();
        this.genre = genre;
        this.healthInsuranceNumber = healthInsuranceNumber;
        this.dateOfBirth = dateOfBirth;
        this.cityOfBirth = cityOfBirth;
        this.isHealthcareProfessionnal = isHealthcareProfessionnal;
        this.isPhysician = isPhysician;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCityOfBirth() {
        return cityOfBirth;
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

    public List<ContactInformation> getContactInformation() {
        return contactInformations;
    }

    public void setContactInformation(List<ContactInformation> contactInformation) {
        this.contactInformations = contactInformation;
    }

    @Override
    protected void consultMedicalRecord() {

    }

    @Override
    protected void authentification() {

    }

//    @Override
//    public String toString() {
//        String healthInsuranceNumber = this.getHealthInsuranceNumber().toString();
//        String dateOfBirth = this.getDateOfBirth().toString();
//    }
}
