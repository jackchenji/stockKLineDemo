package zzg.klinechart.mycandle.entry;

/**
 * author by chenji on 2019/3/21
 */
public class CandleEntry {
    public   float  high;  //最高价
    public   float  low;   //最低价
    public   float  open;  //开盘价
    public   float  close;  //收盘价
    public   float    volume;   //成交量
    public   float    volumeMoney;  //成交价
    public   String date;      //成交日期
    public   float xValue;   //平均价
    public   Boolean isRise;  //是否升降


    public CandleEntry(float high, float low, float open, float close, float volume, float volumeMoney, String date, float xValue) {
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
        this.volume = volume;
        this.volumeMoney = volumeMoney;
        this.date = date;
        this.xValue = xValue;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public float getVolumeMoney() {
        return volumeMoney;
    }

    public void setVolumeMoney(int volumeMoney) {
        this.volumeMoney = volumeMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getxValue() {
        return xValue;
    }

    public void setxValue(float xValue) {
        this.xValue = xValue;
    }

    public Boolean getRise() {
        return isRise;
    }

    public void setRise(Boolean rise) {
        isRise = rise;
    }







}
