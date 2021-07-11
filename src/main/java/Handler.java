import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashSet;

public class Handler extends DefaultHandler {
    static HashSet<Movie> all_movie;
    private Movie curr_movie;
    private StringBuilder elementValue;

    Handler(){
        all_movie = new HashSet<>();
    }

    public HashSet<Movie> getAll_movie(){
        return all_movie;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {
        String token = qName.toLowerCase();
        switch (token) {
            case "movie":
                curr_movie = new Movie(attr.getValue("id"));
                break;
            case "genres":
                curr_movie.genres = new ArrayList<>();
                break;
            case "directors":
                curr_movie.directors = new ArrayList<>();
                break;
            case "keywords":
                curr_movie.keyWords = new ArrayList<>();
                break;
            default: elementValue = new StringBuilder();

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String token = qName.toLowerCase();
        switch (qName) {
            case "movie":
                all_movie.add(curr_movie);
                break;
            case "title":
                curr_movie.title = elementValue.toString();
                break;
            case "year":
                curr_movie.year = elementValue.toString();
                break;
            case "genre":
                curr_movie.genres.add(elementValue.toString());
                break;
            case "director":
               curr_movie.directors.add(elementValue.toString());
                break;
            case "keyword":
                curr_movie.keyWords.add(elementValue.toString());
                break;
            case "plot":
                curr_movie.plot = elementValue.toString();

        }
    }
}
