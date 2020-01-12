package com.kais.my.animator;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * @author Kais
 * @date 2020/1/9
 * @description
 */
public class MyObjectAnimator implements VSYNCManager.AnimationFrameCallback{
    private float mStartTime = -1;
    private long mDuration = 0;
    /**
     * 帧画面的索引值
     */
    private int frameIndex = 0;
    private WeakReference<View> mTarget;

    private TimeInterpolator interpolator;
    private MyPropertyValuesHolder mPropertyValuesHolder;

    public MyObjectAnimator(View target, String propertyName, float... values) {
        mTarget = new WeakReference<>(target);
        mPropertyValuesHolder = new MyPropertyValuesHolder(propertyName,values);
    }

    public static MyObjectAnimator ofFloat(View target,String propertyName,float... values){
        return new MyObjectAnimator(target,propertyName,values);
    }

    public void setStartTime(float startTime) {
        this.mStartTime = startTime;
    }

    public void setDuration(long duration) {
        this.mDuration = duration;
    }

    @Override
    public boolean doAnimationCallback(long currentTime) {
        //计算所有帧
        //比如间隔时间1000 ms，则一共有 1000 / 16 帧
        float total = mDuration / 16;
        //计算出当前帧的百分比
        float fraction = frameIndex++ / total;
        if (interpolator != null){
            //通过插值器获取值
            fraction = interpolator.getInterpolator(fraction);
        }
        //重置为0，恢复到第一帧
        if (frameIndex >= total){
            frameIndex = 0;
        }
        mPropertyValuesHolder.setAnimatedValue(mTarget.get(),fraction);
        return false;
    }

    /**
     * 开启动画
     */
    public void start(){
        mPropertyValuesHolder.setupSetter();
        mStartTime = System.currentTimeMillis();
        VSYNCManager.getInstance().addCallback(this);
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }
}
