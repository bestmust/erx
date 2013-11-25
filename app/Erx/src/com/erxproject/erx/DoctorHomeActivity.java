package com.erxproject.erx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erxproject.erx.controller.MainController;

public class DoctorHomeActivity extends Activity {
	MainController mainController;
	Button btnLogout;
	TextView title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String name = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);

		setTitle(name + "'s Home");

		/**
		 * Dashboard Screen for the application
		 * */
		// Check login status in database
		mainController = new MainController(getApplicationContext());
		if (mainController.isUserLoggedIn()) {
			// user already logged in show databoard
			setContentView(R.layout.activity_doctor_home);
			btnLogout = (Button) findViewById(R.id.btnLogout);

			btnLogout.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					mainController.logoutUser(getApplicationContext());
					Intent login = new Intent(getApplicationContext(),
							LoginActivity.class);
					login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(login);
					// Closing dashboard screen
					finish();
				}
			});

		} else {
			// user is not logged in show login screen
			Intent login = new Intent(getApplicationContext(),
					LoginActivity.class);
			login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(login);
			// Closing dashboard screen
			finish();
		}
	}

	public void openLoginPatient(View view) {
		Intent intent = new Intent(this, DocPatientLoginActivity.class);
		startActivity(intent);
	}
}