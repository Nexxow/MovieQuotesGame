package modele.bd;

import modele.classes.Film;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nicolas Bourges on 13/03/18.
 */
public class FilmMongoTest {

  Connexion co;
  Film film;
  Date ajd;
  Document doc;

  @Before
  public void initialize(){
    co = new Connexion();
    DateFormat dateFormat = new SimpleDateFormat("yyyy");
    ajd = new Date();
    film = new Film(42,"titre",ajd,"resume","lienImage",5);
     doc = new Document();
  }

  @Test
  public void TestajoutFilmBD() throws Exception {
    co.ajoutFilmBD(film);
    assertEquals("ajout film dans BD", true, co.getFilmBD(film.getTitle()));
  }

  @Test
  public void TestmajFilmBD() throws Exception {
    co.ajoutFilmBD(film);
    film.setId(film.getId()+1);
    co.majFilmBD(film);
    assertEquals("MAJ film dans bd",film, co.getFilmBD(film.getTitle()));
  }

  @Test
  public void TestgetFilmBD() throws Exception {
    co.ajoutFilmBD(film);
    assertEquals("test récupération film dans BD", film, co.getFilmBD(film.getTitle()));
  }

  @Test
  public void TestgetFilmBD1() throws Exception {
    assertEquals("test récupération film dans BD", film, co.getFilmBD(film.getId()));
  }

  @Test
  public void TestgetFilmsBD() throws Exception {
    Film film2 = new Film(66,"test",ajd,"resumeTest","lienImageTest",0);
    co.ajoutFilmBD(film);
    co.ajoutFilmBD(film2);
    assertTrue("Récupération liste de film", co.getFilmsBD()!= null);
  }

  @Test
  public void TestjavaToMongo() throws Exception {
    assertTrue("Test envoi d'un film java vers mongo", co.javaToMongo(film)!= null);
  }

  @Test
  public void TestmongoToJava() throws Exception {
    assertEquals("Test récupération d'un film depuis Mongo vers java",film, co.mongoToJavaFilm(doc));
  }

}