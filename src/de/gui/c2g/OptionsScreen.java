package de.gui.c2g;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class OptionsScreen extends Activity{
	Spinner spinner;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spinner = (Spinner) findViewById(R.id.GPSSpinner);

        setContentView(R.layout.guioptionsscreen);
    }
	
	public void SaveChangesButtonOnClick(View view){
		switch(view.getId()){
		case R.id.SaveChangesButton:
			Spinner GPSSpinner = (Spinner) findViewById(R.id.GPSSpinner);
			
			// TODO: identify item and parse content
			
			if(GPSSpinner.getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.yes))){
				SettingClass.setUseGps(true);
				
			}
			else{
				SettingClass.setUseGps(false);
				
			}
			
			
			// TODO: identify item and parse content
			
			Spinner RadiusSpinner = (Spinner) findViewById(R.id.RadiusSpinner);
			if(RadiusSpinner.getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.big))){
				SettingClass.setSearchRadius(getResources().getString(R.string.big));
				
			}
			else if(RadiusSpinner.getSelectedItem().toString().equalsIgnoreCase(getResources().getString(R.string.middle))){
				SettingClass.setSearchRadius(getResources().getString(R.string.middle));
				
			}
			else{
				SettingClass.setSearchRadius(getResources().getString(R.string.small));
			}
			setResult(RESULT_OK);
			break;
		}
		this.finish();
		
	}
}
