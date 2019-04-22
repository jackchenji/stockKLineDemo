package zzg.example;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        entryData.add(new CandleEntry(getNumeral()[0],getNumeral()[1],getNumeral()[2],getNumeral()[3],getVolume(),4030,"2019-4-2",3010));
        entryData.add(new CandleEntry(getNumeral()[0],getNumeral()[1],getNumeral()[2],getNumeral()[3],getVolume(),2000,"2019-4-3",3010));
        entryData.add(new CandleEntry(getNumeral()[0],getNumeral()[1],getNumeral()[2],getNumeral()[3],getVolume(),5000,"2019-4-4",3010));
        myCandle.setData(entryData);//设置数据
    }

    //获取float型数据
    private  float getVolume(){
        Random random=new Random();
        //产生[1~100]的随机数
        float r=random.nextInt(10000)+100;
        return r;
    }

    //获取成交量
    private  float[] getNumeral(){
        Random random=new Random();
        float[] a = new float[2];
        float[] b = new float[2];
        for(int i = 0; i < a.length; i++) {
            a[i] = (float) ( random.nextInt(200)+2900);
        }
        // 冒泡排序
        boolean swapped = true; // 有没有发生过交换
        for(int i = 1; swapped && i <= a.length - 1; i++) {
            swapped = false;
            for(int j = 0; j < a.length - i; j++) {
                if(a[j] < a[j + 1]) {
                    float temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
        }

        for(int i = 0; i < b.length; i++) {
            b[i] = a[1]+((a[0] - a[1]) * new Random().nextFloat());
        }
       ;


        float[] temp = new float[4];
        temp[0]=a[0];
        temp[1]=a[1];
        temp[2]=b[0];
        temp[3]=b[1];
        return temp;
    }

}
