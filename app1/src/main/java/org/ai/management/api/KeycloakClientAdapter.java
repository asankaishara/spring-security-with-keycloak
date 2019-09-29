package org.ai.management.api;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakClientAdapter {

    private Keycloak keycloakAdapter;

    public KeycloakClientAdapter(String keycloakDomain, String keycloakRealm, String keycloakClientId, String keycloakClientSecret) {
        keycloakAdapter = KeycloakBuilder.builder()
                .serverUrl(keycloakDomain)
                .realm(keycloakRealm)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakClientId)
                .clientSecret(keycloakClientSecret)
                .build();
    }

    public Keycloak getKeycloakAdapter() {
        return keycloakAdapter;
    }

}
