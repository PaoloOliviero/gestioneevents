package paolooliviero.ges.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import paolooliviero.ges.entities.Evento;

import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    Optional<Evento> findByEmail(String email);
}