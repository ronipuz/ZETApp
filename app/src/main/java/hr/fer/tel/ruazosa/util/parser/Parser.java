package hr.fer.tel.ruazosa.util.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.fer.tel.ruazosa.model.Tram;

/**
 * Created by roni on 1/14/15.
 */
public class Parser {

	public Detalji parseDetalji (final String htmlSource) {
		
		Document doc = Jsoup.parse(htmlSource, "UTF-8");
		
		//linija
    	Element linijaElement = doc.getElementsByClass("pageTitle").first();
    	String[] linijaArr = linijaElement.text().split(" ");
    	Integer linija = Integer.parseInt(linijaArr[3]);
    	
    	//vremena i stanice
		Element stationList = doc.getElementsByTag("ol").first();
		Elements stationsWithTime = stationList.getElementsByTag("li");
		
		List<String> timeStations = new ArrayList<>();
		
		for(Element stat : stationsWithTime) {
			timeStations.add(stat.text());
		}
		
		Detalji detalji = new Detalji(linija, timeStations);
		return detalji;
	}
	
    public Raspored parseRaspored(final String htmlSource) {
    	
    	Document doc = Jsoup.parse(htmlSource, "UTF-8");
    	//raspored
    	Element contentRides = doc.getElementsByClass("pageContent").first();
    	//karta
    	Element coordinates = doc.getElementsByClass("leftMenu").first();
    	//linija
    	Element linijaElement = doc.getElementsByClass("pageTitle").first();
    	String[] linijaArr = linijaElement.text().split(" ");
    	Integer linija = Integer.parseInt(linijaArr[3]);
    	
        Elements tables = contentRides.select("table");
        Elements trRightDirection = tables.get(1).getElementsByTag("tr");
        
        Elements trLeftDirection = tables.get(2).getElementsByTag("tr");
        
        List<String> polazisteOdredisteLista = new ArrayList<>();
        List<LinkToDetails> rightDirectionLinkovi = new ArrayList<>();
        for(int i=1; i<trRightDirection.size(); i++) {
        	// vrijeme Polaziste Odrediste
        	String trStringData = trRightDirection.get(i).text();
        	polazisteOdredisteLista.add(trStringData);
        	
        	//Linkovi na voznje
        	Elements linkoviElements = trRightDirection.get(i).getElementsByTag("a");
        	String hrefAttr = linkoviElements.attr("href");
        	String[] hrefArr = hrefAttr.split("&");
        	
        	String[] kArr = hrefArr[1].split("="); 
        	int route_id = Integer.parseInt(kArr[1]);
        	String[] kArr2 = hrefArr[2].split("="); 
        	String trip_id = kArr2[1];
        	String[] kArr3 = hrefArr[3].split("="); 
        	int direction_id = Integer.parseInt(kArr3[1]);
        	LinkToDetails link = new LinkToDetails(route_id, trip_id, direction_id);
        	rightDirectionLinkovi.add(link);
        }
        
        List<String> odredistePolazisteLista = new ArrayList<>();
        List<LinkToDetails> leftDirectionLinkovi = new ArrayList<>();
        for(int i=1; i<trLeftDirection.size(); i++) {
        	// vrijeme Polaziste Odrediste
        	String trStringData = trLeftDirection.get(i).text();
        	odredistePolazisteLista.add(trStringData);
        	
        	//Linkovi na voznje
        	Elements linkoviElements = trLeftDirection.get(i).getElementsByTag("a");
        	String hrefAttr = linkoviElements.attr("href");
        	String[] hrefArr = hrefAttr.split("&");
        	
        	String[] kArr = hrefArr[1].split("="); 
        	int route_id = Integer.parseInt(kArr[1]);
        	String[] kArr2 = hrefArr[2].split("="); 
        	String trip_id = kArr2[1];
        	String[] kArr3 = hrefArr[3].split("="); 
        	int direction_id = Integer.parseInt(kArr3[1]);
        	LinkToDetails link = new LinkToDetails(route_id, trip_id, direction_id);
        	leftDirectionLinkovi.add(link);
        }

        //koordinate
        Element mapElement = coordinates.getElementsByTag("img").first();
        String src = mapElement.attr("src");
        String[] srcArr = src.split("\\|");
        String koord = "";
        List<Coordinates> coordinatesList = new ArrayList<>();
        for(int i=2; i<srcArr.length; i++) {
        	//koord += srcArr[i] + "\n";
        	String krdPair;
        	if(srcArr[i].contains("&")) {
        		String[] splittedArr = srcArr[i].split("&");
        		krdPair = splittedArr[0];
        	} else {
        		krdPair = srcArr[i];
        	}
        	
        	String[] krdPairArr = krdPair.split(",");
        	Double x = Double.parseDouble(krdPairArr[0]);
        	Double y = Double.parseDouble(krdPairArr[1]);
        	Coordinates coordObj = new Coordinates(x, y);
        	
        	if(!coordinatesList.contains(coordObj)) {
        		coordinatesList.add(coordObj);
        	}
        	
        }
        
        String out = "";
        for(Coordinates c : coordinatesList) {
        	out += c.toString() + "\n";
        }
        
        Raspored raspored = new Raspored(linija, polazisteOdredisteLista, odredistePolazisteLista,
        		rightDirectionLinkovi, leftDirectionLinkovi, coordinatesList);
    	
        return raspored;
    }

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
           
            Tram tramObj = new Tram(tramNumber, tramName, false);
            tramLines.add(tramObj);
        }
        
        return tramLines;
    }
}
