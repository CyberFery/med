# Partie \#1

> **Notes:** Modifier la structure comme vous voulez et surtout n'hésitez pas à ajouter vos idées, de critiquer et
> d'améliorer les choses.

## Consignes (on enlèvera ça, simplement un rappel)

- Le rapport doit contenir une page de présentation et une **table des matières**.

- Il doit également contenir le texte
  nécessaire pour **introduire la modélisation** et ce qui est présenté dans chaque section du document.

- ~~Le rapport doit être un fichier markdown nommé _partie1.md_.~~

- Le rapport et le **code (PlantUml)** des diagrammes
  doivent être disponible à la racine de votre projet dans la branche principale (_main_ dans notre cas) avant le **17
  mars 2024 à 23h55**.

## Table des matières

- [Architecture Microservices](#architecture-one)
- [Diagramme des composants](#composant-one) 
---

# Architecture Microservices
<a id="architecture-one"></a>

> TODO Si vous pouvez juste ajouter l'image du diagramme UML ici svp.

## Frontend - User interface - Application VuesJS et/ou une application Java

Tout d'abord, nous avons notre frontend développé en VueJS et/ou en Java. C'est ici que nos utilisateurs interagissent
avec notre application, bénéficiant d'une interface utilisateur conviviale et réactive.

## Backend - APIs RESTful - Applications Springboot

### API Gateway

Ensuite, nous avons notre API Gateway, qui est le point d'entrée unique pour tous nos clients. Elle gère les requêtes
clients**, **assure la sécurité avec le <u>contrôle d'accès</u>, et simplifie le traitement côté client en déplaçant la
logique d'orchestration du client vers le Gateway. Elle reçoit la requête client et effectue des actions telles que la *
*vérification des autorisations**, le **routage** de la requête vers le bon microservice, ou l'agrégation de données à
partir de plusieurs microservices si nécessaire.

**Exemple:** Si la requête concerne des informations utilisateur, elle pourrait être dirigée vers un microservice
d'authentification.

Enfin, l'API Gateway reçoit la réponse du microservice et la renvoie au client (frontend), qui affiche ensuite les
résultats à l'utilisateur.

### Microservices - SQLite3

Nous avons quatre microservices indépendants, chacun ayant sa propre base de données SQLite3 pour une isolation des
données et une scalabilité optimale. Le microservice reçoit la requête, **effectue le traitement nécessaire**, *
*interagit avec sa base de données** ou **d'autres microservices si nécessaire**, et prépare la réponse. Une fois que le
microservice a terminé le traitement, il renvoie la réponse à l'API Gateway.

#### API Medical Record Service

Il gère les dossiers médicaux des patients.

#### API Modifications Archive Service

Il gère l'historique des modifications apportées aux dossiers médicaux.

#### API Ramq Service

Il gère les reconstructions des dossiers médicaux et la création de nouveaux dossiers.

#### API Authentication Service

Enfin, notre Authentication Service assure la sécurité et la gestion des identités des utilisateurs.

### Explication des microservices

Chaque microservice expose une **API RESTful** avec ses propres **endpoints** pour des fonctionnalités spécifiques. Ils
communiquent entre eux de manière **asynchrone**, ce qui garantit la **scalabilité** et la **robustesse** de notre
système.

## Résumé

En résumé, notre architecture de microservices offre une **scalabilité horizontale**, une **isolation des données** et
une **flexibilité** pour <u>développer</u>, <u>déployer</u> et <u>maintenir</u> nos services de manière efficace. Ceci
implique aussi d`Ajouter plus d'unités de calcul ou de stockage en parallèle afin de répondre à une demande croissante
sans affecter négativement les performances de notre système.

Grâce à cette séparation des responsabilités, notre architecture offre une **modularité** et une **flexibilité**
accrues, permettant à chaque microservice de se concentrer sur des **tâches spécifiques** et de <u>garantir un
fonctionnement optimal</u> de l'ensemble de notre système.


# Diagramme des composants
<a id="composant-one"></a>

Les diagrammes de composants sont un type de vue statique qui représente la structure du système. Un diagramme de 
composant décrit le système modélisé sous forme de composants réutilisables et met en évidence leurs relations de 
dépendances. Un composant est une entité indépendante et exécutable qui fournit des services à d'autres composants ou 
utilise des services. 