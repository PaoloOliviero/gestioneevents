package paolooliviero.ges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paolooliviero.ges.entities.Utente;

import java.util.Optional;

    @Repository
    public interface UtenteRepository extends JpaRepository<Utente, Integer> {
        Optional<paolooliviero.ges.repositories.UtenteRepository> findByEmail(String email);
    }
