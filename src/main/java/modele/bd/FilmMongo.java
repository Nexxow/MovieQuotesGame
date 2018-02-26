package modele.bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modele.classes.Compte;
import modele.classes.Film;
import org.bson.Document;

/**
 * Created by Ulysse Blaineau on 26/02/18.
 */
public class FilmMongo {

    public void ajoutFilmBD(Film film) {

        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("movieQuotesGame");

        MongoCollection<Document> collection = database.getCollection("films");

        Document doc = new Document("titre", film.getTitre()).append("date", film.getAnnee().toString()).append("resume", film.getResume()).append("imageLien", film.getImageLien())
                .append("votesJour", film.getVotesJour());

        collection.insertOne(doc);
    }
}
