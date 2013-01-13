package dk.esmann;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditDeleteDialog extends Activity {
    private static final String TAG = "BOARDGAMECRAP";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_delete);
        Intent intent = getIntent();

        Button button = (Button) findViewById(R.id.editButton);
        button.setEnabled(false);

        this.setTitle(R.string.edit_delete_title);
        TextView text = (TextView) findViewById(R.id.game_title);
        Game game = intent.getParcelableExtra("game");
        text.setText(game.getName());
    }

    @SuppressWarnings("UnusedDeclaration")
    public void doEdit(View view) {
        Intent intent = getIntent();
        Game game = intent.getParcelableExtra("game");
        Log.d(TAG, "edit game: " + game.getId());
        finish();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void doDelete(View view) {
        Intent intent = getIntent();
        Game game = intent.getParcelableExtra("game");
        Log.d(TAG, "delete game: " + game.getId());
        DatabaseHandler db = DatabaseHandler.getInstance(this);
        db.deleteGame(game);
        finish();
    }
}