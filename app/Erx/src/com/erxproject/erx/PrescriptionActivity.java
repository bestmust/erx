package com.erxproject.erx;

import org.json.JSONException;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Doctor;
import com.erxproject.erx.model.Patient;
import com.erxproject.erx.model.Prescription;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PrescriptionActivity extends Activity {

	Prescription prescription;
	Patient patient;
	Doctor doctor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription);
		setTitle(R.string.new_prescription_title);

		patient = Patient.get(getApplicationContext());
		doctor = Doctor.get(getApplicationContext());
		//prescription = Prescription.getNewPrescription(getApplicationContext());

		PrescriptionController pc = new PrescriptionController(
				getApplicationContext());
		
		try {
			prescription = pc.getUnsavedPrescription(patient.getPatientId(),patient.getPersonId(),doctor.getDoctorId());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void openDiseaseDiagnosed(View view) {
		Intent intent = new Intent(this, DiseaseDiagnosedActivity.class);
		startActivity(intent);
	}

	public void openMedicines(View view) {
		Intent intent = new Intent(this, Medicines.class);
		startActivity(intent);
	}

	public void openParameters(View view) {
		Intent intent = new Intent(this, Parameters.class);
		startActivity(intent);
	}

	public void openSymptoms(View view) {
		// Intent intent = new Intent(this, Symptoms.class);
		Intent intent = new Intent(this, SymptomsListActivity.class);
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
