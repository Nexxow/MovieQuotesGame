package metier;

import modele.bd.Connexion;
import modele.classes.Compte;
import modele.classes.Film;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Lazy(false)
@Component
public class Score {

    private Connexion co = new Connexion();
    private Vote vote = new Vote();

    /**
     * Fonction permettant de prendre le classement entre les utilisateurs
     * @return
     */
    public ArrayList<Compte> getClassement(){
            ArrayList<Compte> comptes = co.getComptesBD();

            Collections.sort(comptes, new Comparator<Compte>() {
                @Override
                public int compare(Compte o1, Compte o2) {
                    return o1.compareTo(o2);
                }
            });
            return comptes;
    }

    /**
     * Fonction permettant de compter les scores des joueurs pour les mettre à jour
     */
    @Scheduled(cron = "06 16 * * * *")
    public void compterScore(){
        System.out.println("lancement");
        Film prFilm = vote.getPremierFilm();
        ArrayList<Compte> comptes = co.getComptesBD();
        for (Compte compte : comptes){
            if (compte.getFilmVote() == prFilm){
                compte.setScore(compte.getScore() + 1);
            }
        }
    }

}
