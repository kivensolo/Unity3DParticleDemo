package com.DefaultCompany.JM_tst;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.unity3d.player.UnityPlayer;

/**
 * 包含UnitPlayer的Activity
 *
 * Android调用Unity的方法：
 *   // 第一个参数是unity中的交互脚本所挂在的物体的名字，第二个是方法名，第三个是参数
 *   // 下面这行代码的意思是交互脚本挂在了LoadingManager上，交互方法叫message
 *   UnityPlayer.UnitySendMessage("LoadingManager", "message", type);
 *
 */
public class UnityPlayerActivity extends Activity {
    //NOTICE: 不要改变这个变量的名称; native代码会使用
    protected UnityPlayer mUnityPlayer;

    // Override this in your custom UnityPlayerActivity to tweak the command line arguments passed to the Unity Android Player
    // The command line arguments are passed as a string, separated by spaces
    // UnityPlayerActivity calls this from 'onCreate'
    // Supported: -force-gles20, -force-gles30, -force-gles31, -force-gles31aep, -force-gles32, -force-gles, -force-vulkan
    // See https://docs.unity3d.com/Manual/CommandLineArguments.html
    // @param cmdLine the current command line arguments, may be null
    // @return the modified command line string or null
    protected String updateUnityCommandLineArguments(String cmdLine) {
        return cmdLine;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        String cmdLine = updateUnityCommandLineArguments(getIntent().getStringExtra("unity"));
        getIntent().putExtra("unity", cmdLine);

        mUnityPlayer = new UnityPlayer(this);
        setContentView(mUnityPlayer);
        mUnityPlayer.requestFocus();

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
        lps.gravity = Gravity.CENTER;
        addContentView(view,lps);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }

    // Quit Unity
    @Override
    protected void onDestroy() {
        mUnityPlayer.destroy();
        super.onDestroy();
    }

    // Pause Unity
    @Override
    protected void onPause() {
        super.onPause();
        mUnityPlayer.pause();
    }

    // Resume Unity
    @Override
    protected void onResume() {
        super.onResume();
        mUnityPlayer.resume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUnityPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUnityPlayer.stop();
    }

    // Low Memory Unity
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL) {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    /*API12*/
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    /**
     * 暴露给unity的方法
     */
    public void method_XXX(){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
