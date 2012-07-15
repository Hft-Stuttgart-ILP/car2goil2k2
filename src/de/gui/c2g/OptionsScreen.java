package de.gui.c2g;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class OptionsScreen extends Activity {
	Spinner GpsSpinner;
	boolean GpsSpinnerFlagSelected = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GpsSpinner = (Spinner) findViewById(R.id.GPSSpinner);
		String myString = "";
		if(SettingClass.isUseGps()){
			myString = "Yes"; //the value you want the position for
		}
		else{
			myString = "No";
		}
		

		ArrayAdapter myAdap = (ArrayAdapter) GpsSpinner.getAdapter(); //cast to an ArrayAdapter

		int spinnerPosition = myAdap.getPosition(myString);

		//set the default according to value
		GpsSpinner.setSelection(spinnerPosition);

		
		setContentView(R.layout.guioptionsscreen);
	}

	public void SaveChangesButtonOnClick(View view) {
		switch (view.getId()) {
		case R.id.SaveChangesButton:
			Spinner GPSSpinner = (Spinner) findViewById(R.id.GPSSpinner);

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
			break;
		}
		this.finish();

	}
}
