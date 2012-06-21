package de.gui.c2g;

public class SettingClass {
	
	boolean useGps;
	String searchRadius;
	enum sRadius{ small, middle, big};
	
	public SettingClass(){
		searchRadius = sRadius.small.name();
		this.useGps = true;
	}
	public SettingClass(boolean GPS, String SearchRadius){
		
		if(SearchRadius.equals("Small")){
			searchRadius = sRadius.small.name();
		}
		else if(SearchRadius.equals("Middle")){
			searchRadius = sRadius.middle.name();
		}
		else if(SearchRadius.equals("Big")){
			searchRadius = sRadius.big.name();
		}
		else{
			searchRadius = sRadius.big.name();
		}
		
		this.useGps = GPS;
	}
	
}
