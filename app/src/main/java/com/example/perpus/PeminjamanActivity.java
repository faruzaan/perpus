package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.perpus.adapter.AdapterPeminjaman;
import com.example.perpus.adapter.AdapterPeminjaman;
import com.example.perpus.data.DataPeminjaman;
import com.example.perpus.data.DataPeminjaman;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeminjamanActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = PeminjamanActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    ListView listDataPeminjaman;
    SwipeRefreshLayout swipe;

    List<DataPeminjaman> itemList = new ArrayList<DataPeminjaman>();
    AdapterPeminjaman adapterPeminjaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        listDataPeminjaman = (ListView) findViewById(R.id.listDataPeminjaman);
        adapterPeminjaman = new AdapterPeminjaman(this,itemList);
        listDataPeminjaman.setAdapter(adapterPeminjaman);

        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                itemList.clear();
                adapterPeminjaman.notifyDataSetChanged();
                //loadData();
            }
        });
    }

    @Override
    public void onRefresh() {

    }
    private void loadData() {
        swipe.setRefreshing(true);
        itemList.clear();
        adapterPeminjaman.notifyDataSetChanged();
        final StringRequest strReq = new StringRequest(Request.Method.POST, "https://faruzaan.000webhostapp.com/Perpus/getPeminjamanB.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");
//                    JSONObject result = jObj.getJSONObject("result");
                    if (error.matches("false")) {
                        JSONArray jArr = jObj.getJSONArray("data");
                        for (int i = 0; i < jArr.length(); i++) {
                            try {
                                JSONObject dataObj = jArr.getJSONObject(i);
                                DataPeminjaman item = new DataPeminjaman();
                                item.setPinjaman_id(dataObj.getString("pinjaman_id"));
                                item.setSiswa_id(dataObj.getString("siswa_id"));
                                item.setSiswa_nama(dataObj.getString("siswa_nama"));
                                item.setBuku_judul(dataObj.getString("buku_judul"));
                                item.setCover(dataObj.getString("cover"));
                                item.setDetail_pinjaman_jumlah(dataObj.getString("detail_pinjaman_jumlah"));
                                item.setAdmin_id(dataObj.getString("admin_id"));
                                item.setAdmin_nama(dataObj.getString("admin_nama"));
                                item.setPinjaman_tanggal(dataObj.getString("pinjaman_tanggal"));
                                itemList.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    adapterPeminjaman.notifyDataSetChanged();
                    swipe.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(PeminjamanActivity.this, "Tidak dapat mengambil data", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("noid", "isinilai");
                params.put("parameter2", "isinilai");
                return params;
            }

            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                //headers.put("API-KEY", "kjashdkjaskjhduihoas978978as9udhujahksdh98");
                //headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
