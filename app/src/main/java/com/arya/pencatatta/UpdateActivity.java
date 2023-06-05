package com.arya.pencatatta;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextId, editTextNoinduk, editTextJudul, editTextPemilik, editTextPembimbing, editTextTempat, editTextTahun;
    private Button buttonUpdate, buttonDelete;
    private ImageButton btn_Back;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();

        id = intent.getStringExtra(Config.KEY_ID);

        editTextId = (EditText) findViewById(R.id.etId);
        editTextNoinduk = (EditText) findViewById(R.id.etNoinduk);
        editTextJudul = (EditText) findViewById(R.id.etJudul);
        editTextPemilik = (EditText) findViewById(R.id.etPemilik);
        editTextPembimbing = (EditText) findViewById(R.id.etPembimbing);
        editTextTempat = (EditText) findViewById(R.id.etTempat);
        editTextTahun = (EditText) findViewById(R.id.etTahun);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        btn_Back = (ImageButton) findViewById(R.id.arrow_back);
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBack();
            }
        });

        editTextId.setText(id);

        getCatatan();
    }

    private void getCatatan(){
        class GetSiswa extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateActivity.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showCatatan(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_DATA,id);
                return s;
            }
        }
        GetSiswa ge = new GetSiswa();
        ge.execute();
    }

    private void showCatatan(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String id = c.getString(Config.TAG_ID);
            String noinduk = c.getString(Config.TAG_NOINDUK);
            String judul = c.getString(Config.TAG_JUDUL);
            String pemilik = c.getString(Config.TAG_PEMILIK);
            String pembimbing = c.getString(Config.TAG_PEMBIMBING);
            String tempat = c.getString(Config.TAG_TEMPAT);
            String tahun = c.getString(Config.TAG_TAHUN);

            editTextId.setText(id);
            editTextNoinduk.setText(noinduk);
            editTextJudul.setText(judul);
            editTextPemilik.setText(pemilik);
            editTextPembimbing.setText(pembimbing);
            editTextTempat.setText(tempat);
            editTextTahun.setText(tahun);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateSiswa(){
        final String noinduk = editTextNoinduk.getText().toString().trim();
        final String judul = editTextJudul.getText().toString().trim();
        final String pemilik = editTextPemilik.getText().toString().trim();
        final String pembimbing = editTextPembimbing.getText().toString().trim();
        final String tempat = editTextTempat.getText().toString().trim();
        final String tahun = editTextTahun.getText().toString().trim();

        class UpdateSiswa extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateActivity.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UpdateActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_ID,id);
                hashMap.put(Config.KEY_NOINDUK,noinduk);
                hashMap.put(Config.KEY_JUDUL,judul);
                hashMap.put(Config.KEY_PEMILIK,pemilik);
                hashMap.put(Config.KEY_PEMBIMBING,pembimbing);
                hashMap.put(Config.KEY_TEMPAT,tempat);
                hashMap.put(Config.KEY_TAHUN,tahun);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_DATA,hashMap);

                return s;
            }
        }

        UpdateSiswa ue = new UpdateSiswa();
        ue.execute();
    }

    private void deleteSiswa(){
        class DeleteSiswa extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateActivity.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UpdateActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE_DATA, id);
                return s;
            }
        }

        DeleteSiswa de = new DeleteSiswa();
        de.execute();
    }

    private void confirmDeleteSiswa(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteSiswa();
                        startActivity(new Intent(UpdateActivity.this, ShowActivity.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateSiswa();
        }

        if(v == buttonDelete){
            confirmDeleteSiswa();
        }
    }

    public void btnBack() {
        Intent intent = new Intent(this, ShowActivity.class);
        startActivity(intent);
        finish();
    }

    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), ShowActivity.class);
        startActivity(i);
        finish();
    }
}