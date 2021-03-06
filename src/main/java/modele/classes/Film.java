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
 * Classe définissant un film
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
        if (release_date != null) {
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

        return new String(new SimpleDateFormat("yyyy-MM-dd").format(this.annee));
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
            return -1;
        }
        else if (this.score == o.score){
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (score != film.score) return false;
        if (urlImage != null ? !urlImage.equals(film.urlImage) : film.urlImage != null) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (poster_path != null ? !poster_path.equals(film.poster_path) : film.poster_path != null) return false;
        if (release_date != null ? !release_date.equals(film.release_date) : film.release_date != null) return false;
        if (overview != null ? !overview.equals(film.overview) : film.overview != null) return false;
        return annee != null ? annee.equals(film.annee) : film.annee == null;
    }

    @Override
    public int hashCode() {
        int result = urlImage != null ? urlImage.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (poster_path != null ? poster_path.hashCode() : 0);
        result = 31 * result + (release_date != null ? release_date.hashCode() : 0);
        result = 31 * result + (overview != null ? overview.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + score;
        result = 31 * result + (annee != null ? annee.hashCode() : 0);
        return result;
    }
}
