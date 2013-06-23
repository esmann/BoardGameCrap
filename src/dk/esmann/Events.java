package dk.esmann;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 6/22/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Events extends Activity {

    private static final String PREFS_NAME = "BoardGameCrapPrefs";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventsview);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String calendarId = settings.getString("calendarId", "");
        Toast.makeText(getBaseContext(), "found calendarId: " + calendarId, Toast.LENGTH_LONG).show();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void addEvent(View view) {
        Toast.makeText(getBaseContext(), "Not supported yet", Toast.LENGTH_LONG).show();
    }


}