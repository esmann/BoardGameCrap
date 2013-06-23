package dk.esmann;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "games";

    // Table name
    private static final String TABLE_GAMES = "games";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ROUNDS = "numberOfRounds";
    private static final String KEY_HOURS = "hours";
    private static final String KEY_MIN = "minutes";
    private static final DatabaseHandler instance = null;

    public static DatabaseHandler getInstance(Context context) {
        if(instance == null) {
            return new DatabaseHandler(context.getApplicationContext());
        }
        return instance;
    }

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAMES_TABLE = "CREATE TABLE " + TABLE_GAMES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ROUNDS + " INTEGER," + KEY_HOURS + " INTEGER,"
                + KEY_MIN + " INTEGER" + ")";
        db.execSQL(CREATE_GAMES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, game.getName());
        values.put(KEY_ROUNDS, game.getNumberOfRounds());
        values.put(KEY_HOURS, game.getHours());
        values.put(KEY_MIN, game.getMinutes());

        // Inserting Row
        db.insert(TABLE_GAMES, null, values);
        db.close();
    }

    Game getGame(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GAMES, new String[] { KEY_ID,
                KEY_NAME, KEY_ROUNDS, KEY_HOURS, KEY_MIN }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        Game game = new Game();
        if (cursor != null) {
            cursor.moveToFirst();
            game = new Game(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
        }
        db.close();
        return game;
    }

    public List<Game> getAllGames() {
        List<Game> gameList = new ArrayList<Game>();
        String selectQuery = "SELECT * FROM " + TABLE_GAMES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setId(Integer.parseInt(cursor.getString(0)));
                game.setName(cursor.getString(1));
                game.setNumberOfRounds(cursor.getInt(2));
                game.setHours(cursor.getInt(3));
                game.setMinutes(cursor.getInt(4));

                gameList.add(game);
            } while (cursor.moveToNext());
        }
        db.close();
        return gameList;
    }

    public Boolean updateGame(Game game) {
        if (game.getId() == -1) {
            throw new IllegalArgumentException("Cannot update game with invalid id");
        } else {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, game.getName());
            values.put(KEY_ROUNDS, game.getNumberOfRounds());
            values.put(KEY_HOURS, game.getHours());
            values.put(KEY_MIN, game.getMinutes());


            int result = db.update(TABLE_GAMES, values, KEY_ID + " = ?",
                    new String[] { String.valueOf(game.getId()) });
            db.close();
            return (result == 1);
        }
    }

    public void deleteGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAMES, KEY_ID + " = ?",
                new String[] { String.valueOf(game.getId()) });
        db.close();
    }

    public int getGamesCount() {
        String countQuery = "SELECT * FROM " + TABLE_GAMES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int result = cursor.getCount();
        cursor.close();
        db.close();
        return result;
    }
}