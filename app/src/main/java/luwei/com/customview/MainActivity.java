package luwei.com.customview;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
