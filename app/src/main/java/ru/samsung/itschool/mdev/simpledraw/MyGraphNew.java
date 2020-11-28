package ru.samsung.itschool.mdev.simpledraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;


public class MyGraphNew extends View {

    int N = 10;
    int[] l = new int [N];
    double x0, y0;
    double[] x = new double [N];
    double[] y = new double [N];
    double g = 9.832f, pi = Math.PI;
    double[] w = new double[N];
    double fi0;
    double[] fi = new double[N];
    int t = 0, deltaT = 1;
    int[] RED = new int[N];
    int[] GREEN = new int[N];
    int[] BLUE = new int[N];
    int[] rad = new int[N];

    void makePendulum()
    {
        fi0 = pi/2;

        int l_min = 100;
        for (int i = 0; i<N; i++)
        {
            l[i] = l_min;
            l_min += 100;

            fillArrayRandom(RED, 0, 255);
            fillArrayRandom(GREEN, 0, 255);
            fillArrayRandom(BLUE, 0, 255);

            fillArrayRandom(rad, 10, 30);

            w[i] = Math.sqrt(g/l[i]);
        }
    }
    void movePendulum()
    {
        t += deltaT;

        for (int i = 0; i<N; i++)
        {
            fi[i] = fi0 * Math.cos(w[i] * t);
            x[i] = l[i]*Math.sin(fi[i]);
            y[i] = l[i]*Math.cos(fi[i]);
        }
    }

    MyGraphNew(Context context) {
        super(context);
        makePendulum();
        MyTimer timer = new MyTimer();
        timer.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        x0 = getWidth()/2;
        y0 = getHeight()/4;
        Paint paint = new Paint();
        canvas.drawCircle((float) x0, (float) y0, 10, paint);
        for (int i = 0; i<N; i++)
        {
            paint.setColor(Color.argb(255, 255 - RED[i], 255 - GREEN[i], 255 - BLUE[i]));
            canvas.drawLine((float)x0, (float)y0, (float)(x[i] + x0), (float)(y[i]+ y0), paint);
            paint.setColor(Color.argb(255, RED[i], GREEN[i], BLUE[i]));
            canvas.drawCircle((float)(x[i] + x0), (float)(y[i] + y0), rad[i], paint);
        }
    }

    void nextFrame()
    {
        movePendulum();
        invalidate();
    }

    void fillArrayRandom(int[] a, int min, int max) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
    }

    class MyTimer extends CountDownTimer
    {
        MyTimer()
        {
            super(100000, 32);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            nextFrame();
        }
        @Override
        public void onFinish() {
        }
    }
}