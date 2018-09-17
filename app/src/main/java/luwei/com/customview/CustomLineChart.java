package luwei.com.customview;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author   : luweicheng on 2018/6/25 10:24
 * E-mail   ：1769005961@qq.com
 * GitHub   : https://github.com/luweicheng24
 * function:
 **/

public class CustomLineChart extends View {
    private Path xPath;
    private Path yPath;
    private Paint mPaint;
    private Paint chartPaint;

    private static final int width = 1000;
    private static final int height = 1000;
    private static final int originX = 100;
    private static final int originY = 900;
    private static final int endX = 900;
    private static final int endY = 900;
    private int scale = 30;
    private ValueAnimator animator;

    public CustomLineChart(Context context) {
        super(context);
    }

    public CustomLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
        xPath = new Path();
        yPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);
        chartPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        chartPaint.setColor(Color.RED);
        chartPaint.setStrokeWidth(13);

    }

    private boolean hasInit = false;
    private float[] chart = new float[(endX - 100) / 50 - 1];
    private float[] temp = new float[(endX - 100) / 50 - 1];

    @SuppressLint("CheckResult")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        xPath.moveTo(originX, originY);
        xPath.lineTo(endX, endY);
        xPath.lineTo(endX - 20, endY + 20);
        canvas.drawPath(xPath, mPaint);
        yPath.moveTo(originX, originY);
        yPath.lineTo(originX, 100);
        yPath.lineTo(80, 120);
        canvas.drawPath(yPath, mPaint);
        for (int i = 0; i < (endX - 100) / 50 - 1; i++) {
            canvas.drawLine(100 + 50 * (i + 1), originY, 100 + 50 * (i + 1), originY - 10, mPaint);
        }
        for (int i = 0; i < (endX - 100) / 50 - 1&&!hasInit; i++) {
            chart[i] = (float) (Math.random() * 400 + 100);
            temp[i] = chart[i];
        }
        System.out.println(" init  chart["+0+"] = "+chart[0]);

        for (int i = 0; i < (endY - 100) / 50 - 1; i++) {
            canvas.drawLine(100, originY - 50 * (i + 1), 110, originY - 50 * (i + 1), mPaint);
        }
        for (int i = 0; i < chart.length&&hasInit; i++) {
            canvas.drawLine(100 + 50 * (i + 1), originY, 100 + 50 * (i + 1), chart[i], chartPaint);
        }
        if (hasInit) {
            return;
        }
        Observable.intervalRange(0, 100, 1000, 50, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Double>() {
                    @Override
                    public Double apply(Long aLong) throws Exception {
                        return aLong.doubleValue();
                    }
                })
                .subscribe(new Consumer<Double>() {
                    @Override
                    public void accept(Double aLong) throws Exception {
                        double frac = aLong / 100;
                       // Log.e("luwei", "accept: " + frac);
                        for (int i = 0; i < chart.length; i++) {
                            chart[i] = originY - (float) (temp[i] * frac);
                            if(i==0){
                                System.out.println(temp[i]+" * "+frac+"="+temp[i]*frac);
                            }
                        }
                        //System.out.println(chart[0]);
                        invalidate();

                    }
                });

        hasInit = true;
    }


}
