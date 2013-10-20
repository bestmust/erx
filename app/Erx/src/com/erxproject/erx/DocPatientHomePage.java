package com.erxproject.erx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
	 
	 public void savePrescription(View view) {
		 Context context = getApplicationContext();
		 CharSequence text = "Prescription Saved.";
		 int duration = Toast.LENGTH_SHORT;

		 Toast toast = Toast.makeText(context, text, duration);
		 toast.show();
	        finish();
	    }
	 
}
