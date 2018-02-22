package modele;

import java.util.Date;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class Citation {
    private String citation;
    private Date date;

    public Citation(String citation, Date date) {
        this.citation = citation;
        this.date = date;
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
}
