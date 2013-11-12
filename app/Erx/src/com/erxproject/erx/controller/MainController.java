package com.erxproject.erx.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.erxproject.erx.R;
import com.erxproject.erx.library.DatabaseHandler;
import com.erxproject.erx.library.JSONParser;

import android.content.Context;

public class MainController {

	private JSONParser jsonParser;
	private Context mContext;
	private String site;
	private String mainExtension;
	private String registerExtension;
	private static String login_tag = "login"; //$NON-NLS-1$
	private static String register_tag = "register"; //$NON-NLS-1$

	// constructor
	public MainController(Context context) {
		jsonParser = new JSONParser();
		mContext = context.getApplicationContext();
		site = mContext.getString(R.string.site);
		mainExtension = mContext.getString(R.string.main_extension);
		registerExtension = mContext.getString(R.string.register_extension);
	}

	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn() {
		DatabaseHandler db = new DatabaseHandler(mContext);
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
		JSONObject json = jsonParser.getJSONFromUrl(site + mainExtension,
				params);
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
		JSONObject json = jsonParser.getJSONFromUrl(site + registerExtension,
				params);
		// return json
		return json;
	}

}
