package de.gui.c2g;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import c2G.mobile.api.communication.Endpoint;
import c2G.mobile.api.objekts.Location;

public class StadtelisteScreen extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guistadtliste);
        Endpoint ep = new Endpoint();
        List<Location> townList = ep.getAllLocations("AdrianMarsch");
        RadioGroup rg = (RadioGroup)this.findViewById(R.id.townlistradiobuttongroup);
        for (Location location : townList) {
			RadioButton rb = new RadioButton(this);
			rb.setText(location.getLocationName());
			rb.setLayoutParams(new RadioGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			rg.addView(rb);
		}
    }
}
