package de.c2g.oauth;




import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.hft.il.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
    /** Called when the activity is first created. */

    
    public final static String  C2G_BASE          = "http://www.car2go.com/api/v2.1/";
    public final static String  C2G_ACCOUNTS      = "accounts";
    public final static String  C2G_BOOKINGS      = "bookings";
    public final static String  C2G_BOOKING       = "booking";
    private final static String C2G_CONSUMERKEY   = "AdrianMarsch";
    private final static String C2G_SHAREDSECRET  = "f9WOr3fkr8BREt6l";

    private static OAuthService service;
    private static Token        requestToken;
    private static Token        accessToken;
    private static boolean 	 	accountStatus;
   
    TextView text ;
    String url = null;
    WebView web;
	EditText pinEdit;
	Toast toast;
    
	
	
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anmeldung);
       
        pinEdit = (EditText) findViewById(R.id.pin);
    	
        try {
			loadScreen();
		} catch (IllegalArgumentException e) {
			toast = Toast.makeText(Login.this,"Fehler beim Verbindungsauf. Überprüfen sie ihre Internetverbindung", Toast.LENGTH_SHORT);
			toast.show();
			e.printStackTrace();
			Log.d(url, "Connection is failed"+e.getMessage());
		}
        
    }
        

   
    public void action (final View view)  {
  	   
  	   
  	   switch(view.getId()){
 	    	case R.id.PinButton:
		try {
			validate();
		} catch (IllegalArgumentException e) {
			toast = Toast.makeText(Login.this,"Die eingebene Pin ist falsch. Bitte versuchen sie es erneut" , Toast.LENGTH_SHORT);
			toast.show();
			e.printStackTrace();
			Log.d(url, "Validate is failed"+e.getMessage());
			try {
				loadScreen();
			} catch (IllegalArgumentException ex ) {
				Log.d(url, "No relead"+ex.getMessage());
				e.printStackTrace();
			}
		}
 	    		
 			break;
 	        }
     } 
    
    

    public void loadScreen()throws IllegalArgumentException {
    	
    	service = new ServiceBuilder().provider(Car2GoApi.class)
        .apiKey(C2G_CONSUMERKEY).apiSecret(C2G_SHAREDSECRET)
        .scope(C2G_BASE).build();

        requestToken = service.getRequestToken();
        url = service.getAuthorizationUrl(requestToken);
        
        Log.d(url, "da......");
        
        web = (WebView) findViewById(R.id.login);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.requestFocus(View.FOCUS_DOWN); 
        web.loadUrl(url);
        web.getUrl();
    }
    

     private void validate() throws IllegalArgumentException {
        
    
    	String pin2;
        
		pin2 = pinEdit.getText().toString();
		
		accessToken = service.getAccessToken(requestToken, new Verifier(
        pin2));
     
		
		toast = Toast.makeText(Login.this,"Anmeldung erfolgreich: " + accessToken.toString(), Toast.LENGTH_SHORT);
    	toast.show();
         
    	Log.d(accessToken.toString(),"da............");
        	
    	//Implementierung startIntent --->
    	
    }
     
     
     public static String privateService(String requestUrl){
    	 
    	 OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl);

         service.signRequest(accessToken, request);
         Response response = request.send();
         
         Util.d("C2G rc=" + response.getCode());
         return response.getBody();
    	 
     }
     
     
     public static void dbWriter(Token AccessToken){
    	 
    	 // Coming soon.....
    	 
     }
     
     public static void accountHandler(){
    	 
    	 // Coming soon.....
     }












}