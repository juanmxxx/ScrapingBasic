package pojo;

import java.util.ArrayList;
import java.util.List;

public class InfoFilm {
    private String type;
    private int year;
    private List<String> actors;

    public InfoFilm(String info, int year) {
        this.type = info;
        this.year = year;
        this.actors = new ArrayList<>();
    }


    public String getType() {
        return type;
    }

    public List<String> getActors() {
        return actors;
    }

    public int getYear() {
        return year;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void addActor(String actor) {
        actors.add(actor);
    }

    public String toString() {
        return type + " - " + year + " - " + actors;
    }
}
