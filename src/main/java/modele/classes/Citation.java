package modele.classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class Citation {
    private String citation;
    private Date date;
    private Film film;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Citation(String citation, Date date) {
        this.citation = citation;
        this.date = date;
    }

    public Citation(String citation, Date date, Film film) {
        this.citation = citation;
        this.date = date;
        this.film = film;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean estCitationJour() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date ajd = new Date();

        String ajdStr = dateFormat.format(ajd);
        String dateStr = dateFormat.format(this.date);

        return (dateStr.equals(ajdStr));
    }
}
