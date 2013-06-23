package dk.esmann;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class AddGame extends Activity {
    private static final String TAG = "ADDGAME";
    private Integer gameId = -1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        intent.getParcelableExtra("game");
        TimePicker time;
        if (intent.hasExtra("game")) {
            setContentView(R.layout.editgame);
            Game game = intent.getParcelableExtra("game");
            Log.d(TAG, "editing game: " + game.toString());
            EditText rounds = (EditText)findViewById(R.id.numberOfRounds);
            rounds.setText(String.valueOf(game.getNumberOfRounds()));
            EditText name = (EditText)findViewById(R.id.game_name);
            name.setText(game.getName());
            time = (TimePicker)findViewById(R.id.playTime);
            time.setCurrentHour(game.getHours());
            time.setCurrentMinute(game.getMinutes());
            gameId = game.getId();
        } else {
            Log.d(TAG , "adding game");
            setContentView(R.layout.addgame);
            time = (TimePicker)findViewById(R.id.playTime);
            time.setIs24HourView(true);

        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public void add(View view) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(this);
        Game game = getGameInfo();
        databaseHandler.addGame(game);
        setResult(RESULT_OK);
        finish();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void save(View view) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(this);
        Game game = getGameInfo();
        if (databaseHandler.updateGame(game)) {
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_CANCELED);
        }

        finish();
    }

    private Game getGameInfo() {
        Game game = new Game();
        game.setId(gameId);
        EditText rounds = (EditText)findViewById(R.id.numberOfRounds);
        EditText name = (EditText)findViewById(R.id.game_name);
        TimePicker time = (TimePicker)findViewById(R.id.playTime);
        game.setName(name.getText().toString());
        game.setNumberOfRounds(Integer.parseInt(rounds.getText().toString()));
        game.setHours(time.getCurrentHour());
        game.setMinutes(time.getCurrentMinute());
        return game;
    }
}