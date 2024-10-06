# Install
1. Create a .env file in the root directory of the project and add the following environment variables:
```
# DB
DB_URL=jdbc:postgresql://<host>/falcon_shield_dev
DB_USERNAME=
DB_PASSWORD=
```
2. Run the docker-compose file to start the keycloak server (optional)
3. Go to http://localhost:9090/realms/FalconShield/account and create a new user