package paolooliviero.ges.payloads;

import paolooliviero.ges.entities.Utente;

public record NewEventoRespDTO (
        int eventoId,
        String titolo,
        String Descrizione,
        String Luogo,
        int numeropostidisponibili,
        Utente utente
)  {


}
