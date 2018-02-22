package modele;

import java.util.Date;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class Film {
    private String titre;
    private Date annee;
    private String resume;


    public Film(String titre, Date annee, String resume) {
        this.titre = titre;
        this.annee = annee;
        this.resume = resume;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getAnnee() {
        return annee;
    }

    public void setAnnee(Date annee) {
        this.annee = annee;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
