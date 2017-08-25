package com.simple.view;

import android.os.Handler;

import com.xcheng.view.processbtn.ProcessButton;

import java.util.Random;

public class ProgressGenerator {

    public interface OnCompleteListener {
        void onComplete();
    }

    private OnCompleteListener mListener;


    public ProgressGenerator(OnCompleteListener listener) {
        mListener = listener;
    }

    public void start(final ProcessButton button) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            private int mProgress;
            @Override
            public void run() {
                if (mProgress==100){
                    mProgress=0;
                }
                mProgress += 10;
                button.setProgress(mProgress);
                if (mProgress < 100) {
                    handler.postDelayed(this, generateDelay());
                } else {
                    mListener.onComplete();
                }
            }
        }, generateDelay());
    }

    private Random random = new Random();

    private int generateDelay() {
        return random.nextInt(1000);
    }
}