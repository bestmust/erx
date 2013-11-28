package com.erxproject.erx;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erxproject.erx.library.JSONParser;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			prescriptions = refreshPatientHistoryFromServer();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		PrescriptionAdapter adapter = new PrescriptionAdapter(prescriptions);
		setListAdapter(adapter);
	}

	private ArrayList<PrescriptionListItem> refreshPatientHistoryFromServer()
			throws JSONException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json;
		JSONArray jsonPrescriptionList;
		JSONObject jsonPrescription;
		ArrayList<PrescriptionListItem> prescriptionList;
		PrescriptionListItem tempPrescriptionListItem;
		int length = 1;
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		// TODO change the URL to get the history and also add the parameters.

		json = jsonParser.getJSONFromUrl(
				"http://192.168.1.4/android_api/test/json_array_test.php",
				params);
		jsonPrescriptionList = json.getJSONArray("patient_history");
		length = jsonPrescriptionList.length();

		prescriptionList = new ArrayList<PrescriptionListItem>();

		for (int i = 0; i < length; i++) {
			jsonPrescription = jsonPrescriptionList.getJSONObject(i);
			tempPrescriptionListItem = new PrescriptionListItem(
					jsonPrescription.getInt("patient_id"),
					jsonPrescription.getInt("person_id"),
					jsonPrescription.getInt("doctor_id"),
					jsonPrescription.getInt("history_id"),
					jsonPrescription.getString("date_modified"),
					jsonPrescription.getString("saved"));
			prescriptionList.add(tempPrescriptionListItem);
		}
		return prescriptionList;
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
