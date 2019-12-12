package com.example.nannypoppins;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;
import android.content.Context;
import android.util.AttributeSet;

public class DrawingView extends View{

    private Path drawPath;
    private boolean erase = false;
    private Paint drawPaint, canvasPaint;
    private Canvas drawCanvas;
    private int paintColor = 0xFF660000;
    private Bitmap canvasBitmap;
    private float brushSize, lastBrushSize;

    public DrawingView(Context context, AttributeSet set) {
        super(context, set);
        setupDrawing();
    }

    public void startNew() {
        drawCanvas.drawColor(0xffffffff);
        invalidate();;
    }

    public void setErase(boolean isErase) {
        erase = isErase;
        if (erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPaint.setXfermode(null);
    }

    public void setBrushSize(float newSize) {
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize, getResources().getDisplayMetrics());
        brushSize = pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void setLastBrushSize(float lastSize) {
        lastBrushSize = lastSize;
    }

    public float getBrushSize() {
        return lastBrushSize;
    }

    @Override
    protected  void onSizeChanged(int w, int b, int oldw, int oldb) {
        super.onSizeChanged(w, b, oldw, oldb);
        canvasBitmap = Bitmap.createBitmap(w, b, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        drawCanvas.drawColor(0xffffffff);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    public void overlayCanvas(){

        Bitmap over = BitmapFactory.decodeResource(getResources(), R.drawable.turkey_paint);
        Bitmap image = Bitmap.createScaledBitmap(over, drawCanvas.getWidth(), drawCanvas.getHeight(), true);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        drawCanvas.drawBitmap(image, 0, 0, null);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setStrokeWidth(brushSize);
        invalidate();
        return true;
    }

    public void setColor(String newColor) {
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    public void setupDrawing() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        brushSize = 10;
        lastBrushSize = brushSize;
        drawPaint.setStrokeWidth(brushSize);
    }
}
