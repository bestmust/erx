package com.erxproject.erx;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class DocPatientHomePage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_docpatienthomepage);
	}
	
	 public void openSymptoms(View view) {
	        Intent intent = new Intent(this, Symptoms.class);
	        startActivity(intent);
	    }
	 
	 public void openParameters(View view) {
	        Intent intent = new Intent(this, Parameters.class);
	        startActivity(intent);
	    }
	 
	 public void openDiseaseDiagnosed(View view) {
	        Intent intent = new Intent(this, DiseaseDiagnosed.class);
	        startActivity(intent);
	    }
	 

	 public void openMedicines(View view) {
	        Intent intent = new Intent(this, Medicines.class);
	        startActivity(intent);
	    }
	 
}
