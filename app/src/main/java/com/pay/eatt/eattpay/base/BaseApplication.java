package com.pay.eatt.eattpay.base;

import android.app.Application;

import com.pay.eatt.eattpay.http.OkgoManager;
import com.pay.eatt.eattpay.utils.SharePreferenceUtils;


/**
 * Created by bill on 2017/8/29.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        SharePreferenceUtils.init(this);
        OkgoManager.getInstance().init(this);
    }

    public static BaseApplication getInstance(){
        return instance;
    }
}
