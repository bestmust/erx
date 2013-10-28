package com.erxproject.erx.model;

import java.util.ArrayList;

import android.content.Context;
import com.erxproject.erx.model.prescription.*;

public class Prescription {
	
	private Context mContext;
	private ArrayList<Symptom> mSymptoms;
	
	public Prescription(Context context) {
		mContext = context;
		mSymptoms = new ArrayList<Symptom>();
		
		for(int i=0;i<10;i++) {
			Symptom s = new Symptom();
			s.setName("symptom " + i);
			mSymptoms.add(s);
		}
		
	}

	public ArrayList<Symptom> getSymptoms() {
		return mSymptoms;
	}
	
}
