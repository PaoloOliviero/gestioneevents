package paolooliviero.ges.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class prenotazione {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn (name = "evento_id")
    private Evento evento;
    @JoinColumn  (name= "utente_id")
    private Utente utente;

    public prenotazione(Evento evento, Utente utente) {
        this.evento = evento;
        this.utente = utente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "prenotazione{" +
                "evento=" + evento +
                ", utente=" + utente +
                ", id=" + id +
                '}';
    }
}


