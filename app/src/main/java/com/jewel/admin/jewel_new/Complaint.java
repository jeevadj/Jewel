package com.jewel.admin.jewel_new;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.jewel.admin.jewel_new.Adapter.Complaint_Adapter;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;


public class Complaint extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Calligrapher calligrapher;
    EditText adhar,ijsc,cmt;
    Button submit;
    Spinner spinner;
    Firebase fb;
    String url="https://jewel-o-track.firebaseio.com/";
    SharedPreferences sharedPreferences;
    String stijsc,stadhar,stcmt,stspi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        adhar = (EditText)findViewById(R.id.adhar);
        ijsc = (EditText)findViewById(R.id.ijsc);
        cmt = (EditText)findViewById(R.id.cmt);
        submit = (Button) findViewById(R.id.submit);
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> list = new ArrayList<String>();
        list.add("Lost");
        list.add("Stolen");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        Firebase.setAndroidContext(this);
        fb=new Firebase(url);
        sharedPreferences=getSharedPreferences("dataset",MODE_PRIVATE);
        String aadhar=sharedPreferences.getString("aadhaar","");
        adhar.setText(aadhar);

        calligrapher = new Calligrapher(this);
        calligrapher.setFont(Complaint.this,"Ubuntu_R.ttf",true);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public class complaint_Task extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            stijsc=ijsc.getText().toString();
            stcmt=cmt.getText().toString();
            stadhar=adhar.getText().toString();
            stspi=spinner.getSelectedItem().toString();

            Complaint_Adapter complaint_adapter=new Complaint_Adapter();
            complaint_adapter.setAadhaar(stadhar);
            complaint_adapter.setIjsc(stijsc);
            complaint_adapter.setParticulars(stcmt);
            complaint_adapter.setStatus(stspi);


            fb.child("Complaints").child(stijsc).setValue(complaint_adapter);



            return null;
        }
    }
}
