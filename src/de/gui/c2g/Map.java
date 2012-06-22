package de.gui.c2g;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Map extends Activity{
	
	private boolean useGps = true;
	private boolean login = false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guimapscreen);
        useGps = this.getIntent().getBooleanExtra("UseGps", true);
        login = this.getIntent().getBooleanExtra("Login", false);
        if(useGps){
        	Toast.makeText(this, "useGPS == true", Toast.LENGTH_LONG).show();
        }
        if(login){
        	Toast.makeText(this, "login == true", Toast.LENGTH_LONG).show();
        }
    }
	
}
