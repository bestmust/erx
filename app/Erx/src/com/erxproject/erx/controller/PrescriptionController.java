package com.erxproject.erx.controller;

import com.erxproject.erx.R;
import com.erxproject.erx.library.JSONParser;

import android.content.Context;

public class PrescriptionController {
	
	Context mContext;
	JSONParser jsonParser;
	String site;
	String prescriptionExtension;

	public PrescriptionController(Context applicationContext) {
		mContext = applicationContext.getApplicationContext();
		jsonParser = new JSONParser();
		site = mContext.getString(R.string.site);
		prescriptionExtension = mContext.getString(R.string.prescription_extension);
	}

}
