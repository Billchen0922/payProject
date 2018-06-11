package com.pay.eatt.eattpay.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pay.eatt.eattpay.R;
import com.pay.eatt.eattpay.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by bill on 2018/6/11.
 */

public class ShopFragment extends BaseFragment {
    @BindView(R.id.tv_shop)
    TextView tvShop;

    @Override
    protected int setView() {
        return R.layout.fragment_shop;
    }

    @Override
    protected void initView(View view) {

        tvShop.setText("商城");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
