# MovieQuotesGame

Chaque jour l’application propose une quote aléatoire. Les utilisateurs ont ensuite une liste de films, ils y choisissent lequel est le plus adapté à la quote. A la fin du jour, chaque utilisateur gagne autant de points qu’il y a eu de personne qui a mis la quote pour le même film.

##API
**GET** `/getCitationJour`

*Description* : Permet d'avoir la citation du jour.  
*Paramètres* : aucun  
*Réponse* : Citation en json  
*Exemple de réponse* :  
```json
{
    film: null,
    citation: "la citation",
    date: "2018-03-13"
}
```

**POST** `/newUser`

*Description* : Permet de créer un nouvel utilisateur.  
*Paramètres* : Objet json  
*Exemple de paramètres* :  
```json
{
  "pseudo": "pseudo",
  "mail" : "mail",
  "genrePrefere" : "genre",
  "mdp" : "mdp",
  "lienAvatar" : "lien"
}

```
*Réponse* : Compte en json  
*Exemple de réponse* :   
```json
{
"score": 0,
"pseudo": "pseudo",
"mail": "mail",
"genrePrefere": "genre",
"mdp": "mdp",
"lienAvatar": "lien",
"citationFav": null,
"filmVote": null,
"token": "[B@7a2b6e97"
}
```
**GET** `/getFilms`

*Description* : Permet de récupérer tous les films pouvant être votés.  
*Paramètres* : aucun  
*Réponse* : Une Liste de film en json  
*Exemple de réponse* :  
```json
[
    {
        id: 269149,
        score: 0,
        titre: "Zootopia",
        lien_image: "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg",
        date: "2016-02-11",
        resume: "Determined to prove herself, Officer Judy Hopps, the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means partnering with scam-artist fox Nick Wilde to solve the mystery."
    },
    {
        id: 399055,
        score: 0,
        titre: "The Shape of Water",
        lien_image: "https://image.tmdb.org/t/p/w600_and_h900_bestv2/k4FwHlMhuRR5BISY2Gm2QZHlH5Q.jpg",
        date: "2017-12-01",
        resume: "An other-worldly story, set against the backdrop of Cold War era America circa 1962, where a mute janitor working at a lab falls in love with an amphibious man being held captive there and devises a plan to help him escape."
    }
]
```
**GET** `/voteFilm`

*Description* : Permet de voter pour un film avec un compte.  
*Paramètres* : String token, int id  
*Example de paramètres* :   
`?token=[B@7a2b6e97&id=269149`  
*Réponse* : Un compte en json  
*Exemple de réponse* :  
```json
{
    score: 0,
    pseudo: "pseudo",
    mail: "mail",
    genrePrefere: "genre",
    mdp: "mdp",
    lienAvatar: "lien",
    citationFav: null,
    filmVote: {
        id: 269149,
        score: 1,
        titre: "Zootopia",
        lien_image: "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg",
        date: "2016-02-11",
        resume: "Determined to prove herself, Officer Judy Hopps, the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means partnering with scam-artist fox Nick Wilde to solve the mystery."
    },
    token: "[B@7a2b6e97"
}
```
**GET** `/getCitationJour`

*Description* : Permet de récupérer le classement des comptes par score.  
*Paramètres* : aucun  
*Réponse* : Liste de compte classé en Json   
*Exemple de réponse* :  
```json
[
    {
        score: 0,
        pseudo: "pseudo",
        mail: "mail",
        genrePrefere: "genre",
        mdp: "mdp",
        lienAvatar: "lien",
        citationFav: null,
        filmVote: {
            id: 269149,
            score: 1,
            titre: "Zootopia",
            lien_image: "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg",
            date: "2016-02-11",
            resume: "Determined to prove herself, Officer Judy Hopps, the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means partnering with scam-artist fox Nick Wilde to solve the mystery."
        },
        token: "[B@7a2b6e97"
    }
]
```