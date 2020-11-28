package ru.samsung.itschool.mdev.simpledraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import ru.samsung.itschool.mdev.simpledraw.task.Task;

public class MyViewOld extends View {
    int N = 10; // количество шариков
    float[] x  = new float[N];
    float[] y  = new float[N];
    float[] vx = new float[N];
    float[] vy = new float[N];
    boolean started = false;

    public MyViewOld(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!started){
            // код заполнения массивов
            fillRandom(x, 0, canvas.getWidth());
            fillRandom(y, 0, canvas.getHeight());
            fillRandom(vx, -3, 3);
            fillRandom(vy, -3, -3);

            started = true;
        }

        drawBalls(canvas, new Paint());

        // готовим массивы x и у для следущего кадра
        addValues(x, vx);
        addValues(y, vy);
        //запрашиваем перерисовку
        invalidate();
    }

    void addValues(float[] array , float[] values){
        for (int i = 0; i < array.length; i++){
            array[i] += values[i];
        }
    }

    void fillRandom(float[] array , float min, float max){
        for (int i = 0; i < array.length; i++){
            array[i] = rand(min, max);
        }
    }

    float rand(float min , float max){
        return (float)(Math.random() * (max - min + 1)) + min;
    }

    void drawBalls(Canvas canvas, Paint paint) {
        // отрисовываем все шарики
        for (int i = 0; i < N; i++) {
            canvas.drawCircle(x[i], y[i], 20, paint);
        }
    }
}