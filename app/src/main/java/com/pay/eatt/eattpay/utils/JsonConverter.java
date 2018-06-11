package com.pay.eatt.eattpay.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.lzy.okgo.convert.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by bill on 2017/10/26.
 */

public class JsonConverter<T> implements Converter<T> {

    private Type type;
    private Class<T> clazz;

    public JsonConverter() {
    }

    public JsonConverter(Type type) {
        this.type = type;
    }

    public JsonConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null){
            return null;
        }
        T date = null;
        JSONReader jsonReader = new JSONReader(body.charStream());
        if(type !=null){
            date = JSON.parseObject(jsonReader.readString(),type);
        }else if(clazz !=null){
            date = JSON.parseObject(jsonReader.readString(),clazz);
        }else{
            Type actualTypeArguments = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            date = JSON.parseObject(jsonReader.readString(),actualTypeArguments);
        }
        return date;
    }
}
