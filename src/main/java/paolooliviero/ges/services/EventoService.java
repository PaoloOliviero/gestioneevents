package paolooliviero.ges.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import paolooliviero.ges.Enum.Ruolo;
import paolooliviero.ges.entities.Evento;
import paolooliviero.ges.entities.Utente;
import paolooliviero.ges.exceptions.BadRequestException;
import paolooliviero.ges.exceptions.NotFoundException;
import paolooliviero.ges.payloads.NewEventoDTO;
import paolooliviero.ges.payloads.NewUtenteDTO;
import paolooliviero.ges.repositories.EventoRepository;
import paolooliviero.ges.repositories.UtenteRepository;

@Service
@Slf4j
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public Evento save(NewEventoDTO payload, Utente utente) {

        Evento newEvento = new Evento(payload.titolo(), payload.descrizione(), payload.data(), payload.luogo(), payload.numeropostidisponibili(), Utente utente);

        Evento savedEvento = this.eventoRepository.save(newEvento);

        log.info("L'evento con id: " + savedEvento.getId() + " è stato salvato correttamente!");


        return savedEvento;
    }

    public Page<Evento> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.eventoRepository.findAll(pageable);
    }

    public Evento findById(int userId) {
        return this.eventoRepository.findById(userId).orElseThrow(() -> new NotFoundException(eventoId));
    }
}