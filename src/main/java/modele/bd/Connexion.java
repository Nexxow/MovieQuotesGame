package modele.bd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Collection;

/**
 * Created by E155781C on 27/02/18.
 */
public class Connexion {

    public MongoDatabase Connexion(){

        // Creating a Mongo client
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("movieQuotesGame");

        return database;
    }

}
