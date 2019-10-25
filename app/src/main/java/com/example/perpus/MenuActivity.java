package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.HashMap;

public class MenuActivity extends AppCompatActivity {

    SessionManager sessionManager;

    ImageView imgFoto;
    TextView txtNama,txtKelas;
    Button btnBuku, btnPeminjaman, btnTentang, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imgFoto = (ImageView) findViewById(R.id.imgFoto);
        txtNama = (TextView) findViewById(R.id.txtNama);
        txtKelas = (TextView) findViewById(R.id.txtKelas);
        btnBuku = (Button) findViewById(R.id.btnBuku);
        btnPeminjaman = (Button) findViewById(R.id.btnPeminjaman);
        btnTentang = (Button) findViewById(R.id.btnTentang);
        btnKeluar = (Button) findViewById(R.id.btnKeluar);
        sessionManager  = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String nama = user.get(SessionManager.key_nama);
        String kelas = user.get(SessionManager.key_kelas);
        String gambar = user.get(SessionManager.key_foto);


        //mengatur text di textview dengan nilai di variable

        txtNama.setText(nama);
        txtKelas.setText(kelas);
//        imgFoto.setImageResource(gambar);


        btnBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i;
                i = new Intent(MenuActivity.this,BukuActivity.class);

                startActivity(i);
            }
        });
        btnPeminjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> user = sessionManager.getUserDetails();
                String noid = user.get(SessionManager.key_noid);
                Intent i;
                i = new Intent(MenuActivity.this,PeminjamanActivity.class);
                i.putExtra("noid",noid);
                startActivity(i);
            }
        });
        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
                Intent i;
                i = new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
