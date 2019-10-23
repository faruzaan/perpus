package com.example.perpus.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.perpus.R;
import com.example.perpus.data.DataBuku;

import java.util.List;

public class AdapterRecyclerBuku extends RecyclerView.Adapter<AdapterRecyclerBuku.ViewHolder> {
    private List<DataBuku> mDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCover;
        TextView txtJudul, txtKategori;

        public ViewHolder(View view) {
            super(view);
            imgCover = view.findViewById(R.id.imgCover);
            txtJudul = view.findViewById(R.id.txtJudul);
            txtKategori = view.findViewById(R.id.txtKategori);
        }
    }

    public void add(int position, DataBuku item) {
        mDataSet.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(DataBuku item) {
        int position = mDataSet.indexOf(item);
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }

    public AdapterRecyclerBuku(List<DataBuku> dataList) {
        mDataSet = dataList;
    }

    @Override
    public AdapterRecyclerBuku.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_buku, parent, false);
        AdapterRecyclerBuku.ViewHolder vh = new AdapterRecyclerBuku.ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerBuku.ViewHolder holder, int position) {

        holder.txtJudul.setText(mDataSet.get(position).getBuku_judul());
        holder.txtKategori.setText(mDataSet.get(position).getBuku_kategori());

        Glide.with(holder.imgCover.getContext())
                .load(mDataSet.get(position).getBuku_cover())
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .into(holder.imgCover);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

