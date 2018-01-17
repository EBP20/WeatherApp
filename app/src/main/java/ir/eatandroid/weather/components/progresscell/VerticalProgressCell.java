package ir.eatandroid.weather.components.progresscell;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;



/**
 * Created by elham on 9/9/17.
 */

public class VerticalProgressCell extends View {


    private Paint paint;
    private Paint paintProgress;
    private Paint paintText;
    private int height;
    private float progress;
    private float max_progress;
    private float min_progress;
    private int wight;
    private float progress_width;
    private int centerX;
    private int centerY;
    private RectF rectF;
    private RectF rectFProgress;
    private int mProgrssColor;
    private int mBackgroundColor;
    private int mTextColor;
    private float mTextSize;
    private int textBaseLine;
    private float p_height;
    private String mText = "0 %";
    private IProgressCell iProgressCell;
    public static float density = 1;

    public interface IProgressCell {
        void onListenChangeProgress(int progress);
    }

    public void setListener(IProgressCell iProgressCell) {
        this.iProgressCell = iProgressCell;
    }

    public VerticalProgressCell(Context context) {
        super(context);
        init(context, null);
    }

    public VerticalProgressCell(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public VerticalProgressCell(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
//        initAttributes(context, attrs);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mBackgroundColor);

        paintProgress = new Paint();
        paintProgress.setAntiAlias(true);
        paintProgress.setStyle(Paint.Style.FILL);


        paintText = new Paint();
        paintText.setAntiAlias(true);
        paintText.setColor(mTextColor);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextSize(mTextSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        wight = getWidth() - getPaddingLeft() - getPaddingRight();
        height = getHeight() - getPaddingBottom() - getPaddingTop();
        mText = progress + "c";
        p_height = (progress / max_progress) * (height);

        centerX = wight / 2;
        centerY = height / 2;

        rectF = new RectF(wight, 0, wight, height);
        textBaseLine = (int) ((paintText.descent() + paintText.ascent()) / 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawView(canvas);
    }

    private void drawView(Canvas canvas) {
        wight = getWidth() - getPaddingLeft() - getPaddingRight();
        height = getHeight() - getPaddingBottom() - getPaddingTop();
        paintProgress.setColor(mProgrssColor);

        mText = progress + "c";

        progress = progress + min_progress;
        max_progress = max_progress + min_progress;

        p_height = ((progress == 0 ? 1 : progress) / max_progress) * (height);
        canvas.drawRect(rectF,paint);
        rectFProgress = new RectF(centerX - progress_width / 2 , 0, progress_width / 2 + centerX, p_height);
        canvas.drawRect(rectFProgress,paintProgress);
        canvas.drawText(mText,centerX , p_height + dp(35), paintText);

    }

//    private void initAttributes(Context context, AttributeSet attributeSet) {
//        TypedArray attr = getTypedArray(context, attributeSet, R.styleable.ProgressCell);
//        if (attr == null) {
//            return;
//        }
//        try {
//            mBackgroundColor = attr.getColor(R.styleable.ProgressCell_pc_background, 0);
//            mProgrssColor = attr.getColor(R.styleable.ProgressCell_pc_progresstint, Color.parseColor("#ffffff"));
//            mTextColor = attr.getColor(R.styleable.ProgressCell_pc_textcolor, Color.parseColor("#ffffff"));
//            mTextSize = attr.getDimension(R.styleable.ProgressCell_pc_text_size, 20);
//            progress = attr.getDimension(R.styleable.ProgressCell_pc_progress, 0);
//            max_progress = attr.getDimension(R.styleable.ProgressCell_pc_progress, 100);
//            progress_width = attr.getDimension(R.styleable.ProgressCell_pc_width, 10);
//

//        } finally {
//            attr.recycle();
//        }
//    }

    protected int getColor(int id) {
        return getResources().getColor(id);
    }

    protected TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    public void setProgress(final float value) {
        progress = (int) value;
        if(progress == 0){
            mProgrssColor = Color.parseColor("#f5f5f5");
        }else if(progress < 0){
            mProgrssColor = Color.parseColor("#a3d0ff");
        }else{
            mProgrssColor = Color.parseColor("#ffa3a9");

        }
        invalidate();
    }

    public void setMaxProgress(final float progress) {
        this.max_progress = (int) progress + 10;
        invalidate();
    }

    public void setMinProgress(final float progress) {
        this.min_progress = (int) progress;
        min_progress = min_progress >= 0 ? 0 : (-1) * min_progress;
        invalidate();
    }

    public static int dp(float value) {
        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }
}
