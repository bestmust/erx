package com.erxproject.erx;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class Parameters extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_parameters);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

	}

}
