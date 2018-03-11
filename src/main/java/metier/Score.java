package metier;

import modele.classes.Compte;
import modele.classes.Film;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static metier.Vote.getPremierFilm;
import static modele.bd.CompteMongo.getComptesBD;

public class Score {

    public static ArrayList<Compte> getClassement(){
            ArrayList<Compte> comptes = getComptesBD();

            Collections.sort(comptes, new Comparator<Compte>() {
                @Override
                public int compare(Compte o1, Compte o2) {
                    return o1.compareTo(o2);
                }
            });
            return comptes;
    }

    public void compterScore(){
        Film prFilm = getPremierFilm();
        ArrayList<Compte> comptes = getComptesBD();
        for (Compte compte : comptes){
            if (compte.getFilmVote() == prFilm){
                compte.setScore(compte.getScore() + 1);
            }
        }
    }

}
