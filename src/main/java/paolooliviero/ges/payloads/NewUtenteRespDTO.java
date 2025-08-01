package paolooliviero.ges.payloads;

public record NewUtenteRespDTO
        (int id,
        String name,
        String surname,
        String email,
        String password
        ){
}
