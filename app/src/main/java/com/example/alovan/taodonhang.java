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
            final TextView S???_l???nh = new TextView(this);
            S???_l???nh.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            S???_l???nh.setGravity(Gravity.CENTER);


            S???_l???nh.setPadding(15, 15, 15, 15);


            if (i == -1) {
                S???_l???nh.setText("S??? l???nh");

                S???_l???nh.setBackgroundColor(Color.parseColor("#f7f7f7"));
                S???_l???nh.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);

            } else {

                S???_l???nh.setBackgroundColor(Color.parseColor("#ffffff"));
                S???_l???nh.setText(String.valueOf(row.S???_l???nh));
                S???_l???nh.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            final TextView Ng??y_??i = new TextView(this);

            if (i == -1) {
                Ng??y_??i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Ng??y_??i.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Ng??y_??i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Ng??y_??i.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Ng??y_??i.setGravity(Gravity.CENTER);


            Ng??y_??i.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Ng??y_??i.setText("Ng??y ??i");
                Ng??y_??i.setBackgroundColor(Color.parseColor("#ffffff"));

            }else {
                Ng??y_??i.setBackgroundColor(Color.parseColor("#f7f7f7"));
                Ng??y_??i.setTextColor(Color.parseColor("#000000"));
                Ng??y_??i.setText(String.valueOf(row.Ng??y_??i));
            }


            final TextView S???_Km = new TextView(this);
            if (i == -1) {
                S???_Km.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                S???_Km.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                S???_Km.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                S???_Km.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            S???_Km.setGravity(Gravity.CENTER);

            S???_Km.setPadding(15, 15, 15, 15);
            if (i == -1) {
                S???_Km.setText("S??? Km");
                S???_Km.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                S???_Km.setBackgroundColor(Color.parseColor("#ffffff"));
                S???_Km.setTextColor(Color.parseColor("#000000"));
                S???_Km.setText(String.valueOf(row.S???_Km));
            }

            final TextView S???_Bill = new TextView(this);
            if (i == -1) {
                S???_Bill.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                S???_Bill.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                S???_Bill.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                S???_Bill.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            S???_Bill.setGravity(Gravity.CENTER);

            S???_Bill.setPadding(15, 15, 15, 15);
            if (i == -1) {
                S???_Bill.setText("S???_Bill");
                S???_Bill.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {
                S???_Bill.setBackgroundColor(Color.parseColor("#f7f7f7"));
                S???_Bill.setTextColor(Color.parseColor("#000000"));
                S???_Bill.setText(String.valueOf(row.S???_Bill));
            }


            final TextView S???_xe = new TextView(this);
            if (i == -1) {
                S???_xe.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                S???_xe.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                S???_xe.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                S???_xe.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            S???_xe.setGravity(Gravity.CENTER);

            S???_xe.setPadding(15, 15, 15, 15);
            if (i == -1) {
                S???_xe.setText("S???_xe");
                S???_xe.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                S???_xe.setBackgroundColor(Color.parseColor("#ffffff"));
                S???_xe.setTextColor(Color.parseColor("#000000"));
                S???_xe.setText(String.valueOf(row.S???_xe));
            }

            final TextView l??i_xe = new TextView(this);
            if (i == -1) {
                l??i_xe.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                l??i_xe.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                l??i_xe.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                l??i_xe.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            l??i_xe.setGravity(Gravity.CENTER);

            l??i_xe.setPadding(15, 15, 15, 15);
            if (i == -1) {
                l??i_xe.setText("l??i_xe");
                l??i_xe.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {
                l??i_xe.setBackgroundColor(Color.parseColor("#f7f7f7"));

                l??i_xe.setTextColor(Color.parseColor("#000000"));
                l??i_xe.setText(String.valueOf(row.l??i_xe));
            }



            final TextView L????ng_chuy???n = new TextView(this);
            if (i == -1) {
                L????ng_chuy???n.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                L????ng_chuy???n.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                L????ng_chuy???n.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                L????ng_chuy???n.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            L????ng_chuy???n.setGravity(Gravity.CENTER);

            L????ng_chuy???n.setPadding(15, 15, 15, 15);
            if (i == -1) {
                L????ng_chuy???n.setText("L????ng_chuy???n");
                L????ng_chuy???n.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                L????ng_chuy???n.setBackgroundColor(Color.parseColor("#ffffff"));
                L????ng_chuy???n.setTextColor(Color.parseColor("#000000"));
                L????ng_chuy???n.setText(String.valueOf(row.L????ng_chuy???n));
            }


            final TextView ???ng_chuy???n_??i = new TextView(this);
            if (i == -1) {
                ???ng_chuy???n_??i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                ???ng_chuy???n_??i.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                ???ng_chuy???n_??i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                ???ng_chuy???n_??i.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            ???ng_chuy???n_??i.setGravity(Gravity.CENTER);

            ???ng_chuy???n_??i.setPadding(5, 15, 15, 15);
            if (i == -1) {
                ???ng_chuy???n_??i.setText("???ng_chuy???n_??i");
                ???ng_chuy???n_??i.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {

                ???ng_chuy???n_??i.setBackgroundColor(Color.parseColor("#f7f7f7"));
                ???ng_chuy???n_??i.setTextColor(Color.parseColor("#000000"));
                ???ng_chuy???n_??i.setText(String.valueOf(row.???ng_chuy???n_??i));
            }


            final TextView Chi_ph?? = new TextView(this);
            if (i == -1) {
                Chi_ph??.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Chi_ph??.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Chi_ph??.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Chi_ph??.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Chi_ph??.setGravity(Gravity.CENTER);

            Chi_ph??.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Chi_ph??.setText("Chi_ph??");
                Chi_ph??.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                Chi_ph??.setBackgroundColor(Color.parseColor("#ffffff"));
                Chi_ph??.setTextColor(Color.parseColor("#000000"));
                Chi_ph??.setText(String.valueOf(row.Chi_ph??));
            }



            final TextView C??n_l???i = new TextView(this);
            if (i == -1) {
                C??n_l???i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                C??n_l???i.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                C??n_l???i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                C??n_l???i.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            C??n_l???i.setGravity(Gravity.CENTER);

            C??n_l???i.setPadding(15, 15, 15, 15);
            if (i == -1) {
                C??n_l???i.setText("C??n_l???i");
                C??n_l???i.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {

                C??n_l???i.setBackgroundColor(Color.parseColor("#f7f7f7"));
                C??n_l???i.setTextColor(Color.parseColor("#000000"));
                C??n_l???i.setText(String.valueOf(row.C??n_l???i));
            }

//trang thai

            final TextView Tr???ng_Th??i = new TextView(this);
            if (i == -1) {
                Tr???ng_Th??i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Tr???ng_Th??i.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Tr???ng_Th??i.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Tr???ng_Th??i.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Tr???ng_Th??i.setGravity(Gravity.CENTER);

            Tr???ng_Th??i.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Tr???ng_Th??i.setText("Tr???ng_Th??i");
                Tr???ng_Th??i.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                Tr???ng_Th??i.setBackgroundColor(Color.parseColor("#ffffff"));
                Tr???ng_Th??i.setTextColor(Color.parseColor("#000000"));
                Tr???ng_Th??i.setText(String.valueOf(row.Tr???ng_Th??i));
            }

// ghi ch??

            final TextView Ghi_ch?? = new TextView(this);
            if (i == -1) {
                Ghi_ch??.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                Ghi_ch??.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                Ghi_ch??.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                Ghi_ch??.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            Ghi_ch??.setGravity(Gravity.CENTER);

            Ghi_ch??.setPadding(15, 15, 15, 15);
            if (i == -1) {
                Ghi_ch??.setText("Ghi_ch??");
                Ghi_ch??.setBackgroundColor(Color.parseColor("#ffffff"));
            }else {

                Ghi_ch??.setBackgroundColor(Color.parseColor("#f7f7f7"));
                Ghi_ch??.setTextColor(Color.parseColor("#000000"));
                Ghi_ch??.setText(String.valueOf(row.Ghi_ch??));
            }

            //manv

            final TextView M??Nv = new TextView(this);
            if (i == -1) {
                M??Nv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                M??Nv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                M??Nv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                M??Nv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }

            M??Nv.setGravity(Gravity.CENTER);

            M??Nv.setPadding(15, 15, 15, 15);
            if (i == -1) {
                M??Nv.setText("M??Nv");
                M??Nv.setBackgroundColor(Color.parseColor("#f7f7f7"));
            }else {
                M??Nv.setBackgroundColor(Color.parseColor("#ffffff"));
                M??Nv.setTextColor(Color.parseColor("#000000"));
                M??Nv.setText(String.valueOf(row.M??Nv));
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
            tr.addView(S???_l???nh);
            tr.addView(Ng??y_??i);

            tr.addView(S???_Km);
            tr.addView(S???_Bill);
            tr.addView(S???_xe);
            tr.addView(l??i_xe);
            tr.addView(Chi_ph??);
            tr.addView(???ng_chuy???n_??i);
            tr.addView(L????ng_chuy???n);
            tr.addView(C??n_l???i);
            tr.addView(Tr???ng_Th??i);
            tr.addView(Ghi_ch??);
            tr.addView(M??Nv);

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