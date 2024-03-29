package uqam.team17.modificationsarchiveservice.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Modification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ModificationType type;

    private String content;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Doctor creator;
    private LocalDateTime timestamp;

    public Modification(){

    }

    public Modification(ModificationType type, String content, Doctor doctor, LocalDateTime timestamp){
        this.type = type;
        this.content = content;
        this.creator = doctor;
        this.timestamp = timestamp;
    }

}
