package uqam.team17.modificationsarchiveservice.model;

import java.time.LocalDateTime;

public class ModificationRequest {
    ModificationType type;
    String content;
    LocalDateTime timestamp;


    public ModificationRequest (ModificationType type, String content, LocalDateTime timestamp){
        this.type = type;
        this.content = content;
        this.timestamp = timestamp;
    }
}
