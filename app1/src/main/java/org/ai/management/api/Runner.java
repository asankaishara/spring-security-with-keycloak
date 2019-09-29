package org.ai.management.api;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.Optional;

public class Runner {

    public static void main(String[] args) {
        System.out.println("creating user in keycloak..");
        KeycloakClientAdapter adapter = new KeycloakClientAdapter(
                "http://localhost:8090/auth",
                "demo-realm",
                "realm-management",
                "41192bfc-b534-4721-9b67-7edbe9eefbe8"
        );
        RealmResource realmResource = adapter.getKeycloakAdapter().realm("demo-realm");
        createNewUserInKeycloak(
                "ishara", "ishara@gmail.com", "asanka", "ishara", realmResource
        );
    }

    private static void createNewUserInKeycloak(String username, String email, String firstName, String lastName,
                                         RealmResource realmResource) {
        UserRepresentation ur = new UserRepresentation();
        ur.setEmail(email);
        ur.setFirstName(firstName);
        ur.setLastName(lastName);
        ur.setUsername(username);
        ur.setEnabled(true);
        realmResource.users().create(ur);
        Optional<UserRepresentation> userRepresentation = searchUserByEmailInKeycloak(ur.getEmail(), realmResource.users());
        if(userRepresentation.isPresent()) {
            getCalistaGroupRepresentation(realmResource.groups().groups()).ifPresent(
                    groupRepresentation -> joinGroup(realmResource.users().get(userRepresentation.get().getId()), groupRepresentation)
            );
        }
    }

    private static Optional<UserRepresentation> searchUserByEmailInKeycloak(String email, UsersResource userResource) {
        List<UserRepresentation> urList =
                userResource.search(null, null, null, email, null, null, true);
        return urList.isEmpty() ? Optional.empty() : Optional.of(urList.get(0));
    }

    private static Optional<GroupRepresentation> getCalistaGroupRepresentation(List<GroupRepresentation> groups) {
        for (GroupRepresentation group : groups) {
            if(group.getName().equalsIgnoreCase("Sample_Group")) {
                return Optional.of(group);
            }
        }
        return Optional.empty();
    }

    private static void joinGroup(UserResource userResource, GroupRepresentation group) {
        for (GroupRepresentation userGroup : userResource.groups()) {
            if(userGroup.getName().equalsIgnoreCase("Sample_Group")) {
                System.out.println("group already exist");
                return;
            }
        }
        userResource.joinGroup(group.getId());
    }

}
