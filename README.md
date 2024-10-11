# Setup
1. Create a .env file in the root directory of the project and add the following environment variables:
```
# DB
DB_URL=jdbc:postgresql://<host>/falcon_shield
DB_USERNAME=
DB_PASSWORD=

#Keycloak
KC_DB_URL=jdbc:postgresql://<host>/falcon_shield_keycloak
```
2. Run the project
3. Go to https://auth.falcon-shield.tech/realms/FalconShield/account and create a new user
4. Go to http://localhost:8080/swagger-ui/index.html and login clicking on the "Authorize" button on the top right corner
5. Use the **"OIDC (OAuth2, implicit)" flow** to login 
6. Set the `client_id` to "falcon-shield-client" and select all the scopes with "select all" button
7. Click on "Authorize" and then login with the user created in step 3
8. You should now be able to use the API