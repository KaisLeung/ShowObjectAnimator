package com.kais.kaianimator;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.Button;

import com.kais.my.animator.LinearInterpolator;
import com.kais.my.animator.MyObjectAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "RotationY", 0,180,240,360,0);
        MyObjectAnimator animator = MyObjectAnimator.ofFloat(button, "ScaleY", 1f,2f,4f);
//        MyObjectAnimator animator = MyObjectAnimator.ofFloat(button, "RotationY", 0,180,240,360,0);
        animator.setDuration(2000);
        animator.setInterpolator(new LinearInterpolator());
//        animator.setInterpolator(new LinearInterpolator());
        animator.start();

    }
}
