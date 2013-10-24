package com.erxproject.erx;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;

public class PatientRegisterActivity extends FragmentActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.a_patientregister);
		
		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.patientRegisterFragmentContainer);
		
		if (fragment == null) {
			fragment = new PatientRegisterFragment();
			fm.beginTransaction()
				.add(R.id.patientRegisterFragmentContainer, fragment)
				.commit();
		}
		
	}
	
	
	
	
}
