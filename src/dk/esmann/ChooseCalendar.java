package dk.esmann;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 6/22/13
 * Time: 11:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChooseCalendar extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosecalendar);

        Cursor cursor = getCalendars();
        String[] names = cursor.getColumnNames();
        for (String name: names) {
            Log.d("BOARDGAMECRAP", "column name " + name);
        }
        Log.d("BOARDGAMECRAP", "found " + cursor.getCount() + " calendars");
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), android.R.layout.simple_list_item_1, getCalendars(), new String[] {CalendarContract.Calendars.NAME}, new int[] {android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        final ListView listView = (ListView)findViewById(R.id.chooseCalendarList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Cursor item = (Cursor)listView.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.putExtra("calendarId", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private Cursor getCalendars() {
        String[] projection =
                new String[]{
                        CalendarContract.Calendars._ID,
                        CalendarContract.Calendars.NAME,
                        CalendarContract.Calendars.ACCOUNT_NAME,
                        CalendarContract.Calendars.ACCOUNT_TYPE};
        return getContentResolver().
                        query(CalendarContract.Calendars.CONTENT_URI,
                                projection,
                                CalendarContract.Calendars.VISIBLE + " = 1",
                                null,
                                CalendarContract.Calendars.NAME + " ASC");
    }
}