package cn.com.yomo.unity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Administrator on 2018/4/12.
 * Demo源自 https://github.com/DingMouRen/UnityDemo
 */

public class ShowU3DActivity extends UnityPlayerActivity {
    private static final String TAG = "ShowU3DActivity";
    private String str = "{\"scene\":\"1\",\"getuser\":{\"name\":\"\",\"id\":\"\",\"password\":\"\",\"url\":\"\",\"info\":\"\"},\"giveuser\":{\"number\":\"\",\"packetid\":3748,\"info\":\"\",\"imageurl\":\"\",\"name\":\"\",\"id\":\"\",\"url\":\"\"},\"other\":{\"latitude\":300.180322,\"longitude\":120.151592,\"address\":\"\",\"walknumber\":\"\"}}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UnitySplashSDK.getInstance().onCreate(savedInstanceState, mUnityPlayer, 1);
        mUnityPlayer.UnitySendMessage("Manager", "Manager", str);
    }

    /**
     * 展示红包，与Unity工程师协定的方法名。（Unity调用android的方法）
     */
    public void ShowRedPacket() {
        Log.e(TAG,"ShowRedPacket所在线程："+Thread.currentThread().getName());//这里的线程是Unity main线程，需要转换到android的主线程中操作自己的业务逻辑
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showMyDialog();
            }
        });
    }


    /**
     * 隐藏过渡界面，与Unity工程师协定的方法名。（Unity调用android的方法）
     */
    public void hideSplash() {
        UnitySplashSDK.getInstance().onHideSplash();
    }

    //---------------------------  自己的业务逻辑  -----------------------------------

    /**
     * 展示对话框
     */
    private void showMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Unity调用android的方法展示出来的对话框");
        builder.setNeutralButton("关闭u3d界面", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                closeUnity3D();
            }
        });
        builder.create().show();
    }

    /**
     * 关闭Unity3D
     */
    private void closeUnity3D() {
        finish();
    }

}
