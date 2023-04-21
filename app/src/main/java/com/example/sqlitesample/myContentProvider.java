package com.example.sqlitesample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class myContentProvider extends ContentProvider {
    private DBhelper mDb;
    private static final String AUTHORITY = "com.example.sqlitesample.manageContact";
    private static final String TABLE_NAME = "CONTACT_TABLE";
    private final Uri CONTENT_URI = Uri.parse("content://"+ AUTHORITY + "/" + TABLE_NAME);
    public static final int ROW_ID = 1;
    public static final int ROW = 2;
    static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static  {
        matcher.addURI(AUTHORITY, TABLE_NAME + "/#", ROW_ID);
        matcher.addURI(AUTHORITY, TABLE_NAME, ROW);
    }
    @Override
    public boolean onCreate() {
        mDb = new DBhelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);
        switch (matcher.match(uri)){
            case ROW_ID:
                queryBuilder.appendWhere(DBhelper.id + "="+ uri.getEncodedPath());
                break;
            case ROW:
                break;
        }
        Cursor qCursor;
        qCursor =  queryBuilder.query(mDb.getReadableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
        getContext().getContentResolver().notifyChange(uri, null);
        return qCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String type  = new String();
        switch (matcher.match(uri)){
            case ROW_ID:
                type = "vnd.android.cursor.item/vnd.com.example.sqlitesample.manageContact.CONTACT_TABLE";
                break;
            case ROW:
                type = "vnd.android.cursor.dir/vnd.com.example.sqlitesample.manageContact.CONTACT_TABLE";
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return type;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase sqlDB = mDb.getWritableDatabase();
        long rowId = 0;
        rowId = sqlDB.insert(TABLE_NAME,null, contentValues);
            if (rowId > 0){
                Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowId);
                getContext().getContentResolver().notifyChange(uri, null);
                return _uri;
            }
        throw new SQLiteException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase sqlDB = mDb.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);
        int row_delete = 0;
        switch (matcher.match(uri)){
            case ROW:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    row_delete = queryBuilder.delete(sqlDB, selection, selectionArgs);
                }
                break;
            case ROW_ID:
                queryBuilder.appendWhere(DBhelper.id + "=" + uri.getEncodedPath());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    row_delete = queryBuilder.delete(sqlDB,selection, selectionArgs);
                }
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return row_delete;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        SQLiteDatabase sqlDB = mDb.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);
        switch (matcher.match(uri)) {
            case ROW:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    count = queryBuilder.update(sqlDB, contentValues, selection, selectionArgs);
                }
                break;
            case ROW_ID:
                queryBuilder.appendWhere(DBhelper.id + "=" + uri.getEncodedPath());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    count = queryBuilder.update(sqlDB, contentValues, selection, selectionArgs);
                }
                break;
        }
        return count;
    }
}
