package com.erxproject.erx;

import java.io.IOException;
import java.io.OutputStream;

import org.json.JSONException;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Doctor;
import com.erxproject.erx.model.Patient;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.print.MyPrintDocumentAdapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.print.PrintManager;
import android.view.View;
import android.widget.Toast;

public class PrescriptionActivity extends Activity {

	Prescription prescription;
	PrescriptionController pc;
	Patient patient;
	Doctor doctor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription);
		setTitle(R.string.new_prescription_title);

		patient = Patient.get(getApplicationContext());
		doctor = Doctor.get(getApplicationContext());
		// prescription =
		// Prescription.getNewPrescription(getApplicationContext());

		pc = new PrescriptionController(getApplicationContext());

		try {
			prescription = pc.getUnsavedPrescription(patient.getPatientId(),
					patient.getPersonId(), doctor.getDoctorId());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void openDiseaseDiagnosed(View view) {
		Intent intent = new Intent(this, DiseaseDiagnosedListActivity.class);
		startActivity(intent);
	}

	public void openMedicines(View view) {
		Intent intent = new Intent(this, PrescriptionMedicineListActivity.class);
		startActivity(intent);
	}

	public void openParameters(View view) {
		Intent intent = new Intent(this, ParametersListActivity.class);
		startActivity(intent);
	}

	public void openSymptoms(View view) {
		// Intent intent = new Intent(this, Symptoms.class);
		Intent intent = new Intent(this, SymptomsListActivity.class);
		startActivity(intent);
	}

	public void savePrescription(View view) throws NumberFormatException,
			JSONException {
		Context context = getApplicationContext();

		boolean isSaved;

		isSaved = pc.savePrescription(prescription.getHistoryId());
		CharSequence text;
		if (isSaved) {
			text = "Prescription Saved.";
		} else {
			text = "Error in saving Prescription";
		}

		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		finish();
	}

	@TargetApi(19)
	public void printPrescription(View view) throws IOException {
		
		//Check if the Android version is 4.4 or higher.
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
			Toast t = Toast.makeText(getApplicationContext(),
					String.format("This feature is available in Android 4.4 or higher.\nYour version: Android %s", Build.VERSION.RELEASE), Toast.LENGTH_SHORT);
			t.show();
			return;
		}

		// Get a PrintManager instance
		PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);

		// Set job name, which will be displayed in the print queue
		String jobName = getString(R.string.app_name) + "_Document";

		// Start a print job, passing in a PrintDocumentAdapter implementation
		// to handle the generation of a print document
		printManager.print(jobName,
				new MyPrintDocumentAdapter(this.getApplicationContext()), null); //

	}

}
