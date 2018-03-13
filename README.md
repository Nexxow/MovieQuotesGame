# MovieQuotesGame

Chaque jour l’application propose une quote aléatoire. Les utilisateurs ont ensuite une liste de films, ils y choisissent lequel est le plus adapté à la quote. A la fin du jour, chaque utilisateur gagne autant de points qu’il y a eu de personne qui a mis la quote pour le même film.

##API
**GET** `/getCitationJour`

Description :
Permet d'avoir la citation du jour.
Paramètres : aucun
Réponse : Citation en json
Exemple de réponse :
```json
{
    film: null,
    citation: "la citation",
    date: "2018-03-13"
}
```