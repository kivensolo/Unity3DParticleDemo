package com.kingz.welcome;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.com.yomo.unity.ShowU3DActivity;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createContentView());
        hideNavigation();
    }

    /**
     * 隐藏设备导航栏UI
     */
    private void hideNavigation() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private View createContentView() {
        TextView view = new TextView(this);
        view.setText(R.string.welcom_tips);
        view.setTextSize(48);
        view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        view.setSingleLine(true);
        view.setMarqueeRepeatLimit(-1);
        view.setFocusableInTouchMode(true);
        view.setFocusableInTouchMode(true);
        view.setTextColor(Color.WHITE);
        view.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
        view.setBackgroundColor(Color.BLACK);
        view.setOnClickListener(v -> {
//            Intent intent = new Intent(LoginActivity.this, JM_tst.UnityPlayerActivity.class);
//            startActivity(intent);

           startActivity(new Intent(this, ShowU3DActivity.class));
        });
        FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(900, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setGravity(Gravity.CENTER);
        view.setLayoutParams(lps);
        return view;
    }
}
