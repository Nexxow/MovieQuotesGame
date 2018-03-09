package metier;

import modele.classes.Film;

import java.util.Map;

/**
 * Created by Ulysse Blaineau on 09/03/18
 * Classe permettant de rajouter au score, et d'avoir le score final de chaque film
 */
public class Score {

    private static Map<Film, Integer> map;

    /**
     *
     * @param film
     * @return
     */
    public static void addScoreFilm(Film film){
        // On regarde si la map contient déjà le film
        if (map.containsKey(film)) {
            map.replace(film, map.get(film) + 1);
        }
        // sinon on l'ajoute avec un vote
        else {
            map.put(film, 1);
        }
    }

    public static int getScore(Film film){
        if (map.containsKey(film)){
            return map.get(film);
        }
        else {
            return 0;
        }
    }
}
