package gr.hua.android.contactapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by vasilhs12 on 16/12/2016.
 */
public class ContactsContentProvider extends ContentProvider {
    final DBHelper helper = new DBHelper(getContext());
    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI("gr.hua.android.contactapplication","contacts/#",1);
        uriMatcher.addURI("gr.hua.android.contactapplication","contacts",2);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();

        switch (uriMatcher.match(uri)) {
            case 1:
                selection = "_ID=?";
                selectionArgs[0] = uri.getLastPathSegment();
                break;
            case 2:
                selection = null;
                selectionArgs = null;
                sortOrder = null;
                break;
            default:
                break;
        }
        Cursor cursor = db.query(DBHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = helper.getReadableDatabase();

        switch (uriMatcher.match(uri)) {
            case 1:
                try {
                    throw new Exception("Unsopported");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        long id = db.insert(DBHelper.TABLE_NAME,null,values);
        uri = Uri.parse(uri.getScheme()+"/"+uri.getAuthority()+"/"+uri.getPath()+"/"+id);
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
