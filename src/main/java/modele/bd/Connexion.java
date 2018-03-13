package modele.bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import modele.classes.Citation;
import modele.classes.Compte;
import modele.classes.Film;
import org.bson.Document;


import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by E155781C on 27/02/18.
 */

/**
 * Classe qui permet de créer le lien entre la base de données et l'application
 */
public class Connexion {
    private MongoDatabase database;

    /**
     * Constructeur de la classe
     */
    public Connexion(){

        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        this.database = mongoClient.getDatabase("movieQuotesGame");
    }

    /**
     * méthode permettant d'ajouter une citation à la base de données
     * @param citation la citation a mettre dans la BD
     */
    public void ajoutCitationBD(Citation citation) {

        MongoCollection<Document> collection = this.database.getCollection("citations");

        Document doc = javaToMongo(citation);

        collection.insertOne(doc);
    }

    /**
     * Méthode permettant de mettre à jour une citation se trouvant dans la base de données
     * @param citation la citation à modifier
     */
    public void majCitationBD(Citation citation){

        MongoCollection<Document> collection = this.database.getCollection("citations");

        // On remplace par la nouvelle valeur
        collection.replaceOne(Filters.eq("citation", citation.getQuote()), javaToMongo(citation));
    }

    /**
     * Méthode permettant de récupérer une citation depuis la base de données via sa date
     * @param date la date lorsque la citation était la citation du jour
     * @return la citation correspondante
     */
    public Citation getCitationBD(Date date) {
        MongoCollection<Document> collection = this.database.getCollection("citations");

        // Recherche dans la collection la citation avec la bonne date
        Document doc = collection.find(eq("date", date)).first();

        return mongoToJavaCitation(doc);
    }

    /**
     * Méthode permettant de récupérer toutes les citations se trouvant dans la base de données
     * @return un tableau comprenant toutes les citations
     */
    public ArrayList<Citation> getCitationsBD(){

        ArrayList<Citation> citations = new ArrayList<>();

        MongoCollection<Document> collection = this.database.getCollection("citations");

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()){
            citations.add(mongoToJavaCitation(cursor.next()));
        }

        cursor.close();

