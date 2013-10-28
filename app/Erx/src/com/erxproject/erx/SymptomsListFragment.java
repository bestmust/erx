package com.erxproject.erx;

import java.util.ArrayList;

import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Symptom;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class SymptomsListFragment extends ListFragment {
	
	private ArrayList<Symptom> mSymptoms;
	private Prescription tempPrescription;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.symptoms_title);
		tempPrescription = new Prescription(getActivity());
		mSymptoms = tempPrescription.getSymptoms();
		
		ArrayAdapter<Symptom> adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,mSymptoms);
		
		setListAdapter(adapter);
		
	}
	
}
