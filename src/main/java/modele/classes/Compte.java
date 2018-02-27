package modele.classes;

import modele.bd.CompteMongo;

import java.security.SecureRandom;

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
    private String token;

    // Pour la première création on génère le token
    public Compte(String pseudo, String mail, String genrePrefere, String mdp, String lienAvatar) {
        // On initialise le score à 0
        this.score = 0;
        this.pseudo = pseudo;
        this.mail = mail;
        this.genrePrefere = genrePrefere;
        this.mdp = mdp;
        this.lienAvatar = lienAvatar;
        generateToken();
    }

    // Lorsque l'on récupère dans la db
    public Compte(String pseudo, String mail, String genrePrefere, String mdp, String lienAvatar, String token, int score) {
        this.pseudo = pseudo;
        this.mail = mail;
        this.genrePrefere = genrePrefere;
        this.mdp = mdp;
        this.lienAvatar = lienAvatar;
        this.token = token;
        this.score = score;
    }

    // Appel dans CompteMongo pour ajouter à la bd
    private void ajoutCompteMongo() {
        CompteMongo.ajoutCompteBD(this);
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

    public void generateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        this.token = bytes.toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Voter pour un film
    public boolean voter(Film filmVote){
        if (this.filmVote == null){
            this.filmVote = filmVote;
            return true;
        }
        else {
            return false;
        }
    }
}
