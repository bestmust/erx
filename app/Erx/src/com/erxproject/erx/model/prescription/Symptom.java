package com.erxproject.erx.model.prescription;

public class Symptom {
	private String mName;

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
}
