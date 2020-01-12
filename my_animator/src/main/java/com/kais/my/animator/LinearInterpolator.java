package com.kais.my.animator;

/**
 * @author Kais
 * @date 2020/1/9
 * @description
 */
public class LinearInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolator(float input) {
        return input;
    }
}
