package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


public class DetailBukuActivity extends AppCompatActivity {

    TextView txtJudul,txtKategori,txtJenis,txtPenulis,txtPenerbit,txtTahunTerbit,txtDesk;
    ImageView imgCover;
    String judul,kategori,jenis,penulis,penerbit,tahunterbit,desk,gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        judul = getIntent().getStringExtra("JUDUL");
        kategori = getIntent().getStringExtra("KATEGORI");
        jenis = getIntent().getStringExtra("JENIS");
        penulis = getIntent().getStringExtra("PENULIS");
        penerbit = getIntent().getStringExtra("PENERBIT");
        tahunterbit = getIntent().getStringExtra("TAHUNTERBIT");
        desk = getIntent().getStringExtra("DESK");
        gambar = getIntent().getStringExtra("COVER");

        txtJudul = (TextView)findViewById(R.id.txtJudul);
        txtJenis = (TextView)findViewById(R.id.txtJenis);
        txtPenulis = (TextView)findViewById(R.id.txtPenulis);
        txtPenerbit = (TextView)findViewById(R.id.txtPenerbit);
        txtTahunTerbit = (TextView)findViewById(R.id.txtTahunPenerbit);
        txtDesk = (TextView)findViewById(R.id.txtDesk);
        imgCover = (ImageView)findViewById(R.id.imgCover);


        txtJudul.setText(judul);
        txtJenis.setText(jenis);
        txtPenulis.setText(penulis);
        txtPenerbit.setText(penerbit);
        txtTahunTerbit.setText(tahunterbit);
        txtDesk.setText(desk);
//        Glide.with(this).load(gambar).into((ImageView) findViewById(R.id.imgCover));
        Picasso.with(this).load(gambar).into(imgCover);
    }
}
