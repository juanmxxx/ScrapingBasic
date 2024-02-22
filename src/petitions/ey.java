package petitions;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ey {
    public static void main(String[] args) throws Exception {
        String html = "your HTML content"; // replace with your HTML content
        Document doc = Jsoup.parse(html);

        // Select the section that contains the label "Titles"
        Elements titleSections = doc.select("section[data-testid=find-results-section-title]");

        for (Element titleSection : titleSections) {
            // Select the list items within the section
            Elements listItems = titleSection.select("li.ipc-metadata-list-summary-item");

            for (Element listItem : listItems) {
                // Extract the desired information
                String title = listItem.select("a.ipc-metadata-list-summary-item__t").text();
                String year = listItem.select("span.ipc-metadata-list-summary-item__li").first().text();
                String actors = listItem.select("span.ipc-metadata-list-summary-item__li").last().text();

                System.out.println("Title: " + title);
                System.out.println("Year: " + year);
                System.out.println("Actors: " + actors);
                System.out.println("-------------------");
            }
        }
    }
}