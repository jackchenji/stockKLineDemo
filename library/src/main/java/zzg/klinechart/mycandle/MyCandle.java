package zzg.klinechart.mycandle;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.List;

import zzg.klinechart.internal.EntryData;
import zzg.klinechart.mycandle.entry.CandleEntry;
import zzg.klinechart.mycandle.entry.HistoryPrice;
import zzg.klinechart.mycandle.util.CoordinateUtil;

/**
 * author by chenji on 2019/3/15
 */
public class MyCandle extends RecyclerView {
    public Paint  mPaint;         //矩形画笔
    public Paint  linePaint;         //线画笔
    public Paint   textPaint;
    public RectF  candleRectF;   //k线图的矩形图
    public RectF  volumeRectF;   //成交量的矩形图
    private float contentMinOffset;
    private Path  mPath1;      //运动路径
    private Path  mPath2;      //运动路径
    public  HistoryPrice   historyPrice;   //历史最高 和历史最低
    public  Paint  redKPaint;  //红色k线画笔
    public  Paint  greenKPaint;  //绿色k线画笔
    public  RectF  lastRectf;//上一个矩形框

    int screenWidth;
    int screenhight;


    int kWidth=100;   //定义k线的宽度为100个像素
    int kSpace=10;  //定义k线横向距离
    public  List<CandleEntry>  data;   //k线数据


    public MyCandle(Context context) {
        super(context);
        init(context);

    }


    public MyCandle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public MyCandle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //设置数据源
    public   void   setData(List<CandleEntry> data){
        this.data=data;
    }

    //通知它数据改变了
    public  void notifyDataChanged(Boolean isInvalid){


    }


    //自定义控件的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        Log.i("onMeasure：","onMeasure");
    }


    //位置坐标
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i("onLayout：","onLayout");
    }


    //当尺寸发生变化的时候 触发这个方法
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("onSizeChanged：","width："+w+"hight"+h+"原来的宽:"+oldw+"原来的高:"+oldh);
    }


    //重新绘制
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
           //   canvas.drawText("hello",0,0,mPaint);
         //    canvas.drawCircle(220,220,50,mPaint);
               canvas.drawRect(candleRectF,mPaint); //k线图的矩形框
               canvas.drawRect(volumeRectF,mPaint); //成交量的矩形框

            //canvas.drawLine(candleRectF.left,candleRectF.top+(candleRectF.bottom-candleRectF.top)/3,candleRectF.right,candleRectF.top+(candleRectF.bottom-candleRectF.top)/3,linePaint);
             //canvas.drawLine(candleRectF.left,candleRectF.top+(candleRectF.bottom-candleRectF.top)*2/3,candleRectF.right,candleRectF.top+(candleRectF.bottom-candleRectF.top)*2/3,linePaint);
               canvas.drawText(String.valueOf(historyPrice.getTopPrice()),candleRectF.left-65,candleRectF.top-5,textPaint); //坐标图最高点
               canvas.drawText(String.valueOf((historyPrice.getTopPrice()-historyPrice.getBottomPrice())/2+historyPrice.getBottomPrice()),candleRectF.left-65,candleRectF.top+(candleRectF.bottom-candleRectF.top)/3+5,textPaint); //坐标图第二高点
               canvas.drawText(String.valueOf((historyPrice.getTopPrice()-historyPrice.getBottomPrice())/4+historyPrice.getBottomPrice()),candleRectF.left-65,candleRectF.top+(candleRectF.bottom-candleRectF.top)*2/3+5,textPaint); //坐标图第三高点
               canvas.drawText(String.valueOf(historyPrice.getBottomPrice()),candleRectF.left-65,candleRectF.bottom+10,textPaint); //坐标图最低点



// 绘制虚线开始
DashPathEffect dashPathEffect1 = new DashPathEffect(new float[]{10, 10}, 0); //数组第一个参数长度实线的长度，数组第二个参数虚线空间的长度
mPaint.setPathEffect(dashPathEffect1);
mPath1.reset();
mPath1.moveTo(candleRectF.left, candleRectF.top+(candleRectF.bottom-candleRectF.top)/3);  //虚线的起点
mPath1.lineTo(candleRectF.right, candleRectF.top+(candleRectF.bottom-candleRectF.top)/3); //虚线的终点
canvas.drawPath(mPath1, mPaint);

 mPath2.reset();
 mPath2.moveTo(candleRectF.left, candleRectF.top+(candleRectF.bottom-candleRectF.top)*2/3);  //虚线的起点
 mPath2.lineTo(candleRectF.right, candleRectF.top+(candleRectF.bottom-candleRectF.top)*2/3); //虚线的终点
 canvas.drawPath(mPath2, mPaint);

          //绘制虚线结束
        //从这里开始绘制k线,先画第一根k线
        //要定义k线的宽和高
        //还有k线的位置



        for(int i=0;i<data.size();i++){
            //canvas.drawRect(,redKPaint);
            RectF rectF=CoordinateUtil.getRectf(historyPrice,data.get(i),candleRectF,i);
            float[] kLine=CoordinateUtil.getkine(historyPrice,data.get(i),candleRectF,i);
            if(data.get(i).getOpen()<data.get(i).getClose()){
            canvas.drawRect(rectF,redKPaint);
            canvas.drawLines(kLine,redKPaint);}else{
                canvas.drawRect(rectF,greenKPaint);
                canvas.drawLines(kLine,greenKPaint);
            }
        }


    }

    //实例化布局
    protected  void init(Context context){
        historyPrice=new HistoryPrice(3100,2900);    //历史价格


        candleRectF=new RectF();
        volumeRectF=new RectF();
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);  //设置矩形没有颜色，不填充实体
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(1);


        linePaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(1);



        //画笔的属性
        textPaint=new Paint();
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(Color.RED);
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(20);


        //红色k线画笔
        redKPaint=new Paint();
        redKPaint.setStyle(Paint.Style.FILL);
        redKPaint.setColor(Color.RED);
        redKPaint.setStrokeWidth(3);
        redKPaint.setTextSize(20);


        //绿色k线画笔  start
        greenKPaint=new Paint();
        greenKPaint.setStyle(Paint.Style.FILL);
        greenKPaint.setColor(Color.GREEN);
        greenKPaint.setStrokeWidth(3);
        greenKPaint.setTextSize(20);
        //end


        contentMinOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, context.getResources().getDisplayMetrics());
        screenWidth=getScreenWidth();
        screenhight=getScreenHight();  //屏幕的高度
        candleRectF.set(screenWidth/8,screenhight*2/8,screenWidth*7/8,screenhight*4/10);
        volumeRectF.set(screenWidth/8,screenhight*4/10+20,screenWidth*7/8,screenhight*1/2);



        mPath1=new Path();
        mPath2=new Path();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context);

        Log.i("width:",String.valueOf(screenWidth));
        Log.i("height:",String.valueOf(screenhight));
        lastRectf=candleRectF;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    /**
     * 得到屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 得到屏幕高度
     *
     * @return
     */
    private int getScreenHight(){
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }



}
