package zzg.klinechart.mycandle.util;
import android.graphics.RectF;
import zzg.klinechart.mycandle.entry.CandleEntry;
import zzg.klinechart.mycandle.entry.HistoryPrice;

/**
 * author by chenji on 2019-04-01
 * 坐标工具
 */
public class CoordinateUtil {

    //坐标工具方法
    public static Float coordinateUtil(Float x, Float y, Float z) {
        Float goal;
        goal = (Float) y * z / x;
        return goal;
    }

    //获取k线矩形位置的位置坐标
    public static RectF getRectf(HistoryPrice historyPrice, CandleEntry candleEntry, RectF baseRecf,int i) {
        Float left;
        Float right;
        Float top;
        Float bottom;
        left = i*(baseRecf.right-baseRecf.left)/30+getMaxPrice(candleEntry.getOpen(),candleEntry.getClose()) / historyPrice.getTopPrice() * baseRecf.left +(i+1)*10;
        right = left + (baseRecf.right-baseRecf.left)/30;
        top = (historyPrice.getTopPrice() / getMaxPrice(candleEntry.getOpen(),candleEntry.getClose())) * baseRecf.top;
        bottom =  (historyPrice.getBottomPrice() / getMinPrice(candleEntry.getOpen(),candleEntry.getClose())) * baseRecf.bottom;
        return new RectF(left, top, right, bottom);
    }

    //获取k线数组最高点，最低点坐标
    public static float[] getkine(HistoryPrice historyPrice, CandleEntry candleEntry, RectF baseRecf,int i) {
        float[] kLine=new float[4];
        Float left;
        Float right;
        Float top;
        Float bottom;
        left = i*(baseRecf.right-baseRecf.left)/30+getMaxPrice(candleEntry.getOpen(),candleEntry.getClose()) / historyPrice.getTopPrice() * baseRecf.left +(i+1)*10;
        right = left + (baseRecf.right-baseRecf.left)/30;
        top = (historyPrice.getTopPrice() /candleEntry.getHigh()) * baseRecf.top;
        bottom =  (historyPrice.getBottomPrice() / candleEntry.getLow()) * baseRecf.bottom;
        kLine[0]=left+(right-left)/2;
        kLine[1]=top;
        kLine[2]=left+(right-left)/2;
        kLine[3]=bottom;
        return kLine;
    }

    //绘制成交量矩形
    public static RectF getVolumeRectf(HistoryPrice historyPrice, CandleEntry candleEntry, RectF baseRecf,int i) {
        Float left;
        Float right;
        Float top;
        Float bottom;
        left =i*(baseRecf.right-baseRecf.left)/30+getMaxPrice(candleEntry.getOpen(),candleEntry.getClose()) / historyPrice.getTopPrice() * baseRecf.left +(i+1)*10;
        right =left + (baseRecf.right-baseRecf.left)/30;
        top =baseRecf.bottom-candleEntry.getVolume()/historyPrice.getMaxVolumn() * (baseRecf.bottom-baseRecf.top);
        bottom =baseRecf.bottom-1;//留出5像素的距离
        return new RectF(left, top, right, bottom);
    }

    //获取较大数
    private  static float getMaxPrice(float one,float two){
        if (one - two > 0) {
            return one;
        } else {
            return   two;
        }
    }


    //获取较大数
    private  static float getMinPrice(float one,float two) {
        if (one - two <0) {
            return one;
        } else {
            return two;
        }
    }

}




