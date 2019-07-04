package uk.co.caprica.spa.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Credentials {

    @JsonProperty("username")
    private final String username;

    @JsonProperty("password")
    private final String password;

    @JsonCreator
    public Credentials(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        // FIXME bcrypt hash, we don't want the real password ever
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
