package paolooliviero.ges.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import paolooliviero.ges.entities.Utente;
import paolooliviero.ges.exceptions.ValidationException;
import paolooliviero.ges.payloads.LoginDTO;
import paolooliviero.ges.payloads.LoginRespDTO;
import paolooliviero.ges.payloads.NewUtenteDTO;
import paolooliviero.ges.payloads.NewUtenteRespDTO;
import paolooliviero.ges.services.AuthService;
import paolooliviero.ges.services.UtenteService;

    @RestController
    @RequestMapping("/auth")
    public class AuthController {
        @Autowired
        private AuthService authService;
        @Autowired
        private UtenteService utenteService;

        @PostMapping("/login")
        public LoginRespDTO login(@RequestBody LoginDTO body) {
            String accessToken = authService.checkCredentialsAndGenerateToken(body);
            return new LoginRespDTO(accessToken);
        }

        @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED)
        public NewUtenteRespDTO save(@RequestBody @Validated NewUtenteDTO payload, BindingResult validationResult) {
            if (validationResult.hasErrors()) {
                //validationResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
                throw new ValidationException(validationResult.getFieldErrors()
                        .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
            } else {
                Utente newUtente = this.utenteService.save(payload);
                return new NewUtenteRespDTO(newUtente.getId());
            }

        }

    }
}
