# Migration diti4_spring_mvc → Spring Boot (API REST + Swagger + Flyway + Docker)

## Fichiers fournis
- `pom.xml` — starters Spring Boot 3.3.4 + Flyway + springdoc-openapi + MapStruct
- `src/main/java/diti/DitiApplication.java` — point d'entrée
- `src/main/resources/application.properties` — config datasource/JPA/Flyway/Swagger
- `src/main/resources/db/migration/V1__init_schema.sql` — schéma initial Flyway
- `Dockerfile` — build multi-stage (Maven → JRE Alpine)
- `docker-compose.yml` — services `app` + `db` (PostgreSQL 16)
- `.env.example` — variables à copier en `.env`

## 1. Copier ton code métier
Copie `entity/`, `dto/`, `mapper/`, `repository/`, `service/`,
`ProduitRestController`, `TypeRestController`, `GlobalExceptionHandler`,
`ResourceNotFoundException` sous `src/main/java/diti/`. Remplace
`javax.persistence`/`javax.validation` par `jakarta.persistence`/`jakarta.validation`.

## 2. Flyway remplace `hibernate.ddl-auto=update`
`V1__init_schema.sql` crée `types` et `products` d'après les champs visibles
dans `Produit.java` (id, libelle, prix, type_id). **Vérifie et ajuste les
colonnes de `types`** si `Type.java` a d'autres champs que `id`/`libelle` —
je n'avais pas ce fichier sous les yeux.

Toute nouvelle évolution de schéma va dans un nouveau fichier
`V2__quelque_chose.sql`, jamais en modifiant V1 après qu'il ait tourné.

## 3. Swagger / OpenAPI
Rien à coder : `springdoc-openapi-starter-webmvc-ui` scanne automatiquement
tes `@RestController`. Une fois l'app lancée :
- UI interactive : `http://localhost:8081/swagger-ui.html`
- JSON brut : `http://localhost:8081/v3/api-docs`

Pour enrichir la doc, tu peux ajouter `@Operation(summary = "...")` sur les
méthodes et `@Schema(description = "...")` sur les champs des DTO — optionnel,
ça marche déjà sans.

## 4. Lancer avec Docker Compose
```bash
cp .env.example .env
docker compose up --build
```
- L'API démarre sur `http://localhost:8080`
- PostgreSQL est exposé sur `localhost:5432` (utile pour t'y connecter avec un client SQL)
- Les données persistent dans le volume `diti4-db-data` entre les redémarrages
- Flyway s'exécute automatiquement au démarrage de `app`, avant que
  l'application ne réponde aux requêtes

Pour arrêter : `docker compose down` (ajoute `-v` pour aussi supprimer les
données de la base).

## 5. Lancer en local sans Docker (optionnel)
```bash
export DB_HOST=localhost DB_NAME=diti4 DB_USER=postgres DB_PASSWORD=Nabou988
mvn spring-boot:run
```
