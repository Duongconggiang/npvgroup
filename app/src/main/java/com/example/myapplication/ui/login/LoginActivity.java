package com.example.myapplication.ui.login;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private static final String[] paths = {"Chọn nhóm khách hàng  ","LÁI XE ", "CHỦ HÀNG", "CHỦ PHƯƠNG TIỆN"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Button loginbacgroud = findViewById(R.id.loginbacgroud);
        String item = adapterView.getItemAtPosition(i).toString();

        switch (i) {
            case 0:
                Toast.makeText(this, "Vui long chon", Toast.LENGTH_SHORT).show();
                loginbacgroud.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(LoginActivity.this, "Vui lòng chọn hình thức đăng nhập", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 1:
                Toast.makeText(this, " Đăng nhập với " + item, Toast.LENGTH_SHORT).show();
                loginbacgroud.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(LoginActivity.this, BacgroudActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 2:
                Toast.makeText(this, "Đăng nhập với" + item, Toast.LENGTH_SHORT).show();
                loginbacgroud.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(LoginActivity.this, BacgroudActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 3:
                Toast.makeText(this, "Đăng nhập với " +item, Toast.LENGTH_SHORT).show();
                loginbacgroud.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(LoginActivity.this, BacgroudActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        finish();
    }
}



