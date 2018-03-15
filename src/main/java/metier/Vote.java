package metier;

import modele.bd.Connexion;
import modele.classes.Citation;
import modele.classes.Compte;
import modele.classes.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Ulysse Blaineau on 09/03/18
 * Classe permettant de rajouter au score, et d'avoir le score final de chaque film
 */

@Lazy(false)
@Service
public class Vote {

    private Connexion co = new Connexion();

    /**
     * Fonction permettant de rajouter un point au score du film
     * @param film le film où on augmente son score
     */
    public void addScoreFilm(Film film){
        film.setScore(film.getScore() + 1);
    }

    /**
     * Fonction permettant de récuperer le premier film du classement
     * @return le premier film
     */
    public Film getPremierFilm(){
        ArrayList<Film> films = co.getFilmsBD();

        Collections.sort(films, new Comparator<Film>() {
            @Override
            public int compare(Film o1, Film o2) {
                return o1.compareTo(o2);
            }
        });
        return films.get(0);
    }

    /**
     * Fonction permettant de réinitialiser les votes des comptes et donc le score des films
     */
    @Scheduled(cron = "0 10 12 * * ?")
    public void reinitialiserVotes(){
        ArrayList<Compte> comptes = co.getComptesBD();
        for (Compte compte : comptes){
            compte.reinitialiseVote();
            co.majCompteBD(compte.getToken(), compte);
        }

        ArrayList<Film> films = co.getFilmsBD();
        for (Film film : films){
            film.setScore(0);
            co.majFilmBD(film);
        }

    }

    /**
     * Fonction mettant en lien une citation (du jour) avec le film le plus voté
     * @param citation une citation du jour ou non
     * @return la citation à jour
     */
    public Citation lienFilmCitation(Citation citation){
        if (citation.estCitationJour()){
            citation.setFilm(getPremierFilm());
        }

        return citation;
    }

    /**
     * Fonction mettant en lien toutes les citations
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void tousLiens(){
        for (Citation citation : co.getCitationsBD()){
            co.majCitationBD(this.lienFilmCitation(citation));
        }
    }

}
