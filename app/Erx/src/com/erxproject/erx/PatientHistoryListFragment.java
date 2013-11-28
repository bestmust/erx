package com.erxproject.erx;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.library.JSONParser;
import com.erxproject.erx.model.Doctor;
import com.erxproject.erx.model.Patient;
import com.erxproject.erx.model.PrescriptionListItem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PatientHistoryListFragment extends ListFragment {

	private ArrayList<PrescriptionListItem> prescriptions;
	private PrescriptionController prescriptionController;
	private Patient patient;
	private Doctor doctor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		patient = Patient.get(getActivity());
		doctor = Doctor.get(getActivity());
		prescriptionController = new PrescriptionController(getActivity());

		try {
			prescriptions = prescriptionController.getPatientHistory(
					patient.getPatientId(), doctor.getDoctorId());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrescriptionAdapter adapter = new PrescriptionAdapter(prescriptions);
		setListAdapter(adapter);
	}

	private class PrescriptionAdapter extends
			ArrayAdapter<PrescriptionListItem> {

		public PrescriptionAdapter(ArrayList<PrescriptionListItem> prescriptions) {
			super(getActivity(), 0, prescriptions);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_prescription, null);
			}

			PrescriptionListItem p = getItem(position);
			TextView prescriptionTextView = (TextView) convertView
					.findViewById(R.id.prescriptionTextView);
			prescriptionTextView.setText("" + p.getHistoryId() + " "
					+ p.getDateCreated());

			return convertView;
		}

	}

}
