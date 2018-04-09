package com.jaydenxiao.common.commonwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.commonutils.DisplayUtil;


public class CustomTitleBar extends RelativeLayout {

    private Toolbar toolbar;
    private ImageView ivRight, ivLeft;
    private TextView tvTitle, tvRight, tvLeft;
    private Context context;
    private String title;
    private String leftTitle;
    private String rightTitle;
    /**
     * 是否是白色背景Toolbar
     */
    private boolean isWhite = false;
    /**
     * 默认2dp阴影
     */
    private int elevation = 2;

    public CustomTitleBar(Context context) {
        this(context, null);
        this.context = context;
    }

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setUp(attrs);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 高于5.0版本系统
//            setElevation(DisplayUtil.dip2px(2));
//        }
    }


    /**
     * 设置属性
     *
     * @param attrs
     */
    private void setUp(AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.CustomTitleBar, 0, 0);
        try {
            title = a.getString(R.styleable.CustomTitleBar_title);
            leftTitle = a.getString(R.styleable.CustomTitleBar_left_title);
            rightTitle = a.getString(R.styleable.CustomTitleBar_right_title);
            elevation = a.getInteger(R.styleable.CustomTitleBar_title_elevation, 2);
            isWhite = a.getBoolean(R.styleable.CustomTitleBar_is_white, false);
        } finally {
            a.recycle();
        }
        if (isWhite) {
            setBackgroundColor(Color.WHITE); // setElevation生效必须设置背景，且不能为透明
            View.inflate(context, R.layout.custom_toolbar_layout_black, this);
            toolbar = (Toolbar) findViewById(R.id.tool_bar_balck);
            tvTitle = (TextView) findViewById(R.id.tool_bar_title_tv_balck);
            tvRight = (TextView) findViewById(R.id.right_tv_balck);
            tvLeft = (TextView) findViewById(R.id.left_tv_balck);
            ivRight = (ImageView) findViewById(R.id.right_iv_balck);
            ivLeft = (ImageView) findViewById(R.id.left_iv_balck);

        } else {
            setBackgroundColor(context.getResources().getColor(R.color.color_main_color)); // setElevation生效必须设置背景，且不能为透明
            View.inflate(context, R.layout.custom_toolbar_layout, this);
            toolbar = (Toolbar) findViewById(R.id.tool_bar_white);
            tvTitle = (TextView) findViewById(R.id.tool_bar_title_tv_white);
            tvRight = (TextView) findViewById(R.id.right_tv_white);
            tvLeft = (TextView) findViewById(R.id.left_tv_white);
            ivRight = (ImageView) findViewById(R.id.right_iv_white);
            ivLeft = (ImageView) findViewById(R.id.left_iv_white);
        }
        // 阴影，默认是2dp
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 高于5.0版本系统
            setElevation(DisplayUtil.dip2px(elevation));
        }

        tvTitle.setText(title);
        tvRight.setText(rightTitle);
        tvLeft.setText(leftTitle);
    }

    /**
     * 设置标题栏左侧字符串
     *
     * @param tvLeftText
     */
    public void setTvLeft(String tvLeftText) {
        tvLeft.setVisibility(VISIBLE);
        tvLeft.setText(tvLeftText);
    }

    /**
     * 设置标题栏右侧字符串
     *
     * @param tvRightText
     */
    public void setTvRight(String tvRightText) {
        tvRight.setVisibility(VISIBLE);
        tvRight.setText(tvRightText);
    }


    /**
     * 管理标题
     */
    public void setTitleVisibility(boolean visible) {
        if (visible) {
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
    }

    public void setTitleText(String string) {
        tvTitle.setText(string);
    }

    public void setTitleText(int string) {
        tvTitle.setText(string);
    }

    public void setTitleColor(int color) {
        tvTitle.setTextColor(color);
    }

    /**
     * 右图标
     */
    public void setRightImagVisibility(boolean visible) {
        ivRight.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setRightImagSrc(int id) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(id);
    }

    /**
     * 获取右按钮
     *
     * @return
     */
    public View getRightImage() {
        return ivRight;
    }

    /**
     * 获取Toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 左图标
     *
     * @param id
     */
    public void setLeftImagSrc(int id) {
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setImageResource(id);
    }
    public void setLeftImgGone() {
        ivLeft.setVisibility(View.GONE);
    }
    /*
     * 点击事件
     */
    public void setOnBackListener(OnClickListener listener) {
    }

    public void setOnRightImagListener(OnClickListener listener) {
        ivRight.setOnClickListener(listener);
    }

    public void setOnLeftImagListener(OnClickListener listener) {
        ivLeft.setOnClickListener(listener);
    }

    public void setOnRightTextListener(OnClickListener listener) {
        tvRight.setOnClickListener(listener);
    }

    public void setOnLeftTextListener(OnClickListener listener) {
        tvLeft.setOnClickListener(listener);
    }

    /**
     * 标题背景颜色
     *
     * @param color
     */
    public void setBackGroundColor(int color) {
        toolbar.setBackgroundColor(context.getResources().getColor(color));
    }

    public TextView getTvTitle() {
        return tvTitle;
    }
}
