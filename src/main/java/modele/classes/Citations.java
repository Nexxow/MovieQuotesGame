package modele.classes;

import java.util.List;

public class Citations {

    private List<Citation> quotes;

    public List<Citation> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Citation> citations) {
        this.quotes = citations;
    }


    public String toString() {
        return String.format("%s", getQuotes());
    }
}
