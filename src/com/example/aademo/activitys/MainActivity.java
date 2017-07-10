package com.example.aademo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ListView;

import com.example.aademo.R;

import java.util.ArrayList;

import zxing.activity.CaptureActivity;

;

public class MainActivity extends BaseActivity {

    ListView lv;
    ArrayList<ItemBean>arraylist_itemBeans=new ArrayList<ItemBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	private void init(){
        lv=(ListView)findViewById(R.id.main_lv);
		arraylist_itemBeans.add(new ItemBean("滑动动画开关", SlideParent_Activity.class));
		arraylist_itemBeans.add(new ItemBean("自定义seekbar", CustomSeekBarActivity.class));
		arraylist_itemBeans.add(new ItemBean("横向进度条", HorizontalProgressBarActivity.class));
		arraylist_itemBeans.add(new ItemBean("层图片", LayersDrawableActivity.class));
		arraylist_itemBeans.add(new ItemBean("滑动遮盖效果", CoverActivity.class));
		arraylist_itemBeans.add(new ItemBean("圆形图片(带选择效果的)", CircleImageActivity.class));
		arraylist_itemBeans.add(new ItemBean("下拉放大效果", TouchScaleActivity.class));
		arraylist_itemBeans.add(new ItemBean("Text颜色渐变AND圆形进度条", CircleProgressActivity.class));
		arraylist_itemBeans.add(new ItemBean("自定义drawable.xml的深入研究", DrawableXMLActivity.class));
		arraylist_itemBeans.add(new ItemBean("横向滑动的listview", HorizontalListViewActivity.class));
		arraylist_itemBeans.add(new ItemBean("翻阅listview源码后根据自己的理解写出的一些小玩意", CustomViewGroupActivity.class));
		arraylist_itemBeans.add(new ItemBean("图片的手动控制旋转", WheelMenuActivity.class));
		arraylist_itemBeans.add(new ItemBean("仿新版QQ侧滑", ResideMenuActivity.class));
		arraylist_itemBeans.add(new ItemBean("雪花旋转进度条", SnowProgressActivity.class));
        arraylist_itemBeans.add(new ItemBean("带闪光灯的二维码扫描", CaptureActivity.class));
        arraylist_itemBeans.add(new ItemBean("Event Bus 全局通信DEMO", EventBusActivity.class));
        arraylist_itemBeans.add(new ItemBean("EffectPath 逐步动画实现", EffectPathActivity.class));
        arraylist_itemBeans.add(new ItemBean("自动改变字体大小以适应宽度的textview", ScaleTextViewActivity.class));
        arraylist_itemBeans.add(new ItemBean("不弹输入法edittext", NoSoftInputActivity.class));
        arraylist_itemBeans.add(new ItemBean("自定义小动画", AnimationActivity.class));
        arraylist_itemBeans.add(new ItemBean("高斯模糊效果", GaussoBscureActivity.class));
        arraylist_itemBeans.add(new ItemBean("上拉查看详情效果", DragToDetailActivity.class));
        arraylist_itemBeans.add(new ItemBean("支持双向滑动的listview(待调整)",TwoDirectListViewActivity.class));
        arraylist_itemBeans.add(new ItemBean("ButterKnife使用方法",ButterknifeActivity.class));
        arraylist_itemBeans.add(new ItemBean("关于手势传递的测试",EventDirActivity.class));
        arraylist_itemBeans.add(new ItemBean("性能优化(渲染)",OptimizeUIActivity.class));
        arraylist_itemBeans.add(new ItemBean("双ScrollView嵌套",ScrollActivity.class));
        arraylist_itemBeans.add(new ItemBean("SVG矢量缩放",SVGActivity.class));
        arraylist_itemBeans.add(new ItemBean("ViewDrawHelper的使用",ViewDragHelperActivity.class));
        arraylist_itemBeans.add(new ItemBean("webview兼容type=file类型",FileWebViewActivity.class));
        arraylist_itemBeans.add(new ItemBean("小游戏效果",AnimViewActivity.class));
        arraylist_itemBeans.add(new ItemBean("多个item的viewpager", MoreItemViewpagerActivity.class));
        arraylist_itemBeans.add(new ItemBean("Recyclerview用法", RecyclerViewActivity.class));
        arraylist_itemBeans.add(new ItemBean("来玩一玩RxAndroid", RxAndroidActivity.class));
        arraylist_itemBeans.add(new ItemBean("测试Activity", DeviceInfoActivity.class));

        HorizontalScrollView lin;

        MainAdapter adapter=new MainAdapter();
        lv.setAdapter(adapter);
	}


    class MainAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arraylist_itemBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Button btn;
            final ItemBean itembean=arraylist_itemBeans.get(position);
            if(convertView==null){
                convertView=getLayoutInflater().inflate(R.layout.main_item,null);
                btn=(Button)convertView.findViewById(R.id.main_item_btn);
                convertView.setTag(btn);
            }else{
                btn=(Button)convertView.getTag();
            }
            btn.setText(itembean.getName());
            btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, itembean.getCla());
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }


    class ItemBean{
        private String name;
        private Class<?>cla;
        public ItemBean(String n,Class<?> c){
         name=n;
         cla=c;
        }

        public String getName() {
            return name;
        }

        public Class<?> getCla() {
            return cla;
        }

    }
}
