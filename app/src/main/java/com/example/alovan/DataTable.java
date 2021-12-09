package com.example.alovan;

import java.math.BigDecimal;
import java.util.Date;

public class DataTable {
    public TableTaodonhang[] getInvoices() {
        TableTaodonhang[] data = new TableTaodonhang[20];

//               for(int i = 0; i < 20; i ++) {
//            TableTaodonhang row = new TableTaodonhang();
//            row.MÃ = (i+1);
//            row.Số_Km = row.MÃ;
//            row.Số_Bill = "dsdasd";
//            row.Ngày_đi = new Date();
//            row.Số_xe =  "1234";
//            row.lái_xe = "NYC";
//            row.Ứng_chuyển_đi =  "1323";
//            row.Lương_chuyển = "4324324";
//            row.Chi_phí =  "43423";
//            row.Còn_lại = "34234234";
//            row.Trạng_Thái =  "324324";
//            row.Ghi_chú = "32423";
//            data[i] = row;
//        }
//        return data;
//
//    }
//}

        for(int i = 0; i < 20; i ++) {
            TableTaodonhang row = new TableTaodonhang();
            row.id = (i+1);
            row.invoiceNumber = row.id;
            row.amountDue = BigDecimal.valueOf(20.00 * i);
            row.invoiceAmount = BigDecimal.valueOf(120.00 * (i+1));
            row.invoiceDate = new Date();
            row.customerName =  "Thomas John Beckett";
            row.customerAddress = "1112, Hash Avenue, NYC";

            data[i] = row;
        }
        return data;

    }
}

//package com.example.alovan;
//
//import android.util.Log;
//
//import com.example.myapplication.data.ConnectDB;
//
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Calendar;
//import java.util.Date;
//
//public class DataTable {
//    public TableTaodonhang[] getInvoices() {
//
//        int ThisMonth = Calendar.MONTH;
//        int ThisYear = Calendar.YEAR;
//        TableTaodonhang[] data = new TableTaodonhang[5];
//
//        ConnectDB connectDB = new ConnectDB();
//        Connection connection = connectDB.CONN();
////        try {
////            //String qr = "Select * From vList_LenhDD Where Remooc=0 and Month(NgayTao)='" + ThisMonth +"'And Year(NgayTao)='"+ ThisYear+"'Order by NgayDi";
////            String qr = "Select * From vList_LenhDD Where Remooc=0 and Month(NgayTao)='" + ThisMonth +"'And Year(NgayTao)='"+ ThisYear+"'";
////            Statement stm = connection.createStatement();
////            ResultSet rs = stm.executeQuery(qr);
////            int i = 0;
////            while (rs.next()){
////                if(i>6){break;}
//        for(int i =0; i<5;i++){
//                TableTaodonhang row = new TableTaodonhang();
//                row.MÃ = "SoLenh";
//                row.Số_Km = "TongKm" ;
//                row.Số_Bill = "TongBill";
//                row.Ngày_đi = "NgayDi";
//                row.Số_xe =  "Soxe";
//                row.lái_xe = "Laixe";
//                row.Ứng_chuyển_đi =  "TamUng";
//                row.Lương_chuyển = "Luong";
//                row.Chi_phí =  "TongCP";
//                row.Còn_lại = "Soxe";
//                row.Trạng_Thái =  "Soxe";
//                row.Ghi_chú = "GhiChu";
////                Log.e("Số lệnh",rs.getString("SoLenh"));
//
//
//
////                row.MÃ = rs.getString("SoLenh");
////                row.Số_Km = rs.getString("TongKm") ;
////                row.Số_Bill = rs.getString("TongBill");
////                row.Ngày_đi = rs.getString("NgayDi");
////                row.Số_xe =  rs.getString("Soxe");
////                row.lái_xe = rs.getString("Laixe");
////                row.Ứng_chuyển_đi =  rs.getString("TamUng");
////                row.Lương_chuyển = rs.getString("Luong");
////                row.Chi_phí =  rs.getString("TongCP");
////                row.Còn_lại = rs.getString("Soxe");
////                row.Trạng_Thái =  rs.getString("Soxe");
////                row.Ghi_chú = rs.getString("GhiChu");
//                data[i] = row;
////                i++;
//            }
//
////        } catch (SQLException throwables) {
////            throwables.printStackTrace();
////        }
//
//
//
//        //10 table
//
////               for(int i = 0; i < 10; i ++) {
////            TableTaodonhang row = new TableTaodonhang();
////            row.MÃ = (i+1);
////            row.Số_Km = row.MÃ;
////            row.Số_Bill = "dsdasd";
////            row.Ngày_đi = new Date();
////            row.Số_xe =  "1234";
////            row.lái_xe = "NYC";
////            row.Ứng_chuyển_đi =  "1323";
////            row.Lương_chuyển = "4324324";
////            row.Chi_phí =  "43423";
////            row.Còn_lại = "34234234";
////            row.Trạng_Thái =  "324324";
////            row.Ghi_chú = "32423";
////            data[i] = row;
////        }
//        return data;
//
//    }
//}
//
////        for(int i = 0; i < 20; i ++) {
////            TableTaodonhang row = new TableTaodonhang();
////            row.id = (i+1);
////            row.invoiceNumber = row.id;
////            row.amountDue = BigDecimal.valueOf(20.00 * i);
////            row.invoiceAmount = BigDecimal.valueOf(120.00 * (i+1));
////            row.invoiceDate = new Date();
////            row.customerName =  "Thomas John Beckett";
////            row.customerAddress = "1112, Hash Avenue, NYC";
////
////            data[i] = row;
////        }
////        return data;
////
////    }
////}