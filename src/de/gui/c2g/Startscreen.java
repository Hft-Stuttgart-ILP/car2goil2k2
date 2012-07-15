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
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingClass.LoadSettings(this);
        setContentView(R.layout.main);
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
    			//startActivityForResult(intent, 1);
            	startActivity(intent);
                return true;
//                LoginScreen
            case R.id.item2:
            	intent = new Intent(this, Anmeldescreen.class);
    			startActivityForResult(intent, 0);
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
            case 0:
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
    
    @Override
    public void onStop(){
    	SettingClass.SaveSettings(this);
    }
    
    public void myHandler(View view) {
		switch (view.getId()) {
		case R.id.MapButton:
			intent = new Intent(this, Map.class);
//			intent.putExtra("Login", login);
//			intent.putExtra("UseGps", useGps);
			startActivity(intent);
			break;	
		}
    }
}