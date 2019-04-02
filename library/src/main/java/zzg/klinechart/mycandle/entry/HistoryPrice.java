package zzg.klinechart.mycandle.entry;


/**
 * author by chenji on 2019/4/2
 * 最高价
 * 最低价
 * 历史价格,最高点最低点
 */
public class HistoryPrice {
    public  float topPrice;   //最高价
    public  float bottomPrice;  //最低价


    //历史k线走势
    public HistoryPrice(float topPrice, float bottomPrice){
        this.topPrice=topPrice;
        this.bottomPrice=bottomPrice;
    }

    public float getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(float topPrice) {
        this.topPrice = topPrice;
    }

    public float getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(float bottomPrice) {
        this.bottomPrice = bottomPrice;
    }












}
