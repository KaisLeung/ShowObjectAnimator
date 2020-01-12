package com.kais.my.animator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kais
 * @date 2020/1/9
 * @description 子线程——模拟vsync信号
 */
public class VSYNCManager {
    private List<AnimationFrameCallback> mCallbackQueue = new ArrayList<>();

    public static VSYNCManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final VSYNCManager INSTANCE = new VSYNCManager();
    }

    private VSYNCManager() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //执行回调
                    for (AnimationFrameCallback c :
                            mCallbackQueue) {
                        if (c != null) {
                            c.doAnimationCallback(System.currentTimeMillis());
                        }
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    public void addCallback(AnimationFrameCallback callback) {
        mCallbackQueue.add(callback);
    }

    interface AnimationFrameCallback {
        boolean doAnimationCallback(long currentTime);
    }
}
