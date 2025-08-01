package paolooliviero.ges.controllers;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.ges.entities.Utente;
import paolooliviero.ges.exceptions.ValidationException;
import paolooliviero.ges.payloads.NewUtenteDTO;
import paolooliviero.ges.payloads.NewUtenteRespDTO;
import paolooliviero.ges.services.UtenteService;


    @RestController
    @RequestMapping("/users")
    public class UtenteController {

        @Autowired
        private UtenteService usersService;

        @GetMapping
        @PreAuthorize("hasAuthority('Utente_Normale')")
        public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy
        ) {
            return (Page<Utente>) this.usersService.findAll(page, size, sortBy);
        }


        @PostMapping()
        @ResponseStatus(HttpStatus.CREATED)
        @PreAuthorize("hasAuthority('Utente_Normale')")
        public NewUtenteRespDTO save(@RequestBody @Validated NewUtenteDTO payload, BindingResult validationResult) {
            if (validationResult.hasErrors()) {
                throw new ValidationException(validationResult.getFieldErrors()
                        .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
            } else {
                Utente newUtente = this.usersService.save(payload);
                return new NewUtenteRespDTO(newUtente.getId());
            }
        }

        @GetMapping("/{utenteId}")
        @PreAuthorize("hasAuthority('Utente_Normale')")
        public Utente getById(@PathVariable int utenteId) {
            return this.usersService.findById(utenteId);
        }

        @PutMapping("/{userId}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public Utente getByIdAndUpdate(@PathVariable int userId, @RequestBody NewUtenteDTO payload) {
            return this.usersService.findByIdAndUpdate(userId, payload);
        }
    }
