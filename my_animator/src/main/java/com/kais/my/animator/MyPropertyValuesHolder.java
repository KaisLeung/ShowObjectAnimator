package com.kais.my.animator;

import android.text.TextUtils;
import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Kais
 * @date 2020/1/9
 * @description View属性管理器
 */
public class MyPropertyValuesHolder {
    private String mPropertyName;
    private Class mValueType;
    /**
     * View属性的setter方法
     * 通过反射获取
     */
    private Method mSetter = null;
    /**
     * 关键帧管理类
     */
    private MyKeyFrameSet keyFrameSet;

    public MyPropertyValuesHolder(String propertyName,float... values){
        this.mPropertyName = propertyName;
        this.mValueType = float.class;
        this.keyFrameSet = MyKeyFrameSet.ofFloat(values);
    }

    /**
     * 通过反射获取View的setter方法，比如：
     * public void setScaleX(float scaleX)
     */
    public void setupSetter(){
        if (!TextUtils.isEmpty(mPropertyName)) {
            char firstLetter = Character.toUpperCase(mPropertyName.charAt(0));
            String methodName = "set" + firstLetter + mPropertyName.substring(1);
            //通过反射获取setter方法(比如 View.setScaleX)
            try {
                mSetter = View.class.getMethod(methodName,mValueType);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置帧画面的属性值
     */
    public void setAnimatedValue(View view,float fraction){
        Object value = keyFrameSet.getValue(fraction);
        //反射方法调用view.setScaleX方法
        try {
            mSetter.invoke(view,value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
