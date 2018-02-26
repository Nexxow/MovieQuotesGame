package modele.bd;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modele.classes.Compte;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Ulysse Blaineau on 26/02/18.
 */
public class CompteMongo {

    public static void ajoutCompteBD(Compte compte) {

        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("movieQuotesGame");

        MongoCollection<Document> collection = database.getCollection("comptes");

        Document doc = new Document("token", compte.getToken()).append("pseudo", compte.getPseudo()).append("mdp", compte.getMdp()).append("mail", compte.getMail()).append("lienAvatar",
                compte.getLienAvatar()).append("genrePrefere", compte.getGenrePrefere());

        collection.insertOne(doc);
    }

    public static String getCompteBD(String token) {

        final String[] jsonDoc = new String[1];

        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("movieQuotesGame");

        MongoCollection<Document> collection = database.getCollection("comptes");

        // Permet de retourner en Json ce qu'on query
        Block<Document> returnJson = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                jsonDoc[0] = document.toJson();
            }
        };

        // Recherche dans la collection le compte avec le bon token
        collection.find(eq("token", token)).forEach(returnJson);

        return jsonDoc[0];
    }
}
