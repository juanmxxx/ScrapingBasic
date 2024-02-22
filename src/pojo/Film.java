package pojo;

public class Film {
    private String title;
    private InfoFilm info;

    public Film(String title, InfoFilm info) {
        this.title = title;
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public InfoFilm getInfo() {
        return info;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInfo(InfoFilm info) {
        this.info = info;
    }

    public String toString() {
        return title + " - " + info;
    }
}
