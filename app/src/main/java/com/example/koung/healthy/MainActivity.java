package com.example.koung.healthy;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String SQL_CREATE_DATABASE = "CREATE TABLE IF NOT EXISTS sleep" +
            "(date VARCHAR(255) PRIMARY KEY," +
            "sleeptime VARCHAR(255)," +
            "wakeuptime VARCHAR(255)," +
            "duration VARCHAR(255))";

    private void createDatabase() {
        SQLiteDatabase database = openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        database.execSQL(SQL_CREATE_DATABASE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDatabase();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new LoginFragment())
                    .commit()
            ;
        }
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String dateFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);

        TextView date = findViewById(R.id.weight_from_date);
        date.setText(sdf.format(calendar.getTime()));
    }
}
