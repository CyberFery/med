
# Diagrammes de cas d'utilisation 
Cette section présente les diagrammes de cas d'utilisation modélisant les fonctionnalités du système du point de vue des acteurs.

## Diagramme de cas d'utilisation du dossier médical 
![](./_models/UseCase/MedicalRecordUsecase.png)

### Cas d'utilisation : Consulter dossier médical
Le médecin, le patient et le professionnel de la santé peuvent consulter le dossier médical. Chacun de ces acteurs doit s'authentifier afin d'accéder au dossier. De plus, pour consulter le dossier du patient, les utilisateurs doivent être munis de sa carte d'assurance maladie.

## Diagramme de cas d'utilisation du médecin
Lorsque le médecin accède au dossier médical du patient, il y peut apporter des modifications. Également, il peut annuler les modifications qu'il a apportées. De plus, le système sauvegarde et archive automatiquement chaque modification du dossier. 

![](./_models/UseCase/DoctorUsecase.png)

## Diagramme de cas d'utilisation du patient
Lors de la consultation du dossier médical par un patient, ce dernier peut seulement modifier ses coordonnées.

![](./_models/UseCase/PatientUsecase.png)

## Diagramme de cas d'utilisation de la RAMQ
Un dossier médical est créé pour chaque personne inscrite à la RAMQ. De plus, la RAMQ peut reconstruire un dossier médical à partir d'une date donnée ou d'une modification précise faite dans le passé.

![](./_models/UseCase/RAMQUsecase.png)


# Diagrammes de classes
Cette section présente les diagrammes de classes du système, illustrant les relations entre les différentes classes. Elle détaille également les responsabilités de chacune d'entre elles.

## Vue d'ensemble des différentes classes
![](./_models/classDiagram/SystemDiagram.png)

## Diagramme de classes des acteurs
![](./_models/classDiagram/Users.png)

### - Utilisation du patron `Polymorphisme`
Ce patron a été utilisé pour représenter les différents types d'acteurs qui intéragit avec notre système. En effet, la classe `User` hérite de la classe `Person`, les classes `Patient`, `Doctor`et `HealthProfessional` héritent de la classe `User`. 

## Diagramme de classes du registre des dossiers médicaux
![](./_models/classDiagram/MedicalRecordRegistry.png)

### - Utilisation du patron `Contrôleur`
Ce patron a été utilisé pour coordonner les messages provenant des utilisateurs. De cette façon, la classe `User` ne pourra pas accéder directement aux données de la classe `MedicalRecord`. Ainsi, cette dernière ne pourra pas y apporter de modification.

### - Respect du patron `Créateur`
Le patron `Créateur` a été respecté dans notre modèlisation. Par exemple, la responsabilité de créer les dossiers médicaux a été confié à la classe `MedicalRecordRegistry`. Car, cette dernière est composée d'une liste de `MedicalRecord`.

### - Justification des méthodes publiques du registre des dossiers médicaux
La classe `MedicalRecordRegistry` offre les services suivants : 
- Créer un dossier médical en utilisant la méthode `createMedicalRecord()`. Elle obtient cette méthode en implémentant l'interface `RecordCreator`.
- Consulter un dossier médical en utilisant la méthode `viewMedicalRecord()`
- Obtenir un dossier médical en utilisant la méthode `getMedicalRecord()`

Ces méthodes sont publiques, car elles permettent à la classe `MedicalRecordRegistry` d'acquitter ces responsabilités.  

## Diagramme de classes du dossier médical
![](./_models/classDiagram/MedicalRecord.png)

### - Utilisation du patron `Faible couplage`
Ce patron a été utilisé afin de minimiser les dépendances entre les objets et réduire
l’impact des changements. Par exemple, une modification dans la classe `MedicalVisit` n'affectera pas la classe `MedicalHistory`. 

### - Utilisation du patron `forte cohésion`
Nous avons attribué les responsabilités de sorte que la cohésion soit forte entre les classes. Par exemple, la classe `MedicalRecord` est composée des classes `MedicalHistory`et `MedicalVisit`. La classe `MedicalHistory` contient uniquement les informations relatives à un antécédent médical. De même, la classe `MedicalVisit` contient uniquement les données concernant une visite médicale. 

### - Justification des méthodes publiques du dossier médical
La classe `MedicalRecord` offre les services suivants : 
- Consulter un dossier médical en utilisant la méthode `consultMedicalRecord()`. Elle obtient cette méthode en implémentant l'interface `RecordViewer`.
- Modifier et supprimer les modifications apporter au dossier médical en utilisant les méthodes `updateMedicalRecord` et `cancelMedicalRecord()` en implémentant l'interface `RecordModifier`.

Ces méthodes sont publiques, car elles permettent à la classe `MedicalRecord` d'acquitter ces responsabilités.  