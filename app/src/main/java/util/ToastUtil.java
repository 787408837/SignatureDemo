package util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import you.with.com.signature.R;


/**
 * ToastUtil
 *
 * @author With You
 * @version 1.0
 * @date 2016/6/4
 */
public class ToastUtil {

    private static Toast mToast = null;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
            mToast = null;//toast隐藏后，将其置为null
        }
    };

    /**
     * @param context
     * @param message
     */
    public static void show(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_item, null);//自定义布局
        ScreenAdapterTools.getInstance().loadView(view);
        TextView text = view.findViewById(R.id.toast_text);//显示的提示文字
        text.setText(message);
        mHandler.removeCallbacks(r);
        if (mToast == null) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.BOTTOM, 0, 150);
            mToast.setView(view);
        }
        mHandler.postDelayed(r, 1500);//延迟1.5秒隐藏toast
        mToast.show();
    }

    /**
     * @param context
     * @param resId
     */
    public static void show(Context context, int resId) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_item, null);//自定义布局
        ScreenAdapterTools.getInstance().loadView((ViewGroup) view);
        TextView text = view.findViewById(R.id.toast_text);//显示的提示文字
        text.setText(context.getString(resId));
        mHandler.removeCallbacks(r);
        if (mToast == null) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.BOTTOM, 0, 150);
            mToast.setView(view);
        }
        mHandler.postDelayed(r, 1500);//延迟1.5秒隐藏toast
        mToast.show();
    }

    /**
     * @param context
     * @param message
     */
    public static void showLong(Context context, String message) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_item, null);//自定义布局
        ScreenAdapterTools.getInstance().loadView(view);
        TextView text = view.findViewById(R.id.toast_text);//显示的提示文字
        text.setText(message);
        mHandler.removeCallbacks(r);
        if (mToast == null) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.BOTTOM, 0, 150);
            mToast.setView(view);
        }
        mHandler.postDelayed(r, 2000);//延迟2秒隐藏toast
        mToast.show();
    }

    /**
     * @param context
     * @param resId
     */
    public static void showLong(Context context, int resId) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_item, null);//自定义布局
        ScreenAdapterTools.getInstance().loadView((ViewGroup) view);
        TextView text = view.findViewById(R.id.toast_text);//显示的提示文字
        text.setText(context.getString(resId));
        mHandler.removeCallbacks(r);
        if (mToast == null) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.BOTTOM, 0, 150);
            mToast.setView(view);
        }
        mHandler.postDelayed(r, 2000);//延迟2秒隐藏toast
        mToast.show();
    }

}
