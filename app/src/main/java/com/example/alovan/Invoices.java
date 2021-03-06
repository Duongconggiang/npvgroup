package com.example.alovan;

import android.util.Log;

import com.example.myapplication.data.ConnectDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Invoices {
    ArrayList <String> Solenh = new ArrayList<>();
    ArrayList <String> Ngaydi = new ArrayList<>();
    ArrayList <String> Tongkm = new ArrayList<>();
    ArrayList <String> Tongbill = new ArrayList<>();
    ArrayList <String> Soxe = new ArrayList<>();
    ArrayList <String> Tamung = new ArrayList<>();
    ArrayList <String> Luong = new ArrayList<>();
    ArrayList <String> Laixe = new ArrayList<>();
    ArrayList <String> Ghichu = new ArrayList<>();
    ArrayList <String> Manv = new ArrayList<>();
    ArrayList <String> Trangthai = new ArrayList<>();
    ArrayList <String> TongCP = new ArrayList<>();
    int f =0;
    public InvoiceData[] getInvoices() {

        int ThisYear = Calendar.getInstance().get(Calendar.YEAR);
        int ThisMonth = Calendar.getInstance().get(Calendar.MONTH);

        ConnectDB connectDB = new ConnectDB();
        Connection connection = connectDB.CONN();

        try {
            String qr = "Select * From vList_LenhDD Where Remooc=0 and Month(NgayTao)='" + ThisMonth + "'And Year(NgayTao)='" + ThisYear + "'";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(qr);
            while(rs.next()){
                Solenh.add(rs.getString("SoLenh"));
                Ngaydi.add(rs.getString("NgayDi"));
                Tongkm.add(rs.getString("Luong"));
                Tongbill.add(rs.getString("TongBill"));
                Soxe.add(rs.getString("Soxe"));
                Tamung.add(rs.getString("TamUng"));
                Luong.add(rs.getString("Luong"));
                Laixe.add(rs.getString("Ten"));
                Manv.add(rs.getString("Laixe"));
                Ghichu.add(rs.getString("GhiChu"));
                TongCP.add(rs.getString("TongCP"));
                int tl = Integer.parseInt(rs.getString("TrangThai"));
                if(tl==0){
                    Trangthai.add("????ng");
                }if(tl==1){
                    Trangthai.add("M???");
                }else {
                    Trangthai.add("T??nh l????ng");
                }
                f++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        InvoiceData[] data = new InvoiceData[f];
            for (int i = 0; i < f; i++) {

                InvoiceData row = new InvoiceData();
                row.S???_l???nh = Solenh.get(i);
                row.Ng??y_??i = Ngaydi.get(i);
                row.S???_Km = Tongkm.get(i);
                row.S???_Bill = Tongbill.get(i);
                row.S???_xe = Soxe.get(i);
                row.l??i_xe = Laixe.get(i);
                row.???ng_chuy???n_??i = Tamung.get(i);
                row.L????ng_chuy???n = Luong.get(i);
                row.Chi_ph?? = TongCP.get(i);
                int conlai = Integer.parseInt(Luong.get(i)) - Integer.parseInt(Tamung.get(i)) - Integer.parseInt(TongCP.get(i));
                row.C??n_l???i = String.valueOf(conlai);
                row.Tr???ng_Th??i = Trangthai.get(i);
                row.Ghi_ch?? = " Test";
                        // Ghichu.get(i);
                row.M??Nv = Manv.get(i);
                data[i] = row;
                //rs.next();
            }

            return data;

    }

}