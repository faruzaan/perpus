package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.perpus.adapter.AdapterBuku;
import com.example.perpus.data.DataBuku;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BukuActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = BukuActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    ListView listDataBuku;
    SwipeRefreshLayout swipe;

    List<DataBuku> itemList = new ArrayList<DataBuku>();
    AdapterBuku adapterBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        listDataBuku = (ListView) findViewById(R.id.listDataBuku);

        adapterBuku = new AdapterBuku(this,itemList);
        listDataBuku.setAdapter(adapterBuku);

        swipe.setOnRefreshListener(this);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                itemList.clear();
                adapterBuku.notifyDataSetChanged();
                loadData();
            }
        });

        listDataBuku.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BukuActivity.this, DetailBukuActivity.class);
                intent.putExtra("JUDUL", itemList.get(i).getBuku_judul());
                intent.putExtra("KATEGORI", itemList.get(i).getBuku_kategori());
                intent.putExtra("JENIS", itemList.get(i).getBuku_jenis());
                intent.putExtra("PENULIS", itemList.get(i).getBuku_penulis());
                intent.putExtra("PENERBIT", itemList.get(i).getBuku_penerbit());
                intent.putExtra("COVER", itemList.get(i).getBuku_cover());
                intent.putExtra("TAHUNTERBIT", itemList.get(i).getBuku_tahunterbit());
                intent.putExtra("DESK", itemList.get(i).getBuku_desk());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {

    }
    private void loadData() {
        swipe.setRefreshing(true);
        itemList.clear();
        adapterBuku.notifyDataSetChanged();
        final StringRequest strReq = new StringRequest(Request.Method.POST, "https://faruzaan.000webhostapp.com/Perpus/getBuku.php", new Response.Listener<String>() {
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
                                DataBuku item = new DataBuku();
                                item.setBuku_id(dataObj.getString("buku_id"));
                                item.setBuku_judul(dataObj.getString("buku_judul"));
                                item.setBuku_kategori(dataObj.getString("buku_kategori"));
                                item.setBuku_jenis(dataObj.getString("buku_jenis"));
                                item.setBuku_penulis(dataObj.getString("buku_penulis"));
                                item.setBuku_penerbit(dataObj.getString("buku_penerbit"));
                                item.setBuku_tahunterbit(dataObj.getString("buku_tahunterbit"));
                                item.setBuku_cover(dataObj.getString("buku_cover"));
                                item.setBuku_penerbit(dataObj.getString("buku_penerbit"));
                                item.setBuku_desk(dataObj.getString("buku_desk"));
                                itemList.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    adapterBuku.notifyDataSetChanged();
                    swipe.setRefreshing(false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(BukuActivity.this, "Tidak dapat mengambil data", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("parameter1", "isinilai");
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
