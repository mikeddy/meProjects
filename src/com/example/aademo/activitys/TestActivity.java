package com.example.aademo.activitys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aademo.R;

/**
 * Created by mik_eddy on 15/8/14.
 */
public class TestActivity extends BaseActivity {
    Button btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isFromOtherApp(getIntent())){
            Uri uri = getIntent().getData();
            if(uri==null)return;
            String token=uri.getQueryParameter("token");
            showToast("token:"+token);
        }
    }

    // 是否是从其他APP跳转过来
    public static boolean isFromOtherApp(Intent intent) {
        if (intent != null && intent.getScheme()!=null) {
            String scheme = intent.getScheme();
            if (scheme.equals("testDemo")) return true;
        }
        return false;
    }

    private void init() {
        btn_2 = (Button) findViewById(R.id.test_btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("generalprojects://api?scheme=testDemo&id=1583"));
                startActivity(intent);
            }
        });
    }
}
