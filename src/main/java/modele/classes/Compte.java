package modele.classes;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class Compte {
    private int score;
    private String pseudo;
    private String mail;
    private String genrePrefere;
    private String mdp;
    private String lienAvatar;
    private String citationFav;
    private Film filmVote;

    public Compte(String pseudo, String mail, String genrePrefere, String mdp, String lienAvatar) {
        // On initialise le score à 0
        this.score = 0;
        this.pseudo = pseudo;
        this.mail = mail;
        this.genrePrefere = genrePrefere;
        this.mdp = mdp;
        this.lienAvatar = lienAvatar;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGenrePrefere() {
        return genrePrefere;
    }

    public void setGenrePrefere(String genrePrefere) {
        this.genrePrefere = genrePrefere;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getLienAvatar() {
        return lienAvatar;
    }

    public void setLienAvatar(String lienAvatar) {
        this.lienAvatar = lienAvatar;
    }

    public String getCitationFav() {
        return citationFav;
    }

    public void setCitationFav(String citationFav) {
        this.citationFav = citationFav;
    }

    public Film getFilmVote() {
        return filmVote;
    }

    public void setFilmVote(Film filmVote) {
        this.filmVote = filmVote;
    }
}
