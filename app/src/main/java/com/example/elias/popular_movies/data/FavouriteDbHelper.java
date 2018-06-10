package com.example.elias.popular_movies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.elias.popular_movies.data.FavouriteContract.FavMovieEntry;

public class FavouriteDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fav_movies.db";
    private static final int VERSION = 1;

    FavouriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " + FavMovieEntry.TABLE_NAME + " (" +
                FavMovieEntry._ID + " INTEGER PRIMARY KEY, " +
                FavMovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                FavMovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                FavMovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                FavMovieEntry.COLUMN_VOTE_AVERAGE + " FLOAT NOT NULL, " +
                FavMovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                FavMovieEntry.COLUMN_POSTER_URL + " TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavMovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
