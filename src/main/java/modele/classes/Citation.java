package modele.classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class Citation {
    private String quote;
    private Date date;
    private Film film;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Citation(String citation, Date date) {
        this.quote = citation;
        this.date = date;
    }

    public Citation(String citation, Date date, Film film) {
        this.quote = citation;
        this.date = date;
        this.film = film;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
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
