package bmc.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
public class main extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    //setRequestedOrientation(savedInstanceState..SCREEN_ORIENTATION_PORTRAIT);
        
	    setContentView(new Panel(this));
	}
}
