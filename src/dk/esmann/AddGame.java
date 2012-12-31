package dk.esmann;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class AddGame extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addgame);
        TimePicker time = (TimePicker)findViewById(R.id.playTime);
        time.setIs24HourView(true);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void add(View view) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(this);
        Game game = new Game();
        EditText rounds = (EditText)findViewById(R.id.numberOfRounds);
        EditText name = (EditText)findViewById(R.id.game_name);
        TimePicker time = (TimePicker)findViewById(R.id.playTime);
        game.setName(name.getText().toString());
        game.setNumberOfRounds(Integer.parseInt(rounds.getText().toString()));
        game.setHours(time.getCurrentHour());
        game.setMinutes(time.getCurrentMinute());
        databaseHandler.addGame(game);
        setResult(RESULT_OK);
        finish();
    }
}