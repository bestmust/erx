package com.erxproject.erx;

import java.util.ArrayList;

import org.json.JSONException;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Patient;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Parameter;
import com.erxproject.erx.model.prescription.Symptom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ParametersListFragment extends ListFragment {

	private ArrayList<Parameter> mParameters;
	private Prescription tempPrescription;
	private PrescriptionController prescriptionController;
	private Patient patient;
	private Prescription prescription;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle("Parameters");
		patient = Patient.get(getActivity());
		prescriptionController = new PrescriptionController(getActivity());
		prescription = Prescription.get(getActivity());
		
		try {
			mParameters = prescriptionController.getParameterList(prescription.getHistoryId());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prescription.setParameters(mParameters);

		ParametersAdapter adapter = new ParametersAdapter(mParameters);

		setListAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		((ParametersAdapter) getListAdapter()).notifyDataSetChanged();
	}

	private class ParametersAdapter extends ArrayAdapter<Parameter> {

		public ParametersAdapter(ArrayList<Parameter> Parameters) {
			super(getActivity(), 0, Parameters);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_parameter, null);
			}

			Parameter p = getItem(position);

			TextView parameterNameTextView = (TextView) convertView
					.findViewById(R.id.parameterNameTextView);
			TextView parameterValueTextView = (TextView) convertView
					.findViewById(R.id.parameterValueTextView);
			parameterNameTextView.setText(p.getmParameterType());
			parameterValueTextView.setText(p.getmParameterValue());

			return convertView;
		}

	}

}
