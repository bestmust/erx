package com.erxproject.erx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erxproject.erx.controller.DoctorController;
import com.erxproject.erx.model.Doctor;

public class DoctorHomeActivity extends Activity {
	DoctorController doctorController;
	Button btnLogout;
	TextView title;
	TextView date;
	TextView lastLogin;
	TextView visitNumber;

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
		doctorController = new DoctorController(getApplicationContext());
		if (doctorController.isUserLoggedIn()) {
			// user already logged in show databoard
			setContentView(R.layout.activity_doctor_home);

			// get the doctor details
			Doctor d;
			d = doctorController.getUser();
			setTitle(d.getName() + "'s Home");

			btnLogout = (Button) findViewById(R.id.btnLogout);
			date = (TextView) findViewById(R.id.doctorHomeDate);
			lastLogin = (TextView) findViewById(R.id.doctorHomeLastVisit);
			visitNumber = (TextView) findViewById(R.id.doctorHomeVisitNumber);

			Date dateObject = new Date(System.currentTimeMillis());
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"EEE, d MMM yyyy");
			SimpleDateFormat lastVisitFormatter = new SimpleDateFormat(
					"dd/MM/yy hh:mm:ss");
			String s = dateFormatter.format(dateObject);

			date.setText(s);
			visitNumber.setText("Login Number: " + d.getLoginNumber());
			Date lastVisitDate = d.getLastVisitDate();
			if (lastVisitDate != null) {
				lastLogin.setText("Last Login: "
						+ lastVisitFormatter.format(d.getLastVisitDate()));
			} else {
				lastLogin.setText("");
			}
			btnLogout.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					doctorController.logoutUser(getApplicationContext());
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
		Intent intent = new Intent(this, DoctorPatientLoginActivity.class);
		startActivity(intent);
	}
}