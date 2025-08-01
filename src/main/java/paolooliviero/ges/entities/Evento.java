package paolooliviero.ges.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@ToString
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numeropostidisponibili;
    @ManyToOne
    @JoinColumn(name="utente_id")
    private Utente utente;

    public Evento(String titolo, String descrizione, LocalDate data, String luogo, int numeropostidisponibili, Utente utente) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.numeropostidisponibili = numeropostidisponibili;
        this.utente = utente;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public int getNumeropostidisponibili() {
        return numeropostidisponibili;
    }

    public void setNumeropostidisponibili(int numeropostidisponibili) {
        this.numeropostidisponibili = numeropostidisponibili;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
