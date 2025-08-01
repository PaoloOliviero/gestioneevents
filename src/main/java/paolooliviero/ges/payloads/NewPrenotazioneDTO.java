package paolooliviero.ges.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewPrenotazioneDTO(
        @NotEmpty(message = "l'ID dell'evento è obbligatorio!")
        @Size(min = 2, max = 40, message = "Il nome deve essere di lunghezza compresa tra 2 e 40")
        Integer eventoid,
        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 2, max = 40, message = "Il cognome deve essere di lunghezza compresa tra 2 e 40")
        String eventotitolo,
        @NotEmpty(message = "La password è obbligatoria!")
        @Size(min = 4)
        Long Utenteid,
        //@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{4,}$", message = "La password deve contenere: 1 carat maiuscolo, uno minuscolo.....")
        String username) { }
