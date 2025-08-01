package paolooliviero.ges.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class NewEventoDTO {
    @NotEmpty(message = "Il Titolo è obbligatorio!")
    @Size(min = 2, max = 40, message = "Il nome deve essere di lunghezza compresa tra 2 e 40")
    String titolo;
    @NotEmpty(message = "Il descrizione è obbligatorio!")
    @Size(min = 2, max = 40, message = "Il cognome deve essere di lunghezza compresa tra 2 e 40")
    String descrizione;
    @NotEmpty(message = "La data è obbligatoria")
    LocalDate data;
    @NotEmpty(message = "La password è obbligatoria!")
    @Size(min = 4)
    String luogo;
    @NotEmpty(message = "La password è obbligatoria!")
    Integer numeropostidisponibili;

}
