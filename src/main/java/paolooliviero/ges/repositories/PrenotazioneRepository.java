package paolooliviero.ges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paolooliviero.ges.entities.Prenotazione;

import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
    Optional<Prenotazione> findByEmail(String email);
}