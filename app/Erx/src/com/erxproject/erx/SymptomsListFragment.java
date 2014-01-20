package com.erxproject.erx;

import java.util.ArrayList;

import org.json.JSONException;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Patient;
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
	private PrescriptionController prescriptionController;
	private Prescription prescription;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.symptoms_title);
		Patient.get(getActivity());
		prescriptionController = new PrescriptionController(getActivity());
		prescription = Prescription.get(getActivity());
		
		try {
			mSymptoms = prescriptionController.getSymptomList(prescription.getHistoryId());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prescription.setSymptoms(mSymptoms);
		
		SymptomsAdapter adapter = new SymptomsAdapter(mSymptoms);

		setListAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		((SymptomsAdapter) getListAdapter()).notifyDataSetChanged();
	}

	private class SymptomsAdapter extends ArrayAdapter<Symptom> {

		public SymptomsAdapter(ArrayList<Symptom> Symptoms) {
			super(getActivity(), 0, Symptoms);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_symptom, null);
			}

			Symptom s = getItem(position);

			TextView symptomNumberTextView = (TextView) convertView
					.findViewById(R.id.symptomNumberTextView);
			TextView symptomTitleTextView = (TextView) convertView
					.findViewById(R.id.symptomTitleTextView);
			symptomNumberTextView.setText("" + (position + 1) + " ");
			symptomTitleTextView.setText(s.getDetails());

			return convertView;
		}

	}
}
