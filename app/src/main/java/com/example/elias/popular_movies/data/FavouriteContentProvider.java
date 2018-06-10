package com.example.elias.popular_movies.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static com.example.elias.popular_movies.data.FavouriteContract.FavMovieEntry.TABLE_NAME;

public class FavouriteContentProvider extends ContentProvider {

    private FavouriteDbHelper mFavouriteDbHelper;

    public static final int FAV_MOVIES = 100;
    public static final int FAV_MOVIE_BY_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(FavouriteContract.AUTHORITY, FavouriteContract.PATH_FAV_MOVIES, FAV_MOVIES);
        uriMatcher.addURI(FavouriteContract.AUTHORITY, FavouriteContract.PATH_FAV_MOVIES + "/#", FAV_MOVIE_BY_ID);

        return uriMatcher;
    }




    @Override
    public boolean onCreate() {
        Context context = getContext();
        mFavouriteDbHelper = new FavouriteDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = mFavouriteDbHelper.getReadableDatabase();

        Cursor returnCursor;
        int match = sUriMatcher.match(uri);
        switch (match){
            case FAV_MOVIES:
                returnCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case FAV_MOVIE_BY_ID:
                String id = uri.getPathSegments().get(1);
                returnCursor = db.query(TABLE_NAME,
                        projection,
                        "movie_id=?",
                        new String[]{id},
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unkown uri " + uri);
        }

        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mFavouriteDbHelper.getWritableDatabase();

        Uri returnUri;
        int match = sUriMatcher.match(uri);
        switch (match){
            case FAV_MOVIES:
                long id = db.insert(TABLE_NAME, null, values);
                if(id > 0){
                    returnUri = ContentUris.withAppendedId(FavouriteContract.FavMovieEntry.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mFavouriteDbHelper.getWritableDatabase();

        int removedMovies;

        int match = sUriMatcher.match(uri);
        switch (match) {
            // recognized by the ID included in the URI path
            case FAV_MOVIE_BY_ID:
                String id = uri.getPathSegments().get(1);
                removedMovies = db.delete(TABLE_NAME, "movie_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }


        if (removedMovies != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return removedMovies;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
