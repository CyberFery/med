package uqam.team17.modificationsarchiveservice.model;

public class CancelModificationRequest {
    private String healthInsuranceNumber;

    private ModificationType type;

    public CancelModificationRequest() {
    }

    public String getHealthInsuranceNumber() {
        return healthInsuranceNumber;
    }

    public void setHealthInsuranceNumber(String healthInsuranceNumber) {
        this.healthInsuranceNumber = healthInsuranceNumber;
    }

    public ModificationType getType() {
        return type;
    }

    public void setType(ModificationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CancelModificationRequest{" +
                "healthInsuranceNumber='" + healthInsuranceNumber + '\'' +
                ", type=" + type +
                '}';
    }
}
