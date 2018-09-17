package luwei.com.customview;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // >= 5.0
            //5.0 全透明实现
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setNavigationBarColor(Color.BLUE);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // >= 4.4
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        FlowLayout fl = findViewById(R.id.fl);
        List<String> list = new ArrayList<>();
        list.add("运动");
        list.add("学习");
        list.add("爱好");
        list.add("科技");
        list.add("爱好");
        list.add("科技");
        list.add("打篮球");
        list.add("这是一个标签");
        list.add("爱好");
        list.add("科技");
        list.add("打篮球");
        list.add("这是一个标签");
        fl.addTexts(list);
    }
}
