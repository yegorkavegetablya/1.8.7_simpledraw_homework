package ru.samsung.itschool.mdev.simpledraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import ru.samsung.itschool.mdev.simpledraw.task.Task;

public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        int x = 0, y, h = canvas.getWidth(), w = canvas.getWidth();
        y = h;
        // рисование круга при помощи линий
        while (y >= 0) {
            y -= 50;
            canvas.drawLine(0, y, Math.min(y, w), h, paint);
        }
        // диагональные полосы
        while (x <= w - 50) {
            x += 50;
            canvas.drawLine(x, 0, (w - x < h) ? (w) : (x + h), Math.min(w - x, h), paint);
        }
    }
}
