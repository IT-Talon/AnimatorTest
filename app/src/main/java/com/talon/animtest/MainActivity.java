package com.talon.animtest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText mEditText;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.edit);
        mTextView = findViewById(R.id.tv_hint);


        ObjectAnimator moveX1 = ObjectAnimator.ofFloat(mTextView, "translationX",  0f);
        ObjectAnimator moveY1 = ObjectAnimator.ofFloat(mTextView, "translationY", 0f);
        ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(mTextView, "scaleX",  1f);
        ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(mTextView, "scaleY", 1f);

        final AnimatorSet animSet1 = new AnimatorSet();
        animSet1.play(moveX1).with(moveY1).with(scaleX1).with(scaleY1);
        animSet1.setInterpolator(new AccelerateInterpolator());
        animSet1.setDuration(200);
        final ObjectAnimator moveX2 = ObjectAnimator.ofFloat(mTextView, "translationX", -60f);
        ObjectAnimator moveY2 = ObjectAnimator.ofFloat(mTextView, "translationY", -25f);
        ObjectAnimator scaleX2 = ObjectAnimator.ofFloat(mTextView, "scaleX",  0.7f);
        ObjectAnimator scaleY2 = ObjectAnimator.ofFloat(mTextView, "scaleY", 0.7f);
        final AnimatorSet animSet2 = new AnimatorSet();
        animSet2.play(moveX2).with(moveY2).with(scaleX2).with(scaleY2);
        animSet2.setInterpolator(new AccelerateInterpolator());
        animSet2.setDuration(200);
        mTextView.setPivotX(15);
        mTextView.setPivotY(0);

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (mEditText.getText().length() == 0) {
                    if (hasFocus) {
                        mTextView.setTextColor(Color.RED);
                        if (animSet1.isRunning()){
                            animSet1.cancel(); // 结束动画，停在当前位置  animSet1.end() 结束动画，停在最终状态位置
                        }
                        animSet2.setupStartValues(); // 重置起始位置为当前位置
                        animSet2.start();
                    } else {
                        mTextView.setTextColor(Color.GRAY);
                        if (animSet2.isRunning()){
                            animSet2.cancel();
                        }
                        animSet1.setupStartValues(); // 重置起始位置为当前位置
                        animSet1.start();
                    }
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
