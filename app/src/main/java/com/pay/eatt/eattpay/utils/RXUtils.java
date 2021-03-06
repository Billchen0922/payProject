package com.pay.eatt.eattpay.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.Request;
import com.lzy.okrx2.adapter.ObservableBody;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by bill on 2017/10/26.
 */

public class RXUtils {
    public static <T> Observable<T> request(HttpMethod method, String url, Type type) {
        return request(method, url, type, null);
    }

    public static <T> Observable<T> request(HttpMethod method, String url, Type type, HttpParams params) {
        return request(method, url, type, params, null);
    }

    public static <T> Observable<T> request(HttpMethod method, String url, Type type, HttpParams params, HttpHeaders headers) {
        return request(method, url, type, null, params, headers);
    }

    public static <T> Observable<T> request(HttpMethod method, String url, Class<T> clazz) {
        return request(method, url, clazz, null);
    }

    public static <T> Observable<T> request(HttpMethod method, String url, Class<T> clazz, HttpParams params) {
        return request(method, url, clazz, params, null);
    }

    public static <T> Observable<T> request(HttpMethod method, String url, Class<T> clazz, HttpParams params, HttpHeaders headers) {
        return request(method, url, null, clazz, params, headers);
    }


    /**
     * 这个封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
     * 这个封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
     * 这个封装其实没有必要，只是有些人喜欢这么干，我就多此一举写出来了。。
     */
    public static <T> Observable<T> request(HttpMethod method, String url, Type type, Class<T> clazz, HttpParams params, HttpHeaders headers) {
        Request<T, ? extends Request> request;
        if (method == HttpMethod.GET) {
            request = OkGo.get(url);
        } else if (method == HttpMethod.POST){
            request = OkGo.post(url);
        } else if (method == HttpMethod.PUT){
            request = OkGo.put(url);
        } else if (method == HttpMethod.DELETE){
            request = OkGo.delete(url);
        } else if (method == HttpMethod.HEAD){
            request = OkGo.head(url);
        } else if (method == HttpMethod.PATCH){
            request = OkGo.patch(url);
        } else if (method == HttpMethod.OPTIONS){
            request = OkGo.options(url);
        } else if (method == HttpMethod.TRACE){
            request = OkGo.trace(url);
        } else {
            request = OkGo.get(url);
        }

        request.headers(headers);
        request.params(params);
        if (type != null) {
            request.converter(new JsonConverter<T>(type));
        } else if (clazz != null) {
            request.converter(new JsonConverter<T>(clazz));
        }
        LoggerUtil.d(request.getUrl());
        return request.adapt(new ObservableBody<T>());
    }
}
