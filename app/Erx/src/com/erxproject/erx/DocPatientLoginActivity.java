package com.erxproject.erx;

import org.json.JSONException;
import org.json.JSONObject;

import com.erxproject.erx.controller.PatientController;
import com.erxproject.erx.library.DatabaseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DocPatientLoginActivity extends Activity {

	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docpatientlogin);
		inputEmail = (EditText) findViewById(R.id.PloginEmail);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

		setTitle(R.string.docpatientlogin_title);

	}

	public void openPatientHomePage(View view) {

		JSONObject json;
		String email = inputEmail.getText().toString();
		PatientController pc = new PatientController(getApplicationContext());

		json = pc.loginPatient(email);

		// check for login response
		try {
			if (json.getString(KEY_SUCCESS) != null) {
				loginErrorMsg.setText("");
				String res = json.getString(KEY_SUCCESS);
				if (Integer.parseInt(res) == 1) {
					
					//TODO create new Patient() here from the given json

					Intent intent = new Intent(this, DocPatientHomeActivity.class);
					startActivity(intent);
					finish();

				} else {
					// Error in login
					loginErrorMsg.setText(json.getString(KEY_ERROR_MSG));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void patientRegister(View view) {
		Intent intent = new Intent(this, PatientRegisterActivity.class);
		startActivity(intent);
	}

}
