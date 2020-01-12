package com.kais.my.animator;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kais
 * @date 2020/1/9
 * @description 关键帧管理类
 */
public class MyKeyFrameSet {
    /**
     * 存储关键帧
     */
    private List<MyFloatKeyFrame> frames;
    /**
     * 估值器
     */
    private TypeEvaluator mEvaluator;

    private MyKeyFrameSet(MyFloatKeyFrame... frames) {
        this.frames = Arrays.asList(frames);
        mEvaluator = new FloatEvaluator();
    }

    public static MyKeyFrameSet ofFloat(float... values) {
        //关键帧初始化
        if (values.length <= 0) {
            return null;
        }
        int keyFramesNum = values.length;
        MyFloatKeyFrame[] frames = new MyFloatKeyFrame[keyFramesNum];
//        frames[0] = new MyFloatKeyFrame(0, values[0]);
        for (int i = 0; i < keyFramesNum; i++) {
            //计算关键帧所处的百分比值
            frames[i] = new MyFloatKeyFrame((float) i / (keyFramesNum - 1), values[i]);
        }
        return new MyKeyFrameSet(frames);
    }

    /**
     * 通过估值器获取帧画面对应的属性值
     *
     * @param fraction 帧画面对应的百分值
     * @return 属性值
     * //计算出帧画面在相邻的两个关键帧中的百分比
     * //然后通过该百分比，通过估值器计算出对应的值
     * //比如用户设置有3个关键帧，1f，2f，4f
     * //第二个关键帧所处的百分比为0.5
     * //那么在第二个关键帧（值为2f）和第三个关键帧（值为4f）之间
     * //当前帧画面的百分值为0.7,那么就要先计算出当前帧所在关键帧区间中所占百分比值
     * //即为0.7 - (1 - 0.5) = 0.2
     * //从而计算出当前帧画面对应的属性值为 (4f-2f) * 0.2 + 2f= 2.4
     */
    @SuppressWarnings("unchecked")
    public Object getValue(float fraction) {
        //从第一个关键帧开始
        MyFloatKeyFrame preKeyframe = frames.get(0);
        for (int i = 0; i < frames.size(); i++) {
            //获取相邻的关键帧
            MyFloatKeyFrame nextKeyframe = frames.get(i);
            if (fraction < nextKeyframe.getFraction()) {
                float intervalFraction =
                        (fraction - preKeyframe.getFraction()) / (nextKeyframe.getFraction() - preKeyframe.getFraction());
                //通过估计器计算出对应的属性值
                return mEvaluator == null ? preKeyframe.getValue() + intervalFraction * (nextKeyframe.getValue() - preKeyframe.getValue()) :
                        mEvaluator.evaluate(intervalFraction,preKeyframe.getValue(),nextKeyframe.getValue());
            } else {
                //将当前关键帧移动到下一帧
                preKeyframe = nextKeyframe;
            }
        }
        //遍历到最后一个关键帧
        return frames.get(frames.size() - 1).getValue();
    }
}
