package com.uniquext.android.drinkwater.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.uniquext.android.drinkwater.R;

/**
 * @author penghaitao
 * @version 1.0
 * @date 2018/10/20 10:00
 * @description
 */
public class RingView extends View {

    private static final float START_ANGLE = -90;

    private int mOutColor;
    private int mInsideColor;
    private int mRingBackgroundColor;
    private float mPercent = 0;
    private float mOutRadius = 0;
    private float mInsideRadius = 0;
    private Paint mPaint = new Paint();
    private RectF mOuterRectF = new RectF();
    private RectF mInsideRectF = new RectF();

    public RingView(Context context) {
        this(context, null);
    }

    public RingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initStyleable(attrs);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float centerX = w * 0.5f;
        float centerY = h * 0.5f;
        mOuterRectF.set(centerX - mOutRadius, centerY - mOutRadius,
                centerX + mOutRadius, centerY + mOutRadius);
        mInsideRectF.set(centerX - mInsideRadius, centerY - mInsideRadius,
                centerX + mInsideRadius, centerY + mInsideRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float sweepAngle = mPercent * 3.6f;
        mPaint.setColor(mOutColor);
        canvas.drawArc(mOuterRectF, START_ANGLE, sweepAngle, true, mPaint);
        mPaint.setColor(mRingBackgroundColor);
        float startAngle = START_ANGLE + sweepAngle;
        canvas.drawArc(mOuterRectF, startAngle, 360 - sweepAngle, true, mPaint);
        mPaint.setColor(mInsideColor);
        canvas.drawArc(mInsideRectF, 0, 360, true, mPaint);
    }

    private void initStyleable(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.RingView);
        mPercent = typedArray.getFloat(R.styleable.RingView_percent, 0);
        mOutRadius = typedArray.getDimension(R.styleable.RingView_outRadius, mOutRadius);
        mInsideRadius = typedArray.getDimension(R.styleable.RingView_insideRadius, 0);
        mInsideColor = typedArray.getColor(
                R.styleable.RingView_insideColor, getResources().getColor(R.color.colorAccent));
        mOutColor = typedArray.getColor(
                R.styleable.RingView_outColor, getResources().getColor(R.color.colorPrimary));
        mRingBackgroundColor = typedArray.getColor(
                R.styleable.RingView_ringBackgroundColor, getResources().getColor(R.color.colorPrimaryDark));
        typedArray.recycle();
    }

}
