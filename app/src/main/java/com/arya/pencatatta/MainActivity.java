package com.arya.pencatatta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText etNoinduk, etJudul, etPemilik, etPembimbing, etTempat, etTahun;
    private Button btnSave, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNoinduk = (EditText) findViewById(R.id.etNoinduk);
        etJudul = (EditText) findViewById(R.id.etJudul);
        etPemilik = (EditText) findViewById(R.id.etPemilik);
        etPembimbing = (EditText) findViewById(R.id.etPembimbing);
        etTempat = (EditText) findViewById(R.id.etTempat);
        etTahun = (EditText) findViewById(R.id.etTahun);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String noinduk = etNoinduk.getText().toString().trim();
                final String judul = etJudul.getText().toString().trim();
                final String pemilik = etPemilik.getText().toString().trim();
                final String pembimbing = etPembimbing.getText().toString().trim();
                final String tempat = etTempat.getText().toString().trim();
                final String tahun = etTahun.getText().toString().trim();

                class AddSuhu extends AsyncTask<Void, Void, String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(MainActivity.this,
                                "Menambahkan...", "Tunggu...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(MainActivity.this, s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    protected String doInBackground(Void... v) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put(Config.KEY_NOINDUK, noinduk);
                        params.put(Config.KEY_JUDUL, judul);
                        params.put(Config.KEY_PEMILIK, pemilik);
                        params.put(Config.KEY_PEMBIMBING, pembimbing);
                        params.put(Config.KEY_TEMPAT, tempat);
                        params.put(Config.KEY_TAHUN, tahun);

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(Config.URL_ADD_DATA, params);
                        return res;
                    }
                }

                AddSuhu ae = new AddSuhu();
                ae.execute();
            }
        });

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

    }
}