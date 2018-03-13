package modele.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class Film implements Comparable<Film> {
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

    // a supprimé dans la version finale
    public Film(String title, Date annee, String resume, String imageLien) {
        this.score = 0;
        this.title = title;
        this.annee = annee;
        this.overview = resume;
        this.poster_path = imageLien;

        Random r = new Random();
        this.id = r.nextInt((1000 - 0) + 1) + 0;
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
        if (!release_date.isEmpty()) {
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                annee = dateFormat.parse(release_date);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
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
        // si le film à été récupéré depuis la BD
        if (poster_path.contains("http")) {
            return poster_path;
        }
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

    @Override
    public int compareTo(Film o) {
        if (this.score > o.score){
            return 1;
        }
        else {
            return -1;
        }
    }
}
