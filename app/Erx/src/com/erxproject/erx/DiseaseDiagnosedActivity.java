package com.erxproject.erx;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class DiseaseDiagnosedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diseasediagnosed);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		setTitle(R.string.disease_title);

	}

}
