package de.gui.c2g;

public class SettingClass {
	
	static boolean useGps = false;
	static sRadius searchRadius = sRadius.small;
	static String town;
	static boolean hometownSet = false;
	
	public static boolean isHometownSet() {
		return hometownSet;
	}
	public static void setHometownSet(boolean hometownSet) {
		SettingClass.hometownSet = hometownSet;
	}
	public static void setSearchRadius(sRadius searchRadius) {
		SettingClass.searchRadius = searchRadius;
	}
	static enum sRadius{ small, middle, big}
	
	public static boolean isUseGps() {
		return useGps;
	}
	public static void setUseGps(boolean useGps) {
		SettingClass.useGps = useGps;
	}
	public static sRadius getSearchRadius() {
		return searchRadius;
	}
	public static void setSearchRadius(String searchRadius) {
		if(searchRadius.equalsIgnoreCase("small")){
			SettingClass.searchRadius = sRadius.small;
		}
		else if(searchRadius.equalsIgnoreCase("middle")){
			SettingClass.searchRadius = sRadius.middle;
		}
		else if(searchRadius.equalsIgnoreCase("big")){
			SettingClass.searchRadius = sRadius.big;
		}
	}
	public static String getTown() {
		return town;
	}
	public static void setTown(String town) {
		SettingClass.town = town;
	}
	public static void RestoreValues() {
		// TODO Auto-generated method stub
		
	}
}
