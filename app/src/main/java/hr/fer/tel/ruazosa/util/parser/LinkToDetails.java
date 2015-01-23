package hr.fer.tel.ruazosa.util.parser;

public class LinkToDetails {

	private int route_id;
	private String trip_id;
	private int direction_id;
	
	public LinkToDetails(int route_id, String trip_id, int direction_id) {
		super();
		this.route_id = route_id;
		this.trip_id = trip_id;
		this.direction_id = direction_id;
	}

	public int getRoute_id() {
		return route_id;
	}

	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public int getDirection_id() {
		return direction_id;
	}

	public void setDirection_id(int direction_id) {
		this.direction_id = direction_id;
	}
	
	
}
