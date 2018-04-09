package com.jaydenxiao.common.commonwidget;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.jaydenxiao.common.R;
/**
 * Created by HOREN on 2018/2/7.
 *
 * 水滴进度条实现可定制化
 * water_progressbar_width 进度条宽度既水滴的高
 * water_progressbar_start 进度条开始的角度 0 从正上方开始
 * water_progressbar_count 进度条水滴数
 * water_progressbar_bg    进度条背景图片
 * water_progressbar_bg_img 进度条图片
 *
 */
public class WaterProgressBar extends View{
    // Properties
    private float progress;
    private int count;
    private float strokeWidth = getResources().getDimension(R.dimen.margin_20);
    private int startAngle = 0;
    private Paint backgroundPaint;
    private Bitmap bitmapBackground;
    private Bitmap bitmap;
    private int x,y,distance;
    public WaterProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WaterProgressBar, 0, 0);
        try {
            strokeWidth = typedArray.getDimension(R.styleable.WaterProgressBar_water_progressbar_width, strokeWidth);
            startAngle = typedArray.getInt(R.styleable.WaterProgressBar_water_progressbar_start,startAngle);
            count = typedArray.getInt(R.styleable.WaterProgressBar_water_progressbar_count,24);
            int bg = typedArray.getResourceId(R.styleable.WaterProgressBar_water_progressbar_bg,R.drawable.ic_water_prob);
            int bgImg = typedArray.getResourceId(R.styleable.WaterProgressBar_water_progressbar_bg_img,R.drawable.ic_water_pro);

            //初始化进度条图片
            bitmapBackground =  BitmapFactory.decodeResource(getResources(),bg);
            bitmap =  BitmapFactory.decodeResource(getResources(),bgImg);
        } finally {
            typedArray.recycle();
        }

        //普通Paint即可
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setFilterBitmap(false);
        backgroundPaint.setDither(false);

    }
    //endregion
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        x = width / 2;
        y = height / 2;//设置进度条中心位置

        distance = x-39;//为进度条预留画布位置
    }

    //region Draw Method
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width1 = bitmapBackground.getWidth();
        int height1 = bitmapBackground.getHeight();
        float f1 = strokeWidth/height1;//获取缩放比
        float c = 360/count;//计算每个水滴之间的角度
        for (int i = 0;i<count;i++){//画一圈白底水滴
            float a = (float) (distance * Math.sin(i * c * Math.PI / 180) + x);//计算每个水滴的x坐标
            float b = (float) (y - distance * Math.cos(i * c * Math.PI / 180));//计算每个水滴的y坐标
            Matrix matrix1 = new Matrix();
            matrix1.postScale(f1, f1);//缩放图片 或者放大图片
            matrix1.postRotate(i * c);//旋转角度 顺时针
            Bitmap bmap = Bitmap.createBitmap(bitmapBackground, 0, 0, width1,height1, matrix1, false);
            canvas.drawBitmap(bmap,a-bmap.getWidth()/2,b-bmap.getHeight()/2,null);
        }

        int size;
        if (progress >= 100){//大于100显示24颗水滴
            size = count;
        } else if (progress > 96){//97,98,99不显示最后一颗水滴
            size = count-1;
        } else {
            float f = progress / 100 * count;
            if (f > 0 && f < 1){
                size = 1;
            } else {
                size = (int)f;//计算得出具体显示水滴数
            }
        }
//        foregroundPaint.setShader(mShader);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = strokeWidth/height;//获取缩放比
        for (int i = 0;i<size;i++){
            //(i * 15 + startAngle) 加180水滴从正下方开始滚动， 不加则从正上方开始滚动
            float a = (float) (distance * Math.sin((i * c + startAngle) * Math.PI / 180) + x);
            float b = (float) (y - distance * Math.cos((i * c + startAngle) * Math.PI / 180));
            Matrix matrix2 = new Matrix();
            matrix2.postScale(f, f);//图片缩小0.7倍
            matrix2.postRotate((i * c + startAngle));//图片旋转：参数为具体度数
            //createBitmap 得到经过Matrix处理的bitmap
            Bitmap bmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix2, false);
            canvas.drawBitmap(bmap,a-bmap.getWidth()/2,b-bmap.getHeight()/2,backgroundPaint);
            //a-bmap.getWidth()/2,b-bmap.getHeight()/2 将图片在自己位置居中
        }
        canvas.restore();
    }
    public float getProgress() {
        return progress;
    }
    public void setProgress(float progress) {
        this.progress = (progress<=100) ? progress : 100;
        invalidate();
    }
    /**
     * 外部调用此方法，进度条动画加载 传入百分比
     * @param progress
     * 传入 80 代表80%
     * */
    public void setProgressWithAnimation(float progress) {
        setProgressWithAnimation(progress, 800);
    }
    public void setProgressWithAnimation(float progress, int duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.start();
    }
}