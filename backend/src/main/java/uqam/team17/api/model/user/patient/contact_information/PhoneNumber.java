package uqam.team17.api.model.user.patient.contact_information;

class PhoneNumber {
    private Integer number;

    PhoneNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
