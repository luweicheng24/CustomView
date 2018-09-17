package luwei.com.customview;

import android.content.Context;

/**
 * Author   : luweicheng on 2018/9/17 12:32
 * E-mail   ：1769005961@qq.com
 * GitHub   : https://github.com/luweicheng24
 * function:
 **/

public class DisplayUtils {

    /**
     * @param context
     * @param px
     * @return
     */
    public static int px2Dp(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将dp转换成px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2Px(Context context, int dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 将文字的大小sp转换成px
     *
     * @param context
     * @param sp
     * @return
     */
    public static int sp2Px(Context context, int sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

    /**
     * 将文字大小px转换成sp
     *
     * @param context
     * @param px
     * @return
     */

    public static int px2Sp(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity; //文字的像素密度
        return (int) (px / scale + 0.5f);
    }
}
