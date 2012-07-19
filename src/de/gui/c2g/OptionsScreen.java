package de.gui.c2g;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class OptionsScreen extends Activity {
	
	boolean GpsSpinnerFlagSelected = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guioptionsscreen);
		
		//Set value and Selection of GPS Spinner
		Spinner GpsSpinner = (Spinner) findViewById(R.id.GPSSpinner);
		String myString = "";
		if(SettingClass.isUseGps()){
			myString = "Yes";
		}
		else{
			myString = "No";
		}
		int spinnerPosition = setSpinnerSelection(myString, GpsSpinner);
		GpsSpinner.setSelection(spinnerPosition);
		
		//Set value and Selection of Searchradius Spinner
		
		Spinner SearchSpinner = (Spinner) findViewById(R.id.RadiusSpinner);
		myString = "";
		if(SettingClass.getSearchRadius().equals(SettingClass.searchRadius.big)){
			myString = "Big";
		}
		else if(SettingClass.getSearchRadius().equals(SettingClass.searchRadius.middle)){
			myString = "Middle";
		}
		else{
			myString = "Small";
		}
		
		spinnerPosition = setSpinnerSelection(myString, SearchSpinner);
		SearchSpinner.setSelection(spinnerPosition);
		
		
	}
	
	// Methode zum Finden der Position auf die Spinner gesetzt werden muss
	public int setSpinnerSelection(String value, Spinner spinner){
		
		for (int i = 0; i < spinner.getCount(); i++) {              
            if(spinner.getItemAtPosition(i).equals(value)){
            	return i;
            }
      }

		
		return 1;
	}

	// Handler für SaveChanges Button
	public void SaveChangesButtonOnClick(View view) {
		switch (view.getId()) {
		case R.id.SaveChangesButton:
			Spinner GPSSpinner = (Spinner) findViewById(R.id.GPSSpinner);
			boolean useGps = false;
			if(GPSSpinner.getSelectedItem().toString()
					.equalsIgnoreCase("Yes")){
				useGps = true;
			}
			// Setzen des Wertes ob Gps benutzt werden soll
			SettingClass.setUseGps(useGps);

			Spinner RadiusSpinner = (Spinner) findViewById(R.id.RadiusSpinner);
			if (RadiusSpinner.getSelectedItem().toString()
					.equalsIgnoreCase(getResources().getString(R.string.big))) {
				SettingClass.setSearchRadius(getResources().getString(
						R.string.big));

			} else if (RadiusSpinner
					.getSelectedItem()
					.toString()
					.equalsIgnoreCase(getResources().getString(R.string.middle))) {
				SettingClass.setSearchRadius(getResources().getString(
						R.string.middle));

			} else {
				SettingClass.setSearchRadius(getResources().getString(
						R.string.small));
			}
			//Einstellungen Speichern
			SettingClass.SaveSettings(this);
			// Einstellungen gespeichert
			setResult(RESULT_OK);
			break;
		}
		this.finish();

	}
	
    @Override
    public void onStop(){
    	super.onStop();
    	SettingClass.SaveSettings(this);
    }
}
