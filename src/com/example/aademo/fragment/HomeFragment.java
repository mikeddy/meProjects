package com.example.aademo.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aademo.ResideMenuActivity;
import com.example.aademo.R;
import com.example.aademo.widget.ResideMenu;

public class HomeFragment extends Fragment {

    private View parentView;
    private ResideMenu resideMenu;
    private ListView lv;
    private Activity mAc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.residemenu_home, container, false);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
    	mAc=getActivity();
        ResideMenuActivity parentActivity = (ResideMenuActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();
        lv=(ListView)parentView.findViewById(R.id.home_lv);
        lv.setAdapter(new HomeAdapter());
//        parentView.findViewById(R.id.btn_open_menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
//            }
//        });

        // add gesture operation's ignored views
//        FrameLayout ignored_view = (FrameLayout) parentView.findViewById(R.id.ignored_view);
//        resideMenu.addIgnoredView(ignored_view);
    }
    
    
    class HomeAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return 50;
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
			TextView tv;
			if(convertView==null){
				tv=new TextView(mAc);
				tv.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, 100));
//				tv.setLayoutParams(new layoutparam(LayoutParams.FILL_PARENT, 100));
				tv.setTextColor(Color.RED);
				convertView=tv;
			}
			else {
				tv=(TextView)convertView;
			}
			tv.setText("hello 这里是 position"+position);
			return convertView;
		}
    	
    }

}
