package com.horen.cortp.platform.widget;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.bean.PlanTypeLookup;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import java.util.List;
/**
 * Created by HOREN on 2017/11/29.
 *
 * 全部方案,点击二级菜单,选择框
 *
 */
public class SelectPlanTypeDialog extends Dialog {
    private final String COLOR_SELECT_BLUE = "#5F8BFA";
    private final String COLOR_SELECT_BLACK = "#000000";
    private Context context;
    private OnSelectClickListener listener;
    private List<PlanTypeLookup> listType;
    private LinearLayout layout;
    public SelectPlanTypeDialog(@NonNull Context context,OnSelectClickListener listener,List<PlanTypeLookup> listType) {
        this(context,R.style.DialogSelectPlanType);
        this.context = context;
        this.listener = listener;
        this.listType = listType;
    }
    public SelectPlanTypeDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
    protected SelectPlanTypeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.requestFeature(window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_allplan_type);
        layout = (LinearLayout) findViewById(R.id.ll_dialog_allplan_content);
        for (int i=0;i<listType.size();i++){
            PlanTypeLookup type = listType.get(i);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_allplan_type_text,null);
            final TextView textView = (TextView) view.findViewById(R.id.tv_all_plan_type_text);
            textView.setText(type.lookup_name);
            textView.setTag(type);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.click((PlanTypeLookup) textView.getTag());
                        initColor();
                        textView.setTextColor(Color.parseColor(COLOR_SELECT_BLUE));
                    }
                }
            });
            if (i == 0){
                textView.setTextColor(Color.parseColor(COLOR_SELECT_BLUE));
            }
            layout.addView(view);
        }
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DisplayUtil.getScreenWidth(context)-DisplayUtil.dip2px(110);
        lp.height = DisplayUtil.dip2px(200);
        window.getAttributes().gravity = Gravity.RIGHT | Gravity.TOP;
        lp.x = DisplayUtil.dip2px(5);
        window.setAttributes(lp);
        //在构造方法里设置style，style里有动画，不管用，需要用下面方法设置dialog进入退出动画
        window.setWindowAnimations(R.style.DialogSelectPlanTypeAnimation);
    }
    private void initColor() {
        int size = layout.getChildCount();
        for (int i=0;i<size;i++){
            View view = layout.getChildAt(i);
            TextView textView = (TextView) view.findViewById(R.id.tv_all_plan_type_text);
            textView.setTextColor(Color.parseColor(COLOR_SELECT_BLACK));
        }
    }
    public interface OnSelectClickListener {
        void click(PlanTypeLookup select);
    }
    public void setLayout(View view){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        int[] position = new int[2];
        view.getLocationInWindow(position);
        //getLocationInWindow 获取：状态栏+标题栏+自身距离顶部(距离屏幕顶部)
        //view.getLocationOnScreen(position);//距离activity顶部
        //position[0]获取x坐标绝对距离
        lp.y = position[1]+view.getHeight()-DisplayUtil.getStatusBarHeight(context)+DisplayUtil.dip2px(5);
        window.setAttributes(lp);
    }
}