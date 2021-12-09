package com.example.alovan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.myapplication.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.myapplication.R.drawable.*;

public class taodonhang extends AppCompatActivity  {
    TextView edittext, tvDeliveryTime;
    final  Calendar myCalendar = Calendar.getInstance();


    private TableLayout mTableLayout;
    ProgressDialog mProgressBar;

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


        mProgressBar = new ProgressDialog(this);

        // setup the table
        mTableLayout = (TableLayout) findViewById(R.id.tableInvoices);
        mTableLayout.setBackgroundResource(R.drawable.a);

        mTableLayout.setStretchAllColumns(true);



        startLoadData();
    }

    public void startLoadData() {

        mProgressBar.setCancelable(false);
        mProgressBar.setMessage("Fetching Invoices..");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();
        new LoadDataTask().execute(0);

    }


    public void loadData() {

        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;

        int textSize = 0, smallTextSize =0, mediumTextSize = 0;

        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

        Invoices invoices = new Invoices();
        InvoiceData[] data = invoices.getInvoices();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        int rows = data.length;
        /* getSupportActionBar().setTitle("Invoices (" + String.valueOf(rows) + ")");*/
        TextView textSpacer = null;

        mTableLayout.removeAllViews();


        // -1 means heading row
        for(int i = -1; i < rows; i ++) {
            InvoiceData row = null;
            if (i > -1)
                row = data[i];
            else {
                textSpacer = new TextView(this);
                textSpacer.setText("");

            }
            // data columns
            final TextView Số_lệnh = new TextView(this);
            Số_lệnh.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            Số_lệnh.setGravity(Gravity.CENTER);


            Số_lệnh.setPadding(15, 15, 15, 15);


            if (i == -1) {
                Số_lệnh.setText("Số lệnh");

                Số_lệnh.setBackgroundColor(Color.parseColor("#f7f7f7"));
                Số_lệnh.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

            } else {

                Số_lệnh.setBackgroundColor(Color.parseColor("#ffffff"));
                Số_lệnh.setText(String.valueOf(row.Số_lệnh));
                Số_lệnh.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            final TextView Ngày_đi = new TextView(this);

            if (i == -1) {
                Ngày_đi.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Ngày_đi.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Ngày_đi.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Ngày_đi.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Ngày_đi.setGravity(Gravity.CENTER);


            Ngày_đi.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Ngày_đi.setText("Ngày đi");
                Ngày_đi.setBackgroundColor(Color.parseColor("#ffffff"));

            }else {
                Ngày_đi.setBackgroundColor(Color.parseColor("#f7f7f7"));
                Ngày_đi.setTextColor(Color.parseColor("#000000"));
                Ngày_đi.setText(String.valueOf(row.Ngày_đi));
            }


            final TextView Số_Km = new TextView(this);
            if (i == -1) {
                Số_Km.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Số_Km.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Số_Km.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Số_Km.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Số_Km.setGravity(Gravity.CENTER);

            Số_Km.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Số_Km.setText("Số Km");
                Số_Km.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                Số_Km.setBackgroundColor(Color.parseColor("#ffffff"));
                Số_Km.setTextColor(Color.parseColor("#000000"));
                Số_Km.setText(String.valueOf(row.Số_Km));
            }

            final TextView Số_Bill = new TextView(this);
            if (i == -1) {
                Số_Bill.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Số_Bill.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Số_Bill.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Số_Bill.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Số_Bill.setGravity(Gravity.CENTER);

            Số_Bill.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Số_Bill.setText("Số_Bill");
                Số_Bill.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {
                Số_Bill.setBackgroundColor(Color.parseColor("#f7f7f7"));
                Số_Bill.setTextColor(Color.parseColor("#000000"));
                Số_Bill.setText(String.valueOf(row.Số_Bill));
            }


            final TextView Số_xe = new TextView(this);
            if (i == -1) {
                Số_xe.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Số_xe.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Số_xe.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Số_xe.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Số_xe.setGravity(Gravity.CENTER);

            Số_xe.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Số_xe.setText("Số_xe");
                Số_xe.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                Số_xe.setBackgroundColor(Color.parseColor("#ffffff"));
                Số_xe.setTextColor(Color.parseColor("#000000"));
                Số_xe.setText(String.valueOf(row.Số_xe));
            }

            final TextView lái_xe = new TextView(this);
            if (i == -1) {
                lái_xe.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                lái_xe.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                lái_xe.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                lái_xe.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            lái_xe.setGravity(Gravity.CENTER);

            lái_xe.setPadding(15, 15, 15, 15);
            if (i == -1) {
                lái_xe.setText("lái_xe");
                lái_xe.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {
                lái_xe.setBackgroundColor(Color.parseColor("#f7f7f7"));

                lái_xe.setTextColor(Color.parseColor("#000000"));
                lái_xe.setText(String.valueOf(row.lái_xe));
            }



            final TextView Lương_chuyển = new TextView(this);
            if (i == -1) {
                Lương_chuyển.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Lương_chuyển.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Lương_chuyển.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Lương_chuyển.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Lương_chuyển.setGravity(Gravity.CENTER);

            Lương_chuyển.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Lương_chuyển.setText("Lương_chuyển");
                Lương_chuyển.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                Lương_chuyển.setBackgroundColor(Color.parseColor("#ffffff"));
                Lương_chuyển.setTextColor(Color.parseColor("#000000"));
                Lương_chuyển.setText(String.valueOf(row.Lương_chuyển));
            }


            final TextView Ứng_chuyển_đi = new TextView(this);
            if (i == -1) {
                Ứng_chuyển_đi.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Ứng_chuyển_đi.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Ứng_chuyển_đi.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Ứng_chuyển_đi.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Ứng_chuyển_đi.setGravity(Gravity.CENTER);

            Ứng_chuyển_đi.setPadding(5, 15, 15, 15);
            if (i == -1) {
                Ứng_chuyển_đi.setText("Ứng_chuyển_đi");
                Ứng_chuyển_đi.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {

                Ứng_chuyển_đi.setBackgroundColor(Color.parseColor("#f7f7f7"));
                Ứng_chuyển_đi.setTextColor(Color.parseColor("#000000"));
                Ứng_chuyển_đi.setText(String.valueOf(row.Ứng_chuyển_đi));
            }


            final TextView Chi_phí = new TextView(this);
            if (i == -1) {
                Chi_phí.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Chi_phí.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Chi_phí.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Chi_phí.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Chi_phí.setGravity(Gravity.CENTER);

            Chi_phí.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Chi_phí.setText("Chi_phí");
                Chi_phí.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                Chi_phí.setBackgroundColor(Color.parseColor("#ffffff"));
                Chi_phí.setTextColor(Color.parseColor("#000000"));
                Chi_phí.setText(String.valueOf(row.Chi_phí));
            }



            final TextView Còn_lại = new TextView(this);
            if (i == -1) {
                Còn_lại.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Còn_lại.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Còn_lại.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Còn_lại.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Còn_lại.setGravity(Gravity.CENTER);

            Còn_lại.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Còn_lại.setText("Còn_lại");
                Còn_lại.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {

                Còn_lại.setBackgroundColor(Color.parseColor("#f7f7f7"));
                Còn_lại.setTextColor(Color.parseColor("#000000"));
                Còn_lại.setText(String.valueOf(row.Còn_lại));
            }

//trang thai

            final TextView Trạng_Thái = new TextView(this);
            if (i == -1) {
                Trạng_Thái.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Trạng_Thái.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Trạng_Thái.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Trạng_Thái.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Trạng_Thái.setGravity(Gravity.CENTER);

            Trạng_Thái.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Trạng_Thái.setText("Trạng_Thái");
                Trạng_Thái.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                Trạng_Thái.setBackgroundColor(Color.parseColor("#ffffff"));
                Trạng_Thái.setTextColor(Color.parseColor("#000000"));
                Trạng_Thái.setText(String.valueOf(row.Trạng_Thái));
            }

// ghi chú

            final TextView Ghi_chú = new TextView(this);
            if (i == -1) {
                Ghi_chú.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Ghi_chú.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Ghi_chú.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Ghi_chú.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Ghi_chú.setGravity(Gravity.CENTER);

            Ghi_chú.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Ghi_chú.setText("Ghi_chú");
                Ghi_chú.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {

                Ghi_chú.setBackgroundColor(Color.parseColor("#f7f7f7"));
                Ghi_chú.setTextColor(Color.parseColor("#000000"));
                Ghi_chú.setText(String.valueOf(row.Ghi_chú));
            }

            //manv

            final TextView MãNv = new TextView(this);
            if (i == -1) {
                MãNv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                MãNv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                MãNv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                MãNv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            MãNv.setGravity(Gravity.CENTER);

            MãNv.setPadding(15, 15, 15, 15);
            if (i == -1) {
                MãNv.setText("MãNv");
                MãNv.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                MãNv.setBackgroundColor(Color.parseColor("#ffffff"));
                MãNv.setTextColor(Color.parseColor("#000000"));
                MãNv.setText(String.valueOf(row.MãNv));
            }




//            final LinearLayout layCustomer = new LinearLayout(this);
//            layCustomer.setOrientation(LinearLayout.VERTICAL);
//            layCustomer.setPadding(0, 10, 0, 10);
//            layCustomer.setBackgroundColor(Color.parseColor("#f8f8f8"));
//
//            final TextView tv3 = new TextView(this);
//            if (i == -1) {
//                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
//                        TableRow.LayoutParams.MATCH_PARENT));
//                tv3.setPadding(5, 5, 0, 5);
//                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
//            } else {
//                tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
//                        TableRow.LayoutParams.MATCH_PARENT));
//                tv3.setPadding(5, 0, 0, 5);
//                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//            }
//
//            tv3.setGravity(Gravity.TOP);
//
//
//            if (i == -1) {
//                tv3.setText("Customer");
//                tv3.setBackgroundColor(Color.parseColor("#f0f0f0"));
//            } else {
//                tv3.setBackgroundColor(Color.parseColor("#f8f8f8"));
//                tv3.setTextColor(Color.parseColor("#000000"));
//                tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
//                tv3.setText(row.customerName);
//            }
//            layCustomer.addView(tv3);


//            if (i > -1) {
//                final TextView tv3b = new TextView(this);
//                tv3b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                        TableRow.LayoutParams.WRAP_CONTENT));
//
//                tv3b.setGravity(Gravity.RIGHT);
//                tv3b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//                tv3b.setPadding(5, 1, 0, 5);
//                tv3b.setTextColor(Color.parseColor("#aaaaaa"));
//                tv3b.setBackgroundColor(Color.parseColor("#f8f8f8"));
//                tv3b.setText(row.customerAddress);
//                layCustomer.addView(tv3b);
//            }
//
//            final LinearLayout layAmounts = new LinearLayout(this);
//            layAmounts.setOrientation(LinearLayout.VERTICAL);
//            layAmounts.setGravity(Gravity.RIGHT);
//            layAmounts.setPadding(0, 10, 0, 10);
//            layAmounts.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
//                    TableRow.LayoutParams.MATCH_PARENT));



//            final TextView tv4 = new TextView(this);
//            if (i == -1) {
//                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
//                        TableRow.LayoutParams.MATCH_PARENT));
//                tv4.setPadding(5, 5, 1, 5);
//                layAmounts.setBackgroundColor(Color.parseColor("#f7f7f7"));
//            } else {
//                tv4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
//                        TableRow.LayoutParams.WRAP_CONTENT));
//                tv4.setPadding(5, 0, 1, 5);
//                layAmounts.setBackgroundColor(Color.parseColor("#ffffff"));
//            }
//
//            tv4.setGravity(Gravity.RIGHT);
//
//            if (i == -1) {
//                tv4.setText("Inv.Amount");
//                tv4.setBackgroundColor(Color.parseColor("#f7f7f7"));
//                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
//            } else {
//                tv4.setBackgroundColor(Color.parseColor("#ffffff"));
//                tv4.setTextColor(Color.parseColor("#000000"));
//                tv4.setText(decimalFormat.format(row.invoiceAmount));
//                tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//            }
//
//            layAmounts.addView(tv4);
//
//
//            if (i > -1) {
//                final TextView tv4b = new TextView(this);
//                tv4b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
//                        TableRow.LayoutParams.WRAP_CONTENT));
//
//                tv4b.setGravity(Gravity.RIGHT);
//                tv4b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//                tv4b.setPadding(2, 2, 1, 5);
//                tv4b.setTextColor(Color.parseColor("#00afff"));
//                tv4b.setBackgroundColor(Color.parseColor("#ffffff"));
//
//                String due = "";
//                if (row.amountDue.compareTo(new BigDecimal(0.01)) == 1) {
//                    due = "Due:" + decimalFormat.format(row.amountDue);
//                    due = due.trim();
//                }
//                tv4b.setText(due);
//                layAmounts.addView(tv4b);
//            }


            // add table row
            final TableRow tr = new TableRow(this);
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(5,5,5,5);
            tr.setBackgroundResource(R.drawable.a);
            tr.setLayoutParams(trParams);
            tr.addView(Số_lệnh);
            tr.addView(Ngày_đi);

            tr.addView(Số_Km);
            tr.addView(Số_Bill);
            tr.addView(Số_xe);
            tr.addView(lái_xe);
            tr.addView(Chi_phí);
            tr.addView(Ứng_chuyển_đi);
            tr.addView(Lương_chuyển);
            tr.addView(Còn_lại);
            tr.addView(Trạng_Thái);
            tr.addView(Ghi_chú);
            tr.addView(MãNv);

//            tr.addView(layCustomer);
//            tr.addView(layAmounts);

            if (i > -1) {
                tr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TableRow tr = (TableRow) v;
                        //do whatever action is needed

                    }
                });

            }
            mTableLayout.addView(tr, trParams);

            if (i > -1) {

                // add separator row
                final TableRow trSep = new TableRow(this);
                TableLayout.LayoutParams trParamsSep = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);

                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(this);
                TableRow.LayoutParams tvSepLay = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 5;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(5);
                trSep.addView(tvSep);
                mTableLayout.addView(trSep, trParamsSep);
            }


        }
    }

    //////////////////////////////////////////////////////////////////////////////

    //
    // The params are dummy and not used
    //
    class LoadDataTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {

            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Task Completed.";
        }
        @Override
        protected void onPostExecute(String result) {
            mProgressBar.hide();
            loadData();
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {

        }
    }

}