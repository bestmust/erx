package com.erxproject.erx.model;

import android.content.Context;

public class Patient {
	
	private static Patient sPatient;
	private Context mContext;
	private int personId;
	private String name;
	private String eMail;
	private String uid;
	private String createdAt;

	private Patient(Context context) {
		this.mContext = context.getApplicationContext();
	}

	public static Patient get(Context context) {
		if (sPatient == null) {
			sPatient = new Patient(context);
		}
		return sPatient;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

}
