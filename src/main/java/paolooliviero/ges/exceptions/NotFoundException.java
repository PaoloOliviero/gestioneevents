package paolooliviero.ges.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("La risorsa con id " + id + " non è stata trovata!");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}