        return citations;

    }

    /**
     * Méthode permettant de convertir une citation en java en document pour l'intégrer dans la base de données
     * @param citation la citation à convertir
     * @return le document pour la base de données
     */
    public Document javaToMongo(Citation citation){
        String titre = null;
        if (citation.getFilm() != null){
            titre = citation.getFilm().getTitle();
        }

        Document doc = new Document("citation", citation.getQuote()).append("date", citation.getDate().getTime()).append("film", titre);

        return doc;
    }

    /**
     * Méthode permettant de convertir un document Mongo en une citation java
     * @param doc le document de la base de données
     * @return la citation en Java
     */
    public Citation mongoToJavaCitation(Document doc) {

        Film film = null;

        String titre = doc.getString("film");
        if (titre != null) {
            film = getFilmBD(titre);
        }

        // Initialisation d'un objet
        Citation citation = new Citation(doc.getString("citation"), new Date(doc.getLong("date")), film);

        return citation;
    }

    /**
     * Méthode permettant d'ajouter un compte dans la base de données
     * @param compte le compte à intégrer dans la base de données
     */
    public void ajoutCompteBD(Compte compte) {
        MongoCollection<Document> collection = this.database.getCollection("comptes");

        Document doc = javaToMongo(compte);

        collection.insertOne(doc);
    }

    // met à jour un élément dans la bd
    public void majCompteBD(Compte compte) {

        MongoCollection<Document> collection = this.database.getCollection("comptes");

        // On remplace par la nouvelle valeur
        collection.replaceOne(Filters.eq("token", compte.getToken()), javaToMongo(compte));
    }

    /**
     * Méthode permettant de récupérer un compte depuis la base de données grâce à son token
     * @param token le token du compte à récupérer
     * @return le compte provenant de la base de données
     */
    public Compte getCompteBD(String token) {

        MongoCollection<Document> collection = this.database.getCollection("comptes");

        // Recherche dans la collection le compte avec le bon token
        Document doc = collection.find(eq("token", token)).first();

        return mongoToJavaCompte(doc);
    }

    /**
     * Méthode permettant de récupérer tous les comptes de la base de données
     * @return un tableau comprenant tous les comptes
     */
    public ArrayList<Compte> getComptesBD(){
        ArrayList<Compte> comptes= new ArrayList<>();

        MongoCollection<Document> collection = this.database.getCollection("comptes");

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()){
            comptes.add(mongoToJavaCompte(cursor.next()));
        }

        cursor.close();

        return comptes;
    }

    /**
     * Méthode permettant de convertir un compte en java en document pour l'intégrer dans la base de données
     * @param compte le compte à convertir
     * @return le document à intégrer dans la base de données
     */
    public Document javaToMongo(Compte compte){
        String titre = null;
        if (compte.getFilmVote() != null){
            titre = compte.getFilmVote().getTitle();
        }
        Document doc = new Document("token", compte.getToken()).append("pseudo", compte.getPseudo()).append("mdp", compte.getMdp()).append("mail", compte.getMail()).append("lienAvatar",
                compte.getLienAvatar()).append("genrePrefere", compte.getGenrePrefere()).append("citationFav", compte.getCitationFav()).append("score", compte.getScore()).append("film", titre);

        return doc;
    }

    /**
     * Méthode permettant de convertir un document Mongo en un compte java
     * @param doc le document de la base de données
     * @return le compte en Java
     */
    public Compte mongoToJavaCompte(Document doc){

        Film film = null;

        String titre = doc.getString("film");
        if (titre != null) {
            film = getFilmBD(titre);
        }

        // Initialisation d'un objet
        Compte compte = new Compte(doc.getString("pseudo"), doc.getString("mdp"), doc.getString("mail"), doc.getString("genrePrefere"),
                doc.getString("citationFav"), doc.getString("lienAvatar"), doc.getString("token"), doc.getInteger("score"), film);
        return compte;
    }

    /**
     * Méthode permettant d'ajouter un film dans la base de données
     * @param film le film à intégrer
     */
    public void ajoutFilmBD(Film film) {
        MongoCollection<Document> collection = this.database.getCollection("films");

        Document doc = javaToMongo(film);

        collection.insertOne(doc);
    }

    public void majFilmBD(Film film){
        MongoCollection<Document> collection = this.database.getCollection("films");

        // On remplace par la nouvelle valeur
        collection.replaceOne(Filters.eq("id", film.getId()), javaToMongo(film));
    }

    /**
     * Méthode permettant de récupérer un film depuis la base données grâce à son titre
     * @param titre le titre du film
     * @return le film
     */
    public Film getFilmBD(String titre) {

        MongoCollection<Document> collection = this.database.getCollection("films");

        // Recherche dans la collection le Film avec le bon titre
        Document doc = collection.find(eq("titre", titre)).first();

        return mongoToJavaFilm(doc);
    }

    /**
     * Méthode permettant de récupérer un film depuis la base données grâce à son identifiant
     * @param id l'identifiant du film
     * @return le film
     */
    public Film getFilmBD(int id) {

        MongoCollection<Document> collection = this.database.getCollection("films");

        // Recherche dans la collection le Film avec le bon id
        Document doc = collection.find(eq("id", id)).first();

        return mongoToJavaFilm(doc);
    }

    /**
     * Méthode permettant de récupérer l'ensemble des films présents dans la base de données
     * @return un tableau comprenant l'ensemble des films
     */
    public ArrayList<Film> getFilmsBD(){
        ArrayList<Film> films= new ArrayList<>();

        MongoCollection<Document> collection = this.database.getCollection("films");

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()){
            films.add(mongoToJavaFilm(cursor.next()));
        }

        cursor.close();

        return films;
    }

    /**
     * Méthode permettant de convertir un film en java en document pour l'intégrer dans la base de données
     * @param film le film à convertir
     * @return le document à intégrer dans la base de données
     */
    public Document javaToMongo(Film film){
        Document doc = new Document("id", film.getId()).append("titre", film.getTitle()).append("date", film.getAnnee().getTime()).append("resume", film.getOverview())
                .append("imageLien", film.getPoster_path()).append("score", film.getScore());
        return doc;
    }

    /**
     * Méthode permettant de convertir un document Mongo en un film en java
     * @param doc le document de la base de données
     * @return le film en Java
     */
    public Film mongoToJavaFilm(Document doc){

        // Initialisation d'un objet
        Film film = new Film(doc.getInteger("id"), doc.getString("titre"), new Date(doc.getLong("date")), doc.getString("resume"), doc.getString("imageLien"),
                doc.getInteger("score"));
        return film;
    }
}
