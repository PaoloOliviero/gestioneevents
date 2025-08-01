package paolooliviero.ges.payloads;

public record NewPrenotazioneResponseDTO (
        int id,
        int eventoid,
        String eventoTitolo,
        int utenteID,
        String username
) {
}
