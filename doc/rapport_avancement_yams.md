# 📄 Rapport d'avancement – Projet Yams

## 1. 🧭 Rappel des objectifs

- Objectif 1 : Yams solo jouable sur ligne de commande
- Objectif 2 : Yams duo avec adversaire IA ou humain
- Ce rapport fait le point sur l'avancement à la fin de la première période.

---

## 2. ✅ Tâches effectuées

### Objectif 1 – Yams solo
- Analyse du code fourni :
  - cf squelette_solo.md
- Cahier des charges :
  - cf squelette_solo.md
- Programmation :
  - cf squelette_solo.md
- Livrable :
  - État du projet : le mode solo est jouable, du point de vue original il n'est pas complet mais du point de vue de l'objectif le mode solo est fini

### Objectif 2 – Yams duo
- Analyse :
  - Afin d'implémenter facilement un jeu à 2 joueurs (à tour de rôle), il est nécessaire de créer une méthode regroupant l'ensemble des actions à réaliser pour un joueur au cours d'une manche pour simplement la réutiliser pour le 2ème joueur.
- Cahier des charges et architecture envisagée :
  - Les 2 joueurs seront simplement représentés par leur `scoreSheet` respectif et bien évidemment par le `board` qui leur est attribué à chaque manche. La méthode `playerTurn` se charge de gérer la manche d'un joueur, et peut donc être appelé pour les 2 joueurs, il suffit simplement de changer les paramètres propre à chaque joueur.
- Programmation :
  - La création d'une méthode `playerTurn` permettant de gérer une manche pour un joueur. 
  - Réecriture des méthodes equals et hashCode pour les classes Combinaisons car celles-ci (permettre aux combinaisons déjà utilisées d'être bien detectés pour les 2 joueurs respectifs)
- Livrable :
  - Mode Duo pour l'objectif 2, avec une interface textuelle simple.

---

## 3. 🧩 Organisation du groupe

- Répartition des tâches :
  - Nolann : 
    - Implémentation de la possibilité de relancer plusieurs dés en même temps
    - Modification de l'affichage de la `scoreSheet` et du score total
    - Implémentation de protection du code en vérifiant les différentes entrées pour éviter les erreurs.
    - Contribution à l'implémentation de l'IA
    - Contribution à la recherche et correction des bugs
  - Vibol : 
    - Implémentation, gestion et optimisation de toutes les classes Combinaisons
    - Implémentation non exhaustive du sacrifice d'une case
    - Implémentation du mode duo (création de la méthode `playerTurn`)
    - Contribution à l'implémentation de l'IA
    - Contribution à la recherche et correction des bugs
- Outils utilisés :
  - Eclipse
  - Discord (communication)

---

## 4. ⚠️ Difficultés rencontrées

- Techniques :  
  - Comparer les différentes les combinaisons (vérifier si elles ont déjà utilisées notamment) -> Problème avec le equals
  - Structuration d'un algorithme d'IA pertinent
- Organisationnelles :  
  - Travail en appel / Partage de version

---

## 5. 🔭 Pistes d’amélioration du jeu

- Idée 1 : Optimisation de l'IA
- Idée 2 : Meilleure interface graphique
- Idée 3 : Intégrer la partie supérieur du scoreSheet (valeur des dés)
- Idée 4 : Ajouter des jokers qui permettent de faire des actions spéciale
- Idée 5 : Possibilité de faire plusieurs niveau de difficulté d'IA (ajouter une IA qui fait des actions aléatoire)

---

## 6. 🎯 Objectifs 3+ envisagés

- Propositions d’objectifs à développer (techniquement réalistes dans le temps imparti) :
  - Nouvelles combinaisons (valeur des dés)
  - IA plus avancée (plus pertimente et plus optimisée)
  - Sauvegarde des scores dans des fichiers txt

