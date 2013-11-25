package com.erxproject.erx;

import java.util.ArrayList;

import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Disease;
import com.erxproject.erx.model.prescription.Symptom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DiseaseDiagnosedListFragment extends ListFragment {

	private ArrayList<Disease> mDiseases;
	private Prescription tempPrescription;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// getActivity().setTitle(R.string.symptoms_title);
		tempPrescription = Prescription.get(getActivity());
		mDiseases = tempPrescription.getDisease();

		DiseaseAdapter adapter = new DiseaseAdapter(mDiseases);

		setListAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		((DiseaseAdapter) getListAdapter()).notifyDataSetChanged();
	}

	private class DiseaseAdapter extends ArrayAdapter<Disease> {

		public DiseaseAdapter(ArrayList<Disease> Diseases) {
			super(getActivity(), 0, Diseases);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_disease, null);
			}

			Disease d = getItem(position);
			//
			// TextView symptomNumberTextView = (TextView) convertView
			// .findViewById(R.id.symptomNumberTextView);
			// TextView symptomTitleTextView = (TextView) convertView
			// .findViewById(R.id.symptomTitleTextView);
			// symptomNumberTextView.setText("" + position + " ");
			// symptomTitleTextView.setText(s.getName());

			return convertView;
		}

	}
}
