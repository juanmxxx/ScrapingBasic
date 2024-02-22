package petitions;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pojo.*;
import variables.GeneralVariables;
import variables.GettingVariables;
import variables.SaveVariables;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.*;

import static petitions.RegularMethods.writeHTMLFile;

public class ConsultingMethods {

    public static List<String> getTitleFilm() throws Exception {
        List<String> films = new ArrayList<>();
        writeHTMLFile();
        File file = new File(SaveVariables.HTMLFILENAME);
        Document document = Jsoup.parse(file, "UTF-8");
        Elements titles = document.select(GettingVariables.FILM);

        for(Element title : titles) {
            if(!title.text().equals(GeneralVariables.ACTOR))
                films.add(title.text());
        }

        return films;
    }

    public static List<String> getInfo() throws Exception {
        List<String> info = new ArrayList<>();
        writeHTMLFile();
        File file = new File(SaveVariables.HTMLFILENAME);
        Document document = Jsoup.parse(file, "UTF-8");
        Elements titles = document.select(GettingVariables.INFO);

        for(Element title : titles) {
            info.add(title.text());
        }

        return info;
    }

    public static List<Film> storeAndGetFilm() throws IOException {
        List<Film> films = new ArrayList<>();

        writeHTMLFile();
        File file = new File(SaveVariables.HTMLFILENAME);

        Document doc = Jsoup.parse(file, "UTF-8");

        Elements titleSections = doc.select("section[data-testid=find-results-section-title]");

        for (Element titleSection : titleSections) {
            Elements listItems = titleSection.select("li.ipc-metadata-list-summary-item");
            for (Element listItem : listItems) {
                String title = listItem.select("a.ipc-metadata-list-summary-item__t").text();
                String year = listItem.select("span.ipc-metadata-list-summary-item__li").first().text();
                String actors = listItem.select("span.ipc-metadata-list-summary-item__li").last().text();
                InfoFilm infoFilm = new InfoFilm(actors, Integer.parseInt(year));
                Film film = new Film(title, infoFilm);
                films.add(film);
            }
        }

        return films;
    }


    public static void showAllInformation() throws IOException {
        writeHTMLFile();
        File file = new File(SaveVariables.HTMLFILENAME);

        Document doc = Jsoup.parse(file, "UTF-8");

        Elements titleSections = doc.select("section[data-testid=find-results-section-title]");

        for (Element titleSection : titleSections) {
            Elements listItems = titleSection.select("li.ipc-metadata-list-summary-item");
            for (Element listItem : listItems) {
                String title = listItem.select("a.ipc-metadata-list-summary-item__t").text();
                String year = listItem.select("span.ipc-metadata-list-summary-item__li").first().text();
                String actors = listItem.select("span.ipc-metadata-list-summary-item__li").last().text();
                System.out.println("Title: " + title);
                System.out.println("Year: " + year);
                System.out.println("Actors: " + actors);

            }
        }



    }








}
