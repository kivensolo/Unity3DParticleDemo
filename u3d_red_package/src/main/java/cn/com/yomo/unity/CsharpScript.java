package cn.com.yomo.unity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: King.Z <br>
 * date:  2020/7/30 23:13 <br>
 * description: unity与android通信的注解标记 <br>
 *     被此注解标记的函数名不可轻易改动，需保持与Unity端相同。
 *
 * Unity端这样调用:
 *      AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
 *      AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
 *      jo.Call("ShowRedPacket"); // 参数为函数名
 *
 * Unity3D调用Android接口的api：
 * 方法名      方法类型     说明
 * Call         实例    调用实例方法
 * Get          实例    获取实例变量（非静态）
 * Set          实例    设置实例变量（非静态）
 * CallStatic   静态    调用静态方法
 * GetStatic    静态    获取静态变量
 * SetStatic    静态    设置静态变量
 *
 *
 * 例如Android端定一个含参函数：
 * public String showMsg(String name,int age){
 *   // doSomthing in Unity main thread(not android ui thread.).
 *   ...
 *   return "result"
 * }
 * Unity工程中：
 * AndroidJavaClass jc = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
 * AndroidJavaObject jo = jc.GetStatic<AndroidJavaObject>("currentActivity");
 * string result = jo.Call("showMsg","小明",18);
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface CsharpScript {}
