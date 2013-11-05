package com.erxproject.erx;

import java.util.ArrayList;

import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Symptom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SymptomsListFragment extends ListFragment {

	private ArrayList<Symptom> mSymptoms;
	private Prescription tempPrescription;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.symptoms_title);
		tempPrescription = Prescription.get(getActivity());
		mSymptoms = tempPrescription.getSymptoms();

		SymptomsAdapter adapter = new SymptomsAdapter(mSymptoms);
		
		setListAdapter(adapter);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((SymptomsAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	private class SymptomsAdapter extends ArrayAdapter<Symptom> {

		public SymptomsAdapter(ArrayList<Symptom> Symptoms) {
			super(getActivity(), 0, Symptoms);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_symptom, null);
			}
			
			Symptom s = getItem(position);
			
			TextView symptomNumberTextView = (TextView) convertView.findViewById(R.id.symptomNumberTextView);
			TextView symptomTitleTextView = (TextView) convertView.findViewById(R.id.symptomTitleTextView);
			symptomNumberTextView.setText("" + position + " ");
			symptomTitleTextView.setText(s.getName());
			
			return convertView;
		}

	}
}
