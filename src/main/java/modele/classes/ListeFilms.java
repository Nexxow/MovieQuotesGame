package modele.classes;

import java.util.List;

/**
 * Created by Ulysse Blaineau on 22/02/18.
 */
public class ListeFilms {
    public List<Film> getResults() {
        return results;
    }

    public void setResults(List<Film> results) {
        this.results = results;
    }

    private List<Film> results;



    public String toString() {
        return String.format("%s", results);
    }
}
