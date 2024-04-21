# Partie \#2

# Dossier Médical Centralisé
## INF5153 - Génie logiciel : Conception - HIVER 2024
### UQAM - Département Informatique - Groupe 30
#### Sous la supervision de Serge Dogny

---

# Rapport du dossier médical centralisé
Le dossier médical centralisé est un logiciel permettant aux utilisateurs du système de santé québécois d'avoir un dossier médical centralisé, peu importe le médecin ou l'établissement qu'ils fréquentent. Ce rapport modélise la conception du système.

## Présenté par :

| Nom       | Prénom  | Code Permanent 
|-----------|---------|---------------|
| Montpetit | Carl    | MONC08069000  | 
| Blemur    | Lindsay | BLEL21578506  | 
| Damas     | Johanie | DAMJ73520007  | 
| Ferrat    | Yacine  | FERY14099608  |


## Table des matières
- Patron Strategy
  - Diagramme de classes
  - Diagrammes de séquence
- Patron Factory
  - Diagramme de classes
  - Diagrammes de séquence
# Patron Strategy
Nous avons utilisé le patron `Strategy` pour permettre au médecin de télécharger le dossier du patient en format Json ou Txt. 
Ainsi, le code est évolutif et on pourrait ajouter d'autres formats à l'avenir.

## Diagramme de classe 
![](./_models/Patterns/StrategyPattern/ClassDiagram/MedicalRecordDownloader.png)

## Diagrammes de séquence
### Json Strategy
![](./_models/Patterns/StrategyPattern/SequenceDiagram/JsonDownloaderStrategy.png)
### Txt Strategy
![](./_models/Patterns/StrategyPattern/SequenceDiagram/TxtDownloaderStrategy.png)

# Patron Factory
## Problème
Les applications web doivent intégrer plusieurs méthodes d'authentification pour répondre aux besoins diversifiés des utilisateurs et des systèmes externes. L'intégration de méthodes variées comme les identifiants traditionnels et les services OAuth présente des défis en matière de gestion sécurisée et unifiée des flux d'authentification et des jetons.

## Adopter le patron Factory
Le patron Factory abstrait la création des objets (jetons dans ce cas) en isolant le code de création du code d'utilisation. Cela permet d'étendre et de modifier les types de jetons supportés sans impacter le reste du système. Il favorise l'encapsulation, la flexibilité et la maintenabilité en définissant une interface commune pour la création d'objets, facilitant ainsi l'ajout de nouveaux types de jetons ou méthodes d'authentification.

## Diagramme de classe
![](./_models/Patterns/FactoryPattern/ClassDiagram/class_diagram.png)

## Description du Diagramme de Classes

**TokenFactory et JwtTokenFactory** : Interfaces et classes qui définissent comment les jetons sont générés et validés. JwtTokenFactory crée des instances de JwtToken.  
**Token, JwtToken, OAuthToken** : Interfaces et classes pour les jetons. Chaque classe de jeton implémente les méthodes pour obtenir et valider les données du jeton.  
**Association** : JwtTokenFactory crée des JwtToken, illustrant la spécialisation de la factory pour un type spécifique de jeton.

## Diagrammes de séquence

![](./_models/Patterns/FactoryPattern/SequenceDiagram/sequence_diagram.png)

## Description des Diagrammes de Séquence

### Génération de Jetons
**Composants** : AuthController, AuthService, TokenFactory, Token  
**Flux** : Le Client demande un jeton via AuthController, qui délègue la création à AuthService. AuthService utilise TokenFactory pour générer un jeton, qui est ensuite retourné au client.

### Validation de Jetons
**Flux** : Le Client demande la validation d'un jeton. AuthController transmet la demande à AuthService, qui utilise TokenFactory pour valider le jeton. Le résultat est renvoyé au client.
