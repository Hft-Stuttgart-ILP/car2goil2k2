package de.gui.c2g;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Startscreen extends Activity {
    /** Called when the activity is first created. */    

    // private Button button1;
    private Intent intent;

    private final int StadteListeResultId = 3;
    private final int OptionsScreenResultId = 1;
    private final int AnmeldescreenResultId = 0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingClass.RestoreValues();
        setContentView(R.layout.main);
        if(!SettingClass.isHometownSet()){
		      	Intent aintent = new Intent(this, StadtelisteScreen.class);
    			startActivityForResult(aintent, StadteListeResultId);
                //return true;
    	}
    }
    
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.guistartscreenmenu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
//    	OptionsScreen
        switch (item.getItemId()) {
            case R.id.item1:
            	intent = new Intent(this, OptionsScreen.class);
    			//startActivityForResult(intent, OptionsScreenResultId);
            	startActivity(intent);
                return true;
//                LoginScreen
            case R.id.item2:
            	intent = new Intent(this, Anmeldescreen.class);
    			startActivityForResult(intent, AnmeldescreenResultId);
    			break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // See which child activity is calling us back.
        switch (requestCode) {
        //login
            case AnmeldescreenResultId:
                // This is the standard resultCode that is sent back if the
                // activity crashed or didn't doesn't supply an explicit result.
                if (resultCode == RESULT_OK){
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                } 
                else {
//                	this.login = true;
                }
                break;
        //options
            case 1: 
            	if (resultCode == RESULT_OK){
            		Toast.makeText(this, "Save successfull", Toast.LENGTH_SHORT).show();
            	}
            default:
                break;
        }
    }
    
    public void myHandler(View view) {
		switch (view.getId()) {
		case R.id.MapButton:
			intent = new Intent(this, StadtelisteScreen.class);
//			intent.putExtra("Login", login);
//			intent.putExtra("UseGps", useGps);
			startActivity(intent);
			break;	
		}
    }
}