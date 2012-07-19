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
    // definieren der resultIds
    private final int BookingsResultId = 2;
    private final int OptionsScreenResultId = 1;
    private final int LoginScreenResultId = 0;
    
    
    //Der Startbildschirm --> Android Activity Lifecycle
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Laden der Einstellungen
        SettingClass.LoadSettings(this);
        setContentView(R.layout.main);
    	// Show Dialog on first startups
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
    
    // Erstellen des Optionsmenü
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.guistartscreenmenu, menu);
        return true;
    }
    
	// Was passiert bei auswählen eines MenüEintrages
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
//    	OptionsScreen
        switch (item.getItemId()) {
            case R.id.item1:
            	intent = new Intent(this, OptionsScreen.class);
    			startActivityForResult(intent, OptionsScreenResultId);
                return true;
//                LoginScreen
            case R.id.item2:
            	intent = new Intent(this, Anmeldescreen.class);
    			startActivityForResult(intent, LoginScreenResultId);
    			break;
            case R.id.item3:
            	intent = new Intent(this, Bookings.class);
    			startActivityForResult(intent, BookingsResultId);
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
            case LoginScreenResultId:
                if (resultCode == RESULT_OK){
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
                } 
                else {
//                	this.login = true;
                }
                break;
                //options
            case OptionsScreenResultId: 
            	if (resultCode == RESULT_OK){
            		Toast.makeText(this, "Save successfull", Toast.LENGTH_SHORT).show();
            	}
                //bookings
            case BookingsResultId: 
            	if (resultCode == RESULT_OK){
            		// Do sth
            	}
            default:
                break;
        }
    }
    
    // Klick auf Map Button
    public void myHandler(View view) {
		switch (view.getId()) {
		case R.id.MapButton:
			intent = new Intent(this, Car2Go.class);
			startActivity(intent);
			break;	
		}
    }
    
    // Klick auf Nächstes freies Fahrzeug buchen
    public void BookNextAvailableCarButtonClickHandler(View view) {
		switch (view.getId()) {
		case R.id.booknextavailablecarbutton:
			// Do nothing --> Code von Tino nicht implementiert
			break;	
		}
    }
    
    
}