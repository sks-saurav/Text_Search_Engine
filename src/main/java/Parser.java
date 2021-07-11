import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;

public class Parser {
    public static HashSet<Movie> parseXMLFile() {
        SAXParserFactory fact = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = fact.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        try {
            assert parser != null;
            parser.parse("src/main/data/database.xml", handler);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        return  handler.getAll_movie();
    }
}
