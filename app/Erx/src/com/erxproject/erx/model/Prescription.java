package com.erxproject.erx.model;

import java.util.ArrayList;

import android.content.Context;
import com.erxproject.erx.model.prescription.*;

public class Prescription {

	private int patientId;
	private int dictorId;
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
		mSymptoms = new ArrayList<Symptom>();

		for (int i = 0; i < 10; i++) {
			Symptom s = new Symptom();
			s.setName("symptom " + i);
			mSymptoms.add(s);
		}
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

}
