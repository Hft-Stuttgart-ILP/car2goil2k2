package de.gui.c2g;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import de.age.car2goil2k2.Car2Go;

public class Startscreen extends Activity {
    /** Called when the activity is first created. */    

    // private Button button1;
    private Intent intent;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingClass.LoadSettings(this);
        setContentView(R.layout.main);
        if(SettingClass.isFirstStartUp()){
        	String message = "Hello and Welcome to the Car2Il2k2 App.\n\nUnder Menu you find options to edit the settings, to login to your Car2Go Account and to view your bookings.\n\nBy clicking Map the mapview is shown.\n\nBy clicking \"Buche nächstes Fahrzeug\" you can book the next free Car.\n\nHope you enjoy.\n\nThe Car2Il2k2-Team";
        	new AlertDialog.Builder( this )
        	.setTitle( "Welcome to Car2Il2k2" )
        	.setMessage(message)
        	.setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, just close
					// the dialog box and do nothing
					SettingClass.setFirstStartUp(false);
					dialog.cancel();
				}
			})
        	.show();
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
    
    public void myHandler(View view) {
		switch (view.getId()) {
		case R.id.MapButton:
			intent = new Intent(this, Car2Go.class);
//			intent.putExtra("Login", login);
//			intent.putExtra("UseGps", useGps);
			startActivity(intent);
			break;	
		}
    }
}