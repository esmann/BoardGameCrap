package dk.esmann;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 6/22/13
 * Time: 9:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ListView listView = (ListView)findViewById(R.id.mainView);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = listView.getItemAtPosition(position);
                Intent intent = null;
                switch(position) {
                    case 0: // Events
                        intent = new Intent(getBaseContext(), Events.class);
                        break;
                    case 1: // Game Timers
                        intent = new Intent(getBaseContext(), GameTimers.class);
                        break;
                    case 2:  // Plays
                        break;
                    case 3: // Settings
                        intent = new Intent(getBaseContext(), Settings.class);
                        break;

                }
                if (intent != null) {
                    startActivity(intent);
                }

                Toast.makeText(getApplicationContext(), String.format("Clicked %s at position %d with id %d", item, position, id), Toast.LENGTH_LONG).show();
            }
        });
    }
}