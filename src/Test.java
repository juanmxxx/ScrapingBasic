import petitions.ConsultingMethods;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        ConsultingMethods.showAllInformation();
    }


    public static void showFilms() {
        try {
            List<String> films = ConsultingMethods.getTitleFilm();
            for (String film : films) {
                System.out.println(film);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showInfo() {
        try {
            List<String> info = ConsultingMethods.getInfo();
            for (String inf : info) {
                System.out.println(inf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showFilmWithInfo() {
        try {
            List<String> films = ConsultingMethods.getTitleFilm();
            List<String> info = ConsultingMethods.getInfo();

            for (int i = 0; i < films.size(); i++) {
                System.out.println(films.get(i) + " - " + info.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
