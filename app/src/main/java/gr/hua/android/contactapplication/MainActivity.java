package gr.hua.android.contactapplication;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBHelper helper = new DBHelper(this);

        Button insertButton = (Button) findViewById(R.id.insertButton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameET = (EditText) findViewById(R.id.nameInput);
                EditText phoneET = (EditText) findViewById(R.id.phoneInput);
                String name = nameET.getText().toString();
                String phone = phoneET.getText().toString();

                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DBHelper.KEY_NAME,name);
                values.put(DBHelper.KEY_PHONE,phone);
                db.insert(DBHelper.TABLE_NAME,null,values);
            }
        });

        Button selectButton = (Button) findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getReadableDatabase();
                String[] cols = {DBHelper.KEY_NAME};
                String selection = DBHelper.KEY_ID+"=?";
                String[] selargs ={"6"};
                Cursor c = db.query(DBHelper.TABLE_NAME,cols,selection,selargs,null,null,null);
                if (c.moveToFirst()){
                    do{
                        String name = c.getString(0);
                        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                    }while(c.moveToNext());
                }
            }
        });
    }
}





