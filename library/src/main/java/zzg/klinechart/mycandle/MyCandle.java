package zzg.klinechart.mycandle;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * author by chenji on 2019/3/15
 */
public class MyCandle extends RecyclerView {
    public Paint  mPaint;         //矩形画笔
    public Paint  linePaint;         //矩形画笔
    public RectF  candleRectF;   //k线图的矩形图
    public RectF  volumeRectF;   //成交量的矩形图
    private float contentMinOffset;
    int screenWidth;
    int screenhight;


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
               canvas.drawLine(candleRectF.left,candleRectF.top+(candleRectF.bottom-candleRectF.top)/3,candleRectF.right,candleRectF.top+(candleRectF.bottom-candleRectF.top)/3,linePaint);
               canvas.drawLine(candleRectF.left,candleRectF.top+(candleRectF.bottom-candleRectF.top)*2/3,candleRectF.right,candleRectF.top+(candleRectF.bottom-candleRectF.top)*2/3,linePaint);
    }

    //实例化布局
    protected  void init(Context context){
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


        contentMinOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, context.getResources().getDisplayMetrics());
        screenWidth=getScreenWidth();
        screenhight=getScreenHight();  //屏幕的高度
        candleRectF.set(screenWidth/8,screenhight*2/8,screenWidth*7/8,screenhight*4/10);
        volumeRectF.set(screenWidth/8,screenhight*4/10+20,screenWidth*7/8,screenhight*1/2);



        Log.i("width:",String.valueOf(screenWidth));
        Log.i("height:",String.valueOf(screenhight));
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
