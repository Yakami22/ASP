package aeroport;

import enstabretagne.engine.InitData;

public class InitAirplane extends InitData {
	
	private final String planeNumber ;

	public InitAirplane(String name, String planeNumber) {
		super(name);
		this.planeNumber = planeNumber ;
	}
	
	public String getPlaneNumber() {
		return this.planeNumber;
	}

}