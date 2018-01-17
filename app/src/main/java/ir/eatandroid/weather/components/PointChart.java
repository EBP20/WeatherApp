package ir.eatandroid.weather.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import ir.oxima.weatherapp.R;

/**
 * Created by elham on 12/13/2017.
 */

public class PointChart extends View {

    private Paint paint;
    private Paint paintText;
    private int height;
    private int wight;
    private int centerX;
    private int centerY;
    private int mColor;
    private float mTextSize;
    private float mRadius;
    private float P_Y;
    private float P_Ymax;
    private float min_Y;
    private int textBaseLine;

    public PointChart(Context context) {
        super(context);
        init(context, null);
    }

    public PointChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PointChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttributes(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(mColor);

        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setColor(mColor);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextSize(mTextSize);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        wight = getWidth() - getPaddingLeft() - getPaddingRight();
        height = getHeight() - getPaddingBottom() - getPaddingTop();
        centerX = wight / 2;
        centerY = height / 2;
        textBaseLine = (int) ((paintText.descent() + paintText.ascent()) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawView(canvas);
    }

    private void drawView(Canvas canvas) {

        float mHeight = height - (4 * mRadius);
        P_Y = P_Y + min_Y;
        P_Ymax = P_Ymax + min_Y;
        float cY = mHeight - (((P_Y / P_Ymax) * mHeight));
        canvas.drawCircle(centerX, cY, mRadius, paint);
        canvas.drawText(P_Y + "c", centerX, cY + (2*textBaseLine), paintText);

    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = getTypedArray(context, attributeSet, R.styleable.PointChart);
        if (attr == null) {
            return;
        }
        try {
            mColor = attr.getColor(R.styleable.PointChart_pc_color, Color.parseColor("#6aa0ce"));
            mRadius = attr.getDimension(R.styleable.PointChart_pc_redius, 8);
            P_Y = attr.getInteger(R.styleable.PointChart_pc_Y, 0);
            P_Ymax = attr.getInteger(R.styleable.PointChart_pc_Ymax, 0);
            mTextSize = attr.getDimension(R.styleable.PointChart_pc_text_size, 14);

        } finally {
            attr.recycle();
        }
    }

    protected int getColor(int id) {
        return getResources().getColor(id);
    }

    protected TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    public void setY(float value) {
        P_Y = value ;
        invalidate();
    }

    public void setYmax(float value) {
        P_Ymax = value + 10 ;
        invalidate();
    }
    public void setMinY(final float value) {
        this.min_Y = (int) value;
        min_Y = value >= 0 ? 0 : (-1) * value;
        invalidate();
    }
    public static float density = 1;

    public static int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }
}

