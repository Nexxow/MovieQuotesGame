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
public class Connexion {
    private MongoDatabase database;


    public Connexion(){

        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        this.database = mongoClient.getDatabase("movieQuotesGame");
    }

    public void ajoutCitationBD(Citation citation) {

        MongoCollection<Document> collection = this.database.getCollection("citations");

        Document doc = javaToMongo(citation);

        collection.insertOne(doc);
    }

    public void majCitationBD(Citation citation){

        MongoCollection<Document> collection = this.database.getCollection("citations");

        // On remplace par la nouvelle valeur
        collection.replaceOne(Filters.eq("citation", citation.getQuote()), javaToMongo(citation));
    }

    public Citation getCitationBD(Date date) {
        MongoCollection<Document> collection = this.database.getCollection("citations");

        // Recherche dans la collection le compte avec la bonne date
        Document doc = collection.find(eq("date", date)).first();

        return mongoToJavaCitation(doc);
    }

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

    public Document javaToMongo(Citation citation){
        String titre = null;
        if (citation.getFilm() != null){
            titre = citation.getFilm().getTitle();
        }

        Document doc = new Document("citation", citation.getQuote()).append("date", citation.getDate().getTime()).append("film", titre);

        return doc;
    }

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

    public Compte getCompteBD(String token) {

        MongoCollection<Document> collection = this.database.getCollection("comptes");

        // Recherche dans la collection le compte avec le bon token
        Document doc = collection.find(eq("token", token)).first();

        return mongoToJavaCompte(doc);
    }

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

    public Document javaToMongo(Compte compte){
        String titre = null;
        if (compte.getFilmVote() != null){
            titre = compte.getFilmVote().getTitle();
        }
        Document doc = new Document("token", compte.getToken()).append("pseudo", compte.getPseudo()).append("mdp", compte.getMdp()).append("mail", compte.getMail()).append("lienAvatar",
                compte.getLienAvatar()).append("genrePrefere", compte.getGenrePrefere()).append("citationFav", compte.getCitationFav()).append("score", compte.getScore()).append("film", titre);

        return doc;
    }

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

    public Film getFilmBD(String titre) {

        MongoCollection<Document> collection = this.database.getCollection("films");

        // Recherche dans la collection le Film avec le bon titre
        Document doc = collection.find(eq("titre", titre)).first();

        return mongoToJavaFilm(doc);
    }

    public Film getFilmBD(int id) {

        MongoCollection<Document> collection = this.database.getCollection("films");

        // Recherche dans la collection le Film avec le bon id
        Document doc = collection.find(eq("id", id)).first();

        return mongoToJavaFilm(doc);
    }

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

    public Document javaToMongo(Film film){
        Document doc = new Document("id", film.getId()).append("titre", film.getTitle()).append("date", film.getAnnee().getTime()).append("resume", film.getOverview())
                .append("imageLien", film.getPoster_path()).append("score", film.getScore());
        return doc;
    }

    public Film mongoToJavaFilm(Document doc){

        // Initialisation d'un objet
        Film film = new Film(doc.getInteger("id"), doc.getString("titre"), new Date(doc.getLong("date")), doc.getString("resume"), doc.getString("imageLien"),
                doc.getInteger("score"));
        return film;
    }
}
