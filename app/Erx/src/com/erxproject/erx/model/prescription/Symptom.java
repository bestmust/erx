package com.erxproject.erx.model.prescription;

public class Symptom {
	private String mName;
	private String mDetails;
	private int mSymptomId;
	private int mHistoryId;
	
	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	@Override
	public String toString() {
		return mName;
	}

	public String getDetails() {
		return mDetails;
	}

	public void setDetails(String mDetails) {
		this.mDetails = mDetails;
	}
}
