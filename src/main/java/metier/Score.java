package metier;

import modele.bd.Connexion;
import modele.classes.Compte;
import modele.classes.Film;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Ulysse Blaineau on 09/03/18
 * Classe permettant de rajouter au score, et d'avoir le score final de chaque film
 * Classe permettant de rajouter au score, et d'avoir le score final de chaque film
 */
@Lazy(false)
@Service
public class Score {

    private Connexion co = new Connexion();
    private Vote vote = new Vote();

  /**
   * Récupère le classement des comptes
   * @return le classement des comptes
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
    @Scheduled(cron = "0 5 12 * * *")
    public void compterScore(){
        System.out.println("lancement");
        Film prFilm = vote.getPremierFilm();
        ArrayList<Compte> comptes = co.getComptesBD();
        System.out.println(prFilm.toString());

        for (Compte compte : comptes){
            if (compte.getFilmVote().equals(prFilm)){

                System.out.println("on passe dedans");
                compte.setScore(compte.getScore() + 1);
                co.majCompteBD(compte.getToken(), compte);
            }
        }
    }

}
