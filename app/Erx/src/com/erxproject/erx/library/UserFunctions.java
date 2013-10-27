package com.erxproject.erx.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class UserFunctions {

	private JSONParser jsonParser;
	private static String loginURL = Messages.getString("UserFunctions.site") + Messages.getString("UserFunctions.login_extn"); //$NON-NLS-1$

	private static String registerURL = Messages
			.getString("UserFunctions.site") + Messages.getString("UserFunctions.login_extn");//$NON-NLS-1$

	private static String login_tag = "login"; //$NON-NLS-1$
	private static String register_tag = "register"; //$NON-NLS-1$

	// constructor
	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn(Context context) {
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if (count > 0) {
			// user logged in
			return true;
		}
		return false;
	}

	/**
	 * function make Login Request
	 * 
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String email, String password) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("email", email)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("password", password)); //$NON-NLS-1$
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// Log.e("JSON", json.toString());
		return json;
	}

	/**
	 * Function to logout user Reset Database
	 * */
	public boolean logoutUser(Context context) {
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}

	/**
	 * function make Login Request
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * */
	public JSONObject registerUser(String name, String email, String password,
			String address, String contact) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("name", name)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("email", email)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("password", password)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("address", address)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("contact", contact)); //$NON-NLS-1$

		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		return json;
	}

}
