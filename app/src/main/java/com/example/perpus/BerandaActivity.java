package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.perpus.adapter.AdapterPeminjaman;
import com.example.perpus.adapter.AdapterRecyclerBuku;
import com.example.perpus.adapter.AdapterSlide;
import com.example.perpus.data.DataBuku;
import com.example.perpus.data.DataPeminjaman;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class BerandaActivity extends AppCompatActivity {

    SessionManager sessionManager;

    TextView txtBuku;
    ViewPager mPager;
    CircleIndicator indicator;

    private static int currentPage = 0;
    private ArrayList<Integer> SlideArray = new ArrayList<Integer>();

    TextView txtBukude,txtPeminjaman;

    private static final String TAG = BerandaActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    RecyclerView recBuku;
    List<DataBuku> itemBuku = new ArrayList<DataBuku>();
    AdapterRecyclerBuku adapterBuku;

    //SessionManager sessionManager;
//    ListView listDataPeminjaman;
//    List<DataPeminjaman> itemList = new ArrayList<DataPeminjaman>();
//    AdapterPeminjaman adapterPeminjaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        //slide start
        currentPage = 0;
        SlideArray.clear();
        final Integer[] SlideIndex = {R.drawable.perpus1,R.drawable.perpus2,R.drawable.perpus3};
//        final Interger[] SlideIndex = (R.mipmap.ic_launcher,R.);
        for (int i=0;i<3;i++){
            SlideArray.add(SlideIndex[i]);
        }
        txtBukude = (TextView)findViewById(R.id.txtBuku);
        txtPeminjaman = (TextView)findViewById(R.id.txtPinjaman);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new AdapterSlide(this,SlideArray));
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        txtBukude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(BerandaActivity.this,BukuActivity.class);
                startActivity(i);
            }
        });
        txtPeminjaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(BerandaActivity.this,PeminjamanActivity.class);
                startActivity(i);
            }
        });
        //slide otomatsi berganti gamber
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == SlideIndex.length)
                {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++,true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },3000,3000);

        adapterBuku = new AdapterRecyclerBuku(itemBuku);

        recBuku = (RecyclerView) findViewById(R.id.recBuku);

        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false);
        recBuku.setLayoutManager(mLayoutManager1);
        recBuku.setItemAnimator(new DefaultItemAnimator());
        recBuku.setAdapter(adapterBuku);


//        HashMap<String, String> user = sessionManager.getUserDetails();
//        String pnoid = user.get(SessionManager.key_noid);
        //loadBuku();

//        listDataPeminjaman = (ListView) findViewById(R.id.listDataPeminjaman);
//        adapterPeminjaman = new AdapterPeminjaman(this,itemList);
//        listDataPeminjaman.setAdapter(adapterPeminjaman);

        loadBuku();


    }
    private void loadBuku(){
        itemBuku.clear();
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
                                itemBuku.add(item);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    adapterBuku.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(BerandaActivity.this, "Tidak dapat mengambil data", Toast.LENGTH_LONG).show();
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
