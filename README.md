# Doctor - Centralized Medical Records

Doctor - Centralized Medical Records est un projet basé sur une architecture de microservices, conçu pour gérer les dossiers
médicaux de manière centralisée pour les médecins. Il comprend plusieurs services autonomes qui interagissent entre eux pour fournir une
solution complète de gestion des dossiers médicaux.

## Services

### Gateway

Le service Gateway agit en tant que point d'entrée pour tous les clients externes. Il gère la redirection du trafic vers
les services appropriés en fonction des requêtes reçues.

### Authentication Service

Le service Authentication Service gère l'authentification et l'autorisation des utilisateurs pour accéder aux
fonctionnalités du système.

### Medical Records Service

Le service Medical Records Service est responsable de la gestion des dossiers médicaux des patients. Il permet de créer,
mettre à jour, lire et supprimer des dossiers médicaux, ainsi que d'effectuer des recherches dans la base de données des
dossiers médicaux.

### Modifications Archive Service

Le service Modifications Archive Service enregistre toutes les modifications apportées aux dossiers médicaux dans une
archive. Cela permet de suivre l'historique des modifications pour des raisons de traçabilité et de conformité.

### Ramq Service

Le service Ramq Service est responsable de l'intégration avec le système de la Régie de l'assurance maladie du Québec (
RAMQ). Il permet de vérifier l'éligibilité des patients, de récupérer des informations sur l'assurance maladie et d'
autres fonctionnalités liées à la RAMQ.

### Eureka Server

Le service Eureka Server est un serveur de registre et de découverte des services. Il permet aux différents services de
s'enregistrer et de découvrir les autres services disponibles dans le système.

## Technologies Utilisées

### Backend

- Spring Boot
- Spring Cloud (Eureka Server, Gateway)
- Spring Security
- Hibernate
- SQLite3
- Maven
- PlantUML
- JUnit5

### Frontend

- Vue3
- Vuetify3
- HTML5
- CSS
- TypeScript

## Configuration - Backend

Chaque service dispose de son propre fichier de configuration `application.properties` et de son `pom.xml` pour définir
les propriétés spécifiques à ce service.

### Les ports utilisés en `localhost` par les services :

1. 8081: `authentication-service`
2. 8761: `eureka-server`
3. 8080: `gateway`
4. 8083: `medical-records-service`
5. 8084: `modifications-archive-service`
6. 8085: `ramq-service`

# Instructions d'Installation et de Démarrage

## Dépendances

Avant de lancer l'application, assurez-vous d'avoir installé les dépendances nécessaires listées ci-dessous. Voici les étapes pour installer chaque outil requis :

- **Maven** : Suivez les instructions sur le site officiel pour installer Maven sur votre système.
- **Node.js et npm** : Téléchargez et installez Node.js depuis leur [site officiel](https://nodejs.org/). npm est inclus avec l'installation de Node.js.
- **Yarn** : Après avoir installé npm, vous pouvez installer Yarn en utilisant la commande `npm install -g yarn`.
- **Newman** : Newman est nécessaire pour exécuter des collections Postman via la ligne de commande. Installez-le globalement avec npm en utilisant la commande `npm install -g newman`.

## Comment Exécuter l'Application

Pour démarrer l'application, suivez les étapes ci-dessous dans l'ordre spécifié :

1. **Démarrage des Microservices** :
    - Naviguez vers le dossier `backend` avec la commande `cd backend`.
    - Exécutez le script pour démarrer tous les microservices en utilisant `./runAllMicroservices.sh`. Il est important que ce soit le premier script à être exécuté.

2. **Initialisation de la Base de Données** :
    - Après avoir démarré les microservices, exécutez le script `./dbseeding.sh` pour peupler la base de données.

## Données des bases de données

- **Dossier Médical pour le patient**: Mario
   - **Numéro d'assurance maladie**: DOEM010190035

- **Docteur**: Cole Palmer
   - **Nom d'utilisateur**: cole_palmer
   - **Mot de passe**: GOLDENboot2024


3. **Configuration du Frontend** :
    - Changez de répertoire pour le frontend en exécutant `cd frontend`.
    - Installez les dépendances en utilisant `yarn install` ou `npm install`.
    - Pour démarrer le serveur de développement, utilisez `yarn dev` ou `npm run dev`.

## Accès à l'Application

- Pour accéder à l'application, ouvrez votre navigateur et allez à l'URL suivante : http://localhost:3000/. Veuillez noter que l'API Gateway n'accepte que les requêtes  provenant de cette adresse et non de http://127.0.0.1:3000/.

En suivant ces instructions, vous devriez être en mesure de démarrer l'application sans problèmes.
