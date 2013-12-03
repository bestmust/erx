package com.erxproject.erx.model;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.erxproject.erx.R;
import com.erxproject.erx.model.prescription.*;

public class Prescription {

	private int patientId;
	private int doctorId;
	private int historyId;
	private String dateCreated;
	private Context mContext;
	private ArrayList<Symptom> mSymptoms;
	private ArrayList<Parameter> mParamter;
	private ArrayList<Disease> mDisease;
	private ArrayList<Medicine> mMedicine;
	private Note mNote;
	private static Prescription mPrescription;

	private Prescription(Context context) {
		mContext = context.getApplicationContext();
	}

	public static Prescription get(Context context) {
		if (mPrescription == null) {
			mPrescription = new Prescription(context);
		}
		return mPrescription;
	}

	public static Prescription getNewPrescription(Context context) {
		if (mPrescription != null) {
			mPrescription = null;
		}
		mPrescription = new Prescription(context);
		return mPrescription;
	}

	public void getPrescriptionFromJSON(JSONObject json)
			throws NumberFormatException, JSONException {
		patientId = Integer.parseInt(json.getString(mContext
				.getString(R.string.key_patient_id)));
		doctorId = Integer.parseInt(json.getString(mContext
				.getString(R.string.key_doctor_id)));
		historyId = Integer.parseInt(json.getString(mContext
				.getString(R.string.key_history_id)));
		dateCreated = json.getString(mContext
				.getString(R.string.key_date_modified));
	}

	public ArrayList<Symptom> getSymptoms() {
		return mSymptoms;
	}

	public ArrayList<Parameter> getParameters() {
		return mParamter;
	}

	public ArrayList<Disease> getDisease() {
		return mDisease;
	}

	public ArrayList<Medicine> getMedicine() {
		return mMedicine;
	}

	public Note getNote() {
		return mNote;
	}

	public int getHistoryId() {
		return historyId;
	}

	public void setSymptoms(ArrayList<Symptom> mSymptoms) {
		this.mSymptoms = mSymptoms;
	}

}
