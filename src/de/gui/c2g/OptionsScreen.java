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
			myString = "Big"; //the value you want the position for
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
	
	public int setSpinnerSelection(String value, Spinner spinner){
		
		for (int i = 0; i < spinner.getCount(); i++) {              
            if(spinner.getItemAtPosition(i).equals(value)){
            	return i;
            }
      }

		
		return 1;
	}

	public void SaveChangesButtonOnClick(View view) {
		switch (view.getId()) {
		case R.id.SaveChangesButton:
			Spinner GPSSpinner = (Spinner) findViewById(R.id.GPSSpinner);
			boolean useGps = false;
			if(GPSSpinner.getSelectedItem().toString()
					.equalsIgnoreCase("Yes")){
				useGps = true;
			}
			SettingClass.setUseGps(useGps);
			
			
			// TODO: identify item and parse content

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
			setResult(RESULT_OK);
			SettingClass.SaveSettings(this);
			break;
		}
		this.finish();

	}
	
    @Override
    public void onStop(){
    	super.onStop();
    }
}
