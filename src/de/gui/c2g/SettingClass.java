package de.gui.c2g;

public class SettingClass {
	
	static boolean useGps;
	static String searchRadius;
	static String town;
	static enum sRadius{ small, middle, big}
	
	public static boolean isUseGps() {
		return useGps;
	}
	public static void setUseGps(boolean useGps) {
		SettingClass.useGps = useGps;
	}
	public static String getSearchRadius() {
		return searchRadius;
	}
	public static void setSearchRadius(String searchRadius) {
		if(searchRadius.equalsIgnoreCase("small")){
			SettingClass.searchRadius = sRadius.small.toString();
		}
		else if(searchRadius.equalsIgnoreCase("middle")){
			SettingClass.searchRadius = sRadius.middle.toString();
		}
		else if(searchRadius.equalsIgnoreCase("big")){
			SettingClass.searchRadius = sRadius.big.toString();
		}
	}
	public static String getTown() {
		return town;
	}
	public static void setTown(String town) {
		SettingClass.town = town;
	}
}
