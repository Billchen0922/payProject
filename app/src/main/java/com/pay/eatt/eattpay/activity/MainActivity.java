package com.pay.eatt.eattpay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pay.eatt.eattpay.R;
import com.pay.eatt.eattpay.base.BaseActivity;
import com.pay.eatt.eattpay.fragment.FindFragment;
import com.pay.eatt.eattpay.fragment.HomeFragment;
import com.pay.eatt.eattpay.fragment.MyFragment;
import com.pay.eatt.eattpay.fragment.ShopFragment;
import com.pay.eatt.eattpay.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.viewpage)
    NoScrollViewPager viewpage;
    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.txt_home)
    TextView txtHome;
    @BindView(R.id.img_me)
    ImageView imgMe;
    @BindView(R.id.txt_me)
    TextView txtMe;
    @BindView(R.id.linlayout_me)
    LinearLayout linlayoutMe;
    @BindView(R.id.bottom)
    LinearLayout bottom;
    @BindView(R.id.linlayout_home)
    LinearLayout linlayoutHome;
    @BindView(R.id.img_shop)
    ImageView imgShop;
    @BindView(R.id.txt_shop)
    TextView txtShop;
    @BindView(R.id.linlayout_shop)
    LinearLayout linlayoutShop;
    @BindView(R.id.img_find)
    ImageView imgFind;
    @BindView(R.id.txt_find)
    TextView txtFind;
    @BindView(R.id.linlayout_find)
    LinearLayout linlayoutFind;

    private List<Fragment> fragmentList;
    private HomeFragment mainFragment;
    private ShopFragment shopFragment;
    private FindFragment findFragment;
    private MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseSetContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        if (fragmentList != null) {
            fragmentList.clear();
        } else {
            fragmentList = new ArrayList<>();
        }

        mainFragment = new HomeFragment();
        linlayoutHome.setOnClickListener(this);
        fragmentList.add(mainFragment);

        shopFragment = new ShopFragment();
        linlayoutShop.setOnClickListener(this);
        fragmentList.add(shopFragment);

        findFragment = new FindFragment();
        linlayoutFind.setOnClickListener(this);
        fragmentList.add(findFragment);

        myFragment = new MyFragment();
        linlayoutMe.setOnClickListener(this);
        fragmentList.add(myFragment);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),fragmentList);

        viewpage.setAdapter(pagerAdapter);
        viewpage.setOffscreenPageLimit(fragmentList.size());
        viewpage.setNoScroll(true);
    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linlayout_home:
                viewpage.setCurrentItem(0, false);
                setHome();
                break;
            case R.id.linlayout_shop:
                viewpage.setCurrentItem(1, false);
                setShop();
                break;
            case R.id.linlayout_find:
                viewpage.setCurrentItem(2, false);
                setFind();
                break;
            case R.id.linlayout_me:
                viewpage.setCurrentItem(3, false);
                setMe();
                break;
            default:
        }
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;

        PagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    void setHome() {
        imgHome.setImageResource(R.mipmap.tab_icon_shouye_xuanzhong);
        imgShop.setImageResource(R.mipmap.tab_icon_shanghu_weixuanzhong);
        imgFind.setImageResource(R.mipmap.tab_icon_discover_weixuanzhong);
        imgMe.setImageResource(R.mipmap.tab_icon_wode_weixuanzhong);
        setTitle("首页");
    }

    void setShop() {
        imgHome.setImageResource(R.mipmap.tab_icon_shouye_weixuanzhong);
        imgShop.setImageResource(R.mipmap.tab_icon_shanghu_xuanzhong);
        imgFind.setImageResource(R.mipmap.tab_icon_discover_weixuanzhong);
        imgMe.setImageResource(R.mipmap.tab_icon_wode_weixuanzhong);
        setTitle("商城");
    }

    void setFind() {
        imgHome.setImageResource(R.mipmap.tab_icon_shouye_weixuanzhong);
        imgShop.setImageResource(R.mipmap.tab_icon_shanghu_weixuanzhong);
        imgFind.setImageResource(R.mipmap.tab_icon_discover_xuanzhong);
        imgMe.setImageResource(R.mipmap.tab_icon_wode_weixuanzhong);
        setTitle("发现");
    }

    void setMe() {
        imgHome.setImageResource(R.mipmap.tab_icon_shouye_weixuanzhong);
        imgShop.setImageResource(R.mipmap.tab_icon_shanghu_weixuanzhong);
        imgFind.setImageResource(R.mipmap.tab_icon_discover_weixuanzhong);
        imgMe.setImageResource(R.mipmap.tab_icon_wode_xuanzhong);
        setTitle("我的");
    }
}
