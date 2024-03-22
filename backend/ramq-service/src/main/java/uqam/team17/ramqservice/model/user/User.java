package uqam.team17.ramqservice.model.user;

import uqam.team17.ramqservice.model.Person;

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