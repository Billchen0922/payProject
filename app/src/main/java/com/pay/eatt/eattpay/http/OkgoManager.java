package com.pay.eatt.eattpay.http;

import android.app.Application;
import android.support.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.pay.eatt.eattpay.utils.LoggerUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by bill on 2017/10/26.
 */

public class OkgoManager {

    private static OkgoManager okgoManager;

    public static OkgoManager getInstance() {
        if (null == okgoManager) {
            synchronized (OkgoManager.class) {
                if (okgoManager == null) {
                    okgoManager = new OkgoManager();
                }
            }
        }
        return okgoManager;
    }

    public void init(Application application){
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        //公共参数
        /*HttpHeaders headers = new HttpHeaders();
        //headers.put("commonHeaderKey1","commonHeaderValue1");    //header不支持中文，不允许有特殊字符
        HttpParams params = new HttpParams();
        //params.put("commonParamsKey1","commonParamsValue1");     //param支持中文,直接传,不要自己编码*/

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("http");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(sLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();


        OkGo.getInstance()
                .init(application)                       //必须调用初始化
                .setOkHttpClient(okHttpClient)               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(1)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);                          //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
/*                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数*/
    }


    /**
     * 打印返回的json数据拦截器
     */
    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            //            Request.Builder requestBuilder = request.newBuilder();
            //            request = requestBuilder.post(RequestBody.create(MediaType.parse
            // ("application/json;charset=GBK"),
            //                    URLDecoder.decode(request.body().toString(), "UTF-8")))
            //                    .build();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            } else {
                LoggerUtil.d("LogTAG-----request.body() == null");
            }
            //打印url信息
            LoggerUtil.w(request.url() + (request.body() != null ? "?" + _parseParams(request.body(),
                    requestBuffer) : ""));

            return chain.proceed(request);
        }
    };

    @NonNull
    private static String _parseParams(RequestBody body, Buffer requestBuffer) throws
            UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "GBK");

        }
        return "null";
    }
}
