package modele.bd;

import com.mongodb.client.MongoDatabase;
import modele.classes.Film;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static modele.bd.FilmMongo.*;

/**
 * Created by Nicolas Bourges on 13/03/18.
 */
public class FilmMongoTest {

  Connexion co;
  MongoDatabase database;
  Film film;
  Date ajd;
  Document doc;

  @Before
  public void initialize() {
    co = new Connexion();
    database = co.Connexion();
    DateFormat dateFormat = new SimpleDateFormat("yyyy");
    ajd = new Date();
    film = new Film(42, "titre", ajd, "resume", "lienImage", 5);
    doc = new Document("id", film.getId()).append("titre", film.getTitle()).append("date", film.getAnnee().getTime()).append("resume", film.getOverview())
            .append("imageLien", film.getPoster_path()).append("score", film.getScore());
  }

  @Test
  public void TestajoutFilmBD() throws Exception {
    ajoutFilmBD(film);
    assertEquals("ajout film dans BD", true, getFilmBD(film.getTitle()));
  }

  @Test
  public void TestmajFilmBD() throws Exception {
    ajoutFilmBD(film);
    film.setId(film.getId() + 1);
    majFilmBD(film);
    assertEquals("MAJ film dans bd", film, getFilmBD(film.getTitle()));
  }

  @Test
  public void TestgetFilmBD() throws Exception {
    ajoutFilmBD(film);
    assertEquals("test récupération film dans BD", film, getFilmBD(film.getTitle()));
  }

  @Test
  public void TestgetFilmBD1() throws Exception {
    assertEquals("test récupération film dans BD", film, getFilmBD(film.getId()));
  }

  @Test
  public void TestgetFilmsBD() throws Exception {
    ArrayList<Film> films = new ArrayList<>();
    Film film2 = new Film(66, "test", ajd, "resumeTest", "lienImageTest", 0);
    films.add(film);
    films.add(film2);
    ajoutFilmBD(film);
    ajoutFilmBD(film2);
    assertEquals("Récupération liste de film", films, getFilmsBD());
  }

  @Test
  public void TestjavaToMongo() throws Exception {
    assertEquals("Test envoi d'un film java vers mongo", doc, javaToMongo(film));
  }

  @Test
  public void TestmongoToJava() throws Exception {
    assertEquals("Test récupération d'un film depuis Mongo vers java", film, mongoToJava(doc));
  }

}