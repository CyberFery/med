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

