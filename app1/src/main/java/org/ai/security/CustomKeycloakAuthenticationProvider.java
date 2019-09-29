package org.ai.security;

import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomKeycloakAuthenticationProvider extends KeycloakAuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) super.authenticate(authentication);
        //token can be used to read user details, roles, authentication status
        return new CustomAuthenticationToken(
                token.getCredentials(), token.getAccount(), true, token.getAuthorities()
        );
    }
}
