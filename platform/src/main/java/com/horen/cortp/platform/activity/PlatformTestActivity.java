package com.horen.cortp.platform.activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.horen.cortp.platform.R;
import com.horen.cortp.platform.adapter.HomeBannerAdapter;
import com.horen.cortp.platform.adapter.UseBoxFeelAdapter;
import com.horen.cortp.platform.bean.HomeBanner;
import com.jaydenxiao.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HOREN on 2017/11/28.
 *
 * 用箱体验activity
 *
 */
public class PlatformTestActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ConvenientBanner banner;
    public static List<HomeBanner> localImages = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }
    @Override
    public void initPresenter() {
    }
    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new UseBoxFeelAdapter(this));
        banner = (ConvenientBanner) findViewById(R.id.banner_plarform);
        HomeBanner banner1 = new HomeBanner();
        HomeBanner banner2 = new HomeBanner();
        HomeBanner banner3 = new HomeBanner();
        banner1.banner_image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515657044040&di=b171ee203b9ba1e4b0739bac24f88fe3&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fa044ad345982b2b713b5ad7d3aadcbef76099b65.jpg";
        banner2.banner_image = "https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=1ac3db8a3aadcbef1e3478069cae2e0e/cdbf6c81800a19d8765f664b38fa828ba61e4624.jpg";
        banner3.banner_image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515657044208&di=72c076ff6db320f4d03f5992c27c2b54&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fdcc451da81cb39db47338c18db160924aa183089.jpg";
        localImages.add(banner1);
        localImages.add(banner2);
        localImages.add(banner3);
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new HomeBannerAdapter();
            }
        },localImages);
        banner.startTurning(3000);
    }
}