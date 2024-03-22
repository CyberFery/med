package uqam.team17.ramqservice.model.user.patient.contact_information;

class EmailAddress {
    private String email;

    EmailAddress(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

