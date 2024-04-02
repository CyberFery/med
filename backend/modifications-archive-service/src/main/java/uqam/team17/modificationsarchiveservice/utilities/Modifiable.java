package uqam.team17.modificationsarchiveservice.utilities;

import uqam.team17.modificationsarchiveservice.model.ModificationType;

public interface Modifiable {
    ModificationType getType();
    String serialize();
    void deserialize(String data);
}
