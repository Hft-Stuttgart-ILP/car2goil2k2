package de.gui.c2g;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Bookings extends Activity {
	
	@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.guibookingsscreen);
}
	public void BookingsBackClickHandler(View view) {
		setResult(RESULT_OK);
		this.finish();
	}

}
