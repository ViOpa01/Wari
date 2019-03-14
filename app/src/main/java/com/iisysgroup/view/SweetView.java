package com.iisysgroup.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.iisysgroup.newlandtestapp.R;


public class SweetView extends View implements Runnable {

    private Paint mPaint = new Paint();
    private RectF mRectF = null;
    private int startAngle = 0;
    private int sweepAngle = 0;
    private boolean issweep = true;
    private int colorItem = 0;
    private int defWidth = 0;
    private int defHeight = 0;
    private int maign = 10;
    private boolean isShow = true;
    private Thread thread;

    private int colorArray[] = new int[]{R.color.sweet_color_1,
            R.color.sweet_color_2, R.color.sweet_color_3,
            R.color.sweet_color_4, R.color.sweet_color_5, R.color.sweet_color_6};

    public SweetView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initView();
    }

    public SweetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initView();


    }

    public SweetView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initView();

    }

    public void show() {
        isShow = true;
        invalidate();
//        System.out.println(" show()---->");
    }

    private void initView() {

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(getResources().getColor(colorArray[colorItem]));
        setBackgroundColor(getResources().getColor(R.color.tran_white_0));
        thread = new Thread(this);
        thread.start();

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, mPaint);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
//        System.out.println("----->isShow-->" + isShow);
        while (isShow) {
//            System.out.println("----->issweep-->" + issweep);
            updateSweet();
        }
    }

    private synchronized void updateSweet() {
        try {
            if (issweep) {
                if (sweepAngle < 320) {
                    sweepAngle += 20;
                    startAngle += 10;
                } else {
                    issweep = false;
                }
            } else {
                if (sweepAngle > 20) {
                    startAngle += 30;
                    sweepAngle -= 20;
                } else {
                    issweep = true;
                    colorItem++;

                    if (colorArray.length - colorItem <= 0) {
                        colorItem = 0;
                    }
                    mPaint.setColor(getResources().getColor(colorArray[colorItem]));
                }
            }
            startAngle %= 360;
            Thread.sleep(50);
            postInvalidate();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int measuredHeight = measureHeight(heightMeasureSpec);
        int measuredWidth = measureWidth(widthMeasureSpec);
        defWidth = measuredWidth;
        defHeight = measuredHeight;
        int left = maign;
        int top = maign;
        int right = defWidth - maign;
        int bottom = defHeight - maign;
        mRectF = new RectF(left, top, right, bottom);
        setMeasuredDimension(measuredHeight, measuredWidth);
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        // ���û������Ĭ�ϴ�С��ָ����.
        int result = 100;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDetachedFromWindow() {
        // TODO Auto-generated method stub
        System.out.println("onDetachedFromWindow");
//        try {
//            System.out.println("线程暂停......");
//            thread.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        super.onDetachedFromWindow();
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = 100;
        if (specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

}
