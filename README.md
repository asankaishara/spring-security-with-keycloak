# spring-security-with-keycloak

* This application shows how to integrate spring secured application with keycloak.
   Use keycloak authentication, mange user's with keycloak
   
* Followings are the keycloak settings
    * version : 4.8.3.Final
    * keycloak server is running on port 8090
        * start keycloak using command --> ./standalone.sh -Djboss.socket.binding.port-offset=10
        * admin console --> http://localhost:8090/auth/
        * create realm --> demo-realm
        * edit "realm-management" as below
            * Access Type = confidential
            * Service Accounts Enabled = ON
            * Authorization Enabled = ON
            * Valid Redirect URIs = *
            * Service Account Role -> Client Roles = add all the roles
        * create new client with following settings
            * name = demo-client
            * Access Type = confidential
            * Service Accounts Enabled = ON
            * Authorization Enabled = ON
            * Valid Redirect URIs = *
        * create role called "Sample_Role"
        * create group called "Sample_Group"
            * assign role mapping -> Sample_Role
        * create some users in keycloak
            * username : user1
            * password : Password1234
            * assign the group : Sample_Group
        * go to "demo-client" -> installation, copy the json formatted installation and update the "keycloak.json"
        
* Build the "app1" and deploy the war file found in target to any compatible server.
* if you goto "http://localhost:8080/app1/" you can access public unprotected service
* if you goto "http://localhost:8080/app1/admin" you will have to login to your account to access the protected resource

* To use keycloak admin rest API, first update the configuration of "realm-management" client as mentioned above
* Just run the "Runner.java". it will create user in keycloak according to the given details.
