package com.example.aademo;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.aademo.widget.MyDialogFragment;

public class DialogFragement_Activity extends BaseActivity {
	Button btn;

	int  mStackLevel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentdialo_main);
		btn = (Button) findViewById(R.id.btn_show);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 mStackLevel++;
				 FragmentTransaction ft = getFragmentManager().beginTransaction();
				    Fragment prev = getFragmentManager().findFragmentByTag("dialog");
				    if (prev != null) {
				        ft.remove(prev);
				    }
				    ft.addToBackStack(null);

				    // Create and show the dialog.
				    DialogFragment newFragment = MyDialogFragment.newInstance(mStackLevel);
				    newFragment.show(ft, "dialog");
			}
		});
	}
}
