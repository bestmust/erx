package com.erxproject.erx.model;

import java.util.ArrayList;

import android.content.Context;
import com.erxproject.erx.model.prescription.*;

public class Prescription {

	private Context mContext;
	private ArrayList<Symptom> mSymptoms;
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

	public ArrayList<Symptom> getSymptoms() {
		return mSymptoms;
	}

}
