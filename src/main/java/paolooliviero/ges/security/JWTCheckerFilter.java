package paolooliviero.ges.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import paolooliviero.ges.entities.Utente;
import paolooliviero.ges.exceptions.UnauthorizedException;
import paolooliviero.ges.services.UtenteService;

import java.io.IOException;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {
    // Estendendo OncePerRequestFilter sto "conformando" il mio filtro alla Filter Chain. Sarò costretto ad implementare il metodo
    // astratto ereditato doFilterInternal

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UtenteService usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'Authorization Header nel formato corretto!");

        String accessToken = authHeader.replace("Bearer ", "");

        jwtTools.verifyToken(accessToken);

        String userId = jwtTools.extractIdFromToken(accessToken);
        Utente currentUser = this.usersService.findById(UUID.fromString(userId));

        // 2. Trovato l'utente devo associarlo al Security Context, questa è la maniera per Spring Security di sapere qual è l'utente
        // che sta effettuando la richiesta, ciò ci è fondamentale perché quando arriviamo al controller dobbiamo sapere qual è il ruolo
        // di chi sta effettuando la richiesta o in alcuni casi ci interessa proprio sapere chi sia l'utente per controllare ad es se sia
        // lui il proprietario della risorsa che sta andando a leggere/modificare/cancellare
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, currentUser.getAuthorities());
        // Il terzo parametro serve per poter utilizzare i vari @PreAuthorize sugli endpoint, perché così il SecurityContext saprà quali sono
        // i ruoli dell'utente che sta effettuando la richiesta
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
