package de.gui.c2g;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Anmeldescreen extends Activity{
	
	private Intent intent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guianmeldescreen);
        
    }
	
	// Handler für Button Zurück
	public void backClickHandler(View view) {
		switch (view.getId()) {
		case R.id.button1:
			setResult(RESULT_CANCELED);
			this.finish();
		}		
	}
	
	//Handler für Button Login
	public void LoginButtonClick(View view){
		switch(view.getId()){
		case R.id.LoginButton:
			setResult(RESULT_OK);
			this.finish();
		}
	}
	
}
