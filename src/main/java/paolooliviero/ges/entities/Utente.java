package paolooliviero.ges.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import paolooliviero.ges.Enum.Ruolo;


@Entity
    @Getter
    @AllArgsConstructor
    @ToString
    public class Utente {
       @Id
       @GeneratedValue (strategy = GenerationType.IDENTITY)
       private int id;
       private String name;
       private String surname;
        private String password;
        private String email;
        @Enumerated(EnumType.STRING)
        private Ruolo ruolo;


    public Utente(String name, String surname, String password, String email, Ruolo ruolo) {
            this.name = name;
            this.surname = surname;
            this.password = password;
            this.email = email;
            this.ruolo = ruolo;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getId() {
            return id;
        }

    @Override
    public String toString() {
        return "Utente{" +
                "ruolo=" + ruolo +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

