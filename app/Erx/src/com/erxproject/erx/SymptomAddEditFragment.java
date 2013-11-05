package com.erxproject.erx;

import java.util.ArrayList;

import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Symptom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SymptomAddEditFragment extends Fragment {
	
	EditText inputSymptom;
	EditText inputDetails;
	Button btnSave;
	Button btnCancel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_symptom_add_edit, parent, false);
		
		inputSymptom = (EditText) v.findViewById(R.id.inputSymptomName);
		inputDetails = (EditText) v.findViewById(R.id.inputSymptomDetails);
		btnSave = (Button) v.findViewById(R.id.btnSymptomSave);
		btnCancel = (Button) v.findViewById(R.id.btnSymptomCancel);
		
		btnSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Symptom s = new Symptom();
				s.setName(inputSymptom.getText().toString());
				s.setDetails(inputDetails.getText().toString());
				
				Prescription prescription = Prescription.get(getActivity());
				ArrayList<Symptom> symptomList = prescription.getSymptoms();
				symptomList.add(s);
				getActivity().finish();
			}
		});
		
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		
		
	return v;
	}
}
