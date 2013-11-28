package com.erxproject.erx.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.erxproject.erx.R;
import com.erxproject.erx.library.JSONParser;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.PrescriptionListItem;

import android.content.Context;
import android.widget.Toast;

public class PrescriptionController {

	Context mContext;
	JSONParser jsonParser;
	String site;
	String prescriptionExtension;
	String debuggerExtension;

	public PrescriptionController(Context applicationContext) {
		mContext = applicationContext.getApplicationContext();
		jsonParser = new JSONParser();
		site = mContext.getString(R.string.site);
		prescriptionExtension = mContext
				.getString(R.string.prescription_extension);
		debuggerExtension = mContext.getString(R.string.debugger_extension);
	}

	public ArrayList<PrescriptionListItem> getPatientHistory(int patientId,
			int doctorId) throws JSONException {
		JSONParser jsonParser = new JSONParser();
		JSONObject json;
		JSONArray jsonPrescriptionList;
		JSONObject jsonPrescription;
		ArrayList<PrescriptionListItem> prescriptionList = new ArrayList<PrescriptionListItem>();
		PrescriptionListItem tempPrescriptionListItem;
		int length;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", mContext
				.getString(R.string.tag_get_patient_history)));
		params.add(new BasicNameValuePair("patient_id", "" + patientId));
		params.add(new BasicNameValuePair("doctor_id", "" + doctorId));

		json = jsonParser.getJSONFromUrl(site + prescriptionExtension + "?"
				+ debuggerExtension, params);
		if (Integer.parseInt(json.getString("success")) == 1) {
			jsonPrescriptionList = json.getJSONArray("patient_history");
			length = jsonPrescriptionList.length();

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
		}
		return prescriptionList;
	}

	public Prescription getUnsavedPrescription(int patientId, int personId,
			int doctorId) throws NumberFormatException, JSONException {

		Prescription p;
		JSONObject json, jsonUnsavedPrescription, jsonNewPrescription;
		p = Prescription.getNewPrescription(mContext);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", mContext
				.getString(R.string.tag_check_unsaved_prescription)));
		params.add(new BasicNameValuePair("patient_id", "" + patientId));
		params.add(new BasicNameValuePair("doctor_id", "" + doctorId));

		json = jsonParser.getJSONFromUrl(site + prescriptionExtension + "?"
				+ debuggerExtension, params);
		if (Integer.parseInt(json.getString("success")) == 1) {
			jsonUnsavedPrescription = json
					.getJSONObject("unsaved_prescription");
			p.getPrescriptionFromJSON(jsonUnsavedPrescription);
		} else if (Integer.parseInt(json.getString("error")) == 1) {
			// insert a new unsaved prescription in the database and then create
			// the prescription object.
			params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("tag", mContext
					.getString(R.string.tag_create_new_prescription)));
			params.add(new BasicNameValuePair("patient_id", "" + patientId));
			params.add(new BasicNameValuePair("person_id", "" + personId));
			params.add(new BasicNameValuePair("doctor_id", "" + doctorId));

			json = jsonParser.getJSONFromUrl(site + prescriptionExtension + "?"
					+ debuggerExtension, params);
			if (Integer.parseInt(json.getString("success")) == 1) {
				jsonNewPrescription = json.getJSONObject("new_prescription");
				p.getPrescriptionFromJSON(jsonNewPrescription);
			} else if (Integer.parseInt(json.getString("error")) == 1) {
				
				Toast t = Toast.makeText(mContext,
						"Error is creating new prescription", Toast.LENGTH_SHORT);
				t.show();

			}
		}

		return p;
	}

}
