package dk.esmann;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.*;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 6/22/13
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Events extends Activity {

    private static final String PREFS_NAME = "BoardGameCrapPrefs";
    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Events._ID,                           // 0
            CalendarContract.Events.TITLE,                  // 1
            CalendarContract.Events.DTSTART,         // 2
            CalendarContract.Events.DTEND,                  // 3
            CalendarContract.Events.HAS_ATTENDEE_DATA,
            CalendarContract.Events.RRULE
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventsview);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String calendarId = settings.getString("calendarId", "");
        Toast.makeText(getBaseContext(), "found calendarId: " + calendarId, Toast.LENGTH_LONG).show();

        Cursor cursor = getEvents(calendarId);
        String[] names = cursor.getColumnNames();
        for (String name: names) {
            Log.d("BOARDGAMECRAP", "column name " + name);
        }
        Log.d("BOARDGAMECRAP", "found " + cursor.getCount() + " events");
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), android.R.layout.simple_list_item_1, getEvents(calendarId), new String[] {CalendarContract.Events.TITLE, }, new int[] {android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        final ListView listView = (ListView)findViewById(R.id.eventList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor item = (Cursor)listView.getItemAtPosition(position);

                Toast.makeText(getBaseContext(), "chose " + item.getString(1) + " which starts at " + item.getString(2), Toast.LENGTH_LONG).show();

            }
        });
    }

    @SuppressWarnings("UnusedDeclaration")
    public void addEvent(View view) {
        Toast.makeText(getBaseContext(), "Not supported yet", Toast.LENGTH_LONG).show();
    }


    private Cursor getEvents(String calendarId) {

        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Events
                .CONTENT_URI;
        String selection = "(" + CalendarContract.Events.CALENDAR_ID + " = ?)";
        String[] selectionArgs = new String[] {calendarId};
        // Submit the query and get a Cursor object back.
        return cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

    }


}