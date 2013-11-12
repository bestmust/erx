package com.erxproject.erx.model;

import android.content.Context;

public class Doctor {

	private static Doctor sDoctor;
	private Context mContext;

	private Doctor(Context context) {
		this.mContext = context.getApplicationContext();
	}

	public static Doctor get(Context context) {
		if (sDoctor == null) {
			sDoctor = new Doctor(context);
		}
		return sDoctor;
	}

}
