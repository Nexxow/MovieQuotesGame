package modele.bd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import modele.classes.Compte;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

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
        Document doc = new Document("token", compte.getToken()).append("pseudo", compte.getPseudo()).append("mdp", compte.getMdp()).append("mail", compte.getMail()).append("lienAvatar",
                compte.getLienAvatar()).append("genrePrefere", compte.getGenrePrefere());

        return doc;
    }

    public static Compte mongoToJava(Document doc){

        // Initialisation d'un objet
        Compte compte = new Compte(doc.getString("token"), doc.getString("mail"), doc.getString("genrePrefere"), doc.getString("mdp"), doc.getString("lienAvatar"));
        return compte;
    }
}
