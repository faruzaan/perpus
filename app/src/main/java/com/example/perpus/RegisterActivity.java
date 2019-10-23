package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText edtNoid,edtNama,edtKelas,edtPassword,edtTelp,edtAlamat;
    Button btnRegister;
    private ProgressDialog progressDialog;

    private static final String TAG = LoginActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtNoid = (EditText)findViewById(R.id.edtNoid);
        edtNama = (EditText)findViewById(R.id.edtNama);
        edtKelas = (EditText)findViewById(R.id.edtKelas);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtTelp = (EditText)findViewById(R.id.edtTelp);
        edtAlamat = (EditText)findViewById(R.id.edtAlamat);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(
                        edtNoid.getText().toString(),
                        edtNama.getText().toString(),
                        edtKelas.getText().toString(),
                        edtPassword.getText().toString(),
                        edtTelp.getText().toString(),
                        edtAlamat.getText().toString()
                );
            }
        });

    }
    private void register(final String noid,final String nama,final String kelas,final String password,final String telp,final String alamat) {
        //menampilkan progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menambahkan User Siswa");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        //mengambil request dari API
        final StringRequest strReq = new StringRequest(Request.Method.POST, "https://faruzaan.000webhostapp.com/Perpus/register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    //mengambil response dari API berupa JSON
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");
                    if (error.matches("false")) {
                        JSONArray jArr = jObj.getJSONArray("data");
                        JSONObject dataObj = jArr.getJSONObject(0);
                        //menyimpan data user ke Session
                        progressDialog.dismiss();
                        //membuka intent / activity menu
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        //Jika id atau password salah
                        Toast.makeText(RegisterActivity.this, "Ada Data Yang Tidak Benar!", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                //Jika koneksi bermasalah atau API error
                Toast.makeText(RegisterActivity.this, "Tidak Ada Koneksi Internet", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //Mengirim parameters ke API
                Map<String, String> params = new HashMap<String, String>();
                params.put("noid", noid);
                params.put("nama", nama);
                params.put("kelas", kelas);
                params.put("password", password);
                params.put("telp", telp);
                params.put("alamat", alamat);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
