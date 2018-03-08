package modele.bd;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import modele.classes.Compte;
import org.bson.Document;
import org.bson.conversions.Bson;

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

    // met à jour un élément dans la bd
    public static void majCompteBD(Compte compte) {
        Connexion co = new Connexion();
        MongoDatabase database = co.Connexion();

        MongoCollection<Document> collection = database.getCollection("comptes");

        Document doc = javaToMongo(compte);

        // On remplace par la nouvelle valeur
        collection.replaceOne(Filters.eq("token", compte.getToken()), javaToMongo(compte));
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
                compte.getLienAvatar()).append("genrePrefere", compte.getGenrePrefere()).append("score", compte.getScore());

        return doc;
    }

    public static Compte mongoToJava(Document doc){

        // Initialisation d'un objet
        Compte compte = new Compte(doc.getString("pseudo"), doc.getString("mail"), doc.getString("genrePrefere"), doc.getString("mdp"), doc.getString("lienAvatar"),
                doc.getString("token"), doc.getInteger("score"));
        return compte;
    }

}
