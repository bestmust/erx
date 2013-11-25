package com.erxproject.erx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

public class DiseaseDiagnosedListActivity extends SingleFragmentActivityWithAdd {

	Button addButton;
	
	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new DiseaseDiagnosedListFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addButton = (Button) findViewById(R.id.addButton);
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(DiseaseDiagnosedListActivity.this,
						DiseaseDiagnosedAddEditFragment.class);
				startActivity(i);
			}

		});
	}

}
