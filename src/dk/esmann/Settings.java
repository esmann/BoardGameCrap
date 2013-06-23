package dk.esmann;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 6/22/13
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Settings extends Activity {

    // TODO move to globals or something similar
    private static final int PICK_CALENDAR_REQUEST = 1;
    private static final String PREFS_NAME = "BoardGameCrapPrefs";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final ListView listView = (ListView)findViewById(R.id.settingsView);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = listView.getItemAtPosition(position);
                Intent intent = null;
                switch(position) {
                    case 0: // Calendar
                        intent = new Intent(getBaseContext(), chooseCalendar.class);
                        break;
                    case 1: // BGG username
                        break;
                }
                if (intent != null) {
                    startActivityForResult(intent, PICK_CALENDAR_REQUEST);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_CALENDAR_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), String.format("received calendar id %d", data.getLongExtra("calendarId", 0)), Toast.LENGTH_LONG).show();
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("calendarId", String.valueOf(data.getLongExtra("calendarId", 0)));
                editor.commit();
            }
        }
    }

}