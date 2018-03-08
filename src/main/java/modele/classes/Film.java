package modele.classes;

import java.util.Date;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class Film {
    private String titre;
    private Date annee;
    private String resume;
    private String imageLien;
    private int votesJour;
    private int id;

    private static int cpt = 0;


    public Film(String titre, Date annee, String resume, String imageLien) {
        this.votesJour = 0;
        this.titre = titre;
        this.annee = annee;
        this.resume = resume;
        this.imageLien = imageLien;
        this.id = cpt;
        cpt ++;
    }

    // Constructeur lors de la récupération dans mongoDB
    public Film(int id, String titre, Date annee, String resume, String imageLien) {
        this.votesJour = 0;
        this.titre = titre;
        this.annee = annee;
        this.resume = resume;
        this.imageLien = imageLien;
        this.id = id;
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

    public String getImageLien() {
        return imageLien;
    }

    public void setImageLien(String imageLien) {
        this.imageLien = imageLien;
    }

    public int getVotesJour() {
        return votesJour;
    }

    public void setVotesJour(int votesJour) {
        this.votesJour = votesJour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
