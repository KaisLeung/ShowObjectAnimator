package com.kais.my.animator;

/**
 * @author Kais
 * @date 2020/1/9
 * @description 关键帧
 */
public class MyFloatKeyFrame {
    /**
     * 关键帧位置百分比
     */
    private float mFraction;

    private float mValue;
    /**
     * 值类型
     * 默认为float
     */
    private Class mValueType;

    public MyFloatKeyFrame(float mFraction, float mValue) {
        this.mFraction = mFraction;
        this.mValue = mValue;
        this.mValueType = float.class;
    }

    public float getFraction() {
        return mFraction;
    }

    public void setFraction(float fraction) {
        this.mFraction = fraction;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        this.mValue = value;
    }

    public Class getValueType() {
        return mValueType;
    }

    public void setValueType(Class valueType) {
        this.mValueType = valueType;
    }
}
