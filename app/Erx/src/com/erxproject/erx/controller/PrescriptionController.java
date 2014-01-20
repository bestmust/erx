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
import com.erxproject.erx.model.prescription.Parameter;
import com.erxproject.erx.model.prescription.Symptom;

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
						"Error is creating new prescription",
						Toast.LENGTH_SHORT);
				t.show();

			}
		}

		return p;
	}
	
	public boolean savePrescription(int historyId) throws NumberFormatException, JSONException {
		
		JSONObject json;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", mContext
				.getString(R.string.tag_save_prescription)));
		params.add(new BasicNameValuePair("history_id", "" + historyId));

		json = jsonParser.getJSONFromUrl(site + prescriptionExtension + "?"
				+ debuggerExtension, params);
		if (Integer.parseInt(json.getString("success")) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public ArrayList<Symptom> getSymptomList(int historyId) throws NumberFormatException, JSONException {
		JSONObject json,jsonSymptom;
		JSONArray jsonSymptomList;
		Symptom tempSymptom;
		ArrayList<Symptom> symptomList = new ArrayList<Symptom>();
		int length;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag",mContext.getString(R.string.tag_get_symptoms)));
		params.add(new BasicNameValuePair(mContext.getString(R.string.key_history_id),"" + historyId));
		
		json = jsonParser.getJSONFromUrl(site + prescriptionExtension, params);
		
		if (Integer.parseInt(json.getString("success")) == 1) {
			jsonSymptomList = json.getJSONArray("symptoms");
			length = jsonSymptomList.length();

			for (int i = 0; i < length; i++) {
				jsonSymptom = jsonSymptomList.getJSONObject(i);
				tempSymptom = new Symptom(
						jsonSymptom.getString("symptom"),
						jsonSymptom.getInt("symptom_id"),
						jsonSymptom.getInt("history_id"));
				symptomList.add(tempSymptom);
			}
			return symptomList;
		}
		else {
			return symptomList;
		}
		
	}
	
	public int saveSymptom(int historyId, String symptom) {
		JSONObject json;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag",mContext.getString(R.string.tag_save_symptom)));
		params.add(new BasicNameValuePair(mContext.getString(R.string.key_history_id),"" + historyId));
		params.add(new BasicNameValuePair(mContext.getString(R.string.key_symptom),"" + symptom));
		
		json = jsonParser.getJSONFromUrl(site + prescriptionExtension, params);
		
		try {
			if (Integer.parseInt(json.getString("success")) == 1) {
				int symptomId = json.getInt("symptom_id");
				return symptomId;
			}
			else {
				return -1;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public Symptom getSymptom(int symptomId) {
		JSONObject json,jsonSymptom;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag",mContext.getString(R.string.tag_get_symptom_from_symptom_id)));
		params.add(new BasicNameValuePair(mContext.getString(R.string.key_symptom_id),"" + symptomId));
		
		json = jsonParser.getJSONFromUrl(site + prescriptionExtension, params);
		
		try {
			if (Integer.parseInt(json.getString("success")) == 1) {
				jsonSymptom = json.getJSONObject("symptom");
				Symptom s = new Symptom(jsonSymptom.getString("symptom"),jsonSymptom.getInt("symptom_id"),jsonSymptom.getInt("history_id"));
				return s;
			}
			else {
				return null;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ArrayList<Parameter> getParameterList(int historyId) throws NumberFormatException, JSONException {
		JSONObject json,jsonParameter;
		JSONArray jsonParameterList;
		Parameter tempParameter;
		ArrayList<Parameter> parameterList = new ArrayList<Parameter>();
		int length;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag",mContext.getString(R.string.tag_get_parameters)));
		params.add(new BasicNameValuePair(mContext.getString(R.string.key_history_id),"" + historyId));
		
		json = jsonParser.getJSONFromUrl(site + prescriptionExtension, params);
		
		if (Integer.parseInt(json.getString("success")) == 1) {
			jsonParameterList = json.getJSONArray("parameters");
			length = jsonParameterList.length();

			for (int i = 0; i < length; i++) {
				jsonParameter = jsonParameterList.getJSONObject(i);
				tempParameter = new Parameter(
						jsonParameter.getInt("parameter_id"),
						jsonParameter.getInt("history_id"),
						jsonParameter.getString("parameter_type"),
						jsonParameter.getString("value"));
				parameterList.add(tempParameter);
			}
			return parameterList;
		}
		else {
			return parameterList;
		}
		
	}
	
	public int saveParameter(int historyId, String parameterType, String value) {
		JSONObject json;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag",mContext.getString(R.string.tag_save_parameter)));
		params.add(new BasicNameValuePair(mContext.getString(R.string.key_history_id),"" + historyId));
		params.add(new BasicNameValuePair("parameter_type","" + parameterType));
		params.add(new BasicNameValuePair("value","" + value));
		
		json = jsonParser.getJSONFromUrl(site + prescriptionExtension, params);
		
		try {
			if (Integer.parseInt(json.getString("success")) == 1) {
				int parameterId = json.getInt("parameter_id");
				return parameterId;
			}
			else {
				return -1;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public Parameter getParameter(int parameterId) {
		JSONObject json,jsonParameter;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag",mContext.getString(R.string.tag_get_parameter_from_parameter_id)));
		params.add(new BasicNameValuePair("parameter_id","" + parameterId));
		
		json = jsonParser.getJSONFromUrl(site + prescriptionExtension, params);
		
		try {
			if (Integer.parseInt(json.getString("success")) == 1) {
				jsonParameter = json.getJSONObject("parameter");
				Parameter p = new Parameter(jsonParameter.getInt("parameter_id"),jsonParameter.getInt("history_id"),jsonParameter.getString("parameter_type"),jsonParameter.getString("value"));
				return p;
			}
			else {
				return null;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
