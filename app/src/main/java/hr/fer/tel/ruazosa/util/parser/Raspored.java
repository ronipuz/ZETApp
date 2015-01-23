package hr.fer.tel.ruazosa.util.parser;

import java.util.List;

public class Raspored {

	private Integer linija;
	private List<String> vrijemePolazisteOdrediste;
	private List<String> vrijemeOdredistePolaziste;
	private List<LinkToDetails> rideLinksD0;
	private List<LinkToDetails> rideLinksD1;
	private List<Coordinates> coordinates;
	
	public Raspored(Integer linija, List<String> vrijemePolazisteOdrediste,
			List<String> vrijemeOdredistePolaziste, List<LinkToDetails> rideLinksD0,
			List<LinkToDetails> rideLinksD1, List<Coordinates> coordinates) {
		super();
		this.linija = linija;
		this.vrijemePolazisteOdrediste = vrijemePolazisteOdrediste;
		this.vrijemeOdredistePolaziste = vrijemeOdredistePolaziste;
		this.rideLinksD0 = rideLinksD0;
		this.rideLinksD1 = rideLinksD1;
		this.coordinates = coordinates;
	}
	public Integer getLinija() {
		return linija;
	}
	public void setLinija(Integer linija) {
		this.linija = linija;
	}
	public List<String> getVrijemePolazisteOdrediste() {
		return vrijemePolazisteOdrediste;
	}
	public void setVrijemePolazisteOdrediste(List<String> vrijemePolazisteOdrediste) {
		this.vrijemePolazisteOdrediste = vrijemePolazisteOdrediste;
	}
	public List<String> getVrijemeOdredistePolaziste() {
		return vrijemeOdredistePolaziste;
	}
	public void setVrijemeOdredistePolaziste(List<String> vrijemeOdredistePolaziste) {
		this.vrijemeOdredistePolaziste = vrijemeOdredistePolaziste;
	}
	public List<LinkToDetails> getRideLinksD0() {
		return rideLinksD0;
	}
	public void setRideLinksD0(List<LinkToDetails> rideLinksD0) {
		this.rideLinksD0 = rideLinksD0;
	}
	public List<LinkToDetails> getRideLinksD1() {
		return rideLinksD1;
	}
	public void setRideLinksD1(List<LinkToDetails> rideLinksD1) {
		this.rideLinksD1 = rideLinksD1;
	}
	public List<Coordinates> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Coordinates> coordinates) {
		this.coordinates = coordinates;
	}
	
	
}	
	