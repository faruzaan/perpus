package com.example.perpus.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.perpus.R;
import com.example.perpus.data.DataBuku;
import com.example.perpus.data.DataPeminjaman;

import java.util.List;


public class AdapterPeminjaman extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataPeminjaman> items;


    public AdapterPeminjaman(Activity activity, List<DataPeminjaman> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_peminjaman, null);

        TextView txtNamaBuku = (TextView) convertView.findViewById(R.id.txtNamaBuku);
        TextView txtJumlahBuku = (TextView) convertView.findViewById(R.id.txtJumlahBuku);
        TextView txtTglPeminjaman = (TextView) convertView.findViewById(R.id.txtTglPeminjaman);

        DataPeminjaman data = items.get(position);

        txtNamaBuku.setText(data.getBuku_judul());
        txtJumlahBuku.setText(data.getDetail_pinjaman_jumlah().toString());
        txtTglPeminjaman.setText(data.getPinjaman_tanggal());

        Glide.with(convertView.getContext()).load(data.getCover())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) convertView.findViewById(R.id.imgCoverPeminjaman));

        return convertView;
    }
}

