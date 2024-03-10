package uqam.team17.healthcare_professionals_medical_records_service.model.user;

import uqam.team17.healthcare_professionals_medical_records_service.model.Person;

public abstract class User extends Person {
    protected abstract void consultMedicalRecord();
    protected abstract void authentification();

    protected User() {
        super();
    }

    protected User(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
