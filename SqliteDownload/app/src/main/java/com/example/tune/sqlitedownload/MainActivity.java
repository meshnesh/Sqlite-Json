package com.example.tune.sqlitedownload;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

//    String ParsingDta= "{\"Employee\":[{\"id\":\"101\",\"name\":\"sonoo jaiswal\",\"salary\":\"50000\"},{\"id\":\"102\",\"name\":\"vimal jaiswal\",\"salary\":\"600000\"}]}";
    String ParsingDta ="http://www.mocky.io/v2/589ae903100000140166e3ef";
    TextView textView1;
    ArrayList arrayList;
    String str="";
    ListView listView;
    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new Database(this);
        // this.deleteDatabase("EmployeeDatabase.db");
        database.getWritableDatabase();
        textView1=(TextView)findViewById(R.id.textView1);
        listView=(ListView)findViewById(R.id.listView);
        try
        {
            JSONObject jsonObject=new JSONObject(ParsingDta);
            JSONArray jsonArray= jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String pestid=jsonObject1.getString("pestid").toString();
                String pestname=jsonObject1.getString("pestname").toString();
                String status=jsonObject1.getString("status").toString();
                database.insertData(pestid,pestname,status);
                str+="\n Data"+i+ "\n pestid:"+pestid+"\n pestname:"+pestname+"\n status:" +status+"\n";
                //textView1.setText(str);
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        arrayList=database.fetchData();
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.activity_list_item,android.R.id.text1,arrayList);
        listView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }}
