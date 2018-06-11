package com.pay.eatt.eattpay.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by bill
 * 用于获取本机的ip地址与mac地址等
 */
public class CommonUtil {


    /*
    * 得到当前时间
    * */
    public static String getTime(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat(s);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return  str;
    }

    /*
    * 得到sd卡路径
    * */
    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }else{
            sdDir.mkdirs();
        }
        return sdDir.toString();
    }

    /**
     * 5.0以上动态获取包名信息
     *
     * @param context
     * @param implicitIntent
     * @return
     */
    public static Intent getExplicitIntent(Context context,
                                           Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent,
                0);
        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);
        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);
        // Set the component to be explicit
        explicitIntent.setComponent(component);
        return explicitIntent;
    }


    /**
     * 判断空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        if(s==null || s.equals("")){
            return true;
        }else {
            return false;
        }
    }


    //getTime方法返回的就是10位的时间戳

    public static String getTime(){

        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳

        String  str=String.valueOf(time);

        return str;

    }

    /**
     * 把固定格式的字符串（key=value&key=value...）转换成Bean对象。
     *
     * @param response
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBeanByString(String response, Class<T> clazz) {
        T t = null;
        try {
            HashMap<String, String> paramMap = getMapByKvString(response);
            if (paramMap != null && !paramMap.isEmpty()) {
                t = clazz.newInstance();
                Field[] fields = clazz.getFields();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    if (paramMap.containsKey(fieldName)) {
                        field.set(t, paramMap.get(fieldName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            t = null;
        }
        return t;
    }


    /**
     * 把固定格式字符串（key=value&key=value...）转换成Map对象。
     *
     * @param kvString
     * @return
     */
    public static HashMap<String, String> getMapByKvString(String kvString) {
        HashMap<String, String> paramMap = null;
        if (!TextUtils.isEmpty(kvString) && kvString.contains(SPLIT_SYMBOL[0])) {
            paramMap = new HashMap<>();
            String[] params = kvString.split(SPLIT_SYMBOL[0]);
            for (String param : params) {
                if (param.contains(SPLIT_SYMBOL[1])) {
                    String[] kvCouple = param.split(SPLIT_SYMBOL[1]);
                    if (kvCouple.length == 2) {
                        paramMap.put(kvCouple[0], kvCouple[1]);
                        continue;
                    }
                }
                paramMap.clear();
                break;
            }
        }
        return paramMap;
    }

    private static final String[] SPLIT_SYMBOL = {"&", "="};


    /**
     * 手机号7-10用*替代
     * @param pNumber
     * @return
     */
  public static String phoneHide(String pNumber){
      if(!TextUtils.isEmpty(pNumber) && pNumber.length() > 6 ){
          StringBuilder sb  =new StringBuilder();
          for (int i = 0; i < pNumber.length(); i++) {
              char c = pNumber.charAt(i);
              if (i >= 3 && i <= 6) {
                  sb.append('*');
              } else {
                  sb.append(c);
              }
          }

         return  sb.toString();
      }
      return null;
  }
}
