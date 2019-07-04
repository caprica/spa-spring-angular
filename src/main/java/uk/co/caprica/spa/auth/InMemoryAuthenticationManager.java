package uk.co.caprica.spa.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// immutability looks important for Authentication instances

@Component
public final class InMemoryAuthenticationManager {

    public Authentication authenticate(UsernamePasswordAuthenticationToken token) {
        // FIXME obviously we'd authenticate against database etc...
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> result = new ArrayList<>();
                result.add(new SimpleGrantedAuthority("shinobi"));
                return result;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "sekiro";
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "Sekiro";
            }
        };
    }

}
