package modele.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 * Classe définissant une liste de films
 */
public class ListeFilms {

    private List<Film> results;

    public List<Film> getResults() {
        return results;
    }

    public void setResults(List<Film> results) {
        this.results = results;
    }

    public ArrayList<Film> getFilms() {
        ArrayList<Film> films = new ArrayList<>();
        for (Film film : results) {
            films.add(film);
        }
        return films;
    }


    public String toString() {
        return String.format("%s", results);
    }
}
