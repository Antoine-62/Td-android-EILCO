# Projet pokedex

## Introduction

Le projet est un pokedex répertoriant tout les pokemons existants. Idée très amusante, mais malheuresment je n'ai pas pu réaliser tout ce je voulais faire à cause d'un problème au niveau de la tab-nav (voir rubrique poblèmes rencontrés).

## Fonctionalités

* Le pokedex affiche une liste de pokemon, dans lequel l'utilisateur peut en selectionner un pour afficher des détails. Du à un problème technique rencontré, certaines générations ne sont pas accessibles.  
* Lorsqu'un pokemon est selectionné, sa fiche technique est enregistré dans la mémoire du téléphone via room.  
* Dû au même problème technique que précedemment, seul la liste des identifiants et des images de la première génération sont enregistrées dans la base de données.

## Problèmes rencontrés

* Le fameux problème technique concerne la tabnav. Plus précisément la gestion des fragments affichés grâce à la tabnav. Pour une raison inconnu, au démarage de l'application, le fragment se charge 2 fois, une fois pour la génération 1, et une autre pour la génération 2. Pour les 3 premieres rubriques de la tab, il indique que la position est égale à 0 au lieu de 0, 1 et 2. Ensuite pour l'afichage des listes de pokemons autres que la 1ére génération, alors qu'il est censé prendre des intervalles précis, il prend des intervalles qui n'ont pas lieu d'être (quand on log les intervalles calculés, ils sont correctes, pourtant derrière c'est pas la bonne liste qui est affichée). J'ai cherché des heures sans trouver la raison. Le pire c'est que l'un de mes camarades à utiliser la même méthode que moi, et pour lui ça marche. Suite  à des recherches hier soir, j'ai vu que la librairie que j'utilisais avait le statut "deprecated". Cela peut-être une raison logique. 
* Par un manque de temps, c'est du Franco-anglais

## Améliorations possibles

* Réparer ce problème de fragment lié au tabnav en utilisant une autre librairie
* Faire 100% français (vive la francophonie)
* Lors de l'affichage de détails, pouvoir afficher des statistiques
* Faire des animations

## Conclusion

Même si ce projet peut être considéré comme non terminé, je préfére garder le positif et voir que l'application rendu est "potable". Bien sûr, un peu déçu à cause du problème de la tabnav qui a pris une grosse partie du temps de réalisation. Mais je suis quand même satisfait, j'en ai tiré pas mal d'enseignements, et ça change du dataflow dans lequel je travaille en entreprise. J'ai aussi appris qu'il falllait se renseigner sur les librairies utilisées par les sites de tutos.  
Bien que j'ai aimé la matière, je ne désire pas travaillé dans ce domaine (enfin pour l'instant), je suis trop heureux dans le domaine que je travaille actuellement :) Je remercie mon professeur, monsieur Busin, pour nous avoir enseigné cette matière.
