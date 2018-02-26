package modele.bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modele.classes.Compte;
import org.bson.Document;

/**
 * Created by Ulysse Blaineau on 26/02/18.
 */
public class CompteMongo {


    public void ajoutCompteBD(Compte compte) {

        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("movieQuotesGame");

        MongoCollection<Document> collection = database.getCollection("comptes");

        Document doc = new Document("token", compte.getToken()).append("pseudo", compte.getPseudo()).append("mdp", compte.getMdp()).append("mail", compte.getMail()).append("lienAvatar",
                compte.getLienAvatar()).append("citationFav", compte.getCitationFav()).append("genrePrefere", compte.getGenrePrefere()).append("score", compte.getScore()).append("filmVote",
                compte.getFilmVote().toString());

        collection.insertOne(doc);

    }
}
