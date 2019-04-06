package zzg.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import zzg.klinechart.internal.EntryData;
import zzg.klinechart.mycandle.MyCandle;
import zzg.klinechart.mycandle.entry.CandleEntry;

public class MyCandleActivity extends AppCompatActivity {
public MyCandle myCandle;
public List<CandleEntry>  entryData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_candle);
        myCandle= (MyCandle) findViewById(R.id.mycandle);
        entryData=new ArrayList<>();
        entryData.add(new CandleEntry(3050,2980,3000,3020,4020,4030,"2019-4-2",3010));
        myCandle.setData(entryData);
    }

}
