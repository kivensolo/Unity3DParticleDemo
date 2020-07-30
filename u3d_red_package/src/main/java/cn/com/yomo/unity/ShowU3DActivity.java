package cn.com.yomo.unity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * 展示Unity3D和Android互通信的demo.
 */
public class ShowU3DActivity extends UnityPlayerActivity {
    private static final String TAG = "ShowU3DActivity";
    private String jsonData = "{\"scene\":\"1\",\"getuser\":{\"name\":\"\",\"id\":\"\"," +
            "\"password\":\"\",\"url\":\"\",\"info\":\"\"}," +
            "\"giveuser\":{\"number\":\"\",\"packetid\":3748,\"info\":\"\",\"imageurl\":\"\"," +
            "\"name\":\"\",\"id\":\"\",\"url\":\"\"},\"other\":{\"latitude\":300.180322," +
            "\"longitude\":120.151592,\"address\":\"\",\"walknumber\":\"\"}}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 加载3D动画时间较长，为了更好的用户体验，自己实现了UnitySplashSDK这个类，
        // 主要是用来展示一个过渡界面和加载完成时隐藏过渡界面。
        SplashMask.getInstance().onCreate(savedInstanceState, mUnityPlayer, 1);

        /**
         * 与Unity工程师协定好的方法，都必须声明到对应的Activity中，否则就调不起3D界面，处于一直加载状态。
         * UnityPlayer是一个FrameLayout,3D的展示主要是这个类，一些FrameLayout的操作都适用于UnityPlayer,
         * 这也是为它添加过渡界面提供的可行性。
         *
         * 调起Unity3D的方法,与Unity工程师协定好传递的参数,这里的第三个就是一段jso
         * 参数1： unity中的交互脚本所挂在的物体的名字
         * 参数2： 方法名
         * 参数3： 参数值
         * Unity工程师去自己解析json，获取到数据，接着就调起Unity3D的动画。
         */
        mUnityPlayer.UnitySendMessage("Manager", "Manager", jsonData);
    }


    /**
     * 展示红包，与Unity工程师协定的方法名。（Unity调用android的方法）
     */
    @CsharpScript
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
     * //TODO
     * hideSplash合适在Unity端调用？？？
     */
    @CsharpScript
    public void hideSplash() {
        SplashMask.getInstance().onHideSplash();
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
