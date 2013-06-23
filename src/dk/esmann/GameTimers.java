package dk.esmann;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class GameTimers extends Activity {
    private static final int ADD_GAME_REQUEST = 1;
    private static final String TAG = "BOARDGAMECRAP";
    private List<Game> games;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gametimers);
        updateListView();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void addGame(View view) {
        Intent intent = new Intent(this, AddGame.class);
        startActivityForResult(intent, ADD_GAME_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateListView();
        Log.d(TAG, "we got intent" + requestCode + ", " + resultCode + ", " + data);
    }

    private void updateListView() {
        DatabaseHandler db = DatabaseHandler.getInstance(this);
        games = db.getAllGames();
        ListView listView = (ListView)findViewById(R.id.gamesView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      onGameClick(position);
                  }
                });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "longpress detected");
                Game game = games.get(position);
                Intent intent = new Intent(getBaseContext(), EditDeleteDialog.class);
                intent.putExtra("game", game);
                startActivityForResult(intent, 0);
                return true;
            }
        });

        ArrayAdapter<Game> adapter = new ArrayAdapter<Game>(this, android.R.layout.simple_list_item_1, android.R.id.text1, games);
        listView.setAdapter(adapter);
    }

    private void onGameClick(int position) {
        Game game = games.get(position);
        Intent intent = new Intent(this, Timer.class);
        intent.putExtra("game", game);
        startActivity(intent);
    }

}
