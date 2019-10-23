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

import java.util.List;


public class AdapterBuku extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<DataBuku> items;


    public AdapterBuku(Activity activity, List<DataBuku> items) {
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
            convertView = inflater.inflate(R.layout.item_buku, null);

        TextView txtJudul = (TextView) convertView.findViewById(R.id.txtBuku);
        TextView txtKategori = (TextView) convertView.findViewById(R.id.txtKategori);

        DataBuku data = items.get(position);

        txtJudul.setText(data.getBuku_judul());
        txtKategori.setText(data.getBuku_kategori());

        Glide.with(convertView.getContext()).load(data.getBuku_cover())
                .thumbnail(0.5f)
                .crossFade()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) convertView.findViewById(R.id.imgCover));

        return convertView;
    }
}

