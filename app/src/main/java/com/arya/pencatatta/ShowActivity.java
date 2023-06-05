package com.arya.pencatatta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private String JSON_STRING;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        listView        =  (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        getJSON();

        btnBack = (ImageButton) findViewById(R.id.arrow_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBack();
            }
        });
    }

    private void showData(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String ,String>> list = new
                ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result =
                    jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++){
                JSONObject jsonObject1 = result.getJSONObject(i);
                String Id           = jsonObject1.getString(Config.TAG_ID);
                String NoInduk      = jsonObject1.getString(Config.TAG_NOINDUK);
                String Judul        = jsonObject1.getString(Config.TAG_JUDUL);
                String Pemilik      = jsonObject1.getString(Config.TAG_PEMILIK);
                String Pembimbing   = jsonObject1.getString(Config.TAG_PEMBIMBING);
                String Tempat       = jsonObject1.getString(Config.TAG_TEMPAT);
                String Angkatan     = jsonObject1.getString(Config.TAG_TAHUN);
                HashMap<String,String> showData = new HashMap<>();
                showData.put(Config.TAG_ID, Id);
                showData.put(Config.TAG_NOINDUK, NoInduk);
                showData.put(Config.TAG_JUDUL, Judul);
                showData.put(Config.TAG_PEMILIK, Pemilik);
                showData.put(Config.TAG_PEMBIMBING, Pembimbing);
                showData.put(Config.TAG_TEMPAT, Tempat);
                showData.put(Config.TAG_TAHUN, Angkatan);
                list.add(showData);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                ShowActivity.this, list, R.layout.list_item,
                new String[]{
                        Config.TAG_ID,
                        Config.TAG_NOINDUK,
                        Config.TAG_JUDUL,
                        Config.TAG_PEMILIK,
                        Config.TAG_PEMBIMBING,
                        Config.TAG_TEMPAT,
                        Config.TAG_TAHUN,
                },
                new  int[]{
                        R.id.id,

                        R.id.noinduk,
                        R.id.judul,
                        R.id.pemilik,
                        R.id.pembimbing,
                        R.id.tempat,
                        R.id.tahun,
                }
        );
        listView.setAdapter(adapter);
    }

    private void getJSON() {
        class GetJSON extends AsyncTask<Void, Void, String > {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ShowActivity.this, "Mengambil Data", "Mohon Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showData();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendGetRequest(Config.URL_SHOW_DATA);
                return s;
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, UpdateActivity.class);
        HashMap <String, String> map = (HashMap) parent.getItemAtPosition(position);
        String Id = map.get(Config.TAG_ID).toString();
        intent.putExtra(Config.KEY_ID, Id);
        startActivity(intent);
        finish();
    }

    public void btnBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }
}
