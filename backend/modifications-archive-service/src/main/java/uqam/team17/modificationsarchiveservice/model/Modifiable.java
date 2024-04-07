package uqam.team17.modificationsarchiveservice.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Modifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modifiableId;

    abstract ModificationType getType();

    public Long getModifiableId() {
        return modifiableId;
    }

    public void setModifiableId(Long modifiableId) {
        this.modifiableId = modifiableId;
    }


    @Override
    public String toString() {
        return "Modifiable{" +
                "modifiableId=" + modifiableId +
                '}';
    }
}
