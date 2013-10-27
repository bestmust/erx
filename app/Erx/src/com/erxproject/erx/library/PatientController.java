package com.erxproject.erx.library;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class PatientController {
	private JSONParser jsonParser;
	private static String patientURL = Messages.getString("UserFunctions.site") + Messages.getString("UserFunctions.patient_extn"); //$NON-NLS-1$
	private static String login_tag = "login"; //$NON-NLS-1$
	private static String register_tag = "register"; //$NON-NLS-1$

	// constructor
	public PatientController() {
		jsonParser = new JSONParser();
	}

	public JSONObject registerPatient(String name, String email,
			String password, String address, String contact) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("name", name)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("email", email)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("password", password)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("address", address)); //$NON-NLS-1$
		params.add(new BasicNameValuePair("telephone", contact)); //$NON-NLS-1$

		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(patientURL, params);
		// return json
		return json;
	}

}
