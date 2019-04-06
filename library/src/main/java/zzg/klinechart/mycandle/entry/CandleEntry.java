package zzg.klinechart.mycandle.entry;

/**
 * author by chenji on 2019/3/21
 */
public class CandleEntry {
    public   float  high;  //最高价
    public   float  low;   //最低价
    public   float  open;  //开盘价
    public   float  close;  //收盘价
    public   int    volume;   //成交量
    public   int    volumeMoney;  //成交价
    public   String date;      //成交日期
    public   float xValue;   //平均价
    public   Boolean isRise;  //是否升降


    public CandleEntry(float high, float low, float open, float close, int volume, int volumeMoney, String date, float xValue) {
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
        this.volume = volume;
        this.volumeMoney = volumeMoney;
        this.date = date;
        this.xValue = xValue;
    }
}
