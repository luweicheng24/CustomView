package luwei.com.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Author   : luweicheng on 2018/6/21 17:50
 * E-mail   ：1769005961@qq.com
 * GitHub   : https://github.com/luweicheng24
 * function: 自定义标签布局
 **/

public class FlowLayout extends ViewGroup {

    private List<String> list;
    private Context context;

    public FlowLayout(Context context) {
        super(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int width = getMeasuredWidth();
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        int lines = 0;
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();
            right = viewWidth + right;
            if (right > width) {
                left = 0;
                right = viewWidth;
                lines++;
                top = lines * viewHeight;
                bottom = top + viewHeight;
            } else {
                top = viewHeight * lines;
                bottom = top + viewHeight;
                left = right - viewWidth;
            }
            view.layout(left, top, right, bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (list == null || list.size() == 0)
            throw new IllegalArgumentException("need a string list ");
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        int childCount = list.size();
        addLabelViews(childCount);
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
        int usedWidth = 0;
        int remainWidth = 0;
        int totalHeight = 0;
        int lines = 1;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int width = view.getMeasuredWidth();
            int height = view.getMeasuredHeight();
            remainWidth = widthMeasureSize - width - usedWidth;
            if (remainWidth < 0) {
                usedWidth = width;
                lines++;
            } else {
                usedWidth += width;
            }
        }
        totalHeight = getChildAt(0).getMeasuredHeight() * lines;
        if (heightMeasureMode == MeasureSpec.AT_MOST || heightMeasureMode == MeasureSpec.UNSPECIFIED) {
            setMeasuredDimension(widthMeasureSize, totalHeight);
        } else if (heightMeasureMode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthMeasureSize, heightMeasureSize);
        }
    }

    private void addLabelViews(int childCount) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        lp.setMargins(10, 10, 10, 10);
        for (int i = 0; i < childCount; i++) {
            LinearLayout linearLayout = new LinearLayout(context);
            TextView tv = new TextView(context);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(15);
            tv.setPadding(20, 10, 20, 10);
            tv.setBackgroundResource(R.drawable.label_bg);
            tv.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            tv.setText(list.get(i));
            linearLayout.addView(tv, lp);
            addView(linearLayout);
        }
    }

    // 添加标签数据
    public void addTexts(List<String> list) {
        if (list == null || list.size() == 0)
            throw new IllegalArgumentException("need a string list ");
        this.list = list;
    }
}
