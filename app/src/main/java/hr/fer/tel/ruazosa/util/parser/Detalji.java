package hr.fer.tel.ruazosa.util.parser;

import java.util.List;

public class Detalji {

	private Integer line;
	private List<String> timesAndStations;
	
	public Detalji(Integer line, List<String> timesAndStations) {
		super();
		this.line = line;
		this.timesAndStations = timesAndStations;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public List<String> getTimesAndStations() {
		return timesAndStations;
	}

	public void setTimesAndStations(List<String> timesAndStations) {
		this.timesAndStations = timesAndStations;
	}
	
}
