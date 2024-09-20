package Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotBlank(message = "Il titolo è richiesto!")
    private String titolo;

    @NotBlank(message = "La descrizione è richiesta!")
    private String descrizione;

    private LocalDateTime data;

    @NotBlank(message = "La location è richiesta!")
    private String location;

    private int postiDisponibili;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Utente organizzatore;
}
