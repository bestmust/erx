package com.erxproject.erx;

import java.util.ArrayList;

import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Symptom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DocPatientHomeFragment extends Fragment {
	
	Button btnPrescription;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_doc_patient, parent,
				false);
		
		btnPrescription = (Button) v.findViewById(R.id.btnDPHomePrescription);		

		btnPrescription.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			
			Intent intent = new Intent(getActivity(), PrescriptionActivity.class);
					startActivity(intent);
				
			}
		});
		
		return v;
	}

}
