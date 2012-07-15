package de.gui.c2g;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.widget.Toast;

public class SettingClass {
	
	static boolean useGps = false;
	static sRadius searchRadius = sRadius.small;
	static String town;
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
	
	public static void SaveSettings(Context ctx){
		FileOutputStream fOut;
		try {
			fOut = ctx.openFileOutput("Settings.txt", Context.MODE_WORLD_WRITEABLE);
		
			OutputStreamWriter osw = new OutputStreamWriter(fOut); 
			osw.write(OutputString());
			osw.flush();
			osw.close();
			} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String OutputString() {
		String s = "";
		s += getSearchRadius() + ";";
		s += getTown() + ";";
		if(isUseGps()){
			s += "true;";
		}
		else{
			s += "false;";
		}
		return s;
	}
	
	public static void LoadSettings(Context ctx){
        String data="";
        
		try {
			File f = new File("Settings.txt");
			if(!f.exists()){
				InputStream TheFileInputStream = ctx.getResources().openRawResource(R.raw.initialsettings);
				InputStreamReader TheInputStreamReader = new InputStreamReader(TheFileInputStream);
				char[] inputBuffer = new char[TheFileInputStream.available()];
				TheInputStreamReader.read(inputBuffer);
				data = new String(inputBuffer);
				TheInputStreamReader.close();
				TheFileInputStream.close();
			}
			else{
				FileInputStream TheFileInputStream = new FileInputStream(f);
				InputStreamReader TheInputStreamReader = new InputStreamReader(TheFileInputStream);
				char[] inputBuffer = new char[TheFileInputStream.available()];
				TheInputStreamReader.read(inputBuffer);
				data = new String(inputBuffer);
				TheInputStreamReader.close();
				TheFileInputStream.close();
			}
		} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		setValues(data);
	}
	
	private static void setValues(String data) {
		
		if(data.length()>0){
			String[] values = data.split(";");
			setSearchRadius(values[0].replace(";", ""));
			setTown(values[1].replace(";", ""));
			if(values[2].replace(";", "").equals("true")){
				setUseGps(true);
				
			}
			else{
				setUseGps(false);
			}
			
		}
		
	}
}
