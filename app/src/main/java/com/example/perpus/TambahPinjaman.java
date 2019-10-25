package com.example.perpus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class TambahPinjaman extends AppCompatActivity {

    private int selectedDate,selectedMonth,selectedYear;
    int itgl,ibln,ithn;
    int status = 0;

    EditText edtTanggalPinjam, edTanggalKembali;
    Button btnTanggalPinjam,btnTanggalKembali,btnPinjam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pinjaman);

        edtTanggalPinjam = (EditText) findViewById(R.id.edtTanggal);
        edTanggalKembali = (EditText) findViewById(R.id.edtKembali);

        btnPinjam = (Button) findViewById(R.id.btnPinjam);
        btnTanggalKembali = (Button) findViewById(R.id.btnTanggalKembali);
        btnTanggalPinjam = (Button) findViewById(R.id.btnTanggalPinjam);
    }
    private void dialogTanggal(final EditText edtTanggal) {
        final Dialog dialog = new Dialog(TambahPinjaman.this);
        dialog.setContentView(R.layout.datepickerview);
        dialog.setTitle("");

        DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        selectedDate = calendar.get(Calendar.DAY_OF_MONTH);
        selectedMonth = calendar.get(Calendar.MONTH);
        selectedYear = calendar.get(Calendar.YEAR);
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Log.e("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);

                Log.e("selected date", selectedDate + "");
                Log.e("selected date", dayOfMonth + "");
                Log.e("selected month", selectedMonth + "");
                Log.e("selected month", month + "");

                Log.e("selected year", selectedYear + "");
                Log.e("selected year", year + "");
                if (selectedDate == dayOfMonth && selectedMonth == month && selectedYear == year) {
                    edtTanggal.setText(year + "-" + String.format("%02d", (month + 1)) + "-" + String.format("%02d", dayOfMonth));
                    dialog.dismiss();
                } else {
                    if (selectedDate != dayOfMonth) {
                        edtTanggal.setText(year + "-" + String.format("%02d", (month + 1)) + "-" + String.format("%02d", dayOfMonth));
                        dialog.dismiss();
                    } else {
                        if (selectedMonth != month) {
                            edtTanggal.setText(year + "-" + String.format("%02d", (month + 1)) + "-" + String.format("%02d", dayOfMonth));
                            dialog.dismiss();
                        }
                    }
                }
                selectedDate = dayOfMonth;
                selectedMonth = (month);
                selectedYear = year;

                itgl = Integer.valueOf(dayOfMonth);
                ibln = Integer.valueOf(month + 1);
                ithn = Integer.valueOf(year);

                Calendar now = Calendar.getInstance();
                if (ithn > now.get(Calendar.YEAR)){
                    status = 1;
                } else if (ithn == now.get(Calendar.YEAR)) {
                    if (ibln > now.get(Calendar.MONTH) + 1) {
                        status = 1;
                    } else if (ibln == now.get(Calendar.MONTH) + 1) {
                        if (itgl >= now.get(Calendar.DATE)) {
                            status = 1;
                        } else {
                            Toast.makeText(TambahPinjaman.this, "Tanggal tidak sesuai", Toast.LENGTH_SHORT).show();
                            status = 0;
                        }
                    } else {
                        Toast.makeText(TambahPinjaman.this, "Tanggal tidak sesuai", Toast.LENGTH_SHORT).show();
                        status = 0;
                    }
                } else {
                    Toast.makeText(TambahPinjaman.this, "Tanggal tidak sesuai", Toast.LENGTH_SHORT).show();
                    status = 0;
                }
            }
        });
        dialog.show();
    }

}
