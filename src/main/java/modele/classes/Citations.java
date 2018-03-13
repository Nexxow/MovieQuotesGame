package modele.classes;

import java.util.List;

/**
 * Classe permettant de lister toutes les citations
 */
public class Citations {

    private List<Citation> quotes;

    public List<Citation> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Citation> citations) {
        this.quotes = citations;
    }

    public Citation getCitation() {
        return quotes.get(0);
    }

    public String toString() {
        return String.format("%s", getQuotes());
    }
}
