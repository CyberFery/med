package uqam.team17.healthcare_professionals_medical_records_service.model.user.patient.contact_information;

import java.util.List;

class ResidentialAddress {
    private List<String> addresses;

    ResidentialAddress(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
