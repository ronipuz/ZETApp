package hr.fer.tel.ruazosa.util.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.tel.ruazosa.model.Tram;

/**
 * Created by roni on 1/14/15.
 */
public class Parser {

    //public List<Schedule> parseBusLines(final String htmlSource) {
    //    return null;
    //}

    public List<Tram> parseTramLines(final String htmlSource) {

        Document doc = Jsoup.parse(htmlSource, "UTF-8");
        Element tramList = doc.select("ul").first();
        Elements trams = tramList.select("li");

        List<Tram> tramLines = new ArrayList<>();
        for (Element tram : trams) {
        	final String REGEX = "[0-9]+";
            String tramText = tram.text();
            
            Pattern p = Pattern.compile(REGEX);
            Matcher m = p.matcher(tramText);
            
            Integer tramNumber = null;
            String tramName = null;
            
            if(m.find()) {
            	String number = tramText.substring(m.start(), m.end());
            	tramNumber = Integer.parseInt(number);
            	tramName = tramText.substring(m.end());
            }
            
            
            //String[] tramNumberName = tramText.split(" ");
            //Integer tramNumber = Integer.parseInt(tramNumberName[0]);
            Tram tramObj = new Tram(tramNumber, tramName, false);
            tramLines.add(tramObj);
        }
        
        return tramLines;
    }
}
