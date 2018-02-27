package modele.bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import modele.classes.Citation;
import modele.classes.Film;
import modele.classes.Film;
import org.bson.Document;

import java.util.ArrayList;

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

    public static Film getFilmBD(String titre) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("films");

        // Recherche dans la collection le Film avec le bon token
        Document doc = collection.find(eq("titre", titre)).first();

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
        Document doc = new Document("titre", film.getTitre()).append("date", film.getAnnee().toString()).append("resume", film.getResume()).append("imageLien", film.getImageLien())
                .append("votesJour", film.getVotesJour());

        return doc;
    }

    public static Film mongoToJava(Document doc){

        // Initialisation d'un objet
        Film film = new Film(doc.getString("titre"), doc.getDate("annee"), doc.getString("resume"), doc.getString("imageLien"));
        return film;
    }

}
