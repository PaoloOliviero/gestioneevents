package paolooliviero.ges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.ges.entities.Evento;
import paolooliviero.ges.entities.Utente;
import paolooliviero.ges.exceptions.ValidationException;
import paolooliviero.ges.payloads.NewEventoDTO;
import paolooliviero.ges.payloads.NewEventoRespDTO;
import paolooliviero.ges.services.EventoService;

@RestController
@RequestMapping("/users")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    @PreAuthorize("hasAuthority('Utente_Normale')")
    public Page<Evento> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String sortBy
    ) {
        return (Page<Evento>) this.eventoService.findAll(page, size, sortBy);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('Organizzatore_evento')")
    public NewEventoRespDTO save(@RequestBody @Validated NewEventoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Evento newEvento = this.eventoService.save(payload);
            return new NewEventoRespDTO(newEvento.getId());
        }
    }

    @GetMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('Utente_Normale')")
    public Evento getById(@PathVariable int eventoId) {
        return this.eventoService.findById(eventoId);
    }

}