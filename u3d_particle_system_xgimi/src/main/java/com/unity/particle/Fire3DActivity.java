package com.unity.particle;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * author: King.Z <br>
 * date:  2020/7/30 23:30 <br>
 * description:  <br>
 */
public class Fire3DActivity extends UnityPlayerActivity {

    boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addMarqueeText();
    }

    private void addMarqueeText() {
        TextView view = new TextView(this);
        view.setText(R.string.particle_desc);
        view.setTextSize(48);
        view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        view.setSingleLine(true);
        view.setSelected(true);
        view.setMarqueeRepeatLimit(-1);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setTextColor(Color.parseColor("#ffffff99"));
        view.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
        view.setPadding(10,0,10,0);
        FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,200);
        lps.gravity = Gravity.START | Gravity.TOP;
        addContentView(view,lps);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                return true;
            case KeyEvent.KEYCODE_DPAD_CENTER:
                if (isPlaying) {
                    isPlaying = false;
                    mUnityPlayer.pause();
                } else {
                    mUnityPlayer.resume();
                    isPlaying = true;
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 暴露给unity的方法
     */
    public void method_XXX(){

    }
}
