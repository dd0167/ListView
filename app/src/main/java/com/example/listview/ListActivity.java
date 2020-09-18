package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ListViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list;
    String[] numbers;
    TextView n;
    TextView x;
    TextView d;
    TextView sn;
    double sum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent la=getIntent();
        double fnum=la.getDoubleExtra("fnum",1);
        double snum=la.getDoubleExtra("snum",1);
        String s=la.getStringExtra("s");

        list=(ListView) findViewById(R.id.list);
        n=(TextView) findViewById(R.id.n);
        x=(TextView) findViewById(R.id.x);
        d=(TextView) findViewById(R.id.d);
        sn=(TextView) findViewById(R.id.sn);
        numbers=new String[20];

        x.setText("האיבר הראשון: "+EditNumber(fnum));
        d.setText("הקפיצות: "+EditNumber(snum));
        if (s.equals("han"))
        {
            for(int i=1;i<=numbers.length;i++){
                numbers[i-1]=EditNumber(fnum*(double)Math.pow((double)snum,(double)(i-1)));
            }
        }
        else if (s.equals("hes"))
        {
            for(int i=1;i<=numbers.length;i++){
                numbers[i-1]=EditNumber(fnum+(i-1)*snum);
            }
        }

        list.setOnItemClickListener(this);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, numbers);
        list.setAdapter(adp);
    }

    private String EditNumber(double v) {
        if((((double)((int)v))==(double)v))
        {
            return(String.valueOf((int)v));
        }
        else
        {
            return String.valueOf(v);
        }
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        sum=0;
        n.setText("מיקום המספר: "+position);
        for(int i=0;i<=position;i++){
            sum=sum+Double.parseDouble(numbers[i]);
        }
        sn.setText("סכום המספרים עד המספר שנלחץ: "+EditNumber(sum));
    }
}