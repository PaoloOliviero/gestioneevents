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
import paolooliviero.ges.entities.Utente;
import paolooliviero.ges.exceptions.BadRequestException;
import paolooliviero.ges.exceptions.NotFoundException;
import paolooliviero.ges.payloads.NewUtenteDTO;
import paolooliviero.ges.repositories.UtenteRepository;

@Service
@Slf4j
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder bcrypt;


    public Utente save(NewUtenteDTO payload) {
        this.utenteRepository.findByEmail(payload.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email" + utente.getEmail() + "è già in uso!");
        });

        Utente newUtente = new Utente(payload.name(), payload.surname(), payload.email(), bcrypt.encode(payload.password()), Ruolo.Utente_Normale);

        Utente savedUtente = this.utenteRepository.save(newUtente);

        log.info("L'utente con id: " + savedUtente.getId() + " è stato salvato correttamente!");


        return savedUtente;
    }

    public Page<Utente> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.utenteRepository.findAll(pageable);
    }

    public Utente findById(int userId) {
        return this.utenteRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public Utente findByIdAndUpdate(int utenteId, NewUtenteDTO payload) {

        Utente found = this.findById(utenteId);

        if (!found.getEmail().equals(payload.email()))
            this.utenteRepository.findByEmail(payload.email()).ifPresent(user -> {
                throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
            });

        found.setName(payload.name());
        found.setSurname(payload.surname());
        found.setEmail(payload.email());
        found.setPassword(payload.password());

        Utente modifiedUser = this.utenteRepository.save(found);

        log.info("L'utente con id " + found.getId() + " è stato modificato!");

        return modifiedUser;




    }


}
