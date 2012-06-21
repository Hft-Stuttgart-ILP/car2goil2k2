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
				setResult(RESULT_OK);
				this.finish();
				break;
		}
	}
}
