package modele.bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import modele.classes.Citation;
import org.bson.Document;

/**
 * Created by Ulysse Blaineau on 26/02/18.
 */
public class CitationMongo {

    public void ajoutCitationBD(Citation citation) {

        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("movieQuotesGame");

        MongoCollection<Document> collection = database.getCollection("citations");

        Document doc = new Document("citation", citation.getCitation()).append("estCitationJour", citation.estCitationJour()).append("date", citation.getDate().toString());

        collection.insertOne(doc);
    }
}
