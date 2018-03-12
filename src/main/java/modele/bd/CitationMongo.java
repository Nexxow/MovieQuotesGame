package modele.bd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import modele.classes.Citation;
import modele.classes.Film;
import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static modele.bd.FilmMongo.getFilmBD;

/**
 * Created by Ulysse Blaineau on 26/02/18.
 */
public class CitationMongo {

    public static void ajoutCitationBD(Citation citation) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("citations");



        Document doc = javaToMongo(citation);

        collection.insertOne(doc);


    }

    public static void majCitationBD(Citation citation){
        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("citations");

        // On remplace par la nouvelle valeur
        collection.replaceOne(Filters.eq("citation", citation.getQuote()), javaToMongo(citation));
    }

    public static Citation getCitationBD(Date date) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("citations");

        // Recherche dans la collection le compte avec la bonne date
        Document doc = collection.find(eq("date", date)).first();

        return mongoToJava(doc);
    }

    public static ArrayList<Citation> getCitationsBD(){

        ArrayList<Citation> citations = new ArrayList<>();

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("citations");

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()){
            citations.add(mongoToJava(cursor.next()));
        }

        cursor.close();

        return citations;

    }

    public static Document javaToMongo(Citation citation){
        String titre = null;
        if (citation.getFilm() != null){
            titre = citation.getFilm().getTitle();
        }

        Document doc = new Document("citation", citation.getQuote()).append("date", citation.getDate().getTime()).append("titre", titre);

        return doc;
    }

    public static Citation mongoToJava(Document doc) {

        Film film = null;

        String titre = doc.getString("film");
        if (titre != null) {
            film = getFilmBD(titre);
        }

        // Initialisation d'un objet
        Citation citation = new Citation(doc.getString("citation"), new Date(doc.getLong("date")), film);

        return citation;
    }
}
