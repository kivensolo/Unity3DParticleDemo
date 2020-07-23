package com.DefaultCompany.JM_tst;

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

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(createContentView());
    }

    private View createContentView() {
        TextView view = new TextView(this);
        view.setText("Click Open Unity Player Page");
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
            Intent intent = new Intent(LoginActivity.this, UnityPlayerActivity.class);
            startActivity(intent);
        });
        FrameLayout.LayoutParams lps = new FrameLayout.LayoutParams(900, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setGravity(Gravity.CENTER);
        view.setLayoutParams(lps);
        return view;
    }
}
