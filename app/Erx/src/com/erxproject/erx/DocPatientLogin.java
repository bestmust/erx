package com.erxproject.erx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DocPatientLogin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_docpatientlogin);
	}

	public void openPatientHomePage(View view) {
		Intent intent = new Intent(this, DocPatientHomePage.class);
		startActivity(intent);
		finish();
	}

	public void patientRegister(View view) {
		Intent intent = new Intent(this, PatientRegisterActivity.class);
		startActivity(intent);
	}

}
