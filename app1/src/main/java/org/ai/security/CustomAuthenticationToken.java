package org.ai.security;

import org.keycloak.adapters.spi.KeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class CustomAuthenticationToken extends KeycloakAuthenticationToken {

    private static final long serialVersionUID = 320L;
    private transient Object principal;
    private boolean interactive;
    private transient Object credentials;
    private UserDetails userDetails;

    CustomAuthenticationToken(Object aCredentials, KeycloakAccount account,
                               boolean interactive, Collection<? extends GrantedAuthority> authorities) {
        super(account, interactive, authorities);
        credentials = aCredentials;
        setDetails(account);
        this.interactive = interactive;
        principal = account.getPrincipal();
        //this.userDetails = userDetails;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isInteractive() {
        return interactive;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomAuthenticationToken that = (CustomAuthenticationToken) o;
        return interactive == that.interactive &&
                Objects.equals(principal, that.principal) &&
                Objects.equals(credentials, that.credentials) &&
                Objects.equals(userDetails, that.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), principal, interactive, credentials, userDetails);
    }
}
