package modele.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class Film {
    private final String urlImage ="https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    @JsonProperty("titre")
    private String title;

    @JsonProperty("lien_image")
    private String poster_path;

    @JsonProperty("date")
    private String release_date;

    @JsonProperty("resume")
    private String overview;

    private int id;

    private int score;

    @JsonIgnore
    private Date annee;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static int cpt = 0;


    public Film(String title, Date annee, String resume, String imageLien) {
        this.score = 0;
        this.title = title;
        this.annee = annee;
        this.overview = resume;
        this.poster_path = imageLien;
        this.id = cpt++;
    }

    // Constructeur lors de la récupération dans mongoDB
    public Film(int id, String title, Date annee, String resume, String imageLien, int score) {
        this.title = title;
        this.annee = annee;
        this.overview = resume;
        this.poster_path = imageLien;
        this.id = id;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getAnnee() {
        try {
            annee =  format.parse(release_date);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            return annee;
        }

        return annee;
    }

    public void setAnnee(Date annee) {
        this.annee = annee;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return urlImage+poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public String toString() {
        return String.format("id:%d, title:%s, image:%s, annee:%s, résumé:%s", id, title, getPoster_path(), getAnnee(), overview);
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
