package com.pay.eatt.eattpay.fragment;

import android.os.Bundle;
import android.view.View;

import com.pay.eatt.eattpay.R;
import com.pay.eatt.eattpay.base.BaseFragment;

/**
 * Created by bill on 2018/6/11.
 */

public class FindFragment extends BaseFragment {
    @Override
    protected int setView() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView(View view) {
        mActivity.setTitle("发现");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
