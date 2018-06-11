package com.pay.eatt.eattpay.http;



import com.pay.eatt.eattpay.base.BaseActivity;
import com.pay.eatt.eattpay.utils.LoggerUtil;
import com.pay.eatt.eattpay.utils.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by bill on 2017/10/26.
 */

public abstract class MyObserver<T> implements Observer<T> {

    private BaseActivity activity;

    public MyObserver(BaseActivity activity){
        this.activity=activity;
    }
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }


    @Override
    public void onError(@NonNull Throwable e) {
        LoggerUtil.e(e.getMessage());
        //ToastUtils.toast(activity.getString(R.string.erroe_tip1));
        activity.hideProgressDialog();
    }

    @Override
    public void onComplete() {
        activity.hideProgressDialog();
    }

}
