package modele.bd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import modele.classes.Film;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Ulysse Blaineau on 26/02/18.
 */
public class FilmMongo {

    public static void ajoutFilmBD(Film film) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("films");

        Document doc = javaToMongo(film);

        collection.insertOne(doc);
    }

    public static void majFilmBD(Film film){
        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("films");

        // On remplace par la nouvelle valeur
        collection.replaceOne(Filters.eq("id", film.getId()), javaToMongo(film));
    }

    public static Film getFilmBD(String titre) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("films");

        // Recherche dans la collection le Film avec le bon titre
        Document doc = collection.find(eq("titre", titre)).first();

        return mongoToJava(doc);
    }

    public static Film getFilmBD(int id) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("films");

        // Recherche dans la collection le Film avec le bon id
        Document doc = collection.find(eq("id", id)).first();

        return mongoToJava(doc);
    }

    public static ArrayList<Film> getFilmsBD(){
        ArrayList<Film> films= new ArrayList<>();

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("films");

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()){
            films.add(mongoToJava(cursor.next()));
        }

        cursor.close();

        return films;
    }

    public static Document javaToMongo(Film film){
        Document doc = new Document("id", film.getId()).append("titre", film.getTitle()).append("date", film.getAnnee().getTime()).append("resume", film.getOverview())
                .append("imageLien", film.getPoster_path()).append("score", film.getScore());
        return doc;
    }

    public static Film mongoToJava(Document doc){

        // Initialisation d'un objet
        Film film = new Film(doc.getInteger("id"), doc.getString("titre"), new Date(doc.getLong("date")), doc.getString("resume"), doc.getString("imageLien"),
                doc.getInteger("score"));
        return film;
    }

}
