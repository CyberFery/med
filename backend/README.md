## Documentation de référence

Pour plus de détails, veuillez consulter les sections suivantes :

* [Documentation officielle d'Apache Maven](https://maven.apache.org/guides/index.html)
* [Guide de référence du plugin Spring Boot Maven](https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/)
* [Créer une image OCI](https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#using.devtools)
* [Support Docker Compose](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#features.docker-compose)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#web.security)
* [Spring Session](https://docs.spring.io/spring-session/reference/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#web)

### Guides

Les guides suivants illustrent concrètement l'utilisation de certaines fonctionnalités :

* [Sécuriser une application Web](https://spring.io/guides/gs/securing-web/)
* [Spring Boot et OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authentifier un utilisateur avec LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Créer un service Web RESTful](https://spring.io/guides/gs/rest-service/)
* [Servir du contenu Web avec Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Créer des services REST avec Spring](https://spring.io/guides/tutorials/rest/)

### Support Docker Compose (au besoin pour s'assurer du fonctionnement indépendamment de l'environnement)

Ce projet contient un fichier Docker Compose nommé `compose.yaml`.

Il faut s'assurer d'ajouter au moins un service dans le fichier `compose.yaml`.

> Note : Structurer une application backend Spring Boot Java comme une API RESTful implique d'organiser le code et les
> ressources de manière à respecter les principes RESTful.

## Voici une structure typique pour une telle application :

1. **Structure du projet** :
    - **src/main/java** : Contient le code source Java.
        - **uqam.team17.api** : Package racine de l'application.
            - **controller** : Contient les classes de contrôleur responsables de la gestion des requêtes HTTP entrantes
              et du retour des réponses HTTP.
            - **service** : Contient les classes de service qui implémentent la logique métier/affaire.
            - **repository** : Contient les classes de référentiel responsables de l'interaction avec la base de
              données.
            - **model** : Contient les classes de modèle qui représentent les entités de l'application.
    - **src/main/resources** : Contient les ressources non Java telles que `application.properties` (pour la
      configuration
      de Spring Boot) et d'autres fichiers statiques.
    - **src/test** : Contient les classes de test.

2. **Couche Contrôleur** :
    - Les contrôleurs reçoivent les requêtes HTTP entrantes, délèguent le traitement aux classes de service et renvoient
      les réponses HTTP.
    - Chaque classe de contrôleur est responsable d'un ensemble spécifique de points de terminaison (endpoints) liés (
      par
      exemple (en localhost), `localhost:8080/api/users` pour les opérations liées aux utilisateurs).
    - Utilisez des annotations telles
      que `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping` et `@DeleteMapping` pour
      définir les points de terminaison et gérer les méthodes HTTP.

3. **Couche Service** :
    - Les classes de service contiennent la logique métier et orchestrent les interactions entre les contrôleurs et les
      référentiels.
    - Chaque classe de service encapsule un ensemble spécifique d'opérations liées (par exemple, les opérations de
      gestion des utilisateurs).

4. **Couche Référentiel** :
    - Les classes de référentiel interagissent avec la base de données pour effectuer des opérations CRUD (Create, Read,
      Update, Delete) sur les entités.
    - Utilisez Spring Data JPA ou d'autres frameworks ORM pour simplifier les opérations de base de données.

5. **Couche Modèle** :
    - Les classes de modèle représentent les entités dans le domaine d'application de l'application plus spécifiquement.
    - Définir les classes pour les objets/entités de domaine et annotez-les avec les annotations JPA appropriées (par
      exemple, `@Entity`, `@Id`, `@GeneratedValue`, `@Column`).

6. **Configuration** :
    - Configurer l'application à l'aide de fichiers `application.properties` ou `application.yml` dans le répertoire
      `src/main/resources`.
    - Définir les paramètres de connexion à la base de données, le port du serveur, les configurations de
      journalisation, etc.

7. **Sécurité** :
    - Implémentez des fonctionnalités de sécurité telles que l'**authentification** et l'**autorisation** à l'aide de
      Spring
      Security.
    - Sécurisez vos points de terminaison API **en fonction des rôles et des permissions des utilisateurs**.

8. **Gestion des Exceptions** :
    - Implémenter une gestion globale des exceptions pour gérer les erreurs de manière élégante et renvoyer des réponses
      d'erreur significatives aux clients (code d'erreur HTTP et des vues significatives).

9. **Tests** :
    - Écrire des tests unitaires et des tests d'intégration pour vérifier la fonctionnalité de l'application.
    - Utilisez des frameworks tels que JUnit et Mockito pour les tests.

10. **Documentation** :

- Documenter les points de terminaison (endpoints) API à l'aide d'outils tels que Swagger ou Springfox pour générer une
  documentation API interactive.

## Explication plus détaillée de la structure de packages

1. **uqam.team17.api** :
    - Il s'agit du package racine de votre application, généralement nommé d'après votre projet ou votre organisation.
    - Il est courant d'utiliser le placeholder "demo" pour des projets d'échantillon ou des prototypes.

2. **controller** :
    - Ce package contient des classes chargées de gérer les requêtes HTTP entrantes et de générer les réponses HTTP.
    - Les contrôleurs définissent des points de terminaison RESTful et les associent à des méthodes qui traitent les
      requêtes.
    - Chaque classe de contrôleur est annotée avec `@RestController` ou `@Controller` pour indiquer son rôle dans le
      traitement des requêtes HTTP.

3. **service** :
    - Les classes de service encapsulent la logique métier/affaire de l'application.
    - Elles traitent les opérations métier/affaire complexes et interagissent avec les référentiels pour exécuter des
      opérations
      CRUD.
    - Les services sont généralement appelés par les méthodes de contrôleur pour traiter les requêtes et mettre en œuvre
      la logique spécifique de l'application.

4. **repository** :
    - Ce package contient des classes chargées d'interagir avec la base de données.
    - Les classes de référentiel utilisent des frameworks ORM comme Spring Data JPA ou Hibernate pour exécuter des
      opérations CRUD sur les entités.
    - Elles fournissent des méthodes pour interroger et manipuler les données dans la base de données.

5. **model** :
    - Les classes de modèle représentent des entités dans le domaine d'application de l'application.
    - Elles encapsulent à la fois les données et le comportement associés à des entités métier spécifiques.
    - Les modèles sont couramment annotés avec des annotations JPA telles que `@Entity`, `@Id`, `@GeneratedValue`, etc.,
      pour établir des mappings avec les tables de la base de données (Springboot).

6. **exception** :
    - Ce package héberge des classes dédiées à la gestion des exceptions au sein de l'application.
    - Il est conseillé de définir des classes d'exception personnalisées adaptées à différents types d'erreurs
      potentielles.
    - La logique de gestion des exceptions peut être centralisée dans un gestionnaire d'exceptions global ou mise en
      œuvre dans des contrôleurs ou services individuels.

7. **config** :
    - Les classes de configuration définissent les paramètres et configurations de l'application.
    - Elles facilitent la configuration des beans Spring, des connexions de base de données, des paramètres de sécurité,
      etc.
    - Les classes de configuration comportent souvent des annotations telles que `@Configuration` ou des annotations
      connexes.

8. **util** :
    - Les classes utilitaires contiennent du code réutilisable et des méthodes d'assistance utilisées dans l'ensemble de
      l'application.
    - Elles fournissent des fonctionnalités communes qui ne sont pas spécifiques à un domaine ou une couche
      particulière.
    - Les classes utilitaires sont généralement sans état et composées de méthodes statiques.

9. **security** :
    - Ce package contient des classes liées à la configuration et aux fonctionnalités de sécurité.
    - Si l'application nécessite une authentification et une autorisation, il faut définir des composants liés à
      la sécurité tels que des fournisseurs d'authentification, des filtres et des règles de contrôle d'accès ici.

> Chaque package dans la structure ci-haut remplit un rôle spécifique, facilitant ainsi l'organisation du code de
> manière modulaire et maintenable.