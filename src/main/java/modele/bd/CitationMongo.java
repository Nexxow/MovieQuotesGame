package modele.bd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import modele.classes.Citation;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by Ulysse Blaineau on 26/02/18.
 */
public class CitationMongo {

    public static void ajoutCitationBD(Citation citation) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("citations");

        Document doc = new Document("citation", citation.getCitation()).append("date", citation.getDate().toString());

        collection.insertOne(doc);
    }

    public static Citation getCitationBD(Date date) {

        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("citations");

        // Recherche dans la collection le compte avec le bon token
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
        Document doc = new Document("citation", citation.getCitation()).append("date", citation.getDate());

        return doc;
    }

    public static Citation mongoToJava(Document doc){

        // Initialisation d'un objet
        Citation citation = new Citation(doc.getString("citation"), doc.getDate("date"));

        return citation;
    }
}
