package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.CheckBox;
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

public class LoginActivity extends AppCompatActivity {

    SessionManager sessionManager;
    EditText email,password;
    CheckBox checkBox;
    ImageButton signin;
    Button signup;

    //deklarasikan view yang ada di layout login
    EditText edtNoid, edtPswd;
    Button btnMasuk;

    private ProgressDialog progressDialog;

    private static final String TAG = LoginActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager  = new SessionManager(getApplicationContext());
        //hubungkan view dengan id yang ada di layout
        edtNoid = (EditText) findViewById(R.id.edtNoid);
        edtPswd = (EditText) findViewById(R.id.edtPswd);
        signin =(ImageButton)findViewById(R.id.btnMasuk);
        checkBox = (CheckBox)findViewById(R.id.checkbox);
        signup = (Button) findViewById(R.id.signup);


        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"Sign In Button Clicked",Toast.LENGTH_LONG).show();
                login(edtNoid.getText().toString(), edtPswd.getText().toString());
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        //tambahankan event onclick
//        btnMasuk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //membuka activity menu
//                startActivity(new Intent(LoginActivity.this, MenuActivity.class));
//                login(edtNoid.getText().toString(), edtPswd.getText().toString());
//            }
//        });
    }

    private void login(final String noid,final String pass) {
        //menampilkan progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mengecek ID dan Kata Sandi");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        //mengambil request dari API
        final StringRequest strReq = new StringRequest(Request.Method.POST, "https://faruzaan.000webhostapp.com/Perpus/login.php", new Response.Listener<String>() {
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
                        sessionManager.createSession(
                                dataObj.getString("siswa_id"),
                                dataObj.getString("siswa_nama"),
                                dataObj.getString("siswa_kelas"),
                                dataObj.getString("siswa_foto"));
                        progressDialog.dismiss();
                        //membuka intent / activity menu
                        startActivity(new Intent(LoginActivity.this, BerandaActivity.class));
                        finish();
                    } else {
                        //Jika id atau password salah
                        Toast.makeText(LoginActivity.this, "ID atau Kata Sandi Salah!", Toast.LENGTH_LONG).show();
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
                Toast.makeText(LoginActivity.this, "Tidak Ada Koneksi Internet", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //Mengirim parameters ke API
                Map<String, String> params = new HashMap<String, String>();
                params.put("noid", noid);
                params.put("pswd", pass);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}
