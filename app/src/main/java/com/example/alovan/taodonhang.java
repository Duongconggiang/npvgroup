package com.example.alovan;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.app.DatePickerDialog.*;

public class taodonhang extends AppCompatActivity  {
    TextView edittext, tvDeliveryTime;
    final  Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taodonhang);
////        edittext = findViewById(R.id.tvDeliveryDate);
//           final Calendar myCalendar = Calendar.getInstance();
//        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                  int dayOfMonth) {
//                // TODO Auto-generated method stub
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
//            }
//
//        };
//        edittext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new DatePickerDialog(taodonhang.this, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });

        getDataTable();

    }

    private void getDataTable() {
        TextView textViewb = (TextView) findViewById(R.id.b);
        TextView textViewb1 = (TextView) findViewById(R.id.b1);

        textViewb.setText("B Text view");
        textViewb1.setText("B1 Text view");
        textViewb.setText("B Text view");
        textViewb1.setText("B1 Text view");
    }
//        private void updateLabel(){
//            String myFormat = "MM/dd/yy"; //In which you need put here
//            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//            edittext.setText(sdf.format(myCalendar.getTime()));
//    }

}
