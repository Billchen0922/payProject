package com.pay.eatt.eattpay.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.pay.eatt.eattpay.base.BaseApplication;


/**
 * Toast的自定义方法
 * Created by bill
 */
public class ToastUtils {

    /**
     * 默认显示时间Toast.LENGTH_SHORT
     * @param ctx      上下文
     * @param stringid 文字id
     * @return
     */
    public static void toast(Context ctx, int stringid){
        toast(ctx,stringid, Toast.LENGTH_SHORT);
    }

    /**
     * 默认显示时间Toast.LENGTH_SHORT
     * @param stringid 文字id
     * @return
     */
    public static void toast(int stringid){
        toast(stringid, Toast.LENGTH_SHORT);
    }

    /**
     * 显示的可以及时清除
     * @param ctx       上下文
     * @param stringid  文字资源id
     * @param lastTime  显示时间
     * @return
     */
    public static void  toast(Context ctx, int stringid, int lastTime) {
        toast(ctx,ctx.getString(stringid),lastTime);
    }

    /**
     * 显示的可以及时清除
     * @param stringid  文字资源id
     * @param lastTime  显示时间
     * @return
     */
    public static void  toast(int stringid, int lastTime) {
        toast(BaseApplication.getInstance().getString(stringid),lastTime);
    }

    /**
     * 默认显示时间用Toast.LENGTH_LONG
     * @param ctx  上下文
     * @param tips  表达内容
     * @return
     */
    public static void toast(Context ctx, String tips){
        toast(ctx,tips, Toast.LENGTH_SHORT);
    }


    /**
     * 默认显示时间用Toast.LENGTH_SHORT
     * @param tips 表达内容
     * @return
     */
    public static void toast(String tips){
        toast(tips, Toast.LENGTH_SHORT);
    }
    /**
     * 显示的可以及时清除且居中表达
     * @param ctx      上下文
     * @param tips     显示文字
     * @param lastTime 显示时间
     * @return
     */
    public static void toast(Context ctx, String tips, int lastTime) {
        toast(ctx,tips,lastTime, Gravity.CENTER);
    }

    /**
     * 显示的可以及时清除且居中表达
     * @param tips     显示文字
     * @param lastTime 显示时间
     * @return
     */
    public static void toast(String tips, int lastTime) {
        toast(tips,lastTime, Gravity.CENTER);
    }

    /**
     * @param ctx      上下文
     * @param tips     显示文字
     * @param lastTime 显示时间
     * @param gravity  .屏幕显示位置
     * @return
     */
    private static void toast(Context ctx, String tips, int lastTime, int gravity) {
        if(null == ctx){
            return ;
        }
        Toast toast= Toast.makeText(ctx, tips, lastTime);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    /**
     * @param tips     显示文字
     * @param lastTime 显示时间
     * @param gravity  .屏幕显示位置
     */
    private static void toast(String tips, int lastTime, int gravity) {
        toast(BaseApplication.getInstance(), tips, lastTime, gravity);
    }



}
