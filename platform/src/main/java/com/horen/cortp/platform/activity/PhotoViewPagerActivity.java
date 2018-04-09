package com.horen.cortp.platform.activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.horen.cortp.platform.R;
import java.util.List;
/**
 * Created by BuZhiheng on 2017/12/08.
 *
 * 图片查看 activity (viewpager+photoview+glide)
 * 类似与微信空间
 * 需要传入list<String> 图片URL地址
 * 需要传入当前显示的图片position 默认显示第一张
 *
 */
public class PhotoViewPagerActivity extends AppCompatActivity {
    public static String PHOTO_URL = "PHOTO_URL";
    public static String PHOTO_POSITION = "PHOTO_POSITION";
    private ViewPager mPager;
    private TextView tvPosition;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewpage);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        list = intent.getStringArrayListExtra(PHOTO_URL);
        if (list == null || list.size() == 0){
            finish();
            return;
        }
        tvPosition = (TextView) findViewById(R.id.tv_photo_viewpage_position);
        mPager = (ViewPager) findViewById(R.id.vp_photo_viewpage);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(PhotoViewPagerActivity.this);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Glide.with(PhotoViewPagerActivity.this).load(list.get(position)).into(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                container.addView(view);
                return view;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                int position = i+1;
                tvPosition.setText(position+"/"+list.size());
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        int currImg = intent.getIntExtra(PHOTO_POSITION,0);
        if (list.size() > 0){
            for (int i=0;i<list.size();i++){
                if (currImg == i){
                    mPager.setCurrentItem(i);
                    break;
                }
            }
        }
    }
}