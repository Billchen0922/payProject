package com.pay.eatt.eattpay.http;

import android.graphics.Bitmap;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.BitmapConvert;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableBody;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.pay.eatt.eattpay.base.BaseActivity;
import com.pay.eatt.eattpay.utils.JsonConverter;
import com.pay.eatt.eattpay.utils.RXUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bill on 2017/10/26.
 */

public class ServiceApi {

    private BaseActivity activity;

    public ServiceApi(BaseActivity activity){
        this.activity = activity;
    }
    public <T>void request(HttpMethod method, String url, Class<T> clazz, MyObserver<T> myObserver){
        setObserver(RXUtils.request(method, url, clazz, null),myObserver);
    }

    public <T>void request(HttpMethod method, String url, Class<T> clazz, HttpParams params, MyObserver<T> myObserver) {
        setObserver(RXUtils. request(method, url, clazz, params, null),myObserver);
    }

    public <T>void request(HttpMethod method, String url, Class<T> clazz, HttpParams params, HttpHeaders headers, MyObserver<T> myObserver) {
        setObserver(RXUtils. request(method, url, clazz, params, headers),myObserver);
    }

    public <T>void request(String url, Class<T> clazz, JSONObject jsonObject, HttpHeaders headers, MyObserver<T> myObserver) {
        Observable<T> adapt = null;
            adapt = OkGo.<T>post(url)
                    .headers(headers)
                    .upJson(jsonObject.toString())
                    .converter(new JsonConverter<T>(clazz))
                    .adapt(new ObservableBody<T>());
        setObserver(adapt,myObserver);
    }

    public <T>void getString(HttpMethod method, String url, HttpParams params, HttpHeaders headers, MyObserver<Response<String>> myObserver) {
        Observable<Response<String>> adapt = null;
        if (method == HttpMethod.POST) {
            adapt = OkGo.<String>post(url)
                    .headers(headers)
                    .params(params)
                    .converter(new StringConvert())//
                    .adapt(new ObservableResponse<String>());
        }else{
            adapt = OkGo.<String>get(url)
                    .headers(headers)
                    .params(params)
                    .converter(new StringConvert())//
                    .adapt(new ObservableResponse<String>());
        }
        setObserver(adapt,myObserver);
    }

    public void getBitmap(String url, HttpHeaders headers, HttpParams params, MyObserver<Response<Bitmap>> myObserver) {
        Observable<Response<Bitmap>> adapt = OkGo.<Bitmap>post(url)//
                .headers(headers)
                .params(params)
                .converter(new BitmapConvert())//
                .adapt(new ObservableResponse<Bitmap>());
        setObserver(adapt,myObserver);
    }

    public <T>void setObserver(Observable<T> observable,MyObserver<T> myObserver){
        observable
        .subscribeOn(Schedulers.io())//
        .doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                //loading
                activity.showProgressDialog("");
            }
        })//
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(myObserver);
    }

    /**
    * 获取沐融公共参数
    * */
    public HttpParams getMRBaseHttpParams(){
        HttpParams mParamsMap = new HttpParams();
        mParamsMap.put("characterSet", "00");
        mParamsMap.put("merchantId", "000000000000000");
        mParamsMap.put("requestId", System.currentTimeMillis() + "000001");
        mParamsMap.put("signType", "MD5");
        mParamsMap.put("version", "1.0.2");
        mParamsMap.put("hmac", "0000000000000000");
        mParamsMap.put("oprId", "001");
        return mParamsMap;
    }

}
