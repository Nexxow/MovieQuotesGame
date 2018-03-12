package modele.bd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import modele.classes.Compte;
import modele.classes.Film;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static modele.bd.FilmMongo.getFilmBD;

/**
 * Created by Ulysse Blaineau on 26/02/18.
 */
public class CompteMongo {

    public static void ajoutCompteBD(Compte compte) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("comptes");

        Document doc = javaToMongo(compte);

        collection.insertOne(doc);

    }

    // met à jour un élément dans la bd
    public static void majCompteBD(Compte compte) {
        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("comptes");

        Document doc = javaToMongo(compte);

        // On remplace par la nouvelle valeur
        collection.replaceOne(Filters.eq("token", compte.getToken()), javaToMongo(compte));
    }

    public static Compte getCompteBD(String token) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("comptes");

        // Recherche dans la collection le compte avec le bon token
        Document doc = collection.find(eq("token", token)).first();

        return mongoToJava(doc);
    }

    public static ArrayList<Compte> getComptesBD(){
        ArrayList<Compte> comptes= new ArrayList<>();

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("comptes");

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()){
            comptes.add(mongoToJava(cursor.next()));
        }

        cursor.close();

        return comptes;
    }

    public static Document javaToMongo(Compte compte){
        String titre = null;
        if (compte.getFilmVote() != null){
            titre = compte.getFilmVote().getTitle();
        }
        Document doc = new Document("token", compte.getToken()).append("pseudo", compte.getPseudo()).append("mdp", compte.getMdp()).append("mail", compte.getMail()).append("lienAvatar",
                compte.getLienAvatar()).append("genrePrefere", compte.getGenrePrefere()).append("citationFav", compte.getCitationFav()).append("score", compte.getScore()).append("film", titre);

        return doc;
    }

    public static Compte mongoToJava(Document doc){

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

}
