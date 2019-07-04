package uk.co.caprica.spa.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
public final class AuthController {

    @Autowired
    private InMemoryAuthenticationManager authenticationManager;

    @PostMapping(value="/login", consumes="application/json")
    public ResponseEntity<?> login(HttpServletRequest req, @RequestBody Credentials credentials) {
        // We don't want the Spring security formlogin, we want programmatic login from the SPA so we do this bit ourselves

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password());
        authReq.setDetails(new WebAuthenticationDetails(req)); // required? first example didn't do this

        // Presumably this is correct...
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        // Presumably this is needed...
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        // FIXME should a failed login remove that session attribute etc? should i remove it at the start?

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
